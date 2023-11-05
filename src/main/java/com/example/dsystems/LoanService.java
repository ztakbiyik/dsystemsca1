package com.example.dsystems;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.NotFoundException;

import java.util.Date;
import java.util.List;

public class LoanService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit-name");

    public List<Loan> getAllLoans() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT l FROM Loan l", Loan.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Loan createLoan(Loan loan) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(loan);
            em.getTransaction().commit();
            return loan;
        } finally {
            em.close();
        }
    }

    public Loan getLoanById(long loanId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Loan.class, loanId);
        } finally {
            em.close();
        }
    }

    public Payment makeLoanPayment(Loan loan, Payment payment) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Verify Loan Existence
            Loan existingLoan = em.find(Loan.class, loan.getId());
            if (existingLoan == null) {
                throw new NotFoundException("Loan with ID " + loan.getId() + " not found");
            }

            // Record Payment
            Payment recordedPayment = new Payment(payment.getAmount(), new Date());
            em.persist(recordedPayment);

            // Update Loan Information
            existingLoan.getPayments().add(recordedPayment);
            em.merge(existingLoan);

            em.getTransaction().commit();
            return recordedPayment;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e; // Rethrow the exception to handle it at a higher level
        } finally {
            em.close();
        }
    }

}
