package main.java.com.excilys.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.excilys.exceptions.ComputerNotFoundException;
import main.java.com.excilys.models.ComputerDTO;
import main.java.com.excilys.services.ComputerService;

public class ListComputers extends HttpServlet {

	private static final long serialVersionUID = 6648745594952899706L;
	
	public static final String VUE_LIST_COMPUTERS = "WEB-INF/views/listComputers.jsp";
	public static final String ATT_LIST_COMPUTERS = "computersDTO";
	public static final String ATT_PAGINATION = "pagination";
	public static final String ATT_SORTED = "sorted";
	public static final String ATT_SEARCH = "search";
	public static final String REDIRECT_DASHBOARD = "listComputers";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		ComputerService computerService = new ComputerService();
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		
		try {
			computersDTO = computerService.getAll(request);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ComputerNotFoundException e) {
			e.printStackTrace();
		}
		
		computerService.sortComputers(computersDTO, request);
		
		request.setAttribute(ATT_SEARCH, request.getParameter("search"));
		request.setAttribute(ATT_SORTED, request.getParameter("sorted"));
		request.setAttribute(ATT_LIST_COMPUTERS, computersDTO);
		
		request.getRequestDispatcher(VUE_LIST_COMPUTERS).forward(request, response);
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] checkboxValues = request.getParameterValues("cb");
		List<String> idToRemove = Arrays.asList(checkboxValues);
		ComputerService computerService = new ComputerService();
		
		for (String idStr : idToRemove) {
			Integer id = Integer.valueOf(idStr);
			try {
				computerService.remove(id);
			} catch (ComputerNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		response.sendRedirect(REDIRECT_DASHBOARD);
	}
}