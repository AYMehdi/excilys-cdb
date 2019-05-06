<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>Computer Database</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="utf-8">
	<!-- Bootstrap -->
	<link href="static/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="static/css/font-awesome.css" rel="stylesheet" media="screen">
	<link href="static/css/main.css" rel="stylesheet" media="screen">
</head>

<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand"
				href="<c:url value="/Dashboard?currentPage=0"/>"> Application -	Computer Database </a>
			<input type="button" onclick="location.href='<c:url value="/Dashboard?lang=en"/>'" value="EN"> 
			<input type="button" onclick="location.href='<c:url value="/Dashboard?lang=fr"/>'" value="FR">
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				<spring:message code="nbComputers" arguments="${page.size}" />
			</h1>
			
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">
						<input type="search" id="searchbox" name="search"
							class="form-control"
							placeholder="<spring:message code="searchBox" />" /> 
						<input type="submit" id="searchsubmit"
							value="<spring:message code="searchButton" />"
							class="btn btn-primary" />
					</form>
				</div>
				
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer"	href="<c:url value="/AddComputer"/>">
						<spring:message	code="addComputer" /></a> 
					<a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">
						<spring:message	code="edit" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="Dashboard" method="POST">
			<input type="hidden" name="selection" value="">

			<div class="container" style="margin-top: 10px;">
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<!-- Variable declarations for passing labels as parameters -->
							<!-- Table header for Computer Name -->
							<th class="editMode" style="width: 60px; height: 22px;">
								<input type="checkbox" id="selectall" /> 
								<span style="vertical-align: top;"> - 
									<a href="#"	id="deleteSelected" onclick="$.fn.deleteSelected();"> 
										<i class="fa fa-trash-o fa-lg"></i></a>
								</span>
							</th>
							
							<th>
								<a onclick="location.href='<c:url value="/Dashboard?sortBy=name" />'" title="<spring:message code="titleComputerName" />">
									<spring:message	code="computerName" /></a>
							</th>
							<th>
								<a onclick="location.href='<c:url value="/Dashboard?sortBy=companyName" />'" title="<spring:message code="titleComputerName" />">
								<spring:message code="company" /></a>
							</th>							
							<th>
								<a onclick="location.href='<c:url value="/Dashboard?sortBy=introduced" />'" title="<spring:message code="titleIntroducedDate" />">
									<spring:message code="introducedDate" /></a>
							</th>
							<th>
								<a onclick="location.href='<c:url value="/Dashboard?sortBy=discontinued" />'" title="<spring:message code="titleDiscontinuedDate" />">
									<spring:message code="discontinuedDate" /></a>
							</th>
						</tr>
					</thead>
					
					<!-- Browse attribute computers -->
					<tbody id="results">
						<c:forEach var="ordi" items="${page.data}" begin="${page.start()}"
							end="${page.end()}">
							<tr>
								<td class="editMode"><input type="checkbox" name="cb"
									class="cb" value="${computer.id}"></td>
								<td><a
									href="<c:url value="/EditComputer?computerId=${computer.id}"/>"
									onclick="">${computer.name}</a></td>
								<td>${computer.companyName}</td>
								<td>${computer.introducedDate}</td>
								<td>${computer.discontinuedDate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</form>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<li><a href="?currentPage=${page.previousPage()}" aria-label="Previous"> 
					<span aria-hidden="true">&laquo;</span></a>
				</li>
				<li><a href="?currentPage=0">1</a></li>
				<li><a href="#">..</a></li>
				<c:forEach varStatus="index" begin="${page.startIndex()}" end="${page.endIndex()}">
					<li><a href="?currentPage=${index.index-1}">${index.index}</a></li>
				</c:forEach>
				<li><a href="#">..</a></li>
				<li><a href="?currentPage=${page.getMaxPages()-1}">${page.getMaxPages()}</a></li>
				<li><a href="?currentPage=${page.nextPage()}" aria-label="Next">
					<span aria-hidden="true">&raquo;</span></a>
				</li>
			</ul>
		</div>

		<div class="btn-group btn-group-sm pull-right" role="group">
			<button type="button" class="btn btn-default">10</button>
			<button type="button" class="btn btn-default">50</button>
			<button type="button" class="btn btn-default">100</button>
		</div>
	</footer>
	
	<script src="<c:url value="/static/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/static/js/dashboard.js"/>"></script>
</body>
</html>