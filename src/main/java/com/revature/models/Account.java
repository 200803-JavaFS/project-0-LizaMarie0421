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
	public String userID;
	
	//to keep track of accounts created;
	public int accountCount;
	
	public User user;
	//to create new account 
	public Account() {
		super();
		//if 0 accounts created new account will be 1 
		accountNumber=++accountCount;
		//new account will start with 0 balance
		balance=0;
	}
//want accountNumber to be a random unique number
	public Account(User user, int accountNumber, double balance, String userID) {
		super();
		this.user= user;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.userID = userID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	public String getUserID() {
		return userID;
	}

	@Override
	public String toString() {
		return "Account[Account Number: " + accountNumber +"\nBalance: "+ balance + "\nUser info: "+ user +"\nUserID: "+ userID +"]";
	}

}
