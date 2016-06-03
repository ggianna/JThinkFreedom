package org.scify.jthinkfreedom.talkandplay.gui.helpers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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

    public JPanel createImagePanel(String imagePath, String text, JFrame frame) {
        JPanel panel = new JPanel(new BorderLayout());

        java.awt.Image image;
        try {
            image = ImageIO.read(new File(imagePath));
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH));

            JLabel imgLabel = new JLabel(imageIcon);
            JLabel txtLabel = new JLabel(text);
            txtLabel.setFont(new Font("Courier New", Font.PLAIN, 40));

            panel.setBackground(Color.LIGHT_GRAY);
            panel.add(imgLabel, BorderLayout.CENTER);
            panel.add(txtLabel, BorderLayout.NORTH);
            txtLabel.setHorizontalAlignment(JLabel.CENTER);

            return panel;
        } catch (IOException ex) {
            JOptionPane.showInternalMessageDialog(frame,
                    "Η εικόνα της καρτέλας " + text + " δεν βρέθηκε",
                    "Σφάλμα",
                    JOptionPane.ERROR_MESSAGE);
            return panel;
        }
    }

    
}
