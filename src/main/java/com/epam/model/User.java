package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@XmlRootElement
@XmlType(propOrder = {"firstName", "lastName", "email"})
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="user")
public class User {
    @XmlAttribute(name = "id")
    @Id
    private long id;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="email")
    private String email;

    public User(long userId) {
        this.id = userId;
    }
}
