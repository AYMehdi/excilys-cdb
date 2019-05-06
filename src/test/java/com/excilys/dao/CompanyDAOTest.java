package com.excilys.dao;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.excilys.exception.DAOException;
import com.excilys.model.Company;
import com.excilys.model.mapper.CompanyMapper;

public class CompanyDAOTest {
	
// ******* VARIABLE *******
	@Mock
	ResultSet resultSet;
	
// ******* CONSTRUCTOR *******
	public CompanyDAOTest() {
		super();
	}
	
// ******* BEFORE *******
	@Before
	public void setUp() throws SQLException {
		this.resultSet = Mockito.mock(ResultSet.class);
		Mockito.when(this.resultSet.next()).thenReturn(true);
		Mockito.when(this.resultSet.getInt(1)).thenReturn(1);
		Mockito.when(this.resultSet.getString(2)).thenReturn("Stark Industries");
	}
	
// ******* TEST *******
	@Test
	public void testCompany() throws SQLException, DAOException {
		
		Company company = CompanyMapper.resultSetToCompany(this.resultSet);
		
		assertEquals(company.getId(), 1);
		assertEquals(company.getName(),"Stark Industries");
	}
	
// ******* AFTER *******
	@After
	public void tearDown() {
		this.resultSet = null;
	}
}