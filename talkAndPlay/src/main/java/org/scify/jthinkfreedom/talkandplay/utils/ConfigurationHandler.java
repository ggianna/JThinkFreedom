package org.scify.jthinkfreedom.talkandplay.utils;

import OS.Os;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

            /* configurationFile = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(projectPath));
             configurationFile.normalize(); */
            profiles = parseXML();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public Document getConfigurationFile() {
        return configurationFile;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public List<User> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<User> profiles) {
        this.profiles = profiles;
    }

    public User getProfile(String name) {
        for (User user : this.profiles) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
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
     * Get the configuration list for a certain user
     *
     * @param configurationNode
     * @param profile
     * @return
     * @throws Exception
     */
    private List<Configuration> getConfigurations(List configurationNode) throws Exception {
        Configuration configurationObj;
        List<Configuration> configurations = new ArrayList<>();
        SensorAdapter sensor = null;
        StimulusAdapter stimulus = null;
        ReactorAdapter reactor = null;

        for (int i = 0; i < configurationNode.size(); i++) {

            Element configuration = (Element) configurationNode.get(i);

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
                        Integer.parseInt(categoryEl.getChildText("columns")), null);

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

    /**
     * Write the new data to the xml file
     */
    public void writeToXmlFile() {
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        try {
            xmlOutput.output(configurationFile, new FileWriter(projectPath));
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     public void saveConfiguration(Configuration conf, User user) {
     NodeList profiles = configurationFile.getElementsByTagName("profile");
     for (int i = 0; i < profiles.getLength(); i++) {
     Element profile = (Element) profiles.item(i);
     String name = profile.getElementsByTagName("name").item(0).getTextContent();

     if (name.equals(user.getName())) {
     Element configurations = (Element) profile.getElementsByTagName("configurations").item(0);
     Element sensorClass = configurationFile.createElement("sensor");
     sensorClass.appendChild(configurationFile.createTextNode(conf.getSensor().getClass().getCanonicalName()));
     Element stimulusClass = configurationFile.createElement("stimulus");

     stimulusClass.appendChild(configurationFile.createTextNode(conf.getStimulus().getClass().getCanonicalName()));
     Element reactorClass = configurationFile.createElement("reactor");

     reactorClass.appendChild(configurationFile.createTextNode(conf.getReactor().getClass().getCanonicalName()));
     Element configuration = configurationFile.createElement("configuration");

     configuration.appendChild(sensorClass);
     configuration.appendChild(stimulusClass);
     configuration.appendChild(reactorClass);
     configurations.appendChild(configuration);

     try {
     Transformer tr = TransformerFactory.newInstance().newTransformer();
     tr.setOutputProperty(OutputKeys.INDENT, "yes");
     tr.transform(new DOMSource(configurationFile),
     //new StreamResult(new FileOutputStream(new File(getClass().getResource("/conf.xml").toURI()))));
     new StreamResult(new FileOutputStream(new File(projectPath))));
     } catch (TransformerException | FileNotFoundException e) {
     e.printStackTrace(System.err);
     }
     }
     }
     }
     */
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
