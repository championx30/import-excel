package com.vietanh.test.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
	
	public Connection getJDBCConnection() {
		String url = "jdbc:oracle:thin:@10.200.38.10:1521/TESTDB";
		String user = "ipa";
		String password = "TEST123";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return (Connection) DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found!");
//			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Access denied!");
//			e.printStackTrace();
		}
		return null;
	}
}
