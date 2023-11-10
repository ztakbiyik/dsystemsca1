package com.projects.dsystems.repository;

import com.projects.dsystems.model.Loan;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LoanRepository implements CrudDOARepository<Loan> {

    private final SessionFactory sessionFactory;

    @SneakyThrows
    @Override
    public List<Loan> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT a FROM Loan a", Loan.class).getResultList();
    }

    @SneakyThrows
    public Loan findByCustomerId(Long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Loan s where s.customerId = :customerId", Loan.class)
                .setParameter("customerId", id).getSingleResult();
    }

    @SneakyThrows
    @Override
    public Loan findById(Long id) {
        return sessionFactory.getCurrentSession().find(Loan.class, id);
    }

    @SneakyThrows
    @Override
    public boolean delete(Long id) {
        Loan c = findById(id);
        if (c != null) {
            sessionFactory.getCurrentSession().delete(c);
            return true;
        }
        return false;
    }

    @SneakyThrows
    @Override
    public Loan save(Loan loan) {
        if (loan.getId() == null) {
            sessionFactory.getCurrentSession().getTransaction().begin();
            sessionFactory.getCurrentSession().persist(loan);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } else {
            Loan c = findById(loan.getId());
            c.setAmount(loan.getAmount());
            c.setDeposits(loan.getDeposits());
            c.setDescription(loan.getDescription());
            c.setCustomerId(loan.getCustomerId());
            sessionFactory.getCurrentSession().getTransaction().begin();
            sessionFactory.getCurrentSession().merge(loan);
            sessionFactory.getCurrentSession().getTransaction().commit();
        }

        return loan;
    }
}
