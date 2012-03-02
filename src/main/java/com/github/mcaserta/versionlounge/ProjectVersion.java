package com.github.mcaserta.versionlounge;

import com.github.mcaserta.versionlounge.xml.Project;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Date;

public final class ProjectVersion {

    private final Project project;
    private final String version;
    private final String versionUrl;
    private final Date date;

    public ProjectVersion(Project project, String version, String versionUrl, Date date) {
        this.project = project;
        this.version = version;
        this.versionUrl = versionUrl;
        this.date = date;
    }

    public Project getProject() {
        return project;
    }

    public String getVersion() {
        return version;
    }

    public Date getDate() {
        return date;
    }

    public String getVersionUrl() {
        return versionUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("project", project).
                append("version", version).
                append("versionUrl", versionUrl).
                append("date", date).
                toString();
    }

}
