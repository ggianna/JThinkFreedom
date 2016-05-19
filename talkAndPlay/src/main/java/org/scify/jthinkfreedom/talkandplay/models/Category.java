package org.scify.jthinkfreedom.talkandplay.models;

import java.util.ArrayList;

public class Category {

    private int rows, columns;
    private String name;
    private String imagePath;
    private String imageText;
    private ArrayList<Image> images;
    private Category parentCategory;
    private ArrayList<Category> subCategories;

    public Category(String name, ArrayList<Image> images, Category parentCategory, ArrayList<Category> subCategories) {
        this.name = name;
        this.images = images;
        this.parentCategory = parentCategory;
        this.subCategories = subCategories;
    }

    public Category(int rows, int columns, String name) {
        this.rows = rows;
        this.columns = columns;
        this.name = name;
    }

    public Category() {
        images = new ArrayList<>();
        subCategories = new ArrayList<>();
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageText() {
        return imageText;
    }

    public void setImageText(String imageText) {
        this.imageText = imageText;
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

    @Override
    public String toString() {
        return "Category{" + "rows=" + rows + ", columns=" + columns + ", name=" + name + ", imagePath=" + imagePath + ", imageText=" + imageText + ", images=" + images + ", parentCategory=" + parentCategory + ", subCategories=" + subCategories + '}';
    }

}
