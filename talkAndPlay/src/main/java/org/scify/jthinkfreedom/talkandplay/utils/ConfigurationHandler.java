package org.scify.jthinkfreedom.talkandplay.utils;

import OS.Os;
import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.scify.jthinkfreedom.reactors.SlideShowReactor;
import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;
import org.scify.jthinkfreedom.skeleton.sensors.SensorAdapter;
import org.scify.jthinkfreedom.skeleton.stimuli.Stimulus;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAdapter;
import org.scify.jthinkfreedom.talkandplay.models.Category;
import org.scify.jthinkfreedom.talkandplay.models.Configuration;
import org.scify.jthinkfreedom.talkandplay.models.User;
import org.w3c.dom.NodeList;

public class ConfigurationHandler {

    Os os = new Os();
    private Document configFile;
    private List<User> profiles;
    private File file;
    private String project_path;

    public ConfigurationHandler() {
        try {
            project_path = System.getProperty("user.dir") + os.returnChatracter() + "conf.xml";

            file = new File(project_path);
            if (!file.exists() || file.isDirectory()) {
                PrintWriter writer = new PrintWriter(project_path, "UTF-8");
                writer.println("<?xml version=\"1.0\"?>\n"
                        + "<profiles></profiles>");
                writer.close();
            }

            SAXBuilder builder = new SAXBuilder();
            configFile = (Document) builder.build(file);

            /* configFile = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(project_path));
             configFile.normalize();
             */
            profiles = parseXML();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

    public Document getConfigurationFile() {
        return configFile;
    }

    public String getProjectPath() {
        return project_path;
    }

    public List<User> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<User> profiles) {
        this.profiles = profiles;
    }

    /**
     * Parse the XML file that holds all users' configuration
     *
     * @return
     * @throws Exception
     */
    private List<User> parseXML() throws Exception {

        List<User> list = new ArrayList<>();
        List profiles = configFile.getRootElement().getChildren();

        for (int i = 0; i < profiles.size(); i++) {

            Element profile = (Element) profiles.get(i);
            User user = new User(profile.getChildText("name"),
                    new ImageIcon(getClass().getResource("/org/scify/jthinkfreedom/talkandplay/resources/"
                                    + profile.getChildText("picture"))));

            List configurationsList = profile.getChildren("configurations");
            Element configurations = (Element) configurationsList.get(0);

            user.setConfigurations(getConfigurations(configurations.getChildren()));

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
            List categoriesList = configuration.getChildren("categories");
            List<Category> categoriesArray = new ArrayList<>();

            if (categoriesList.size() > 0) {
                Element categories = (Element) categoriesList.get(0);
                categoriesArray = getCategories(categories.getChildren(), categoriesArray);

            }

            configurationObj.setCategories(categoriesArray);
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
    private List<Category> getCategories(List categoriesNode, List<Category> categories) {
        if (categoriesNode.size() == 0) {
            return categories;
        } else {
            //get the user categories
            for (int i = 0; i < categoriesNode.size(); i++) {

                Element categoryEl = (Element) categoriesNode.get(i);
                Category category = new Category(Integer.parseInt(categoryEl.getChildText("rows")),
                        Integer.parseInt(categoryEl.getChildText("columns")),
                        categoryEl.getAttributeValue("name"));

                List subCategoriesList = categoryEl.getChildren("categories");
                if (subCategoriesList.size() > 0) {
                    Element subCategories = (Element) subCategoriesList.get(0);

                    List<Category> categoriesArray = new ArrayList<>();
                    category.setSubCategories((ArrayList<Category>) getCategories(subCategories.getChildren(), categoriesArray));
                }
                categories.add(category);
            }
            return categories;
        }
    }


    /*
     public void saveConfiguration(Configuration conf, User user) {
     NodeList profiles = configFile.getElementsByTagName("profile");
     for (int i = 0; i < profiles.getLength(); i++) {
     Element profile = (Element) profiles.item(i);
     String name = profile.getElementsByTagName("name").item(0).getTextContent();

     if (name.equals(user.getName())) {
     Element configurations = (Element) profile.getElementsByTagName("configurations").item(0);
     Element sensorClass = configFile.createElement("sensor");
     sensorClass.appendChild(configFile.createTextNode(conf.getSensor().getClass().getCanonicalName()));
     Element stimulusClass = configFile.createElement("stimulus");

     stimulusClass.appendChild(configFile.createTextNode(conf.getStimulus().getClass().getCanonicalName()));
     Element reactorClass = configFile.createElement("reactor");

     reactorClass.appendChild(configFile.createTextNode(conf.getReactor().getClass().getCanonicalName()));
     Element configuration = configFile.createElement("configuration");

     configuration.appendChild(sensorClass);
     configuration.appendChild(stimulusClass);
     configuration.appendChild(reactorClass);
     configurations.appendChild(configuration);

     try {
     Transformer tr = TransformerFactory.newInstance().newTransformer();
     tr.setOutputProperty(OutputKeys.INDENT, "yes");
     tr.transform(new DOMSource(configFile),
     //new StreamResult(new FileOutputStream(new File(getClass().getResource("/conf.xml").toURI()))));
     new StreamResult(new FileOutputStream(new File(project_path))));
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
