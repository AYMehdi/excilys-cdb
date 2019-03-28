package main.com.excilys.model;

public enum Pagination {
	
	NEXT("N"),
	PREVIOUS("P"),
	QUIT("Q"),
	INVALID;
	
	private Pagination() {}
	
	private Pagination(String choice) {}
	
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