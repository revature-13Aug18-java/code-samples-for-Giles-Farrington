package com.bank.main;

import java.util.ArrayList;
import java.util.List;

import com.bank.pojos.BankAccount;
import com.bank.pojos.Customer;

public class CheckInfo {

	static boolean checkIfNewAccount(int id, String name, int acctID) {
		List<BankAccount> accounts = new ArrayList<BankAccount>();
		accounts = App.bService.getAll(id);
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getAccount_name().equals(name) && accounts.get(i).getAccount_id() == acctID) {
				return false;
			}
		}
		return true;
	}
	
	static boolean checkExistingUsernames(String un) {
		List<Customer> customers = new ArrayList<Customer>();
		customers = App.cService.getAll();
		for(int i = 0; i < customers.size(); i++) {
			if(un.equals(customers.get(i).getUsername())) {
				return true;
			}
		}
		return false;
	}
	
	static boolean checkMaxAccounts(ArrayList<BankAccount> al) {
		if(al.size() > 4) {
			return true;
		}
		else return false;
	}
}
