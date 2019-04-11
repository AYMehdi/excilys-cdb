package main.java.com.excilys.hikaricp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCP {

	private static Properties properties = new Properties();
	private String url;
	private String user;
	private String pwd;
	private final static Logger logger = Logger.getLogger(HikariCP.class);

	
	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource dataSource;
	private final static HikariCP instance = new HikariCP();

	private HikariCP() {
		try (InputStream in = HikariCP.class.getResourceAsStream("/database.properties")) {
			properties.load(in);
			Class.forName(properties.getProperty("driver"));
			this.url = properties.getProperty("url");
			this.user = properties.getProperty("user");
			this.pwd = properties.getProperty("pwd");
		} catch (FileNotFoundException e) {
			logger.error("Cannot find property file for database.", e);
		} catch (IOException e) {
			logger.error("Cannot load or close property file stream.", e);
		} catch (ClassNotFoundException e) {
			logger.error("Cannot load mysql Driver", e);
		}
		
		config.setJdbcUrl(this.url);
		config.setUsername(this.user);
		config.setPassword(this.pwd);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		dataSource = new HikariDataSource(config);
	}

	public static HikariCP getInstance() {
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}