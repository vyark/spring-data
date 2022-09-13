package com.epam.model;

import com.epam.mapper.DateAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"title", "date"})
@Entity
public class Event {
    @XmlAttribute(name="id")
    @Id
    private long id;
    private String title;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date eventDate;

    private BigDecimal ticketPrice;

    public Event(long eventId) {
        this.id=eventId;
    }
}
