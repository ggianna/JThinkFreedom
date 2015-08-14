/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.reactors;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;

/**
 *
 * @author xrousakis
 */
public class SlideShowReactor extends ReactorAdapter {

    private boolean open;
    private SlideShow gui;
    private String imagesPath;
    private boolean keepRunning;
    private final Object lock = new Object();
    Thread t;
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
            gui = new SlideShow(imagesPath);

            gui.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    open = false;
                    System.out.println("closed");
                }
            });
            try {
                open = true;
                gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
                gui.setMaximizedLabel();
                gui.setVisible(true);
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                keepRunning = true;
                Run();
            } catch (Exception ex) {
                System.out.println(ex.getCause());
            }
        } else {
            //gui.SwitchPic();
            if (keepRunning == true) {
                //setStateOfRun(false)
                t.suspend();
                keepRunning=false;
            } else {
                t.resume();
                
                keepRunning=true;
                //System.out.println(keepRunning);
                //setStateOfRun(true);
                
            }
        }

    }

    public boolean getStateOfRun() {
        synchronized (lock) {
            return keepRunning;
        }
    }

    public void setStateOfRun(boolean run) {
        synchronized (lock) {
            keepRunning = run;
        }
    }

    public void Run() {
        //keepRunning=true;
         t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("HERERE");
                while (true) {
                    //System.out.println("heres");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SlideShowReactor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    gui.SwitchPic();
                }
                
            }
        });
        t.start();
    }

    public void setPath(String path) {
        imagesPath = path;
        //System.out.println(path);
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
