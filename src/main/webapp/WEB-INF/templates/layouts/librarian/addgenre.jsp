<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

        	<div class="row">
        	
					<div class="col-md-7">
				       
						<form:form method="POST" class="form-horizontal" role="form" style="margin-left: 70px ; margin-top:30px">
							
							 
							 <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">Genre description</label>
								    <div class="col-sm-10">
								    	 <input name="eng" class="form-control"/> 
								    </div>
							 </div>  
							
				           <div class="col-md-1 col-md-offset-10" style="margin-top:10px">
							<input type="submit" class="btn btn-default btn-sm" value="<spring:message code="message.libSave"/>" style="background-color: #00A1A1 ; color: #FFFFFF">
						</div>
				       </form:form>	
				   </div>     
		    </div>    	
