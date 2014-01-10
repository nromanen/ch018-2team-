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
			html{
					background-color: ;
				}
			body{
					background-color: #FFFFFF;
				}
				
		</style>
<title>Librarian Books</title>

</head>
<body>

<div class="container">

	<div class="row" style="background-color: #A1A1A1" >
	
		<div class="col-md-1 col-md-offset-7">
			<t class="ex3">
			<a href="<c:url value="/librarian/users"/>" style="color: #FFFFFF"><h5>Users</h5></a>
			
				
			<c:set var="highlight" value="books" scope="request"/>
			</t>
		</div>
		<div class="col-md-1">
		<a href="<c:url value="/librarian/books"/>" style="color: #FFFFFF"><h5>Books</h5></a>
			
		</div>
		<div class="col-md-1">
		<a href="<c:url value="/librarian/orders"/>" style="color: #FFFFFF"><h5>Orders</h5></a>
		</div>
		<div class="col-md-1">
			<a href="<c:url value="/librarian/toreturn"/>" style="color: #FFFFFF"><h5>To Return</h5></a>
		</div>
		
	</div>
	<div class="row">
		<div class="col-md-2 col-md-offset-5" style="margin-top:30px">
				
					<form name="search" action="<c:url value="/librarian/books/simplesearch" />" method="POST" >
						<input type="text" class="form-control" id="" name="request"/>  <!--  <input type="submit" value="Search" /> -->
						
					</form>
				
			</div>
			<div class="col-md-2"  style="margin-top:35px" >
				<t class="ex1"><a href="<c:url value="/librarian/books/advancedsearch"/>"  class="btn btn-default btn-xs" role="button" style="margin-left: 7px">Advanced</a></t>
			</div>
	<div class="row">
			<div class="col-md-12" style="margin-top:30px">
			<center>
				<div class="col-md-12">
				<table class="table table-hover table-striped table-bordered table-condensed">
				
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
				                    <td><a href="<c:url value="/librarian/books/editbook?id=${book.bId}"/>" style="color: #757575">Edit</a>
				                    <a href="<c:url value="/librarian/books/deletebook?id=${book.bId}"/>" style="color: #757575">Delete</a></td>
				                </tr>
				        </c:forEach>
			   </table>
			   </div>
			   
					
				
				</div>
			</div>
<div class="row">

	<div class="col-md-1 col-md-offset-9" style="margin-top:10px">
		<a href="<c:url value="/librarian/books/addbook"/>" class="btn btn-default btn-sm" role="button">Add Book</a>
	</div>
   
</div>			
			
		</div>
		</div>
	
</body>
</html>