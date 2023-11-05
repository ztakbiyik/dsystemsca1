package com.example.dsystems;

import jakarta.persistence.*;
import java.util.List;

public class CustomerDAO {
    private final EntityManager entityManager;

    public CustomerDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createCustomer(Customer customer) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(customer);
        transaction.commit();
    }

    public Customer getCustomerById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    public List<Customer> getAllCustomers() {
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    public void updateCustomer(Customer customer) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(customer);
        transaction.commit();
    }

    public void deleteCustomer(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.remove(customer);
        transaction.commit();
    }
}
