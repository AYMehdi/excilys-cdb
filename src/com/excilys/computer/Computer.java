package com.excilys.computer;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Computer {
	
	// ******* VARIABLES *******
	private String nom;
	private String manufacturer;
	private Date introducedDate;
	private Date discontinuedDate;
	
	// ******* CONSTRUCTEURS *******
	public Computer(String nom) {
		this.setNom(nom);
		this.setManufacturer("unknown company");
	}
	
	public Computer(String nom, String manufacturer) {
		this.setNom(nom);
		this.setManufacturer(manufacturer);
	}
	
	public Computer(String nom, String manufacturer, Date introducedDate) {
		this.setNom(nom);
		this.setManufacturer(manufacturer);
		this.setIntroducedDate(introducedDate);
	}
	
	public Computer(String nom, String manufacturer, Date introducedDate, Date discontinuedDate) {
		this.setNom(nom);
		this.setManufacturer(manufacturer);
		this.setIntroducedDate(introducedDate);
		if (this.getIntroducedDate().compareTo(discontinuedDate) < 0) {
			this.setDiscontinuedDate(discontinuedDate);
		}
	}
	
	public Computer(String nom, Date introducedDate) {
		this.setNom(nom);
		this.setManufacturer("unknown company");
		this.setIntroducedDate(introducedDate);
	}
	
	public Computer(String nom, Date introducedDate, Date discontinuedDate) {
		this.setNom(nom);
		this.setManufacturer("unknown company");
		this.setIntroducedDate(introducedDate);
		if (this.getIntroducedDate().compareTo(discontinuedDate) < 0) {
			this.setDiscontinuedDate(discontinuedDate);
		}
	}
	
	// ******* GETTERS *******
	public String getNom() {
		return this.nom;
	}
	
	public String getManufacturer() {
		return this.manufacturer;
	}
	
	public Date getIntroducedDate() {
		return this.introducedDate;
	}
	
	public Date getDiscontinuedDate() {
		return this.discontinuedDate;
	}
	
	// ******* SETTERS *******
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public void setIntroducedDate(Date introducedDate) {
		this.introducedDate = introducedDate;
	}
	
	public void setDiscontinuedDate(Date discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}
	
	// ******* AUTRES METHODES *******
	public static Date StringToDate(String dateString) throws Exception {
	    Date result = null;
	    
	    try{
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        result  = dateFormat.parse(dateString);
	    }
	    catch(ParseException e){
	        e.printStackTrace();
	    }
	    return result ;
	}
	
	public static String DateToString(Date date) {
	    String result = null;
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    result  = dateFormat.format(date);
	    return result ;
	}
	
	public String toString(){
		 if(this.getIntroducedDate() == null) {
			 return this.getNom()+" is a computer built by "
					 +this.getManufacturer();
		 }
		 else if(this.getIntroducedDate() != null && this.getDiscontinuedDate() == null) {
			 return this.getNom()+" is a computer built by "
					 +this.getManufacturer()+ " in "
					 +DateToString(this.getIntroducedDate());
		 }
		 else {
			 return this.getNom()+" is a computer built by "
					 +this.getManufacturer()
					 +" in "+DateToString(this.getIntroducedDate())
					 +" and discontinued in "
					 +DateToString(this.getDiscontinuedDate());
		 }
	}
}
