package main.java.com.excilys.model.builder;

import java.sql.Timestamp;

import main.java.com.excilys.model.Company;
import main.java.com.excilys.model.Computer;

public class ComputerBuilder {
	
	// ******* VARIABLES *******
	private int id;
	private String name;
	private Company company;
	private Timestamp introducedDate, discontinuedDate;
	
	// ******* BUILDER *******
	public ComputerBuilder() {}
	
	public ComputerBuilder withId(int id) {
		this.setId(id);
		return this;
	}
	
	public ComputerBuilder withName(String name) {
		this.setName(name);
		return this;
	}
	
	public ComputerBuilder withCompany(Company company) {
		this.setCompany(company);
		return this;
	}

	public ComputerBuilder withIntroducedDate(Timestamp introducedDate) {
		this.setIntroducedDate(introducedDate);
		return this;
	}
	
	public ComputerBuilder withDiscontinuedDate(Timestamp discontinuedDate) {
		this.setDiscontinuedDate(discontinuedDate);
		return this;
	}
	
	public Computer build() {
		Computer computer = new Computer();
		computer.setId(this.getId());
		computer.setName(this.getName());
		computer.setCompany(this.getCompany());
		computer.setIntroducedDate(this.getIntroducedDate());
		computer.setDiscontinuedDate(this.getDiscontinuedDate());
		return computer;
	}
	
	// ******* GETTERS *******
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Company getCompany() {
		return company;
	}

	public Timestamp getIntroducedDate() {
		return introducedDate;
	}

	public Timestamp getDiscontinuedDate() {
		return discontinuedDate;
	}
	
	// ******* SETTERS *******
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public void setIntroducedDate(Timestamp introducedDate) {
		this.introducedDate = introducedDate;
	}
	
	public void setDiscontinuedDate(Timestamp discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}
}