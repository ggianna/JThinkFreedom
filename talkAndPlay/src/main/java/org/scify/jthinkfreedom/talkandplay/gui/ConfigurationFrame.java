package org.scify.jthinkfreedom.talkandplay.gui;

import org.scify.jthinkfreedom.talkandplay.gui.configuration.GeneralSettingsPanel;
import org.scify.jthinkfreedom.talkandplay.gui.configuration.CommunicationModuleSettingsPanel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import org.scify.jthinkfreedom.reactors.SlideShowReactor;
import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;
import org.scify.jthinkfreedom.talkandplay.models.User;
import org.scify.jthinkfreedom.talkandplay.utils.ConfigurationHandler;

/**
 *
 * @author peustr
 */
public class ConfigurationFrame extends javax.swing.JFrame {

    private static final int VIEW_LIMIT = 2;

    private ConfigurationHandler configurationHandler;
    private GeneralSettingsPanel generalSettingsPanel;
    private CommunicationModuleSettingsPanel communicationModuleSettingsPanel;
    private MainFrame mainFrame;
    private int selectedUser;

    /**
     * Creates new form ConfigurationScreen
     */
    public ConfigurationFrame() {
        this.configurationHandler = new ConfigurationHandler();
        initComponents();
        initCustomComponents();
    }

    public ConfigurationFrame(MainFrame mainFrame) {
        this.configurationHandler = new ConfigurationHandler();
        this.mainFrame = mainFrame;
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

        contentPane = new javax.swing.JPanel();
        logoLabel = new javax.swing.JLabel();
        menuPanel = new javax.swing.JPanel();
        userSettingsLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        usersList = new javax.swing.JList();
        settingsTabbedPane1 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Talk&Play");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        contentPane.setBackground(new java.awt.Color(255, 255, 255));
        contentPane.setPreferredSize(new java.awt.Dimension(800, 720));

        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scify/jthinkfreedom/talkandplay/resources/tp_logo_small.png"))); // NOI18N

        menuPanel.setBackground(new java.awt.Color(255, 255, 255));
        menuPanel.setForeground(new java.awt.Color(255, 255, 255));

        userSettingsLabel.setText("Ρυθμίσεις χρηστών");

        usersList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        usersList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                usersListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(usersList);

        settingsTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        settingsTabbedPane1.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userSettingsLabel))
                .addGap(18, 18, 18)
                .addComponent(settingsTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(settingsTabbedPane1)
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addComponent(userSettingsLabel)
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 231, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout contentPaneLayout = new javax.swing.GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(logoLabel)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPane, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(contentPane, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized

    }//GEN-LAST:event_formComponentResized

    private void usersListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_usersListValueChanged

        //if no user is selected, hide tab elements
        if (usersList.getSelectedIndex() == -1) {
            generalSettingsPanel.hideElements();
            communicationModuleSettingsPanel.hideElements();
        } else {
            selectedUser = usersList.getSelectedIndex();            
            try {
                User user = configurationHandler.getProfile(usersList.getSelectedValue().toString());
                generalSettingsPanel.repaintSettings(user);
                communicationModuleSettingsPanel.repaintSettings(user);
            } catch (IOException ex) {
                Logger.getLogger(ConfigurationFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_usersListValueChanged

    private String setImageGalleryPath(ReactorAdapter reactor) {
        //System.out.println("yes");
        String path = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Select Picture Folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().getAbsolutePath();
        } else {
            System.out.println("No Selection ");
        }
        SlideShowReactor r = (SlideShowReactor) reactor;
        r.setPath(path);
        return path;
    }

    private void initCustomComponents() {
        // Grid bag layout manager fill from left to right
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 9999;

        DefaultListModel model = new DefaultListModel();
        usersList.setModel(model);
        for (User user : configurationHandler.getProfiles()) {
            model.addElement(user.getName());
        }

        //initialize the tabs and the panels
        generalSettingsPanel = new GeneralSettingsPanel(this, this.mainFrame);
        settingsTabbedPane1.addTab("Γενικές ρυθμίσεις", generalSettingsPanel);

        communicationModuleSettingsPanel = new CommunicationModuleSettingsPanel();
        settingsTabbedPane1.addTab("Επικοινωνία", communicationModuleSettingsPanel);

        settingsTabbedPane1.addTab("Ψυχαγωγία", null);

        settingsTabbedPane1.addTab("Παιχνίδια", null);

        pack();
    }

    public void updateUsersList(String newName) {
        DefaultListModel model = (DefaultListModel) usersList.getModel();
        if (selectedUser != -1) {
            model.set(selectedUser, newName);
        }
    }

    public void removeFromUsersList() {
        DefaultListModel model = (DefaultListModel) usersList.getModel();
        if (selectedUser != -1) {
            model.remove(selectedUser);
        }
    }

    private Font originalFont;
    private GridBagConstraints gbc;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPane;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JTabbedPane settingsTabbedPane1;
    private javax.swing.JLabel userSettingsLabel;
    private javax.swing.JList usersList;
    // End of variables declaration//GEN-END:variables
}
