<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			t.ex2 {margin: 50px 30px 30px 500px}
		</style>
<title>Librarian Book Holders</title>

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
	<div style="margin-top:15px">
    <h5> <b> Title: </b> ${book.getTitle()} </h5> <h5> <b> Autors: </b> ${book.getAuthors()} </h5>
	<table   border = "1" style="margin-top:15px">
		 <thead>
                <tr>
                        <td> <b> Holder </b> </td>
                        <td> <b> Return Date </b> </td>
                        <td> <b> Days to Return </b> </td>
                        
                </tr>
        </thead>
		<c:forEach items="${booksInUse}" var="bookInUse">
                <tr>
                        <td>${bookInUse.person}</td>
                        <td>${bookInUse.getReturnDate()}</td>
                        <td>${bookInUse.daysToreturn}</td>
                </tr>
        </c:forEach>
	</table>
	</div>
	</div>
	</div>
</body>
</html>