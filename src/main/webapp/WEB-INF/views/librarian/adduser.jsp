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
    	
        	<div class="row">
        	
        		<div class="col-md-2">
        		
					<t class="ex3">
					<a href="<c:url value="/librarian/users"/>"><h4>Users</h4></a>
					<a href="<c:url value="/librarian/books"/>"><h4>Books</h4></a>
					<a href="<c:url value="/librarian/orders"/>"><h4>Orders</h4></a>
					<a href="<c:url value="/librarian/toreturn"/>"><h4>To Return</h4></a>
						
					<c:set var="highlight" value="users" scope="request"/>
					</t>
				</div>
					<div class="col-md-7">
				        <form:form method="POST" commandName="user" >
				            
				            <center> <h4> Add User</h4> </center>
				             
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
				            Role
				                <t class="role"> <input type="text" name="prole"/> </t>
				            </p>
				            
				            <p>
				            Cell phone
				                <t class="cellphone"> <input type="text" name="cellphone"/> </t>
				            </p>
				            
							<p>
				            Confirmed
				                <t class="confirmed"> <input type="text" name="confirm"/> </t>
				            </p>				            
				            
				            <p>
				            SMS
				                <t class="sms"> <input type="text" name="sms"/> </t>
				            </p>
				            
				            <p>
				            Returned in time
				                <t class="returnedintime"> <input type="text" name="timelyReturn"/> </t>
				            </p>
				            
				            <p>
				            Returned not in time
				                <t class=returnednotintime> <input type="text" name="untimekyReturn"/> </t>
				            </p>
				            
				            <p>
				            Books Allowed
				                <t class="booksallowed"> <input type="text" name="booksAllowed"/> </t>
				            </p>
				            
				            <p>
							Rating
				            <t class="rating"> <input type="text" name="generalRating"/> </t>
				            </p>
				          <input type="submit" value="Add">
				        </form:form>
				   </div>     
		    </div>    	
        </div>
    </body>
</html>