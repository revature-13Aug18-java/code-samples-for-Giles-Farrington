package com.bank.main;

import java.util.List;


import com.bank.pojos.Customer;

public class Misc {

	public static String quote(String s) {
	    return new StringBuilder()
	        .append('\'')
	        .append(s)
	        .append('\'')
	        .toString();
	}
	
	static void viewCustomers() {
		List<Customer> customers = App.cService.getAll();
		for(Customer c : customers) {
			System.out.println(c.getUsername());
		}
	}
	static boolean checkIfUsernameExists(String name) {
		List<Customer> customers = App.cService.getAll();
		for(Customer c : customers) {
			if (quote(name).equals(c.getUsername()))
				return true;
		}
		return false;
	}
}
