package com.projects.dsystems.repository;

import com.projects.dsystems.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepositRepository
        extends JpaRepository<Deposit, Long> {

}
