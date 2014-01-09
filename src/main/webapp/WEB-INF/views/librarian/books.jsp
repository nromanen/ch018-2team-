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
			ex1 {margin:300px 20px}
			t.ex3 {margin:10px 70px}
			t.ex2 {margin: 50px 30px 30px 500px}
		</style>
<title>Librarian Books</title>

</head>
<body bgcolor="#E5E5E5">

<div class="container">

	<div class="row" >
	
		<div class="col-md-1 col-md-offset-7">
			<t class="ex3">
			<a href="<c:url value="/librarian/users"/>"><h5>Users</h5></a>
			
				
			<c:set var="highlight" value="books" scope="request"/>
			</t>
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
	<div class="row">
		<div class="col-md-12" style="margin-top:15px">
				<center>
					<form name="search" action="<c:url value="/librarian/books/simplesearch" />" method="POST" >
						<input type="text"  name="request"/> <input type="submit" value="Search"/> 
						<t class="ex1"><a href="<c:url value="/librarian/books/advancedsearch"/>">Advanced</a></t>
					</form>
				</center>
			</div>
	<div class="row">
			<div class="col-md-12" style="margin-top:30px">
			<center>
				<div class="col-md-12">
				<table border = "2">
				
     			   <thead>
         		       <tr>
           	                <td> <b> ID </b> </td>
           	   	            <td> <b> Title </b> </td>
            	            <td> <b> Authors </b> </td> 
                            <td> <b> Year </b> </td>
                            <td> <b> Publisher </b> </td>
                            <td> <b> Pages </b> </td>
                            <td> <b> Genre </b> </td>
                            <td> <b> Description </b> </td>    
                            <td> <b> Shelf </b> </td>
                            <td> <b> Term </b> </td> 
                            <td> <b> Current Quantity</b> </td>
                            <td> <b> General Quantity</b> </td>
                            <td> <b> Options </b> </td>
               	         
               		   </tr>
       			 </thead>
				        <c:forEach items="${books}" var="book">
				                <tr>
				                        <td>${book.bId}</td>
				                        <td>${book.title}</td>
				                        <td>${book.authors}</td>
				                        <td>${book.year}</td>
				                        <td>${book.publisher}</td>
				                        <td>${book.pages}</td>
				                        <td>${book.genre}</td>
				                        <td>${book.description}</td>
				                        <td>${book.shelf}</td>
				                        <td>${book.term}</td>
				                        <td>${book.currentQuantity}</td>
				                        <td>${book.generalQuantity}</td>
				                    <td><a href="<c:url value="/librarian/books/editbook?id=${book.bId}"/>">Edit</a>
				                    <a href="<c:url value="/librarian/books/deletebook?id=${book.bId}"/>">Delete</a></td>
				                </tr>
				        </c:forEach>
			   </table>
			   </div>
			   
					
				
				</div>
			</div>
<div class="row">

	<div class="col-md-1 col-md-offset-9" style="margin-top:10px">
		<a href="<c:url value="/librarian/books/addbook"/>">Add Book</a>
	</div>

</div>			
			
		</div>
		</div>
	
</body>
</html>