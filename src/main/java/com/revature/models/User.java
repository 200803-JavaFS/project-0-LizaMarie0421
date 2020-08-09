package com.revature.models;

public class User {
	

	public int id;
	public String name;
	public String address;
	public String phone;
	
	public int userCount;
	
	
	public User() {
		super();
		this.id=++userCount;
	}

	//allows for customer to register id,name, address and phone number
	public User(int id, String name, String address, String phone) {
		super();
		this.id = ++userCount;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	//allows for customer to register id,name, address but they have option to 
	//not include phone number
	public User(int id, String name, String address) {
		super();
		this.id = ++userCount;
		this.name = name;
		this.address = address;
	}
	
	public int getUserCount() {
		return userCount;
	}

	public  void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void accessInfo(User user) {
		System.out.println("User "+ this.getId() +" personal info:  \nname: " +
				this.getName() + " \nAdress: " + this.getAddress() + " \nPhone Number: "+ 
				this.getPhone());
	}

	
}
