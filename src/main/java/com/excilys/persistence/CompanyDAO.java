package main.java.com.excilys.persistence;

import main.java.com.excilys.exception.DAOException;
import main.java.com.excilys.model.Company;

import java.util.ArrayList;

public interface CompanyDAO {
	
	ArrayList<Company> companiesList() throws DAOException;
	Company find(int pId) throws DAOException;
	
	public void add(Company company);
	public void update(Company company);
	public void remove(Company company);
}