package com.excilys.persistence;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.exception.DAOException;
import com.excilys.model.Company;
import com.excilys.model.mapper.CompanyMapper;

@Repository
public class CompanyDAO {
	
// ******** QUERIES *******
	private final String SQL_GET_ALL = "SELECT id, name FROM company";
	private final String SQL_GET_ALL_ORDER_BY_NAME = "SELECT id, name FROM company ORDER BY name";
	private final String SQL_GET_BY_ID = "SELECT id, name FROM company WHERE id = ?";
	private final String SQL_GET_BY_NAME = "SELECT id, name FROM company WHERE name = ?";
	private final String SQL_GET_LIST = "SELECT id, name FROM company WHERE id >= ? AND id <= ?";
	private final String SQL_INSERT = "INSERT INTO company (name) VALUES (?)";
	private final String SQL_UPDATE = "UPDATE company SET name = ? WHERE id = ?";
	private final String SQL_DELETE = "DELETE FROM company WHERE id = ?";
	private final String SQL_DELETE_COMPUTERS = "DELETE FROM computer WHERE company_id = ?";
	private final String SQL_COUNT = "SELECT COUNT(id) AS len FROM company";

// ******** VARIABLES *******
	@Autowired
	DataSource dataSource;
	
	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	CompanyDAO companyDAO;
	
	ConnectionDAO connectionDAO;
	
// ******** METHODS *******
	/**
	 * Return Company informations
	 * @param id Company ID
	 * @return Optional<Company>
	 * @throws SQLException 
	 * @throws DAOException
	 */
	public Optional<Company> find(int id) throws SQLException, DAOException {
		Company company = null;
		
		try {
			if(dataSource == null) {
				connectionDAO = ConnectionDAO.getInstance(ConnectionDAO.DAO_PROPERTIES);
				dataSource = connectionDAO.getDataSource();
			}
			JdbcTemplate select = new JdbcTemplate(dataSource);
			company = select.queryForObject(SQL_GET_BY_ID, new Object[] {id}, companyMapper);
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException("Sorry, there is no company associated with this ID", id);
		} 
		
		return  Optional.of(company);
	}

	/**
	 * Return Company informations
	 * @param name Company name
	 * @return Optional<Company>
	 * @throws SQLException 
	 */
	public Optional<Company> find(String name) throws SQLException {
		Company company = null;
		
		try {
			JdbcTemplate select = new JdbcTemplate(dataSource);
			company = select.queryForObject(SQL_GET_BY_NAME, new Object[] {name}, companyMapper);
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException("Sorry, there is no company associated with this name", 0);
		}
		
		return Optional.of(company);
	}

	/**
	 * List all companies in the database
	 * @return List<Company>
	 * @throws SQLException 
	 */
	public List<Company> getAll() throws SQLException {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(SQL_GET_ALL, companyMapper);
	}

	/**
	 * List all companies in the database, order by name
	 * @return List<Company>
	 * @throws SQLException 
	 */
	public List<Company> getAllOrderByName() throws SQLException {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(SQL_GET_ALL_ORDER_BY_NAME, companyMapper);
	}
	
	/**
	 * List all companies from idFrom until idUntil in the database
	 * @return List<Company>
	 * @throws SQLException 
	 */
	public List<Company> getById(int idFrom, int idUntil) throws SQLException {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(SQL_GET_LIST, new Object[] {idFrom, idUntil}, companyMapper);
	}

	/**
	 * Return List company size 
	 * @return size
	 * @throws SQLException 
	 */
	public int size() throws SQLException {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		return update.queryForObject(SQL_COUNT, int.class);
	}

	/**
	 * Add a Company in the database
	 * @param company Company
	 * @return Optional<Company>
	 * @throws SQLException 
	 */
	public Optional<Company> add(Company company) throws SQLException {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		update.update(SQL_INSERT, new Object[] {company.getName()});
		return Optional.of(company);
	}

	/**
	 * Update a Company in the database
	 * @param company Company
	 * @return Optional<Company>
	 * @throws SQLException 
	 */
	public Optional<Company> update(Company company) throws SQLException {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		update.update(SQL_UPDATE, new Object[] {company.getName(), company.getId()} );
		return Optional.of(company);
	}
	
	/**
	 * Remove company from the database
	 * @param company Company
	 * @throws SQLException 
	 */
	public void remove(Company company) throws SQLException {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		update.update(SQL_DELETE_COMPUTERS, new Object[] {company.getId()} );
		update.update(SQL_DELETE, new Object[] {company.getId()} );
	}
}