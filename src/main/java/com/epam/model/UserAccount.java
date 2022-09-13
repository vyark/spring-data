package com.epam.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class UserAccount {
    @Id
    private Long id;

    private BigDecimal prepaidAmount;

}
