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
	
		<div class="col-md-2">
			<t class="ex3">
     		<a href="<c:url value="/librarian/users"/>"><h4>Users</h4></a>
			<a href="<c:url value="/librarian/books"/>"><h4>Books</h4></a>
			<a href="<c:url value="/librarian/orders"/>"><h4>Orders</h4></a>
			<a href="<c:url value="/librarian/toreturn"/>"><h4>To Return</h4></a>
				
			<c:set var="highlight" value="books" scope="request"/>
			</t>
		</div>
		<div class="col-md-7">
			
			<div class="col-md-7 col-md-offset-5">
					<form name="search" action="<c:url value="/librarian/users/simplesearch" />" method="POST" >
						<input type="text"  name="request"/> <input type="submit" value="Search"/> 
						<t class="ex1"><a href="<c:url value="/librarian/users/advencedsearch"/>">Advanced</a></t>
					</form>
			</div>
			
				<table border = "1">
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
                            <td> <b> Books Allowed</b> </td>
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
				                        <td>${user.booksAllowed}</td>
				                        <td>${user.generalRating}/100</td>
				                        
				                    <td><a href="<c:url value="/librarian/users/edituser?id=${user.pid}"/>">Edit</a>
				                        <a href="<c:url value="/librarian/users/deleteuser?id=${user.pid}"/>">Delete</a></td>
				                </tr>
				        </c:forEach>
			   </table>
					<t class="ex2"><a href="<c:url value="/librarian/users/adduser"/>">Add User</a></t>
				
			</div>
		
		</div>
	</div>
	
</body>
</html>