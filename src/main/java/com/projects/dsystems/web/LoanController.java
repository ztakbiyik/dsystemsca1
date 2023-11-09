package com.projects.dsystems.web;

import com.projects.dsystems.exception.RecordNotFoundException;
import com.projects.dsystems.model.Deposit;
import com.projects.dsystems.model.Loan;
import com.projects.dsystems.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService service;

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> list = service.getAllLoans();

        return new ResponseEntity<List<Loan>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Loan entity = service.getLoanById(id);

        return new ResponseEntity<Loan>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Loan> createOrUpdateLoan(@RequestBody Loan loan)
            throws RecordNotFoundException {
        Loan updated = service.createOrUpdateLoan(loan);
        return new ResponseEntity<Loan>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public HttpStatus deleteLoanById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteLoanById(id);
        return HttpStatus.OK;
    }

    @PostMapping(path = "/deposit", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Loan> deposit(@RequestBody Deposit deposit)
            throws RecordNotFoundException {
        Loan updated = service.deposit(deposit);
        return new ResponseEntity<Loan>(updated, new HttpHeaders(), HttpStatus.OK);
    }

}