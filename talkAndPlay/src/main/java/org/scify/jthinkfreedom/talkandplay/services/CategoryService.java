package org.scify.jthinkfreedom.talkandplay.services;

import OS.Os;
import java.util.ArrayList;
import org.scify.jthinkfreedom.talkandplay.models.Category;
import org.scify.jthinkfreedom.talkandplay.models.Image;

public class CategoryService {

    private static final Os os = new Os();
/*
    public ArrayList<Image> getSubCategoriesTiles(Category category) {
        ArrayList<Image> images = new ArrayList();
        Image image;
        for (Category c : category.getSubCategories()) {
            if (c.getResourcePath() != null) {
                //  image = new Image(null, c.getText(), "", c.getFilename(), "", c.getResourcePath() + "/" + c.getFilename());
                image = new Image();
                image.setImagePath(null);
                image.setTxt(c.getText());
                image.setCategory("");
                image.setFileName(c.getFilename());
                image.setSoundFile("");
                image.setImagePath(c.getResourcePath() + "/" + c.getFilename());
            } else {
                image = new Image(c.getFolder() + os.returnChatracter() + c.getFilename(), c.getText(), "", c.getFilename());
            }

            images.add(image);
        }

        return images;
    }
*/
    /**
     *  Returns the distance from the main menu and for the main menu the distance
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
    /*this function checks if another category exists with the name categoryToAddName for the level of the category given*/
    public boolean categoryExists(Category category, String categoryToAddName, boolean foo) {
        for (Category c : category.getSubCategories()) {
            //if there is a subcategory that matches the given name then return
            if (c.getName().equals(categoryToAddName)) {
                return true;
            }
        }

        if (category.getName().equals(categoryToAddName) && foo == false) {
            return true;
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
