package main.java.com.excilys.models.builders;

import main.java.com.excilys.models.ComputerDTO;

public class ComputerDTOBuilder {
	
	// ******* VARIABLES *******
	private int computerID, companyID;
	private String computerName, companyName, introducedDate, discontinuedDate;
	
	// ******* BUILDER *******
	public ComputerDTOBuilder() {}
	
	public ComputerDTOBuilder withComputerId(int computerID) {
		this.setComputerId(computerID);
		return this;
	}
	
	public ComputerDTOBuilder withCompanyId(int companyID) {
		this.setCompanyId(companyID);
		return this;
	}
	
	public ComputerDTOBuilder withComputerName(String computerName) {
		this.setComputerName(computerName);
		return this;
	}
	
	public ComputerDTOBuilder withCompanyName(String companyName) {
		this.setCompanyName(companyName);
		return this;
	}

	public ComputerDTOBuilder withIntroducedDate(String introducedDate) {
		this.setIntroducedDate(introducedDate);
		return this;
	}
	
	public ComputerDTOBuilder withDiscontinuedDate(String discontinuedDate) {
		this.setDiscontinuedDate(discontinuedDate);
		return this;
	}
	
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
	public int getComputerId() {
		return computerID;
	}
	
	public int getCompanyId() {
		return companyID;
	}
	
	public String getComputerName() {
		return computerName;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public String getIntroducedDate() {
		return introducedDate;
	}

	public String getDiscontinuedDate() {
		return discontinuedDate;
	}
	
	// ******* SETTERS *******
	public void setComputerId(int computerID) {
		this.computerID = computerID;
	}
	
	public void setCompanyId(int companyID) {
		this.companyID = companyID;
	}
	
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public void setIntroducedDate(String introducedDate) {
		this.introducedDate = introducedDate;
	}
	
	public void setDiscontinuedDate(String discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}
}