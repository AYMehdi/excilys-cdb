package main.java.com.excilys.mapper;

import main.java.com.excilys.model.Computer;
import main.java.com.excilys.model.dto.ComputerDTO;

public class ComputerDTOMapper {

	private static final String EMPTY = "";
	
	public static ComputerDTO map(Computer computer) {
		
		ComputerDTO dto = new ComputerDTO();
		
		dto.setId(computer.getId());
		dto.setName(computer.getName());
		dto.setCompany( computer.getCompany().getName());
		
		if(computer.getIntroducedDate() == null) {
			dto.setIntroducedDate(EMPTY);			
		} else {
			dto.setIntroducedDate(computer.getIntroducedDate().toString());
		}
		
		if(computer.getDiscontinuedDate() == null) {
			dto.setDiscontinuedDate(EMPTY);			
		} else {
			dto.setDiscontinuedDate(computer.getDiscontinuedDate().toString());
		}
		
		return dto;
	}
	
}
