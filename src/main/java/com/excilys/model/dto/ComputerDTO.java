package com.excilys.model.dto;

public class ComputerDTO {
	
// ******* VARIABLES *******
	private int computerID, companyID;
	private String computerName, companyName;
	private String introducedDate, discontinuedDate;

// ******* CONSTRUCTORS *******
	/**
	 * Constructor without parameter
	 */
	public ComputerDTO() {
		super();
		this.setComputerId(0);
		this.setComputerName("unknown computer");
		this.setCompanyId(0);
		this.setCompanyName("unknown company");
		this.setIntroducedDate(null);
		this.setDiscontinuedDate(null);
	}

	/**
	 * Constructor with six parameters
	 * @param computerID Computer ID
	 * @param computerName Computer name
	 * @param companyID Company ID
	 * @param companyName Company name
	 * @param introducedDate Computer introduced date
	 * @param discontinuedDate Computer discontinued date
	 */
	public ComputerDTO(int computerID, String computerName, int companyID, String companyName, String introducedDate, String discontinuedDate) {
		super();
		this.setComputerId(computerID);
		this.setComputerName(computerName);
		this.setCompanyId(companyID);
		this.setCompanyName(companyName);
		this.setIntroducedDate(introducedDate);
		this.setDiscontinuedDate(discontinuedDate);
	}

// ******* GETTERS *******
	/**
	 * @return computerID Computer ID
	 */
	public int getComputerId() {
		return this.computerID;
	}

	/**
	 * @return computerName Computer name
	 */
	public String getComputerName() {
		return this.computerName;
	}

	/**
	 * @return companyID  Company ID
	 */
	public int getCompanyId() {
		return this.companyID;
	}

	/**
	 * @return companyName Company name
	 */
	public String getCompanyName() {
		return this.companyName;
	}
	
	/**
	 * @return introducedDate Computer introduced date
	 */
	public String getIntroducedDate() {
		return this.introducedDate;
	}

	/**
	 * @return discontinuedDate Computer discontinued date
	 */
	public String getDiscontinuedDate() {
		return this.discontinuedDate;
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
	 * @param companyID  Company ID
	 */
	public void setCompanyId(int companyID) {
		this.companyID = companyID;
	}

	/**
	 * @param companyName Company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	/**
	 * @param introducedDate Computer introduced date
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

// ******* OTHER METHODS *******
	@Override
	public String toString() {
		return "ComputerDTO ID : " + this.getComputerId() + "\n    	"
				+ "Name : " + this.getComputerName() + "\n    	"
				+ "Manufacturer : " +this.getCompanyName()+" (ID n°"+this.getCompanyId()+")\n    	" 
				+ "Introduced in " + this.getIntroducedDate() + "\n    	" 
				+ "Discontinued in " + this.getDiscontinuedDate();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + computerID;
		result = prime * result + companyID;
		result = prime * result + ((computerName == null) ? 0 : computerName.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((introducedDate == null) ? 0 : introducedDate.hashCode());
		result = prime * result + ((discontinuedDate == null) ? 0 : discontinuedDate.hashCode());
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
		ComputerDTO other = (ComputerDTO) obj;
		if (companyID != other.companyID)
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (computerID != other.computerID)
			return false;
		if (computerName == null) {
			if (other.computerName != null)
				return false;
		} else if (!computerName.equals(other.computerName))
			return false;
		if (discontinuedDate == null) {
			if (other.discontinuedDate != null)
				return false;
		} else if (!discontinuedDate.equals(other.discontinuedDate))
			return false;
		if (introducedDate == null) {
			if (other.introducedDate != null)
				return false;
		} else if (!introducedDate.equals(other.introducedDate))
			return false;
		return true;
	}
}