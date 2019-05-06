package com.excilys.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PageTest<T> {
	
// ******* VARIABLE *******
	Page<Computer> page;
	
// ******* BEFORE *******
	@Before
	public void setUp() {
		this.page = new Page<Computer>(new ArrayList<Computer>());
	}
	
// ******* CONSTRUCTOR *******
	public PageTest() {
		super();
	}
		
// ******* TEST *******
	@Test
	public void testCurrentPage() {
		// test 1
		assertEquals(page.getIndex(), 0);
		
		// test 2
		page.setSize(12);
		page.setIndex(7);
		assertEquals(page.getSize(), 12);
		assertEquals(page.getIndex(),7);
	}
	
// ******* AFTER *******
	@After
	public void tearDown() {
		this.page = null;
	}
}