/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.reactors;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author xrousakis
 */
public class TileXmlParser {

    private ArrayList<Tile> tiles;
    private Document configFile;
    private String xmlPath;
    private Map<String, String> hierarchy;
    private Map<String, ArrayList<Tile>> childTiles;
    private String rootNode;

    //all the info of the category stored here 
    private ArrayList<Category> categories;
    //

    private Map<String, String> categoryFolders;
    private Map<String, List<Integer>> categoryDimensions;
    private Map<String, List<String>> categoryItems;

    public TileXmlParser() {
        childTiles = new HashMap();
        hierarchy = new HashMap();
        categoryFolders = new HashMap();
        categoryDimensions = new HashMap();
        categoryItems = new HashMap();
        try {
            xmlPath = System.getProperty("user.dir") + "/categories.xml";
            configFile = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(xmlPath));
            configFile.normalize();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        parseXML();
    }

    public void printDimensions() {
        for (Map.Entry entry : categoryDimensions.entrySet()) {
            ArrayList<Integer> lista = (ArrayList<Integer>) entry.getValue();
            System.out.println(entry.getKey());
            for (Integer i : lista) {
                System.out.println("\t" + i);
            }
        }
    }

    public void printCategoryItems() {
        for (Map.Entry entry : categoryItems.entrySet()) {
            ArrayList<String> lista = (ArrayList<String>) entry.getValue();
            System.out.println(entry.getKey());
            for (String i : lista) {
                System.out.println("\t" + i);
            }
        }
    }

    public void print() {
        for (Map.Entry entry : childTiles.entrySet()) {
            ArrayList<Tile> lista = (ArrayList<Tile>) entry.getValue();
            System.out.println(entry.getKey());

            for (Tile t : lista) {
                System.out.println("\t" + t.getCategory() + "|||" + t.getImagePath());
            }
        }
    }

    /*Gets elements that belong only to this category and not any of its subcategories*/
    private ArrayList<Node> getImmediateElementsByTagName(Element element, String tagName) {
        /*TODO: na epistrefei ta stoixeia mono tou sigegrimenou category element*/
        ArrayList<Node> list = new ArrayList<>();
        Element parent;
        NodeList tags = element.getElementsByTagName(tagName);
        for (int i = 0; i < tags.getLength(); i++) {
            parent = (Element) tags.item(i).getParentNode();
            String attribute = parent.getAttribute("name");
            String category_name = element.getAttribute("name");
            if (attribute.equalsIgnoreCase(category_name)) {
                list.add(tags.item(i));
            }
        }
        return list;
    }

    //TODO: when a resource is being used create a new foder on local machone to store new iamges for this category
    public void createFileFromResource() {

    }

    /*Parses the xml and stores the information we need in the maps*/
    private void parseXML() {
        NodeList categories = configFile.getElementsByTagName("category");
        for (int i = 0; i < categories.getLength(); i++) {
            Node category = categories.item(i);
            Element cElement = (Element) category;
            if (i == 0) {
                rootNode = cElement.getAttribute("name");
            }
            Tile categoryTile;
            //NodeList alltiles = cElement.getElementsByTagName("tile");
            ArrayList<Node> tiles = getImmediateElementsByTagName(cElement, "tile");
            String folder = getImmediateElementsByTagName(cElement, "folder").get(0).getTextContent();
            String categoryText = getImmediateElementsByTagName(cElement, "text").get(0).getTextContent();
            String filename = getImmediateElementsByTagName(cElement, "filename").get(0).getTextContent();

            Integer rows = Integer.parseInt(getImmediateElementsByTagName(cElement, "rows").get(0).getTextContent());
            Integer columns = Integer.parseInt(getImmediateElementsByTagName(cElement, "columns").get(0).getTextContent());

            if (cElement.getAttribute("resource").equals("yes")) {
                ClassLoader classLoader = getClass().getClassLoader();
                File file = new File(classLoader.getResource(folder).getFile());
                folder = file.getAbsolutePath();
                categoryTile = new Tile(folder + "/" + filename, categoryText, cElement.getAttribute("name"));
                ArrayList<Node> resources = getImmediateElementsByTagName(cElement, "resource");
                insertTiles(resources, folder, cElement);
            } else {
                categoryTile = new Tile(folder + "/" + filename, categoryText, cElement.getAttribute("name"));

            }

            insertCategoryItems(tiles, cElement);
            /*put child tiles in map*/
            insertTiles(tiles, folder, cElement);
            setCategoryDimensions(rows, columns, cElement);
            /*put category dimension in categoryDimensions maps*/
            categoryFolders.put(cElement.getAttribute("name"), folder);

            Node parent = (Node) cElement.getParentNode();
            if (!parent.getNodeName().equalsIgnoreCase("#Document")) {
                Element el = (Element) parent;
                hierarchy.put(cElement.getAttribute("name"), el.getAttribute("name"));
                childTiles.get(el.getAttribute("name")).add(categoryTile);
            }
        }
    }

    private void parseXmlV2() {
        NodeList categories = configFile.getElementsByTagName("category");
        for (int i = 0; i < categories.getLength(); i++) {
            Node category = categories.item(i);
            Element cElement = (Element) category;
            if (i == 0) {
                rootNode = cElement.getAttribute("name");
            }
        }
    }

    private void setCategoryDimensions(Integer rows, Integer columns, Element cElement) {
        ArrayList<Integer> dimensions = new ArrayList<>();
        dimensions.add(rows);
        dimensions.add(columns);
        categoryDimensions.put(cElement.getAttribute("name"), dimensions);
    }

    private void insertTiles(ArrayList<Node> tiles1, String folder, Element cElement) throws DOMException {
        ArrayList<Tile> lista = new ArrayList<>();
        for (int j = 0; j < tiles1.size(); j++) {
            Element tile = (Element) tiles1.get(j);
            String fullname = folder + "/" + tile.getElementsByTagName("filename").item(0).getTextContent();
            String txt = tile.getElementsByTagName("text").item(0).getTextContent();
            lista.add(new Tile(fullname, txt, cElement.getAttribute("name")));
        }
        if (childTiles.get(cElement.getAttribute("name")) == null) {
            childTiles.put(cElement.getAttribute("name"), lista);
        } else {
            childTiles.get(cElement.getAttribute("name")).addAll(lista);
        }

    }

    /*Adds the items of the category fodler to the map*/
    private void insertCategoryItems(ArrayList<Node> tiles1, Element cElement) {
        ArrayList<String> lista = new ArrayList();
        for (int j = 0; j < tiles1.size(); j++) {
            Element tile = (Element) tiles1.get(j);
            String item = tile.getElementsByTagName("filename").item(0).getTextContent();
            lista.add(item);
        }
        if (categoryItems.get(cElement.getAttribute("name")) == null) {
            categoryItems.put(cElement.getAttribute("name"), lista);
        } else {
            categoryItems.get(cElement.getAttribute("name")).addAll(lista);
        }
    }

    public void printFolders() {
        for (Map.Entry entry : categoryFolders.entrySet()) {
            System.out.println(entry.getKey() + "|||" + entry.getValue());
        }
    }

    /*Returns root element of the xml*/
    public String getRoot() {
        return rootNode;
    }

    /*Returns the map that contains for keys the caregory names and value array list of tiles of this category*/
    public Map getTiles() {
        return childTiles;
    }

    public Map getCategoryFolders() {
        return categoryFolders;
    }

    /*Returns the map that contains for keys category names and for value the name of the exact higher category*/
    public Map getHierarchy() {
        return hierarchy;
    }

    /*Returns the dimensions of each category*/
    public Map getCategoryDimensions() {
        return categoryDimensions;
    }
    
    public Map getCategoryItems(){
        return categoryItems;
    }

    /*public Map<String, List<String>> getCategoryItems() {
        return categoryItems;
    }*/

    public static void main(String[] args) {
        TileXmlParser var = new TileXmlParser();
        var.print();
    }
}
