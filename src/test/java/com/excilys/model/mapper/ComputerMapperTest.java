package com.excilys.model.mapper;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.exception.ModelException;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.dto.ComputerDTO;

public class ComputerMapperTest {
	
// ******* VARIABLES *******
	String dateStr1 = "1994-07-12 00:00:00";
	String dateStr2 = "1995-01-28 00:00:00";
	
	Timestamp date1 = Timestamp.valueOf(dateStr1);
	Timestamp date2 = Timestamp.valueOf(dateStr2);
	
	Computer computer;
	Company company;
	ComputerDTO computerDTO;
	
// ******* BEFORE *******
	@Before
	public void setUp() {
		computer = new Computer();
		company = new Company(7, "Stark Industries");
		computerDTO = new ComputerDTO();
	}
	
// ******* TEST *******
	@Test
	public void computerToComputerDTOTest() {
		computer = new Computer(12, "J.A.R.V.I.S", company, date1, date2);
		computerDTO = ComputerMapper.computerToComputerDTO(computer);
		assertEquals(computer.getId(), computerDTO.getComputerId());
		assertEquals(computer.getName(), computerDTO.getComputerName());
		assertEquals(computer.getCompany().getId(), computerDTO.getCompanyId());
		assertEquals(computer.getCompany().getName(), computerDTO.getCompanyName());
		assertEquals(TimestampMapper.timestampToString(computer.getIntroducedDate()).substring(0,10), computerDTO.getIntroducedDate().substring(0,10));
		assertEquals(TimestampMapper.timestampToString(computer.getDiscontinuedDate()).substring(0,10), computerDTO.getDiscontinuedDate().substring(0,10));
		}
	
	@Test
	public void computerDTOToComputerTest() throws ModelException {
		computerDTO = new ComputerDTO(12, "J.A.R.V.I.S", company.getId(), company.getName(), dateStr1, dateStr2);
		computer = ComputerMapper.computerDTOToComputer(computerDTO);
		assertEquals(computer.getId(), computerDTO.getComputerId());
		assertEquals(computer.getName(), computerDTO.getComputerName());
		assertEquals(computer.getCompany().getId(), computerDTO.getCompanyId());
		assertEquals(computer.getCompany().getName(), computerDTO.getCompanyName());
		assertEquals(TimestampMapper.timestampToString(computer.getIntroducedDate()).substring(0,10), computerDTO.getIntroducedDate().substring(0,10));
		assertEquals(TimestampMapper.timestampToString(computer.getDiscontinuedDate()).substring(0,10), computerDTO.getDiscontinuedDate().substring(0,10));
		}
	
// ******* AFTER *******
	@After
	public void tearDown() {
		computer = null;
		computerDTO = null;
		company = null;
	}
}