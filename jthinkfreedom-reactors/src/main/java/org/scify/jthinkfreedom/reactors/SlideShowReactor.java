/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.reactors;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

    public SlideShowReactor() {
        super();
        open = false;
    }

    public String Configure() {
        return "";
    }

    @Override
    public void react() {
       
        if (open == false ) { 
             
            gui = new SlideShow(imagesPath);
            /*
            gui.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    open = false;
                    System.out.println("closed");
                }
            });
            try {
                open = true;
                gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
                gui.setVisible(true);
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } catch (Exception ex) {
                System.out.println(ex.getCause());
            }*/
             
            JFrame jf=new JFrame();
            jf.setVisible(true);
            
        } else {
            gui.SwitchPic();
        }

    }

    public void setPath(String path) {
        
        imagesPath = path;
        System.out.println(path);
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
