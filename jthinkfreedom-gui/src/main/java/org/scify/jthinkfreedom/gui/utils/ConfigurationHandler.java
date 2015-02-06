package org.scify.jthinkfreedom.gui.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.scify.jthinkfreedom.gui.model.Configuration;
import org.scify.jthinkfreedom.gui.model.User;
import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;
import org.scify.jthinkfreedom.skeleton.sensors.SensorAdapter;
import org.scify.jthinkfreedom.skeleton.stimuli.Stimulus;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAdapter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

}
