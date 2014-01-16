<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

        	<div class="row">
        		
					<div class="col-md-7">
						
				        <form:form id="addbook" method="POST" commandName="book" class="form-horizontal" role="form" style="margin-left: 70px ; margin-top:30px">
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libTitle"/></label>
							    <div class="col-sm-10">
							    	<form:input type="text" class="form-control" path="title" id="titleval" />
							        <form:errors path="title" cssClass="error" style="color: red;"/>
							    </div>
							</div> 
				          
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libAuthors"/></label>
							    <div class="col-sm-10">
							    	<form:input type="text" class="form-control" path="authors"/>
							    	<form:errors path="authors" cssClass="error" style="color: red;"/>
							    </div>
							</div> 
				          
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libYear"/></label>
							    <div class="col-sm-10">
							    	<form:input type="text" class="form-control" path="year"/>
							    	<form:errors path="year" cssClass="error" style="color: red;"/>
							    </div>
							</div> 
				           
				           <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libPublisher"/></label>
							    <div class="col-sm-10">
							    	<form:input type="text" class="form-control" path="publisher"/>
							   		<form:errors path="publisher" cssClass="error" style="color: red;"/>
							    </div>
							</div> 
				           
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libPages"/></label>
							    <div class="col-sm-10">
							    	<form:input type="text" class="form-control" path="pages"/>
							    	<form:errors path="pages" cssClass="error" style="color: red;"/>
							    </div>
							</div> 
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libDescription"/></label>
							    <div class="col-sm-10">
							    	<form:input type="text" class="form-control" path="description"/>
							    	<form:errors path="description" cssClass="error" style="color: red;"/>
							    </div>
							</div>
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libImg"/></label>
							    <div class="col-sm-10">
							    	<form:input type="text" class="form-control" path="img"/>
							    	<form:errors path="img" cssClass="error" style="color: red;"/>
							    </div>
							</div>
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libShelf"/></label>
							    <div class="col-sm-10">
							    	<form:input type="text" class="form-control" path="shelf"/>
							    	<form:errors path="shelf" cssClass="error" style="color: red;"/>
							    </div>
							</div>
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libTerm"/></label>
							    <div class="col-sm-10">
							    	<form:input type="text" class="form-control" path="term" />
							    	<form:errors path="term" cssClass="error" style="color: red;"/>
							    </div>
							</div>
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libGeneralQuantity"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="generalQuantity" />
							    	<form:errors path="generalQuantity" cssClass="error" style="color: red;"/>
							    </div>
							</div>
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libGenre"/></label>
							    <div class="col-sm-10">
							    	   <select name="genreId" class="form-control">
					                    <c:forEach var="genre" items="${genre}">
					                        <option value="${genre.getId()}">${genre.getDescription()}</option>
					                    </c:forEach>
				                </select>
							    </div>
							 </div>
				              
		
						<div class="col-md-1 col-md-offset-10" style="margin-top:10px" >
							<input type="submit" class="btn btn-default btn-sm" value="Add" style="background-color: #00A1A1 ; color: #FFFFFF">
						</div>
				        </form:form>
				        
				   </div>     
		    </div>    	
      