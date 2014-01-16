<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

        	<div class="row">
				
					<div class="col-md-4">
				        <form:form method="POST" commandName="order" class="form-horizontal" role="form" style="margin-left: 70px ; margin-top:30px">
				             
				             <form:input path="id" type="hidden" class="form-control"/> 
				             
				              <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">Name</label>
								    <div class="col-sm-10">
								    	 <h5> ${order.person.name}  ${order.person.surname}</h5> 
								    </div>
							 </div>  
							 
							 <div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">Return Date</label>
								    <div class="col-sm-10">
								    	 <h5> ${order.book.title} ${order.book.authors} </h5> 
								    </div>
							 </div> 
								 <div class="form-group">
									    <label for="inputEmail3" class="col-sm-2 control-label">Term</label>
									    <div class="col-sm-10">
									    	<input name="term" class="form-control" value="${term}">
									    </div>
								 </div>  
								 
								 <div class="form-group">
									    <label for="inputEmail3" class="col-sm-2 control-label"></label>
									    <div class="col-sm-10">
									    	<h5 style="color: red;">${validation}</h5>
									    </div>
								 </div> 
				          <div class="col-md-1 col-md-offset-7" style="margin-top:10px">
							<input type="submit" class="btn btn-default btn-sm" value="Issue" style="background-color: #00A1A1 ; color: #FFFFFF">
						</div>
				        </form:form>
				   </div>     
</div>