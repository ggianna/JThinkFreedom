package org.scify.jthinkfreedom.talkandplay.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.scify.jthinkfreedom.talkandplay.models.User;
import org.scify.jthinkfreedom.talkandplay.utils.ConfigurationHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UserService {

    private ConfigurationHandler configurationHandler;
    private Document configFile;
    String projectPath;

    public void UserService() {
        configurationHandler = new ConfigurationHandler();
       // configFile = configurationHandler.getConfigurationFile();
        projectPath = configurationHandler.getProjectPath();
    }

    /**
     * Save a user to the xml file
     * 
     * @param user 
     */
    public void saveUser(User user) {

        NodeList profiles = configFile.getElementsByTagName("profiles");
        Element profile = configFile.createElement("profile");
        Element name = configFile.createElement("name");
        Element picture = configFile.createElement("picture");
        Element configurations = configFile.createElement("configurations");
        name.appendChild(configFile.createTextNode(user.getName()));

        picture.appendChild(configFile.createTextNode(user.getPhoto().toString().split("/")[user.getPhoto().toString().split("/").length - 1]));
        profile.appendChild(name);
        profile.appendChild(picture);
        profile.appendChild(configurations);
        profiles.item(0).appendChild(profile);

        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.transform(new DOMSource(configFile),
                    //new StreamResult( new FileOutputStream(ConfigurationHandler.class.getClass().getResourceAsStream("/conf.xml"))));
                    //new StreamResult(new FileOutputStream(new File(getClass().getResource("/conf.xml").toURI()))));
                    new StreamResult(new FileOutputStream(new File(projectPath))));

        } catch (TransformerException | FileNotFoundException e) {
            e.printStackTrace(System.err);
        }
    }

    /**
     * Remove a user from the xml file 
     * 
     * @param user 
     */
    public void deleteUser(User user) {
        
        configurationHandler.getProfiles().remove(user);
        NodeList profiles = configFile.getElementsByTagName("profile");
        
        Element profile;
        Node parent;
        
        for (int i = 0; i < profiles.getLength(); i++) {
            
            profile = (Element) profiles.item(i);
            
            if (profile.getElementsByTagName("name").item(0).getTextContent().equals(user.getName())) {
                
                parent = profile.getParentNode();
                parent.removeChild(profile);
                
                try {
                    Transformer tr = TransformerFactory.newInstance().newTransformer();
                    tr.setOutputProperty(OutputKeys.INDENT, "yes");
                    tr.transform(new DOMSource(configFile),
                            //new StreamResult(new FileOutputStream (new File(getClass().getResource("/conf.xml").toURI())) ));
                            new StreamResult(new FileOutputStream(new File(projectPath))));
                } catch (TransformerException | FileNotFoundException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

}
