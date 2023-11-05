package com.example.dsystems;

import jakarta.persistence.*;
import java.util.List;

public class DepositDAO {
    private final EntityManager entityManager;

    public DepositDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createDeposit(Deposit deposit) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(deposit);
        transaction.commit();
    }

    public Deposit getDepositById(Long id) {
        return entityManager.find(Deposit.class, id);
    }

    public List<Deposit> getAllDeposits() {
        return entityManager.createQuery("SELECT d FROM Deposit d", Deposit.class).getResultList();
    }

    public void updateDeposit(Deposit deposit) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(deposit);
        transaction.commit();
    }

    public void deleteDeposit(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Deposit deposit = entityManager.find(Deposit.class, id);
        entityManager.remove(deposit);
        transaction.commit();
    }
}
