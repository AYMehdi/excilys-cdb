package main.java.com.excilys.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.excilys.exceptions.ComputerNotFoundException;
import main.java.com.excilys.exceptions.DAOException;
import main.java.com.excilys.exceptions.DiscontinuedBeforeIntroducedException;
import main.java.com.excilys.exceptions.MismatchDateException;
import main.java.com.excilys.mappers.ComputerDTOMapper;
import main.java.com.excilys.models.Company;
import main.java.com.excilys.models.ComputerDTO;
import main.java.com.excilys.persistence.CompanyDAO;
import main.java.com.excilys.persistence.ComputerDAO;
import main.java.com.excilys.persistence.DAOFactory;
import main.java.com.excilys.services.ComputerService;

public class EditComputer extends HttpServlet {
	
	private static final long serialVersionUID = -641726259929350647L;
	
	private static final String VUE_EDIT_COMPUTER = "/views/editComputer.jsp";
	private static final String VUE_LIST_COMPUTERS = "listComputers";
	private static final String ATT_COMPUTER = "computer";
	private static final String ATT_LIST_MANUFACTURERS = "company";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int computerID = Integer.valueOf(request.getParameter("computerID"));

		CompanyDAO companyDAO = DAOFactory.getInstance().getCompanyDAO();
		ArrayList<Company> company = new ArrayList<Company>();
		
		try {
			company = companyDAO.getAll();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		ComputerDTO computerDTO = null;
		
		try {
			computerDTO = ComputerDTOMapper.map(ComputerDAO.getById(computerID).get());
		} catch (ComputerNotFoundException | DAOException e) {
			e.printStackTrace();
		}
		
		request.setAttribute(ATT_LIST_MANUFACTURERS, company);
		request.setAttribute(ATT_COMPUTER, computerDTO);
		
		this.getServletContext().getRequestDispatcher(VUE_EDIT_COMPUTER).forward(request, response);
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ComputerService computerService = new ComputerService();
		try {
			computerService.update(request);
		} catch (ComputerNotFoundException e) {
			e.printStackTrace();
		} catch (MismatchDateException e) {
			e.printStackTrace();
		} catch (DiscontinuedBeforeIntroducedException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(VUE_LIST_COMPUTERS);
	}
}