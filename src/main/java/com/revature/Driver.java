package com.revature;

import java.util.Random;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.Admin;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.service.AccountFunctionality;
import com.revature.service.Login;

public class Driver {

	public static void main(String[] args) {

		System.out.println("\nWelcome to Revature Bank! \nLets get started ! \n");
		boolean exit = false;
		whileLoop: while (exit != true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Please type the number in order to select an action and then 'enter'\n");
			System.out.println("_________________________________________________________________________");
			System.out.println("_________________________________________________________________________\n");

			System.out.println("How would you like to start?" + "\n[1]: Log into user account"
					+ "\n[2]: Create customer acount" + "\n[3]: Close application ");
			switchCase: {
				String action = scan.nextLine();
				int action1 = Integer.parseInt(action);
	
				switch (action1) {
				case 1:
					System.out.println("user wants to log into an existing account");
					Login.login();
					// login class from service package
					break;
				case 2:
					System.out.println("User wants to create an customer account");
					AccountFunctionality.createAccount();
					break;
				case 3:
					System.out.println("User wants to close application");
					scan.close();
					exit = true;
					break whileLoop;
				default:
					System.out.println("You did not enter a valid choice. Please try again!");
					break switchCase;
				}
			}
			System.out.println("_________________________________________________________________________");
			System.out.println("_________________________________________________________________________\n");

			
			/*
			 * System.out.println("======================testing account array List ================");
			 * AccountFunctionality.displayAccountList();
			 * 
			 * System.out.println("======================testing user array List ================");
			 * AccountFunctionality.displayUserList();
			 
			 

			System.out.println("_________________________________________________________________________");
			System.out.println("_________________________________________________________________________\n");
			 */ 
			System.out.println("Would you like to continue with another action? Y/N");
			String answer = scan.nextLine();
			if (answer.equals("N")) {
				scan.close();
				exit = true;
			} else if (answer.equals("Y")) {
				// change to continue to loop with deposit or exit or w.e
				continue whileLoop;
			}

		}
		System.out.println("Thank you for banking with Revature!!");
	}

}
