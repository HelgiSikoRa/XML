package com.epam.lab.Task3;

import com.epam.lab.Task3.Enum.Status;
import com.epam.lab.Task3.Enum.Valuable;

import javax.xml.bind.annotation.*;


@XmlRootElement
public class OldCards {
    private String theme;
    private Status status;
    private String country;
    private short year;
    private String author;
    private Valuable valuable;

    public OldCards() {
    }

    public OldCards(String theme, Status status, String country, short year, String author, Valuable valuable) {
        this.theme = theme;
        this.status = status;
        this.country = country;
        this.year = year;
        this.author = author;
        this.valuable = valuable;
    }

    @XmlAttribute
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @XmlAttribute
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @XmlElement
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlElement
    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    @XmlElement
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @XmlElement
    public Valuable getValuable() {
        return valuable;
    }

    public void setValuable(Valuable valuable) {
        this.valuable = valuable;
    }

    @Override
    public String toString() {
        return "OldCards{" +
                "theme='" + theme + '\'' +
                ", status='" + status + '\'' +
                ", country='" + country + '\'' +
                ", year=" + year +
                ", author='" + author + '\'' +
                ", valuable='" + valuable + '\'' +
                '}';
    }
}
