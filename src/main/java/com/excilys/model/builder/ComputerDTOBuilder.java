package com.excilys.model.builder;

import com.excilys.exception.ModelException;
import com.excilys.model.dto.ComputerDTO;

public class ComputerDTOBuilder {
	
// ******* VARIABLES *******
	private int computerID;
	private int companyID;
	private String computerName;
	private String companyName;
	private String introducedDate, discontinuedDate;
	
// ******* BUILDER *******
	/**
	 * Constructor without parameter
	 */
	public ComputerDTOBuilder() {
		super();
	}
	
	/**
	 * Build an empty Computer DTO
	 * @return computerDTO ComputerDTO
	 */
	public ComputerDTOBuilder empty() {
		this.setComputerId(0);
		this.setComputerName("unknown computer");
		this.setCompanyId(0);
		this.setCompanyName("unknown company");
		this.setIntroducedDate(null);
		this.setDiscontinuedDate(null);
		return this;
	}
	
	
	/**
	 * Return a ComputerDTOBuilder
	 * @param computerID Computer ID
	 * @return this ComputerDTOBuilder
	 */
	public ComputerDTOBuilder withComputerId(int computerID) {
		this.setComputerId(computerID);
		return this;
	}
	
	/**
	 * Return a ComputerDTOBuilder
	 * @param computerName Computer name
	 * @return this ComputerDTOBuilder
	 */
	public ComputerDTOBuilder withComputerName(String computerName) {
		this.setComputerName(computerName);
		return this;
	}
	
	/**
	 * Return a ComputerBuilder
	 * @param companyID Manufacturer ID
	 * @return this ComputerBuilder
	 */
	public ComputerDTOBuilder withCompanyId(int companyID) {
		this.setCompanyId(companyID);
		return this;
	}
	
	/**
	 * Return a ComputerBuilder
	 * @param companyName Manufacturer name
	 * @return this ComputerBuilder
	 */
	public ComputerDTOBuilder withCompanyName(String companyName) {
		this.setCompanyName(companyName);
		return this;
	}

	/**
	 * Return a ComputerBuilder
	 * @param introducedDate Computer introduced date
	 * @return this ComputerBuilder
	 */
	public ComputerDTOBuilder withIntroducedDate(String introducedDate) {
		this.setIntroducedDate(introducedDate);
		return this;
	}
	
	/**
	 * Return a ComputerBuilder
	 * @param discontinuedDate Computer discontinued date
	 * @return this ComputerBuilder
	 */
	public ComputerDTOBuilder withDiscontinuedDate(String discontinuedDate) {
		this.setDiscontinuedDate(discontinuedDate);
		return this;
	}
	
	/**
	 * Build a computerDTO
	 * @return computerDTO
	 * @throws ModelException 
	 */
	public ComputerDTO build() {
		ComputerDTO computerDTO = new ComputerDTO();
		
		computerDTO.setComputerId(this.getComputerId());
		computerDTO.setComputerName(this.getComputerName());
		computerDTO.setCompanyId(this.getCompanyId());
		computerDTO.setCompanyName(this.getCompanyName());
		computerDTO.setIntroducedDate(this.getIntroducedDate());
		computerDTO.setDiscontinuedDate(this.getDiscontinuedDate());
		
		return computerDTO;
	}
	
// ******* GETTERS *******
	/**
	 * @return computerID Computer ID
	 */
	public int getComputerId() {
		return computerID;
	}
	
	/**
	 * @return computerName Computer name
	 */
	public String getComputerName() {
		return computerName;
	}
	
	/**
	 * @return companyID Manufacturer ID
	 */
	public int getCompanyId() {
		return companyID;
	}
	
	/**
	 * @return companyName Manufacturer name
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @return introducedDate Computer introduced date
	 */
	public String getIntroducedDate() {
		return introducedDate;
	}

	/**
	 * @return discontinuedDate Computer discontinued date
	 */
	public String getDiscontinuedDate() {
		return discontinuedDate;
	}
	
// ******* SETTERS *******
	/**
	 * @param computerID Computer ID
	 */
	public void setComputerId(int computerID) {
		this.computerID = computerID;
	}
	
	/**
	 * @param computerName Computer name
	 */
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	
	/**
	 * @param companyID Manufacturer ID
	 */
	public void setCompanyId(int companyID) {
		this.companyID = companyID;
	}
	
	/**
	 * @param companyName Manufacturer name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	/**
	 * @param introducedDate Computer introduced Date
	 */
	public void setIntroducedDate(String introducedDate) {
		this.introducedDate = introducedDate;
	}
	
	/**
	 * @param discontinuedDate Computer discontinued date
	 */
	public void setDiscontinuedDate(String discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}
}