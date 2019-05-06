package com.excilys.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.exception.ModelException;
import com.excilys.model.builder.CompanyBuilder;
import com.excilys.model.builder.ComputerBuilder;

public class ComputerTest {

// ******* VARIABLES *******
	Timestamp date1 = Timestamp.valueOf("1994-07-12 00:00:00");
	Timestamp date2 = Timestamp.valueOf("1995-01-28 00:00:00");
	
	private ComputerBuilder computerBuilder1 = new ComputerBuilder();
	private ComputerBuilder computerBuilder2 = new ComputerBuilder();

	private Computer computer1;
	private Computer computer2;
	private Computer computer3;
	
// ******* CONSTRUCTOR *******
	public ComputerTest() {
		super();
	}

// ******* BEFORE *******
	@Before
	public void setUp() throws ModelException {
		computer1 = computerBuilder1.empty().build();
		computer2 = computerBuilder2.empty().build();
		computer3 = new Computer(0, "no name", new Company(), date1, date2);
	}

// ******* TEST *******
	@Test
	public void testEmpty() {
		assertEquals(computer1, computer1);
		assertNotNull(computer1);
	}

	@Test
	public void testId() throws ModelException {
		// test 1
		computer1 = computerBuilder1.withId(1).build();
		computer2 = computerBuilder2.withId(2).build();
		assertNotEquals(computer1, computer2);
		assertNotEquals(computer1, computer3);

		// test 2
		computer2 = computerBuilder2.withId(1).build();
		assertEquals(computer1, computer2);
	}

	@Test
	public void testName() throws ModelException {
		// test 1
		computer1 = computerBuilder1.withName(null).build();
		computer2 = computerBuilder2.withName(null).build();
		assertEquals(computer1, computer2);

		// test 2
		computer1 = computerBuilder1.withName("J.A.R.V.I.S").build();
		assertNotEquals(computer1, computer2);
		assertNotEquals(computer2, computer1);

		// test 3
		computer2 = computerBuilder2.withName("JARVIS").build();
		assertNotEquals(computer1, computer2);
		assertNotEquals(computer1, computer3);
		
		// test 4
		computer2 = computerBuilder2.withName("J.A.R.V.I.S").build();
		assertEquals(computer1, computer2);
	}

	@Test
	public void testCompany() throws ModelException {
		CompanyBuilder companyBuilder1 = new CompanyBuilder();
		CompanyBuilder companyBuilder2 = new CompanyBuilder();
		
		// test 1
		computer1 = computerBuilder1.withCompany(null).build();
		computer2 = computerBuilder2.withCompany(null).build();
		assertEquals(computer1, computer2);

		// test 2
		Company company1 = companyBuilder1.empty().build();
		Company company2 = companyBuilder2.withId(1).build();
		computer1 = computerBuilder1.withCompany(company1).build();
		computer2 = computerBuilder2.withCompany(company2).build();
		assertNotEquals(computer1, computer2);
		assertNotEquals(computer2, computer1);
		
		// test 3
		company1 = companyBuilder1.withId(1).withName("Stark Industries").build();
		company2 = companyBuilder2.withId(1).withName("Stark Industrie").build();
		computer1 = computerBuilder1.withCompany(company1).build();
		computer2 = computerBuilder2.withCompany(company2).build();
		assertNotEquals(computer1, computer2);
		assertNotEquals(computer1, new Company(12, "Stark Industries"));
		
		// test 4
		computer1 = computerBuilder1.withCompany(company1).build();
		computer2 = computerBuilder2.withCompany(company1).build();
		assertEquals(computer1, computer2);
	}
	
	@Test
	public void testIntroducedDate() throws ModelException {
		// test 1
		computer1 = computerBuilder1.withIntroducedDate(null).build();
		computer2 = computerBuilder2.withIntroducedDate(null).build();
		assertEquals(computer1, computer2);

		// test 2
		computer1 = computerBuilder1.withIntroducedDate(date1).build();
		assertNotEquals(computer1, computer2);
		assertNotEquals(computer2, computer1);

		// test 3
		computer2 = computerBuilder2.withIntroducedDate(date2).build();
		assertNotEquals(computer1, computer2);
		assertNotEquals(computer2, computer3);

		// test 5
		computer2 = computerBuilder2.withIntroducedDate(date1).build();
		assertEquals(computer1, computer2);
	}

	@Test
	public void testDiscontinuedDate() throws ModelException {
		// test 1
		computer1 = computerBuilder1.withDiscontinuedDate(null).build();
		computer2 = computerBuilder2.withDiscontinuedDate(null).build();
		assertEquals(computer1, computer2);

		// test 2
		computer1 = computerBuilder1.withDiscontinuedDate(date1).build();
		assertNotEquals(computer1, computer2);
		assertNotEquals(computer2, computer1);

		// test 3
		computer2 = computerBuilder2.withDiscontinuedDate(date2).build();
		assertNotEquals(computer1, computer2);
		assertNotEquals(computer1, computer3);

		// test 4
		computer2 = computerBuilder2.withDiscontinuedDate(date1).build();
		assertEquals(computer1, computer2);
	}

	@Test
	public void testBuilder() throws ModelException {
		// test
		computer1 = computerBuilder1
				.withId(0)
				.withName("no name")
				.withCompany(new Company())
				.withIntroducedDate(date1)
				.withDiscontinuedDate(date2)
				.build();
		assertEquals(computer1, computer3);
	}
	
	@Test
	public void testGetters() throws ModelException {
		// test
		computer1 = computerBuilder1
				.withId(0)
				.withName("no name")
				.withCompany(new Company())
				.withIntroducedDate(date1)
				.withDiscontinuedDate(date2)
				.build();
		assertEquals(computer1.getId(), computer3.getId());
		assertEquals(computer1.getName(), computer3.getName());
		assertEquals(computer1.getIntroducedDate(), computer3.getIntroducedDate());
		assertEquals(computer1.getDiscontinuedDate(), computer3.getDiscontinuedDate());
		assertEquals(computer1.getCompany(), computer3.getCompany());
	}
	
// ******* AFTER *******
	@After
	public void tearDown() {
		this.computer1 = null;
		this.computer2 = null;
		this.computer3 = null;
		this.computerBuilder1 = null;
		this.computerBuilder2 = null;
		this.date1 = null;
		this.date2 = null;
	}
}