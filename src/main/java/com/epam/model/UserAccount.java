package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="userAccount")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
    @Id
    @Column(name="userAccountId")
    private Long id;

    @Column(name="userId")
    private Long userId;

    @Column(name="prepaidAmount")
    private BigDecimal prepaidAmount;

}
