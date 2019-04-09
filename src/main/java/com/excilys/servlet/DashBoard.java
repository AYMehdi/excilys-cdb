package main.java.com.excilys.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DashBoard",urlPatterns= {"/dashboard"})
public class DashBoard extends HttpServlet {

	private static final long serialVersionUID = 1916658056294539809L;

	public DashBoard() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		PrintWriter writer = resp.getWriter();
		writer.println("Hello World !!!");
	}
}