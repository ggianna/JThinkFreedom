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

    private int rows, columns;
    private String name;
    private String folder;
    private String filename;
    private String text;
    private String resourcePath;
    private ArrayList<Tile> tiles;
    private ArrayList<Tile> resources;
    private Category parentCategory;
    //private String 
    private ArrayList<Category> subCategories;

    public Category(String name, String folder, String filename, String text, ArrayList<Tile> tiles, Category parentCategory, ArrayList<Category> subCategories) {
        this.name = name;
        this.folder = folder;
        this.filename = filename;
        this.text = text;
        this.tiles = tiles;
        this.parentCategory = parentCategory;
        this.subCategories = subCategories;
    }

    public ArrayList<Tile> getResources() {
        return resources;
    }

    public void storeToResources(Tile tile) {
        resources.add(tile);
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public Category() {
        tiles = new ArrayList<>();
        subCategories = new ArrayList<>();
        resources = new ArrayList<>();
    }

    public void storeTile(Tile tile) {
        tiles.add(tile);
    }

    public void storeSubCategory(Category category) {
        subCategories.add(category);
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public ArrayList<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(ArrayList<Category> subCategories) {
        this.subCategories = subCategories;
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

    public ArrayList<Tile> getSubCategoriesTiles() {
        ArrayList<Tile> tiles = new ArrayList();
        Tile tile;
        for (Category c : subCategories) {
            tile = new Tile(c.getFolder() + "/" + c.getFilename(), c.getText(), "",c.getFilename());
            tiles.add(tile);
        }

        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    
    /*Returns the distance from the main*/ 
    public int deptfOfCategory() {
        int depth = 0;
        Category parent = this.getParentCategory();
        while (parent.getName() != null) {
            depth++;
            parent = parent.getParentCategory();
        }
        return depth;
    }

    /*this function checks if another category exists with the name categoryToAddName for the level of the category given*/
    public boolean categoryExists(Category category,String categoryToAddName) {
        for (Category c : category.getSubCategories()) {
            /*if there is a subcategory that matches the given name then return*/
            if(c.getName().equals(categoryToAddName))
                return true;
        }
        return false;
    }

}
