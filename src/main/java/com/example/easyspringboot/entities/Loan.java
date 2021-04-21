package com.example.easyspringboot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author bkariuki
 */
@Entity
public class Loan implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "TRANSACTION_ID")
    private String transactionId;
    @Column(name = "AMOUNT")
    private String amount;
    @Column(name = "INTEREST")
    private String interest;
    @Column(name = "LOAN_CODE")
    private String loanCode;
    @Column(name = "LOAN_DATE")
    private String loanDate;
    @Column(name = "LOAN_DUE_DATE")
    private String loanDueDate;
    @Column(name = "CUSTOMER_ID")
    private String customerId;

    public Loan() {
    }

    public Loan(String transactionId, String amount, String interest, String loanCode, String loanDate,
                String loanDueDate, String customerId) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.interest = interest;
        this.loanCode = loanCode;
        this.loanDate = loanDate;
        this.loanDueDate = loanDueDate;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanDueDate() {
        return loanDueDate;
    }

    public void setLoanDueDate(String loanDueDate) {
        this.loanDueDate = loanDueDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
