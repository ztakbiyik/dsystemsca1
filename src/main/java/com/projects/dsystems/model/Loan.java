package com.projects.dsystems.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "LOANS")
@Data
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "customer_id")
    private Long customerId;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private List<Deposit> deposits;

}
