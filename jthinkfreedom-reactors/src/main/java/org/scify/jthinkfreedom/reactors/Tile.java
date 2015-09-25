/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.reactors;

import javax.swing.ImageIcon;

/**
 *
 * @author xrousakis
 */
public class Tile {

    private String fileName;
    private String imagePath;
    private String txt;
    private String category;
    private String soundFile;
    private String imageIcon;

    public Tile() {

    }

    public String getSoundFile() {
        return soundFile;
    }

    public void setSoundFile(String soundFile) {
        this.soundFile = soundFile;
    }

    public String getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }

    public Tile(String imagePath, String txt, String category) {
        this.imagePath = imagePath;
        this.txt = txt;
        this.category = category;
    }

    public Tile(String imagePath, String txt, String category, String fileName, String soundFile) {
        this.imagePath = imagePath;
        this.txt = txt;
        this.category = category;
        this.fileName = fileName;
        this.soundFile = soundFile;
    }

    public Tile(String imagePath, String txt, String category, String fileName, String soundFile, String imageIcon) {
        this.imagePath = imagePath;
        this.txt = txt;
        this.category = category;
        this.fileName = fileName;
        this.soundFile = soundFile;
        this.imageIcon = imageIcon;
    }

    public Tile(String imagePath, String txt, String category, String fileName) {
        this.imagePath = imagePath;
        this.txt = txt;
        this.category = category;
        this.fileName = fileName;
    }

    public Tile(String imagePath, String txt) {
        this.imagePath = imagePath;
        this.txt = txt;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getMusicFile() {
        return soundFile;
    }

    public void setMusicFile(String musicFile) {
        this.soundFile = musicFile;
    }

}
