/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.talkandplay.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.scify.jthinkfreedom.talkandplay.gui.grid.GridFrame;
import org.scify.jthinkfreedom.talkandplay.gui.users.CreateUserPanel;
import org.scify.jthinkfreedom.talkandplay.gui.users.ProfilePanel;
import org.scify.jthinkfreedom.talkandplay.models.User;
import org.scify.jthinkfreedom.talkandplay.utils.ConfigurationHandler;

/**
 *
 * @author christina
 */
public class MainPanel extends javax.swing.JPanel {

    private ConfigurationHandler configurationHandler;
    private List<ProfilePanel> profilesPanel;
    private MainFrame parent;

    public MainPanel(MainFrame parent) {
        this.configurationHandler = new ConfigurationHandler();
        this.parent = parent;
        initComponents();
        initCustomComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        profilePanel = new javax.swing.JPanel();

        profilePanel.setBackground(new java.awt.Color(255, 255, 255));
        profilePanel.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(profilePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(profilePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void initCustomComponents() {

        List<User> users = configurationHandler.getProfiles();

        if (users.size() > 5) {
            profilePanel.setLayout(new GridLayout(0, 6));
        } else {
            profilePanel.setLayout(new GridLayout(0, users.size()+1));
        }

        profilesPanel = new ArrayList<>();
        for (final User profile : users) {
            ProfilePanel profilePanel = new ProfilePanel(this, profile);

            profilePanel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    GridFrame imagesFrame;
                    try {
                        imagesFrame = new GridFrame(profile.getName());
                        imagesFrame.setLocationRelativeTo(null);
                        imagesFrame.setTitle("Talk&Play");
                        imagesFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        imagesFrame.setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            profilesPanel.add(profilePanel);
        }

        repaintProfiles();
    }

    public void removeUser(User selectedUser) {
        repaintProfiles();
        for (ProfilePanel p : profilesPanel) {
            if (p.getUser().equals(selectedUser)) {
                profilesPanel.remove(p);
                break;
            }
        }
        repaintProfiles();
    }

    public void repaintProfiles() {
        profilePanel.removeAll();
        if (!profilesPanel.isEmpty()) {
            for (JPanel panel : profilesPanel) {
                profilePanel.add(panel);
            }
        }
        addUserPanel();
        profilePanel.repaint();
    }

    private void addUserPanel() {
        JPanel addUserPanel = new JPanel();
        addUserPanel.setLayout(new BoxLayout(addUserPanel, BoxLayout.Y_AXIS));
        addUserPanel.setBackground(Color.white);

        JLabel nameLabel = new JLabel("Πρόσθεσε νέο χρήστη");
        final JLabel imageLabel = new JLabel(new ImageIcon(getClass().getResource("/org/scify/jthinkfreedom/talkandplay/resources/add-icon.png")));

        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setBorder(new EmptyBorder(10, 0, 20, 0));
        nameLabel.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));

        addUserPanel.add(imageLabel);
        addUserPanel.add(nameLabel);
        profilePanel.add(addUserPanel);

        final MainPanel currentPanel = this;

        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent arg0) {
                imageLabel.setIcon(new ImageIcon(getClass().getResource("/org/scify/jthinkfreedom/talkandplay/resources/add-icon.png")));
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                imageLabel.setIcon(new ImageIcon(getClass().getResource("/org/scify/jthinkfreedom/talkandplay/resources/add-icon-hover.png")));
            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
                parent.changePanel(new CreateUserPanel(parent));

                /*CreateUserScreen cus = new CreateUserScreen(currentFame);
                 cus.setLocationRelativeTo(null);
                 cus.setVisible(true);*/
            }
        });
    }

    public void updateProfilesPanel(User user, String oldName) {
        System.out.println("user: " + user.getName() + ", " + oldName);

        for (ProfilePanel p : profilesPanel) {
            if (p.getUser().getName().equals(oldName)) {
                p.repaintPanel(user);
                break;
            }
        }
        repaintProfiles();
    }

    public void removeFromProfilesPanel(String removeProfile) {
        for (ProfilePanel p : profilesPanel) {
            if (p.getUser().getName().equals(removeProfile)) {
                profilesPanel.remove(p);
                break;
            }
        }
        repaintProfiles();
    }

    public List<ProfilePanel> getProfilesPanel() {
        return profilesPanel;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel profilePanel;
    // End of variables declaration//GEN-END:variables
}