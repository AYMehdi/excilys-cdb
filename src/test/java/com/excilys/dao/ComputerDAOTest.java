package com.excilys.dao;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.excilys.exception.DAOException;
import com.excilys.exception.ModelException;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.mapper.ComputerMapper;


public class ComputerDAOTest {
	
// ******* VARIABLE *******
	@Mock
	ResultSet resultSet;

// ******* CONSTRUCTOR *******
	public ComputerDAOTest() {
		super();
	}
		
// ******* BEFORE *******
	@Before
	public void setUp() throws SQLException {
		this.resultSet = Mockito.mock(ResultSet.class);
		Mockito.when(this.resultSet.next()).thenReturn(true);
		Mockito.when(this.resultSet.getInt(1)).thenReturn(1);
		Mockito.when(this.resultSet.getString(2)).thenReturn("J.A.R.V.I.S");
		Mockito.when(this.resultSet.getTimestamp(3)).thenReturn(Timestamp.valueOf("1994-07-12 00:00:00"));
		Mockito.when(this.resultSet.getTimestamp(4)).thenReturn(Timestamp.valueOf("1995-01-28 00:00:00"));
		Mockito.when(this.resultSet.getInt(5)).thenReturn(1);
		Mockito.when(this.resultSet.getString(6)).thenReturn("Stark Industries");
	}
	
// ******* TEST *******
	@Test
	public void testComputer() throws SQLException, DAOException, ModelException {
		Computer computer = ComputerMapper.resultSetToComputer(this.resultSet);
		
		assertEquals(computer.getId(), 1);
		assertEquals(computer.getName(),"J.A.R.V.I.S");
		assertEquals(computer.getCompany(), new Company(1, "Stark Industries")); 
		assertEquals(computer.getIntroducedDate(),Timestamp.valueOf("1994-07-12 00:00:00"));
		assertEquals(computer.getDiscontinuedDate(),Timestamp.valueOf("1995-01-28 00:00:00"));
	}
	
// ******* AFTER *******
	public void tearDown() {
		this.resultSet = null;
	}
}