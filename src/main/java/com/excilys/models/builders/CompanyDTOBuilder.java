package main.java.com.excilys.models.builders;

import main.java.com.excilys.models.CompanyDTO;

public class CompanyDTOBuilder {
	
	// ******* VARIABLES *******
	private int id;
	private String name;
	
	// ******* BUILDER *******
	public CompanyDTOBuilder() {}
	
	public CompanyDTOBuilder withId(int id) {
		this.setId(id);
		return this;
	}
	
	public CompanyDTOBuilder withName(String name) {
		this.setName(name);
		return this;
	}
	
	public CompanyDTO build() {
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(this.getId());
		companyDTO.setName(this.getName());
		return companyDTO;
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