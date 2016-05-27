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
    String projectPath;

    public CategoryService() {
        configurationHandler = new ConfigurationHandler();
    }

    public List<Category> getCategories(String userName) throws Exception {
        List<Category> categories = new ArrayList<>();

        User user = configurationHandler.getUser(userName);

        if (user != null) {
            getCategories(user.getCategories(), categories);
        }
        return categories;
    }

    private void getCategories(List<Category> userCategories, List<Category> categories) {

        if (userCategories == null) {
            return;
        } else {
            for (Category category : userCategories) {
                categories.add(category);
                getCategories(category.getSubCategories(), categories);
            }
        }
    }

    /**
     * Save a new category
     *
     * @param category
     * @param user
     */
    public void save(Category category, User user) throws Exception {

        Element profile = configurationHandler.getProfileElement(user.getName());

        if (profile != null) {

            Element categoryChild = new Element("category");
            categoryChild.setAttribute(new Attribute("name", category.getName()));
            categoryChild.addContent(new Element("rows").setText(String.valueOf(category.getRows())));
            categoryChild.addContent(new Element("columns").setText(String.valueOf(category.getColumns())));
            categoryChild.addContent(new Element("image").setText(category.getImage()));

            attachToParent(profile.getChild("communication").getChild("categories"), category.getParentCategory().getName(), categoryChild);

            configurationHandler.writeToXmlFile();
        }
    }

    /**
     * Update a category
     *
     * @param category
     * @param user
     */
    public List<Category> update(Category category, User user, String oldName) throws Exception {

        Element profile = configurationHandler.getProfileElement(user.getName());

        if (profile != null) {
            Element categoryChild = new Element("category");
            categoryChild.setAttribute(new Attribute("name", category.getName()));
            categoryChild.addContent(new Element("rows").setText(String.valueOf(category.getRows())));
            categoryChild.addContent(new Element("columns").setText(String.valueOf(category.getColumns())));
            categoryChild.addContent(new Element("image").setText(category.getImage()));

            updateToParent(profile.getChild("communication").getChild("categories"), oldName, category);

            configurationHandler.writeToXmlFile();
            return configurationHandler.getUser(user.getName()).getCategories();
        } else {
            return null;
        }
    }

    /**
     * Delete a category
     *
     * @param category
     * @param user
     */
    public void delete(String categoryName, User user) throws Exception {
        Element profile = configurationHandler.getProfileElement(user.getName());

        if (profile != null) {

            deleteFromParent(profile.getChild("communication").getChild("categories"), categoryName);

            configurationHandler.writeToXmlFile();
        }
    }

    /**
     * Find the category parent and add the categoryChild
     *
     * @param categoryNode
     * @param name
     * @return
     */
    private Element attachToParent(Element categoryNode, String name, Element categoryChild) {

        if (name.equals(categoryNode.getAttributeValue("name"))) {

            if (categoryNode.getChild("categories") == null) {
                Element categories = new Element("categories");
                categories.addContent(categoryChild);
                categoryNode.addContent(categories);
            } else {
                categoryNode.getChild("categories").addContent(categoryChild);
            }

            return categoryNode;

        } else {

            for (int i = 0; i < categoryNode.getChildren().size(); i++) {
                Element categoryEl = (Element) categoryNode.getChildren().get(i);
                attachToParent(categoryEl, name, categoryChild);
            }
        }
        return categoryNode;
    }

    /**
     * For a given category, find the category and update it
     *
     * @param categoryNode
     * @param name
     * @param categoryChild
     * @return
     */
    private void updateToParent(Element categoryNode, String oldName, Category categoryChild) {

        if (oldName.equals(categoryNode.getAttributeValue("name"))) {

            categoryNode.getAttribute("name").setValue(categoryChild.getName());
            categoryNode.getChild("rows").setText(String.valueOf(categoryChild.getRows()));
            categoryNode.getChild("columns").setText(String.valueOf(categoryChild.getColumns()));
            categoryNode.getChild("image").setText(categoryChild.getImage());

        } else {
            for (int i = 0; i < categoryNode.getChildren().size(); i++) {
                Element categoryEl = (Element) categoryNode.getChildren().get(i);
                updateToParent(categoryEl, oldName, categoryChild);
            }
        }
    }

    /**
     * Delete a given category
     *
     * @param categoryNode
     * @param categoryName
     */
    private void deleteFromParent(Element categoryNode, String categoryName) {

        if (categoryName.equals(categoryNode.getAttributeValue("name"))) {
            categoryNode.detach();
        } else {
            for (int i = 0; i < categoryNode.getChildren().size(); i++) {
                Element categoryEl = (Element) categoryNode.getChildren().get(i);
                deleteFromParent(categoryEl, categoryName);
            }
        }
    }

}
