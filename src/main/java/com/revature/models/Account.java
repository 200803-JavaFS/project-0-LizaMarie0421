package com.revature.models;

public class Account {

	/*
	 * public static String username;
	 * public static String password;
	 */
	
	//unique
	public int accountNumber;
	public double balance;
	
	//foreign key to User table
	public int userID;
	
	//to keep track of accounts created;
	public int accountCount;
	
	public User customer;
	//to create new account 
	public Account() {
		super();
		//if 0 accounts created new account will be 1 
		accountNumber=++accountCount;
		//new account will start with 0 balance
		balance=0;
	}

	public Account(User customer, int accountNumber, double balance, int userID) {
		super();
		this.customer= customer;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.userID = userID;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	/*
	 * public static String getUsername() { return username; }
	 * 
	 * public static void setUsername(String username) { Account.username =
	 * username; }
	 * 
	 * public static String getPassword() { return password; }
	 * 
	 * public static void setPassword(String password) { Account.password =
	 * password; }
	 */

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}


	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}


	public int getAccountCount() {
		return accountCount;
	}

	public void setAccountCount(int accountCount) {
		this.accountCount = accountCount;
	}
	
	//do not want to set userId that is limited to User class
	public int getUserID() {
		return userID;
	}

	
	public void deposit(double amount) {
		balance+=amount;
			
	}
	
	public void withdrawal(double amount) {
				
		if(amount>=balance) {
			balance =0;
			//want to set error message not allowed to withdraw more than balance
		}
		else
			balance-=amount;
	}
	
	
}
