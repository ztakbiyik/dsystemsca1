package com.projects.dsystems.web;

import com.projects.dsystems.exception.RecordNotFoundException;
import com.projects.dsystems.model.Customer;
import com.projects.dsystems.model.Loan;
import com.projects.dsystems.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> list = service.getAllCustomers();

        return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Customer entity = service.getCustomerById(id);

        return new ResponseEntity<Customer>(entity, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/loan/{id}")
    public ResponseEntity<Loan> getCustomerLoanById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Loan entity = service.getCustomerLoanById(id);
        return new ResponseEntity<Loan>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> createOrUpdateCustomer(@RequestBody Customer employee)
            throws RecordNotFoundException {
        Customer updated = service.createOrUpdateCustomer(employee);
        return new ResponseEntity<Customer>(updated, new HttpHeaders(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public HttpStatus deleteCustomerById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteCustomerById(id);
        return HttpStatus.OK;
    }

}