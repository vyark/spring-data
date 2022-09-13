package com.epam.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAttribute;

@Data
@XmlRootElement
@XmlType(propOrder = {"user", "event", "category", "place"})
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Ticket {
    public enum Category {STANDARD, PREMIUM, BAR}
    @XmlAttribute(name="id")
    @Id
    private long id;
    private Event event;
    private User user;
    private Category category;
    private int place;
}
