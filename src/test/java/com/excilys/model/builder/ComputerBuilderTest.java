package com.excilys.model.builder;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.exception.ModelException;
import com.excilys.model.Company;
import com.excilys.model.Computer;

public class ComputerBuilderTest {
	
// ******* VARIABLES *******
	Computer computer1;
	Computer computer2;
	ComputerBuilder computerBuilder;
	
	@Before
	public void setUp() {
		computer1 = new Computer();
		computer2 = new Computer();
		computerBuilder = new ComputerBuilder();
	}
	
	@Test
	public void testEmpty() throws ModelException {
		computer1 = computerBuilder.empty().build();
		computer2 = new Computer();
		assertEquals(computer1, computer2);
	}
	
	@Test
	public void testId() throws ModelException {
		computer1 = computerBuilder.withId(1).build();
		computer2 = new Computer(1);
		assertEquals(computer1, computer2);
	}
	
	@Test
	public void testName() throws ModelException {
		computer1 = computerBuilder.withName("J.A.R.V.I.S").build();
		computer2 = new Computer("J.A.R.V.I.S");
		assertEquals(computer1, computer2);
	}
	
	@Test
	public void testIntroduced() throws ModelException {
		Timestamp introduced = Timestamp.valueOf("2000-01-01 00:00:00");
		computer1 = computerBuilder.withCompany(new Company()).withIntroducedDate(introduced).build();
		computer2 = new Computer(0, "unknown computer", new Company(), introduced, null);
		assertEquals(computer1, computer2);
	}
	
	@Test
	public void testDiscontinued() throws ModelException {
		Timestamp discontinued = Timestamp.valueOf("2001-01-01 00:00:00");
		computer1 = computerBuilder.withCompany(new Company()).withDiscontinuedDate(discontinued).build();
		computer2 = new Computer(0, "unknown computer", new Company (), null, discontinued);
		assertEquals(computer1, computer2);
	}
	
	@Test
	public void testCompany() throws ModelException {
		Company company = new Company();
		computer1 = computerBuilder.withCompany(company).build();
		computer2 = new Computer(0, "unknown computer", company, null, null);
		assertEquals(computer1, computer2);
	}
	
	@Test
	public void testAllArguments() throws ModelException {
		Timestamp introduced = Timestamp.valueOf("2000-01-01 00:00:00");
		Timestamp discontinued = Timestamp.valueOf("2001-01-01 00:00:00");
		Company company = new Company();
		computer1 = computerBuilder.withId(1).withName("J.A.R.V.I.S").withCompany(company)
				.withIntroducedDate(introduced).withDiscontinuedDate(discontinued).build();
		computer2 = new Computer(1, "J.A.R.V.I.S", company, introduced, discontinued);
		assertEquals(computer1, computer2);
	}
	
	@After
	public void tearDown() {
		computer1 = null;
		computer2 = null;
		computerBuilder = null;
	}
}
