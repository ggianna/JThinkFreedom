package org.scify.jthinkfreedom.talkandplay.models;

public class Image {

    private String path;
    private String name;
    private String soundFile;
    private Category category;

    public Image() {

    }

    public Image(String path, String name, String soundFile, Category category) {
        this.path = path;
        this.name = name;
        this.soundFile = soundFile;
        this.category = category;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoundFile() {
        return soundFile;
    }

    public void setSoundFile(String soundFile) {
        this.soundFile = soundFile;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
