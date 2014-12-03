/* 
 * Copyright 2014 SciFY.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
