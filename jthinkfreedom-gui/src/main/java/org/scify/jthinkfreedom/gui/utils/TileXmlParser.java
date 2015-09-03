/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.gui.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
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
    //private Map<String, ArrayList<Tile>> categories;
    private Map<String, String> hierarchy;
    private Map<String, ArrayList<Tile>> childTiles;
    private String rootNode;
    private Map<String, String> categoryFolders;
    // private Map<String,>
    //private Map<String,Integer[2]> categoryDimensions;
    private Map<String, List<Integer>> categoryDimensions;

    public TileXmlParser() {
        childTiles = new HashMap();
        hierarchy = new HashMap();
        categoryFolders = new HashMap();
        categoryDimensions =new HashMap();
        try {
            //xmlPath = System.getProperty("user.home") + "/categories.xml";
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

    public void print() {
        //System.out.println("");
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

    /*Parses the xml and stores the information we need in the maps*/
    private void parseXML() {
        NodeList categories = configFile.getElementsByTagName("category");
        for (int i = 0; i < categories.getLength(); i++) {
            Node category = categories.item(i);
            Element cElement = (Element) category;
            if (i == 0) {
                rootNode = cElement.getAttribute("name");
            }

            NodeList alltiles = cElement.getElementsByTagName("tile");
            ArrayList<Node> tiles = getImmediateElementsByTagName(cElement, "tile");;

            String folder = getImmediateElementsByTagName(cElement, "folder").get(0).getTextContent();

            String categoryText = getImmediateElementsByTagName(cElement, "text").get(0).getTextContent();

            String filename = getImmediateElementsByTagName(cElement, "filename").get(0).getTextContent();

            Tile categoryTile = new Tile(folder + "/" + filename, categoryText, cElement.getAttribute("name"));
            
            Integer rows =Integer.parseInt( getImmediateElementsByTagName(cElement,"rows").get(0).getTextContent() );
            
            Integer columns =Integer.parseInt( getImmediateElementsByTagName(cElement,"columns").get(0).getTextContent() );
            
            ArrayList<Integer> dimensions =new ArrayList<>();
            dimensions.add(rows);
            dimensions.add(columns);
            
            //System.out.println("Rows:"+rows);
            //System.out.println("Columns:"+columns);
            //ctegoryDimensions.put(cElement.getAttribute("name"),dimensions);
            
            categoryDimensions.put(cElement.getAttribute("name"), dimensions);
            
            categoryFolders.put(cElement.getAttribute("name"), folder);
            ArrayList<Tile> lista = new ArrayList<>();

            for (int j = 0; j < tiles.size(); j++) {
                Element tile = (Element) tiles.get(j);
                String fullname = folder + "/" + tile.getElementsByTagName("filename").item(0).getTextContent();
                String txt = tile.getElementsByTagName("text").item(0).getTextContent();

                lista.add(new Tile(fullname, txt, cElement.getAttribute("name")));
            }

            childTiles.put(cElement.getAttribute("name"), lista);
            Node parent = (Node) cElement.getParentNode();
            if (!parent.getNodeName().equalsIgnoreCase("#Document")) {
                Element el = (Element) parent;
                hierarchy.put(cElement.getAttribute("name"), el.getAttribute("name"));
                childTiles.get(el.getAttribute("name")).add(categoryTile);
            }
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
    
    public Map getCategoryDimensions(){
        return categoryDimensions;
    }

    public static void main(String[] args) {
        TileXmlParser var = new TileXmlParser();
        var.parseXML();
        var.printDimensions();
        //System.out.println(var.getRoot());

    }
}
