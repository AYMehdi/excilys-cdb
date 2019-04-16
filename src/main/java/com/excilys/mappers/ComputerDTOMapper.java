package main.java.com.excilys.mappers;

import main.java.com.excilys.exceptions.ComputerNotFoundException;
import main.java.com.excilys.models.Computer;
import main.java.com.excilys.models.ComputerDTO;

public class ComputerDTOMapper {

	private static final String EMPTY = "";
	
	public static ComputerDTO map(Computer computer) throws ComputerNotFoundException {
		
		ComputerDTO computerDTO = new ComputerDTO();
		
		computerDTO.setComputerId(computer.getId());
		computerDTO.setComputerName(computer.getName());
		computerDTO.setCompanyId(computer.getCompany().getId());
		computerDTO.setCompanyName(computer.getCompany().getName());
		
		if(computer.getIntroducedDate() == null) {
			computerDTO.setIntroducedDate(EMPTY);			
		} else {
			computerDTO.setIntroducedDate(computer.getIntroducedDate().toString());
		}
		
		if(computer.getDiscontinuedDate() == null) {
			computerDTO.setDiscontinuedDate(EMPTY);			
		} else {
			computerDTO.setDiscontinuedDate(computer.getDiscontinuedDate().toString());
		}
		
		return computerDTO;
	}
}