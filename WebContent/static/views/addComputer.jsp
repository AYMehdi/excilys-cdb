<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>Computer Database</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Bootstrap -->
	<link href="static/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="static/css/font-awesome.css" rel="stylesheet" media="screen">
	<link href="static/css/main.css" rel="stylesheet" media="screen">
</head>

<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="<c:url value="/Dashboard?currentPage=0"/>"> Application -	Computer Database </a> 
			<input type="button" onclick="location.href='<c:url value="/Dashboard?lang=en"/>'" value="EN">
			<input type="button" onclick="location.href='<c:url value="/Dashboard?lang=fr"/>'" value="FR">
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>
						<spring:message code="addComputer" />
					</h1>
					
					<form:form method="POST" action="AddComputer" modelAttribute="computer">
						<fieldset>
							<div class="form-group">
								<form:label path="computerName" for="computerName">
									<spring:message code="computerName" var="computerName" />
									<spring:message code="computerName" />
								</form:label>
								<form:input path="computerName" type="text" class="form-control" id="name" name="name" placeholder="${computerName}" required="required" />
							</div>
							<div class="form-group">
								<form:label path="companyId" for="companyId">
									<spring:message code="company" />
								</form:label>
								<form:select path="companyId" class="form-control" id="companyId" name="companyId">
									<option value="0">--</option>
									<c:forEach var="company" items="${listCompanies}">
										<form:option value="${company.id}">${company.name}</form:option>
									</c:forEach>
								</form:select>
							</div>
							<div class="form-group">
								<form:label path="introducedDate" for="introducedDate">
									<spring:message code="introducedDate" />
								</form:label>
								<form:input path="introducedDate" type="date" class="form-control" id="introducedDate" name="introducedDate" placeholder="Introduced date" />
							</div>
							<div class="form-group">
								<form:label path="discontinuedDate" for="discontinuedDate">
									<spring:message code="discontinuedDate" />
								</form:label>
								<form:input path="discontinuedDate" type="date" class="form-control" id="discontinuedDate" name="discontinuedDate"
									placeholder="Discontinued date" />
							</div>
						</fieldset>

						<div class="exception">
							<font color="red" size="+1"> <c:out value="${exception}" />
							</font>
						</div>

						<div class="actions pull-right">
							<input type="submit" value="<spring:message code="add" />" class="btn btn-primary">
							<spring:message code="or" />
							<a href="<c:url value="/Dashboard"/>" class="btn btn-default"><spring:message code="cancel" /></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>