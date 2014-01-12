<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

				
					<div class="row">
					<div class="col-md-7">
				        <form:form method="POST" commandName="user" class="form-horizontal" role="form" style="margin-left: 70px ; margin-top:30px" >
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libFirstName"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="name" placeholder="<spring:message code="message.libFirstName"/>">
							    </div>
							 </div> 
				           <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libLastName"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="surname" placeholder="<spring:message code="message.libLastName"/>">
							    </div>
							 </div>
							 
							  <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libEMail"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="email" placeholder="<spring:message code="message.libEMail"/>">
							    </div>
							 </div>
				                
				           	  <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libPassword"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="password" placeholder="<spring:message code="message.libPassword"/>">
							    </div>
							 </div>
							 
							 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libCellPhone"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="cellphone" placeholder="<spring:message code="message.libCellPhone"/>">
							    </div>
							 </div>
				           
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libBooksAllowed"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="booksAllowed" placeholder="<spring:message code="message.libBooksAllowed"/>">
							    </div>
							 </div>
							 
				            <div class="col-md-1 col-md-offset-10" style="margin-top:10px">
								<input type="submit" class="btn btn-default btn-sm" value="<spring:message code="message.libAddUser"/>">
							</div>
				        </form:form>
				   </div>     
		    </div>    	
     