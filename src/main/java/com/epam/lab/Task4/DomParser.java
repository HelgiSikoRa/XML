package com.epam.lab.Task4;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

public class DomParser {
    private final static Logger LOG = LogManager.getLogger(DomParser.class);
    public static final String USER_DIR = "user.dir";
    public static final String PATH = "\\src\\main\\resources\\oldpostcard.xml";
    public static final String PATH_XSD = "\\src\\main\\resources\\oldcard.xsd";
    public static final String PATH_XSL = "\\src\\main\\resources\\oldcard.xsl";
    public static final String PATH_HTML = "\\src\\main\\resources\\oldcard.html";
    public static final String PATH_CHANGED_XML = "\\src\\main\\resources\\changedOldCard.xml";

    public static void main(String[] args) {
        try {
            File srcXmlFile = new File(System.getProperty(USER_DIR) + PATH);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(srcXmlFile);

            doc.getDocumentElement().normalize();
            LOG.info("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("postcard");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                LOG.info("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    LOG.info("Theme : " + eElement.getAttribute("theme"));
                    LOG.info("Status : " + eElement.getAttribute("status"));
                    LOG.info("Type : " + eElement.getElementsByTagName("type").item(0).getTextContent());
                    LOG.info("Country : " + eElement.getElementsByTagName("country").item(0).getTextContent());
                    LOG.info("Year : " + eElement.getElementsByTagName("year").item(0).getTextContent());
                    LOG.info("Author : " + eElement.getElementsByTagName("author").item(0).getTextContent());
                    LOG.info("Valuable : " + eElement.getElementsByTagName("valuable").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}