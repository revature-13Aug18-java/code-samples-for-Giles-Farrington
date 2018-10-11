package com.bank.main;

import java.util.Scanner;
import com.bank.service.BankAccountService;
import com.bank.service.CustomerService;



public class App {
	
	static Scanner scanner = new Scanner(System.in);
	static CustomerService cService = new CustomerService();
	static BankAccountService bService = new BankAccountService();

	public static void main(String[] args) {
		System.out.println("Welcome to Bank$mart! We ensure all of your imaginary banking needs are taken care of. \nWhat can we do for you today?");
		LoginScreen.loginScreen();
		
	}
}
