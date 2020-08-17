package com.revature.utils;

import java.util.InputMismatchException;
import java.util.List;
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

		System.out.println("How would you like to start?" + "\n[1]: Log In"
				+ "\n[2]: Create customer account" + "\n[3]: Close application ");
		
		String action = scan.nextLine();
		actionSwitch(action);		
	}

	private void actionSwitch(String action) {
		int actionResult=0;
		try {
			actionResult= Integer.parseInt(action);
		}catch(NumberFormatException e) {
			System.out.println("Please enter a numeric value!");
			beginApp();
		}
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
			checkUserHasAccount(u.getId());
			//checkStatus();
			makeTrasnsactionCustomer();
		}
		else if (type.equals("Admin")) {
			makeTransactionAdmin();
		}else if (type.equals("Employee")) {
			makeTransactionEmployee();
		}
	}

	private void makeTransactionEmployee() {
		System.out.println("What would you like to do? Enter Number and hit 'enter'");
		System.out.println("[1] View customer information\n"
				+ "[2] View an existing account\n"
				+ "[3] Change status of an account\n"
				+ "[4] Close Application");
				
		String action = scan.nextLine();
		int actionInt=0;
		try {
			actionInt= Integer.parseInt(action);
		}catch(NumberFormatException e) {
			System.out.println("Please enter a numeric value!");
			makeTransactionEmployee();
		}
		if (actionInt==1) {
			System.out.println("Employee wants to view customer info");
			diplayUsers();
			viewUserInfo();
		}else if (actionInt ==2) {
			System.out.println("Employee wants to view an existing account");
			displayAccounts();
			viewAccountInfo();
		}else if (actionInt ==3) {
			System.out.println("Employee wants to change the status of an account");
			displayAccounts();
			System.out.println("Enter the Account Number of account: ");
			int accountNumber= scan.nextInt();
			scan.nextLine();
			Account a =as.findByAccountNumber(accountNumber);
			String status = a.getApprovalStatus();
			changeStatus(a);
			as.updateAccount(a);
		}else if (actionInt==4) {
			closeApp();
		}else {
			System.out.println("Enter a valid number. Try Again!");
		}
		makeTransactionEmployee();
		
	}

	private void diplayUsers() {
		List<User> list = us.findAll();
		for (int i = 0; i <list.size();i++) {
			User user= list.get(i);
			if(user.getType().equals("Customer")) {
				System.out.println(user);
			}
		}
		
	}

	private void viewAccountInfo() {
		label: {
		System.out.println("Enter account number of the account you would like to view: ");
		String accountNumberScan= scan.nextLine();
		int accountNumber=0;
			try{
				accountNumber= Integer.parseInt(accountNumberScan);
			}catch (NumberFormatException e) {
				System.out.println("Please enter a numeric value!");
				viewAccountInfo();
			}finally {
			switch (accountNumber) {
				case 0:
					break label;
			}
		}
		Account a=null;
		try {
			a =as.findByAccountNumber(accountNumber);
		}
		catch(NullPointerException e) {
			System.out.println("Account Number does not exist. Try Again");
			manageAccounts();
		}
		System.out.println(a);
	}
	}

	private void viewUserInfo() {
		label: {
		System.out.println("Enter user id of the user you would like to view: ");
		String userIdScan= scan.nextLine();
		int userID=0;
		try{
			userID= Integer.parseInt(userIdScan);
		}catch (NumberFormatException e) {
			System.out.println("Please enter a numeric value!");
			viewUserInfo();
		}finally {
			switch (userID) {
				case 0:
					break label;
			}
		}
		User u = us.findById(userID);
		System.out.println(u);
	}
	}

	private void makeTransactionAdmin() {
		System.out.println("What would you like to do? Enter Number and hit 'enter'");
		System.out.println("[1] Manage Existing account(s)\n"
				+ "[2] Create new account\n"
				+ "[3] Edit Customer information\n"
				+ "[4] Close an existing account\n"
				+ "[5] Close Application");
				
		String action = scan.nextLine();
		int actionInt = 0;
		label: {
				try {
					actionInt= Integer.parseInt(action);
				}catch(NumberFormatException e) {
					System.out.println("Please enter a numeric value!");
					makeTransactionAdmin();
				}
				finally {
					switch (actionInt) {
						case 0:
							break label;
					}
				}
		if (actionInt==1) {
			System.out.println("Admin wants to manage account(s)");
			displayAccounts();
			checkStatus("Admin");
			manageAccounts();
		}else if (actionInt ==2) {
			System.out.println("Admin wants to add an account");
			findUser();
		}else if (actionInt ==3) {
			System.out.println("Admin wants to edit personal info.");
			diplayUsers();
			editUserInforAsAdmin();
			makeTransactionAdmin();
		}else if (actionInt==4) {
			System.out.println("Admin wants to close an existing account");
			closeAccount();
		}else if (actionInt ==5) {
			closeApp();
		}else {
			System.out.println("You did not enter a valid choice. Please try again!");
		}
		makeTransactionAdmin();
	}}

	private void displayAccounts() {
		List<Account> list = as.findAll();
		for (int i = 0; i <list.size();i++) {
			Account account= list.get(i);
			System.out.println(account);
		}
	}

	private void editUserInforAsAdmin() {
		System.out.println("Enter username of the user information you would like to manage: ");
		String username = scan.nextLine();
		
		User u = ud.findByUsername(username);
		if(u==null) {
			System.out.println("User does not exist!");
			makeTransactionAdmin();
		}
		System.out.println("Here is the user information we have for the user with username: "+username+ ": ");
		System.out.println(u);
		
		System.out.println("What information would you like to edit (enter number and hit 'Enter')");
		System.out.println("[1] Edit First Name\n"
				+ "[2] Edit Last Name\n"
				+ "[3] Change or add phone number\n"
				+ "[4] Change username\n"
				+ "[5] Change password");

		int answer = scan.nextInt();
		scan.nextLine();
		if (answer==1) {
			changeUserName(u);
		}else if (answer==2) {
			changeUserLastName(u);
		}else if (answer==3) {
			changeUserPhone(u);
		}else if (answer==4) {
			changeUserUsername(u);
		}else if (answer==5) {
			changeUserPassword(u);
		}else {
			System.out.println("You did not enter a valid choice. Please try again!");
			makeTransactionAdmin();
		}
		
		System.out.println("This is the updated user info:");
		ud.updateUser(u);
		System.out.println(u);
		
	}

	private void closeAccount() {
		System.out.println("Enter account number that you would like to close: ");
		int accountNumber = scan.nextInt();
		scan.nextLine();
		Account a=null;
		try {
			a =as.findByAccountNumber(accountNumber);
		}
		catch(NullPointerException e) {
			System.out.println("Account Number does not exist. Try Again");
			closeAccount();
		}
		a.setApprovalStatus("Closed");
		as.updateAccount(a);
		
	}

	private void makeTrasnsactionCustomer() {
		System.out.println("What would you like to do? Enter Number and hit 'enter'");
		System.out.println("[1] Manage Existing account(s)\n"
				+ "[2] Create new account\n"
				+ "[3] Edit personal information\n"
				+ "[4] Close Application");
		String action = scan.nextLine();
		int actionInt =0;
		label:{
		try {
			actionInt= Integer.parseInt(action);
		}catch(NumberFormatException e) {
			System.out.println("Please enter a numeric value!");
			makeTrasnsactionCustomer();
		}finally {
			switch (actionInt) {
			case 0:
				break label;
		}
	}
		if (actionInt==1) {
			System.out.println("User wants to manage account(s)");
			checkStatus("Customer");
			manageAccounts();
		}else if (actionInt ==2) {
			System.out.println("User wants to create new account");
			createAccount();
		}else if (actionInt ==3) {
			System.out.println("User wants to edit personal info.");
			editUserInfo();
		}else if (actionInt ==4) {
			closeApp();
		}else {
			System.out.println("You did not enter a valid choice. Please try again!");
		}
		makeTrasnsactionCustomer();
		
		}
	}

	private void checkUserHasAccount(int userId) {
		List<Account> list =as.findByUser(userId);
		System.out.println("User has " +list.size()+ " account(s)");
		if (list.isEmpty()) {
			System.out.println("You have no accounts. Try again!");
			beginApp();
		}
		else {
			System.out.println("You can manage any of these accounts");
			for(int i = 0; i < list.size(); i++) {
	            System.out.println("Account Number: "+ list.get(i).getAccountNumber()+ " with a balance of "+ list.get(i).getBalance() );
	        }
		}
	}

	private void closeApp() {
		System.out.println("User wants to close application");
		System.out.println("Thank you for banking with Revature!!");
		System.exit(0);
		
	}

	private void editUserInfo() {
		System.out.println("Enter username of the user information you would like to manage: ");
		String username = scan.nextLine();
		
		User u = ud.findByUsername(username);
		if(u==null) {
			System.out.println("User does not exist!");
			manageAccounts();
		}
		System.out.println("Here is the user information we have for the user with username: "+username+ ": ");
		System.out.println(u);
		
		System.out.println("What information would you like to edit (enter number and hit 'Enter')");
		System.out.println("[1] Edit First Name\n"
				+ "[2] Edit Last Name\n"
				+ "[3] Change or add phone number\n"
				+ "[4] Change username\n"
				+ "[5] Change password");

		int answer = scan.nextInt();
		scan.nextLine();
		if (answer==1) {
			changeUserName(u);
		}else if (answer==2) {
			changeUserLastName(u);
		}else if (answer==3) {
			changeUserPhone(u);
		}else if (answer==4) {
			changeUserUsername(u);
		}else if (answer==5) {
			changeUserPassword(u);
		}else {
			System.out.println("You did not enter a valid choice. Please try again!");
			editUserInfo();
		}
		
		System.out.println("This is the new information we have for you:");
		ud.updateUser(u);
		System.out.println(u);
		
	}

	private void changeUserPassword(User u) {
		System.out.println("Enter new password: ");
		String newPassword = scan.nextLine();
		u.setPassword(newPassword);
	}

	private void changeUserUsername(User u) {
		System.out.println("Enter new username: ");
		String newUserName = scan.nextLine();
		u.setUsername(newUserName);
	}

	private void changeUserPhone(User u) {
		System.out.println("Enter new phone number: ");		
		String newPhone = scan.nextLine();
		u.setPhone(newPhone);
	}

	private void changeUserLastName(User u) {
		System.out.println("Enter last name you would like to change to: ");	
		String newName = scan.nextLine();
		u.setLast(newName);
	}

	private void changeUserName(User u) {
		System.out.println("Enter name you would like to change to: ");
		String newName = scan.nextLine();
		u.setFirst(newName);
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
		System.out.println("Your account number is: "+ a.getAccountNumber());
	}

	private void manageAccounts() {
		System.out.println("What account would you like to manage? Enter Account Number: ");
		int accountNumber= scan.nextInt();
		scan.nextLine();
		Account a=null;
		try {
			a =as.findByAccountNumber(accountNumber);
		
		
			System.out.println("What transaction would you like to complete?");
			System.out.println("[D] Make a Deposit\n"
					+ "[W] Make a Withdrawal\n"
					+ "[T] Make a Transfer\n"
					+ "[B] See current balance\n"
					+ "[E] Exit ");		
			String transaction = scan.nextLine();
			String typeOfUser = a.getUser().getType();
			if (transaction.toLowerCase().equals("d")) {
				System.out.println(typeOfUser+ " would like to make a Deposit.");
				deposit(a);
			} else if (transaction.toLowerCase().equals("w")) {
				System.out.println(typeOfUser+ " would like to make a Withdrawal");
				withdraw(a);
			} else if (transaction.toLowerCase().equals("t")) {
				System.out.println(typeOfUser+ " would like to make a Transaction");
				transfer(a);
			}else if (transaction.toLowerCase().equals("b")) {
				System.out.println(typeOfUser+ " would like to see current balance");
				System.out.println("Current balance of account "+ accountNumber+ " is "+ a.getBalance());
				beginApp();
			}else if (transaction.toLowerCase().equals("e")){
				System.out.println("exit...");
			}else {
				System.out.println("You did not enter a valid choice. Please try again!");
				manageAccounts();
			}
		}
		catch(NullPointerException e) {
			System.out.println("Account Number does not exist. Try Again");
			beginApp();
		}
	}

	private void changeStatus(Account a) {
		String status = a.getApprovalStatus();
		System.out.println("Would you like to [A] Approve account or [D] Deny account");
		String newStatus= scan.nextLine();
		if (newStatus.toLowerCase().equals("a")) {
			a.setApprovalStatus("Approved");
		}else if (newStatus.toLowerCase().equals("d")) {
			a.setApprovalStatus("Denied");
		}
		
		
	}

	private void checkStatus(String type) {
		System.out.println("Let's Confirm the status of the account. Enter Account Number: ");
		int accountNumber= scan.nextInt();
		scan.nextLine();
		
		try {
			Account a;
			a =as.findByAccountNumber(accountNumber);
		
		
		String status = a.getApprovalStatus();
		
		System.out.println("Account Status for account "+ a.getAccountNumber()+ " is " + status);
		
		if (a.getApprovalStatus().equals("Pending")&& type.equals("Customer")) {
			System.out.println("Account status is pending please wait til your account is approved");
			beginApp();
		}else if (a.getApprovalStatus().equals("Pending")&& (type.equals("Admin") || type.equals("Employee"))) {
			System.out.println("Status of account needs to change before you can manage it");
			System.out.println("Would you like to change status of account?");
			String answer = scan.nextLine();
			if (answer.toLowerCase().equals("yes")) {
				changeStatus(a);
				ad.updateAccount(a);
				makeTransactionAdmin();
			}else {
				makeTransactionAdmin();
			}
		}else if (a.getApprovalStatus().equals("Closed")) {
			System.out.println("Cannot manage a closed account. Please try again");
			beginApp();
		}else if (a.getApprovalStatus().equals("Denied")) {
			System.out.println("Account was denied. Sorry, try again");
			if(type.equals("Admin")){
				makeTransactionAdmin();
			}else if (type.equals("Employee")) {
				makeTransactionEmployee();
			}else {
				makeTrasnsactionCustomer();
			}
		}}
		catch(NullPointerException e) {
			System.out.println("Account Number does not exist. Try Again");
			manageAccounts();
		}
	}


	public void transfer(Account a) {
		label: {
			System.out.println("What account would you like to transfer amount to? Enter Account Number: ");
			String account= scan.nextLine(); 
			int accountNumber=0;
			try {
				accountNumber= Integer.parseInt(account);
			}catch(NumberFormatException e) {
				System.out.println("Please enter a numeric value!");
				transfer(a);
			}finally {
				switch (accountNumber) {
					case 0:
						break label;
				}
			}
			try {
				System.out.println("How much would you like to transfer: ");
				String transferAmount= scan.nextLine(); 
				
				double transferAmountDouble = Double.parseDouble(transferAmount);
				Account otherAccount;
				
					otherAccount = as.findByAccountNumber(accountNumber);
				
				as.transfer(a, otherAccount, transferAmountDouble);
//				//get the balance of the account you want to transfer to and increase it 
//				double ogOtherAccountBalance = otherAccount.getBalance();
//				double newOtherAccountBalance = ogOtherAccountBalance + transferAmountDouble;
//				
//				
//				
//				//get balance of account that you are withdrawing from to transfer
//				double ogAccountBalance = a.getBalance();
//				double newAccountBalance = ogAccountBalance- transferAmountDouble;
//				
				if(transferAmountDouble<0||transferAmountDouble==0) {
					System.out.println("You cannot transfer a negative number or transfer 0 dollars. Try again!");
					transfer(a);
				}else if (transferAmountDouble>a.getBalance()) {
					System.out.println("You cannot overdraw your account. Select a smaller amount to withdraw");
					transfer(a);
				}
//				
//				otherAccount.setBalance(newOtherAccountBalance);
//				a.setBalance(newAccountBalance);
				
				
//				as.updateAccount(otherAccount);
//				as.updateAccount(a);
			}
			catch(NullPointerException e) {
				System.out.println("Account Number does not exist. Try Again");
				break label;
			}
		}
	}

	private void withdraw(Account a) {
		label:{
			System.out.println("How much would you like to withdraw: ");
			String withdraw= scan.nextLine(); 
			double withdrawDouble=0;
			try{
				withdrawDouble= Double.parseDouble(withdraw);
			}catch (NumberFormatException e) {
				System.out.println("Please enter a numeric value!");
				withdraw(a);
			}finally {
				if(withdrawDouble==0) {
					System.out.println("You cannot deposit a negative number or withdraw 0 dollars. Try again!");
					break label;
				}
			}
			
			
			if(withdrawDouble<0|| withdrawDouble==0) {
				System.out.println("You cannot deposit a negative number or withdraw 0 dollars. Try again!");
				break label;
			}else if (withdrawDouble> a.getBalance()) {
				System.out.println("You cannot overdraw your account. Select a smaller amount to withdraw");
				break label;
			}
			
			as.withdraw(a, withdrawDouble);
			
			if (ad.updateAccount(a)) {
				System.out.println("Your account balance has been updated");
			}else {
				System.out.println("Something went wrong. Deposit was not complete please try again!");
				makeTrasnsactionCustomer();
			}
		}
	}

	private void deposit(Account a) {
		label: {
			System.out.println("How much would you like to deposit: ");
			String deposit= scan.nextLine(); 
			//double depositDouble = Double.parseDouble(deposit);
			
			double depositDouble=0;
			try{
				depositDouble= Double.parseDouble(deposit);
			}catch (NumberFormatException e) {
				System.out.println("Please enter a numeric value!");
				deposit(a);
			}finally {
				if(depositDouble==0 || depositDouble <0) {
					System.out.println("You cannot deposit a negative number or deposit 0 dollars. Try again!");
					break label;
				}
			}
			
			as.deposit(a, depositDouble);
			
			
			if (ad.updateAccount(a)) {
				System.out.println("Your account balance has been updated");
			}else {
				System.out.println("Something went wrong. Deposit was not complete please try again!");
				makeTrasnsactionCustomer();
			}		
		}
	}

	private void createAccountScratch() {
		label:{
			User u= createUser();
			System.out.println("Enter balance you want your Account to start off with: "); 
			String balance= scan.nextLine(); 
			//double balanceDouble = Double.parseDouble(balance);
			
			double balanceDouble=0;
			try{
				balanceDouble= Double.parseDouble(balance);
			}catch (NumberFormatException e) {
				System.out.println("Please enter a numeric value!");
				createAccountScratch();
			}finally {
				if(balanceDouble==0) {
					break label;
				}
			}
			
			System.out.println("Your account status will be 'pending' until an Admin approves it");
			
			Account a = new Account("Pending", balanceDouble, u);
			
			if (as.insertAccount(a)) {
				System.out.println("Account was added to database. Please login to confirm");
				beginApp();
			}else {
				System.out.println("Account not added: Something went wrong please try again");
				beginApp();
			}
			
			//System.out.println("Your account number is: "+ a.getAccountNumber());
		}		
	}

	private void findUser() {
		System.out.println("Does your User already exist already exist in database? yes or no");
		String response = scan.nextLine();
		
		if (response.toLowerCase().equals("yes")) {
			createAccountAsAdmin();
			
		}else if(response.toLowerCase().equals("no")) {
			createAccountScratch();
			
		}else {
			makeTransactionAdmin();
		}
	}

	private void createAccountAsAdmin() {
		label: {
			System.out.println("Enter User information for new account\n"
					+ "Username: ");
			String username = scan.nextLine();
			System.out.println("Confirm Password:");
			String password = scan.nextLine();
			User u= ud.findByUserPassword(username, password);
			
			System.out.println("Enter balance you want your Account to start off with: "); 
			String balance= scan.nextLine(); 
			double balanceDouble=0;
			try{
				balanceDouble= Double.parseDouble(balance);
			}catch (NumberFormatException e) {
				System.out.println("Please enter a numeric value!");
				createAccountScratch();
			}finally {
				if(balanceDouble==0) {
					break label;
				}
			}	
			
			Account a = new Account("Approved", balanceDouble, u);
			
			if (as.insertAccount(a)) {
				System.out.println("Account was added to database ");
			}else {
				System.out.println("Account not added: Something went wrong please try again");
				beginApp();
			}
			//System.out.println("User with the username " +u.getUsername()+  " will now have access to account number: "+ a.getAccountNumber());
		}		
	}

	private User createUser() {
		System.out.println("Please enter in necessary information");
		System.out.println("Name (first, last): ");
		String first,last;
		User userFound=null;
		try {
			String name[] = scan.nextLine().split(" ");
			if(name.length >2) {
				System.out.println("Please only enter first name and last name. "
						+ "2 last names need to be joined by a - and middle names are not necessary");
				beginApp();
			}
			first= name[0];
			last= name[1];
		
			System.out.println("Do you want to add a phone number? Y/N"); 
			String answer = scan.nextLine(); 
			
			String phoneNumber;
			if (answer.equals("Y")) {
				System.out.println("Enter phone number: ");
				phoneNumber = scan.nextLine(); 
			}
			else {
				System.out.println("User does not want to enter phone number");
				phoneNumber = null;
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
			
			userFound =ud.findByUsername(username);
		}catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("You need to enter first and last name. Try again");
			createUser();
		}
		return userFound;
	}
	
	
}