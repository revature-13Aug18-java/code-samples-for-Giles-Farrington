package com.bank.main;

import com.bank.exceptions.ContainsSpacesException;
import com.bank.exceptions.CustomExceptionMethods;
import com.bank.exceptions.NameTooLargeException;
import com.bank.exceptions.NonAlphaNumericalException;
import com.bank.pojos.BankAccount;
import com.bank.pojos.Customer;

public class CreateNewAccount {

	static void createAccount(Customer c) {
		int choice = 0;
		BankAccount newAccount = new BankAccount();
		newAccount.setCustomer_id(c.getCustomer_id());
		System.out.println("____________________________________________"
				+ "\n|_____________ACCOUNT CREATION______________|"
				+ "\n|What type of account would like to make?   |"
				+ "\n|1) Savings Account                         |"
				+ "\n|2) Checking Account                        |"
				+ "\n|3) Exit and return to main menu            |"
				+ "\n---------------------------------------------");
		try { //DONT USE SCANNER.NEXT INT!!!!!!
			choice = Integer.parseInt(App.scanner.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("Sorry, you must choose one of the 2 options: Please type '1' or '2' and then press enter)");
			createAccount(c);
		}
		if( choice < 1 || choice > 3) {
			System.out.println("Sorry, you must choose one of the 2 options: Please type '1' or '2' and then press enter)");
			createAccount(c);
		}
		switch(choice) {
		case 1:
			if(ReturnNumOfAccounts.returnNumOfSavings(c.getCustomer_id()) == 2) {
				System.out.println("Sorry you can only have a maximum of 2 savings accounts which you currently have, returning to main menu");
				MainMenu.mainMenu(c);
				break;
			}
			System.out.println("You chose Savings account, beginning savings account creation process.");
			newAccount.setAccount_type(Misc.quote("savings"));
			nameNewAccount(newAccount);
			break;
		case 2:
			if(ReturnNumOfAccounts.returnNumOfChecking(c.getCustomer_id()) == 2) {
				System.out.println("Sorry you can only have a maximum of 2 checking accounts which you currently have, returning to main menu");
				MainMenu.mainMenu(c);
				break;
			}
			System.out.println("You chose Checking Account, beginning checking account creation process");
			newAccount.setAccount_type(Misc.quote("checking"));
			nameNewAccount(newAccount);
			break;
		case 3:
			MainMenu.mainMenu(c);
			break;
		}
		
	}
	
	static void nameNewAccount(BankAccount acc) {
		String account_name = " ";
		System.out.println("Time to name your new " + acc.getAccount_type() + " account.");
		System.out.println("Please enter a valid name: ");
		try {
			account_name = App.scanner.nextLine();
			CustomExceptionMethods.checkForSpaces(account_name);
			CustomExceptionMethods.checkAlphaNumerical(account_name);
			CustomExceptionMethods.checkLargeName(account_name);
		}
		catch(ContainsSpacesException e) {
			System.out.println(e.getMessage());
			nameNewAccount(acc);
		}
		catch(NameTooLargeException e) {
			System.out.println(e.getMessage());
			nameNewAccount(acc);
		}
		catch(NonAlphaNumericalException e) {
			System.out.println(e.getMessage());
			nameNewAccount(acc);
		}
		if(account_name.toLowerCase().equals("exit")) {
			LoginScreen.loginScreen();
		}
		acc.setAccount_name(Misc.quote(account_name));
		System.out.println("You have successfully named your new " + acc.getAccount_type() + " account " + account_name);
		Transaction.deposit(acc);
		
	}
}
