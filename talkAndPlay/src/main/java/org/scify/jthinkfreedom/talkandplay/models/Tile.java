package org.scify.jthinkfreedom.talkandplay.models;

public class Tile {

    private String name;
    private String image;
    private String sound;
    private Category category;

    public Tile() {

    }

    public Tile(String name, String image, String sound) {
        this.image = image;
        this.name = name;
        this.sound = sound;
    }

    public Tile(String name, String image, String sound, Category category) {
        this.image = image;
        this.name = name;
        this.sound = sound;
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
