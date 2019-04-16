<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Computer Database</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta charset="utf-8">

		<!-- Bootstrap -->
		<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" media="screen">
		<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" media="screen">
	</head>
	
	<body>
	    <c:import url="/navbar.jsp"/>
	
	    <section id="main">
	        <div class="container">
	            <div class="row">
	                <div class="col-xs-8 col-xs-offset-2 box">
	                    <h1>Add Computer</h1>
	                   
	                    <form id="computerForm" action="addComputer" method="POST">
	                        <fieldset>
	                            <div class="form-group">
	                                <label for="computerName">Computer name</label>
	                                <input type="text" class="form-control" id="computerName" name="computerName" required="required" placeholder="Computer name">
	                            </div>
	                            
	                            <div class="form-group">
	                                <label for="companyId">Manufacturer</label>
	                                <select class="form-control" id="companyId" name="companyId">
	                                    <option value="0">--</option>
	                                    <c:set var="count" value="1"></c:set>
	                                    <c:forEach items="${company}" var="company">
	                                    	<option class="companyId-option" value="${count}">${company.name}</option>
	                                    	 <c:set var="count" value="${count + 1}"></c:set>
	                                    </c:forEach>
	                                </select>
	                            </div>
	                            
	                            <div class="form-group">
	                                <label for="introduced">Introduced date</label>
	                                <input type="date" class="form-control" id="introduced" name="introducedDate" placeholder="Introduced date">
	                            </div>
	                            
	                            <div class="form-group">
	                                <label for="discontinued">Discontinued date</label>
	                                <input type="date" class="form-control" id="discontinued" name="discontinuedDate" placeholder="Discontinued date">
	                            </div>
	                        </fieldset>
	                        
	                        <div class="actions pull-right">
	                            <input id="addButton" type="submit" value="Add" class="btn btn-primary">
	                            or
	                            <a id="cancelButton" href="listComputers" class="btn btn-default">Cancel</a>
	                        </div>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </section>
	    
		<script src="/js/jquery.min.js"></script>
		<script src="/js/bootstrap.min.js"></script>
		<script src="/js/dashboard.js"></script>
	</body>
</html>