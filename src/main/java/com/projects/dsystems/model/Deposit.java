package com.projects.dsystems.model;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@Table(name = "DEPOSITS")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Deposit")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "deposit_date")
    private Date depositDate;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "loan_id")
    private Long loanId;
    @Column(name = "customer_id")
    private Long customerId;
}
