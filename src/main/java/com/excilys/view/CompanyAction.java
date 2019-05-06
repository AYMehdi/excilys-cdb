package com.excilys.view;

public enum CompanyAction {
	
// ******* ENUM *******
	BACK("0"),
	LIST_COMPANIES("1"),
	SELECT_COMPANIES("2"),
	SHOW_COMPANY_DETAILS("3"),
	ADD_COMPANY("4"),
	UPDATE_COMPANY("5"),
	REMOVE_COMPANY("6");
	
// ******* VARIABLES *******
	String action;
	
// ******* CONSTRUCTOR *******
	/**
	 * Constructor without parameter
	 */
	CompanyAction(String action) {
		this.setAction(action);
	}
	
// ******* SETTER *******
	/**
	 * @param action Action
	 */
	private void setAction(String action) {
		this.action = action;
	}
}