package com.example.easyspringboot.resources;

import com.example.easyspringboot.entities.Loan;
import com.example.easyspringboot.repositories.LoanRepository;
import com.example.easyspringboot.wrappers.LoanRequest;
import com.example.easyspringboot.wrappers.LoanWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author bkariuki
 * Rest endpoint resource or class
 * to access apis use this base url http://localhost:9000/api/v1
 * to acces swagger ui for api documentation use http://localhost:9000/api/v1/swagger-ui.html
 */
@RestController
@RequestMapping("/loan")
public class LoanResource {
    protected final LoanRepository loanRepository;
    protected final LoanRequest loanRequest;

    public LoanResource(LoanRepository loanRepository, LoanRequest loanRequest) {
        this.loanRepository = loanRepository;
        this.loanRequest = loanRequest;
    }

    //create new loan
    @PostMapping("/create")
    public ResponseEntity createLoan(@RequestBody Loan loan) {
        Loan getLoanByCustomer = loanRepository.findByCustomerId(loan.getCustomerId());
        //check if all fields are not null
        if (loan.getLoanDate() == null || loan.getLoanDueDate() == null || loan.getLoanCode() == null ||
                loan.getAmount() == null || loan.getInterest() == null || loan.getCustomerId() == null) {
            return new ResponseEntity("all data fields are required!!", HttpStatus.BAD_REQUEST);
        }
        //check if customer id exists
        if (Objects.nonNull(getLoanByCustomer)) {
            return new ResponseEntity("customer id already exists", HttpStatus.CONFLICT);
        }
        Loan response = loanRequest.create(loan);
        return ResponseEntity.status(201).body(response);
    }

    //find loan by customer id
    @GetMapping("/customer/{id}")
    public ResponseEntity getLoanByCustomerId(@PathVariable("id") String id) {
        Loan loan = loanRepository.findByCustomerId(id);
        if (Objects.isNull(loan)) {
            return new ResponseEntity("customer id does not exist!!", HttpStatus.NOT_FOUND);
        }
        Loan response = loanRequest.getLoanByCustomerId(id);
        return ResponseEntity.ok(response);
    }

    //patch loan by customer id
    @PatchMapping("/patch/{id}")
    @Transactional
    public ResponseEntity patchLoan(@RequestBody LoanWrapper wrapper, @PathVariable("id") String id) {
        Loan loan = loanRepository.findByCustomerId(id);
        if (Objects.isNull(loan)) {
            return new ResponseEntity("could not find requested loan due to customer id does not exist",
                    HttpStatus.NOT_FOUND);
        }
        if (wrapper.getAmount() != null) {
            loan.setAmount(wrapper.getAmount());
        }
        if (wrapper.getInterest() != null) {
            loan.setInterest(wrapper.getInterest());
        }
        if (wrapper.getLoanDate() != null) {
            loan.setLoanDate(wrapper.getLoanDate());
        }
        if (wrapper.getLoanDueDate() != null) {
            loan.setLoanDueDate(wrapper.getLoanDueDate());
        }
        if (wrapper.getCustomerId() != null) {
            loan.setCustomerId(wrapper.getCustomerId());
        }
        if (wrapper.getTransactionId() != null) {
            loan.setTransactionId(wrapper.getTransactionId());
        }
        Loan response = loanRepository.save(loan);
        return ResponseEntity.ok(response);
    }

    //put request for updating all fields in loan db by customer id
    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity updateLoan(@RequestBody Loan loan, @PathVariable("id") String id) {
        Loan patchLoan = loanRepository.findByCustomerId(id);
        if (Objects.isNull(loan)) {
            return new ResponseEntity("could not find requested loan due to customer id does not exist",
                    HttpStatus.NOT_FOUND);
        }
        patchLoan.setLoanDate(loan.getLoanDate());
        patchLoan.setLoanDueDate(loan.getLoanDueDate());
        patchLoan.setInterest(loan.getInterest());
        patchLoan.setTransactionId(loan.getTransactionId());
        patchLoan.setAmount(loan.getAmount());
        patchLoan.setCustomerId(loan.getCustomerId());
        patchLoan.setLoanCode(loan.getLoanCode());
        Loan response = loanRepository.save(patchLoan);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
