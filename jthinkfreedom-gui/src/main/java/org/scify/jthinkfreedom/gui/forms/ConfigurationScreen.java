package org.scify.jthinkfreedom.gui.forms;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import org.scify.jthinkfreedom.gui.model.Configuration;
import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;
import org.scify.jthinkfreedom.skeleton.sensors.SensorAdapter;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAdapter;

/**
 *
 * @author peustr
 */
public class ConfigurationScreen extends javax.swing.JFrame {

    private static final int VIEW_LIMIT = 2;

    private ProfilePanel caller;
    private ProfileScreen previous;
    private List<ConfigurationPanel> configurations;
    private ReactorPanel reactorPanel;
    private SensorPanel sensorPanel;
    private StimulusPanel stimulusPanel;

    private ReactorAdapter reactor;
    private SensorAdapter sensor;
    private StimulusAdapter stimulus;
    
    /**
     * Creates new form ConfigurationScreen
     */
    public ConfigurationScreen() {
        initComponents();
    }

    public ConfigurationScreen(ProfilePanel caller, ProfileScreen previous) {
        this.caller = caller;
        this.previous = previous;
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
        profilesActionsLabel = new javax.swing.JLabel();
        pictureLabel = new javax.swing.JLabel();
        backLabel = new javax.swing.JLabel();
        configurationPanel = new javax.swing.JPanel();
        addActionButton = new javax.swing.JButton();
        actionSelectionPanel = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        breadcrumbLabel = new javax.swing.JLabel();
        undoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Think Freedom");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        contentPane.setBackground(new java.awt.Color(255, 255, 255));
        contentPane.setPreferredSize(new java.awt.Dimension(800, 720));

        profilesActionsLabel.setFont(new java.awt.Font("Comfortaa", 1, 24)); // NOI18N
        profilesActionsLabel.setText("This profile's actions");

        pictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scify/jthinkfreedom/gui/resources/placeholder_144x144.png"))); // NOI18N

        backLabel.setFont(new java.awt.Font("Comfortaa", 1, 14)); // NOI18N
        backLabel.setForeground(new java.awt.Color(51, 51, 255));
        backLabel.setText("Back");
        backLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backLabelMouseEntered(evt);
            }
        });

        configurationPanel.setBackground(new java.awt.Color(255, 255, 255));
        configurationPanel.setLayout(new java.awt.GridBagLayout());

        addActionButton.setFont(new java.awt.Font("Comfortaa", 1, 14)); // NOI18N
        addActionButton.setText("Click to add a new action");
        addActionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionButtonActionPerformed(evt);
            }
        });

        actionSelectionPanel.setBackground(new java.awt.Color(255, 255, 255));
        actionSelectionPanel.setLayout(new java.awt.GridBagLayout());

        saveButton.setFont(new java.awt.Font("Comfortaa", 1, 14)); // NOI18N
        saveButton.setText("Save");
        saveButton.setEnabled(false);

        breadcrumbLabel.setFont(new java.awt.Font("Comfortaa", 1, 14)); // NOI18N
        breadcrumbLabel.setText("Currently selected: ");

        undoLabel.setFont(new java.awt.Font("Comfortaa", 1, 14)); // NOI18N
        undoLabel.setForeground(new java.awt.Color(51, 51, 255));
        undoLabel.setText("Undo");
        undoLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        undoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                undoLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                undoLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                undoLabelMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout contentPaneLayout = new javax.swing.GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addComponent(configurationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(backLabel)
                            .addComponent(pictureLabel)))
                    .addComponent(addActionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addComponent(profilesActionsLabel)
                        .addGap(0, 537, Short.MAX_VALUE))
                    .addComponent(actionSelectionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                        .addComponent(breadcrumbLabel)
                        .addGap(18, 18, 18)
                        .addComponent(undoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton)))
                .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profilesActionsLabel)
                .addGap(18, 18, 18)
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addComponent(pictureLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backLabel))
                    .addComponent(configurationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(addActionButton)
                .addGap(18, 18, 18)
                .addComponent(actionSelectionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(saveButton)
                    .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(breadcrumbLabel)
                        .addComponent(undoLabel)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(contentPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(contentPane, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backLabelMouseEntered
        originalFont = backLabel.getFont();
        Map attributes = originalFont.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        backLabel.setFont(originalFont.deriveFont(attributes));
    }//GEN-LAST:event_backLabelMouseEntered

    private void backLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backLabelMouseExited
        backLabel.setFont(originalFont);
    }//GEN-LAST:event_backLabelMouseExited

    private void backLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backLabelMouseClicked
        previous.setVisible(true);
        dispose();
    }//GEN-LAST:event_backLabelMouseClicked

    private void addActionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionButtonActionPerformed
        renderReactionPanel();
    }//GEN-LAST:event_addActionButtonActionPerformed

    public void renderReactionPanel() {
        actionSelectionPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        actionSelectionPanel.add(reactorPanel, gbc);
        reactorPanel.setVisible(true);
        sensorPanel.setVisible(false);
        stimulusPanel.setVisible(false);
        pack();
    }

    public void renderSensorPanel() {
        actionSelectionPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        actionSelectionPanel.add(sensorPanel, gbc);
        reactorPanel.setVisible(false);
        sensorPanel.setVisible(true);
        stimulusPanel.setVisible(false);
        pack();
    }

    public void renderStimulusPanel() {
        actionSelectionPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        actionSelectionPanel.add(stimulusPanel, gbc);
        reactorPanel.setVisible(false);
        sensorPanel.setVisible(false);
        stimulusPanel.setVisible(true);
        pack();
    }

    public void updateBreadcrumbs(String addition) {
        StringBuilder sb = new StringBuilder();
        String curText = breadcrumbLabel.getText();
        sb.append(curText).append(addition);
        breadcrumbLabel.setText(sb.toString());
        pack();
    }
    
    public void updateSelection() {
        
    }

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized

    }//GEN-LAST:event_formComponentResized

    private void undoLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_undoLabelMouseEntered
        originalFont = backLabel.getFont();
        Map attributes = originalFont.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        undoLabel.setFont(originalFont.deriveFont(attributes));
    }//GEN-LAST:event_undoLabelMouseEntered

    private void undoLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_undoLabelMouseExited
        undoLabel.setFont(originalFont);
    }//GEN-LAST:event_undoLabelMouseExited

    private void undoLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_undoLabelMouseClicked
        if (sensorPanel.isVisible()) {
            renderReactionPanel();
            breadcrumbLabel.setText("Currently selected: ");
        } else if (stimulusPanel.isVisible()) {
            renderSensorPanel();
            String[] splitBread = breadcrumbLabel.getText().split("/");
            breadcrumbLabel.setText(splitBread[0] + "/");
        }
    }//GEN-LAST:event_undoLabelMouseClicked

    private void initCustomComponents() {
        reactorPanel = new ReactorPanel(this);
        reactorPanel.setVisible(false);
        sensorPanel = new SensorPanel(this);
        sensorPanel.setVisible(false);
        stimulusPanel = new StimulusPanel(this);
        stimulusPanel.setVisible(false);
        configurations = new ArrayList<>();
        // Grid bag layout manager fill from left to right
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        // Put the user's configurations in the panel
        for (Configuration config : caller.getProfile().getConfigurations()) {
            configurations.add(new ConfigurationPanel(config));
        }
        if (!configurations.isEmpty()) {
            for (int i = 0; i < VIEW_LIMIT; i++) {
                try {
                    configurationPanel.add(configurations.get(i), gbc);
                } catch (IndexOutOfBoundsException e) {
                    // Do nothing
                }
            }
        }
        profilesActionsLabel.setText(caller.getProfile().getName() + "'s actions");
        pack();
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    private Font originalFont;
    private GridBagConstraints gbc;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionSelectionPanel;
    private javax.swing.JButton addActionButton;
    private javax.swing.JLabel backLabel;
    private javax.swing.JLabel breadcrumbLabel;
    private javax.swing.JPanel configurationPanel;
    private javax.swing.JPanel contentPane;
    private javax.swing.JLabel pictureLabel;
    private javax.swing.JLabel profilesActionsLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel undoLabel;
    // End of variables declaration//GEN-END:variables
}
