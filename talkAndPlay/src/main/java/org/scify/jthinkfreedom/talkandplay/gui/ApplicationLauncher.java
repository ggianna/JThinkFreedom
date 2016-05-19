package org.scify.jthinkfreedom.talkandplay.gui;

import java.util.List;
import org.scify.jthinkfreedom.talkandplay.models.Category;
import org.scify.jthinkfreedom.talkandplay.models.Configuration;
import org.scify.jthinkfreedom.talkandplay.models.User;
import org.scify.jthinkfreedom.talkandplay.utils.ConfigurationHandler;

/**
 *
 * @author peustr
 */
public class ApplicationLauncher {

    public static void main(String[] args) {

        ConfigurationHandler conf = new ConfigurationHandler();
        MainFrame ps = new MainFrame(conf);
        ps.setLocationRelativeTo(null);
        ps.setVisible(true);

    }

    private void testConfig() {

        ConfigurationHandler conf = new ConfigurationHandler();
        List<User> profiles = conf.getProfiles();

        for (User user : profiles) {

            System.out.println("Profile: " + user.getName());

            for (Configuration configuration : user.getConfigurations()) {
                for (Category cat : configuration.getCategories()) {
                    System.out.println("cat name:" + cat.getName());
                }
            }
        }
    }
}
