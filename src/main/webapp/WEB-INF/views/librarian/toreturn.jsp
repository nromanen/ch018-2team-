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
			t.ex2 {position:fixed; right:430px; top: 195px}
		</style>
<title>Librarian Books To Return</title>

</head>
<body>
  <div class="container">
  	<div class="row">
  		<div class="col-md-2">
			<c:set var="highlight" value="books" scope="request"/>
			<a href="<c:url value="/librarian/users"/>"><h4>Users</h4></a>
			<a href="<c:url value="/librarian/books"/>"><h4>Books</h4></a>
			<a href="<c:url value="/librarian/orders"/>"><h4>Orders</h4></a>
			<a href="<c:url value="/librarian/toreturn"/>"><h4>To Return</h4></a>
		</div>
		<div class="col-md-7">
				<div class="col-md-7 col-md-offset-7">
				<form name="search" action="<c:url value="/librarian/orders/search" />" method="POST" >
						
						<input type="text"  name="request"/>
						<input type="submit" value="Show Result"/>
						
						</form>
				</div>	
				<center>	
				<table border = "1">
				        <thead>
				                <tr>
				                        <td> <b> ID </b> </td>
				                        <td> <b> Person </b> </td>
				                        <td> <b> Book </b> </td>
				                        <td> <b> Return Date</b> </td>
				                        <td> <b> Options </b> </td>
				                         
				                </tr>
				        </thead>
				        <c:forEach items="${booksInUse}" var="bookInUse">
				                <tr>
				                        <td>${bookInUse.id}</td>
				                        <td>${bookInUse.person.getName()}</td>
				                        <td>${bookInUse.book.getTitle()}</td>
				                        <td>${bookInUse.getReturnDate()}</td>
				                        <td><a href="<c:url value="/librarian/toreturn/getback?id=${bookInUse.id}"/>">Get back</a>
				                        <a href="<c:url value="/librarian/toreturn/edit?id=${bookInUse.id}"/>">Edit</a></td>
				                </tr>
				        </c:forEach>
				</table>
				</center>
</div>
</div>
</div>
</body>
</html>