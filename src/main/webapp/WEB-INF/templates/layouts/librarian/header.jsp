<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
