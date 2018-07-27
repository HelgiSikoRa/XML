package com.epam.lab.Task4;

import com.epam.lab.Task3.Enum.Status;
import com.epam.lab.Task3.Enum.Valuable;
import com.epam.lab.Task3.OldCards;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import static com.epam.lab.Task4.DomParser.PATH;
import static com.epam.lab.Task4.DomParser.USER_DIR;

import static java.lang.System.getProperty;

public class StaxParser {
    public static void main(String[] args) {
        String fileName = (getProperty(USER_DIR) + PATH);
        List<OldCards> empList = parseXML(fileName);
        for (OldCards emp : empList) {
            System.out.println(emp.toString());
        }
    }

    private static List<OldCards> parseXML(String fileName) {
        List<OldCards> empList = new ArrayList<>();
        OldCards emp = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("postcard")) {
                        emp = new OldCards();
                        Attribute idAttr = startElement.getAttributeByName(new QName("theme"));
                        if (idAttr != null) {
                            emp.setTheme(idAttr.getValue());
                        }
                    }
                    if (startElement.getName().getLocalPart().equals("type")) {
                        Attribute idAdd = startElement.getAttributeByName(new QName("status"));
                        if (idAdd != null) {
                            emp.setStatus(Status.valueOf(xmlEvent.asCharacters().getData().toUpperCase()));
                        }
                    }
                    else if (startElement.getName().getLocalPart().equals("author")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        emp.setAuthor(xmlEvent.asCharacters().getData());
                    }
                    else if (startElement.getName().getLocalPart().equals("country")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        emp.setCountry(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("year")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        emp.setYear(Short.parseShort(xmlEvent.asCharacters().getData()));
                    }  else if (startElement.getName().getLocalPart().equals("valuable")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        emp.setValuable(Valuable.valueOf(xmlEvent.asCharacters().getData().toUpperCase()));
                    }
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("postcard")) {
                        empList.add(emp);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return empList;
    }
}