package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	
	// check user e password
	static private final String jdbcUrl = "jdbc:mysql://localhost/iscritticorsi?user=root&password=root";

	public static Connection getConnection() {

		try {
			String sql = "jdbc:mysql://localhost/iscritticorsi?user=root&password=kikkalove01";
				Connection connection = DriverManager.getConnection(sql);
				return connection;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException("Cannot get a connection " + jdbcUrl, e);
		}
	}

}
