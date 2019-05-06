package com.excilys.model.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.exception.ModelException;
import com.excilys.model.builder.ComputerDTOBuilder;

public class ComputerDTOTest {

// ******* VARIABLES *******
	String date1 = "1994-07-12 00:00:00";
	String date2 = "1995-01-28 00:00:00";
	
	private ComputerDTO computerDTO1;
	private ComputerDTO computerDTO2;
	private ComputerDTO computerDTO3;
	
	private ComputerDTOBuilder computerDTOBuilder1 = new ComputerDTOBuilder();
	private ComputerDTOBuilder computerDTOBuilder2 = new ComputerDTOBuilder();
	
// ******* CONSTRUCTOR *******
	public ComputerDTOTest() {
		super();
	}

// ******* BEFORE *******
	@Before
	public void setUp() throws ModelException {
		computerDTO1 = new ComputerDTO();
		computerDTO2 = computerDTOBuilder2.empty().build();
		computerDTO3 = new ComputerDTO(12, "J.A.R.V.I.S", 7, "Stark Industries", date1, date2);
	}

// ******* TEST *******
	@Test
	public void testEmpty() {
		assertEquals(computerDTO1, computerDTO2);
		assertNotNull(computerDTO1);
	}

	@Test
	public void testComputerId() throws ModelException {
		// test 1
		assertEquals(computerDTO1.getComputerId(), computerDTO2.getComputerId());
		assertNotEquals(computerDTO1.getComputerId(), computerDTO3.getComputerId());
		
		// test 2
		computerDTO1 = computerDTOBuilder1.withComputerId(12).build();
		computerDTO2 = computerDTOBuilder2.withComputerId(7).build();
		assertNotEquals(computerDTO1.getComputerId(), computerDTO2.getComputerId());
		assertEquals(computerDTO1.getComputerId(), computerDTO3.getComputerId());

		// test 3
		computerDTO2.setComputerId(12);;
		assertEquals(computerDTO1.getComputerId(), computerDTO2.getComputerId());
		assertEquals(computerDTO3.getComputerId(), computerDTO2.getComputerId());
	}

	@Test
	public void testComputerName() throws ModelException {
		// test 1
		computerDTO1 = new ComputerDTO();
		computerDTO2 = computerDTOBuilder2.empty().build();
		assertEquals(computerDTO1.getComputerName(), computerDTO2.getComputerName());
		assertNotEquals(computerDTO1.getComputerName(), computerDTO3.getComputerName());

		// test 2
		computerDTO1.setComputerName("J.A.R.V.I.S");
		computerDTO2.setComputerName("JARVIS");
		assertNotEquals(computerDTO1.getComputerName(), computerDTO2.getComputerName());
		assertEquals(computerDTO1.getComputerName(), computerDTO3.getComputerName());

		// test 3
		computerDTO2.setComputerName("J.A.R.V.I.S");
		assertEquals(computerDTO1.getComputerName(), computerDTO2.getComputerName());
		assertEquals(computerDTO3.getComputerName(), computerDTO2.getComputerName());
	}

	@Test
	public void testCompanyId() throws ModelException {
		// test 1
		computerDTO1 = new ComputerDTO();
		computerDTO2 = computerDTOBuilder2.empty().build();
		assertEquals(computerDTO1.getCompanyId(), computerDTO2.getCompanyId());
		assertNotEquals(computerDTO1.getCompanyId(), computerDTO3.getCompanyId());
		
		// test 2
		computerDTO1 = computerDTOBuilder1.withCompanyId(7).build();
		computerDTO2 = computerDTOBuilder2.withCompanyId(12).build();
		assertNotEquals(computerDTO1.getCompanyId(), computerDTO2.getCompanyId());
		assertEquals(computerDTO1.getCompanyId(), computerDTO3.getCompanyId());

		// test 3
		computerDTO2.setCompanyId(7);;
		assertEquals(computerDTO1.getCompanyId(), computerDTO2.getCompanyId());
		assertEquals(computerDTO3.getCompanyId(), computerDTO2.getCompanyId());
	}

	@Test
	public void testCompanyName() throws ModelException {
		// test 1
		computerDTO1 = new ComputerDTO();
		computerDTO2 = computerDTOBuilder2.empty().build();
		assertEquals(computerDTO1.getCompanyName(), computerDTO2.getCompanyName());
		assertNotEquals(computerDTO1.getCompanyName(), computerDTO3.getCompanyName());

		// test 2
		computerDTO1.setCompanyName("Stark Industries");
		computerDTO2.setCompanyName("StarkIndustries");
		assertNotEquals(computerDTO1.getCompanyName(), computerDTO2.getCompanyName());
		assertEquals(computerDTO1.getCompanyName(), computerDTO3.getCompanyName());

		// test 3
		computerDTO2.setCompanyName("Stark Industries");
		assertEquals(computerDTO1.getCompanyName(), computerDTO2.getCompanyName());
		assertEquals(computerDTO3.getCompanyName(), computerDTO2.getCompanyName());
	}
	
	@Test
	public void testIntroducedDate() throws ModelException {
		// test 1
		computerDTO1 = new ComputerDTO();
		computerDTO2 = computerDTOBuilder2.empty().build();
		assertEquals(computerDTO1.getIntroducedDate(), computerDTO2.getIntroducedDate());
		assertNotEquals(computerDTO1.getIntroducedDate(), computerDTO3.getIntroducedDate());

		// test 2
		computerDTO1.setIntroducedDate(date1);
		computerDTO2.setIntroducedDate(date2);
		assertNotEquals(computerDTO1.getIntroducedDate(), computerDTO2.getIntroducedDate());
		assertEquals(computerDTO1.getIntroducedDate(), computerDTO3.getIntroducedDate());

		// test 3
		computerDTO2.setIntroducedDate(date1);
		assertEquals(computerDTO1.getIntroducedDate(), computerDTO2.getIntroducedDate());
		assertEquals(computerDTO3.getIntroducedDate(), computerDTO2.getIntroducedDate());
	}

	@Test
	public void testDiscontinuedDate() throws ModelException {
		// test 1
		computerDTO1 = new ComputerDTO();
		computerDTO2 = computerDTOBuilder2.empty().build();
		assertEquals(computerDTO1.getDiscontinuedDate(), computerDTO2.getDiscontinuedDate());
		assertNotEquals(computerDTO1.getDiscontinuedDate(), computerDTO3.getDiscontinuedDate());

		// test 2
		computerDTO1.setDiscontinuedDate(date2);
		computerDTO2.setDiscontinuedDate(date1);
		assertNotEquals(computerDTO1.getDiscontinuedDate(), computerDTO2.getDiscontinuedDate());
		assertEquals(computerDTO1.getDiscontinuedDate(), computerDTO3.getDiscontinuedDate());

		// test 3
		computerDTO2.setDiscontinuedDate(date2);
		assertEquals(computerDTO1.getDiscontinuedDate(), computerDTO2.getDiscontinuedDate());
		assertEquals(computerDTO3.getDiscontinuedDate(), computerDTO2.getDiscontinuedDate());
	}

	@Test
	public void testBuilder() throws ModelException {
		// test
		computerDTO1 = computerDTOBuilder1
				.withComputerId(12)
				.withComputerName("J.A.R.V.I.S")
				.withCompanyId(7)
				.withCompanyName("Stark Industries")
				.withIntroducedDate(date1)
				.withDiscontinuedDate(date2)
				.build();
		assertEquals(computerDTO1, computerDTO3);
	}

	
// ******* AFTER *******
	@After
	public void tearDown() {
		this.computerDTO1 = null;
		this.computerDTO2 = null;
		this.computerDTO3 = null;
		this.computerDTOBuilder1 = null;
		this.computerDTOBuilder2 = null;
		this.date1 = null;
		this.date2 = null;
	}
}