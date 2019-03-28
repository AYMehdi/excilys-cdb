package main.com.excilys.persistence;

import main .com.excilys.model.Company;
import main.com.excilys.persistence.DAOException;

import java.util.ArrayList;

public interface CompanyDAO {
	
	ArrayList<Company> companiesList() throws DAOException;
	Company find(int pId) throws DAOException;
	
	public void add(Company company);
	public void update(Company company);
	public void remove(Company company);
}