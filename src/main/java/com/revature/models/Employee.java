package com.revature.models;

public class Employee extends User{

	
	public Employee() {
		super();
	}

	public Employee(int id, String name, String address, String phone) {
		super(id, name, address, phone);
	}

	public Employee(int id, String name, String address) {
		super(id, name, address);
	}

	
	
	//approve/deny applications to create account
	public boolean approveAccount(boolean answer) {
		return answer;
	}

	@Override
	public void accessInfo(User user) {
		System.out.println("Accessing User as Employee");
		System.out.println("User "+ user.getId() +" personal info:  \nname:" +
				user.getName() + " \nAdress: " + user.getAddress() + " \nPhone Number: "+ 
				user.getPhone());
	}
	
	public void accessUserBalance( User user, Account account) {
		System.out.println("User "+ user.getName()+" Account Number: "+ account.getAccountNumber()+ "Balance: " + account.getBalance());
	}
}
