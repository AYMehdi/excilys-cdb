package main.java.com.excilys.services;

public enum CasesSorted {

	// ******* ENUM *******
	BY_NAME("byName"),
	BY_NAME_DECREASING("byNameDecreasing"),
	BY_COMPANY("byCompany"),
	BY_COMPANY_DECREASING("byCompanyDecreasing"),
	BY_INTRODUCED("byIntroduced"),
	BY_INTRODUCED_DECREASING("byIntroducedDecreasing"),
	BY_DISCONTINUED("byDiscontinued"),
	BY_DISCONTINUED_DECREASING("byDiscontinuedDecreasing");
	
	// ******* VARIABLE *******
	private String sortType;
	
	// ******* CONSTRUCTOR *******
	private CasesSorted(String sortType) {
		this.sortType = sortType;
	}

	// ******* GETTER *******
	public String getSortType() {
		return sortType;
	}

	// ******* SETTER *******
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
}
