package com.revature.utils;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.Driver;
import com.revature.daos.AccountDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AccountServices;
import com.revature.services.UserServices;

public class ConsoleUserUtil {

	private static final Logger log = LogManager.getLogger(Driver.class);
	private AccountServices as= new AccountServices();
	private UserServices us= new UserServices();
	
	private UserDAO ud= new UserDAO();
	private AccountDAO ad = new AccountDAO();
	
	private static final Scanner scan = new Scanner(System.in);

	public void beginApp() {
		log.info("The application has started");

		System.out.println("Please type the number in order to select an action and then 'enter'\n");
		System.out.println("_________________________________________________________________________");
		System.out.println("_________________________________________________________________________\n");

		System.out.println("How would you like to start?" + "\n[1]: Log into user account"
				+ "\n[2]: Create customer acount" + "\n[3]: Close application ");
		
		String action = scan.nextLine();
		actionSwitch(action);		
	}

	private void actionSwitch(String action) {
		int actionResult= Integer.parseInt(action);
		switch (actionResult) {
		case 1:
			System.out.println("user wants to log into an existing account");
			login();
			break;
		case 2:
			System.out.println("User wants to create a new customer account");
			createAccountScratch();
			break;
		case 3:
			System.out.println("User wants to close application");
			System.out.println("Thank you for banking with Revature!!");
			System.exit(0);
			break;
		default:
			System.out.println("You did not enter a valid choice. Please try again!");
			beginApp();
			break;
		}
	}

	private void login() {
		User u= null;
		System.out.println("Please enter in necessary information");
		System.out.println("Username: ");
		String username= scan.nextLine();
		
		System.out.println("Password: ");
		String password= scan.nextLine();
		
		u= ud.findByUserPassword(username, password);
		
		if (us.findByUserPassword(username, password)!=null) {
			System.out.println("Login Successful");
			identityUserType(u);
			//makeTransaction();
		}
		else {
			System.out.println("Login failed. Did not find user");
			beginApp();
		}

		
	}


	private void identityUserType(User u) {
		String type = u.getType();
		System.out.println("User is logging in as a: "+ type);
		if (type.equals("Customer")) {
			makeTrasnsactionCustomer();
		}
		else if (type.equals("Employee")) {
			//makeTransactionEmployee();
		}
		else if (type.equals("Admin")) {
			//makeTransaction Employee();
		}
		else {
			System.out.println("You did not enter a valid choice. Please try again!");
		}
	}

	private void makeTrasnsactionCustomer() {
		System.out.println("What would you like to do? Enter Number and hit 'enter'");
		System.out.println("[1] Manage Existing account(s)\n"
				+ "[2] Create new account\n"
				+ "[3] Edit personal information\n"
				+ "[4] Close Application");
		String action = scan.nextLine();
		int actionInt = Integer.parseInt(action);
		if (actionInt==1) {
			System.out.println("User wants to manage account(s)");
			manageAccounts();
		}else if (actionInt ==2) {
			System.out.println("User wants to create new account");
			createAccount();
		}else if (actionInt ==3) {
			System.out.println("User wants to edit personal info.");
			editUserInfo();
		}else if (actionInt ==4) {
			System.out.println("User wants to close application");
			System.out.println("Thank you for banking with Revature!!");
		}
		

	}

	private void editUserInfo() {
		System.out.println("Enter username againg: ");
		String username = scan.nextLine();
		System.out.println("Here is the user information we have for you: ");
		User u = ud.findByUsername(username);
		System.out.println(u);
		
	}

	private void createAccount() {
		
		System.out.println("Confirm User Exists please enter\n"
				+ "Username: ");
		String username = scan.nextLine();
		System.out.println("Password:");
		String password = scan.nextLine();
		User u= ud.findByUserPassword(username, password);
		
		System.out.println("Enter balance you want your Account to start off with: "); 
		String balance= scan.nextLine(); 
		double balanceDouble = Double.parseDouble(balance);
		
		System.out.println("Your account status will be 'pending' until an Admin approves it");
		
		
		Account a = new Account("Pending", balanceDouble, u);
		
		if (as.insertAccount(a)) {
			System.out.println("Account was added to database ");
		}else {
			System.out.println("Account not added: Something went wrong please try again");
			beginApp();
		}
		
	}

	private void manageAccounts() {
		System.out.println("What account would you like to manage? Enter Account Number: ");
		int accountNumber= scan.nextInt();
		scan.nextLine();
		Account a =as.findByAccountNumber(accountNumber);
		
		System.out.println("What transaction would you like to complete?");
		System.out.println("[D] Make a Deposit\n"
				+ "[W] Make a Withdrawal\n"
				+ "[T] Make a Transfer\n"
				+ "[E] Exit ");
		String transaction = scan.nextLine();
		//scan.nextLine();
		if (transaction.toLowerCase().equals("d")) {
			System.out.println("Customer would like to make a Deposit.");
			deposit(a);
		} else if (transaction.toLowerCase().equals("w")) {
			System.out.println("Customer would like to make a Withdrawal");
			withdraw(a);
		} else if (transaction.toLowerCase().equals("t")) {
			System.out.println("Customer would like to make a Transaction");
			transfer(a);
		} else if (transaction.toLowerCase().equals("e")){
			System.out.println("exit...");
		}else {
			System.out.println("You did not enter a valid choice. Please try again!");
		}
		makeTrasnsactionCustomer();
	}

	private void transfer(Account a) {
		System.out.println("What account would you like to transfer amount to? Enter Account Number: ");
		String account= scan.nextLine(); 
		int accountNumber = Integer.parseInt(account);
		System.out.println("How much would you like to transfer: ");
		String transferAmount= scan.nextLine(); 
		double transferAmountDouble = Double.parseDouble(transferAmount);
		
		Account otherAccount = as.findByAccountNumber(accountNumber);
		
		double ogAccountBalance = otherAccount.getBalance();
		double newAccountBalance = ogAccountBalance + transferAmountDouble;
		otherAccount.setBalance(newAccountBalance);

		double ogAccountBalance2 = a.getBalance();
		double newAccountBalance2 = ogAccountBalance2 - transferAmountDouble;
		a.setBalance(newAccountBalance2);
		
	}

	private void withdraw(Account a) {
		System.out.println("How much would you like to withdraw: ");
		String withdraw= scan.nextLine(); 
		double withdrawDouble = Double.parseDouble(withdraw);
		double oldBalance = a.getBalance();
		a.setBalance(oldBalance-withdrawDouble);
		as.updateAccount(a);
		
		if (ad.updateAccount(a)) {
			System.out.println("Your account balance has been updated");
		}else {
			System.out.println("Something went wrong. Deposit was not complete please try again!");
			makeTrasnsactionCustomer();
		}
		
	}

	private void deposit(Account a) {
		System.out.println("How much would you like to deposit: ");
		String deposit= scan.nextLine(); 
		double depositDouble = Double.parseDouble(deposit);
		
		double oldBalance = a.getBalance();
		a.setBalance(oldBalance+depositDouble);
		as.updateAccount(a);
		
		if (ad.updateAccount(a)) {
			System.out.println("Your account balance has been updated");
		}else {
			System.out.println("Something went wrong. Deposit was not complete please try again!");
			makeTrasnsactionCustomer();
		}
		
		
	}

	private void createAccountScratch() {
		
		User u= createUser();
		System.out.println("Enter balance you want your Account to start off with: "); 
		String balance= scan.nextLine(); 
		double balanceDouble = Double.parseDouble(balance);
		
		System.out.println("Your account status will be 'pending' until an Admin approves it");
		
		
		
		Account a = new Account("Pending", balanceDouble, u);
		
		if (as.insertAccount(a)) {
			System.out.println("Account was added to database ");
			beginApp();
		}else {
			System.out.println("Account not added: Something went wrong please try again");
			beginApp();
		}
		
	}

	private User findUser() {
		System.out.println("Does your User already exist already exist in database?\n"
				+ "if so, enter name of the username. \n"
				+ "if not enter 'no'");
		String response = scan.nextLine();
		
		User u = null;
		if (response.toLowerCase().equals("no")) {
			u = createUser();
		}else {
			u= ud.findByUsername(response);
		}
		return u;
	}

	private User createUser() {
		System.out.println("Please enter in necessary information");
		System.out.println("Name (first, last): ");
		String name[] = scan.nextLine().split(" ");
		System.out.println("size of array name: "+ name.length); 
		String first= name[0];
		String last= name[1];
		
		System.out.println("Do you want to add a phone number? Y/N"); 
		String answer = scan.nextLine(); 
		
		String phoneNumber;
		if (answer.equals("Y")) {
			System.out.println("Enter phone number: ");
			phoneNumber = scan.nextLine(); 
		}
		else {
			System.out.println("User does not want to enter phone number");
			phoneNumber = "0";
		}
		System.out.println("Set up a username: ");
		String username = scan.nextLine();
		System.out.println("Set up a password: "); 
		String password = scan.nextLine();
		User u = new User("Customer", first, last, phoneNumber, username, password);
		
		if (us.insertUser(u)) {
			System.out.println("User was added to database ");
		}else {
			System.out.println("User not added: Something went wrong please try again");
		}
		return ud.findByUsername(username);
	}
	
	
}