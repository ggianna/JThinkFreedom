package org.scify.jthinkfreedom.talkandplay.models;

public class Image {

    private String fileName;
    private String imagePath;
    private String txt;
    private String category;
    private String soundFile;
    private String imageIcon;

    public Image() {

    }

    public Image(String imagePath, String txt, String category, String fileName) {
        this.imagePath = imagePath;
        this.txt = txt;
        this.category = category;
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
    
    

}
