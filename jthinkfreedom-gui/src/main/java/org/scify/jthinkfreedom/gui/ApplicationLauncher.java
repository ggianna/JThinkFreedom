package org.scify.jthinkfreedom.gui;

import org.scify.jthinkfreedom.gui.forms.ProfileScreen;
import org.scify.jthinkfreedom.gui.utils.ConfigurationHandler;

/**
 *
 * @author peustr
 */
public class ApplicationLauncher {

    public static void main(String[] args) {
        ConfigurationHandler cf = new ConfigurationHandler();
        ProfileScreen ps = new ProfileScreen(cf);
        ps.setLocationRelativeTo(null);
        ps.setVisible(true);
    }

}
