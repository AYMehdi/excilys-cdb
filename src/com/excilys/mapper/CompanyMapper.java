package com.excilys.mapper;

import com.excilys.model.Company;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyMapper {
	
	public Company map(ResultSet resultSet) throws SQLException {
	    Company company = new Company();
	    
	    company.setId(resultSet.getInt("id"));
	    company.setName(resultSet.getString("name"));
	    
	    return company;
	}
}