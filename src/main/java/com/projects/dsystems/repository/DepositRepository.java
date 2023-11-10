package com.projects.dsystems.repository;

import com.projects.dsystems.model.Deposit;
import com.projects.dsystems.model.Deposit;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DepositRepository implements CrudDOARepository<Deposit> {

    private final SessionFactory sessionFactory;

    @SneakyThrows
    @Override
    public List<Deposit> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT a FROM Deposit a", Deposit.class).getResultList();
    }

    @SneakyThrows
    @Override
    public Deposit findById(Long id) {
        return sessionFactory.getCurrentSession().find(Deposit.class, id);
    }

    @SneakyThrows
    @Override
    public boolean delete(Long id) {
        Deposit c = findById(id);
        if(c!=null){
            sessionFactory.getCurrentSession().delete(c);
            return true;
        }
        return false;
    }

    @SneakyThrows
    @Override
    public Deposit save(Deposit deposit) {
        if(deposit.getId() == null){
            sessionFactory.getCurrentSession().getTransaction().begin();
            sessionFactory.getCurrentSession().persist(deposit);
            sessionFactory.getCurrentSession().getTransaction().commit();
        }else{
            Deposit c = findById(deposit.getId());
            c.setAmount(deposit.getAmount());
            c.setDepositDate(deposit.getDepositDate());
            c.setCustomerId(deposit.getCustomerId());
            c.setLoanId(deposit.getId());
            sessionFactory.getCurrentSession().getTransaction().begin();
            sessionFactory.getCurrentSession().merge(deposit);
            sessionFactory.getCurrentSession().getTransaction().commit();
        }

        return deposit;
    }
}

