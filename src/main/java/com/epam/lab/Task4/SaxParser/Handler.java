package com.epam.lab.Task4.SaxParser;

import java.util.ArrayList;
import java.util.List;

import com.epam.lab.Task3.Enum.Status;
import com.epam.lab.Task3.Enum.Valuable;
import com.epam.lab.Task3.OldCards;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class Handler extends DefaultHandler {

    private List<OldCards> cardList = null;
    private OldCards card = null;

    List<OldCards> getCardList() {
        return cardList;
    }

    private boolean bCountry = false;
    private boolean bYear = false;
    private boolean bAauthor = false;
    private boolean bValuable = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("postcard")) {
            String theme = attributes.getValue("theme");

            card = new OldCards();
            card.setTheme(theme);
            if (cardList == null)
                cardList = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("type")) {
            String status = attributes.getValue("status");
            card.setStatus(Status.valueOf(status.toUpperCase()));
        } else if (qName.equalsIgnoreCase("country")) {
            bCountry = true;
        } else if (qName.equalsIgnoreCase("year")) {
            bYear = true;
        } else if (qName.equalsIgnoreCase("author")) {
            bAauthor = true;
        } else if (qName.equalsIgnoreCase("valuable")) {
            bValuable = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("postcard")) {
            cardList.add(card);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
String string = new String(ch, start, length);
        if (bCountry) {
            card.setCountry(string);
            bCountry = false;
        } else if (bAauthor) {
            card.setAuthor(string);
            bAauthor = false;
        } else if (bYear) {
            card.setYear(Short.parseShort(string));
            bYear = false;
        } else if (bValuable) {
            card.setValuable(Valuable.valueOf(string.toUpperCase()));
            bValuable = false;
        }
    }
}