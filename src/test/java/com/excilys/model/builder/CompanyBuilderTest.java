package com.excilys.model.builder;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.exception.ModelException;
import com.excilys.model.Company;

public class CompanyBuilderTest {
	
	Company company1;
	Company company2;
	CompanyBuilder companyBuilder;
	
	@Before
	public void setUp() {
		company1 = new Company();
		company2 = new Company();
		companyBuilder = new CompanyBuilder();
	}
	
	@Test
	public void testEmpty() throws ModelException {
		company1 = companyBuilder.empty().build();
		company2 = new Company();
		assertEquals(company1, company2);
	}
	
	@Test
	public void testId() throws ModelException {
		company1 = companyBuilder.withId(1).build();
		company2 = new Company(1, null);
		assertEquals(company1, company2);
	}
	
	@Test
	public void testName() throws ModelException {
		company1 = companyBuilder.withName("company").build();
		company2 = new Company(0, "company");
		assertEquals(company1, company2);
	}
	
	@After
	public void tearDown() {
		company1 = null;
		company2 = null;
		companyBuilder = null;
	}
}