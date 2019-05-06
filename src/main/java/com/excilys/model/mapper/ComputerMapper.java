package com.excilys.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.exception.DAOException;
import com.excilys.exception.ModelException;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.builder.ComputerBuilder;
import com.excilys.model.dto.ComputerDTO;
import com.excilys.persistence.CompanyDAO;

@Component
public class ComputerMapper implements RowMapper<Computer> {

// ******* VARIABLES *******
	private static Logger logger = LoggerFactory.getLogger(CompanyMapper.class);
	
	@Autowired
	CompanyDAO companyDAO;
	
// ******* METHODS *******
	/**
	 * Return a ComputerDTO mapped with a Computer associated
	 * @param computer Computer object
	 * @return computerDTO ComputerDTO
	 */
	public static ComputerDTO computerToComputerDTO(Computer computer) {
		ComputerDTO computerDTO = new ComputerDTO();
		
		computerDTO.setComputerId(computer.getId());
		computerDTO.setComputerName(computer.getName());
		
		if (computer.getCompany() != null) {
			computerDTO.setCompanyId(computer.getCompany().getId());
			computerDTO.setCompanyName(computer.getCompany().getName());
		}

		if (computer.getIntroducedDate() != null) {
			computerDTO.setIntroducedDate(TimestampMapper.timestampToString(computer.getIntroducedDate()));
		} 

		if (computer.getDiscontinuedDate() != null) {
			computerDTO.setDiscontinuedDate(TimestampMapper.timestampToString(computer.getDiscontinuedDate()));
		} 

		return computerDTO;
	}
	
	/**
	 * Return a Computer mapped with a ComputerDTO associated
	 * @param computerDTO ComputerDTO object
	 * @return computer Computer
	 * @throws ModelException 
	 */
	public static Computer computerDTOToComputer(ComputerDTO computerDTO) throws ModelException {
		Computer computer = new ComputerBuilder()
				.withId(computerDTO.getComputerId())
				.withName(computerDTO.getComputerName())
				.withCompany(new Company(computerDTO.getCompanyId(), computerDTO.getCompanyName()))
				.withIntroducedDate(Timestamp.valueOf(computerDTO.getIntroducedDate()))
				.withDiscontinuedDate(Timestamp.valueOf(computerDTO.getDiscontinuedDate()))
				.build();
		return computer;
	}

	/**
	 * Return a Computer mapped with a SQL Computer resultSet associated
	 * @param resultSet ResultSet object
	 * @return computer Computer
	 */
	public static Computer resultSetToComputer(ResultSet resultSet) throws DAOException, ModelException {
		Computer computer = new Computer();

		try {
			computer.setId(resultSet.getInt(1));
			computer.setName(resultSet.getString(2));
			computer.setIntroducedDate(resultSet.getTimestamp(3));
			computer.setDiscontinuedDate(resultSet.getTimestamp(4));
			int companyId = resultSet.getInt(5);
			String companyName = resultSet.getString(6);
			computer.setCompany(new Company(companyId, companyName));
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException", e);
			throw new DAOException("SQLException in com.excilys.model.mapper.ComputerMapper.java, method resultSetToComputer(ResultSet resultSet)");
		} 
		return computer;
	}

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		try {
			return resultSetToComputer(rs);
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (ModelException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}