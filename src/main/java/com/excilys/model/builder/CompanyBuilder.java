package com.excilys.model.builder;

import com.excilys.exception.ModelException;
import com.excilys.model.Company;

public class CompanyBuilder {

// ******* VARIABLES *******	
	private int id;
	private String name;
	
// ******* BUILDER *******
	/**
	 * Constructor without parameter
	 */
	public CompanyBuilder() {
		super();
	}
	
	/**
	 * Build an empty Company
	 * @return company Company
	 */
	public CompanyBuilder empty() {
		this.setId(0);
		this.setName("unknown company");
		return this;
	}
	
	/**
	 * Return a CompanyBuilder
	 * @param id Company ID
	 * @return this CompanyBuilder
	 */
	public CompanyBuilder withId(int id) {
		this.setId(id);
		return this;
	}
	
	/**
	 * Return a CompanyBuilder
	 * @param name Company name
	 * @return this CompanyBuilder
	 */
	public CompanyBuilder withName(String name) {
		this.setName(name);
		return this;
	}
		
	/**
	 * Build a new Company
	 * @return company Company
	 * @throws ModelException 
	 */
	public Company build() throws ModelException {
		Company company = new Company();
		
		company.setId(this.getId());
		company.setName(this.getName());
		
		return company;
	}

// ******* GETTERS *******
	/**
	 * @param id Company ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param name Company name
	 */
	public String getName() {
		return name;
	}
	
// ******* SETTERS *******
	/**
	 * @param id Company ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param name Company name
	 */
	public void setName(String name) {
		this.name = name;
	}
}