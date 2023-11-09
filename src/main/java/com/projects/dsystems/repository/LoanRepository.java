package com.projects.dsystems.repository;

import com.projects.dsystems.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoanRepository
        extends JpaRepository<Loan, Long> {

}
