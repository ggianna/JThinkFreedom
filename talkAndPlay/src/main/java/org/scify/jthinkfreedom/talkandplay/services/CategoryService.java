package org.scify.jthinkfreedom.talkandplay.services;

import java.util.ArrayList;
import java.util.List;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.scify.jthinkfreedom.talkandplay.models.Category;
import org.scify.jthinkfreedom.talkandplay.models.User;
import org.scify.jthinkfreedom.talkandplay.utils.ConfigurationHandler;

public class CategoryService {

    private ConfigurationHandler configurationHandler;
    private Document configurationFile;
    String projectPath;

    public CategoryService() {
        configurationHandler = new ConfigurationHandler();
        configurationFile = configurationHandler.getConfigurationFile();
        projectPath = configurationHandler.getProjectPath();
    }

    public void save(Category category, User user) {
        configurationHandler.getProfile(user.getName()).getCategories();
        Element categoryEl;
        List profiles = configurationFile.getRootElement().getChildren();

        //find the user from the users list
        for (int i = 0; i < profiles.size(); i++) {

            Element profile = (Element) profiles.get(i);

            if (user.getName().equals(profile.getChildText("name"))) {

                ((Element) profile.getChild("communication").getChild("categories")).removeChildren("category");
                Element categoryChild = new Element("category");
                categoryChild.setAttribute(new Attribute("name", category.getName()));
                categoryChild.addContent(new Element("rows").setText(String.valueOf(category.getRows())));
                categoryChild.addContent(new Element("columns").setText(String.valueOf(category.getColumns())));
                categoryChild.addContent(new Element("image").setText(category.getImage()));

                ((Element) profile.getChild("communication").getChild("categories")).addContent(categoryChild);

                /* 
                 for (int j = 0; j < profile.getChildren("categories").size(); j++) {

                 categoryEl = (Element) profile.getChildren("categories").get(i);
                    
                 if (categoryEl.getAttributeValue("name").equals(category.getParentCategory())) {

                 } else {
                 for (int k = 0; k < categoryEl.getChildren("categories").size(); k++) {
                 categoryEl = (Element) categoryEl.getChildren("categories").get(k);
                 if (categoryEl.getAttributeValue("name").equals(category.getParentCategory())) {
                 break;
                 }
                 }
                 }
                 }*/
            }
        }

        configurationHandler.writeToXmlFile();
    }

    public void update(Category category, User user) {

    }

    public void delete(Category category, User user) {

    }

    private Element findParent(Element categoryNode, String name) {
        if (name.equals(categoryNode.getAttribute("name"))) {
            return categoryNode;
        } else {
            //get the user categories
            for (int i = 0; i < categoryNode.getChildren().size(); i++) {

                Element categoryEl = (Element) categoryNode.getChildren().get(i);
                findParent(categoryEl, name);
            }
        }
        return categoryNode;
    }
        /**
         * Returns the distance from the main menu and for the main menu the
         * distance
         */
    public int depthOfCategory(Category category) {
        int depth = 0;
        Category parent = category.getParentCategory();
        while (!parent.getName().equals("main menu") && !category.getName().equals("main menu")) {
            depth++;
            parent = parent.getParentCategory();
        }
        return depth;

    }

    /**
     * this function checks if another category exists with the name
     * categoryToAddName for the level of the category given
     */
    public boolean categoryExists(Category category, String categoryToAddName, boolean foo) {

        if (category.getName().equals(categoryToAddName) && foo == false) {
            return true;
        }

        for (Category c : category.getSubCategories()) {
            //if there is a subcategory that matches the given name then return
            if (c.getName().equals(categoryToAddName)) {
                return true;
            }
        }

        return false;
    }
    /*
     public boolean imageExists(String fileName) {
     for (Image image : this.getTiles()) {
     if (image.getFileName().equals(fileName)) {
     return true;
     }
     }
     if (this.filename.equals(fileName)) {
     return true;
     }
     return false;
     }*/

}
