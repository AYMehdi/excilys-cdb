package com.excilys.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.exception.DAOException;
import com.excilys.exception.ValidatorException;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.dto.ComputerDTO;
import com.excilys.model.mapper.ComputerMapper;
import com.excilys.model.mapper.TimestampMapper;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;
import com.excilys.validator.ComputerValidator;

@Service
public class ComputerService {
	
// ******** VARIABLES *******
	@Autowired 
	ComputerDAO computerDAO;
	
	@Autowired
	CompanyDAO companyDAO;
	
// ******** CONSTRUCTOR *******
	/**
	 * Constructor without parameter
	 */
	public ComputerService() {
		super();
	}

// ******** SERVICES *******
	/**
	 * Find a computer in the database with its ID
	 * @param id Computer ID
	 * @return computerDTO Optional<ComputerDTO>
	 * @throws SQLException
	 */
	public Optional<ComputerDTO> find(String id) throws SQLException {
		int computerID = 0;
		Optional<Computer> computer = Optional.empty();
		Optional<ComputerDTO> computerDTO = Optional.empty();
		
		if (id != null && id != "") {
			computerID = Integer.parseInt(id);
			computer = computerDAO.find(computerID);
			
			if (computer.isPresent()) {
				computerDTO = Optional.of(ComputerMapper.computerToComputerDTO(computer.get()));
			}
		}
		
		return computerDTO;
	}

	/**
	 * List all computers in the database
	 * @return computersDTO List<ComputerDTO>
	 * @throws SQLException
	 */
	public List<ComputerDTO> getAll() throws SQLException {
		List<Computer> computers = computerDAO.getAll();
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();

		for (Computer computer : computers) {
			computersDTO.add(ComputerMapper.computerToComputerDTO(computer));
		}

		return computersDTO;
	}

	/**
	 * Add a computer in the database
	 * @param computerDTO ComputerDTO
	 * @throws ValidatorException
	 * @throws SQLException
	 * @throws DAOException 
	 */
	public void add(ComputerDTO computerDTO) throws ValidatorException, SQLException, DAOException {
		Optional<Timestamp> introducedDate = Optional.empty();
		Timestamp introduced = null;
		if (computerDTO.getIntroducedDate() != null) {
			introducedDate = TimestampMapper.stringToTimestamp(computerDTO.getIntroducedDate());
			if (introducedDate.isPresent()) {
				introduced = introducedDate.get();
			}
		} 

		Optional<Timestamp> discontinuedDate = Optional.empty();
		Timestamp discontined = null;
		if (computerDTO.getDiscontinuedDate() != null) {
			discontinuedDate = TimestampMapper.stringToTimestamp(computerDTO.getDiscontinuedDate());
			if (discontinuedDate.isPresent()) {
				discontined = discontinuedDate.get();
			}
		} 

		Optional<Company> company = companyDAO.find(computerDTO.getCompanyId());
		Computer computer;
		if (company.isPresent()) {
			computer = new Computer(computerDTO.getComputerName(), company.get(), introduced, discontined);
		} else {
			computer = new Computer(computerDTO.getComputerName(), new Company(), introduced, discontined);
		}
		
		ComputerValidator.validateComputer(computer);
		computerDAO.add(computer);
	}

	/**
	 * Update a computer in the database
	 * @param computerDTO ComputerDTO
	 * @throws ValidatorException
	 * @throws SQLException
	 * @throws DAOException 
	 */
	public void edit(ComputerDTO computerDTO) throws ValidatorException, SQLException, DAOException {
		Optional<Timestamp> introducedDate = Optional.empty();
		Timestamp introduced = null;
		if (computerDTO.getIntroducedDate() != null) {
			introducedDate = TimestampMapper.stringToTimestamp(computerDTO.getIntroducedDate());
			if (introducedDate.isPresent()) {
				introduced = introducedDate.get();
			}
		} 

		Optional<Timestamp> discontinuedDate = Optional.empty();
		Timestamp discontined = null;
		if (computerDTO.getDiscontinuedDate() != null) {
			discontinuedDate = TimestampMapper.stringToTimestamp(computerDTO.getDiscontinuedDate());
			if (discontinuedDate.isPresent()) {
				discontined = discontinuedDate.get();
			}
		} 

		Optional<Company> company = companyDAO.find(computerDTO.getCompanyId());
		Computer computer;
		if (company.isPresent()) {
			computer = new Computer(computerDTO.getComputerId(), computerDTO.getComputerName(), company.get(), introduced, discontined);
		} else {
			computer = new Computer(computerDTO.getComputerId(), computerDTO.getComputerName(), new Company(), introduced, discontined);
		}
		
		ComputerValidator.validateComputer(computer);
		computerDAO.update(computer);
	}

	/**
	 * Remove a computer in the database
	 * @param id Computer ID
	 * @throws SQLException
	 */
	public void remove(String id) throws SQLException {
		int computerID = 0;
		if (id != null && id != "") {
			computerID = Integer.parseInt(id);
		}

		Optional<Computer> computer = computerDAO.find(computerID);
		if(computer.isPresent()) {
			computerDAO.remove(computer.get());
		}
	}

	/**
	 * Search computer in the database by name
	 * @param name String
	 * @return computersDTO List<ComputerDTO>
	 * @throws SQLException
	 */
	public List<ComputerDTO> searchComputers(String name) throws SQLException {
		List<Computer> computers = computerDAO.find(name);
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		
		for (Computer computer : computers) {
			ComputerDTO computerDTO = ComputerMapper.computerToComputerDTO(computer);
			computersDTO.add(computerDTO);
		}
		
		return computersDTO;
	}

	/**
	 * Sort computer list by ID or name
	 * @param choice String
	 * @return computersDTO List<ComputerDTO>
	 * @throws SQLException
	 */
	public List<ComputerDTO> sortBy(String choice) throws SQLException {
		List<Computer> computers = computerDAO.sortBy(choice);
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			ComputerDTO computerDto = ComputerMapper.computerToComputerDTO(computer);
			computersDTO.add(computerDto);
		}
		return computersDTO;
	}
}