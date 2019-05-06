package com.excilys.persistence;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.model.Computer;
import com.excilys.model.mapper.ComputerMapper;

@Repository
public class ComputerDAO {
	
// ******** QUERIES *******
	private final String SQL_GET_ALL = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name " + "FROM computer AS computer LEFT JOIN company AS company ON computer.company_id = company.id";
	private final String SQL_GET_ALL_ORDER_BY_NAME = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name " + "FROM computer AS computer LEFT JOIN company AS company ON computer.company_id = company.id ORDER BY computer.name";
	private final String SQL_GET_BY_ID = SQL_GET_ALL + " WHERE computer.id = ?";
	private final String SQL_GET_BY_NAME = SQL_GET_ALL + " WHERE computer.name LIKE ? OR company.name LIKE ?";
	private final String SQL_GET_LIST = SQL_GET_ALL + " WHERE computer.id >= ? AND computer.id <= ?";
	private final String SQL_INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
	private final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private final String SQL_DELETE = "DELETE FROM computer WHERE id = ?";
	private final String SQL_COUNT = "SELECT COUNT(id) AS len FROM computer";
	
// ******** VARIABLES *******
	@Autowired
	DataSource dataSource;
	
	@Autowired
	ComputerMapper computerMapper;
	
	@Autowired
	ComputerDAO computerDAO;
	
	@Autowired
	CompanyDAO companyDAO;
	
// ******** METHODS *******
	/**
	 * Return Computer informations
	 * @param id Computer ID
	 * @return Optional<Computer>
	 * @throws SQLException
	 */
	public Optional<Computer> find(int id) throws SQLException {
		Computer computer;
		
		try {
			JdbcTemplate select = new JdbcTemplate(dataSource);
			computer = select.queryForObject(SQL_GET_BY_ID, new Object[] {id}, computerMapper);
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException("Sorry, there is no computer associated with this ID", id);
		}
		
		return Optional.of(computer);
	}

	/**
	 * Return Computer informations
	 * @param name Computer name
	 * @return List<Computer>Remove
	 * @throws SQLException 
	 */
	public List<Computer> find(String name) throws SQLException {
		List<Computer> computer = null;
		
		try {
			JdbcTemplate select = new JdbcTemplate(dataSource);
			computer = select.query(SQL_GET_BY_NAME, new Object[] {"%" + name + "%", "%" + name + "%"}, computerMapper);
		} catch(EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException("Sorry, there is no computer associated with this name", 0);
		}
		
		return computer;
	}

	/**
	 * List all computers in the database
	 * @return List<Computer>
	 * @throws SQLException 
	 */
	public List<Computer> getAll() throws SQLException {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(SQL_GET_ALL, computerMapper);
	}

	/**
	 * List all computers in the database, order by name
	 * @return List<Computer>
	 * @throws SQLException 
	 */
	public List<Computer> getAllOrderByName() throws SQLException {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(SQL_GET_ALL_ORDER_BY_NAME, computerMapper);
	}
	
	/**
	 * List all computers from idFrom until idUntil in the database
	 * @return List<Computer>
	 * @throws SQLException 
	 */
	public List<Computer> getById(int idFrom, int idUntil) throws SQLException {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(SQL_GET_LIST, new Object[] {idFrom, idUntil}, computerMapper);
	}

	/**
	 * Return List computer size 
	 * @return size
	 * @throws SQLException 
	 */
	public int size() throws SQLException {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		return update.queryForObject(SQL_COUNT, int.class);
	}

	/**
	 * Sort List computer by ID or by name
	 * @param sortBy String
	 * @return List<Computer>
	 * @throws SQLException 
	 */
	public List<Computer> sortBy(String choice) throws SQLException {
		String ORDER_BY = SQL_GET_ALL + " ORDER BY computer." + choice.toLowerCase() + " IS NULL, computer." + choice.toLowerCase();
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(ORDER_BY, computerMapper);
	}
	
	/**
	 * Add a Computer in the database
	 * @param computer Computer
	 * @return Optional<Computer>
	 * @throws SQLException 
	 */
	public Optional<Computer> add(Computer computer) throws SQLException {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		update.update(SQL_INSERT, new Object[] {computer.getName(), computer.getIntroducedDate(), computer.getDiscontinuedDate(), computer.getCompany().getId()} );
		return Optional.of(computer);
	}

	/**
	 * Update a Computer in the database
	 * @param computer Computer
	 * @return Optional<Computer>
	 * @throws SQLException 
	 */
	public Optional<Computer> update(Computer computer) throws SQLException {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		update.update(SQL_UPDATE, new Object[] {computer.getName(), computer.getIntroducedDate(), computer.getDiscontinuedDate(), computer.getCompany().getId(), computer.getId()} );
		return Optional.of(computer);
	}

	/**
	 * Remove a Computer of the database
	 * @param computer Computer
	 * @throws SQLException 
	 */
	public void remove(Computer computer) throws SQLException {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		update.update(SQL_DELETE, new Object[] {computer.getId()} );
	}
}