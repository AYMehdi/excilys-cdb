package com.excilys.view;

public enum MainAction {
	
// ******* VARIABLES *******
	QUITTER("0"),
	COMPUTER_MENU("1"),
	COMPANY_MENU("2");
		
// ******* VARIABLES *******
	String action;
	
// ******* CONSTRUCTOR *******
	/**
	 * Constructor without parameter
	 */
	MainAction(String action) {
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