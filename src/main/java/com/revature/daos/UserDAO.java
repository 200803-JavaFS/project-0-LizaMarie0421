package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.utils.ConnectionUtility;

public class UserDAO implements IUserDAO {

	@Override
	public List<User> findAll() {
		try(Connection conn= ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM users;";
			Statement statement = conn.createStatement();
			
			List<User> list = new ArrayList<>();

			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				User u = new User();
				//structure: h.setHomeBase(result.getString("home_base"));
				//(user_type, user_fname, user_lname, user_phone, user_username, user_password)
				u.setId(result.getInt("user_id"));
				u.setType(result.getString("user_type"));
				u.setFirst(result.getString("user_fname"));
				u.setLast(result.getString("user_lname"));
				u.setPhone(result.getString("user_phone"));
				u.setUsername(result.getString("user_username"));
				u.setPassword(result.getString("user_password"));
				list.add(u);
			}
			
			return list;
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public User findById(int id) {
		try(Connection conn= ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM users WHERE user_id =" +id+";";
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			if (result.next()) {
				//(user_type, user_fname, user_lname, user_phone, user_username, user_password)
				User u = new User(id,result.getString("user_type"), result.getString("user_fname"),
						result.getString("user_lname"), result.getString("user_phone"),
						result.getString("user_username"), result.getString("user_password"));
				return u;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public User findByType(String type) {
		try(Connection conn= ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM users WHERE user_type = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, type);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				//(user_type, user_fname, user_lname, user_phone, user_username, user_password)
				User u = new User(result.getString("user_type"), result.getString("user_fname"),
						result.getString("user_lname"), result.getString("user_phone"),
						result.getString("user_username"), result.getString("user_password"));
				return u;
			}

			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean addUser(User u) {
		try(Connection conn= ConnectionUtility.getConnection()){
			//prepared staement indicates use of ??
			String sql = "INSERT INTO users (user_type, user_fname, user_lname, user_phone, user_username, user_password)"
					+ "VALUES (?,?,?,?,?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index=0;
			statement.setString(++index, u.getType());
			statement.setString(++index, u.getFirst());
			statement.setString(++index, u.getLast());
			statement.setString(++index, u.getPhone());
			statement.setString(++index, u.getUsername());
			statement.setString(++index, u.getPassword());
			
			 statement.execute();
			 return true;
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUser(User u) {
		try(Connection conn= ConnectionUtility.getConnection()){
			String sql = "UPDATE users SET user_type= ?, user_fname= ?,"
					+ " user_lname= ?, user_phone= ?, user_username= ?, user_password= ? "
					+ "WHERE user_id=?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index=0;
			statement.setString(++index, u.getType());
			statement.setString(++index, u.getFirst());
			statement.setString(++index, u.getLast());
			statement.setString(++index, u.getPhone());
			statement.setString(++index, u.getUsername());
			statement.setString(++index, u.getPassword());
			statement.setInt(++index, u.getId());
			statement.execute();
			return true;
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User findByUserPassword(String username, String password) {
		try(Connection conn= ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM users WHERE user_username = ?"
					+ "AND user_password= ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				User u = new User();
				u.setId(result.getInt("user_id"));
				u.setType(result.getString("user_type"));
				u.setFirst(result.getString("user_fname"));
				u.setLast(result.getString("user_lname"));
				u.setPhone(result.getString("user_phone"));
				u.setUsername(result.getString("user_username"));
				u.setPassword(result.getString("user_password"));
				return u;
			}

			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findByUsername(String username) {
		try(Connection conn= ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM users WHERE user_username = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index=0;
			statement.setString(++index, username);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				User u = new User();
				//(user_type, user_fname, user_lname, user_phone, user_username, user_password)
				//order does not matter
				u.setId(result.getInt("user_id"));
				u.setType(result.getString("user_type"));
				u.setFirst(result.getString("user_fname"));
				u.setLast(result.getString("user_lname"));
				u.setPhone(result.getString("user_phone"));
				u.setUsername(result.getString("user_username"));
				u.setPassword(result.getString("user_password"));
				
				return u;
			} else {
				return null;
			}

			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
