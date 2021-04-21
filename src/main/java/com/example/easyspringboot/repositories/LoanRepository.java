package com.example.easyspringboot.repositories;

import com.example.easyspringboot.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bkariuki
 */
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Loan findByCustomerId(String customerId);
}
