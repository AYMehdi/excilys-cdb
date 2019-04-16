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
		<link href="../static/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="../static/css/font-awesome.css" rel="stylesheet" media="screen">
		<link href="../static/css/main.css" rel="stylesheet" media="screen">
	
	<body>
	    <c:import url="/navbar.jsp"/>
	    
	    <section id="main">
	        <div class="container">
	            <div class="row">
	                <div class="col-xs-8 col-xs-offset-2 box">
	                    <div class="label label-default pull-right">ID : ${computer.id}</div>
	                    <h1>Remove Computer</h1>
	                    
	                    <form id="computerForm" action="removeComputer" method="POST">
	                        <input type="hidden" value="${computer.id}" id="id" name="computerId"/>
	                        <fieldset>
	                            <div class="form-group">
	                                <label for="computerID">Computer ID</label>
	                                <input type="number" class="form-control" id="computerID" value="${computer.name}" name="computerID" required="required">
	                            </div>            
	                        </fieldset>
	                        
	                        <div class="actions pull-right">
	                            <input id="removeButton" type="submit" value="Edit" class="btn btn-primary">
	                            or
	                            <a id="cancelButton" href="listComputers" class="btn btn-default">Cancel</a>
	                        </div>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </section>
	    
		<script src="../static/js/jquery.min.js"></script>
		<script src="../static/js/bootstrap.min.js"></script>
		<script src="../static/js/dashboard.js"></script>
	</body>
</html>