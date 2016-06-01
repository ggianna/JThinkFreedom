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
import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;
import org.scify.jthinkfreedom.skeleton.sensors.SensorAdapter;
import org.scify.jthinkfreedom.skeleton.stimuli.Stimulus;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAdapter;
import org.scify.jthinkfreedom.talkandplay.models.Category;
import org.scify.jthinkfreedom.talkandplay.models.CommunicationModule;
import org.scify.jthinkfreedom.talkandplay.models.Configuration;
import org.scify.jthinkfreedom.talkandplay.models.EntertainmentModule;
import org.scify.jthinkfreedom.talkandplay.models.GameModule;
import org.scify.jthinkfreedom.talkandplay.models.Tile;
import org.scify.jthinkfreedom.talkandplay.models.User;

/**
 * ConfigurationHandler is responsible for parsing the xml and other xml-related
 * functions.
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
        try {
            profiles = parseXML();
            return profiles;
        } catch (Exception ex) {
            return null;
        }
    }

    public User getUser(String name) {
        for (User user : getProfiles()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public Element getProfileElement(String name) throws Exception {
        Element profile = null;
        SAXBuilder builder = new SAXBuilder();
        configurationFile = (Document) builder.build(file);
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

            if (profile.getAttributeValue("preselected") != null) {
                user.setPreselected(Boolean.parseBoolean(profile.getAttributeValue("preselected")));
            } else {
                user.setPreselected(false);
            }

            Element configuration = (Element) profile.getChild("configuration");
            user.setConfiguration(getConfiguration(configuration));

            //set the communication module settings
            List<Category> categoriesArray = new ArrayList<>();

            Element categories = (Element) profile.getChild("communication").getChild("categories");
            categoriesArray = getCategories(categories, categoriesArray, null);

            CommunicationModule communicationModule = new CommunicationModule();
            communicationModule.setName(profile.getChild("communication").getChildText("name"));
            communicationModule.setImage(profile.getChild("communication").getChildText("image"));
            communicationModule.setEnabled("true".equals(profile.getChild("communication").getChildText("image")));
            communicationModule.setCategories(categoriesArray);

            //set the entertainment module settings
            EntertainmentModule entertainmentModule = new EntertainmentModule();
            entertainmentModule.setName(profile.getChild("entertainment").getChildText("name"));
            entertainmentModule.setImage(profile.getChild("entertainment").getChildText("image"));
            entertainmentModule.setEnabled("true".equals(profile.getChild("entertainment").getChildText("image")));

            //set the game module settings
            GameModule gameModule = new GameModule();
            gameModule.setName(profile.getChild("games").getChildText("name"));
            gameModule.setImage(profile.getChild("games").getChildText("image"));
            gameModule.setEnabled("true".equals(profile.getChild("games").getChildText("image")));

            user.setCommunicationModule(communicationModule);
            user.setEntertainmentModule(entertainmentModule);
            user.setGameModule(gameModule);

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
    private Configuration getConfiguration(Element configurationNode) {
        Configuration configurationObj;
        Configuration configuration = new Configuration();
        SensorAdapter sensor = null;
        StimulusAdapter stimulus = null;
        ReactorAdapter reactor = null;

        configuration.setRotationSpeed(Integer.parseInt(configurationNode.getChildText("rotationSpeed")));
        configuration.setDefaultGridRow(Integer.parseInt(configurationNode.getChildText("defaultGridRow")));
        configuration.setDefaultGridColumn(Integer.parseInt(configurationNode.getChildText("defaultGridColumn")));

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
        /*  String path = configuration.getChildText("path");
         if (reactor instanceof SlideShowReactor) {
         ((SlideShowReactor) reactor).setPath(path);
         }
         */
        return configuration;
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

                if (categoryEl.getAttributeValue("order") != null) {
                    category.setOrder(Integer.parseInt(categoryEl.getAttributeValue("order")));
                } else {
                    category.setOrder(0);
                }

                //set the tiles
                if (categoryEl.getChild("tiles") != null) {
                    Element tileEl;
                    for (int j = 0; j < categoryEl.getChild("tiles").getChildren().size(); j++) {
                        tileEl = (Element) categoryEl.getChild("tiles").getChildren().get(j);

                        int order = 0;
                        if (tileEl.getAttributeValue("order") != null) {
                            order = Integer.parseInt(categoryEl.getAttributeValue("order"));
                        }

                        category.getTiles().add(new Tile(tileEl.getChildText("name"), tileEl.getChildText("image"), tileEl.getChildText("sound"), order));
                    }
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
