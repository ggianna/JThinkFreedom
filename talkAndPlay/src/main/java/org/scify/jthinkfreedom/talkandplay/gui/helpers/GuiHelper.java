package org.scify.jthinkfreedom.talkandplay.gui.helpers;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;

/**
 *
 * @author christina
 */
public class GuiHelper {

    /**
     * Get the icon for a user, or no photo
     *
     * @param path
     * @return
     */
    public ImageIcon getIcon(String path) {
        if (path != null && new File(path).isFile()) {
            return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));

        } else {
            return new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/jthinkfreedom/talkandplay/resources/no-photo.png")).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        }
    }
    
    

}
