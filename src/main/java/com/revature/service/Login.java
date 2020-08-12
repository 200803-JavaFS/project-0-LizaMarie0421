package com.revature.service;

import java.util.Scanner;

public class Login {

	public static String username;
	public static String password;
	static Scanner s = new Scanner(System.in);

	public static void login() {
		System.out.println("Please enter in necessary information");
		
		
		
		
		System.out.println("What kind of user are you trying to log in as?"
				+ "\n[1]: Customer"
				+ "\n[2]: Employee"
				+ "\n[3]: Administrator");
		switchCase: {
			String action1 = s.nextLine();
			int action = Integer.parseInt(action1);
	
			switch (action) {
			case 1:
				System.out.println("User wants to log in as a Customer");
				loginAsCustomer();
				break;
			case 2:
				System.out.println("User wants to log in as an Employee");
				break;
			case 3:
				System.out.println("User wants to log in as an Administrator");
			default:
				System.out.println("You did not enter a valid choice. Please try again!");
				break switchCase;
			}
		}
	}
	
	public static void loginAsCustomer() {		
		System.out.println("Username: ");
		username= s.nextLine();
		System.out.println("Password: ");
		password= s.nextLine();
		//if username and password are in customer database
		//search database for correct PK FK
		//then they logged in currectly 
		System.out.println("");
	}
	
}
