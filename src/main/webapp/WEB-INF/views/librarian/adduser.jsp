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
			
			t.id {margin: 0px 0px 0px 123px }
			t.firstName {margin: 0px 0px 0px 69px }
			t.lastName {margin: 0px 0px 0px 69px }
			t.email {margin: 0px 0px 0px 97px }
			t.password {margin: 0px 0px 0px 74px }
			t.role {margin: 0px 0px 0px 107px }
			t.cellphone {margin: 0px 0px 0px 70px }
			t.confirmed {margin: 0px 0px 0px 72px }
			t.sms {margin: 0px 0px 0px 105px }
			t.returnedintime {margin: 0px 0px 0px 33px }
			t.returnednotintime {margin: 0px 0px 0px 10px }
			t.booksallowed {margin: 0px 0px 0px 44px }
			t.rating {margin: 0px 0px 0px 94px }
		</style>
		
		<title>Librarian Add User</title>
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
				        <form:form method="POST" commandName="user" >
				            
				            <center> <h4> Add User</h4> 
				             
				           <p> First Name    	
				                <t class="firstName"> <input type="text" name="name"/> </t>
				           </p>
				            
				           <p>     
				            Last Name
				                <t class="lastName"> <input type="text" name="surname"/> </t>
				           </p> 
				           
				           <p>
				            E-mail
				                <t class="email"> <input type="text" name="email"/> </t>
				            </p>
				            
				            
				            <p>
				            Password
				               	<t class="password"> <input type="text" name="password"/> </t>
				            </p>
				               	
				            <p>
				            Cell phone
				                <t class="cellphone"> <input type="text" name="cellphone"/> </t>
				            </p>
				            
				            <p>
				            Books Allowed
				                <t class="booksallowed"> <input type="text" name="booksAllowed"/> </t>
				            </p>
				            
				          <input type="submit" value="Add">
				          </center>
				        </form:form>
				   </div>     
		    </div>    	
        </div>
    </body>
</html>