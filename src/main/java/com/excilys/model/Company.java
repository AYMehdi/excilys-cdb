package com.excilys.model;

import java.util.Objects;

public class Company {
	
// ******* VARIABLES *******
	private int id;
	private String name;
	
// ******* CONSTRUCTOR *******
	/**
	 * Constructor without parameter
	 */
	public Company() {
		super();
		this.setId(0); 
		this.setName("unknown company");
	}
	
	/**
	 * Constructor with one parameter
	 * @param id Company ID
	 */
	public Company(int id) {
		super();
		Objects.requireNonNull(id);
		this.setId(id);
		this.setName("unkown company");
	}
	 
	/**
	 * Constructor with one parameter
	 * @param name Company name
	 */
	public Company(String name) {
		super();
		Objects.requireNonNull(name);
		this.setId(0);
		this.setName(name);
	}
	
	/**
	 * Constructor with two parameters
	 * @param id Company ID
	 * @param name Company name
	 */
	public Company(int id, String name) {
		super();
		Objects.requireNonNull(id);
		this.id = id;
		this.name = name;
	}
	
// ******* GETTERS *******
	/**
	 * @return id Company ID
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * @return name Company name
	 */
	public String getName() {
		return this.name;
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

// ******* OTHER METHODS *******
	@Override
	public String toString(){
		return this.getName() + " (ID n°" +this.getId()+")";
	}
	
	public String companyInfo(){
		switch(this.getId()) {
		case -2: // No ID or name
			return "This company is unrepertoried. This one has no name or ID.";
		case -1: // No ID
			return "The company " +this.getName()+ " is unrepertoried. This one has no ID.";
		default:
			return "The company " +this.getName()+ " is repertoried with the ID n°" +this.getId();
		}
	}
	
	@Override
	public int hashCode() {
		return this.getId() + this.getName().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		
		Company company = (Company) obj;
		
		if (this.id != company.id) return false;
		
		if (this.name == null && company.name != null) return false;
		if (this.name != null && company.name == null) return false;
		if (this.name != null && company.name != null && !this.name.equals(company.name)) return false;
		
		return true;
	}
}