package com.github.mcaserta.versionlounge;

import java.util.Comparator;

public class ProjectVersionComparator implements Comparator<ProjectVersion> {

    @Override
    public int compare(ProjectVersion projectVersion1, ProjectVersion projectVersion2) {
        String s1 = projectVersion1.getVersion();
        String s2 = projectVersion2.getVersion();

        if (s1.equals(s2)) {
            return 0;
        }
        String[] l1 = s1.split("[.,_-]");
        String[] l2 = s2.split("[.,_-]");
        int bound = l1.length;
        if (l2.length > bound) {
            bound = l2.length;
        }
        for (int i = 0; i < bound; i++) {
            if (i >= l1.length) {
                return -1;
            } else if (i >= l2.length) {
                return 1;
            }
            String e1 = l1[i];
            String e2 = l2[i];
            Integer i1 = Integer.parseInt(l1[i]);
            Integer i2 = Integer.parseInt(l2[i]);
            if ((i1 == null) && (i2 == null)) {
                return e1.compareTo(e2);
            } else if (i1 == null) {
                return -1;
            } else if (i2 == null) {
                return 1;
            }
            if (!i1.equals(i2)) {
                return i1.compareTo(i2);
            }
        }
        return 0;
    }

}
