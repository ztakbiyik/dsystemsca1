package com.projects.dsystems.service;

import com.projects.dsystems.exception.RecordNotFoundException;
import com.projects.dsystems.model.Deposit;
import com.projects.dsystems.model.Loan;
import com.projects.dsystems.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;

    public List<Loan> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();

        if (loans.size() > 0) {
            return loans;
        } else {
            return new ArrayList<Loan>();
        }
    }


    public Loan getLoanById(Long id) throws RecordNotFoundException {
        Optional<Loan> customer = loanRepository.findById(id);

        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new RecordNotFoundException("No Loan record exist for given id");
        }
    }

    public Loan createOrUpdateLoan(Loan entity) throws RecordNotFoundException {

        if (entity.getId() == null){
            entity = loanRepository.save(entity);

            return entity;
        } else {
            Optional<Loan> loan = loanRepository.findById(entity.getId());

            if (loan.isPresent()) {
                Loan newEntity = loan.get();
                newEntity.setAmount(entity.getAmount());
                newEntity.setDescription(entity.getDescription());
                newEntity.setDeposits(entity.getDeposits());
                newEntity.setCustomerId(entity.getCustomerId());
                newEntity = loanRepository.save(newEntity);

                return newEntity;
            } else {
                entity = loanRepository.save(entity);

                return entity;
            }
        }

    }

    public void deleteLoanById(Long id) throws RecordNotFoundException {
        Optional<Loan> loan = loanRepository.findById(id);

        if (loan.isPresent()) {
            loanRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Loan record exist for given id");
        }
    }

    public Loan deposit(Deposit deposit) throws RecordNotFoundException {
        Optional<Loan> optionalLoan = loanRepository.findById(deposit.getLoanId());

        if (optionalLoan.isPresent()) {
            Loan loan = optionalLoan.get();
            loan.getDeposits().add(deposit);

            loanRepository.save(loan);
            return loan;
        } else {
            throw new RecordNotFoundException("No Loan record exist for given id");
        }
    }
}
