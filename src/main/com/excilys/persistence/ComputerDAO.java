package main.com.excilys.persistence;

import java.util.ArrayList;

import main .com.excilys.model.Computer;

public interface ComputerDAO {
	
	ArrayList<Computer> computersList() throws DAOException;
	Computer find(int pId) throws DAOException;
	
	public void add(Computer computer);
	public void update(Computer computer);
	public void remove(Computer computer);
}