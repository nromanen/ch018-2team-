<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

        	<div class="row">
				
					<div class="col-md-7">
				        <form:form method="POST" commandName="book" class="form-horizontal" role="form" style="margin-left: 70px ; margin-top:30px">
				            
				         <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">ID</label>
							    <div class="col-sm-10">
							    	<form:input class="form-control" path="bId" placeholder="ID"/>
							    </div>
						 </div>
						  
						 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libTitle"/></label>
							    <div class="col-sm-10">
							    	 <form:input class="form-control" path="title"/>
							    </div>
						 </div> 
						 
						 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libAuthors"/></label>
							    <div class="col-sm-10">
							    	 <form:input class="form-control" path="authors"/>
							    </div>
						 </div> 
						 
						 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libYear"/></label>
							    <div class="col-sm-10">
							    	   <form:input class="form-control" path="year"/>
							    </div>
						 </div> 
						 
						 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libPublisher"/></label>
							    <div class="col-sm-10">
							    	  <form:input class="form-control" path="publisher"/>
							    </div>
						 </div> 
						 
						 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libPages"/></label>
							    <div class="col-sm-10">
							       <form:input class="form-control" path="pages"/>
							    </div>
						 </div> 
						 
						 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libDescription"/></label>
							    <div class="col-sm-10">
							      <form:input class="form-control" path="description"/>
							    </div>
						 </div> 			         
				         
				         <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libImg"/></label>
							    <div class="col-sm-10">
							       <form:input class="form-control" path="img"/>
							    </div>
						 </div> 
						 
						 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libShelf"/></label>
							    <div class="col-sm-10">
							       <form:input class="form-control" path="shelf"/> 
							    </div>
						 </div>       
				                 
				         <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libTerm"/></label>
							    <div class="col-sm-10">
							        <form:input class="form-control" path="term"/>
							    </div>
						 </div>
						 
						 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libCurrentQuantity"/></label>
							    <div class="col-sm-10">
							        <form:input class="form-control" path="currentQuantity"/>
							    </div>
						 </div>        
				             
				         <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libGeneralQuantity"/></label>
							    <div class="col-sm-10">
							        <form:input class="form-control" path="generalQuantity"/>
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
		
							<div class="col-md-1 col-md-offset-10" style="margin-top:10px">
								<input type="submit" class="btn btn-default btn-sm" value="<spring:message code="message.libSave"/>">
							</div>
				        </form:form>
				   </div>     
		    </div>    	
      