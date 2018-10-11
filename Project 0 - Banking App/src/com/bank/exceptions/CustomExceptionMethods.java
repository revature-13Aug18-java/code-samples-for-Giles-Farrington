package com.bank.exceptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomExceptionMethods {

	public static void checkForSpaces(String str) throws ContainsSpacesException {
		Pattern pattern = Pattern.compile("\\s");
		Matcher matcher = pattern.matcher(str);
		if (matcher.find())
			throw new ContainsSpacesException("Your input contains spaces, please enter again without spaces.");
		
	}
	
	public static void checkLargeName(String str) throws NameTooLargeException {
		if(str.length() > 50) 
			throw new NameTooLargeException("The name you entered is way too long to be a name, please enter your real name");
		
	}
	
	public static void checkAlphaNumerical(String str) throws NonAlphaNumericalException {
		if (!str.matches("[a-zA-Z0-9]+"))
			throw new NonAlphaNumericalException("Please only enter letters and numbers and try again.");
	}
}
