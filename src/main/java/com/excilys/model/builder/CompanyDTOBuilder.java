package com.excilys.model.builder;

import com.excilys.exception.ModelException;
import com.excilys.model.dto.CompanyDTO;

public class CompanyDTOBuilder {
	
// ******* VARIABLES *******
	private int id;
	private String name;
	
// ******* BUILDER *******
	/**
	 * Constructor without parameter
	 */
	public CompanyDTOBuilder() {
		super();
	}
	
	/**
	 * Build an empty Company DTO
	 * @return companyDTO CompanyDTO
	 */
	public CompanyDTOBuilder empty() {
		this.setId(0);
		this.setName("unknown company");
		return this;
	}
	
	/**
	 * Return a CompanyDTOBuilder
	 * @param id Company ID
	 * @return this CompanyDTOBuilder
	 */
	public CompanyDTOBuilder withId(int id) {
		this.setId(id);
		return this;
	}
	
	/**
	 * Return a CompanyDTOBuilder
	 * @param name Company name
	 * @return this CompanyDTOBuilder
	 */
	public CompanyDTOBuilder withName(String name) {
		this.setName(name);
		return this;
	}
	
	/**
	 * Build a new CompanyDTO
	 * @return companyDTO CompanyDTO
	 * @throws ModelException 
	 */
	public CompanyDTO build() {
		CompanyDTO companyDTO = new CompanyDTO();
		
		companyDTO.setId(this.getId());
		companyDTO.setName(this.getName());
		
		return companyDTO;
	}
	
// ******* GETTERS *******
	/**
	 * @return id Company ID
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return name Company name
	 */
	public String getName() {
		return name;
	}
	
// ******* SETTERS *******
	/**
	 * @param companyID Company ID
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