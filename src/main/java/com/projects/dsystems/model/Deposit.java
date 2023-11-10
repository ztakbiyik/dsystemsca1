package com.projects.dsystems.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DEPOSITS")
@Data
@ToString
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
