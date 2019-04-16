package main.java.com.excilys.mappers;

import main.java.com.excilys.models.Computer;
import main.java.com.excilys.models.ComputerDTO;
import main.java.com.excilys.models.builders.CompanyBuilder;

public class ComputerBeanMapper {
	
	public static Computer map(ComputerDTO computerDTO) throws Exception {
		Computer computer = new Computer();
		
		computer.setId(computerDTO.getComputerId());
		computer.setName(computerDTO.getComputerName());
		computer.setCompany(new CompanyBuilder().withId(computerDTO.getCompanyId())
			.withName(computerDTO.getCompanyName()).build());

		if (Computer.stringToTimestamp(computerDTO.getIntroducedDate()) != null) {
			computer.setIntroducedDate(Computer.stringToTimestamp(computerDTO.getIntroducedDate()));
		}
		
		if (Computer.stringToTimestamp(computerDTO.getDiscontinuedDate()) != null) {
			computer.setDiscontinuedDate(Computer.stringToTimestamp(computerDTO.getDiscontinuedDate()));
		}

		return computer;
	}
}