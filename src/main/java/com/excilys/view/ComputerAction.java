package com.excilys.view;

public enum ComputerAction {

// ******* ENUM *******
	BACK("0"),
	LIST_COMPUTERS("1"),
	SELECT_COMPUTERS("2"),
	SHOW_COMPUTER_DETAILS("3"),
	ADD_COMPUTER("4"),
	UPDATE_COMPUTER("5"),
	REMOVE_COMPUTER("6");
	
// ******* VARIABLES *******
	String action;
	
// ******* CONSTRUCTOR *******
	/**
	 * Constructor without parameter
	 */
	ComputerAction(String action) {
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