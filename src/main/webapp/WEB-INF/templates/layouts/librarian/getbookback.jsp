<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

        	<div class="row">
				
					<div class="col-md-5">
				        <form:form method="POST" commandName="bookInUse" class="form-horizontal" role="form" style="margin-left: 70px ; margin-top:30px">
				             
				             <form:input path="id" type="hidden" class="form-control"/> 
				             
				              <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">Name</label>
								    <div class="col-sm-10">
								    	 <h5> ${bookInUse.person.name} ${bookInUse.person.surname}</h5> 
								    </div>
							 </div>  
							 
							 <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">Return Date</label>
								    <div class="col-sm-10">
								    	 <h5> <c:out  value="${fn:substring(bookInUse.getReturnDate(), 0, 11)}"/> </h5> 
								    </div>
							 </div> 
							  
							 <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">Shelf</label>
								    <div class="col-sm-10">
								    	  <h5> ${bookInUse.book.shelf} </h5> 
								    </div>
							 </div>  
							 
				          <div class="col-md-1 col-md-offset-5" style="margin-top:10px">
							<input type="submit" class="btn btn-default btn-sm" value="Get Back" style="background-color: #00A1A1 ; color: #FFFFFF">
						</div>
				        </form:form>
				        
				   </div>     
			</div>