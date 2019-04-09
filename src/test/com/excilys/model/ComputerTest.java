package test.com.excilys.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.com.excilys.model.Company;
import main.java.com.excilys.model.Computer;

public class ComputerTest {
	Computer computer1;
	Computer computer2;
	Company company;
	Timestamp introducedDate;
	Timestamp discontinuedDate;
	
	@Before
	public void setUp() {
		this.company = new Company(12, "companyName");
		this.introducedDate = new Timestamp(1207);
		this.discontinuedDate = new Timestamp(1994);
		
		this.computer1 = new Computer();
		this.computer2 = new Computer(12, "computerName", this.company, this.introducedDate, this.discontinuedDate);
	}
	
	
	@Test
	public void testEquals() {
		assertTrue(this.computer1.equals(this.computer1));
		assertFalse(this.computer1.equals(this.computer2));
		
	}
	
	public void testNotNull() {
		assertNotNull(this.computer1);
		assertNotNull(this.computer2);
	}
	
	@After
	public void tearDown() {
		this.computer1 = null;
		this.computer2 = null;
		this.company = null;
		this.introducedDate = null;
		this.discontinuedDate = null;
	}
}
