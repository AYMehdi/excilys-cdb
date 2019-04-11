package main.java.com.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import main.java.com.excilys.exception.DAOException;
import main.java.com.excilys.exception.ItemNotFoundException;
import main.java.com.excilys.mapper.ComputerMapper;
import main.java.com.excilys.model.Company;
import main.java.com.excilys.model.Computer;

public class ComputerDAO {

	static final String[] COMPUTER_COLUMN = { "id", "name", "introduced", "discontinued", "company_id" };
	
	// ******** QUERIES *******
	static final String SQL_INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)";
	static final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	static final String SQL_DELETE = "DELETE FROM computer WHERE id = ?";
	static final String SQL_GET_ALL = "SELECT id, name, introduced, discontinued, company_id FROM computer";
	static final String SQL_GET_ALL_ORDER_BY = "SELECT id, name, introduced, discontinued, company_id FROM computer ORDER BY ";
	static final String SQL_GET_ALL_ORDER_BY_COMPANY_NAME = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id FROM computer LEFT JOIN company ON company.id = computer.company_id ORDER BY company.name IS NULL ASC, company.name";
	static final String SQL_GET_BY_ID = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id = ?";
	static final String SQL_GET_BY_NAME = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name = ?";
	static final String SQL_GET_LIKE = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name LIKE ?";
	static final String SQL_GET_LIKE_ORDER_BY = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name LIKE ? ORDER BY ";
	static final String SQL_GET_LIKE_ORDER_BY_COMPANY_NAME = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.name LIKE ? ORDER BY company.name IS NULL ASC, company.name";

	// ******** VARIABLES *******
	private static ComputerDAO instance = null;

	DAOFactory daoFactory = DAOFactory.getInstance();
	ComputerMapper computerMapper = ComputerMapper.getInstance();
	
	// ******** CONSTRUCTOR *******
	private ComputerDAO() {}

	// ******** GETTER *******
	public static ComputerDAO getInstance() {
		if (instance == null) {
			instance = new ComputerDAO();
		}
		return instance;
	}

	// ******** METHODS *******
	public Optional<Computer> get(int id) throws DAOException {
		Optional<Computer> computerOpt = Optional.empty();
		return TransactionHandler.create((Connection connection, Optional<Computer> computerOptArg) -> {
			PreparedStatement stmt = connection.prepareStatement(SQL_GET_BY_ID);
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				Computer computer = ComputerMapper.map(resultSet);
				Optional<Company> companyOpt = daoFactory.getCompanyDAO().get(computer.getCompany().getId());
				if (companyOpt.isPresent()) {
					computer.setCompany(companyOpt.get());
				}
				computerOptArg = Optional.ofNullable(computer);
			}
			return computerOptArg;
		}).run(computerOpt).getResult();
	}
	
	public Optional<Computer> get(String name) throws DAOException {
		Optional<Computer> computerOpt = Optional.empty();
		return TransactionHandler.create((Connection connection, Optional<Computer> computerOptArg) -> {
			PreparedStatement stmt = connection.prepareStatement(SQL_GET_BY_NAME);
			stmt.setString(1, name);
			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				Computer computer = ComputerMapper.map(resultSet);
				Optional<Company> company = daoFactory.getCompanyDAO().get(computer.getCompany().getId());
				if (company.isPresent()) {
					computer.setCompany(company.get());
				}
				computerOptArg = Optional.ofNullable(computer);
			}
			return computerOptArg;
		}).run(computerOpt).getResult();
	}
	
	public void add(Computer obj) throws DAOException {
		TransactionHandler.create((Connection connection, Computer computerArg) -> {
			PreparedStatement stmt = connection.prepareStatement(SQL_INSERT);
			//stmt.setInt(1, computerArg.getId());
			stmt.setString(1, computerArg.getName());
			stmt.setTimestamp(2, computerArg.getIntroducedDate());
			stmt.setTimestamp(3, computerArg.getDiscontinuedDate());
			if (daoFactory.getCompanyDAO().get(computerArg.getCompany().getId()).isPresent()) {
				stmt.setInt(4, computerArg.getCompany().getId());
			} else {
				stmt.setObject(4, null);
			}
			stmt.executeUpdate();
			return Optional.empty();
		}).run(obj);
	}
	
	public void update(Computer obj) throws DAOException, ItemNotFoundException {
		Optional<Computer> computerOpt = this.get(obj.getId());
		if (computerOpt.isPresent()) {
			TransactionHandler.create((Connection connection, Computer computerArg) -> {
				PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE);
				stmt.setString(1, computerArg.getName());
				stmt.setTimestamp(2, computerArg.getIntroducedDate());
				stmt.setTimestamp(3, computerArg.getDiscontinuedDate());
				if (daoFactory.getCompanyDAO().get(computerArg.getCompany().getId()).isPresent()) {
					stmt.setInt(4, computerArg.getCompany().getId());
				} else {
					stmt.setObject(4, null);
				}
				stmt.setInt(5, obj.getId());
				stmt.executeUpdate();
				return Optional.empty();
			}).run(obj);
		} else {
			throw new ItemNotFoundException("update");
		}
	}
	
	public void remove(Computer obj) throws DAOException {
		TransactionHandler.create((Connection connection, Computer computerArg) -> {
			PreparedStatement stmt = connection.prepareStatement(SQL_DELETE);
			stmt.setInt(1, computerArg.getId());
			stmt.executeUpdate();
			return Optional.empty();
		}).run(obj);
	}

	public ArrayList<Computer> getAll() throws DAOException {
		ArrayList<Computer> computerList = new ArrayList<Computer>();
		return TransactionHandler.create((Connection connection, ArrayList<Computer> computerListArg) -> {
			PreparedStatement stmt = connection.prepareStatement(SQL_GET_ALL);
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				Computer computer = ComputerMapper.map(resultSet);
				Optional<Company> company = daoFactory.getCompanyDAO().get(computer.getCompany().getId());
				if (company.isPresent()) {
					computer.setCompany(company.get());
				}
				computerListArg.add(computer);
			}
			return computerListArg;
		}).run(computerList).getResult();
	}
	
	public List<Computer> getAllOrderBy(String order, boolean isDesc) throws DAOException {
		TransactionHandler<String, List<Computer>> transactionHandler = TransactionHandler
				.create((Connection connection, String req) -> {
					ArrayList<Computer> computerListArg = new ArrayList<Computer>();
					PreparedStatement stmt = connection.prepareStatement(req);
					ResultSet resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						Computer computer = ComputerMapper.map(resultSet);
						Optional<Company> company = daoFactory.getCompanyDAO().get(computer.getCompany().getId());
						if (company.isPresent()) {
							computer.setCompany(company.get());
						}
						computerListArg.add(computer);
					}
					return computerListArg;
				});
		String desc = isDesc ? " DESC" : "";
		if (Arrays.asList(COMPUTER_COLUMN).contains(order)) {
			StringBuilder req = new StringBuilder().append(SQL_GET_ALL_ORDER_BY).append(order).append(" IS NULL ASC, ").append(order).append(desc);
			return transactionHandler.run(req.toString()).getResult();
		} else if (order.equals("companyName")) {
			return transactionHandler.run(SQL_GET_ALL_ORDER_BY_COMPANY_NAME + desc).getResult();
		} else {
			return getAll();
		}
	}

	public List<Computer> getPattern(String pattern) throws DAOException {
		List<Computer> result = new ArrayList<Computer>();
		return TransactionHandler.create((Connection connection, List<Computer> computerListArg) -> {
			PreparedStatement stmt = connection.prepareStatement(SQL_GET_LIKE);
			String patternRequest = new StringBuilder().append("%").append(pattern).append("%").toString();
			stmt.setString(1, patternRequest);
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				Computer computer = ComputerMapper.map(resultSet);
				Optional<Company> companyOpt = daoFactory.getCompanyDAO().get(computer.getCompany().getId());
				if (companyOpt.isPresent()) {
					computer.setCompany(companyOpt.get());
				}
				computerListArg.add(computer);
			}
			return computerListArg;
		}).run(result).getResult();
	}

	public List<Computer> getPatternOrderBy(String pattern, String order, boolean isDesc) throws DAOException {
		TransactionHandler<String, List<Computer>> transactionHandler = TransactionHandler
				.create((Connection connection, String request) -> {
					ArrayList<Computer> computerListArg = new ArrayList<Computer>();
					PreparedStatement stmt = connection.prepareStatement(request);
					String patternRequest = new StringBuilder().append("%").append(pattern).append("%").toString();
					stmt.setString(1, patternRequest);
					ResultSet resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						Computer computer = ComputerMapper.map(resultSet);
						Optional<Company> company = daoFactory.getCompanyDAO().get(computer.getCompany().getId());
						if (company.isPresent()) {
							computer.setCompany(company.get());
						}
						computerListArg.add(computer);
					}
					return computerListArg;
				});
		String desc = isDesc ? " DESC" : "";
		if (Arrays.asList(COMPUTER_COLUMN).contains(order)) {
			StringBuilder req = new StringBuilder().append(SQL_GET_LIKE_ORDER_BY).append(order).append(" IS NULL ASC, ").append(order).append(desc);
			return transactionHandler.run(req.toString()).getResult();
		} else if (order.equals("companyName")) {
			return transactionHandler.run(SQL_GET_LIKE_ORDER_BY_COMPANY_NAME + desc).getResult();
		} else {
			return getPattern(pattern);
		}
	}
}