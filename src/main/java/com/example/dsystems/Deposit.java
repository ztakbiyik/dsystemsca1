package com.example.dsystems;

import jakarta.persistence.*;
import org.hibernate.annotations.Table;

import java.util.Date;

@Entity
@Table(name = "deposits")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date depositDate;
    private double amount;

    @ManyToOne
    private Customer customer; // Establish a many-to-one relationship with the Customer entity

    // Constructors, getters, and setters

    public Deposit() {
        // Default constructor
    }

    public Deposit(Date depositDate, double amount, Customer customer) {
        this.depositDate = depositDate;
        this.amount = amount;
        this.customer = customer;
    }

    // Getter and Setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
