<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
	    
			<div class="col-md-3 col-md-offset-1" style="margin-top: 30px" >
			
				<a href="<c:url value="/librarian/orders"/>" class="btn btn-default btn-sm">All</a>
				
				<a href="<c:url value="/librarian/orders/toissueinhour"/>" class="btn btn-default btn-sm">To issue hour</a>
	
				<a href="<c:url value="/librarian/orders/toissuetoday"/>" class="btn btn-default btn-sm">To issue today</a>
			</div>
			<div class="col-md-10 col-md-offset-1" style="margin-top: 10px">	
				<table  class="table table-hover table-striped table-bordered table-condensed">
		    		    <thead>
				                <tr>
				                        <td> <b> Person </b> </td>
				                        <td> <b> Book </b> </td>
				                        <td> <b> Order Date</b> </td>
				                        <td> <b> Options </b> </td>
				                         
				                </tr>
		       		   </thead>
				        <c:forEach items="${orders}" var="order">
				                <tr>
				                    	<td hidden="true">${order.id}</td>
				                        <td>${order.person.name} ${order.person.surname}</td>
				                        <td>${order.book.title}</td>
				                        <!-- <td>${order.getOrderDate()}</td>  --> 
				                        <td><c:out  value="${fn:substring(order.getOrderDate(), 0, 11)}"/></td>
				                        
				                        <td><a href="<c:url value="/librarian/orders/issue?id=${order.id}"/>" style="color: #0E3846" >Issue</a>
				                </tr>
				        </c:forEach>
				</table>
				</div>
