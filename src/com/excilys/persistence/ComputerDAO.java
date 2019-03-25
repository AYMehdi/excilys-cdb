package com.excilys.persistence;

import com.excilys.model.Computer;

import java.util.ArrayList;

public interface ComputerDAO {
	
	ArrayList<Computer> computersList() throws DAOException;
	Computer find(int pId) throws DAOException;
	
	public void add(Computer computer);
	public void update(Computer computer);
	public void remove(Computer computer);
}