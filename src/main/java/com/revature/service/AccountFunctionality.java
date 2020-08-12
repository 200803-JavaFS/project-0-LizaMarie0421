package com.revature.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.User;

public class AccountFunctionality {
	static Map<Integer, Account>accountList= new HashMap<>();
	static Map<String, User>userList= new HashMap<>();
	public static void addToAccountList(int accountNumber, Account account) {
		
		accountList.put(accountNumber,account);
	}
	public static void displayAccountList() {
		Iterator it= accountList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry singleAccount = (Map.Entry)it.next();
	        System.out.println(singleAccount.getKey() + " = " + singleAccount.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
		
	}
	
	public static void addToUserList(String username, User user) {
		
		userList.put(username,user);
	}
	public static void displayUserList() {
		Iterator it= userList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry singleAccount = (Map.Entry)it.next();
	        System.out.println(singleAccount.getKey() + " = " + singleAccount.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
		
	}
	public static void createAccount() {
		User user= new User();
		
		Scanner iscan = new Scanner(System.in);
	
		System.out.println("Please enter in necessary information");
		System.out.println("Name: ");
		String name = iscan.nextLine();
		user.name= name;
		System.out.println("Address: ");
		String address = iscan.nextLine();
		user.address= address;		
		
		System.out.println("Do you want to add a phone number? Y/N");
		String bool = iscan.nextLine();
		if (bool.equals("Y")) {
			System.out.println("Enter phone number: ");
			String phoneNumber = iscan.nextLine();
			user.phone = phoneNumber;
			
		}			
		System.out.println("Set up a username: ");
		String username = iscan.nextLine();
		System.out.println("Set up a password: ");
		String password = iscan.nextLine();
		
		addToUserList(username, user);
		System.out.println("Thank you for making an account with here is your current account information");
		Random rand= new Random();
		int upperbound=100;
		int accountNumber= rand.nextInt(upperbound);
		Account account= new Account(user, accountNumber, 0.0, username);
		
		addToAccountList(accountNumber,account);
		UserFunctionality.displayAccountInfo(account);
		
		System.out.println("Each account will start with a balance of 0. Would you like to deposit money? Y/N");
		String bool2= iscan.nextLine();
		if (bool2.equals("Y")) {
			System.out.println("Enter amount you would like to deposit : ");
			String s= iscan.nextLine();
			double depositAmount = Double.parseDouble(s);
			UserFunctionality.deposit(account,depositAmount);
			UserFunctionality.displayAccountInfo(account);
		}	
		else {
			System.out.println("Continuee...");
		}
	}
}
