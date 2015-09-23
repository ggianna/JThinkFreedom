/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.reactors;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;

/**
 *
 * @author xrousakis
 */
public class SlideShowReactor extends ReactorAdapter {

    private boolean open;
    private MultipleImages gui;
    private String imagesPath;

    public SlideShowReactor() {
        super();
        open = false;
    }

    public String Configure() {
        return "";
    }

    @Override
    public void react() {

        if (open == false) {
            
            gui = new MultipleImages(imagesPath);
            gui.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    open = false;
                }
            });
            try {
                open = true;
                gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
                gui.setTitle("Image Display");
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setVisible(true);
                gui.redrawImages();
                gui.run();
                gui.addWindowListener(new WindowListener() {

                    @Override
                    public void windowOpened(WindowEvent e) {
                    }

                    @Override
                    public void windowClosing(WindowEvent e) {
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                    }

                    @Override
                    public void windowIconified(WindowEvent e) {
                        gui.stopThread(false);
                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {
                        gui.beginThread(false);
                    }

                    @Override
                    public void windowActivated(WindowEvent e) {
                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {
                    }
                });
            } catch (Exception ex) {
                System.out.println(ex.getCause());
            }
        } else {
            if (gui.isState()) {
                gui.stopThread(true);
            } else {
                gui.beginThread(true);
            }
        }
    }

    public void setPath(String path) {
        imagesPath = path;
    }

    @Override
    public String getCanonicalString() {
        return "Slideshow";
    }

    @Override
    public String getDescription() {
        return "Perform a slide show";
    }

}
