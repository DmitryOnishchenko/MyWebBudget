package com.electdead.webbudget.model;

import java.util.Objects;

import com.google.common.base.Joiner;

public class Account {
	private int accountId;
	private String name;
	private double money;
	private User user;
	
	public int getAccountId() {
		return accountId;
	}
	
	public String getName() {
		return name;
	}
	
	public double getMoney() {
		return money;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}
	
	public void setUser(User user) {
		this.user = user;
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
    	
    	Account other = (Account) obj;
    	
    	return Objects.equals(accountId, other.accountId);
    }
	
	public String toString() {
		return Joiner.on("").join("Account.class [accountId: ", accountId,
				", name: ", name, ", money: ", money, ", user: ", user, "]");
	}
}
