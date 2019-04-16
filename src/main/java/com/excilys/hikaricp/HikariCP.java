package main.java.com.excilys.hikaricp;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCP {

	private final static HikariCP instance = new HikariCP();
	private static HikariConfig config = new HikariConfig("/database.properties");
	private static HikariDataSource dataSource;


	private HikariCP() {

		dataSource = new HikariDataSource(config);
	}

	public static HikariCP getInstance() {
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}