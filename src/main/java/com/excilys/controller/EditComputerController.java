package com.excilys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.exception.DAOException;
import com.excilys.exception.ValidatorException;
import com.excilys.model.dto.CompanyDTO;
import com.excilys.model.dto.ComputerDTO;
import com.excilys.model.mapper.ComputerMapper;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Controller
public class EditComputerController {

	@Autowired
	ComputerService computerService;

	@Autowired
	CompanyService companyService;
	
	@Autowired
	ComputerMapper computerMapper;
	
	@Autowired
	MessageSource messageSource;

	@GetMapping({"/EditComputer", "/editComputer"})
	public String getEditComputer(Model model, 
			@RequestParam(name="computerID", required=false, defaultValue="") String id, 
			Locale locale) 
					throws IOException {
		
		List<CompanyDTO> listCompanies = new ArrayList<CompanyDTO>();
		try {
			listCompanies = companyService.listeCompagnies();
		} catch (SQLException e) {
			String exception = messageSource.getMessage("exceptionListCompanies", null, locale);
			model.addAttribute("exception", exception);
			return "404";
		} 
		model.addAttribute("listCompanies", listCompanies);

		Optional<ComputerDTO> computer = Optional.empty();
		try {
			computer = computerService.find(id);
		} catch (SQLException e) {
			String exception = messageSource.getMessage("exceptionFindComputer", null, locale);
			model.addAttribute("exception", exception);
			return "404";
		} 
		if (computer.isPresent()) {
			model.addAttribute("computer", computer.get());
		} else {
			String exception = messageSource.getMessage("exceptionFindComputer", null, locale);
			model.addAttribute("exception", exception);
			return "404";
		}

		return "editComputer";
	}

	@PostMapping({"/EditComputer", "/editComputer"})
	public String postEditComputer(@ModelAttribute("computer") ComputerDTO computerDto,
			BindingResult result, Model model, Locale locale) throws IOException, DAOException {

		if (result.hasErrors()) {
            return "404";
        }
		
		try {
			computerService.edit(computerDto);
			return "redirect:/Dashboard";
		} catch (ValidatorException e) {
			String exception = messageSource.getMessage(e.getMessage(), null, locale);
			model.addAttribute("exception", exception);
			String id = Long.toString(computerDto.getComputerId());
			return getEditComputer(model, id, locale);
		} catch (SQLException e) {
			e.printStackTrace();
			String id = Long.toString(computerDto.getComputerId());
			return getEditComputer(model, id, locale);
		} 
	}
}