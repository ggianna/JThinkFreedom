
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.reactors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import javax.swing.border.LineBorder;

/**
 *
 * @author xrousakis
 */
public class MultipleImages extends javax.swing.JFrame {

    /*these 2 variables can be used if you want to read images directly from the file and avoid the xml*/
    protected final String imagesPath;
    protected ArrayList<String> contentsNames;

    protected int currentImage;
    protected AudioInputStream audioStream;
    protected Clip audioClip;

    protected ArrayList<JPanel> paneList;
    protected int usedImages;
    protected boolean redraw;

    protected final Object lock;
    protected Thread thread;
    protected boolean state;

    protected final int BORDER_SIZE = 5;
    protected final int IMAGE_GAP = 10;

    protected int tmpCurrentImage;

    protected Category currentCategory;
    protected ArrayList<Tile> directTiles;
    protected ArrayList<Tile> subCategoriesTiles;

    protected ArrayList<Category> categories;
    protected ArrayList<Tile> contents;

    /**
     * Creates new form Test
     *
     * @param imagesPath
     */
    public MultipleImages(String imagesPath) {
        initComponents();
        this.imagesPath = imagesPath;
        lock = new Object();
        parse();
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

    /*Sets the dimension for the gridLayout of the imagesPanel ,creates panel with images and text and stores them,calls function to put info(image,data) in the panels*/
    private void initImages(int rows, int columns) {
        imagesPanel.setLayout(new GridLayout(rows, columns, IMAGE_GAP, IMAGE_GAP));
        imagesPanel.setBackground(Color.WHITE);
        optionPanel.setBackground(Color.WHITE);
        paneList = new ArrayList();
        // For each image
        for (int i = 1; i <= usedImages; i++) {
            JPanel panel = createLabeledImageItem();
            // Add to image panel list
            imagesPanel.add(panel);
        }
        if (!currentCategory.getName().equalsIgnoreCase("main menu")) {
            setBack();
        }

        currentImage = 0;

        this.revalidate();
        this.repaint();

        setImages(usedImages);
        setRedraw(false);

        System.out.println("usedImges" + usedImages);
    }

    /*Create a new panel that contains the text BACK ,if selected by ther user show the contents of the previous category*/
    private void setBack() {
        usedImages++;
        JPanel panel = new JPanel(new BorderLayout());
        paneList.add(panel);
        JLabel imgLabel = new JLabel("");
        JLabel txtLabel = new JLabel("BACK");
        txtLabel.setFont(new Font("Courier New", Font.PLAIN, 40));
        panel.setBorder(createBorder(Color.WHITE));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(txtLabel, BorderLayout.CENTER);
        panel.add(imgLabel, BorderLayout.SOUTH);
        txtLabel.setHorizontalAlignment(JLabel.CENTER);
        imagesPanel.add(panel);
        contents.add(new Tile("", "", "none", ""));
    }

    /*Creates new panel with image and text label and positions them with border layout then adds this panel to panel list which holds all the panels that contain image and text label*/
    private JPanel createLabeledImageItem() {
        JPanel panel = new JPanel(new BorderLayout());
        paneList.add(panel);
        JLabel imgLabel = new JLabel();
        JLabel txtLabel = new JLabel(" ");
        txtLabel.setFont(new Font("Courier New", Font.PLAIN, 40));
        panel.setBorder(createBorder(Color.WHITE));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(imgLabel, BorderLayout.CENTER);
        panel.add(txtLabel, BorderLayout.NORTH);
        txtLabel.setHorizontalAlignment(JLabel.CENTER);
        return panel;
    }

    private void parse() {
        contents = new ArrayList();
        Parser parser = new Parser();
        categories = parser.getCategories();
        currentCategory = categories.get(0);
        System.out.println(currentCategory.getName());
        directTiles = currentCategory.getTiles();
        subCategoriesTiles = currentCategory.getSubCategoriesTiles();
        usedImages = directTiles.size() + subCategoriesTiles.size();
        contents.addAll(directTiles);
        contents.addAll(subCategoriesTiles);
        for (Tile t : contents) {
            System.out.println(t.getImagePath());
        }
    }

    /*Change the content in orber to be relevant to this category*/
    public void changeCategory() {
        if (contents.get(tmpCurrentImage).getCategory().equals("none")) {
            exitSubCategory();
            return;
        }

        int chosenCategory = tmpCurrentImage - directTiles.size();
        currentCategory = currentCategory.getSubCategories().get(chosenCategory);
        adjustStoredInfo(currentCategory);
        /*for (Tile t : contents) {
         System.out.println(t.getTxt());
         }*/
    }

    public void adjustStoredInfo(Category currentCategory) {
        contents = new ArrayList();
        //currentCategory = currentCategory.getSubCategories().get(category);
        directTiles = currentCategory.getTiles();
        subCategoriesTiles = currentCategory.getSubCategoriesTiles();
        usedImages = directTiles.size() + subCategoriesTiles.size();
        contents.addAll(directTiles);
        contents.addAll(subCategoriesTiles);
        redrawImages();
    }

    /*Retrieve to the previous category(to the direct parent category)*/
    public void exitSubCategory() {
        /*first find if the back button was pressed and not another button*/
        currentCategory = currentCategory.getParentCategory();
        adjustStoredInfo(currentCategory);

    }

    private boolean currentImageIsCategory() {
        if (tmpCurrentImage > directTiles.size() - 1) {
            return true;
        } else {
            return false;
        }
    }

    /*Wipes the JFrame clean from any components and calls init to repaint it */
    public void redrawImages() {
        imagesPanel.removeAll();
        initImages(currentCategory.getRows(), currentCategory.getColumns());
        repaint();
        revalidate();
    }

    /*Sets */
    private void setImages(int activeImages) {
        for (int i = 0; i < activeImages; i++) {
            addLabel(contents.get(i).getImagePath(), paneList.get(i), contents.get(i).getTxt());
        }
    }

    /*Returns the value of the slider which determines the frequency in which the images change*/
    public int getElapsedTime() {
        int value = epicSlider.getValue();
        return value;
    }

    /*Creates border given the color*/
    private Border createBorder(Color c) {
        return BorderFactory.createLineBorder(c, BORDER_SIZE);
    }

    /*Creates and begins the thread thats responsible for the change of border*/
    public void run() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(getElapsedTime() * 1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SlideShowReactor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (getRedraw() == false) {
                        swithcPic();
                    }
                }
            }
        });
        thread.start();
        state = true;
    }

    /*Stops music and begins thread that changes border*/
    public void beginThread() {
        stopMusic();
        state = true;
        thread.resume();
    }

    /*Stops the thread that chages the border of images and plays music*/
    public void stopThread() {
        if (!currentImageIsCategory()) {
            state = false;
            thread.suspend();
            playMusic();

        } else {
            changeCategory();
        }
    }

    /*Retruns the stete of the thread that chages border*/
    public boolean isState() {
        return state;
    }

    /*Sets stete of thread*/
    public void setState(boolean state) {
        this.state = state;
    }

    public void swithcPic() {
        //System.out.println(currentImage);
        tmpCurrentImage = currentImage;
        int previous;
        if (currentImage - 1 < 0) {
            paneList.get(usedImages - 1).setBorder(createBorder(Color.WHITE));
            previous = usedImages - 1;
        } else {
            paneList.get(currentImage - 1).setBorder(createBorder(Color.WHITE));
            previous = currentImage - 1;
        }
        if (((LineBorder) paneList.get(previous).getBorder()).getLineColor().equals(Color.WHITE)) {
            paneList.get(currentImage).setBorder(createBorder(Color.BLACK));
        }
        currentImage++;
        //System.out.println("Made change");
        if (currentImage + 1 > usedImages) {
            currentImage = 0;
        }

    }

    /*Plays the  wav file*/
    public void playMusic() {
        String path = System.getProperty("user.dir") + "/sound.wav";
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

    //stop wav file from playing
    public void stopMusic() {
        audioClip.close();
        try {
            audioStream.close();
        } catch (IOException ex) {
            Logger.getLogger(MultipleImages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*Scales the image to fit at a better ratio in the dimensions of the grid cell*/
    private Dimension getImageScale(JLabel l, Image img) {
        Dimension d = new Dimension();
        int labelW = l.getWidth() - (2 * IMAGE_GAP) - (2 * BORDER_SIZE);
        int labelH = l.getHeight() - (2 * IMAGE_GAP) - (2 * BORDER_SIZE);
        int imgW = img.getWidth(rootPane);
        int imgH = img.getHeight(rootPane);
        double ratio = Math.min((double) labelW / imgW, (double) labelH / imgH);
        d.setSize(imgW * ratio, imgH * ratio);
        return d;
    }

    /*adds images with their representing text into the jlabels*/
    private void addLabel(String file_path, JPanel panel, String txtName) {
        JLabel label = (JLabel) panel.getComponents()[0];
        JLabel txtLabel = (JLabel) panel.getComponents()[1];
        txtLabel.setFont(new Font("Courier New", Font.PLAIN, 40));
        txtLabel.setText(txtName);
        ImageIcon icon;
        if (!file_path.equalsIgnoreCase("")) {
            icon = new ImageIcon(file_path);
            Image img = icon.getImage();
            Dimension d = getImageScale(label, img);
            Double w = d.getWidth();
            Double h = d.getHeight();
            int width = w.intValue();
            int height = h.intValue();
            Image newimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newimg);
            label.setIcon(newIcon);
        }

        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel2 = new javax.swing.JPanel();
        imagesPanel = new javax.swing.JPanel();
        optionPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        epicSlider = new javax.swing.JSlider();
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
            .addGap(0, 200, Short.MAX_VALUE)
        );

        jLabel1.setText("Change Frequency");

        epicSlider.setMajorTickSpacing(1);
        epicSlider.setMaximum(10);
        epicSlider.setMinimum(1);
        epicSlider.setPaintLabels(true);
        epicSlider.setPaintTicks(true);
        epicSlider.setValue(1);

        jButton1.setText("Restore images");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout optionPanelLayout = new javax.swing.GroupLayout(optionPanel);
        optionPanel.setLayout(optionPanelLayout);
        optionPanelLayout.setHorizontalGroup(
            optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 261, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addComponent(epicSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        optionPanelLayout.setVerticalGroup(
            optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionPanelLayout.createSequentialGroup()
                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(epicSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addGroup(optionPanelLayout.createSequentialGroup()
                        .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, optionPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(optionPanelLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(optionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(imagesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        this.setRedraw(true);
        if (!currentCategory.getName().equalsIgnoreCase("main menu")) {
            usedImages--;
        }
        this.redrawImages();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider epicSlider;
    private javax.swing.JPanel imagesPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel optionPanel;
    // End of variables declaration//GEN-END:variables
}
