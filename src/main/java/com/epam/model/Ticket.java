package com.epam.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name="ticket")
public class Ticket {
    public enum Category {STANDARD, PREMIUM, BAR}
    @XmlAttribute(name="id")
    @Id
    @Column(name="ticketId")
    private long id;

    @Column(name="eventId")
    private Event event;

    @Column(name="userId")
    private User user;

    @Column(name="category")
    private Category category;

    @Column(name="place")
    private int place;
}
