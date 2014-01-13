<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


        	<div class="row">
				
					<div class="col-md-7">
				        <form:form method="POST" commandName="user" class="form-horizontal" role="form" style="margin-left: 70px ; margin-top:100px">
				            
					          <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">ID</label>
								    <div class="col-sm-10">
								    	 <form:input path="pid" class="form-control"/> 
								    </div>
							 </div>
				            
				             <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libFirstName"/></label>
								    <div class="col-sm-10">
								    	 <form:input path="name" class="form-control"/>
								    </div>
							 </div>
				           
				           	 <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libLastName"/></label>
								    <div class="col-sm-10">
								    	  <form:input path="surname" class="form-control"/> 
								    </div>
							 </div>	
				         
				             <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libEMail"/></label>
								    <div class="col-sm-10">
								    	  <form:input path="email" class="form-control"/>
								    </div>
							 </div>	
				           
				             <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libRole"/></label>
								    <div class="col-sm-10">
								    	  <form:input path="prole" class="form-control"/>
								    </div>
							 </div>
				            
				             <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libCellPhone"/></label>
								    <div class="col-sm-10">
								    	  <form:input path="cellphone" class="form-control"/>
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
								    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libReturnedOnTime"/></label>
								    <div class="col-sm-10">
								    	  <form:input path="timelyReturn" class="form-control"/>
								    </div>
							</div>    	
				            
				            <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libReturnedLate"/></label>
								    <div class="col-sm-10">
								    	  <form:input path="untimekyReturn" class="form-control"/>
								    </div>
							</div>
				           
				            <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libBooksOnHands"/></label>
								    <div class="col-sm-10">
								    	 <form:input path="multiBook" class="form-control"/>
								    </div>
							</div>
							
							<div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libBooksAllowed"/></label>
								    <div class="col-sm-10">
								    	 <form:input path="booksAllowed" class="form-control"/>
								    </div>
							</div>
							
				            <div class="col-md-1 col-md-offset-10" style="margin-top:10px">
								<input type="submit" class="btn btn-default btn-sm" value="<spring:message code="message.libSave"/>">
							</div>
				        </form:form>
				   </div>     
		    </div>    	
        