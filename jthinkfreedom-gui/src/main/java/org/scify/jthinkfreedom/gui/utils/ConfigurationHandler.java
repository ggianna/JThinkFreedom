package org.scify.jthinkfreedom.gui.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.scify.jthinkfreedom.gui.model.Configuration;
import org.scify.jthinkfreedom.gui.model.User;
import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;
import org.scify.jthinkfreedom.skeleton.sensors.SensorAdapter;
import org.scify.jthinkfreedom.skeleton.stimuli.Stimulus;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAdapter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author peustr
 */
public class ConfigurationHandler {

    private Document configFile;
    private List<User> profiles;

    public ConfigurationHandler() {
        try {
            configFile = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new File(getClass().getResource("/conf.xml").toURI()));
            configFile.normalize();
            profiles = parseXML();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public List<User> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<User> profiles) {
        this.profiles = profiles;
    }

    private List<User> parseXML() throws Exception {
        List<User> list = new ArrayList<>();
        NodeList profiles = configFile.getElementsByTagName("profile");
        for (int i = 0; i < profiles.getLength(); i++) {
            Element profile = (Element) profiles.item(i);
            User curUser = new User(profile.getElementsByTagName("name").item(0).getTextContent(),
                    new ImageIcon(getClass().getResource("/org/scify/jthinkfreedom/gui/resources/"
                                    + profile.getElementsByTagName("picture").item(0).getTextContent())));
            NodeList configurations = profile.getElementsByTagName("configuration");
            for (int j = 0; j < configurations.getLength(); j++) {
                Element configuration = (Element) configurations.item(j);
                SensorAdapter sensor = (SensorAdapter) createInstanceFromClassName(configuration.getElementsByTagName("sensor").item(0).getTextContent());
                StimulusAdapter stimulus = (StimulusAdapter) createInstanceFromClassName(configuration.getElementsByTagName("stimulus").item(0).getTextContent());
                ReactorAdapter reactor = null;
                try {
                    reactor = (ReactorAdapter) createInstanceFromClassName(configuration.getElementsByTagName("reactor").item(0).getTextContent());
                } catch (Exception e) {
                    reactor = (ReactorAdapter) createInstanceFromClassName(configuration.getElementsByTagName("reactor").item(0).getTextContent(), stimulus);
                }
                Configuration conf = new Configuration(sensor, stimulus, reactor);
                curUser.getConfigurations().add(conf);
            }
            list.add(curUser);
        }
        return list;
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
                    new StreamResult(new FileOutputStream(new File(getClass().getResource("/conf.xml").toURI()))));
        } catch (TransformerException | FileNotFoundException | URISyntaxException e) {
            e.printStackTrace(System.err);
        }
    }

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
                            new StreamResult(new FileOutputStream(new File(getClass().getResource("/conf.xml").toURI()))));
                } catch (TransformerException | FileNotFoundException | URISyntaxException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
}
