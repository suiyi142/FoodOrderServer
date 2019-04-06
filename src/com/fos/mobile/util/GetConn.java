package com.fos.mobile.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConn {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://127.0.0.1:3306/stock";
	private static String userName = "root";
	private static String password = "123";

	public static Connection getConnection() {
		try {
			Class.forName(driver);
			System.out.println("数据库连接成功");
			return DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void closeconn(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
