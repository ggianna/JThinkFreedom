package org.scify.jthinkfreedom.talkandplay.utils;

import OS.Os;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.scify.jthinkfreedom.reactors.SlideShowReactor;
import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;
import org.scify.jthinkfreedom.skeleton.sensors.SensorAdapter;
import org.scify.jthinkfreedom.skeleton.stimuli.Stimulus;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAdapter;
import org.scify.jthinkfreedom.talkandplay.models.Category;
import org.scify.jthinkfreedom.talkandplay.models.Configuration;
import org.scify.jthinkfreedom.talkandplay.models.User;

/**
 * ConfigurationHandler is responsible for parsing the xml and other xml-related
 * functions. Class is a singleton.
 *
 * @author christina
 */
public class ConfigurationHandler {

    Os os = new Os();
    private Document configurationFile;
    private List<User> profiles;
    private File file;
    private String projectPath;

    public ConfigurationHandler() {
        try {
            projectPath = System.getProperty("user.dir") + os.returnChatracter() + "conf.xml";

            file = new File(projectPath);
            if (!file.exists() || file.isDirectory()) {
                PrintWriter writer = new PrintWriter(projectPath, "UTF-8");
                writer.println("<?xml version=\"1.0\"?>\n"
                        + "<profiles></profiles>");
                writer.close();
            }

            SAXBuilder builder = new SAXBuilder();
            configurationFile = (Document) builder.build(file);

            profiles = parseXML();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public Document getConfigurationFile() {
        return configurationFile;
    }

    public List<User> getProfiles() {
        return profiles;
    }

    public User getProfile(String name) {
        for (User user : this.profiles) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public Element getProfileElement(String name) {
        Element profile = null;
        List profiles = configurationFile.getRootElement().getChildren();

        for (int i = 0; i < profiles.size(); i++) {

            profile = (Element) profiles.get(i);

            if (name.equals(profile.getChildText("name"))) {
                break;
            }
        }
        return profile;
    }

    /**
     * Parse the XML file that holds all users' configuration
     *
     * @return
     * @throws Exception
     */
    private List<User> parseXML() throws Exception {

        List<User> list = new ArrayList<>();
        List profiles = configurationFile.getRootElement().getChildren();

        for (int i = 0; i < profiles.size(); i++) {

            Element profile = (Element) profiles.get(i);
            User user = new User(profile.getChildText("name"), profile.getChildText("image"));

            Element configurations = (Element) profile.getChild("configurations");

            List<Category> categoriesArray = new ArrayList<>();

            Element categories = (Element) profile.getChild("communication").getChild("categories");
            categoriesArray = getCategories(categories, categoriesArray, null);

            user.setConfigurations(getConfigurations(configurations.getChildren()));
            user.setCategories(categoriesArray);

            list.add(user);
        }

        return list;
    }

    /**
     * Get one user
     *
     * @param userName
     * @return
     * @throws Exception
     */
    public User getUser(String userName) {

        Element profile = getProfileElement(userName);
        User user = null;

        if (profile != null) {
            user = new User(profile.getChildText("name"), profile.getChildText("image"));
            Element configurations = (Element) profile.getChild("configurations");

            List<Category> categoriesArray = new ArrayList<>();

            Element categories = (Element) profile.getChild("communication").getChild("categories");
            categoriesArray = getCategories(categories, categoriesArray, null);

            user.setConfigurations(getConfigurations(configurations.getChildren()));
            user.setCategories(categoriesArray);
        }
        return user;
    }

    /**
     * Get the configuration list for a certain user
     *
     * @param configurationNode
     * @param profile
     * @return
     * @throws Exception
     */
    private List<Configuration> getConfigurations(List configurationNode) {
        Configuration configurationObj;
        List<Configuration> configurations = new ArrayList<>();
        SensorAdapter sensor = null;
        StimulusAdapter stimulus = null;
        ReactorAdapter reactor = null;

        for (int i = 0; i < configurationNode.size(); i++) {

            Element configuration = (Element) configurationNode.get(i);
/*
            if (configuration.getChildText("sensor") != null) {
                sensor = (SensorAdapter) createInstanceFromClassName(configuration.getChildText("sensor"));
            }
            if (configuration.getChildText("sensor") != null) {
                stimulus = (StimulusAdapter) createInstanceFromClassName(configuration.getChildText("stimulus"));
            }

            //TODO: check why it throws exception
            if (configuration.getChildText("reactor") != null) {
                try {
                    reactor = (ReactorAdapter) createInstanceFromClassName(configuration.getChildText("reactor"));
                } catch (Exception e) {
                    reactor = (ReactorAdapter) createInstanceFromClassName(configuration.getChildText("reactor"), stimulus);
                }
            }
*/
            String path = configuration.getChildText("path");
            if (reactor instanceof SlideShowReactor) {
                ((SlideShowReactor) reactor).setPath(path);
            }

            configurationObj = new Configuration(sensor, stimulus, reactor);
            configurations.add(configurationObj);
        }
        return configurations;
    }

    /**
     * Recursive function to get the user categories
     *
     * @param categoriesNode
     * @param categories
     * @return
     */
    private List<Category> getCategories(Element categoriesNode, List<Category> categories, Category parent) {

        if (categoriesNode == null) {
            return categories;
        } else {
            //get the user categories

            for (int i = 0; i < categoriesNode.getChildren().size(); i++) {

                Element categoryEl = (Element) categoriesNode.getChildren().get(i);

                Category category = new Category(
                        categoryEl.getAttributeValue("name"),
                        Integer.parseInt(categoryEl.getChildText("rows")),
                        Integer.parseInt(categoryEl.getChildText("columns")),
                        categoryEl.getChildText("image"));

                if (parent != null) {
                    category.setParentCategory(new Category(parent.getName()));
                }

                if (categoryEl.getAttributeValue("editable") != null) {
                    category.setEditable(Boolean.parseBoolean(categoryEl.getAttributeValue("editable")));
                } else {
                    category.setEditable(true);
                }

                if (parent != null) {
                    category.setParentCategory(parent);
                }

                List<Category> categoriesArray = new ArrayList<>();

                Element subCategories = (Element) categoryEl.getChild("categories");
                categoriesArray = getCategories(subCategories, categoriesArray, category);

                category.setSubCategories((ArrayList<Category>) categoriesArray);
                categories.add(category);

            }
            return categories;
        }
    }

    public List refreshXMLFile() throws Exception {
        SAXBuilder builder = new SAXBuilder();
        configurationFile = (Document) builder.build(file);
        profiles = parseXML();
        return profiles;
    }

    /**
     * Write the new data to the xml file
     */
    public void writeToXmlFile() throws Exception {
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        xmlOutput.output(configurationFile, new FileWriter(projectPath));
    }

    private Object createInstanceFromClassName(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        Constructor<?> ctor = clazz.getConstructor();
        return ctor.newInstance();
    }

    private Object createInstanceFromClassName(String className, StimulusAdapter arg) throws Exception {
        Class<?> clazz = Class.forName(className);
        Constructor<?> ctor = clazz.getConstructor(Stimulus.class);
        return ctor.newInstance(arg);
    }
}
