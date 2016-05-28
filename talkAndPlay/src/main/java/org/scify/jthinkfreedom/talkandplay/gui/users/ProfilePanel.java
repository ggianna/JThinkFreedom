package org.scify.jthinkfreedom.talkandplay.gui.users;

import java.awt.Font;
import javax.swing.SwingConstants;
import org.scify.jthinkfreedom.talkandplay.gui.MainFrame;
import org.scify.jthinkfreedom.talkandplay.gui.helpers.GuiHelper;
import org.scify.jthinkfreedom.talkandplay.models.User;

/**
 *
 * @author peustr
 */
public class ProfilePanel extends javax.swing.JPanel {

    private MainFrame parent;
    private User profile;
    private GuiHelper guiHelper;

    /**
     * Creates new form ProfilePanel
     */
    public User getUser() {
        return profile;
    }

    public ProfilePanel() {
        initComponents();
    }

    public ProfilePanel(MainFrame parent, User profile) {
        this.parent = parent;
        this.profile = profile;
        this.guiHelper = new GuiHelper();
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

        jPanel1 = new javax.swing.JPanel();
        imageLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scify/jthinkfreedom/talkandplay/resources/no-photo.png"))); // NOI18N
        jPanel1.add(imageLabel, java.awt.BorderLayout.PAGE_START);

        nameLabel.setFont(nameLabel.getFont().deriveFont(nameLabel.getFont().getSize()+2f));
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel.setText("Name");
        jPanel1.add(nameLabel, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void initCustomComponents() {
        nameLabel.setText(profile.getName());
        imageLabel.setIcon(guiHelper.getIcon((profile.getImage())));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    public void repaintPanel(User user) {
        nameLabel.setText(user.getName());
        imageLabel.setIcon(guiHelper.getIcon((user.getImage())));
    }

    public User getProfile() {
        return profile;
    }

    public void setProfile(User profile) {
        this.profile = profile;
    }

    public void update(String profile) {
        nameLabel.setText(profile);
    }

    private Font originalFont;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nameLabel;
    // End of variables declaration//GEN-END:variables
}