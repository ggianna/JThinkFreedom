package org.scify.jthinkfreedom.talkandplay.models;

import java.util.ArrayList;

public class Category {

    private int rows, columns;
    private String name;
    private String image;
    private boolean editable;
    private Category parentCategory;
    private ArrayList<Category> subCategories;
    private ArrayList<Image> images;

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, ArrayList<Image> images, Category parentCategory, ArrayList<Category> subCategories) {
        this.images = new ArrayList<>();
        this.subCategories = new ArrayList<>();
        this.name = name;
        this.images = images;
        this.parentCategory = parentCategory;
        this.subCategories = subCategories;
    }

    public Category(String name, int rows, int columns, String image) {
        this.images = new ArrayList<>();
        this.subCategories = new ArrayList<>();
        this.rows = rows;
        this.columns = columns;
        this.name = name;
        this.image = image;
    }

    public Category() {
//        this.parentCategory = new Category();
        this.images = new ArrayList<>();
        this.subCategories = new ArrayList<>();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public ArrayList<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(ArrayList<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public String toString() {
        return "Category{" + "rows=" + rows + ", columns=" + columns + ", name=" + name + ", images=" + images + ", parentCategory=" + parentCategory + ", subCategories=" + subCategories + '}';
    }

}
