/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.reactors;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author xrousakis
 */
public class MultipleImages extends javax.swing.JFrame {

    private String currentIcon;
    private String imagesPath;
    private ArrayList<String> contentsNames;
    private int currentImage;
    private int TotalImages;
    private AudioInputStream audioStream;
    private Clip audioClip;
    private ArrayList<JLabel> lista;
    private int usedImages;
    private boolean redraw;
    private Object lock;
    private final int BORDER_SIZE = 5;
    private final int IMAGE_GAP = 20;

    /**
     * Creates new form Test
     *
     * @param imagesPath
     */
    public MultipleImages(String imagesPath) {
        initComponents();
        this.imagesPath = imagesPath;
        //initImages(2, 3);
    }

    public void setRedraw(boolean value) {
        synchronized (lock) {
            redraw = value;
        }
    }

    public boolean getRedraw() {
        synchronized (lock) {
            return redraw;
        }
    }

    private void initImages(int rows, int columns) {
        lock = new Object();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        imagesPanel.setLayout(new GridLayout(rows, columns, IMAGE_GAP, IMAGE_GAP));
        imagesPanel.setBackground(Color.WHITE);
        optionPanel.setBackground(Color.WHITE);
        readImages();
        usedImages = rows * columns;
        lista = new ArrayList();
        for (int i = 1; i <= usedImages; i++) {

            JLabel l = new JLabel();
            l.setBorder(createBorder(Color.WHITE));
            lista.add(l);
            imagesPanel.add(l);
        }
        currentImage = 0;

        this.revalidate();
        this.repaint();

        setImages(usedImages);

        setRedraw(false);
    }

    private void readImages() {
        contentsNames = new ArrayList();
        File folder = new File(imagesPath);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                contentsNames.add(listOfFiles[i].getName());
            }
        }
        TotalImages = contentsNames.size();
        Collections.sort(contentsNames);
        Collections.sort(contentsNames, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                String var1 = o1.split("\\.")[0];
                String var2 = o2.split("\\.")[0];
                return var1.compareTo(var2);
                //return Integer.parseInt(var1[0]) - Integer.parseInt(var2[0]);
            }
        });
    }

    public void redrawImages() {
        int numOfRows = (Integer) spinner1.getValue();
        int numOfCols = (Integer) spinner2.getValue();
        imagesPanel.removeAll();
        initImages(numOfRows, numOfCols);
        this.repaint();
        this.revalidate();
    }

    private void setImages(int activeImages) {
        for (int i = 0; i < activeImages; i++) {
            addLabel(imagesPath + "/" + contentsNames.get(currentImage++), lista.get(i));
        }

        currentImage = 0;
    }

    public int GetElapsedTime() {
        int value = epicSlider.getValue();
        return value;
    }

    private Border createBorder(Color c) {
        return BorderFactory.createLineBorder(c, BORDER_SIZE);
    }

    public void SwithcPic() {

        if (currentImage - 1 < 0) {
            lista.get(usedImages - 1).setBorder(createBorder(Color.WHITE));
        } else {
            lista.get(currentImage - 1).setBorder(createBorder(Color.WHITE));
        }
        lista.get(currentImage).setBorder(createBorder(Color.BLACK));
        currentImage++;
        if (currentImage + 1 > usedImages) {
            currentImage = 0;
        }
    }

    public void playMusic() {
        
        String path = System.getProperty("user.home") + "/water-dripping-1.wav";
        File audioFile = new File(path);
        try {
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(MultipleImages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopMusic() {
        audioClip.close();
        try {
            audioStream.close();
        } catch (IOException ex) {
            Logger.getLogger(MultipleImages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    private Dimension getImageScale(JLabel l, Image img) {
        Dimension d = new Dimension();

        int labelW = l.getWidth()-(2 * IMAGE_GAP) - (2 * BORDER_SIZE);
        int labelH = l.getHeight()-(2 * IMAGE_GAP) - (2 * BORDER_SIZE);
        int imgW = img.getWidth(rootPane);
        int imgH = img.getHeight(rootPane);
                
        double ratio = Math.min((double) labelW / imgW, (double) labelH / imgH);
        
        //System.out.println(ratio);
         
        d.setSize(imgW * ratio, imgH * ratio);

        return d;
    }

    private void addLabel(String file_path, JLabel label) {
        ImageIcon icon = new ImageIcon(file_path); 
        Image img = icon.getImage();
        
        Dimension d = getImageScale(label, img);

        Double w = d.getWidth();
        Double h = d.getHeight();
        
       
        int width = w.intValue();//- (2 * IMAGE_GAP) - (2 * BORDER_SIZE);
        int height = h.intValue();//-  (2 * IMAGE_GAP) - (2 * BORDER_SIZE);

         //
        System.out.println(w+" "+h+"|||||"+width+" "+height);
//        System.out.println("New width: " + width + " | new height: " + height);

        Image newimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        ImageIcon newIcon = new ImageIcon(newimg);
        label.setIcon(newIcon);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

//        imagesPanel.revalidate();
//        imagesPanel.repaint();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel2 = new javax.swing.JPanel();
        imagesPanel = new javax.swing.JPanel();
        optionPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        epicSlider = new javax.swing.JSlider();
        spinner1 = new javax.swing.JSpinner();
        spinner2 = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout imagesPanelLayout = new javax.swing.GroupLayout(imagesPanel);
        imagesPanel.setLayout(imagesPanelLayout);
        imagesPanelLayout.setHorizontalGroup(
            imagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        imagesPanelLayout.setVerticalGroup(
            imagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );

        jLabel1.setText("Change Frequency");

        epicSlider.setMajorTickSpacing(1);
        epicSlider.setMaximum(10);
        epicSlider.setMinimum(1);
        epicSlider.setPaintLabels(true);
        epicSlider.setPaintTicks(true);
        epicSlider.setValue(1);

        spinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, 4, 1));
        spinner1.setValue(2);

        spinner2.setModel(new javax.swing.SpinnerNumberModel(1, 1, 4, 1));
        spinner2.setValue(3);

        jLabel2.setText("Rows");

        jLabel3.setText("Columns");

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout optionPanelLayout = new javax.swing.GroupLayout(optionPanel);
        optionPanel.setLayout(optionPanelLayout);
        optionPanelLayout.setHorizontalGroup(
            optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, optionPanelLayout.createSequentialGroup()
                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(spinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(optionPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 393, Short.MAX_VALUE))
                    .addGroup(optionPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(spinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(23, 23, 23)))
                .addComponent(epicSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        optionPanelLayout.setVerticalGroup(
            optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionPanelLayout.createSequentialGroup()
                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(epicSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addGroup(optionPanelLayout.createSequentialGroup()
                        .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(optionPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(spinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(optionPanelLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(optionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(imagesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(optionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imagesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setRedraw(true);
        this.redrawImages();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider epicSlider;
    private javax.swing.JPanel imagesPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel optionPanel;
    private javax.swing.JSpinner spinner1;
    private javax.swing.JSpinner spinner2;
    // End of variables declaration//GEN-END:variables
}
