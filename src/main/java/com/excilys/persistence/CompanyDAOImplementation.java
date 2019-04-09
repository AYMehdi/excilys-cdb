package main.java.com.excilys.persistence;

import main.java.com.excilys.exception.DAOException;
import main.java.com.excilys.mapper.CompanyMapper;
import main.java.com.excilys.model.Company;

import static main.java.com.excilys.persistence.DAOUtility.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyDAOImplementation implements CompanyDAO {
	
	private static final String SQL_SELECT_ALL = "SELECT id, name FROM company;";
	private static final String SQL_SELECT_BY_ID = "SELECT id, name FROM company WHERE id = ?;";
	private static final String SQL_INSERT = "INSERT INTO company (name) VALUES (?)";
	private static final String SQL_UPDATE = "UPDATE company SET name = ? WHERE id = ?;";
	private static final String SQL_DELETE = "DELETE FROM company WHERE id = ?;";
	
	// ******* VARIABLES *******
	private DAOFactory daoFactory;
	private CompanyMapper companyMapper;
	
	// ******* CONSTRUCTEUR *******
	CompanyDAOImplementation(DAOFactory daoFactory) {
		this.setDAOFactory(daoFactory);
		this.setCompanyMapper(new CompanyMapper());
	}
	
	// ******* GETTERS *******
	public DAOFactory getDAOFactory() {
		return daoFactory;
	}

	public CompanyMapper getCompanyMapper() {
		return companyMapper;
	}
	
	// ******* SETTERS *******
	public void setDAOFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public void setCompanyMapper(CompanyMapper companyMapper) {
		this.companyMapper = companyMapper;
	}
	
	// ******* INTERFACE IMPLEMENTATION *******
	@Override
	public ArrayList<Company> companiesList() throws DAOException {
		ArrayList<Company> companies = new ArrayList<Company>();
	    ResultSet resultSet = null;
	    
	    try (
	    	Connection connection = daoFactory.getConnection();
	    	PreparedStatement preparedStatement = preparedStatementInitialization(connection, SQL_SELECT_ALL, false)
	    	) {
	    	
	        resultSet = preparedStatement.executeQuery();
	        
	        while (resultSet.next()) {
	        	companies.add(this.companyMapper.map(resultSet));
	        }
	        
	    } catch (SQLException e) {
			throw new DAOException(e);
		}
	    
		return companies;
	}

	@Override
	public Company find(int pID) throws DAOException {
	    ResultSet resultSet = null;
	    Company company = null;
	    
	    try (
	    	Connection connection = daoFactory.getConnection();
	    	PreparedStatement preparedStatement = preparedStatementInitialization(connection, SQL_SELECT_BY_ID, false, pID)
	    	) {
	    	
	        resultSet = preparedStatement.executeQuery();
	        
	        if (resultSet.next() ) {
	        	company = this.companyMapper.map(resultSet);
	        } else {
	        	throw new DAOException("No SQL result for this company ID.");
	        }
	        
	    } catch (SQLException e) {
			throw new DAOException(e);
		}
	    
	    return company;
	}

	@Override
	public void add(Company company) throws DAOException {
	    ResultSet autoGeneratedValues = null;
	    
	    this.checkData(company);

	    try {
	    	Connection connection = daoFactory.getConnection();
	    	PreparedStatement preparedStatement = preparedStatementInitialization(connection, SQL_INSERT, true, company.getId(), company.getName());
	        
	        int statut = preparedStatement.executeUpdate();

	        if (statut == 0) {
	            throw new DAOException("Failed to add the company in the database. No line created in the table of the database.");
	        }

	        autoGeneratedValues = preparedStatement.getGeneratedKeys();

	        if (autoGeneratedValues.next()) {
	        	company.setId(autoGeneratedValues.getInt(1));
	        } else {
	            throw new DAOException("Failed to add the company in the database. No auto-generated ID found.");
	        }
	        
	    } catch (SQLException e) {
	        throw new DAOException(e);
	    }
	}

	@Override
	public void update(Company company) throws DAOException {
	    this.checkData(company);

	    try (
	    	Connection connection = daoFactory.getConnection();
	    	PreparedStatement preparedStatement = preparedStatementInitialization(connection, SQL_UPDATE, false, company.getId(), company.getName())) {

	        int statut = preparedStatement.executeUpdate();

	        if (statut == 0) {
	            throw new DAOException("Failed to update the company in the database. No line updated in the table of the database.");
	        }

	    } catch (SQLException e) {
	        throw new DAOException(e);
	    }
	}

	@Override
	public void remove(Company company) throws DAOException {
	    try (
	    		Connection connection = daoFactory.getConnection();
	    		PreparedStatement preparedStatement = preparedStatementInitialization(connection, SQL_DELETE, false, company.getId())) {
		    	
		        int statut = preparedStatement.executeUpdate();

		        if (statut == 0) {
		            throw new DAOException("Failed to remove the company in the database. No line deleted in the table.");
		        }

		    } catch (SQLException e) {
		        throw new DAOException(e);
		    }
		}
	
	public void checkData(Company company) {
		if(company.getName() == null) {
	    	throw new DAOException("Failed to create this company. Company name does not exist.");
	    }
	}
}