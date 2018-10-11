package com.bank.main;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.bank.pojos.BankAccount;

public class Transaction {

	static void deposit(BankAccount acc) {
		System.out.println("How much would you like to deposit?");
		double amount = 0;
		try { //DONT USE SCANNER.NEXT INT!!!!!!
			amount = Double.parseDouble(App.scanner.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("Sorry, you must enter a valid number");
			deposit(acc);
		}
		if(amount > 999999999.99) {
			System.out.println("Sorry, that is WAY too much money, please deposit less at a time");
			deposit(acc);
		}
		else if(amount < 10) {
			System.out.println("Sorry, you must deposit at least $10");
			deposit(acc);
		}
		DecimalFormat df = new DecimalFormat("#.00");
		if(Double.parseDouble(df.format(amount)) + acc.getBalance() > 9999999999999999.99) {
			System.out.println("Sorry you have a ridiculous amount of money, we have nowhere to store it, transaction canceled");
			MainMenu.mainMenu(App.cService.getOne(acc.getCustomer_id()));
		}
		acc.setBalance(Double.parseDouble(df.format(amount)) + acc.getBalance());
		amount = Double.parseDouble(df.format(amount));
		System.out.println("You have successfully deposited: "); 
		System.out.printf("%.2f\n", amount);
		System.out.print("Your account balance is now: ");
		System.out.printf("%.2f\n", acc.getBalance());
		List <BankAccount> accounts = new ArrayList<BankAccount>();
		accounts = App.bService.getAll(acc.getCustomer_id());
		if(accounts.isEmpty()) {
			App.bService.saveNewAccount(acc);
		}
		else if(CheckInfo.checkIfNewAccount(acc.getCustomer_id(),acc.getAccount_name(), acc.getAccount_id())) {
			App.bService.saveNewAccount(acc);
		}
		else {
			App.bService.update(acc);
		}
		AccountOptions.accountOptions(App.cService.getOne(acc.getCustomer_id()), acc);
	}
	
	static void withdraw(BankAccount acc) {
		System.out.println("Your current balance in this account is: ");
		System.out.printf("%.2f\n", acc.getBalance());
		System.out.println("How much would you like to withdraw?");
		double amount = 0;
		try { //DONT USE SCANNER.NEXT INT!!!!!!
			amount = Double.parseDouble(App.scanner.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("Sorry, you must enter a valid number)");
			withdraw(acc);
		}
		if(amount > acc.getBalance()) {
			System.out.println("Sorry, you don't have enough money in your account to withdraw that much, please try withdrawing less than your current balance.");
			withdraw(acc);
		}
		else if(amount < 0) {
			System.out.println("Sorry, you can't withdraw a negative amount, please try again.");
			withdraw(acc);
		}
		DecimalFormat df = new DecimalFormat("###.##");
		acc.setBalance(acc.getBalance() - Double.parseDouble(df.format(amount)));
		amount = Double.parseDouble(df.format(amount));
		App.bService.update(acc);
		System.out.println("You have successfully withdrew " );
		System.out.printf("%.2f\n", amount);
		System.out.println("Your account balance is now: ");
		System.out.printf("%.2f\n", acc.getBalance());
		System.out.println(" \nYou will now be returned to the Account Options Screen.");
		AccountOptions.accountOptions(App.cService.getOne(acc.getCustomer_id()), acc);
	}
}
