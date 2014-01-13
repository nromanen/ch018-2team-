<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

        	<div class="row">
        	
        	
				</div>
					<div class="col-md-7">
				        <form:form method="POST" commandName="bookInUse" >
				            
				            <center> <h4> Edit</h4> </center>
				            
				            <p> ID    	
				               <t class="id"> <form:input path="id"/> </t>
				           </p>
				           
				           <p>
				           Name
				            <t class="id"> <form:input path="person.name"/> </t>
				           </p>
				           
				            <p>
				           	Return Date
				            <t class="id"> <form:input path="returnDate"/> </t>
				           </p>

				            <p>
				           	Shelf
				            <t class="id"> <form:input path="book.shelf"/> </t>
				           </p>
				           
				          <input type="submit" value="Get Back">
				        </form:form>
				   </div>     
