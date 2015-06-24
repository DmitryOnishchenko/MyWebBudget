package com.electdead.webbudget.model;

import java.util.Date;
import java.util.Objects;

import com.google.common.base.Joiner;

public class Transaction {
    private int transactionId;
    private double amount;
    private Date date;
    private Account account;
    private Category category;
    private String comment;
    
    public Transaction() {}
    
    public Transaction(double amount, Date date, Account account,
    		Category category, String comment) {
    	this.amount = amount;
    	this.date = date;
    	this.account = account;
    	this.category = category;
    	this.comment = comment;
    }
    
    public int getTransactionId() {
        return transactionId;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public Date getDate() {
        return date;
    }
    
    public Account getAccount() {
        return account;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public void setAccount(Account account) {
        this.account = account;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public boolean equals(Object obj) {
    	if (this == obj) {
    		return true;
    	}
    	
    	if (obj == null) {
    		return false;
    	}
    	
    	if (getClass() != obj.getClass()) {
    		return false;
    	}
    	
    	Transaction other = (Transaction) obj;
    	
    	return Objects.equals(transactionId, other.transactionId);
    }
    
	public String toString() {
		return Joiner.on("").join("Transaction.class [transactionId: ",
				transactionId, ", amount: ", amount, ", date: ", date, ", account: ",
				account, ", category: ", category, ", comment: ", comment, "]");
	}
}