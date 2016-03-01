package ua.nure.bj.persistence;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
	static {
		setDbProperties();
	}

	private static void setDbProperties() {
		Properties props = new Properties();
		InputStream is = Connector.class
				.getResourceAsStream("/config/db.properties");
		try {
			props.load(is);
			LOGIN = (String) props.get("username");
			PASSWORD = (String) props.get("password");
			HOST = (String) props.get("host");
		} catch (Exception ex) {
			System.out.println("failed setting properties for db");
		}
	}

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static String LOGIN;
	private static String PASSWORD;
	private static String HOST;
	private static Connection connection;

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("JDBC Driver not Found");
		}
		try {
			connection = DriverManager.getConnection(HOST, LOGIN, PASSWORD);

		} catch (SQLException e) {

			throw new SQLException("Connection Failed! Check output console");
		}
		return connection;
	}
}
