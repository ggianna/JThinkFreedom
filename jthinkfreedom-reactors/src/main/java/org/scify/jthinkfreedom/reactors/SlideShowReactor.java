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
    // private final SlideShowGui gui;
    private SlideShow gui;
    private String imagesPath;

    public SlideShowReactor() {
        super();
        open = false;
    }

    @Override
    public void react() {

        if (open == false) {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Select Picture Folder");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                imagesPath = chooser.getSelectedFile().getAbsolutePath();
            } else {
                System.out.println("No Selection ");
            }

            gui = new SlideShow(imagesPath);
            gui.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    open = false;
                    System.out.println("closed");
                }
            });
            open = true;
            gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
            gui.setVisible(true);
            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            gui.SwitchPic();
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCanonicalString() {
        return "Slide show";
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescription() {
        return "Perform a slide show";
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String args[]) throws InterruptedException {
        SlideShowReactor var = new SlideShowReactor();
        while(true){
            var.react();
            Thread.sleep(2000);
        }
    }

}
