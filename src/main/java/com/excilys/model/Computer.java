package com.excilys.model;

import java.sql.Timestamp;
import java.util.Objects;

public class Computer {
	
// ******* VARIABLES *******
	private int id;
	private String name;
	private Company company;
	private Timestamp introducedDate, discontinuedDate;
	
// ******* CONSTRUCTORS *******
	/**
	 * Constructor without parameter
	 */
	public Computer() {
		super();
		this.setId(0);
		this.setName("unknown computer");
		this.setCompany(new Company());
	}

	/**
	 * Constructor with one parameter
	 * @param id Computer ID
	 */
	public Computer(int id) {
		super();
		Objects.requireNonNull(id);
		this.setId(id);
		this.setName("unknown computer");
		this.setCompany(new Company());
	}
	
	/**
	 * Constructor with one parameter
	 * @param name Computer name
	 */
	public Computer(String name) {
		super();
		Objects.requireNonNull(name);
		this.setId(0);
		this.setName(name);
		this.setCompany(new Company());
	}
	
	/**
	 * Constructor with two parameters
	 * @param name Computer name	 
	 * @param manufacturer Computer manufacturer
	 */
	public Computer(String name, String manufacturer) {
		super();
		Objects.requireNonNull(name);
		this.setId(0);
		this.setName(name);
		this.setCompany(new Company(manufacturer));
	}
	
	/**
	 * Constructor with two parameters
	 * @param name Computer name
	 * @param company Computer manufacturer
	 */
	public Computer(String name, Company company) {
		super();
		Objects.requireNonNull(name);
		this.setId(0);
		this.setName(name);
		this.setCompany(company);
	}
	
	/**
	 * Constructor with three parameters
	 * @param name Computer name
	 * @param manufacturer Computer manufacturer
	 * @param introducedDate Computer introduced date
	 */
	public Computer(String name, String manufacturer, Timestamp introducedDate) {
		super();
		Objects.requireNonNull(name);
		this.setId(0);
		this.setName(name);
		this.setCompany(new Company(manufacturer));
		this.setIntroducedDate(introducedDate);
	}
	
	/**
	 * Constructor with three parameters
	 * @param name Computer name
	 * @param company Computer manufacturer
	 * @param introducedDate Computer introduced date
	 */
	public Computer(String name, Company company, Timestamp introducedDate) {
		super();
		Objects.requireNonNull(name);
		this.setId(0);
		this.setName(name);
		this.setCompany(company);
		this.setIntroducedDate(introducedDate);
	}
	
	/**
	 * Constructor with four parameters
	 * @param name Computer name
	 * @param manufacturer Computer manufacturer
	 * @param introducedDate Computer introduced date
	 * @param discontinuedDate Computer discontinued date
	 */
	public Computer(String name, String manufacturer, Timestamp introducedDate, Timestamp discontinuedDate) {
		super();
		Objects.requireNonNull(name);
		this.setId(0);
		this.setName(name);
		this.setCompany(new Company(manufacturer));
		this.setIntroducedDate(introducedDate);
		this.setDiscontinuedDate(discontinuedDate);
	}
	
	/**
	 * Constructor with four parameters
	 * @param name Computer name
	 * @param company Computer manufacturer
	 * @param introducedDate Computer introduced date
	 * @param discontinuedDate Computer discontinued date
	 */
	public Computer(String name, Company company, Timestamp introducedDate, Timestamp discontinuedDate) {
		super();
		Objects.requireNonNull(name);
		this.setId(0);
		this.setName(name);
		this.setCompany(company);
		this.setIntroducedDate(introducedDate);
		this.setDiscontinuedDate(discontinuedDate);
	}
	
	/**
	 * Constructor with two parameters
	 * @param name Computer name
	 * @param introducedDate Computer introduced date
	 */
	public Computer(String name, Timestamp introducedDate) {
		super();
		Objects.requireNonNull(name);
		this.setId(0);
		this.setName(name);
		this.setCompany(new Company("unknown company"));
		this.setIntroducedDate(introducedDate);
	}
	
	/**
	 * Constructor with three parameters
	 * @param name Computer name
	 * @param introducedDate Computer introduced date
	 * @param discontinuedDate Computer discontinued date
	 */
	public Computer(String name, Timestamp introducedDate, Timestamp discontinuedDate) {
		super();
		Objects.requireNonNull(name);
		this.setId(0);
		this.setName(name);
		this.setCompany(new Company("unknown company"));
		this.setIntroducedDate(introducedDate);
		this.setDiscontinuedDate(discontinuedDate);
	}
	
	/**
	 * Constructor with five parameters
	 * @param id Computer ID
	 * @param name Computer name
	 * @param company Computer manufacturer
	 * @param introducedDate Computer introduced date
	 * @param discontinuedDate Computer discontinued date
	 */
	public Computer(int id, String name, Company company, Timestamp introducedDate, Timestamp discontinuedDate) {
		super();
		Objects.requireNonNull(id);
		this.setId(id);
		this.setName(name);
		this.setCompany(company);
		this.setIntroducedDate(introducedDate);
		this.setDiscontinuedDate(discontinuedDate);
	}
	
	/**
	 * Constructor with five parameters
	 * @param id Computer ID
	 * @param name Computer name
	 * @param manufacturer Computer manufacturer
	 * @param introducedDate Computer introduced date
	 * @param discontinuedDate Computer discontinued date
	 */
	public Computer(int id, String name, String manufacturer, Timestamp introducedDate, Timestamp discontinuedDate) {
		super();
		Objects.requireNonNull(id);
		this.setId(id);
		this.setName(name);
		this.setCompany(new Company(manufacturer));
		this.setIntroducedDate(introducedDate);
		this.setDiscontinuedDate(discontinuedDate);
	}
	
// ******* GETTERS *******	
	/**
	 * @return id Computer ID
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * @return name Computer name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return company Computer manufacturer
	 */
	public Company getCompany() {
		return this.company;
	}
	
	/**
	 * @return introducedDate Computer introduced date
	 */
	public Timestamp getIntroducedDate() {
		return this.introducedDate;
	}
	
	/**
	 * @return discontinuedDate Computer discontinued date
	 */
	public Timestamp getDiscontinuedDate() {
		return this.discontinuedDate;
	}
	
// ******* SETTERS *******	
	/**
	 * @param id Computer ID
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @param name Computer name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param company Computer manufacturer
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	
	/**
	 * @param introducedDate Computer introduced Date
	 */
	public void setIntroducedDate(Timestamp introducedDate) {
		this.introducedDate = introducedDate;
	}
	
	/**
	 * @param discontinuedDate Computer discontinued date
	 */
	public void setDiscontinuedDate(Timestamp discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}
	

	// ******* OTHER METHODS *******	
	@Override
	public String toString() {
		return "Computer ID : " + this.getId() + "\n    	"
				+ "Name : " + this.getName() + "\n    	"
				+ "Manufacturer : " + this.getCompany() +"\n    	" 
				+ "Introduced in " + this.getIntroducedDate() + "\n    	" 
				+ "Discontinued in " + this.getDiscontinuedDate();
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Computer)) {
			return false;
		}
		return idEquals(o) 
				&& nameEquals(o)
				&& companyEquals(o)
				&& introducedEquals(o)
				&& discontinuedEquals(o);
	}
	
	private boolean idEquals(Object o) {
		if (this.id == 0)
			return (((Computer) o).getId() ==0);
		else
			return (this.getId()==((Computer) o).getId());
	}

	private boolean nameEquals(Object o) {
		if (this.getName() == null)
			return (((Computer) o).getName() == null);
		else
			return this.getName().equals(((Computer) o).getName());
	}

	private boolean companyEquals(Object o) {
		if (this.getCompany() == null)
			return (((Computer) o).getCompany() == null);
		else
			return this.getCompany().equals( ((Computer) o).getCompany());
	}

	private boolean introducedEquals(Object o) {
		if (this.getIntroducedDate()== null)
			return (((Computer) o).getIntroducedDate()==null);
		else
			return this.getIntroducedDate().equals( ((Computer) o).getIntroducedDate());
	}

	private boolean discontinuedEquals(Object o) {
		if (this.getDiscontinuedDate()== null)
			return (((Computer) o).getDiscontinuedDate()==null);
		else
			return this.getDiscontinuedDate().equals( ((Computer) o).getDiscontinuedDate());
	}

	@Override
	public int hashCode() {
		return this.getId() + this.getName().hashCode() + this.getCompany().hashCode()
				+ this.getIntroducedDate().hashCode() + this.getDiscontinuedDate().hashCode();
	}	
}