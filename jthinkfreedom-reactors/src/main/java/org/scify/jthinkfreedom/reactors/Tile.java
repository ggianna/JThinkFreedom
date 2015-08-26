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

    private String imagePath;
    private String txt;
    

    public Tile(String imagePath, String txt) {
        this.imagePath = imagePath;
        this.txt = txt;
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
