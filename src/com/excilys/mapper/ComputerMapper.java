package com.excilys.mapper;

import com.excilys.model.Computer;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.DAOException;
import com.excilys.persistence.DAOFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComputerMapper {
	
	public Computer map(ResultSet resultSet) throws SQLException {
	    Computer computer = new Computer();
	    DAOFactory daoFactory = DAOFactory.getInstance();
	    CompanyDAO companyDAO = daoFactory.getCompanyDao();
	    
	    computer.setId(resultSet.getInt("id"));
	    computer.setName(resultSet.getString("name"));
	    computer.setIntroducedDate(resultSet.getTimestamp("introduced"));
	    computer.setDiscontinuedDate( resultSet.getTimestamp("discontinued"));
	    
	    try {
	    	computer.setCompany(companyDAO.find(resultSet.getInt("company_id")));
	    } catch (DAOException e) {
	    	computer.setCompany(null);
	    }
	       
	    return computer;
	}
}
