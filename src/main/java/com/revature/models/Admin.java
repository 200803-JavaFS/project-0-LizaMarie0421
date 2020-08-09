package com.revature.models;

public class Admin extends Employee{

	
	public Admin() {
		super();
	}

	public Admin(int id, String name, String address) {
		super(id, name, address);
	}

	public Admin(int id, String type, String name, String address, String phone) {
		super(id, name, address, phone);
	}

	@Override
	public void accessInfo(User user) {
		System.out.println("Accessing User as Admin");
		System.out.println("User "+ user.getId() +" personal info:  \nname:" +
				user.getName() + " \nAdress: " + user.getAddress() + " \nPhone Number: "+ 
				user.getPhone());
	}
	
	public Admin(int id, String type, String name, String address) {
		super(id, type, name, address);
	}

	public void transferBalance(double amount, Account accountTo, Account accountFrom) {
		double ogAccountBalance = accountTo.getBalance();
		double newAccountBalance = ogAccountBalance+amount;
		accountTo.setBalance(newAccountBalance);
		
		double ogAccountBalance2= accountFrom.getBalance();
		double newAccountBalance2= ogAccountBalance2-amount;	
		accountFrom.setBalance(newAccountBalance2);
		
	}

}
