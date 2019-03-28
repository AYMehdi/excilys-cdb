package main.com.excilys.model.builder;

import main .com.excilys.model.Company;

public class CompanyBuilder {
	
	// ******* VARIABLES *******
	private int id;
	private String name;
	
	// ******* BUILDER *******
	public CompanyBuilder() {}
	
	public Company build() {
		return new Company(id, name);
	}
	
	public CompanyBuilder withId(int id) {
		this.setId(id);
		return this;
	}
	
	public CompanyBuilder withName(String name) {
		this.setName(name);
		return this;
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