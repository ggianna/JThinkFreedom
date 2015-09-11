/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.reactors;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author rou
 */
public class Parser {

    private ArrayList<Category> categories;
    private String xmlPath;
    private Document configFile;

    private String rootNode;

    public Parser() {
        categories = new ArrayList();
        try {
            xmlPath = System.getProperty("user.dir") + "/categories.xml";
            configFile = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(xmlPath));
            configFile.normalize();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        parse();
    }

    public void print() {
        ArrayList<Tile> foo;
        for (Category c : categories) {
            System.out.println(c.getName());
            System.out.println(c.deptfOfCategory());
        }

    }

    private Category storeInfo(Element element) {
        Category category = new Category();
        category.setName(element.getAttribute("name"));
        if (element.getAttribute("name").equals("main menu")) {
            category.setParentCategory(new Category());
        }
        Element el;
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i).getNodeType() == Node.ELEMENT_NODE) {
                el = (Element) children.item(i);
                switch (children.item(i).getNodeName()) {
                    case "rows":
                        storeRows(el, category);
                        break;
                    case "columns":
                        storeColumns(el, category);
                        break;
                    case "text":
                        storeText(el, category);
                        break;
                    case "folder":
                        storeFolder(el, category);
                        break;
                    case "filename":
                        storeFilename(el, category);
                        break;
                    case "tile":
                        storeTile(children.item(i), category, "tile", category.getFolder());
                        break;
                    case "category":
                        Category newCategory = storeInfo(el);
                        newCategory.setParentCategory(category);
                        category.storeSubCategory(newCategory);
                        break;
                    case "resource":
                        setPathForResources(category.getFolder(), category);
                        String newFolder = createFolder(category.getFolder());
                        category.setFolder(newFolder);
                        storeTile(children.item(i), category, "resource", category.getResourcePath());
                        break;
                }
            }
        }

        categories.add(category);
        return category;
    }

    public boolean categoryIsRegistered(String name) {
        for (Category c : categories) {
            if (c.getName().equals(name)) {
            }
        }
        return false;
    }

    public void setPathForResources(String resourceFolder, Category category) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceFolder).getFile());
        category.setResourcePath(file.getAbsolutePath());
    }

    private void storeRows(Element el, Category category) {
        category.setRows(Integer.parseInt(el.getTextContent()));
    }

    private void storeColumns(Element el, Category category) {

        category.setColumns(Integer.parseInt(el.getTextContent()));
    }

    private void storeText(Element el, Category category) {

        category.setText(el.getTextContent());
    }

    private void storeFolder(Element el, Category category) {
        category.setFolder(el.getTextContent());
    }

    private void storeFilename(Element el, Category category) {
        category.setFilename(el.getTextContent());
    }

    private void storeTile(Node node, Category category, String type, String categoryFolder) {
        String imagePath = null;
        String text = null;
        String fileName =null;
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeName().equals("filename")) {
                fileName = node.getChildNodes().item(i).getTextContent();
                imagePath = categoryFolder + "/" + fileName;
            } else if (node.getChildNodes().item(i).getNodeName().equals("text")) {
                text = node.getChildNodes().item(i).getTextContent();
            }
        }
        Tile tile = new Tile(imagePath, text, "",fileName);
        if (type.equals("tile")) {
            category.storeTile(tile);
        } else {
            category.storeToResources(tile);
        }
    }

    private void storeResource(Node node, Category category,String folderName) {
        String imagePath = null;
        String text = null;
        String fileName = null;
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeName().equals("filename")) {
                fileName = node.getChildNodes().item(i).getTextContent();
                imagePath = folderName +fileName;
            } else if (node.getChildNodes().item(i).getNodeName().equals("text")) {
                text = node.getChildNodes().item(i).getTextContent();
            }
        }
        Tile tile = new Tile(imagePath, text, "",fileName);
        category.storeTile(tile);
    }

    /*if the category is a resource we have to create a new folder to store user introiduced images and text*/
    private String createFolder(String folderName) {
        String path = System.getProperty("user.dir") + "/" + folderName;
        File f = new File(path);
        if (f.exists()) {
            return path;
        } else {
            f.mkdirs();
        }
        return path;
    }

    public void parse() {
        Element element;
        NodeList categories = configFile.getElementsByTagName("category");
        Element main = (Element) categories.item(0);
        storeInfo(main);
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public static void main(String[] args) {
        Parser parser = new Parser();
        //parser.print();
        ArrayList<String> lista = parser.getCategoryNames();
        System.out.println(lista.size());
        /*for(String s:lista){
            System.out.println(s+"fail");
        }*/
        
    }

    public ArrayList<String> getCategoryNames() {
        ArrayList<String> categoryNames = new ArrayList();
        for (Category c : categories) {
            if (!c.getName().equals("main menu")) {
                categoryNames.add(attachTabs(c.deptfOfCategory(), c.getName()));
            }
        }
        return categoryNames;
    }

    public Document getConfigFile() {
        return configFile;
    }

    private String attachTabs(int x, String text) {
        String newText = "";
        while (x > 0) {
            newText = newText + " ";
            x--;
        }
        text = newText + text;
        //System.out.println(text);
        return text;
    }

    public String getXmlPath(){
        return xmlPath;
    }

}
