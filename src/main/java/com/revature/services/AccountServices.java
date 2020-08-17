package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.AccountDAO;
import com.revature.daos.IAccountDAO;
import com.revature.daos.IUserDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Account;
import com.revature.models.User;

public class AccountServices {
	private static final Logger log = LogManager.getLogger(AccountServices.class);
	private static IAccountDAO adao = new AccountDAO();
	private static IUserDAO udao = new UserDAO();

	
	public List<Account> findAll(){
		log.info("Retrieving all accounts");
		List<Account> list = adao.findAll();
		return list;
		
	}

	public Account findByAccountNumber(int accountNumber) {
		//to log actual things in file
		log.info("Finding Account with Account Number "+ accountNumber);
		return adao.findByAccountNumber(accountNumber);
	}
	public List<Account> findByUser(int userId) {
		log.info("Retrieving all accounts tied to user with user_id="+ userId);
		List<Account> list = adao.findByUserId(userId);
		return list;
	}

	public boolean insertAccount(Account a) {
		log.info("Inserting account: "+ a);
		if (a.getUser()!=null) {
			List<User> list = udao.findAll(); 
			boolean b = false;
			for (User u: list) {
				if (u.equals(u.getId())) {
					b= true;
				}
			}
			if (adao.addAccount(a)) {
				return true;
			}
			
		}
		return false;
	}
	
	public boolean updateAccount(Account a) {
		log.info("Updating Account: "+ a);
		if (adao.updateAccount(a)) {
			return true;
		}
		return false;
	}
	
	public void transfer(Account a, Account b, double amount) {
		//get the balance of the account you want to transfer to and increase it 
		double ogOtherAccountBalance = b.getBalance();
		double newOtherAccountBalance = ogOtherAccountBalance + amount;
		
		
		
		//get balance of account that you are withdrawing from to transfer
		double ogAccountBalance = a.getBalance();
		double newAccountBalance = ogAccountBalance- amount;
		
		
		
		b.setBalance(newOtherAccountBalance);
		a.setBalance(newAccountBalance);
		updateAccount(b);
		updateAccount(a);
	}
	
	public void deposit(Account a, double amount) {
		double oldBalance = a.getBalance();
		a.setBalance(oldBalance+amount);
		updateAccount(a);
	}
	
	public void withdraw(Account a, double amount) {
		double oldBalance = a.getBalance();
		a.setBalance(oldBalance-amount);
		updateAccount(a);
	}
}
