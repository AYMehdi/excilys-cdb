package com.excilys.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.exception.DAOException;
import com.excilys.model.Company;
import com.excilys.model.dto.CompanyDTO;

@Component 
public class CompanyMapper implements RowMapper<Company> {
	
// ******* VARIABLE *******
	private static Logger logger = LoggerFactory.getLogger(CompanyMapper.class);

// ******* METHODS *******
	/**
	 * Return a CompanyDTO mapped with a Company associated
	 * @param company Company object
	 * @return companyDTO CompanyDTO
	 */
	public static CompanyDTO companyToCompanyDTO(Company company) {
		CompanyDTO companyDTO = new CompanyDTO();
		
		companyDTO.setId(company.getId());
		companyDTO.setName(company.getName());
		
		return companyDTO;
	}
	
	/**
	 * Return a Company mapped with a CompanyDTO associated
	 * @param companyDTO CompanyDTO object
	 * @return company Company
	 */
	public static Company companyDTOToCompany(CompanyDTO companyDTO) {
		Company company = new Company();
		
		company.setId(companyDTO.getId());
		company.setName(companyDTO.getName());
		
		return company;
	}

	/**
	 * Return a Company mapped with a SQL Company resultSet associated
	 * @param resultSet ResultSet object
	 * @return company Company
	 */
	public static Company resultSetToCompany(ResultSet resultSet) throws DAOException {
		Company company = new Company();

		try {
			company.setId(resultSet.getInt(1));
			company.setName(resultSet.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException", e);
			throw new DAOException("SQLException in com.excilys.model.mapper.CompanyMapper.java, method resultSetToCompany(ResultSet resultSet)");
		}

		return company;
	}

	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		try {
			return resultSetToCompany(rs);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}
}