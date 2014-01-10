<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <c:url value="/resources/css/bootstrap.min.css" var="bootstrapCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${bootstrapCSS}" />
        <c:url value="/resources/css/main.css" var="mainCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${mainCSS}" /> 
        <c:url value="/resources/css/jquery.datetimepicker.css" var="dateCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${dateCSS}" /> 
        <c:url value="/resources/css/search.css" var="searchCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${searchCSS}" />
        <c:url value="/resources/css/orders.css" var="ordersCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${ordersCSS}" />
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.datetimepicker.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/orders.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/search.js" />"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/books.js"></script>
		<style>
			t.ex1 {margin:300px 20px}
			t.ex3 {margin:10px 70px}
			t.ex2 {margin: 50px 30px 30px 550px}
		</style>
<title>Librarian Users</title>

</head>
<body>

<div class="container">

	<div class="row">
	
		<div class="col-md-1 col-md-offset-7">
     		<a href="<c:url value="/librarian/users"/>"><h5>Users</h5></a>
		</div>
		<div class="col-md-1">
			<a href="<c:url value="/librarian/books"/>"><h5>Books</h5></a>
		</div>
		<div class="col-md-1">
			<a href="<c:url value="/librarian/orders"/>"><h5>Orders</h5></a>
		</div>
		<div class="col-md-1">
			<a href="<c:url value="/librarian/toreturn"/>"><h5>To Return</h5></a>
		</div>
	</div>
	<div class=row>
			
			<div class="col-md-12" style="margin-top:15px">
				<center>
					<form name="search" action="<c:url value="/librarian/users/simplesearch" />" method="POST" >
						<input type="text"  name="request"/> <input type="submit" value="Search"/> 
						<t class="ex1"><a href="<c:url value="/librarian/users/advencedsearch"/>">Advanced</a></t>
					</form>
				</center>	
			</div>
	</div>
	<div class="row">		
			<div class="col-md-12">
				<center>
					<div class="col-md-12" style="margin-top:30px">
					
						<table class="table table-hover table-striped table-bordered table-condensed">
		     			   <thead>
		         		       <tr>
		           	                <td> <b> ID </b> </td>
		           	   	            <td> <b> First Name </b> </td>
		            	            <td> <b> Last Name </b> </td> 
		                            <td> <b> E-mail </b> </td>
		                            <td> <b> Role</b> </td>
		                            <td> <b> Cell Phone </b> </td>
		                            <td> <b> Confirmed </b> </td>
		                            <td> <b> SMS  </b> </td>    
		                            <td> <b> Returned in time </b> </td>
		                            <td> <b> Returned not in time </b> </td> 
		                            <td> <b> Books on Hands</b> </td>
		                            <td> <b> Rating </b> </td>
		                            <td> <b> Options </b> </td>
		               	         
		               		   </tr>
		       			 </thead>
				        <c:forEach items="${users}" var="user">
				                <tr>
				                
				                        <td>${user.pid}</td>
				                        <td>${user.name}</td>
				                        <td>${user.surname}</td>
				                        <td>${user.email}</td>
				                        <td>${user.prole}</td>
				                        <td>${user.cellphone}</td>
				                        <td>${user.confirm}</td>
				                        <td>${user.sms}</td>
				                        <td>${user.timelyReturn}</td>
				                        <td>${user.untimekyReturn}</td>
				                        <td>${user.multiBook} / ${user.booksAllowed}</td>
				                        <td>${user.generalRating}/100</td>
				                        
				                    <td><a href="<c:url value="/librarian/users/edituser?id=${user.pid}"/>">Edit</a>
				                        <a href="<c:url value="/librarian/users/deleteuser?id=${user.pid}"/>">Delete</a></td>
				                </tr>
				        </c:forEach>
			   			</table>
					</div>
				</center>
					
				
			</div>
		<div class="row">

	<div class="col-md-1 col-md-offset-9" style="margin-top:10px">
		<a href="<c:url value="/librarian/users/adduser"/>">Add User</a>
	</div>

</div>		
		</div>
	</div>
	
</body>
</html>