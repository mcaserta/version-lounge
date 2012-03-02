package com.github.mcaserta.versionlounge.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Configuration {

    public Projects getProjects(File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Projects.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (Projects) unmarshaller.unmarshal(file);
    }

}
