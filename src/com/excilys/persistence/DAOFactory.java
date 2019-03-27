package com.excilys.persistence;

import com.excilys.persistence.CompanyDAOImplementation;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DAOFactory {
	
    private static final String FICHIER_PROPERTIES = "com/excilys/resources/properties/dao.properties";
    private static final String PROPERTY_URL	   = "url";
    private static final String PROPERTY_DRIVER	   = "driver";
    private static final String PROPERTY_USER	   = "user";
    private static final String PROPERTY_PWD	   = "pwd";
    
    // ******* VARIABLES *******
    private String url;
    private String username;
    private String password;
    
    // ******* CONSTRUCTEUR *******
	public DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
	
	// ******* GETTERS *******
    public String getUrl() {
		return url;
	}
    
    public String getUsername() {
		return username;
	}
    
    public String getPassword() {
		return password;
	}
    
    // ******* SETTERS *******
	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	// ******* OTHER METHODS *******
    public static DAOFactory getInstance() throws DAOException {
        Properties properties = new Properties();
        String url;
        String driver;
        String user;
        String pwd;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);

        if (fichierProperties == null) {
            throw new DAOException("The file " + FICHIER_PROPERTIES + " is not found.");
        }

        try {
            properties.load( fichierProperties );
            url = properties.getProperty(PROPERTY_URL);
            driver = properties.getProperty(PROPERTY_DRIVER);
            user = properties.getProperty(PROPERTY_USER);
            pwd = properties.getProperty(PROPERTY_PWD);
        } catch (IOException e) {
            throw new DAOException("The property file " + FICHIER_PROPERTIES + "can't be loaded", e);
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DAOException("The driver is not found in the classpath.", e);
        }

        DAOFactory instance = new DAOFactory(url, user, pwd);
        return instance;
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public CompanyDAO getCompanyDao() {
        return new CompanyDAOImplementation(this);
    }
    
    public ComputerDAO getComputerDao() {
        return new ComputerDAOImplementation(this);
    }
}
