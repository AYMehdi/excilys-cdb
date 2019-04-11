package main.java.com.excilys.mapper;

import main.java.com.excilys.model.Computer;
import main.java.com.excilys.model.ComputerDTO;

public class ComputerDTOMapper {

	private static final String EMPTY = "";
	
	public static ComputerDTO map(Computer computer) {
		
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