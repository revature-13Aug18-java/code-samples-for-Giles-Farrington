package com.bank.main;

import com.bank.pojos.Customer;

public class LoginScreen {

	static void loginScreen() {
		System.out.println("\nNOTE: Whenever you are asked to type in a string of characters and NOT a numbered menu choice OR "
				+ "\nnumber value response(such as amounts of withdrawals or deposits), you may type 'exit' to return to this screen."
				+ "\n\nNumbered menu choice prompts will always have a number to enter if you wish to exit and return to a previous menu. "
				+ "\nEnter in that number NOT 'exit' (on this menu it is '3').");
		System.out.println(" \n___________________________________________________________________");
		System.out.println("|_________________________LOG-IN SCREEN____________________________|");
		System.out.println("|1) Create New Account   			                   |\n"
						 + "|2) Log-in to Existing Account                                     |\n"								   
				         + "|3) Exit Application                                               |\n"
				         + "--------------------------------------------------------------------");
		int option = 0;
		try { //DONT USE SCANNER.NEXT INT!!!!!!
			option = Integer.parseInt(App.scanner.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("Sorry, you must choose one of the 3options: Please type '1','2', or '3' and then press enter)");
			loginScreen();
		}
		if(option < 1 || option > 3) {
			System.out.println("Sorry, you must choose one of the 3 options: Please type '1','2', or '3' and then press enter)");
			loginScreen();
		}
		switch(option) {
		case 1: 
			Customer c = new Customer();
			CreateNewCustomerAccount.createNewCustomerAccount(1, c);
			break;
		case 2:
			Login.enterUsername();
			break;
		case 3:
			System.out.println("Exiting application... Thanks for using Bank$mart and we hope to see you again!");
			System.exit(0);
			break;
		}
	}
	
}
