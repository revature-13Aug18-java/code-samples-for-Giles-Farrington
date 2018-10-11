package com.bank.main;

import com.bank.pojos.Customer;

public class Login {

	static void enterUsername() {
		String option = null;
		
		System.out.println("Enter your username: ");
		option = App.scanner.nextLine();
		option = Misc.quote(option);
		if(CheckInfo.checkExistingUsernames(option)) {
			System.out.println("Username found");
			enterPassword(App.cService.getFromString(option));
		}
		else if(option.toLowerCase().equals("'exit'")) {
			LoginScreen.loginScreen();
		}
		else {
			System.out.println("Username not found, please try again.");
			enterUsername();
		}
	}
	
	static void enterPassword(Customer c) {
		String pswrdAttempt = null;
	
		System.out.println("Now please enter your password: ");
		pswrdAttempt = App.scanner.nextLine();
		if(Misc.quote(pswrdAttempt).equals(c.getPassword())) {
			System.out.println("Credentials correct, logging in..");
		}
		else {
			System.out.println("Password incorrect, please try again.");
			enterPassword(c);
		}
		if(pswrdAttempt.toLowerCase().equals("exit")) {
			LoginScreen.loginScreen();
		}
		else MainMenu.mainMenu(c);
	}
}
