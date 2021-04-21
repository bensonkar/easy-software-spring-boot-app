package com.example.easyspringboot.services;

import com.example.easyspringboot.entities.Loan;
import com.example.easyspringboot.repositories.LoanRepository;
import com.example.easyspringboot.wrappers.LoanRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bkariuki
 */
@Component
@Transactional
public class LoanService implements LoanRequest {

    protected final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan create(Loan loan) {
        try {
            Loan saveLoan = loanRepository.save(loan);
            return saveLoan;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Loan getLoanByCustomerId(String id) {
        try {
            Loan getLoanByCustomerId = loanRepository.findByCustomerId(id);
            return getLoanByCustomerId;
        } catch (Exception e) {
            throw e;
        }
    }

}
