package com.projects.dsystems.service;

;
import com.projects.dsystems.exception.RecordNotFoundException;
import com.projects.dsystems.model.Deposit;
import com.projects.dsystems.model.Loan;
import com.projects.dsystems.repository.CustomerRepository;
import com.projects.dsystems.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    public List<Loan> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();

        if (loans.size() > 0) {
            System.out.println("Get All Loans!");
            return loans;
        } else {
            return Collections.emptyList();
        }
    }


    public Loan getLoanById(Long id) throws RecordNotFoundException {
        Loan loan = loanRepository.findById(id);

        if (loan != null) {
            System.out.println("Get Loan! " + loan.toString());
            return loan;
        } else {
            throw new RecordNotFoundException("No Loan record exist for given id");
        }
    }

    public Loan createOrUpdateLoan(Loan entity) throws RecordNotFoundException {
        Loan loan;
        try{
            loan = loanRepository.findByCustomerId(entity.getCustomerId());
            if (loan!=null) {
                loan.setAmount(entity.getAmount());
                loan.setDescription(entity.getDescription());
                loan.setDeposits(entity.getDeposits());
                loan.setCustomerId(entity.getCustomerId());
                loan = loanRepository.save(loan);
                System.out.println("Loan Updated");
                return loan;
            }
        }
        catch (NoResultException e){
            if(customerRepository.findById(entity.getCustomerId())!=null){
                loan = loanRepository.save(entity);
                System.out.println("Loan Created!");
                return entity;
            }else{
                System.out.println("No customer record exist for the loan");
                throw new RecordNotFoundException("No customer record exist for the loan");
            }
        }
        return loan;
    }

    public void deleteLoanById(Long id) throws RecordNotFoundException {
        if (loanRepository.delete(id)) {
            System.out.println("Loan Deleted!");
        } else {
            System.out.println("No loan record exist for given id");
            throw new RecordNotFoundException("No loan record exist for given id");
        }
    }

    public Loan deposit(Deposit deposit) throws RecordNotFoundException {
        Loan loan = loanRepository.findById(deposit.getLoanId());
        if (loan!=null) {
            loan.getDeposits().add(deposit);
            System.out.println("Deposit has been made " + deposit.toString());
            loanRepository.save(loan);
            return loan;
        } else {
            System.out.println("No loan record exist for given deposit");
            throw new RecordNotFoundException("No Loan record exist for given id");
        }
    }


    public Loan deleteDeposit(Deposit deposit) throws RecordNotFoundException {
        Loan loan = loanRepository.findById(deposit.getLoanId());
        if (loan!=null) {
            List<Deposit> list = loan.getDeposits();
            list = list.stream().filter(d -> !Objects.equals(d.getId(), deposit.getId())).collect(Collectors.toList());
            loan.setDeposits(list);
            loanRepository.save(loan);
            System.out.println("Deposit deleted!");
            return loan;
        } else {
            System.out.println("No loan record exist for given deposit");
            throw new RecordNotFoundException("No Loan record exist for given id");
        }
    }
}
