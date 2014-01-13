<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- TODO: sort directives above -->
        	<div class="row">
        		
			
					<div class="col-md-7">
						
				        <form:form method="POST" commandName="book" class="form-horizontal" role="form" style="margin-left: 70px ; margin-top:30px">
				             
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libTitle"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="title" placeholder="<spring:message code="message.libTitle"/>">
							    </div>
							</div> 
				          
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libAuthors"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="authors" placeholder="<spring:message code="message.libAuthors"/>">
							    </div>
							</div> 
				          
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libYear"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="year" placeholder="<spring:message code="message.libYear"/>">
							    </div>
							</div> 
				           
				           <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libPublisher"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="publisher" placeholder="<spring:message code="message.libPublisher"/>">
							    </div>
							</div> 
				           
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libPages"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="pages" placeholder="<spring:message code="message.libPages"/>">
							    </div>
							</div> 
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libDescription"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="description" placeholder="<spring:message code="message.libDescription"/>">
							    </div>
							</div>
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libImg"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="img" placeholder="<spring:message code="message.libImg"/>">
							    </div>
							</div>
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libShelf"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="shelf" placeholder="<spring:message code="message.libShelf"/>">
							    </div>
							</div>
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libTerm"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="term" placeholder="<spring:message code="message.libTerm"/>">
							    </div>
							</div>
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.libGeneralQuantity"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="generalQuantity" placeholder="<spring:message code="message.libGeneralQuantity"/>">
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
							<input type="submit" class="btn btn-default btn-sm" value="<spring:message code="message.libAddBook"/>">
						</div>
				        </form:form>
				        
				   </div>     
		    </div>    	
      