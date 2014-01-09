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
			t.quantity {margin: 0px 0px 0px 30px }
		</style>
		
		<title>Librarian Advanced Search</title>
</head>
    <body>
    
    	<div class="container">
    	  
			<div class="row" >
				
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

        	<div class="row">
        		
			
					<div class="col-md-12">
						
				        <form:form method="POST" commandName="book" >
				            
				            <center> <h4> Add Book</h4> 
				             
				           <p> Title    	
				                <input type="text" name="title">
				           </p>
				           
				           <p>     
				            Author
				               <input type="text" name="authors">
				           </p> 
				           
				           <p>
				            Year
				                <input type="text" name="year">
				            </p>
				            
				            <p>
				            Publisher
				                <input type="text" name="publisher"> 
				            </p>
				            
				            <p>
				            Pages
				                <input type="text" name="pages"> 
				            </p>
				            
							<p>
				            Description
				                <input type="text" name="description">
				            </p>				            
				            
				            <p>
				            Image URL
				                <input type="text" name="img">
				            </p>
				            
				            <p>
				            Shelf
				               <input type="text" name="shelf">
				            </p>
				            
				            <p>
				            Term
				                <form:input path="term"/>
				            </p>
				            
				            <p>
				            Quantity
				              <input type="text" name="generalQuantity"> 
				            </p>
				            
				            <p>
				            Genre
								
				                <select name="genreId" >
				                    <c:forEach var="genre" items="${genre}">
				                        <option value="${genre.getId()}">${genre.getDescription()}</option>
				                    </c:forEach>
				                </select>
				                <input type="submit" value="Add">
				            </p>
				            </center>
				        </form:form>
				        
				   </div>     
		    </div>    	
        </div>
    </body>
</html>