package com.excilys.model.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.exception.ModelException;
import com.excilys.model.builder.CompanyDTOBuilder;

public class CompanyDTOTest {

// ******* VARIABLES *******
	private CompanyDTO companyDTO1;
	private CompanyDTO companyDTO2;
	private CompanyDTO companyDTO3;
	
	private CompanyDTOBuilder companyDTOBuilder1 = new CompanyDTOBuilder();
	private CompanyDTOBuilder companyDTOBuilder2 = new CompanyDTOBuilder();
	
// ******* CONSTRUCTOR *******
	public CompanyDTOTest() {
		super();
	}

// ******* BEFORE *******
	@Before
	public void setUp() throws ModelException {
		companyDTO1 = new CompanyDTO();
		companyDTO2 = companyDTOBuilder2.empty().build();
		companyDTO3 = new CompanyDTO(1, "Stark Industries");
	}

// ******* TEST *******
	@Test
	public void testEmpty() {
		assertEquals(companyDTO1, companyDTO2);
		assertNotNull(companyDTO1);
	}

	@Test
	public void testId() throws ModelException {
		// test 1
		assertEquals(companyDTO1.getId(), companyDTO2.getId());
		assertNotEquals(companyDTO1.getId(), companyDTO3.getId());
		
		// test 2
		companyDTO1 = companyDTOBuilder1.withId(1).build();
		companyDTO2 = companyDTOBuilder2.withId(2).build();
		assertNotEquals(companyDTO1.getId(), companyDTO2.getId());
		assertEquals(companyDTO1.getId(), companyDTO3.getId());

		// test 3
		companyDTO2.setId(1);
		assertEquals(companyDTO1.getId(), companyDTO2.getId());
		assertEquals(companyDTO3.getId(), companyDTO2.getId());
	}

	@Test
	public void testName() throws ModelException {
		// test 1
		companyDTO1 = new CompanyDTO();
		companyDTO2 = companyDTOBuilder2.empty().build();
		assertEquals(companyDTO1.getName(), companyDTO2.getName());
		assertNotEquals(companyDTO1.getName(), companyDTO3.getName());

		// test 2
		companyDTO1.setName("Stark Industries");
		companyDTO2.setName("StarkIndustries");
		assertNotEquals(companyDTO1.getName(), companyDTO2.getName());
		assertEquals(companyDTO1.getName(), companyDTO3.getName());

		// test 3
		companyDTO2.setName("Stark Industries");
		assertEquals(companyDTO1.getName(), companyDTO2.getName());
		assertEquals(companyDTO3.getName(), companyDTO2.getName());
	}

	@Test
	public void testBuilder() throws ModelException {
		// test
		companyDTO1 = companyDTOBuilder1
				.withId(1)
				.withName("Stark Industries")
				.build();
		assertEquals(companyDTO1, companyDTO3);
	}

	
// ******* AFTER *******
	@After
	public void tearDown() {
		this.companyDTO1 = null;
		this.companyDTO2 = null;
		this.companyDTO3 = null;
		this.companyDTOBuilder1 = null;
		this.companyDTOBuilder2 = null;
	}
}