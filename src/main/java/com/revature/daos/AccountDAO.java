package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.utils.ConnectionUtility;

public class AccountDAO implements IAccountDAO {
	private IUserDAO uDAO = new UserDAO();
	@Override
	public List<Account> findAll() {
		try(Connection conn= ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM accounts;";
			Statement statement = conn.createStatement();
			
			List<Account> list = new ArrayList<>();
			
			ResultSet result= statement.executeQuery(sql);
			
			while (result.next()){
				//(account_number, account_status, account_balance, account_user_fk)
				Account a = new Account(result.getInt("account_number"),result.getString("account_status"), 
						result.getDouble("account_balance"),null);
				if(result.getInt("account_user_fk")!=0) {
					a.setUser(uDAO.findById(result.getInt("account_user_fk")));
				}
				
				list.add(a);
				
			}
			return list;
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account findByAccountNumber(int accountNumber) {
		try(Connection conn= ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM accounts WHERE account_number =" +accountNumber+";";
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			if (result.next()) {
				Account a = new Account(result.getInt("account_number"),result.getString("account_status"), 
						result.getDouble("account_balance"),null);
				if(result.getInt("account_user_fk")!=0) {
					a.setUser(uDAO.findById(result.getInt("account_user_fk")));
				}
				
				return a;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Account> findByUserId(int userId) {
		try(Connection conn= ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM accounts WHERE account_user_fk =" +userId+";";
			Statement statement = conn.createStatement();
			List<Account> list = new ArrayList<>();
			ResultSet result = statement.executeQuery(sql);
						
			while (result.next()){
				//(account_number, account_status, account_balance, account_user_fk)
				Account a = new Account(result.getInt("account_number"),result.getString("account_status"), 
						result.getDouble("account_balance"),uDAO.findById(userId));
				list.add(a);
				
			}
			return list;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	@Override
	public boolean addAccount(Account a) {
		try(Connection conn= ConnectionUtility.getConnection()){
			String sql = "INSERT INTO accounts(account_status, account_balance, account_user_fk)"
					+ "VALUES(?,?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index=0;
			statement.setString(++index, a.getApprovalStatus());
			statement.setDouble(++index, a.getBalance());
			
			User u = a.getUser();
			statement.setInt(++index, u.getId());
			
			statement.execute();
			return true;
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateAccount(Account a) {
		try(Connection conn= ConnectionUtility.getConnection()){
			String sql = "UPDATE accounts SET account_status= ?, account_balance= ?"
					+ "WHERE  account_number = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index=0;
			statement.setString(++index, a.getApprovalStatus());
			statement.setDouble(++index, a.getBalance());
			
			statement.setInt(++index, a.getAccountNumber());
			statement.execute();
			return true;
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
}
