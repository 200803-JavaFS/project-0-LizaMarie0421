package com.revature.service;

import com.revature.models.Account;

public class UserFunctionality{
	
	public static void transferBalance(double amount, Account accountTo, Account accountFrom) {
		double ogAccountBalance = accountTo.getBalance();
		double newAccountBalance = ogAccountBalance+amount;
		accountTo.setBalance(newAccountBalance);
		
		double ogAccountBalance2= accountFrom.getBalance();
		double newAccountBalance2= ogAccountBalance2-amount;	
		accountFrom.setBalance(newAccountBalance2);
		
	}
	public static void deposit(Account account,double amount) {
		account.balance+=amount;
			
	}
	
	public static void withdrawal(Account account,double amount) {
				
		if(amount>=account.balance) {
			account.balance =0;
			//want to set error message not allowed to withdraw more than balance
		}
		else
			account.balance-=amount;
	}
	public static void displayAccountInfo(Account account) {
		System.out.println("Account Number: " + account.accountNumber +"\nBalance: "+ account.balance);
		
	}
}
