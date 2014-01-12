<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

        	<div class="row">
				
					<div class="col-md-7">
				        <form:form method="POST" commandName="user" class="form-horizontal" role="form" style="margin-left: 70px ; margin-top:30px">
				            
					          <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">ID</label>
								    <div class="col-sm-10">
								    	 <form:input path="pid" class="form-control" placeholder="ID"/> 
								    </div>
							 </div>
				            
				             <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">First Name</label>
								    <div class="col-sm-10">
								    	 <form:input path="name" class="form-control" placeholder="First Name"/>
								    </div>
							 </div>
				           
				           	 <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">Last Name</label>
								    <div class="col-sm-10">
								    	  <form:input path="surname" class="form-control" placeholder="Last Name"/> 
								    </div>
							 </div>	
				         
				             <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">E-mail</label>
								    <div class="col-sm-10">
								    	  <form:input path="email" class="form-control" placeholder="E-mail"/>
								    </div>
							 </div>	
				           
				             <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">Role</label>
								    <div class="col-sm-10">
								    	  <form:input path="prole" class="form-control" placeholder="Role"/>
								    </div>
							 </div>
				            
				             <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label"> Cell phone</label>
								    <div class="col-sm-10">
								    	  <form:input path="cellphone" class="form-control" placeholder="Cell phone"/>
								    </div>
							 </div>
				           	 
				           	 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">Confirmed</label>
							    <div class="col-sm-10">
							    	 <select name="confirm" class="form-control">
				                		<option value="true">true</option>
				                		<option value="false">false</option>
				                	</select>
							    </div>
							 </div>
							 
							 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">SMS</label>
							    <div class="col-sm-10">
							    	 <select name="sms" class="form-control">
				                		<option value="true">true</option>
				                		<option value="false">false</option>
				                	</select>
							    </div>
							 </div>
				                	
				            <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">Returned in time</label>
								    <div class="col-sm-10">
								    	  <form:input path="timelyReturn" class="form-control" placeholder="Returned in time"/>
								    </div>
							</div>    	
				            
				            <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">Returned not in time</label>
								    <div class="col-sm-10">
								    	  <form:input path="untimekyReturn" class="form-control" placeholder="Returned not in time"/>
								    </div>
							</div>
				           
				            <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">Books on Hands</label>
								    <div class="col-sm-10">
								    	 <form:input path="multiBook" class="form-control" placeholder="Books on Hands"/>
								    </div>
							</div>
							
							<div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">Books Allowed</label>
								    <div class="col-sm-10">
								    	 <form:input path="booksAllowed" class="form-control" placeholder="Books Allowed"/>
								    </div>
							</div>
							
				            <div class="col-md-1 col-md-offset-10" style="margin-top:10px">
								<input type="submit" class="btn btn-default btn-sm" value="Save">
							</div>
				        </form:form>
				   </div>     
		    </div>    	
        