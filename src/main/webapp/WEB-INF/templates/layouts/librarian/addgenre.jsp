<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

        	<div class="row">
        		
					<div class="col-md-7">
				       
						<form id="genre_form" method="POST" action="${pageContext.request.contextPath}/librarian/genres/addgenre" class="form-horizontal" role="form" style="margin-left: 70px ; margin-top:30px">
							
							 
							 <div class="form-group">
								    
								    <div class="col-sm-10">
								    <label for="eng" class="control-label">Eng description</label>
								    	 <input name="eng" class="form-control"/> 
								    </div>
								    
							 </div>  
							 <div class="form-group">
							
								    <div class="col-sm-10">
								     <label for="ukr" class="control-label">Ua description</label>
								    	 <input name="ukr" class="form-control"/> 
								    </div>
							</div>
				           <div class="col-sm-2" style="margin-top:10px">
							<input id="genre_submit" type="submit" class="btn btn-default btn-sm" value="<spring:message code="message.libSave"/>" style="background-color: #00A1A1 ; color: #FFFFFF">
							
						</div>
						<div id="genre_error" class="alert alert-danger hide col-sm-6"></div>
				       </form>	
				   </div>     
		    </div>    	
