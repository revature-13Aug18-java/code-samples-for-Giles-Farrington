package com.bank.main;

import java.util.List;

import com.bank.pojos.BankAccount;
import com.bank.pojos.Customer;

public class AccountOptions {

	public static void accountOptions(Customer c, BankAccount account) {
		int choice = 0;
		System.out.print("\n_______________________________________________________________________________"
				+ "\n|___________________________ACCOUNT OPTIONS SCREEN_____________________________|"
				+ "\n|Current Account Type: " + account.getAccount_type() + " Name: " + account.getAccount_name() 
				+ "\n|What would you like to do?                                                    "
				+ "\n|1) Withdraw from " + account.getAccount_name()); 
				System.out.print("\n|2) Deposit to " + account.getAccount_name());
				System.out.println("\n|3) Create new account                                                         |"
				+ "\n|4) View Balance                                                               |"
				+ "\n|5) Delete Account                                                             |"
				+ "\n|6) Exit to Main Menu                                                          |"
				+ "\n--------------------------------------------------------------------------------");
		try { //DONT USE SCANNER.NEXT INT!!!!!!
			choice = Integer.parseInt(App.scanner.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("Sorry, you must choose one of the 4 options: Please type '1','2','3', or '4' and then press enter)");
			accountOptions(c, account);
		}
		if( choice < 1 || choice > 6) {
			System.out.println("Sorry, you must choose one of the 4 options: Please type '1','2','3', or '4' and then press enter)");
			accountOptions(c, account);
		}
		switch(choice) {
		case 1:
			Transaction.withdraw(account);
			break;
		case 2:
			Transaction.deposit(account);
			break;
		case 3:
			CreateNewAccount.createAccount(c);
			break;
		case 4: 
			System.out.println("Your current balance in your " + account.getAccount_type() + " account called: " + account.getAccount_name() + " is: ");
			System.out.printf("%.2f\n", account.getBalance());
			accountOptions(c, account);
			break;
		case 5:
			if(account.getBalance() > 0.01) {
				System.out.println("You must withdraw all funds from your account before deletion may occur.");
				accountOptions(c, account);
			}
			else DeleteAccount.deleteAccount(account);
			break;
		case 6:
			MainMenu.mainMenu(c);
			break;
		}
	}
	
	public static void chooseAccount(List<BankAccount> accounts) {
		Customer c = new Customer();
		c = App.cService.getOne(accounts.get(0).getCustomer_id());
		BankAccount b = new BankAccount();
		int choice = 0;
		System.out.println("_________________________________________________________________________________");
		System.out.println("|_______________________" + accounts.get(0).getAccount_type().toUpperCase() + " ACCOUNT SELECTION SCREEN______________________|");
		System.out.println("|Which account would you like to access:                                        |");
		System.out.print("|1) " + accounts.get(0).getAccount_name());
		System.out.print("\n|2) " + accounts.get(1).getAccount_name());
		System.out.print("\n|3) Exit and return to account selection screen                                 |"
				+ "\n---------------------------------------------------------------------------------");
		
		try { //DONT USE SCANNER.NEXT INT!!!!!!
			choice = Integer.parseInt(App.scanner.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("Sorry, you must choose one of the 2 options: Please type '1','2', or '3' and then press enter)");
			chooseAccount(accounts);
		}
		if( choice < 1 || choice > 3) {
			System.out.println("Sorry, you must choose one of the 2 options: Please type '1','2', or '3' and then press enter)");
			chooseAccount(accounts);
		}
		switch(choice) {
		case 1:
			b = App.bService.getOne(accounts.get(0).getAccount_id());
			accountOptions(c,b );
			break;
		case 2:
			b = App.bService.getOne(accounts.get(1).getAccount_id());
			accountOptions(c,b );
			break;
		case 3:
			MainMenu.chooseTypeOfAccount(c,App.bService.getAll(accounts.get(0).getCustomer_id()));
			break;
		}
	}
}
