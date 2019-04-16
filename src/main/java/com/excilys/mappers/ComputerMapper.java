package main.java.com.excilys.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.excilys.models.Company;
import main.java.com.excilys.models.Computer;

public class ComputerMapper {
	
	private static ComputerMapper instance = null;

	public ComputerMapper() {}

	public static ComputerMapper getInstance() {
		if (instance == null) {
			instance = new ComputerMapper();
		}
		return instance;
	}
	
	public static Computer map(ResultSet resultSet) throws SQLException {
	    Computer computer = new Computer();
//	    DAOFactory daoFactory = DAOFactory.getInstance();
//	    CompanyDAO companyDAO = daoFactory.getCompanyDao();
	    
	    computer.setId(resultSet.getInt("id"));
	    computer.setName(resultSet.getString("name"));
	    computer.setCompany(new Company(resultSet.getInt("company_id")));
	    computer.setIntroducedDate(resultSet.getTimestamp("introduced"));
	    computer.setDiscontinuedDate( resultSet.getTimestamp("discontinued"));
	       
	    return computer;
	}
}
