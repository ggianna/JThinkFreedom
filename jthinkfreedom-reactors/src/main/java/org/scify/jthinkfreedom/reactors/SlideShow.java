/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.reactors;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
//import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author xrousakis
 */
public class SlideShow extends javax.swing.JFrame {

    private String currentIcon;
    private final String ImagesPath;
    private final ArrayList<String> contentsNames;
    private int currentImage;
    private final int numberOfImages;
    private boolean perform_change;

    /**
     * Creates new form SlideShow
     */
    public SlideShow(String imagesPath) {

        perform_change = true;

        contentsNames = new ArrayList();
        currentImage = 0;
        this.ImagesPath = imagesPath;
        File folder = new File(imagesPath);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                contentsNames.add(listOfFiles[i].getName());
            }
        }

        numberOfImages = contentsNames.size();
        System.out.println(contentsNames);
        //System.out.println(numberOfImages);
        //System.out.println(contentsNames.get(0));

        initComponents();
        ImageIcon icon = new ImageIcon(imagesPath + "/" + contentsNames.get(currentImage));
        System.out.println(contentsNames.get(currentImage) + currentImage);
        icon.getImage().getScaledInstance(300, 10, java.awt.Image.SCALE_SMOOTH);
        ImageLabel.setIcon(icon);
        currentImage++;
    }

    public void SwitchPic() {
        if (perform_change == true) {
            ImageIcon icon = null;
            if (numberOfImages - 1 == currentImage) {
                currentImage = 0;
            }
            icon = new ImageIcon(ImagesPath + "/" + contentsNames.get(currentImage));
            ImageLabel.setIcon(icon);
            currentImage++;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ImageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ImageLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ImageLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ImageLabelMouseClicked
        // TODO add your handling code here:
        /*if (perform_change = true) {
            perform_change = false;
            ImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        } else {
            perform_change = true;
            ImageLabel.setBorder(null);
        }*/
        //ImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        //BorderFactory.createLineBorder(Color.black)
        if(perform_change == true){
            perform_change = false;
           
            //ImageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        else{
            perform_change = true;
            setBackground(null);
            //ImageLabel.setBorder(null);
        }
    }//GEN-LAST:event_ImageLabelMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SlideShow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SlideShow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SlideShow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SlideShow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SlideShow("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ImageLabel;
    // End of variables declaration//GEN-END:variables
}
