package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface IUserDAO {
	public List<User> findAll();
	
	public User findById(int id);
	public User findByType(String type);
	public User findByUserPassword(String username, String password);
	public User findByUsername(String username);
	public boolean addUser(User u);
	public boolean updateUser(User u);
	
}
