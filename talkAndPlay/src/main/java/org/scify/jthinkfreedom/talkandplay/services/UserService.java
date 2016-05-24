package org.scify.jthinkfreedom.talkandplay.services;

import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.scify.jthinkfreedom.talkandplay.models.User;
import org.scify.jthinkfreedom.talkandplay.utils.ConfigurationHandler;

public class UserService {

    private ConfigurationHandler configurationHandler;
    private Document configurationFile;
    String projectPath;

    public UserService() {
        configurationHandler = new ConfigurationHandler();
        configurationFile = configurationHandler.getConfigurationFile();
        projectPath = configurationHandler.getProjectPath();
    }

    /**
     * Save a user to the xml file
     *
     * @param user
     */
    public void save(User user) {

        configurationHandler.getProfiles().add(user);

        Element profiles = configurationFile.getRootElement();

        Element profile = new Element("profile");
        profile.addContent(new Element("name").setText(user.getName()));
        profile.addContent(new Element("image").setText(user.getImage()));

        Element configurations = new Element("configurations");
        profile.addContent(configurations);

        Element categories = new Element("categories");
        profile.addContent(categories);

        System.out.println(profile.toString());
        profiles.addContent(profile);

        /*
         NodeList profiles = configurationFile.getElementsByTagName("profiles");
         Element profile = configurationFile.createElement("profile");
         Element name = configurationFile.createElement("name");
         Element picture = configurationFile.createElement("picture");
         Element configurations = configurationFile.createElement("configurations");
         name.appendChild(configurationFile.createTextNode(user.getName()));

         picture.appendChild(configurationFile.createTextNode(user.getPhoto().toString().split("/")[user.getPhoto().toString().split("/").length - 1]));
         profile.appendChild(name);
         profile.appendChild(picture);
         profile.appendChild(configurations);
         profiles.item(0).appendChild(profile);
         */
        configurationHandler.writeToXmlFile();
    }

    /**
     * Update a user
     *
     * @param name
     */
    public void update(User user, String oldName) {

        List profiles = configurationFile.getRootElement().getChildren();

        //find the user from the users list
        for (int i = 0; i < profiles.size(); i++) {

            Element profile = (Element) profiles.get(i);

            if (profile.getChildText("name").equals(oldName)) {

                profile.getChild("name").setText(user.getName());
                profile.getChild("image").setText(user.getImage());
                configurationHandler.writeToXmlFile();
            }
        }
    }

    /**
     * Remove a user from the xml file
     *
     * @param user
     */
    public void delete(User user) {

        configurationHandler.getProfiles().remove(user);

        List profiles = configurationFile.getRootElement().getChildren();

        //find the user from the users list
        for (int i = 0; i < profiles.size(); i++) {

            Element profile = (Element) profiles.get(i);

            if (profile.getChildText("name").equals(user.getName())) {
                profiles.remove(i);
                configurationHandler.writeToXmlFile();
            }
        }
    }

}
