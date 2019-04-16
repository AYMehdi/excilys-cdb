package main.java.com.excilys.models;

public enum Pagination {
	
	// ******* ENUM *******
	NEXT_PAGE("N"),
	PREVIOUS_PAGE("P"),
	QUIT("Q"),
	INVALID;
	
	// ******* VARIABLE *******
	private String choice;
	
	// ******* CONSTRUCTORS *******
	private Pagination() {}
	
	private Pagination(String choice) {
		setChoice(choice);
	}
	
	// ******* GETTER *******
	public String getChoice() {
		return this.choice;
	}
	
	// ******* SETTER *******
	public void setChoice(String choice) {
		this.choice = choice;
	}
	
	// ******* OTHER METHODS *******
	public static Pagination stringToAction(String choice) {
		int index = 3;
		
		switch(choice) {
			case "N" :
				index = 0;
				break;
			case "P" :
				index = 1;
				break;
			case "Q" :
				index = 2;
				break;
			default :
				index = 3;
				break;
		}
		return Pagination.values()[index];
	}
}