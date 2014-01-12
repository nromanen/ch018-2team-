<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
							    	 <form:input class="form-control" path="title" placeholder="<spring:message code="message.libTitle"/>"/>
							    </div>
						 </div> 
						 
						 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libAuthors"/></label>
							    <div class="col-sm-10">
							    	 <form:input class="form-control" path="authors" placeholder="<spring:message code="message.libAuthors"/>"/>
							    </div>
						 </div> 
						 
						 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libYear"/></label>
							    <div class="col-sm-10">
							    	   <form:input class="form-control" path="year" placeholder="<spring:message code="message.libYear"/>"/>
							    </div>
						 </div> 
						 
						 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libPublisher"/></label>
							    <div class="col-sm-10">
							    	  <form:input class="form-control" path="publisher" placeholder="<spring:message code="message.libPublisher"/>"/>
							    </div>
						 </div> 
						 
						 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libPages"/></label>
							    <div class="col-sm-10">
							       <form:input class="form-control" path="pages" placeholder="<spring:message code="message.libPages"/>"/>
							    </div>
						 </div> 
						 
						 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libDescription"/></label>
							    <div class="col-sm-10">
							      <form:input class="form-control" path="description" placeholder="<spring:message code="message.libDescription"/>"/>
							    </div>
						 </div> 			         
				         
				         <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libImg"/></label>
							    <div class="col-sm-10">
							       <form:input class="form-control" path="img" placeholder="<spring:message code="message.libImg"/>"/>
							    </div>
						 </div> 
						 
						 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libShelf"/></label>
							    <div class="col-sm-10">
							       <form:input class="form-control" path="shelf" placeholder="<spring:message code="message.libShelf"/>"/> 
							    </div>
						 </div>       
				                 
				         <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libTerm"/></label>
							    <div class="col-sm-10">
							        <form:input class="form-control" path="term" placeholder="<spring:message code="message.libTerm"/>"/>
							    </div>
						 </div>
						 
						 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libCurrentQuantity"/></label>
							    <div class="col-sm-10">
							        <form:input class="form-control" path="currentQuantity" placeholder="<spring:message code="message.libCurrentQuantity"/>"/>
							    </div>
						 </div>        
				             
				         <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libGeneralQuantity"/></label>
							    <div class="col-sm-10">
							        <form:input class="form-control" path="generalQuantity" placeholder="<spring:message code="message.libGeneralQuantity"/>"/>
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
      