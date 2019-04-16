package main.java.com.excilys.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.com.excilys.models.Company;
import main.java.com.excilys.persistence.CompanyDAO;
import main.java.com.excilys.persistence.DAOFactory;

public class CompanyService {

	// ******* VARIABLES *******
	private CompanyDAO companyDAO;
	
	// ******* CONSTRUCTOR *******
	public CompanyService() {
		this.setCompanyDAO(DAOFactory.getInstance().getCompanyDAO());
	}
	
	// ******* GETTER *******
	public CompanyDAO getCompanyDAO() {
		return this.companyDAO;
	}
	
	// ******* SETTER *******
	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}
	
	// ******* CONSTRUCTOR *******
	public ArrayList<Company> getAll() throws FileNotFoundException, IOException, SQLException{
		return companyDAO.getAll();
	}
}