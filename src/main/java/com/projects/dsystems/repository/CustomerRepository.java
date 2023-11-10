package com.projects.dsystems.repository;


import com.projects.dsystems.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerRepository implements CrudDOARepository<Customer> {

    private final SessionFactory sessionFactory;

    @SneakyThrows
    @Override
    public List<Customer> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT a FROM Customer a", Customer.class).getResultList();
    }

    @SneakyThrows
    @Override
    public Customer findById(Long id) {
        return sessionFactory.getCurrentSession().find(Customer.class, id);
    }

    @SneakyThrows
    @Override
    public boolean delete(Long id) {
        Customer c = findById(id);
        if(c!=null){
            sessionFactory.getCurrentSession().getTransaction().begin();
            sessionFactory.getCurrentSession().delete(c);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return true;
        }
        return false;
    }

    @SneakyThrows
    @Override
    public Customer save(Customer customer) {
        if(customer.getId() == null){
            sessionFactory.getCurrentSession().getTransaction().begin();
            sessionFactory.getCurrentSession().persist(customer);
            sessionFactory.getCurrentSession().getTransaction().commit();
            System.out.println("Customer Created: " + customer.toString());
        }else{
            Customer c = findById(customer.getId());
            c.setName(customer.getName());
            c.setAddress(customer.getAddress());
            c.setAnnualSalary(customer.getAnnualSalary());
            c.setPhoneNumber(customer.getPhoneNumber());
            sessionFactory.getCurrentSession().getTransaction().begin();
            sessionFactory.getCurrentSession().merge(customer);
            sessionFactory.getCurrentSession().getTransaction().commit();
            System.out.println("Customer Updated: " + customer.toString());
        }

        return customer;
    }
}
