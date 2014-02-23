<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

        	<div class="row">
        	
					<div class="col-md-7">
				        <form id="term_form" method="POST" action="${pageContext.request.contextPath}/librarian/toreturn/edit"   class="form-horizontal" role="form" style="margin-left: 70px ; margin-top:30px">
				          
								    	 <input name="id" type="hidden" value="${id}" class="form-control"/> 
				           
				            <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">Name</label>
								    <div class="col-sm-10">
								    	 <h5> ${inuse.person.name} ${inuse.person.surname}</h5> 
								    </div>
							 </div>  
							 
							 <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">Rating</label>
								    <div class="col-sm-10">
								    	 <h5>  ${inuse.person.generalRating}</h5> 
								    </div>
							 </div> 
				           
				            <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">Return Date</label>
								    <div class="col-sm-10">
								    	 <h5> <c:out  value="${fn:substring(inuse.getReturnDate(), 0, 11)}"/> </h5> 
								    </div>
							 </div>  
							
							<div class="form-group">
							 	<div class="col-sm-10">
									 <c:choose>
									 	<c:when test="${unavailable}">
									 		<input type="text" disabled="disabled" style="color:red">Book Unavailable
									 	</c:when>
									 	<c:otherwise>
									 		<p>
										 	<input type="hidden" id="term_value" value="${term}">
											<label for="term">term:</label>
							 				<input type="text" id="term" style="border: 0;font-weight: bold;">
											</p>
		
											<div id="term-range"></div>
									 	</c:otherwise>
									 </c:choose> 
								</div>
							</div>  
							 
							 <div class="form-group">
									    <label for="inputEmail3" class="col-sm-2 control-label"></label>
									    <div class="col-sm-10">
									    	<h5 style="color: red;">${validation}</h5>
									    </div>
							 </div> 
				           <div class="col-md-1 col-md-offset-10" style="margin-top:10px">
							<input id="term_submit" type="submit" class="btn btn-default btn-sm" value="<spring:message code="message.libSave"/>" style="background-color: #00A1A1 ; color: #FFFFFF">
						</div>
				        </form>
				   </div>     
		    </div>    	
