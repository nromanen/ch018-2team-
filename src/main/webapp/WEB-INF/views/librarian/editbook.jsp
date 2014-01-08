<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
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
			
			t.edit1 {margin: 0px 0px 0px 55px }
			t.edit2 {margin: 0px 0px 0px 41px }
			t.edit3 {margin: 0px 0px 0px 53px }
			t.edit4 {margin: 0px 0px 0px 24px }
			t.edit5 {margin: 0px 0px 0px 43px }
			t.edit6 {margin: 0px 0px 0px 43px }
			t.description {margin: 0px 0px 0px 12px }
			t.img {margin: 0px 0px 0px 14px }
			t.shelf {margin: 0px 0px 0px 51px }
			t.term {margin: 0px 0px 0px 50px }
			t.currquantity {margin: 0px 0px 0px 30px }
			t.genquantity {margin: 0px 0px 0px 30px }
			t.ID {margin: 0px 0px 0px 69px }
		</style>
		
		<title>Librarian Edit Book</title>
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
				        <form:form method="POST" commandName="book" >
				            
				            <center> <h4> Edit</h4> </center>
				            
				            <p> ID    	
				               <t class="ID"> <form:input path="bId"/> </t>
				           </p>
				             
				           <p> Title    	
				                <t class="edit1"> <form:input path="title"/> </t>
				           </p>
				            
				           <p>     
				            Author
				                <t class="edit2"> <form:input path="authors"/> </t>
				           </p> 
				           
				           <p>
				            Year
				                <t class="edit3"> <form:input path="year"/> </t>
				            </p>
				            
				            <p>
				            Publisher
				                <t class="edit4"> <form:input path="publisher"/> </t>
				            </p>
				            
				            <p>
				            Pages
				                <t class="edit5"> <form:input path="pages"/> </t>
				            </p>
				            
							<p>
				            Description
				                <t class="description"> <form:input path="description"/> </t>
				            </p>				            
				            
				            <p>
				            Image URL
				                <t class="img"> <form:input path="img"/> </t>
				            </p>
				            
				            <p>
				            Shelf
				                <t class="shelf"> <form:input path="shelf"/> </t>
				            </p>
				            
				            <p>
				            Term
				                <t class="term"> <form:input path="term"/> </t>
				            </p>
				            
				             <p>
				            Current Quantity
				                <t class="currquantity"> <form:input path="currentQuantity"/> </t>
				            </p>
				            
				            <p>
				            General Quantity
				                <t class="genquantity"> <form:input path="generalQuantity"/> </t>
				            </p>
				            
				            <p>
				            Genre
								<t class="edit6">
				                <select name="genreId" >
				                    <c:forEach var="genre" items="${genre}">
				                        <option value="${genre.getId()}">${genre.getDescription()}</option>
				                    </c:forEach>
				                </select>
				                </t>
				                <input type="submit" value="Save">
				            </p>
				        </form:form>
				   </div>     
		    </div>    	
        </div>
    </body>
</html>