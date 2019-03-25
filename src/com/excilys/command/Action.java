package com.excilys.command;

public enum Action {
	
	EXIT("0"),
	LIST_COMPUTERS("1"), 
	LIST_COMPANIES("2"),
	ADD_COMPUTER("3"),
	UPDATE_COMPUTER("4"),
	REMOVE_COMPUTER("5"),
	SHOW_COMPUTER("6");
	
	String action;
	
	Action(String action) {
		this.action = action;
	}
	
	public static Action getAction(String string) {
		return Action.values()[Integer.parseInt(string)];
	}
}