package org.scify.jthinkfreedom.talkandplay.gui;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import org.scify.jthinkfreedom.talkandplay.gui.configuration.ConfigurationPanel;
import java.awt.GridBagConstraints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import org.scify.jthinkfreedom.talkandplay.models.User;
import org.scify.jthinkfreedom.talkandplay.utils.ConfigurationHandler;

/**
 *
 * @author peustr
 */
public class MainFrame extends javax.swing.JFrame {

    private static final int STEP = 4;

    private int profilePaginationCounterStart = 0;
    private int profilePaginationCounterEnd = STEP;

    private ConfigurationHandler configurationHandler;
    private List<ProfilePanel> profilesPanel;
    private List<ConfigurationPanel> configurations;

    public MainFrame() {
        initComponents();
    }

    public MainFrame(ConfigurationHandler configurationHandler) {
        this.configurationHandler = configurationHandler;
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        contentPane = new javax.swing.JPanel();
        nextProfileButton = new javax.swing.JButton();
        previousProfileButton = new javax.swing.JButton();
        profileCountLabel = new javax.swing.JLabel();
        profilePanel = new javax.swing.JPanel();
        addProfileButton = new javax.swing.JButton();
        logoLabel = new javax.swing.JLabel();
        configureButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Talk&Play");

        contentPane.setBackground(new java.awt.Color(255, 255, 255));
        contentPane.setPreferredSize(new java.awt.Dimension(800, 720));

        nextProfileButton.setBackground(new java.awt.Color(255, 255, 255));
        nextProfileButton.setFont(new java.awt.Font("Comfortaa", 0, 10)); // NOI18N
        nextProfileButton.setForeground(new java.awt.Color(51, 51, 51));
        nextProfileButton.setText(">");
        nextProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextProfileButtonActionPerformed(evt);
            }
        });

        previousProfileButton.setBackground(new java.awt.Color(255, 255, 255));
        previousProfileButton.setFont(new java.awt.Font("Comfortaa", 0, 10)); // NOI18N
        previousProfileButton.setForeground(new java.awt.Color(51, 51, 51));
        previousProfileButton.setText("<");
        previousProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousProfileButtonActionPerformed(evt);
            }
        });

        profileCountLabel.setFont(new java.awt.Font("Comfortaa", 1, 14)); // NOI18N
        profileCountLabel.setText("n profiles");

        profilePanel.setBackground(new java.awt.Color(255, 255, 255));

        addProfileButton.setBackground(new java.awt.Color(255, 255, 255));
        addProfileButton.setFont(new java.awt.Font("Comfortaa", 1, 14)); // NOI18N
        addProfileButton.setForeground(new java.awt.Color(51, 51, 51));
        addProfileButton.setText("New profile");
        addProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProfileButtonActionPerformed(evt);
            }
        });

        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scify/jthinkfreedom/talkandplay/resources/tp_logo_small.png"))); // NOI18N

        configureButton.setBackground(new java.awt.Color(255, 255, 255));
        configureButton.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        configureButton.setForeground(new java.awt.Color(51, 51, 51));
        configureButton.setText("Configure");
        configureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configureButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("© SciFY 2016");

        javax.swing.GroupLayout contentPaneLayout = new javax.swing.GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(profilePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logoLabel)
                            .addComponent(profileCountLabel))
                        .addGap(0, 612, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                        .addComponent(configureButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addProfileButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(previousProfileButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextProfileButton)
                        .addGap(10, 10, 10))
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addComponent(jLabel1))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoLabel)
                .addGap(47, 47, 47)
                .addComponent(profileCountLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(profilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextProfileButton)
                    .addComponent(previousProfileButton)
                    .addComponent(addProfileButton)
                    .addComponent(configureButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPane, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void previousProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousProfileButtonActionPerformed
        if (profilePaginationCounterStart - STEP >= 0) {
            profilePaginationCounterStart -= STEP;
            profilePaginationCounterEnd -= STEP;
            repaintProfiles();
        }
    }//GEN-LAST:event_previousProfileButtonActionPerformed

    private void nextProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextProfileButtonActionPerformed
        if (profilePaginationCounterEnd < profilesPanel.size()) {
            profilePaginationCounterEnd += STEP;
            profilePaginationCounterStart += STEP;
            repaintProfiles();
        }
    }//GEN-LAST:event_nextProfileButtonActionPerformed

    private void addProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProfileButtonActionPerformed
        CreateUserScreen cus = new CreateUserScreen(this);
        cus.setLocationRelativeTo(null);
        cus.setVisible(true);
    }//GEN-LAST:event_addProfileButtonActionPerformed

    private void configureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configureButtonActionPerformed
        // TODO add your handling code here:
        addProfileButton.setEnabled(false);
        configureButton.setEnabled(false);
        //  XmlScreen frame = new XmlScreen();
        ConfigurationFrame configurationFrame = new ConfigurationFrame(this);
        configurationFrame.setVisible(true);
        configurationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configurationFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                addProfileButton.setEnabled(true);
                configureButton.setEnabled(true);
            }
        });
    }//GEN-LAST:event_configureButtonActionPerformed

    private void initCustomComponents() {
        profilePaginationCounterStart = 0;
        profilePaginationCounterEnd = STEP;

        // Grid bag layout manager fill from left to right
       /* gbc = new GridBagConstraints();
         gbc.anchor = GridBagConstraints.WEST;
         gbc.weightx = 1;*/
        // Fill the profile panel according to pagination
        profilesPanel = new ArrayList<>();
        for (User profile : configurationHandler.getProfiles()) {
            profilesPanel.add(new ProfilePanel(this, profile));
        }
        repaintProfiles();
        pack();
    }

    public void removeUser(User selectedUser) {
        //  cf.deleteUser(selectedUser);
        repaintProfiles();
        for (ProfilePanel p : profilesPanel) {
            if (p.getUser().equals(selectedUser)) {
                profilesPanel.remove(p);
                break;
            }
        }
        repaintProfiles();

        //profiles.remove(cf);
    }

    public void repaintProfiles() {           
        profilePanel.removeAll();
        if (!profilesPanel.isEmpty()) {
            profileCountLabel.setText(profilesPanel.size() + " profiles");
            for (int i = profilePaginationCounterStart; i < profilePaginationCounterEnd; i++) {
                try {
                    profilePanel.add(profilesPanel.get(i), gbc);
                } catch (IndexOutOfBoundsException e) {
                    // Do nothing
                }
            }
        }

        pack();
        profilePanel.repaint();
    }
    
    public void removeFromProfilesPanel(String removeProfile){
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

    private GridBagConstraints gbc;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProfileButton;
    private javax.swing.JButton configureButton;
    private javax.swing.JPanel contentPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JButton nextProfileButton;
    private javax.swing.JButton previousProfileButton;
    private javax.swing.JLabel profileCountLabel;
    private javax.swing.JPanel profilePanel;
    // End of variables declaration//GEN-END:variables
}
