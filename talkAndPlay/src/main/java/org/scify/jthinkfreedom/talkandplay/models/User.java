package org.scify.jthinkfreedom.talkandplay.models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;

/**
 *
 * @author peustr
 */
public class User {

    private String name;
    private Icon photo;
    private List<Configuration> configurations;

    public User() {
        configurations = new ArrayList<>();
    }

    public User(String name, Icon photo) {
        this.name = name;
        this.photo = photo;
        configurations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon getPhoto() {
        return photo;
    }

    public void setPhoto(Icon photo) {
        this.photo = photo;
    }

    public List<Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<Configuration> configurations) {
        this.configurations = configurations;
    }
 

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", photo=" + photo + ", configurations=" + configurations + '}';
    }
    
    

}
