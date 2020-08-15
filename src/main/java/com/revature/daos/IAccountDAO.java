package com.revature.daos;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;

public interface IAccountDAO {

	public List<Account> findAll();
	public Account findByAccountNumber(int accountNumber);
	public boolean addAccount(Account a);
	public boolean updateAccount(Account a);


}
