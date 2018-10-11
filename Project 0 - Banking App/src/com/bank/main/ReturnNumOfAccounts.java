package com.bank.main;

import java.util.ArrayList;
import java.util.List;

import com.bank.pojos.BankAccount;

public class ReturnNumOfAccounts {

	static int returnNumOfSavings(int id) {
		int count = 0;
		List<BankAccount> accounts = new ArrayList<BankAccount>();
		accounts = App.bService.getAll(id);
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getAccount_type().equals("'savings'")) {
				count++;
			}
		}
		return count;
	}
	
	static int returnNumOfChecking(int id) {
		int count = 0;
		List<BankAccount> accounts = new ArrayList<BankAccount>();
		accounts = App.bService.getAll(id);
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getAccount_type().equals("'checking'")) {
				count++;
			}
		}
		return count;
	}
}
