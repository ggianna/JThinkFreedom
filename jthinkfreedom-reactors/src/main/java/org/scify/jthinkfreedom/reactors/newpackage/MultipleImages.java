
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.scify.jthinkfreedom.reactors.newpackage;
import org.scify.jthinkfreedom.reactors.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
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

    protected String currentIcon;
    protected final String imagesPath;
    protected ArrayList<String> contentsNames;
    protected int currentImage;
    protected int TotalImages;
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
    protected String currentCategory;
    protected Map<String, String> hierarchy;
    protected Map<String, ArrayList<Tile>> childTiles;
    protected ArrayList<Tile> contents;
    protected Map<String, ArrayList> categoryDimensions;

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
        //imagesPanel.setLayout(new GridLayout(rows, columns, IMAGE_GAP, IMAGE_GAP));
        imagesPanel.setBackground(Color.WHITE);
        optionPanel.setBackground(Color.WHITE);
        paneList = new ArrayList();
        // For each image
        for (int i = 1; i <= usedImages; i++) {
            JPanel panel = createLabeledImageItem("");

            // Add to image panel list
            imagesPanel.add(panel);
        }
        if (!currentCategory.equalsIgnoreCase("main menu")) {
            setBack();
        }
        currentImage = 0;
        this.revalidate();
        this.repaint();
        setImages(usedImages);
        setRedraw(false);
        System.out.println(usedImages+" "+currentImage);
    }

    /*Create a new panel that contains the text BACK ,if selected by ther user show the contents of the previous category*/
    private void setBack() {
        JPanel panel = new JPanel(new BorderLayout());
        usedImages++;
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
        contents.add(new Tile("", "", "none"));
    }

    /*Creates new panel with image and text label and positions them with border layout then adds this panel to panel list which holds all the panels that contain image and text label*/
    private JPanel createLabeledImageItem(String value) {
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

    /*parse information about images from the categories.xml in the home folder and pass the information about the tiles in to the local variables*/
    private void parse() {
        contents = new ArrayList<>();
        TileXmlParser parser = new TileXmlParser();
        /*the map that holds the structure of the xml pecking order*/
        hierarchy = parser.getHierarchy();
        /*hold the tiles per category*/
        childTiles = parser.getTiles();
        /*hold the rootnode of the categories xml*/
        String rootNode = parser.getRoot();
        usedImages = childTiles.get(rootNode).size();
        
        TotalImages = childTiles.get(rootNode).size();
        
        currentCategory = rootNode;
        contents = (ArrayList<Tile>) childTiles.get(rootNode).clone();
        /*hold the grid dimensions of each category*/
        categoryDimensions=parser.getCategoryDimensions();
    }

    /*Gives to contents variable the tiles of the subcategory and changes the number of active images then redraws the jframe componetns with the new information*/
    /*private void enterSubCategory() {
        for (Map.Entry<String, String> entry : hierarchy.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(currentCategory)) {
                String subCategory = childTiles.get(currentCategory).get(tmpCurrentImage).getCategory();
                if (!currentCategory.equalsIgnoreCase("main menu")) {
                    contents.remove(contents.size() - 1);
                }
                contents = childTiles.get(subCategory);
                usedImages = contents.size();
                currentCategory = subCategory;
                break;
            }
        }
        this.redrawImages();
    }*/

    /*Gives to contents variable the tiles of the higher category and changes the number of active images then redraws the the jframe componetns with the new information(image,text)*/
    private void exitSubCategory() {
        for (Map.Entry<String, String> entry : hierarchy.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(currentCategory)) {
                String higherCategory = entry.getValue();
                contents.remove(contents.size() - 1);
                contents = childTiles.get(entry.getValue());
                usedImages = contents.size();
                currentCategory = higherCategory;
                break;
            }
        }
        this.redrawImages();
    }

    /*Reads images from image folder and the folders path is stored in imagesPath*/
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
            }
        });
    }

    /*Wipes the JFrame clean from any components and calls init to repaint it */
    public void redrawImages() {
        imagesPanel.removeAll();
        ArrayList<Integer> list =categoryDimensions.get(currentCategory);
        int numOfRows=list.get(0);
        int numOfColumns=list.get(1);
        /*Rows and Columns for the gridLayoyt*/
        initImages(numOfRows, numOfColumns);
        this.repaint();
        this.revalidate();
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
        thread.resume();
        state = true;
    }

    /*Stops the thread that chages the border of images and plays music*/
    public void stopThread() {
       // if  {
            System.out.println(currentImage);
            thread.suspend();
            playMusic();
            state = false;
        //}
    }

    /*Retruns the stete of the thread that chages border*/
    public boolean isState() {
        return state;
    }

    /*Sets stete of thread*/
    public void setState(boolean state) {
        this.state = state;
    }

    /*Changes the border of the current image to black and of the previous to white*/
    public void swithcPic() {
        //tmpCurrentImage = currentImage;
        if (currentImage - 1 < 0) {
            paneList.get(usedImages - 1).setBorder(createBorder(Color.WHITE));
        } else {
            paneList.get(currentImage - 1).setBorder(createBorder(Color.WHITE));
        }
        paneList.get(currentImage).setBorder(createBorder(Color.BLACK));
        currentImage++;
        if (currentImage + 1 > usedImages) {
            currentImage = 0;
        }
        
    }

    /*Plays the a wav file */
    public void playMusic() {
        String path = System.getProperty("user.dir") + "/water-dripping-1.wav";
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

    /*Scales and adds image to image label and also sets text to text label*/
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

    /*Returns true if the the chosen tile belongs to another category shich means it has sub tiles*/
    /*public boolean tileHasSubTiles(String tilesCategory) {
        if (currentCategory.equalsIgnoreCase(tilesCategory)) {
            return false;
        } else {
            return true;
        }
    }*/

    /*Returns true if the chossen image represents category or is the back image and changes to the appropriate category else it returns false which means that the programm must switch pics*/
    /*public boolean changeCategory() {
        this.setRedraw(true);
        if (childTiles.get(currentCategory).get(tmpCurrentImage).getCategory().equalsIgnoreCase("none")) {
            exitSubCategory();
            return true;
        } else if (tileHasSubTiles(childTiles.get(currentCategory).get(tmpCurrentImage).getCategory())) {
            enterSubCategory();
            return true;
        } else {
            setRedraw(false);
        }
        return false;
    }*/
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
        //
        if (!currentCategory.equalsIgnoreCase("main menu")) {
            usedImages--;
        }
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
