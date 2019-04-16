package main.java.com.excilys.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.excilys.models.Company;

public class CompanyMapper {
	private static CompanyMapper instance = null;

	public CompanyMapper() {}

	public static CompanyMapper getInstance() {
		if (instance == null) {
			instance = new CompanyMapper();
		}
		return instance;
	}
	
	public Company map(ResultSet resultSet) throws SQLException {
	    Company company = new Company();
	    
	    company.setId(resultSet.getInt("id"));
	    company.setName(resultSet.getString("name"));
	    
	    return company;
	}
}