/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.reactors;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;

/**
 *
 * @author xrousakis
 */
public class SlideShowReactor extends ReactorAdapter{
   private boolean open;
  // private final SlideShowGui gui;
  private final SlideShow gui;
    
  
   public  SlideShowReactor(String imagesPath){
         open=false;
         gui=new SlideShow(imagesPath);
        // gui=new SlideShowGui();
         /*gui.addWindowListener(new WindowAdapter(){
             public void WindowClosing(WindowEvent e){
                 //open=false;
                 System.out.println("closed");
             }
         });
         //gui=new SlideShow();*/
         gui.addWindowListener(new WindowAdapter(){
             public void windowClosing(WindowEvent e)
                {   open=false;
                    System.out.println("closed");
                }
         });
    }

    @Override
    public void react() {
        //open a window that displays an image
        if(open==false ){
            //System.out.println("here");
            open =true;
            gui.setVisible(true);
            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else{
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
    
    
    public static void main(String args[]) throws InterruptedException{
      //SlideShowReactor var=new SlideShowReactor("/home/xrousakis/JThinkFreedom/jthinkfreedom-reactors/src/main/resources/SampeImages");
      ///home/xrousakis/Desktop
       SlideShowReactor var=new SlideShowReactor("/home/xrousakis/Desktop/images");
      var.react();
      Thread.sleep(1000);
      var.react();
    }
    
}
