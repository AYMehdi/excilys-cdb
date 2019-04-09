package test.com.excilys.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.com.excilys.model.Company;

public class CompanyTest {
	Company company1;
	Company company2;
	int id;
	String name;
	
	@Before
	public void setUp() {
		this.id = 1207;
		this.name = "Dell";
		
		this.company1 = new Company(this.id, this.name);
		company2 = new Company(1994, "Apple");
	}
	
	
	@Test
	public void testEquals() {
		assertTrue(this.company1.equals(this.company1));
		assertFalse(this.company1.equals(this.company2));
		
	}
	
	public void testNotNull() {
		assertNotNull(this.company1);
		assertNotNull(this.company2);
	}
	
	@After
	public void tearDown() {
		this.company1 = null;
		this.company2 = null;
	}
}
