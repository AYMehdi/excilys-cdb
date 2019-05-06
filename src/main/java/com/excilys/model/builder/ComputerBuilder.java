package com.excilys.model.builder;

import java.sql.Timestamp;

import com.excilys.exception.ModelException;
import com.excilys.model.Company;
import com.excilys.model.Computer;

public class ComputerBuilder {
	
// ******* VARIABES *******
	private int id;
	private String name;
	private Company company;
	private Timestamp introducedDate, discontinuedDate;
	
// ******* BUILDER *******
	/**
	 * Constructor without parameter
	 */
	public ComputerBuilder() {
		super();
	}
	
	/**
	 * Build an empty Computer
	 * @return computer Computer
	 */
	public ComputerBuilder empty() {
		this.setId(0);
		this.setName("unknown computer");
		this.setCompany(new Company());
		this.setIntroducedDate(null);
		this.setDiscontinuedDate(null);
		return this;
	}
	
	/**
	 * Return a ComputerBuilder
	 * @param id Computer ID
	 * @return this ComputerBuilder
	 */
	public ComputerBuilder withId(int id) {
		this.setId(id);
		return this;
	}
	
	/**
	 * Return a ComputerBuilder
	 * @param name Computer name
	 * @return this ComputerBuilder
	 */
	public ComputerBuilder withName(String name) {
		this.setName(name);
		return this;
	}
	
	/**
	 * Return a ComputerBuilder
	 * @param company Computer manufacturer
	 * @return this ComputerBuilder
	 */
	public ComputerBuilder withCompany(Company company) {
		this.company = company;
		return this;
	}
	
	/**
	 * Return a ComputerBuilder
	 * @param introducedDate Computer introduced date
	 * @return this ComputerBuilder
	 */
	public ComputerBuilder withIntroducedDate(Timestamp introducedDate) {
		this.introducedDate = introducedDate;
		return this;
	}
	
	/**
	 * Return a ComputerBuilder
	 * @param discontinuedDate Computer discontinued date
	 * @return this ComputerBuilder
	 */
	public ComputerBuilder withDiscontinuedDate(Timestamp discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
		return this;
	}
		
	/**
	 * Build a new Computer
	 * @return computer Computer
	 * @throws ModelException 
	 */
	public Computer build() throws ModelException {
		Computer computer = new Computer();
		
		computer.setId(this.getId());
		
		if(this.getName() == null)
			computer.setName("unknown computer");
		else
			computer.setName(this.getName());
		
		if(company == null)
			company = new Company();
		computer.setCompany(this.getCompany());
		
		computer.setIntroducedDate(this.getIntroducedDate());
		computer.setDiscontinuedDate(this.getDiscontinuedDate());
		
		return computer;
	}

// ******* GETTERS *******	
	/**
	 * @return id Computer ID
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * @return name Computer name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return company Computer manufacturer
	 */
	public Company getCompany() {
		return this.company;
	}
	
	/**
	 * @return introducedDate Computer introduced date
	 */
	public Timestamp getIntroducedDate() {
		return this.introducedDate;
	}

	/**
	 * @return discontinuedDate Computer discontinued date
	 */
	public Timestamp getDiscontinuedDate() {
		return this.discontinuedDate;
	}
	
// ******* SETTERS *******	
	/**
	 * @param id Computer ID
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @param name Computer name
	 */
	public void setName(String name) {
		this.name = name;
	}
		
	/**
	 * @param company Computer manufacturer
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
		
	/**
	 * @param introducedDate Computer introduced Date
	 */
	public void setIntroducedDate(Timestamp introducedDate) {
		this.introducedDate = introducedDate;
	}
	
	/**
	 * @param discontinuedDate Computer discontinued date
	 */
	public void setDiscontinuedDate(Timestamp discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}
}