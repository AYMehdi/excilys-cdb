package com.excilys.validator;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import com.excilys.exception.ValidatorException;
import com.excilys.model.Company;
import com.excilys.model.Computer;

public class ComputerValidatorTest {
	
	Computer computer;
	
	Timestamp date1 = Timestamp.valueOf("1994-07-12 00:00:00");
	Timestamp date2 = Timestamp.valueOf("1995-01-28 00:00:00");
	
	public ComputerValidatorTest() {
		super();
	}
	
	@Before
	public void setUp() {
		computer = new Computer();
	}
	
	@Test
	public void testOk() throws ValidatorException {
		Timestamp introduced = date1;
		Timestamp discontinued = date2;
		computer = new Computer(0, "J.A.R.V.I.S", new Company(),  introduced, discontinued);
		ComputerValidator.validateComputer(computer);
	}
	
	@Test(expected = ValidatorException.class)
	public void testException1() throws ValidatorException {
		Timestamp introduced = date1;
		Timestamp discontinued = date2;
		computer = new Computer(0, null, new Company(), introduced, discontinued);
		ComputerValidator.validateComputer(computer);
	}
	
	@Test(expected = ValidatorException.class)
	public void testException2() throws ValidatorException {
		Timestamp discontinued = date2;
		computer = new Computer(0, "J.A.R.V.I.S", new Company(), null, discontinued);
		ComputerValidator.validateComputer(computer);
	}
	
	@Test(expected = ValidatorException.class)
	public void testException3() throws ValidatorException {
		Timestamp introduced = date2;
		Timestamp discontinued = date1;
		computer = new Computer(0, "J.A.R.V.I.S", new Company(), introduced, discontinued);
		ComputerValidator.validateComputer(computer);
	}
	
}
