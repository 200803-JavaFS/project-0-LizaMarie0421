package com.revature;

import static org.junit.Assert.assertTrue;

import org.junit.*;

import com.revature.daos.AccountDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AccountServices;
import com.revature.services.UserServices;
import com.revature.utils.ConsoleUserUtil;


public class OpsTest {
	public static int intResult;
	public static Account account; 
	public static Account account2; 
	public static double amount;

	public static User user; 
	
	private AccountServices as= new AccountServices();
	
	private AccountDAO ad = new AccountDAO();
	
	ConsoleUserUtil cons = new ConsoleUserUtil();
	
//	@BeforeClass
//	public void setUserAccounts() {
//		
//	}
	
	@Before
	public void setUp() {	
		amount=100;
		user= new User(19,"Customer","testFirst", "testLast","testPhone", "testUsername", "testPassword");
		//us.insertUser(user);
		//already inserted
		account = new Account(29,"Approved",100.00, user);
		//as.insertAccount(account);
		account2 = new Account(30,"Approved",100.00, user);
		//as.insertAccount(account2);
	}
	
	@After 
	public void shutDown() {
		user = null;
		account =null;
		account2 =null;
	}
	@Test
	public void testTransfer() {
		System.out.println("Testing transfer");
		as.transfer(account, account2, amount);
//		//account 29
		double accountBalance= ad.findByAccountNumber(29).getBalance();
		assertTrue(accountBalance==0);
//		//account 30
		double accountBalance2= ad.findByAccountNumber(30).getBalance();
		assertTrue(accountBalance2==200);
		
	}

	@Test
	public void testDeposit() {
	//	Account a= account;
		System.out.println("Testing deposit");
		as.deposit(account, amount);
		double accountBalance= ad.findByAccountNumber(29).getBalance();
		assertTrue(accountBalance==200);
	}
	@Test
	public void testWithdraw() {
	//	Account a= account;
		System.out.println("Testing withdraw");
		as.withdraw(account, amount);
		double accountBalance= ad.findByAccountNumber(29).getBalance();
		assertTrue(accountBalance==0);
	}
}
