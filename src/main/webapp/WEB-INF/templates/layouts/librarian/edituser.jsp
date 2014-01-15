<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


        	<div class="row">
				
					<div class="col-md-7">
				        <form:form method="POST" commandName="user" class="form-horizontal" role="form" style="margin-left: 70px ; margin-top:100px">
				            
					           <form:input path="pid" type="hidden"/> 
				            
				             <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libFirstName"/></label>
								    <div class="col-sm-10">
								    	 <form:input path="name" class="form-control"/>
								    	 <form:errors path="name" cssClass="error" style="color: red;"/>
								    </div>
							 </div>
				           
				           	 <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libLastName"/></label>
								    <div class="col-sm-10">
								    	  <form:input path="surname" class="form-control"/> 
								    	  <form:errors path="surname" cssClass="error" style="color: red;"/>
								    </div>
							 </div>	
				         
				             <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libEMail"/></label>
								    <div class="col-sm-10">
								    	  <form:input path="email" class="form-control"/>
								    	  <form:errors path="email" cssClass="error" style="color: red;"/>
								    </div>
							 </div>	
				           
				             <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libCellPhone"/></label>
								    <div class="col-sm-10">
								    	  <form:input path="cellphone" class="form-control"/>
								    	  <form:errors path="cellphone" cssClass="error" style="color: red;"/>
								    </div>
							 </div>
				           	 
				           	 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libConfirmed"/></label>
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
								    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libBooksAllowed"/></label>
								    <div class="col-sm-10">
								    	 <form:input path="booksAllowed" class="form-control"/>
								    	 <form:errors path="booksAllowed" cssClass="error" style="color: red;"/>
								    </div>
							</div>
							
				            <div class="col-md-1 col-md-offset-10" style="margin-top:10px">
								<input type="submit" class="btn btn-default btn-sm" value="<spring:message code="message.libSave"/>" style="background-color: #00A1A1 ; color: #FFFFFF">
							</div>
				        </form:form>
				   </div>     
		    </div>    	
        