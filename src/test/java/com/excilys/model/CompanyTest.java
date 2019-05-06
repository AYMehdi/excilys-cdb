package com.excilys.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.exception.ModelException;
import com.excilys.model.builder.CompanyBuilder;


public class CompanyTest {
	
// ******* VARIABLES *******
	private CompanyBuilder companyBuilder1 = new CompanyBuilder();
	private CompanyBuilder companyBuilder2 = new CompanyBuilder();
	
	Company company1;
	Company company2;
	Company company3;
	
	String name = "Stark Industries";
	
// ******* CONSTRUCTOR *******
	public CompanyTest() {
		super();
	}
	
// ******* BEFORE *******
	@Before
    public void setUp() throws ModelException {
		company1 = companyBuilder1.empty().build();
		company2 = companyBuilder2.empty().build();
		company3 = new Company(1, this.name);
    }
	
// ******* TEST *******
	@Test
	public void testCompany() {
		// test 1
		assertNotNull(company1);
		assertNotNull(company2);
		assertNotNull(company3);
	
		// test 2
		assertEquals(company3, company3);
	}
	
	@Test
	public void testId() throws ModelException {
		// test 1
		company1 = companyBuilder1.withId(1).build();
		company2 = companyBuilder2.withId(2).build();
		assertNotEquals(company1, company2);
		assertNotEquals(company2, company3);
		
		// test 2
		company2 = companyBuilder2.withId(1).build();
		assertEquals(company1, company2);
	}
	
	@Test
	public void testName() throws ModelException {
		// test 1
		company1 = companyBuilder1.withName(null).build();
		company2 = companyBuilder2.withName(null).build();
		assertEquals(company1, company2);
		assertNotEquals(company1, company3);
		
		// test 2
		company1 = companyBuilder1.withName(this.name).build();
		assertNotEquals(company1, company2);
		assertNotEquals(company2, company1);
		
		// test 3
		company2 = companyBuilder2.withName("Stark Industrie").build();
		assertNotEquals(company1, company2);
		assertNotEquals(company1, company3);
		
		// test 4
		company2 = companyBuilder2.withName(this.name).build();
		assertEquals(company1, company2);
	}
	
	@Test
	public void testBuilder() throws ModelException {
		// test
		company1 = companyBuilder1.withId(1).withName(this.name).build();
		assertEquals(company1, company3);
	}
	
	@Test
	public void testGetters() throws ModelException {
		// test
		company1 = companyBuilder1.withId(1).withName(this.name).build();
		assertEquals(company1.getId(), company3.getId());
		assertEquals(company1.getName(), company3.getName());
	}
	
// ******* AFTER *******
	@After
	public void tearDown() {
		this.company1 = null;
		this.company2 = null;
		this.company3 = null;
		this.companyBuilder1 = null;
		this.companyBuilder2 = null;
	}
}