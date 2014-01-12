<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

				
					<div class="row">
					<div class="col-md-7">
				        <form:form method="POST" commandName="user" class="form-horizontal" role="form" style="margin-left: 70px ; margin-top:30px" >
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">First Name</label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="name" placeholder="First Name">
							    </div>
							 </div> 
				           <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">Last Name</label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="surname" placeholder="Last Name">
							    </div>
							 </div>
							 
							  <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">E-mail</label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="email" placeholder="E-mail">
							    </div>
							 </div>
				                
				           	  <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">Password</label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="password" placeholder="Password">
							    </div>
							 </div>
							 
							 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">Cell phone</label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="cellphone" placeholder="Cell phone">
							    </div>
							 </div>
				           
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">Books Allowed</label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="booksAllowed" placeholder="Books Allowed">
							    </div>
							 </div>
							 
				            <div class="col-md-1 col-md-offset-10" style="margin-top:10px">
								<input type="submit" class="btn btn-default btn-sm" value="Add">
							</div>
				        </form:form>
				   </div>     
		    </div>    	
     