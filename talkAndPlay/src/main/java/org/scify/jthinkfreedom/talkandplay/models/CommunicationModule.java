package org.scify.jthinkfreedom.talkandplay.models;

import java.util.List;

public class CommunicationModule extends Module {

    private List<Category> categories;

    public CommunicationModule() {
    }
    
    

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        
    }

}
