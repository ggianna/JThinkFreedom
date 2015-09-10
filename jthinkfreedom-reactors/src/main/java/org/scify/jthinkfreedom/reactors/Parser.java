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
        //System.out.println(categories.size());
        for (Category c : categories) {
            System.out.println(c.getName());
           // System.out.println("parent:"+c.getParentCategory().getName());
            //System.out.println(c.deptfOfCategory());
            foo = c.getResources();
            for(Tile t: foo){
                System.out.println("\t"+t.getImagePath());
            }
        }
        
    }

    private Category storeInfo(Element element) {
        Category category = new Category();
        category.setName(element.getAttribute("name"));
        //storeParent(element, category);
        if(element.getAttribute("name").equals("main menu"))
             category.setParentCategory(new Category());
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
                        storeTile(children.item(i), category,"tile");
                        break;
                    case "category":
                        Category newCategory = storeInfo(el);
                        newCategory.setParentCategory(category);
                        category.storeSubCategory(newCategory);
                        break;
                    case "resource":
                        setPathForResources(category.getFolder(),category);
                        String newFolder = createFolder(category.getFolder());
                        category.setFolder(newFolder);
                        storeTile(children.item(i), category,"resource");
                        break;
                }
            }
        }
        //here to store category into the arraylist
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

    /*private void storeParent(Element el, Category category) {
        Node parentNode = el.getParentNode();
        if (!parentNode.getNodeName().equalsIgnoreCase("#Document")) {
            Element parentElement = (Element) parentNode;
            category.setParentCategory(parentElement.getAttribute("name"));
        }
    }*/

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

    private void storeTile(Node node, Category category,String type) {
        String filename = null;
        String text = null;
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeName().equals("filename")) {
                filename = node.getChildNodes().item(i).getTextContent();
            } else if (node.getChildNodes().item(i).getNodeName().equals("text")) {
                text = node.getChildNodes().item(i).getTextContent();
            }
        }
        Tile tile = new Tile(filename, text);
        if(type.equals("tile"))
          category.storeTile(tile);
        else
            category.storeToResources(tile);
    }

    private void storeResource(Node node, Category category) {
        String filename = null;
        String text = null;
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeName().equals("filename")) {
                filename = node.getChildNodes().item(i).getTextContent();
            } else if (node.getChildNodes().item(i).getNodeName().equals("text")) {
                text = node.getChildNodes().item(i).getTextContent();
            }
        }
        Tile tile = new Tile(filename, text);
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

    private void parse() {
        Element element;
        /*all categories*/
        NodeList categories = configFile.getElementsByTagName("category");
        Element main = (Element) categories.item(0);
        /*for (int i = 0; i < categories.getLength(); i++) {
         element = (Element) categories.item(i);
         storeInfo(element);
         }*/
        storeInfo(main);
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public static void main(String[] args) {
        Parser parser = new Parser();
        //parser.parse();
        parser.print();
    }
    
   
}
