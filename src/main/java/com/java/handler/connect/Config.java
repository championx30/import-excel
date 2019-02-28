package com.java.handler.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.java.init.Setting;

public class Config {
	
	public Connection getJDBCConnection() {
		String url = Setting.getUrlDb();
		String user = Setting.getUserDb();
		String password = Setting.getPasswordDb();
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
