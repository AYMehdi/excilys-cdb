package main.java.com.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.java.com.excilys.exceptions.DAOException;
import main.java.com.excilys.mappers.CompanyMapper;
import main.java.com.excilys.models.Company;

public class CompanyDAO {

	// ******** QUERIES *******
	static final String SQL_GET_BY_ID = "SELECT id, name FROM company WHERE id = ?";
	static final String SQL_GET_ALL = "SELECT id, name FROM company";
	static final String SQL_GET_ALL_ORDER_BY_NAME = "SELECT id, name FROM company ORDER BY name";
	static final String SQL_GET_BY_NAME = "SELECT id, name FROM company WHERE name = ?";
	static final String SQL_INSERT = "INSERT INTO company (id, name) VALUES (?,?)";
	static final String SQL_DELETE_COMPUTER_BY_COMPANY_ID = "DELETE FROM computer WHERE company_id = ?";
	static final String SQL_DELETE_COMPANY_BY_ID = "DELETE FROM company WHERE id = ?";

	// ******** VARIABLES *******
	private static CompanyDAO instance = null;
	
	CompanyMapper companyMapper = CompanyMapper.getInstance();
	
	// ******** CONSTRUCTOR *******
	private CompanyDAO() {}

	// ******** GETTER *******
	public static CompanyDAO getInstance() {
		if (instance == null) {
			instance = new CompanyDAO();
		}
		return instance;
	}

	public Optional<Company> getById(int id) throws DAOException {
		Optional<Company> company = Optional.empty();
		return TransactionHandler.create((Connection connection, Optional<Company> companyArg) -> {
			PreparedStatement statement = connection.prepareStatement(SQL_GET_BY_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				companyArg = Optional.ofNullable(companyMapper.map(resultSet));
			}
			return companyArg;
		}).run(company).getResult();
	}

	public Optional<Company> getByName(String name) throws DAOException {
		Optional<Company> company = Optional.empty();
		return TransactionHandler.create((Connection connection, Optional<Company> companyOpt) -> {
			PreparedStatement statement = connection.prepareStatement(SQL_GET_BY_NAME);
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				companyOpt = Optional.ofNullable(companyMapper.map(resultSet));
			}
			return companyOpt;
		}).run(company).getResult();
	}
	
	public ArrayList<Company> getAll() throws DAOException {
		List<Company> listCompanies = new ArrayList<Company>();
		return (ArrayList<Company>) TransactionHandler.create((Connection connection, List<Company> l) -> {
			PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Company company = companyMapper.map(resultSet);
				l.add(company);
			}
			return l;
		}).run(listCompanies).getResult();
	}

	public List<Company> getAllOrderByName(boolean isDesc) throws DAOException {
		TransactionHandler<String, List<Company>> transactionHandler = TransactionHandler.create((Connection connection, String request) -> {
			List<Company> listCompanies = new ArrayList<Company>();
			PreparedStatement statement = connection.prepareStatement(request);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Company company = companyMapper.map(resultSet);
				listCompanies.add(company);
			}
			return listCompanies;
		});
		if (isDesc) {
			return transactionHandler.run(SQL_GET_ALL_ORDER_BY_NAME + " DESC").getResult();
		} else {
			return transactionHandler.run(SQL_GET_ALL_ORDER_BY_NAME).getResult();
		}
	}

	public void add(Company obj) throws DAOException {
		TransactionHandler.create((Connection connection, Company companyArg) -> {
			PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
			statement.setInt(1, companyArg.getId());
			statement.setString(2, companyArg.getName());
			statement.executeUpdate();
			return Optional.empty();
		}).run(obj);
	}
	
	public void delete(Company obj) throws DAOException {
		TransactionHandler.create((Connection connection, Company company) -> {
			PreparedStatement statement = connection.prepareStatement(SQL_DELETE_COMPUTER_BY_COMPANY_ID);
			statement.setInt(1, company.getId());
			statement.executeUpdate();
			statement = connection.prepareStatement(SQL_DELETE_COMPANY_BY_ID);
			statement.setInt(1, company.getId());
			statement.executeUpdate();
			return Optional.empty();
		}).run(obj);
	}
}
