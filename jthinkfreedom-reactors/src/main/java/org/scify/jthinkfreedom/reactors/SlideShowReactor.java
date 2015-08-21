/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.reactors;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;

/**
 *
 * @author xrousakis
 */
public class SlideShowReactor extends ReactorAdapter {

    private boolean open;
    //private SlideShow gui;
    private MultipleImages gui;
    private String imagesPath;
    private boolean keepRunning;
    Thread t;

    public SlideShowReactor() {
        super();
        open = false;
    }

    public String Configure() {
        return "";
    }

    /*public void frame__windowStateChanged(WindowEvent e) {
        // minimized
        if ((e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
            System.out.println("Minimized");
        }// maximized
        else if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
            System.out.println("Maximised");

        }
    }*/

    @Override
    public void react() {

        if (open == false) {
            //gui = new SlideShow(imagesPath);
            gui = new MultipleImages(imagesPath);
            gui.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    open = false;
                    System.out.println("closed");

                }
            });
            try {
                open = true;

                /*gui.addWindowStateListener(new WindowStateListener() {
                    public void windowStateChanged(WindowEvent arg0) {
                        frame__windowStateChanged(arg0);
                    }
                });*/
                gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
                gui.setTitle("Image Display");
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setVisible(true);
                gui.redrawImages();
                keepRunning = true;
                Run();
            } catch (Exception ex) {
                System.out.println(ex.getCause());
            }
        } else {
            if (keepRunning == true) {
                t.suspend();
                gui.playMusic();
                keepRunning = false;

            } else {
                gui.stopMusic();
                t.resume();
                keepRunning = true;
            }
        }
    }

    public void Run() {
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(gui.GetElapsedTime() * 1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SlideShowReactor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (gui.getRedraw() == false) {
                        gui.SwithcPic();
                    }
                }
            }
        });
        t.start();
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
