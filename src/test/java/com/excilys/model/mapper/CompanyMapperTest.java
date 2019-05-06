package com.excilys.model.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.model.Company;
import com.excilys.model.dto.CompanyDTO;

public class CompanyMapperTest {
	
// ******* VARIABLES *******
	Company company;
	CompanyDTO companyDTO;
	
// ******* BEFORE *******
	@Before
	public void setUp() {
		company = new Company();
		companyDTO = new CompanyDTO();
	}
	
// ******* TEST *******
	@Test
	public void companyToCompanyDTOTest() {
		company = new Company(44, "Dell");
		companyDTO = CompanyMapper.companyToCompanyDTO(company);
		assertEquals(company.getId(), companyDTO.getId());
		assertEquals(company.getName(), companyDTO.getName());
		}
	
	@Test
	public void companyDTOToCompanyTest() {
		companyDTO = new CompanyDTO(44, "Dell");
		company = CompanyMapper.companyDTOToCompany(companyDTO);
		assertEquals(company.getId(), companyDTO.getId());
		assertEquals(company.getName(), companyDTO.getName());
	}
	
// ******* AFTER *******
	@After
	public void tearDown() {
		company = null;
		companyDTO = null;
	}
}