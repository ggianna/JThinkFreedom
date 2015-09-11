/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.reactors;

/**
 *
 * @author xrousakis
 */
public class Tile {
    private String fileName;
    private String imagePath;
    private String txt;
    private String category;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public Tile(String imagePath, String txt, String category) {
        this.imagePath = imagePath;
        this.txt = txt;
        this.category = category;
    }
    
    public Tile(String imagePath, String txt, String category,String fileName) {
        this.imagePath = imagePath;
        this.txt = txt;
        this.category = category;
        this.fileName = fileName;
    }

    public Tile(String imagePath, String txt) {
        this.imagePath = imagePath;
        this.txt = txt;
    }

    public Tile() {

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

}
