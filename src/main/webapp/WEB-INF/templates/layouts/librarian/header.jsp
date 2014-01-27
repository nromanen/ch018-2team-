<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"> </script>
		<link href="http://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <c:url value="/resources/css/bootstrap.min.css" var="bootstrapCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${bootstrapCSS}" />

			<link href="http://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet">
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">



        <c:url value="/resources/css/main.css" var="mainCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${mainCSS}" /> 
        <c:url value="/resources/css/jquery.datetimepicker.css" var="dateCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${dateCSS}" /> 
        <c:url value="/resources/css/search.css" var="searchCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${searchCSS}" />
        <c:url value="/resources/css/orders.css" var="ordersCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${ordersCSS}" />

        <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.datetimepicker.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/orders.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/search.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/books.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>


    <style>
			html{
					background-color: ;
				}
			body{
					background-color: #FFFFFF;
				}
				
		</style>
<title><spring:message code="message.lib"/></title>

</head>
	<div class="row" style="background-color: #00A1A1" >
	
		<div class="col-md-2 col-md-offset-1">
			<a href="<c:url value="/librarian/books"/>" style="color: #FFFFFF"><h5><strong><spring:message code="message.lib"/></strong></h5></a>
		</div>	
		<div class="col-md-1">
			<a href="${pageContext.request.contextPath}/j_spring_security_logout" style="color: #FFFFFF"><h5> Log Out </h5></a>
		</div>	
		<div class="col-md-1 col-md-offset-2">
			<a href="<c:url value="/librarian/genres"/>" style="color: #FFFFFF"><h5>Genres</h5></a>
			<c:set var="highlight" value="books" scope="request"/>
		</div>
		<div class="col-md-1">
			<a href="<c:url value="/librarian/users"/>" style="color: #FFFFFF"><h5><spring:message code="message.libUsers"/></h5></a>
			<c:set var="highlight" value="books" scope="request"/>
		</div>
		<div class="col-md-1">
			<a href="<c:url value="/librarian/books"/>" style="color: #FFFFFF"><h5><spring:message code="message.libBooks"/></h5></a>
		</div>
		<div class="col-md-1">
			<a href="<c:url value="/librarian/orders"/>" style="color: #FFFFFF"><h5><spring:message code="message.libOrders"/></h5></a>
		</div>
		<div class="col-md-1">
			<a href="<c:url value="/librarian/toreturn"/>" style="color: #FFFFFF"><h5><spring:message code="message.libToReturn"/></h5></a>
		</div>
		</div>
