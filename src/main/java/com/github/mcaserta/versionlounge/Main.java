package com.github.mcaserta.versionlounge;

import com.github.mcaserta.versionlounge.xml.Configuration;
import com.github.mcaserta.versionlounge.xml.Project;
import org.apache.commons.cli.*;
import org.tmatesoft.svn.core.SVNException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.List;

public class Main {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final HelpFormatter helpFormatter = new HelpFormatter();

    private final Configuration config = new Configuration();
    private final SvnBrowser svnBrowser = new SvnBrowser();

    public static void main(String[] args) throws JAXBException, SVNException, FileNotFoundException {
        CommandLineParser parser = new GnuParser();
        Options options = new Options()
                .addOption("p", "projects-file", true, "projects file")
                .addOption("v", "verbose", false, "verbose mode")
                .addOption("o", "output-file", true, "output file");

        try {
            String projectsFile = null;
            String outputFile = null;
            boolean verbose = false;

            CommandLine line = parser.parse(options, args);

            if (line.hasOption('p')) {
                projectsFile = line.getOptionValue('p');
            } else {
                System.out.println();
                System.out.println("ERROR: project file is a mandatory option");
                System.out.println();
                helpFormatter.printHelp("version-lounge", options);
                System.exit(1);
            }

            if (line.hasOption('o')) {
                outputFile = line.getOptionValue('o');
            }

            if (line.hasOption('v')) {
                verbose = true;
            }

            new Main().perform(projectsFile, outputFile, verbose);
        } catch (ParseException e) {
            helpFormatter.printHelp("version-lounge", options);
        }
    }

    public void perform(String projectsFile, String outputFile, boolean verbose) throws SVNException, JAXBException, FileNotFoundException {
        List<Project> projects = config.getProjects(new File(projectsFile)).getProject();

        if (verbose) {
            System.out.println();
            System.out.print("* using projects file: ");
            System.out.println(projectsFile);
            System.out.println("* fetching versions ...");
            svnBrowser.setVerbose();
        }

        List<ProjectVersion> projectVersions = svnBrowser.getProjectVersions(projects);

        final PrintStream out;

        if (outputFile == null) {
            out = System.out;
        } else {
            if (verbose) {
                System.out.print("* writing output to file: ");
                System.out.print(outputFile);
                System.out.println(" ...");
            }
            out = new PrintStream(new File(outputFile));
        }

        out.println();

        for (ProjectVersion projectVersion : projectVersions) {
            out.print(" [*] ");
            out.print(projectVersion.getProject().getId());
            out.print(": ");
            out.print(projectVersion.getVersion());
            out.print(" (");
            out.print(formatter.format(projectVersion.getDate()));
            out.println(")");
            out.print("     ");
            out.println(projectVersion.getVersionUrl());
            out.println();
        }

        if (verbose) {
            System.out.println("* done.");
        }
    }

}
