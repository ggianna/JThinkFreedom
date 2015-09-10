/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.reactors;

import java.util.ArrayList;

/**
 *
 * @author xrousakis
 */
public class Category {
    private String name;
    private String folder;
    private String filename;
    private String text;
    private ArrayList<Tile> tiles;
    private String parentCategory;

    public Category(String name, String folder, String filename, String text, ArrayList<Tile> tiles, String parentCategory) {
        this.name = name;
        this.folder = folder;
        this.filename = filename;
        this.text = text;
        this.tiles = tiles;
        this.parentCategory = parentCategory;
    }

    public Category() {
        this.tiles = new ArrayList<Tile>();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public String getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }
    
}
