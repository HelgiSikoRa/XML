package com.epam.lab.Task4.SaxParser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.epam.lab.Task3.OldCards;
import org.xml.sax.SAXException;

import static com.epam.lab.Task4.DomParser.PATH;
import static com.epam.lab.Task4.DomParser.USER_DIR;
import static java.lang.System.getProperty;

public class SaxParser {

    public static void main(String[] args) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            Handler handler = new Handler();
            saxParser.parse(new File(getProperty(USER_DIR) + PATH), handler);
            List<OldCards> empList = handler.getCardList();
            for (OldCards emp : empList)
                System.out.println(emp);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}