package main.java.com.excilys.models;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	public Computer(int id, String name, Company company, Timestamp introducedDate, Timestamp discontinuedDate) {
		this.setId(id);
		this.setName(name);
		this.setCompany(company);
		this.setIntroducedDate(introducedDate);
		this.setDiscontinuedDate(discontinuedDate);
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
		if (discontinuedDate != null && this.getIntroducedDate().compareTo(discontinuedDate) <= 0) {
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
	
	@Override
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
			 return "ID : "+this.getId()+". "+this.getName()
			 		 +" is a computer manufactured by " +this.getCompany().getName()
					 +" in "+timestampToString(this.getIntroducedDate())
					 +" and discontinued in " +timestampToString(this.getDiscontinuedDate());
		 }
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Computer)) {
			return false;
		}
		return idEquals(o) 
				&& nameEquals(o)
				&& companyEquals(o)
				&& introducedEquals(o)
				&& discontinuedEquals(o);
	}
	
	private boolean idEquals(Object o) {
		if (this.id == 0)
			return (((Computer) o).getId() ==0);
		else
			return (this.getId()==((Computer) o).getId());
	}

	private boolean nameEquals(Object o) {
		if (this.getName() == null)
			return (((Computer) o).getName() == null);
		else
			return this.getName().equals(((Computer) o).getName());
	}

	private boolean companyEquals(Object o) {
		if (this.getCompany() == null)
			return (((Computer) o).getCompany() == null);
		else
			return this.getCompany().equals( ((Computer) o).getCompany());
	}

	private boolean introducedEquals(Object o) {
		if (this.getIntroducedDate()== null)
			return (((Computer) o).getIntroducedDate()==null);
		else
			return this.getIntroducedDate().equals( ((Computer) o).getIntroducedDate());
	}

	private boolean discontinuedEquals(Object o) {
		if (this.getDiscontinuedDate()== null)
			return (((Computer) o).getDiscontinuedDate()==null);
		else
			return this.getDiscontinuedDate().equals( ((Computer) o).getDiscontinuedDate());
	}

	@Override
	public int hashCode() {
		return this.getId() + this.getName().hashCode() + this.getCompany().hashCode()
				+ this.getIntroducedDate().hashCode() + this.getDiscontinuedDate().hashCode();
	}
}