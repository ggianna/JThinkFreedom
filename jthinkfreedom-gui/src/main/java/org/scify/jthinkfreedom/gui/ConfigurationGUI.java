package org.scify.jthinkfreedom.gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import org.scify.jthinkfreedom.gui.panels.ConfigPanel;
import org.scify.jthinkfreedom.gui.panels.ReactorsPanel;

/**
 *
 * @author peustr
 */
public class ConfigurationGUI extends JFrame {

    private ConfigPanel configPanel;
    private ReactorsPanel reactorsPanel;

    public ConfigurationGUI() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Think Freedom Configuration");
        setPreferredSize(new Dimension(680, 420));
        setMinimumSize(new Dimension(680, 420));
        setMaximumSize(new Dimension(680, 420));
        configPanel = new ConfigPanel();
        reactorsPanel = new ReactorsPanel();
        add(configPanel);
        pack();
    }

    public void next() {
        remove(configPanel);
        add(reactorsPanel);
        pack();
        repaint();
    }

    public void back() {
        remove(reactorsPanel);
        add(configPanel);
        pack();
        repaint();
    }

    public ConfigPanel getConfigPanel() {
        return configPanel;
    }

    public ReactorsPanel getReactorsPanel() {
        return reactorsPanel;
    }

    public static void main(String[] args) {
        (new ConfigurationGUI()).setVisible(true);
    }
}
