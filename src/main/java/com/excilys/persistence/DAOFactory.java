package main.java.com.excilys.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DAOFactory {

	public static final String DAO_PROPERTIES = "dao.properties";
	
	// ******** VARIABLES *******
	private CompanyDAO companyDAO;
	private ComputerDAO computerDAO;

	private static DAOFactory instance = null;

	private Logger logger = LoggerFactory.getLogger(DAOFactory.class);
	private HikariDataSource hikariDataSource;

	// ******** CONSTRUCTOR *******
	public DAOFactory() {
		this(DAO_PROPERTIES);
	}

	public DAOFactory(String propertiesPath) {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(propertiesPath);
		try {
			properties.load(fichierProperties);
			HikariConfig hikariConfig = new HikariConfig(properties);
			this.hikariDataSource = new HikariDataSource(hikariConfig);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	// ******** GETTERS *******
	public static DAOFactory getInstance() {
		if (instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}

	public ComputerDAO getComputerDAO() {
		if (this.computerDAO == null) {
			this.computerDAO = ComputerDAO.getInstance();
		}
		return this.computerDAO;
	}
	
	public CompanyDAO getCompanyDAO() {
		if (this.companyDAO == null) {
			this.companyDAO = CompanyDAO.getInstance();
		}
		return this.companyDAO;
	}

	public Connection getConnection() throws SQLException {
		return hikariDataSource.getConnection();
	}

	public Logger getLogger() {
		return this.logger;
	}
}