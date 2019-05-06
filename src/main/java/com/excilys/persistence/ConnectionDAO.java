package com.excilys.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.exception.DAOException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionDAO {

// ******** CONSTANTS *******	
	private static final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
	public static final String DAO_PROPERTIES = "datasource.properties";
	
// ******** VARIABLES *******	
	private static HikariDataSource hikariDataSource;
	private static ConnectionDAO instance = null;
	
// ******** METHODS *******	
	/**
	 * Establish connection to database
	 * @throws IOException
	 * @throws DAOException
	 */
	private ConnectionDAO() throws IOException, DAOException {
		this(DAO_PROPERTIES);
	}

	/**
	 * Establish connection to database with properties path parameter
	 * @param propertiesPath
	 * @throws IOException
	 * @throws DAOException
	 */
	private ConnectionDAO(String propertiesPath) throws IOException, DAOException {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(propertiesPath);
		try {
			properties.load(fichierProperties);
			HikariConfig hikariConfig = new HikariConfig(properties);
			hikariDataSource = new HikariDataSource(hikariConfig);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("IO Excepyion in com.excilys.persistence.ConnectionDAO", e);
			throw new DAOException("in ConnectionDAO(String propertiedPath) constructor");
		}
	}

	/**
	 * Get connection DAO instance
	 * @return instance ConnectionDAO
	 * @throws IOException
	 * @throws DAOException
	 */
	public static ConnectionDAO getInstance(String propertiesPath) throws DAOException {
		if (instance == null) {
			try {
				instance = new ConnectionDAO(propertiesPath);
			} catch (IOException | DAOException e) {
				e.printStackTrace();
				logger.error("IO Excepyion in com.excilys.persistence.ConnectionDAO", e);
				throw new DAOException("in getInstance(String propertiesPath) method");
			}
		}
		return instance;
	}
	
	/**
	 * Get connection DAO instance
	 * @return instance ConnectionDAO
	 * @throws IOException
	 * @throws DAOException
	 */
	public static ConnectionDAO getInstance() throws DAOException {
		if (instance == null) {
			try {
				instance = new ConnectionDAO();
			} catch (IOException | DAOException e) {
				e.printStackTrace();
				logger.error("IO Excepyion in com.excilys.persistence.ConnectionDAO", e);
				throw new DAOException("in getInstance() method");
			}
		}
		return instance;
	}

	/**
	 * Establish connection to database with Hikari
	 * @return Connection
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return hikariDataSource.getConnection();
	}
	
	/**
	 * Get dataSource with Hikari
	 * @return DataSource
	 */
	public DataSource getDataSource() {
		return hikariDataSource;
	}

	/**
	 * Close connection to database with Hikari
	 */
	public void close() {
		if (instance != null) {
			hikariDataSource.close();
		}		
	}

}
