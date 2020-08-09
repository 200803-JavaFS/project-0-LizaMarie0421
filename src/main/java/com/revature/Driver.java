package com.revature;

import java.util.Scanner;

import com.revature.models.Admin;
import com.revature.models.Employee;
import com.revature.models.User;

public class Driver {

	public static void main(String[] args) {

		System.out.println("Welcome to the Revature Bank! \nLets get started !");
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Please type the number in order to select an action and then 'enter'\n");
		System.out.println("How would you like to start?"
				+ "\n[1]: Log into user account"
				+ "\n[2]: Create user acount"
				+ "\n[3]: Close application ");
		String action = scan.nextLine();
		int action1 = Integer.parseInt(action);

		switch(action1) {
		case 1:
			System.out.println("user wants to log into an existing account");
			//login class from service package
			break;
		case 2:
			System.out.println("User wants to create an account");
			break;
		case 3:
			System.out.println("User wants to close application");
			scan.close();
		}
		
		
		System.out.println("============Testing Methods display right================");

		User user1= new User();
		user1.name= "Liza";
		user1.address= "2425 wesley";
		
		user1.accessInfo(user1);
		
		System.out.println("============employee access================");
		Employee employee1= new Employee();
		employee1.accessInfo(user1);
		
		System.out.println("============Admin access================");
		Admin admin1 = new Admin();
		admin1.accessInfo(user1);
	}

}
