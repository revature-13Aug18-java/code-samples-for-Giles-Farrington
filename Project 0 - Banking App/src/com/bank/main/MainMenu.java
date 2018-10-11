package com.bank.main;

import java.util.ArrayList;
import java.util.List;

import com.bank.pojos.BankAccount;
import com.bank.pojos.Customer;

public class MainMenu {

	static void mainMenu(Customer c) {
		List <BankAccount> accounts = new ArrayList<BankAccount>();
		accounts = App.bService.getAll(c.getCustomer_id());
		chooseTypeOfAccount(c, accounts);
		
	}
	
	public static void chooseTypeOfAccount(Customer c, List <BankAccount> accounts) {
		BankAccount ba = new BankAccount();
		int numOfSAccounts = ReturnNumOfAccounts.returnNumOfSavings(c.getCustomer_id());
		int numOfCAccounts = ReturnNumOfAccounts.returnNumOfChecking(c.getCustomer_id());
		System.out.println("_____________________________________________________________________");
		System.out.println("|___________________________MAIN MENU________________________________|");
		System.out.println("|Which type of account would you like to access? 	             |");
		System.out.println("|1) Savings 							     |");
		System.out.println("|2) Checking 						             |");
		System.out.println("|3) Create new account 						     |");	
		System.out.println("|4) Exit To Log-in Screen                                            |");
		System.out.println("----------------------------------------------------------------------");
		int choice = 0;
		try { //DONT USE SCANNER.NEXT INT!!!!!!
			choice = Integer.parseInt(App.scanner.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("Sorry, you must choose one of the 4 options: Please type '1','2','3', or '4' and then press enter)");
			chooseTypeOfAccount(c, accounts);
		}
		if( choice < 1 || choice > 4) {
			System.out.println("Sorry, you must choose one of the 4 options: Please type '1','2','3', or '4' and then press enter)");
			chooseTypeOfAccount(c, accounts);
		}
		switch(choice) {
		case 1:
			List<BankAccount> sAccounts = new ArrayList<BankAccount>();
			for(int i = 0; i < accounts.size(); i++) {
				if(accounts.get(i).getAccount_type().equals("'savings'")) {
					sAccounts.add(accounts.get(i));
				}
			}
			switch(numOfSAccounts) {
			case 0:
				System.out.println("You don't have any savings accounts, please create one!");
				CreateNewAccount.createAccount(c);
			case 1:
				ba = App.bService.getOne(sAccounts.get(0).getAccount_id());
				System.out.println("You only have one Savings account called: "  + ba.getAccount_name() + ". ");
				AccountOptions.accountOptions(c, ba);
				break;
			case 2:
				AccountOptions.chooseAccount(sAccounts);
				break;
			}
		case 2:
			List<BankAccount> cAccounts = new ArrayList<BankAccount>();
			for(int i = 0; i < accounts.size(); i++) {
				if(accounts.get(i).getAccount_type().equals("'checking'")) {
					cAccounts.add(accounts.get(i));
				}
			}
			switch(numOfCAccounts) {
			case 0:
				System.out.println("You don't have any checking accounts, please create one!");
				CreateNewAccount.createAccount(c);
			case 1:
				ba = App.bService.getOne(cAccounts.get(0).getAccount_id());
				System.out.println("You only have one Checking account called: " + ba.getAccount_name() + ". ");
				AccountOptions.accountOptions(c, ba);
				break;
			case 2:
				AccountOptions.chooseAccount(cAccounts);
				break;
			}
		case 3:
			CreateNewAccount.createAccount(c);
			break;
		case 4:
			LoginScreen.loginScreen();
			break;
		}
	}
}
