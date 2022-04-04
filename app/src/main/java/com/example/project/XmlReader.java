package com.example.project;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlReader {

    private String name;

    public XmlReader() {}

    public static ArrayList<Movie> readXml(ArrayList<Movie> movieList) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://www.finnkino.fi/xml/Events/";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getDocumentElement().getElementsByTagName("Event");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);

                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Movie name: " + element.getElementsByTagName("Title").item(0).getTextContent());
                    movieList.add(new Movie(element.getElementsByTagName("Title").item(0).getTextContent()));
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return movieList;
    }
}
