package com.revature.models;


public class Account {

	
	private int accountNumber;//PK
	private String approvalStatus;
	private double balance;
	private User user; //fk
	
	
	public Account() {
		super();
	}


	public Account(String approvalStatus, double balance, User user) {
		super();
		this.approvalStatus = approvalStatus;
		this.balance = balance;
		this.user = user;
	}
	public Account(int accountNumber, String approvalStatus, double balance, User user) {
		super();
		this.accountNumber = accountNumber;
		this.approvalStatus = approvalStatus;
		this.balance = balance;
		this.user = user;
	}
	

	public int getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}


	public String getApprovalStatus() {
		return approvalStatus;
	}


	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber;
		result = prime * result + ((approvalStatus == null) ? 0 : approvalStatus.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (approvalStatus == null) {
			if (other.approvalStatus != null)
				return false;
		} else if (!approvalStatus.equals(other.approvalStatus))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", approvalStatus=" + approvalStatus + ", balance=" + balance
				+ ", user=" + user + "]";
	}


	

	

}
