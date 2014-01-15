<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
				
					<div class="row">
					
						<div class="col-md-7">
						
				        	<form:form method="POST" commandName="user" class="form-horizontal" role="form" style="margin-left: 70px ; margin-top:30px">
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libFirstName"/></label>
							    <div class="col-sm-10">
							    	<form:input type="text" class="form-control" path="name"/>
							    	<form:errors path="name" cssClass="error" style="color: red;"/>
							    </div>
							 </div> 
				           <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libLastName"/></label>
							    <div class="col-sm-10">
							    	<form:input type="text" class="form-control" path="surname" />
							    	<form:errors path="surname" cssClass="error" style="color: red;"/>
							    </div>
							 </div>
							 
							  <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libEMail"/></label>
							    <div class="col-sm-10">
							    	<form:input type="text" class="form-control" path="email"/>
							    	<form:errors path="email" cssClass="error" style="color: red;"/>
							    </div>
							 </div>
				                
				           	  <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libPassword"/></label>
							    <div class="col-sm-10">
							    	<form:input type="text" class="form-control" path="password"/>
							    	<form:errors path="password" cssClass="error" style="color: red;"/>
							    </div>
							 </div>
							 
							 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libCellPhone"/></label>
							    <div class="col-sm-10">
							    	<form:input type="text" class="form-control" path="cellphone"/>
							    	<form:errors path="cellphone" cssClass="error" style="color: red;"/>
							    </div>
							 </div>
				           
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libBooksAllowed"/></label>
							    <div class="col-sm-10">
							    	<form:input type="text" class="form-control" path="booksAllowed"/>
							    	<form:errors path="booksAllowed" cssClass="error" style="color: red;"/>
							    </div>
							 </div>
							 
				           <div class="col-md-1 col-md-offset-10" style="margin-top:10px">
							<input type="submit" class="btn btn-default btn-sm" value="<spring:message code="message.libAddUser"/>" style="background-color: #00A1A1 ; color: #FFFFFF">
						</div>
				        </form:form>
				   </div>     
		    </div>    	
     