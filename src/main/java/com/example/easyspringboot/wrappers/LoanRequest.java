package com.example.easyspringboot.wrappers;

import com.example.easyspringboot.entities.Loan;

/**
 * @author bkariuki
 */
public interface LoanRequest {
    Loan create(Loan loan);
    Loan getLoanByCustomerId(String id);
}
