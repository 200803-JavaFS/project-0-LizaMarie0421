package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:postgresql://javafs200803.ce1j3wmsah8j.us-east-2.rds.amazonaws.com:5432/bank";
		String username = "postgres";
		String password = "database";

		return DriverManager.getConnection(url, username, password);
	}

//	public static void main(String[] args) {
//		
//		try(Connection conn = ConnectionUtility.getConnection()){
//			System.out.println("Connection sucessful"); 
//		} catch(SQLException e){
//			e.printStackTrace(); 
//		}
//		  
//	}
}
