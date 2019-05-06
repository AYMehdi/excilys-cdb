package com.excilys.main;


import java.sql.SQLException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.controller.ControllerCli;
import com.excilys.exception.DAOException;
import com.excilys.exception.ModelException;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;

public class CliApp {
	
	// ******** MAIN *******
	public static void main(String[] args) throws SQLException, DAOException, ModelException, BeansException, ParseException {
		Logger logger = LoggerFactory.getLogger(CliApp.class);	
		logger.trace("Main started");
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigApp.class);
		
		try {
			logger.info("\n ******* Happy to see you again Sir ! ******* \n");
			new ControllerCli(context.getBean(ComputerDAO.class), context.getBean(CompanyDAO.class));
		} catch (SQLException | DAOException e) {
			e.printStackTrace();
			logger.error("Main crashed");
		} finally{
			logger.info("\n          ******* Goodbye Sir ! ******* \n");
			context.close();
			System.exit(0);	
		}
	}
}