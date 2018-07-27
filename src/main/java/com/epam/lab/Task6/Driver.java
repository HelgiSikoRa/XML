package com.epam.lab.Task6;

import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

import static com.epam.lab.Task4.DomParser.*;
import static java.lang.System.getProperty;

public class Driver {
    private static String pathXml = (getProperty(USER_DIR) + PATH);
    private static String pathXsl = (getProperty(USER_DIR) + PATH_XSL);
    private static String pathHtml = (getProperty(USER_DIR) + PATH_HTML);

    public static void main(String[] args) {
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();

            Source xslDoc = new StreamSource(pathXsl);
            Source xmlDoc = new StreamSource(pathXml);

            String outputFileName = pathHtml;

            OutputStream htmlFile = new FileOutputStream(outputFileName);
            Transformer trasform = tFactory.newTransformer(xslDoc);
            trasform.transform(xmlDoc, new StreamResult(htmlFile));
        } catch (FileNotFoundException | TransformerFactoryConfigurationError | TransformerException e) {
            e.printStackTrace();
        }
    }
}