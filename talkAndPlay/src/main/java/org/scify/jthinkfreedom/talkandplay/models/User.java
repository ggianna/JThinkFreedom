package org.scify.jthinkfreedom.talkandplay.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peustr
 */
public class User {

    private String name;
    private String image;
    private List<Configuration> configurations;
    private List<Category> categories;

    public User() {
        configurations = new ArrayList<>();
        categories = new ArrayList<>();
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String image) {
        this.name = name;
        this.image = image;
        configurations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<Configuration> configurations) {
        this.configurations = configurations;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", image=" + image + ", configurations=" + configurations + '}';
    }

}
