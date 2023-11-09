package com.projects.dsystems.service;

import com.projects.dsystems.exception.RecordNotFoundException;
import com.projects.dsystems.model.Customer;
import com.projects.dsystems.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        if (customers.size() > 0) {
            return customers;
        } else {
            return new ArrayList<Customer>();
        }
    }


    public Customer getCustomerById(Long id) throws RecordNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new RecordNotFoundException("No customer record exist for given id");
        }
    }

    public Customer createOrUpdateCustomer(Customer entity) throws RecordNotFoundException {
        if (entity.getId() == null){
            entity = customerRepository.save(entity);

            return entity;
        } else {
            Optional<Customer> customer = customerRepository.findById(entity.getId());

            if (customer.isPresent()) {
                Customer newEntity = customer.get();
                newEntity.setAddress(entity.getAddress());
                newEntity.setName(entity.getName());
                newEntity.setAnnualSalary(entity.getAnnualSalary());
                newEntity.setPhoneNumber(entity.getPhoneNumber());

                newEntity = customerRepository.save(newEntity);

                return newEntity;
            } else {
                entity = customerRepository.save(entity);

                return entity;
            }
        }

    }

    public void deleteCustomerById(Long id) throws RecordNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()) {
            customerRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

}
