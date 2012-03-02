package com.github.mcaserta.versionlounge;

import com.github.mcaserta.versionlounge.xml.Project;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SvnBrowser {

    static {
        DAVRepositoryFactory.setup();
    }

    private boolean verbose;

    public void setVerbose() {
        this.verbose = true;
    }

    public List<ProjectVersion> getProjectVersions(List<Project> projects) throws SVNException {
        final List<ProjectVersion> projectVersions = new ArrayList<ProjectVersion>(projects.size());

        for (Project project : projects) {
            ProjectVersion projectVersion = getProjectVersion(project);

            if (projectVersion != null) {
                projectVersions.add(projectVersion);
            }
        }

        return projectVersions;
    }

    public ProjectVersion getProjectVersion(Project project) throws SVNException {
        if (verbose) {
            System.out.print("  * processing: ");
            System.out.print(project.getId());
            System.out.print(" ... ");
        }
        
        SVNRepository repository = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(project.getSvnTagsDirUrl()));
        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(project.getSvnUser(), project.getSvnPass());
        repository.setAuthenticationManager(authManager);
        final List<ProjectVersion> entries = getProjectVersions(repository, project);

        if (entries.size() > 0) {
            Collections.sort(entries, new ProjectVersionComparator());
            ProjectVersion projectVersion = entries.get(entries.size() - 1);
            if (verbose) {
                System.out.print(projectVersion.getVersion());
                System.out.println();
            }
            return projectVersion;
        } else {
            if (verbose) System.out.println();
            return null;
        }
    }

    private List<ProjectVersion> getProjectVersions(SVNRepository repository, Project project) throws SVNException {
        final List<ProjectVersion> result = new ArrayList<ProjectVersion>();
        final Collection entries = repository.getDir("", -1, null, (Collection) null);

        for (Object o : entries) {
            SVNDirEntry entry = (SVNDirEntry) o;
            if (entry.getKind() == SVNNodeKind.DIR) {
                if (entry.getName().startsWith(project.getTagName())) {
                    String version = entry.getName().substring(project.getTagName().length() + 1);
                    if (version.matches("^\\d[._\\d]*$")) {
                        ProjectVersion projectVersion = new ProjectVersion(project, version, entry.getURL().toString(), entry.getDate());
                        result.add(projectVersion);
                    }
                }
            }
        }

        return result;
    }

}
