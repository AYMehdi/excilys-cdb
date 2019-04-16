package main.java.com.excilys.models.builders;

import main.java.com.excilys.models.Company;

public class CompanyBuilder {
	
	// ******* VARIABLES *******
	private int id;
	private String name;
	
	// ******* BUILDER *******
	public CompanyBuilder() {}
	
	public CompanyBuilder withId(int id) {
		this.setId(id);
		return this;
	}
	
	public CompanyBuilder withName(String name) {
		this.setName(name);
		return this;
	}
	
	public Company build() {
		Company company = new Company();
		company.setId(this.id);
		company.setName(this.name);
		return company;
	}
	
	// ******* GETTERS *******
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	// ******* SETTERS *******
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}