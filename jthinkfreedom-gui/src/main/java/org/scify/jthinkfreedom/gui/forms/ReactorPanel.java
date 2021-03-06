package org.scify.jthinkfreedom.gui.forms;

import java.awt.GridBagConstraints;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.scify.jthinkfreedom.reactors.ApplicationLaunchReactor;
import org.scify.jthinkfreedom.reactors.LeftClickReactor;
import org.scify.jthinkfreedom.reactors.MouseMoveReactor;
import org.scify.jthinkfreedom.reactors.PlaySoundReactor;
import org.scify.jthinkfreedom.reactors.RightClickReactor;
import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;

/**
 *
 * @author peustr
 */
public class ReactorPanel extends JPanel {

    private static final int GRID_X_LIMIT = 2; // Zero based indexing

    private ConfigurationScreen parent;
    private Map<ReactorAdapter, Icon> reactors; // To add a new reactor, modify initReactorPanels

    /**
     * Creates new form ReactorPanel
     */
    public ReactorPanel() {
        initComponents();
    }

    public ReactorPanel(ConfigurationScreen parent) {
        this.parent = parent;
        initComponents();
        initReactorPanels();
        initCustomComponents();
    }

    public void transitionToSensors() {
        parent.renderSensorPanel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        iwanttoLabel = new javax.swing.JLabel();
        reactorPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(242, 244));

        iwanttoLabel.setFont(new java.awt.Font("Comfortaa", 1, 24)); // NOI18N
        iwanttoLabel.setText("I want to...");

        reactorPanel.setBackground(new java.awt.Color(255, 255, 255));
        reactorPanel.setPreferredSize(new java.awt.Dimension(84, 220));
        reactorPanel.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iwanttoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(reactorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reactorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(iwanttoLabel))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void initReactorPanels() {
        reactors = new HashMap<>();
        reactors.put(new ApplicationLaunchReactor(null), new ImageIcon(getClass().getResource("/org/scify/jthinkfreedom/gui/resources/rocket_64x64.png")));
        reactors.put(new LeftClickReactor(), new ImageIcon(getClass().getResource("/org/scify/jthinkfreedom/gui/resources/left_click_64x64.png")));
        reactors.put(new RightClickReactor(), new ImageIcon(getClass().getResource("/org/scify/jthinkfreedom/gui/resources/right_click_64x64.png")));
        reactors.put(new PlaySoundReactor(null), new ImageIcon(getClass().getResource("/org/scify/jthinkfreedom/gui/resources/sound_64x58.png")));
        reactors.put(new MouseMoveReactor(null), new ImageIcon(getClass().getResource("/org/scify/jthinkfreedom/gui/resources/cursor_64x64.png")));
    }

    private void initCustomComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        for (Entry<ReactorAdapter, Icon> entry : reactors.entrySet()) {
            reactorPanel.add(new MinorComponent(entry.getKey(), this, entry.getKey().getDescription(), entry.getValue()), gbc);
            gbc.gridx++;
            if (gbc.gridx > GRID_X_LIMIT) {
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }
    }

    public void updateBreadcrumbs(String addition) {
        parent.updateBreadcrumbs(addition);
    }

    public void setReactorClass(ReactorAdapter ra) {
        parent.setReactor(ra);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iwanttoLabel;
    private javax.swing.JPanel reactorPanel;
    // End of variables declaration//GEN-END:variables
}
