package com.excilys.model.dto;

public class CompanyDTO {
	
// ******* VARIABLES *******
	private int id;
	private String name;
	
// ******* CONSTRUCTORS *******
	/**
	 * Constructor without parameter
	 */
	public CompanyDTO() {
		super();
		this.setId(0);
		this.setName("unknown company");
	}
	
	/**
	 * Constructor with two parameters
	 * @param id Company ID
	 * @param name Company name
	 */
	public CompanyDTO(int id, String name) {
		super();
		this.setId(id);
		this.setName(name);;
	}
	
// ******* GETTERS *******	
	/**
	 * @return id Company ID
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * @return name Company name
	 */
	public String getName() {
		return this.name;
	}

// ******* SETTERS *******	
	/**
	 * @param id Company ID
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @param name Company name
	 */
	public void setName(String name) {
		this.name = name;
	}

// ******* OTHER METHODS *******
	@Override
	public String toString(){
		return "Company DTO  : " + this.getName() + " (ID n°" +this.getId()+")";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyDTO other = (CompanyDTO) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
