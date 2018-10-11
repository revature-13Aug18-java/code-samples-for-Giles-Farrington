package com.bank.main;

import com.bank.pojos.BankAccount;
import com.bank.pojos.Customer;

public class DeleteAccount {

	static void deleteAccount(BankAccount acc) {
		int choice = 0;
		Customer c = new Customer();
		c = App.cService.getOne(acc.getCustomer_id());
		System.out.println("Are you POSITIVE that you want to DELETE this account?!"
				+ "\n1) YES"
				+ "\n2) NO");
		try { //DONT USE SCANNER.NEXT INT!!!!!!
			choice = Integer.parseInt(App.scanner.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("Sorry, you must choose one of the 2 options: Please type '1' or '2' and then press enter)");
			deleteAccount(acc);
		}
		if(choice != 1 && choice != 2) {
			System.out.println("Sorry, you must choose one of the 2 options: Please type '1' or '2' and then press enter)");
			deleteAccount(acc);
		}
		switch(choice) {
		case 1: 
			App.bService.delete(acc.getAccount_id());
			System.out.println("Account successfully deleted, returning to main menu...");
			MainMenu.mainMenu(c);
			break;
		case 2:
			System.out.println("Account has not been deleted, returning to main menu...");
			MainMenu.mainMenu(c);
			break;
		}
	}
}
