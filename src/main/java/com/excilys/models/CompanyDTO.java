package main.java.com.excilys.models;

public class CompanyDTO {
	
	// ******* VARIABLES *******
	private int id;
	private String name;

	// ******* CONSTRUCTOR *******
	public CompanyDTO() {}

	// ******* GETTERS *******
	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	// ******* SETTERS *******
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	// ******* OTHER METHODS *******
	@Override
	public String toString() {
		return "Company ID : " + this.getId() 
			+ " Company Name : " + this.getName();
	}
}
