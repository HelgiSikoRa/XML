package com.epam.lab.Task7;

import com.epam.lab.Task3.Enum.Status;
import com.epam.lab.Task3.Enum.Valuable;
import com.epam.lab.Task3.OldCards;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import static com.epam.lab.Task4.DomParser.PATH_CHANGED_XML;
import static com.epam.lab.Task4.DomParser.USER_DIR;
import static java.lang.System.getProperty;

public class Jaxb {
    private final static Logger LOG = LogManager.getLogger(Jaxb.class);

    public static void main(String[] args) {
        String newXmlPath = ((getProperty(USER_DIR) + PATH_CHANGED_XML));
        short s = 1965;
        OldCards newCard = new OldCards();
        newCard.setTheme("city landscape");
        newCard.setStatus(Status.NOT_SENT);
        newCard.setCountry("China");
        newCard.setAuthor("Lin Cho");
        newCard.setValuable(Valuable.COLLECTIMLE);
        newCard.setYear(s);

        marshall(newXmlPath, newCard);

        unmarshall(newXmlPath);
    }

    private static void marshall(String newXmlPath, OldCards newCard) {
        try {
            File file = new File(newXmlPath);
            JAXBContext jaxbContext = JAXBContext.newInstance(OldCards.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(newCard, file);
            jaxbMarshaller.marshal(newCard, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void unmarshall(String newXmlPath) {
        try {
            File file = new File(newXmlPath);
            JAXBContext jaxbContext = JAXBContext.newInstance(OldCards.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            OldCards unmarshalCard = (OldCards) jaxbUnmarshaller.unmarshal(file);
            LOG.info(unmarshalCard);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
