package com.projects.dsystems.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "LOANS")
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "amount")
    private Double amount;

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Long customerId;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private List<Deposit> deposits;

}
