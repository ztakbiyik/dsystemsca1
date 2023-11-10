package com.projects.dsystems.service;

import com.projects.dsystems.exception.RecordNotFoundException;
import com.projects.dsystems.model.Customer;
import com.projects.dsystems.model.Loan;
import com.projects.dsystems.repository.CustomerRepository;
import com.projects.dsystems.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final LoanRepository loanRepository;

    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        if (!customers.isEmpty()) {

            System.out.println("Get All Customer!");
            return customers;
        } else {
            return new ArrayList<Customer>();
        }
    }


    public Customer getCustomerById(Long id) throws RecordNotFoundException {
        Customer customer = customerRepository.findById(id);

        if (customer!=null) {
            return customer;
        } else {
            System.out.println("No customer record exist for given id");
            throw new RecordNotFoundException("No customer record exist for given id");
        }
    }



    public Loan getCustomerLoanById(Long id) throws RecordNotFoundException {
        Customer customer = customerRepository.findById(id);
        if(customer != null){
            Loan l = loanRepository.findByCustomerId(id);
            if( l!=null){
                System.out.println("Loan found for customer " + customer.toString());
                return l;
            }
             else {
                System.out.println("No loan record exist for given customer id");
                throw new RecordNotFoundException("No loan record exist for given customer id");
            }
        } else {
            System.out.println("No customer record exist for given id");
            throw new RecordNotFoundException("No customer record exist for given id");
        }

    }


    public Customer createOrUpdateCustomer(Customer entity) throws RecordNotFoundException {
        return customerRepository.save(entity);
    }

    public void deleteCustomerById(Long id) throws RecordNotFoundException {
        if (customerRepository.delete(id)) {
            System.out.println("Customer Deleted!");
        } else {
            System.out.println("No customer record exist for given id");
            throw new RecordNotFoundException("No customer record exist for given id");
        }
    }

}
