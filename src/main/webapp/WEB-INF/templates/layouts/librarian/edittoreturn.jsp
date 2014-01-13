<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

        	<div class="row">
        	
					<div class="col-md-7">
				        <form:form method="POST" commandName="inuse"  class="form-horizontal" role="form" style="margin-left: 70px ; margin-top:30px">
				          
				            <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">ID</label>
								    <div class="col-sm-10">
								    	 <form:input path="id" class="form-control"/> 
								    </div>
							 </div>  
				           
				            <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">Name</label>
								    <div class="col-sm-10">
								    	 <form:input path="person.name" class="form-control"/> 
								    </div>
							 </div>  
				           
				            <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">Return Date</label>
								    <div class="col-sm-10">
								    	 <form:input path="returnDate" class="form-control"/> 
								    </div>
							 </div>  
							
							<div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">Increase by days</label>
								    <div class="col-sm-10">
								    	 <input name="days" class="form-control"/> 
								    </div>
							 </div>  
				           
				          <input type="submit" value="Save">
				        </form:form>
				   </div>     
		    </div>    	
