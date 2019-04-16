package main.java.com.excilys.models;

public class ComputerDTO {
	
	// ******* VARIABLES *******
	private int computerID, companyID;
	private String computerName, companyName, introducedDate, discontinuedDate;
	
	// ******* CONSTRUCTOR *******
	public ComputerDTO() {}
	
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

	// ******* OTHER METHODS *******
	@Override
	public String toString() {
		return "Computer ID : " + this.getComputerId()
				+ " Computer Name : " + this.getComputerName()
				+ " Manufacturer ID : " + this.getCompanyId()
				+ " Manufacturer : " + this.getCompanyName()
				+ " Introduced in : " + this.getIntroducedDate()
				+ " Discontinued in : " + this.getDiscontinuedDate();
	}
}