package com.excilys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.model.Page;
import com.excilys.model.dto.ComputerDTO;
import com.excilys.service.ComputerService;

@Controller
public class DashboardController {

	@Autowired
	ComputerService computerService;

	@Autowired
	MessageSource messageSource;

	@GetMapping({"/", "/Dashboard", "/dashboard"})
	public String getDashboard(Model model, 
			@RequestParam(name="page", required=false) Page<ComputerDTO> page, 
			@RequestParam(name="currentPage", required=false) String currentPage, 
			@RequestParam(name="search", required=false) String search, 
			@RequestParam(name="sortBy", required=false) String sortBy, 
			Locale locale) throws IOException {

		List<ComputerDTO> listComputers = new ArrayList<ComputerDTO>();
		try {
			listComputers = computerService.getAll();
		} catch (SQLException e) {
			String exception = messageSource.getMessage("exceptionList", null, locale);
			model.addAttribute("exception", exception);
			return "404";
		}

		if (page == null) {
			page = new Page<ComputerDTO>(listComputers);
		} 
		page.setData(listComputers);

		if (currentPage != null && currentPage != "") {
			int currentPageInt = Integer.parseInt(currentPage);
			if (currentPageInt < page.getMaxPages() && currentPageInt >= 0) {
				page.setIndex(Integer.valueOf(currentPage));
			} else {
				String exception = messageSource.getMessage("exceptionPage", null, locale);
				model.addAttribute("exception", exception);
				return "404";
			}
		} else {
			page.setIndex(0);
		}
		
		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		if (search != null && search != "") {
			try {
				computers = computerService.searchComputers(search);
			} catch (SQLException e) {
				String exception = messageSource.getMessage("exceptionSearch", null, locale);
				model.addAttribute("exception", exception);
				return "404";
			}
			page.setData(computers);
		}

		List<ComputerDTO> computersSorted = new ArrayList<ComputerDTO>();
		if (sortBy != null && sortBy != "") {
			try {
				computersSorted = computerService.sortBy(sortBy);
			} catch (SQLException e) {
				String exception = messageSource.getMessage("exceptionSort", null, locale);
				model.addAttribute("exception", exception);
				return "404";
			}
			page.setData(computersSorted);
		} 
				
		model.addAttribute("page", page);

		return "dashboard";
	}

	@PostMapping({"/", "/Dashboard", "/dashboard"})
	public String postDashboard(Model model, 
			@RequestParam(name="cb", required=true) String[] computersToDelete, 
			Locale locale) 
					throws IOException {

		if (computersToDelete != null) {
			for (String id : computersToDelete) {
				try {
					computerService.remove(id);
				} catch (SQLException e) {
					String exception = messageSource.getMessage("exceptionDeleteComputer", null, locale);
					model.addAttribute("exception", exception);
					return "404";
				}
			}
		} 
		return "redirect:" + "/Dashboard";
	}
}