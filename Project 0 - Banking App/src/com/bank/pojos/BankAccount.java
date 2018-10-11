package com.bank.pojos;

public class BankAccount {
	
	private int account_id;
	private String account_type;
	private String account_name;
	private double balance;
	private int customer_id;
	
	public BankAccount() {};
	public BankAccount(int account_id, String account_type, double balance, int customer_id) {
		super();
		this.account_id = account_id;
		this.account_type = account_type;
		this.balance = balance;
		this.customer_id = customer_id;
	}
	
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	@Override
	public String toString() {
		return "BankAccount [account_id=" + account_id + ", account_type=" + account_type + ", account_name="
				+ account_name + ", balance=" + balance + ", customer_id=" + customer_id + "]";
	}
	
	
}
