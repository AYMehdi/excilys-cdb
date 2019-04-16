<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="main.java.com.excilys.models.Computer"%>
<%@ page import="main.java.com.excilys.models.ComputerDTO"%>
<%@ page import="main.java.com.excilys.models.Page"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Computer Database</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta charset="utf-8">
		
		<!-- Bootstrap -->
		<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="${pageContext.request.contextPath}/static/css/font-awesome.css" rel="stylesheet" media="screen">
		<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" media="screen">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
	</head>
	
	<body>
		<header style="text-align:center;"><b>
			****************************************************************** <br />
			****************************************************************** <br />
			***** Welcome in your Computer DataBase Manager ! ***** <br />
			******** Happy to see you again Mr AHMED-YAHIA ! ******** <br />
			****************************************************************** <br />
			****************************************************************** <br /></b>
		</header>
		
	    <section id="main">
	        <div class="container">
	            <h1 id="homeTitle">${computersDTO.size()} Computers found (
		            <c:choose>
		            	<c:when test="${computersDTO.size() > 10}">${page.index + 1} to ${page.PAGE_SIZE}</c:when>
		            	<c:otherwise>${computersDTO.size()}</c:otherwise>
		            </c:choose>
		            printed )
	            </h1>
	            
	            <div id="actions" class="form-horizontal">
	                <div class="pull-left">
	                    <form id="searchForm" action="listComputers" method="GET" class="form-inline">
	                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
	                        <input type="submit" id="searchsubmit" value="Filter by name" class="btn btn-primary" />
	                    </form>
	                </div>
	                
	                <div class="pull-right">
	                    <a class="btn btn-success" id="addComputer" href="addComputer">Add Computer</a> 
	                    <a class="btn btn-default" id="editComputer" onclick="$.fn.toggleEditMode();">Edit</a>
						<a class="btn btn-primary" id="deleteComputer" onclick="displayDeleteAlert();"><i class="fas fa-trash-alt"></i></a> 
	                </div>
	            </div>
	        </div>
			
			<form id="deleteForm" action="listComputers" method="POST">
	        <div class="container" style="margin-top: 10px;">
	            <table id="table" class="table table-striped table-bordered">
	                <thead>
	                    <tr>
	                        <th>Computer name 
		                        <c:choose>
		                        	<c:when test="${page.sorted == 'byName'}">
		                        		<a class="pull-right" href="?index=${page.index}&PAGE_SIZE=${page.PAGE_SIZE}&search=${search}&sorted=byNameReverse"><i class="fas fa-sort"></i></a>
		                        	</c:when>
		                        	<c:otherwise>
		                        		<a class="pull-right" href="?index=${page.index}&PAGE_SIZE=${page.PAGE_SIZE}&search=${search}&sorted=byName"><i class="fas fa-sort"></i></a>
		                        	</c:otherwise>
		                        </c:choose>
	                        </th>
	                        	                        
	                        <th>Company name
		                        <c:choose>
		                        	<c:when test="${page.sorted == 'byCompany'}">
		                        		<a class="pull-right" href="?index=${page.index}&PAGE_SIZE=${page.PAGE_SIZE}&search=${search}&sorted=byCompanyReverse"><i class="fas fa-sort"></i></a>
		                        	</c:when>
		                        	<c:otherwise>
		                        		<a class="pull-right" href="?index=${page.index}&PAGE_SIZE=${page.PAGE_SIZE}&search=${search}&sorted=byCompany"><i class="fas fa-sort"></i></a>
		                        	</c:otherwise>
		                        </c:choose>
	                        </th>
	                        
	                        <th>Introduced date
		                        <c:choose>
		                        	<c:when test="${page.sorted == 'byIntroduced'}">
		                        		<a class="pull-right" href="?index=${page.index}&PAGE_SIZE=${page.PAGE_SIZE}&search=${search}&sorted=byIntroducedReverse"><i class="fas fa-sort"></i></a>
		                        	</c:when>
		                        	<c:otherwise>
		                        		<a class="pull-right" href="?index=${page.index}&PAGE_SIZE=${page.PAGE_SIZE}&search=${search}&sorted=byIntroduced"><i class="fas fa-sort"></i></a>
		                        	</c:otherwise>
		                        </c:choose>
	                        </th>
	                        
	                        <th>Discontinued date
		                        <c:choose>
		                        	<c:when test="${page.sorted == 'byDiscontinued'}">
		                        		<a class="pull-right" href="?index=${page.index}&PAGE_SIZE=${page.PAGE_SIZE}&search=${search}&sorted=byDiscontinuedReverse"><i class="fas fa-sort"></i></a>
		                        	</c:when>
		                        	<c:otherwise>
		                        		<a class="pull-right" href="?index=${page.index}&PAGE_SIZE=${page.PAGE_SIZE}&search=${search}&sorted=byDiscontinued"><i class="fas fa-sort"></i></a>
		                        	</c:otherwise>
		                        </c:choose>
	                        </th>
	                    </tr>
	                </thead>
	                
	                <!-- Browse attribute computersDTO -->
	                <tbody id="results">       	
	                	<c:set var="countComputer" value="1"></c:set>
	                	<c:forEach items="${computersDTO}" var="computer">
		                    <tr class="computer-ligne" <c:if test="${countComputer < page.index || countComputer > page.PAGE_SIZE}">hidden</c:if> >
		                        <td><a href="editComputer?computerID=${computer.computerId}" id="computerName${countComputer}">${computer.computerName}</a></td>
		                        <td>${computer.companyName}</td>
		                        <td>${computer.introducedDate}</td>
		                        <td>${computer.discontinuedDate}</td>
		                    </tr>
		                    <c:set var="countComputer" value="${countComputer + 1}"></c:set>
	                    </c:forEach>
	                </tbody>
	            </table> 
	        </div>
	        </form>
	    </section>
	
	    <footer class="navbar-fixed-bottom">
	        <div class="container text-center">
	            <ul class="page">
	                <li>
	                	<a href="?index=0&PAGE_SIZE=10&search=${search}&sorted=${page.sorted}" id="startPaginationButton">Start</a>
	                	<a href="?index=${page.previous50Index}&PAGE_SIZE=${page.previous50Index + 10}&search=${search}&sorted=${page.sorted}" aria-label="Previous" id="previous50PaginationButton"><i class="fas fa-fast-backward"></i></a>
	                	<a href="?index=${page.previous10Index}&PAGE_SIZE=${page.previous10Index + 10}&search=${search}&sorted=${page.sorted}" aria-label="Previous" id="previous10PaginationButton"><i class="fas fa-step-backward"></i></a>
	                	<a href="?index=${page.next10Index - 10}&PAGE_SIZE=${page.next10Index}&search=${search}&sorted=${page.sorted}" aria-label="Next" id="next10PaginationButton"><i class="fas fa-step-forward"></i></a>
	                	<a href="?index=${page.next50Index - 10}&PAGE_SIZE=${page.next50Index}&search=${search}&sorted=${page.sorted}" aria-label="Next" id="next50PaginationButton"><i class="fas fa-fast-forward"></i></a>
	                	<a href="?index=${page.toEndIndex - 10}&PAGE_SIZE=${page.toEndIndex}&search=${search}&sorted=${page.sorted}" id="endPaginationButton">End</a>
	            	</li>
	       		</ul>
	       	</div>
	    </footer>
	    
		<script src="../static/js/jquery.min.js"></script>
		<script src="../static/js/bootstrap.min.js"></script>
		<script src="../static/js/dashboard.js"></script>
	</body>
</html>