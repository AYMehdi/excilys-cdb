package com.excilys.controller;

import java.sql.SQLException;
import java.text.ParseException;

import com.excilys.exception.DAOException;
import com.excilys.exception.ModelException;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;
import com.excilys.view.CommandLineInterface;

public class ControllerCli {
	
	ComputerDAO computerDao;
	CompanyDAO companyDao;
	
	public ControllerCli(ComputerDAO computerDao, CompanyDAO companyDao) throws SQLException, DAOException, ParseException, ModelException {
		this.computerDao = computerDao;
		this.companyDao = companyDao;
		new CommandLineInterface(this.computerDao, this.companyDao);
	}
}