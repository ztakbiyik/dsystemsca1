package com.example.dsystems;
import jakarta.persistence.*;
import java.util.List;

public class LoanDAO {
    private final EntityManager entityManager;

    public LoanDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createLoan(Loan loan) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(loan);
        transaction.commit();
    }

    public Loan getLoanById(Long id) {
        return entityManager.find(Loan.class, id);
    }

    public List<Loan> getAllLoans() {
        return entityManager.createQuery("SELECT l FROM Loan l", Loan.class).getResultList();
    }

    public void updateLoan(Loan loan) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(loan);
        transaction.commit();
    }

    public void deleteLoan(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Loan loan = entityManager.find(Loan.class, id);
        entityManager.remove(loan);
        transaction.commit();
    }
}
