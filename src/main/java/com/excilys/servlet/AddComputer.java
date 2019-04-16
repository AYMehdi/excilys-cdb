package main.java.com.excilys.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.excilys.exceptions.DAOException;
import main.java.com.excilys.exceptions.DiscontinuedBeforeIntroducedException;
import main.java.com.excilys.exceptions.MismatchDateException;
import main.java.com.excilys.exceptions.NoNamedComputerException;
import main.java.com.excilys.models.Company;
import main.java.com.excilys.persistence.CompanyDAO;
import main.java.com.excilys.persistence.DAOFactory;
import main.java.com.excilys.services.ComputerService;

public class AddComputer extends HttpServlet {

	private static final long serialVersionUID = -8114649871346132568L;
	
	public static final String VUE_FORM_NEW_COMPUTER = "/views/addComputer.jsp";
	public static final String VUE_LIST_COMPUTERS = "listComputers";
	public String vueComputerDetails;
	public static final String ATT_LIST_MANUFACTURERS = "company";
	public static final String ATT_FORM = "form";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CompanyDAO companyDAO = DAOFactory.getInstance().getCompanyDAO();
		ArrayList<Company> company = new ArrayList<Company>();
		
		try {
			company = companyDAO.getAll();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		request.setAttribute(ATT_LIST_MANUFACTURERS, company);
		this.getServletContext().getRequestDispatcher(VUE_FORM_NEW_COMPUTER).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerService computerService = new ComputerService();
		
		try {
			computerService.add(request);
		} catch (NoNamedComputerException e) {
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
