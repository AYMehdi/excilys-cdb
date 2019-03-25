package com.excilys.model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.sql.Timestamp;

public class Computer {

	// ******* VARIABLES *******
	private int id;
	private String name;
	private Company company;
	private Timestamp introducedDate, discontinuedDate;
	
	// ******* CONSTRUCTORS *******
	public Computer() { // Default Computer constructor
		this.setName("unknown computer");
		this.setCompany(new Company("unknown company"));
		this.setId(-4); // Manque 4 arguments
	}

	public Computer(String name) {
		this.setName(name);
		this.setCompany(new Company("unknown company"));
		this.setId(-3); // Manque 3 arguments
	}
	
	public Computer(String name, String manufacturer) {
		this.setName(name);
		this.setCompany(new Company(manufacturer));
		this.setId(-2); // Manque 2 arguments
	}
	
	public Computer(String name, Company company) {
		this.setName(name);
		this.setCompany(company);
		this.setId(-2); // Manque 2 arguments
	}
	
	public Computer(String name, String manufacturer, Timestamp introducedDate) {
		this.setName(name);
		this.setCompany(new Company(manufacturer));
		this.setIntroducedDate(introducedDate);
		this.setId(-1); // Manque 1 argument
	}
	
	public Computer(String name, Company company, Timestamp introducedDate) {
		this.setName(name);
		this.setCompany(company);
		this.setIntroducedDate(introducedDate);
		this.setId(-1); // Manque 1 argument
	}
	
	public Computer(String name, String manufacturer, Timestamp introducedDate, Timestamp discontinuedDate) {
		this.setName(name);
		this.setCompany(new Company(manufacturer));
		this.setIntroducedDate(introducedDate);
		this.setDiscontinuedDate(discontinuedDate);
		this.setId(0); // Tous les arguments ont été passés en entrée
	}
	
	public Computer(String name, Company company, Timestamp introducedDate, Timestamp discontinuedDate) {
		this.setName(name);
		this.setCompany(company);
		this.setIntroducedDate(introducedDate);
		this.setDiscontinuedDate(discontinuedDate);
		this.setId(0); // Tous les arguments ont été passés en entrée
	}
	
	public Computer(String name, Timestamp introducedDate) {
		this.setName(name);
		this.setCompany(new Company("unknown company"));
		this.setIntroducedDate(introducedDate);
		this.setId(-2); // Manque 2 arguments
	}
	
	public Computer(String name, Timestamp introducedDate, Timestamp discontinuedDate) {
		this.setName(name);
		this.setCompany(new Company("unknown company"));
		this.setIntroducedDate(introducedDate);
		this.setDiscontinuedDate(discontinuedDate);
		this.setId(-1); // Manque 1 argument
	}
	
	// ******* GETTERS *******
	public int getId() {
		return id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Company getCompany() {
		return company;
	}
	
	public Timestamp getIntroducedDate() {
		return this.introducedDate;
	}
	
	public Timestamp getDiscontinuedDate() {
		return this.discontinuedDate;
	}
	
	// ******* SETTERS *******
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public void setIntroducedDate(Timestamp introducedDate) {
		this.introducedDate = introducedDate;
	}
	
	public void setDiscontinuedDate(Timestamp discontinuedDate) {
		if (this.getIntroducedDate().compareTo(discontinuedDate) <= 0) {
			this.discontinuedDate = discontinuedDate;
		}
	}
	
	// ******* OTHER METHODS *******
	public static Timestamp stringToTimestamp(String dateString) throws Exception {
	    Timestamp result = null;
	    
	    try{
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        Date date  = dateFormat.parse(dateString);
	        long time = date.getTime();
	    	result = new Timestamp(time);
	    }
	    catch(ParseException e){
	        e.printStackTrace();
	    }
	    return result ;
	}
	
	public static String timestampToString(Timestamp date) {
	    String result = null;
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    result  = dateFormat.format(date);
	    return result ;
	}
	
	
	public String toString(){
		 if(this.getIntroducedDate() == null) {
			 return "ID : "+this.getId()+". "+this.getName()+" is a computer manufactured by "
					 +this.getCompany().getName();
		 }
		 else if(this.getIntroducedDate() != null && this.getDiscontinuedDate() == null) {
			 return "ID : "+this.getId()+". "+this.getName()+" is a computer manufactured by "
					 +this.getCompany().getName()+ " in "
					 +timestampToString(this.getIntroducedDate());
		 }
		 else {
			 return "ID : "+this.getId()+". "+this.getName()+" is a computer manufactured by "
					 +this.getCompany().getName()
					 +" in "+timestampToString(this.getIntroducedDate())
					 +" and discontinued in "
					 +timestampToString(this.getDiscontinuedDate());
		 }
	}
}





