package com.bank.main;

import com.bank.exceptions.ContainsSpacesException;
import com.bank.exceptions.CustomExceptionMethods;
import com.bank.exceptions.NameTooLargeException;
import com.bank.exceptions.NonAlphaNumericalException;
import com.bank.pojos.Customer;

public class CreateNewCustomerAccount {

	public static void createNewCustomerAccount(int n, Customer c) {
		String newUN = null;
		String newPW = null;
		String newPW2 = null;
		String fn = null;
		String ln = null;
		
		switch(n) {
		case 1:
			System.out.println("Please type in a username (Can only contain letters and numbers. No spaces either.) ");
			try {
				newUN = App.scanner.nextLine();
				CustomExceptionMethods.checkForSpaces(newUN);
				CustomExceptionMethods.checkAlphaNumerical(newUN);
				CustomExceptionMethods.checkLargeName(newUN);
				
			}
			catch(ContainsSpacesException e) {
				System.out.println(e.getMessage());
				createNewCustomerAccount(n, c);
			}
			catch(NameTooLargeException e) {
				System.out.println(e.getMessage());
				createNewCustomerAccount(n, c);
			}
			catch(NonAlphaNumericalException e) {
				System.out.println(e.getMessage());
				createNewCustomerAccount(n, c);
			}
			if(newUN.toLowerCase().equals("exit")) {
				LoginScreen.loginScreen();
				break;
			}
			else if(Misc.checkIfUsernameExists(newUN)) {
				System.out.println("That username is already taken, please try entering another.");
				createNewCustomerAccount(n, c);
			}
			
			System.out.println("NICE NAME! Now enter a password (Please ensure that you remember it, it's important.");
			c.setUsername(Misc.quote(newUN.toLowerCase()));
			n++;
		case 2:
			try {
				newPW = App.scanner.nextLine();
				CustomExceptionMethods.checkForSpaces(newPW);
				CustomExceptionMethods.checkAlphaNumerical(newPW);
				CustomExceptionMethods.checkLargeName(newPW);
			}
			catch(ContainsSpacesException e) {
				System.out.println(e.getMessage());
				createNewCustomerAccount(n, c);
			}
			catch(NameTooLargeException e) {
				System.out.println(e.getMessage());
				createNewCustomerAccount(n, c);
			}
			catch(NonAlphaNumericalException e) {
				System.out.println(e.getMessage());
				createNewCustomerAccount(n, c);
			}
			if(newPW.toLowerCase().equals("exit")) {
				LoginScreen.loginScreen();
				break;
			}
			c.setPassword(Misc.quote(newPW));
		case 3:
			System.out.println("Please enter your new password again");
			newPW2 = App.scanner.nextLine();
			if(!Misc.quote(newPW2).equals(c.getPassword())) {
				System.out.println("The password you just entered is not the same as the one you previously entered, please start over and enter a password");
				createNewCustomerAccount(n, c);
			}
			else if(newPW2.toLowerCase().equals("exit")) {
				LoginScreen.loginScreen();
				break;
			}
			n = n + 2;
		case 4:
			System.out.println("Alright, you're almost done creating your new account. \nNow please enter you first name");
			try {
				fn = App.scanner.nextLine();
				CustomExceptionMethods.checkForSpaces(fn);
				CustomExceptionMethods.checkLargeName(fn);
			}
			catch(ContainsSpacesException e) {
				System.out.println(e.getMessage());
				createNewCustomerAccount(n, c);
			}
			catch(NameTooLargeException e) {
				System.out.println(e.getMessage());
				createNewCustomerAccount(n, c);
			}
			if (!fn.matches("[a-zA-Z]+")) {
				System.out.println("Names don't contain numbers. Please enter again with only letters");
				createNewCustomerAccount(n, c);
			}
			else if(fn.toLowerCase().equals("exit")) {
				LoginScreen.loginScreen();
				break;
			}
			c.setFirstname(fn);
			n++;
		case 5:
			System.out.println("All I need now is you last name and your account is complete!");
			try {
				ln = App.scanner.nextLine();
				CustomExceptionMethods.checkForSpaces(ln);
				CustomExceptionMethods.checkLargeName(ln);
			}
			catch(ContainsSpacesException e) {
				System.out.println(e.getMessage());
				createNewCustomerAccount(n, c);
			}
			catch(NameTooLargeException e) {
				System.out.println(e.getMessage());
				createNewCustomerAccount(n, c);
			}
			if (!ln.matches("[a-zA-Z]+")) {
				System.out.println("Names don't contain numbers. Please enter again with only letters");
				createNewCustomerAccount(n, c);
			}
			else if(ln.toLowerCase().equals("exit")) {
				LoginScreen.loginScreen();
				break;
			}
			c.setLastname(ln);
		}
		System.out.println("Yay, you're done! Time to make an account.");
		App.cService.save(c);
		CreateNewAccount.createAccount(c);
	}
	
}
