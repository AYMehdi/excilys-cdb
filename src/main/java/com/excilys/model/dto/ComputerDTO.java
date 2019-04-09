package main.java.com.excilys.model.dto;

public class ComputerDTO {
	
	// ******* VARIABLES *******
	private int id;
	private String name, company, introducedDate, discontinuedDate;
	
	// ******* GETTERS *******
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getCompany() {
		return company;
	}
	
	public String getIntroducedDate() {
		return introducedDate;
	}
	
	public String getDiscontinuedDate() {
		return discontinuedDate;
	}
	
	// ******* SETTERS *******
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCompany(String company) {
		this.company = company;
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
		return "Computer ID : " + id
				+ " Computer Name : " + name
				+ " Manufacturer : " + company
				+ " Introduced in : " + introducedDate
				+ " Discontinued in : " + discontinuedDate;
	}
}
