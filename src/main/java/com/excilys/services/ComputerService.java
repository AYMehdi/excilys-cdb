package main.java.com.excilys.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import main.java.com.excilys.exceptions.ComputerNotFoundException;
import main.java.com.excilys.mappers.ComputerBeanMapper;
import main.java.com.excilys.mappers.ComputerDTOMapper;
import main.java.com.excilys.models.Company;
import main.java.com.excilys.models.Computer;
import main.java.com.excilys.models.ComputerDTO;
import main.java.com.excilys.models.Page;
import main.java.com.excilys.models.builders.ComputerBuilder;
import main.java.com.excilys.models.builders.ComputerDTOBuilder;
import main.java.com.excilys.persistence.ComputerDAO;
import main.java.com.excilys.persistence.DAOFactory;

public class ComputerService {
	
	// ******* VARIABLES *******
	private ComputerDAO computerDAO;
	private Page<Computer> page;
	
	// ******* CONSTRUCTOR *******
	public ComputerService() {
		this.computerDAO = DAOFactory.getInstance().getComputerDAO();
		ArrayList<Computer> computer = new ArrayList<Computer>();
		computer = computerDAO.getAll();
		this.page = new Page<Computer>(computer);
	}
	
	// ******* GETTERS *******
	public ComputerDAO getComputerDAO() {
		return computerDAO;
	}
	
	public Page<Computer> getPage() {
		return this.page;
	}
	
	// ******* SETTERS *******
	public void setComputerDAO(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}
	
	public void setPage(Page<Computer> page) {
		this.page = page;
	}
	
	// ******* METHODSS *******
	public ComputerDTO getComputer(int id) throws ComputerNotFoundException, FileNotFoundException, SQLException, IOException {
		ComputerDAO.getInstance();
		return ComputerDTOMapper.map(ComputerDAO.getById(id).get());
	}
	
	public List<ComputerDTO> listComputers() throws FileNotFoundException, IOException, SQLException, ComputerNotFoundException {
		ArrayList<Computer> computers = computerDAO.getAll();
		List<Computer> restrictedList = page.displayComputerList(computers);
		return getAllDTOs(restrictedList);
	}
	
	public List<ComputerDTO> getAll(HttpServletRequest request) throws FileNotFoundException, IOException, SQLException, ComputerNotFoundException {
		ArrayList<Computer> computers = new ArrayList<>();
		String searchStr = request.getParameter("search");
		if (searchStr != null && !searchStr.equals("")){
			for (Computer computerTested : computerDAO.getAll()) {
				Pattern pattern = Pattern.compile(".*" + searchStr.toLowerCase() + ".*");
				Matcher macherComputerName = pattern.matcher(computerTested.getName().toLowerCase());
				
				if (computerTested.getCompany().getName() != null && !computerTested.getCompany().getName().equals("")) {
					Matcher macherCompanyName = pattern.matcher(computerTested.getCompany().getName().toLowerCase());
					if (macherCompanyName.matches() || macherComputerName.matches()) {
						computers.add(computerTested);
					}
				} else {
					if (macherComputerName.matches()) {
						computers.add(computerTested);
					}
				}
			}
		} else {
			computers = computerDAO.getAll();
		}
		return getAllDTOs(computers);
	}
	
	public List<ComputerDTO> getAllDTOs(List<Computer> computers) throws ComputerNotFoundException{
		List<ComputerDTO> result = new ArrayList<ComputerDTO>();
		for (Computer c : computers) {
			result.add(ComputerDTOMapper.map(c));
		}
		return result;
	}
	
	public void add(HttpServletRequest request) throws Exception {
		int companyId = Integer.valueOf(request.getParameter("companyId"));
		String computerName = request.getParameter("computerName");
		String companyName = request.getParameter("companyName");
		String introducedDateString = request.getParameter("introducedDate");
		String discontinuedDateString = request.getParameter("discontinuedDate");
		
		ComputerDTO computerDTO = new ComputerDTOBuilder()
				.withComputerName(computerName)
				.withCompanyId(companyId)
				.withCompanyName(companyName)
				.withIntroducedDate(introducedDateString)
				.withDiscontinuedDate(discontinuedDateString)
				.build();
		
		Computer newComputer = ComputerBeanMapper.map(computerDTO);
		ComputerDAO.add(newComputer);
	}
	
	public void update(HttpServletRequest request) throws Exception {
		int computerID = Integer.valueOf(request.getParameter("computerID"));
		int companyId = Integer.valueOf(request.getParameter("companyId"));
		String computerName = request.getParameter("computerName");
		String introducedDateString = request.getParameter("introducedDate");
		String discontinuedDateString = request.getParameter("discontinuedDate");
		
		Computer computer = new ComputerBuilder()
				.withId(computerID)
				.withName(computerName)
				.withCompany(new Company(companyId))
				.withIntroducedDate(Computer.stringToTimestamp(introducedDateString))
				.withDiscontinuedDate(Computer.stringToTimestamp(discontinuedDateString))
				.build();
		
		ComputerDAO.update(computer);
	}
	
	public void remove(int computerID) throws ComputerNotFoundException, FileNotFoundException, IOException, SQLException {
		ComputerDAO.remove(ComputerDAO.getById(computerID).get());
	}
	
	public void sortComputers(List<ComputerDTO> computers, HttpServletRequest request) {
		
		String sorted = request.getParameter("sorted");
		if (sorted != null && !sorted.equals("")) {
			
			switch(generateEnumFromString(sorted)) {
			
				case BY_NAME:
					page.sortByName(computers);
					page.setSorted(CasesSorted.BY_NAME.getSortType());
					break;
					
				case BY_COMPANY:
					page.sortByCompany(computers);
					page.setSorted(CasesSorted.BY_COMPANY.getSortType());
					break;
					
				case BY_INTRODUCED:
					page.sortByIntroduced(computers);
					page.setSorted(CasesSorted.BY_INTRODUCED.getSortType());
					break;
					
				case BY_DISCONTINUED:
					page.sortByDiscontinued(computers);
					page.setSorted(CasesSorted.BY_DISCONTINUED.getSortType());
					break;
					
				case BY_NAME_DECREASING:
					page.sortByName(computers);
					Collections.reverse(computers);
					page.setSorted(CasesSorted.BY_NAME_DECREASING.getSortType());
					break;
					
				case BY_COMPANY_DECREASING:
					page.sortByCompany(computers);
					Collections.reverse(computers);
					page.setSorted(CasesSorted.BY_COMPANY_DECREASING.getSortType());
					break;
					
				case BY_INTRODUCED_DECREASING:
					page.sortByIntroduced(computers);
					Collections.reverse(computers);
					page.setSorted(CasesSorted.BY_INTRODUCED_DECREASING.getSortType());
					break;
					
				case BY_DISCONTINUED_DECREASING:
					page.sortByDiscontinued(computers);
					Collections.reverse(computers);
					page.setSorted(CasesSorted.BY_DISCONTINUED_DECREASING.getSortType());
					break;
			}
		}
	}
	
	public CasesSorted generateEnumFromString(String str) {
		
		for (CasesSorted casesSorted : CasesSorted.values()) {
			if (casesSorted.getSortType().equals(str)) {
				return casesSorted;
			}
		}
		
		return CasesSorted.BY_NAME;
	}
}