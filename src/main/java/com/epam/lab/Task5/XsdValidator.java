package com.epam.lab.Task5;

import jdk.internal.org.xml.sax.SAXException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

import static com.epam.lab.Task4.DomParser.PATH;
import static com.epam.lab.Task4.DomParser.PATH_XSD;
import static com.epam.lab.Task4.DomParser.USER_DIR;
import static java.lang.System.getProperty;


public class XsdValidator {
    private final static Logger LOG = LogManager.getLogger(XsdValidator.class);

    public static void main(String[] args) {
        String pathXml = (getProperty(USER_DIR) + PATH);
        String pathXsd = (getProperty(USER_DIR) + PATH_XSD);
        LOG.info("Validates: " + validateXMLSchema(pathXsd, pathXml));
    }

    private static boolean validateXMLSchema(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        } catch (org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
        return true;
    }
}