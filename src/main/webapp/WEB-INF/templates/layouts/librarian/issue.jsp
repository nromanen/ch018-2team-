<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

        	<div class="row">
				
					<div class="col-md-4">
				        <form id="issue_form" method="POST" action="${pageContext.request.contextPath}/librarian/orders/issue" class="form-horizontal" role="form" style="margin-left: 70px ; margin-top:30px">
				             
				             <input name="id" type="hidden" class="form-control" value="${order.id}" /> 
				             
				              <div class="form-group">
								    <label  class="col-sm-2 control-label">Name</label>
								    <div class="col-sm-10">
								    	 <h5> ${order.person.name}  ${order.person.surname}</h5> 
								    </div>
							 </div>  
							 
							 <div class="form-group">
								    <label  class="col-sm-2 control-label">Rating</label>
								    <div class="col-sm-10">
								    	 <h5> ${order.person.generalRating}</h5> 
								    </div>
							 </div> 
							 
							 <div class="form-group">
								    <label  class="col-sm-2 control-label">Book</label>
								    <div class="col-sm-10">
								    	 <h5> ${order.book.title} ${order.book.authors} </h5> 
								    </div>
							 </div> 
							 
							  <div class="form-group">
								    <label  class="col-sm-2 control-label">Order Date</label>
								    <div class="col-sm-10">
								    	 <h5> <c:out  value="${fn:substring(order.getOrderDate(), 0, 11)}"/> </h5> 
								    </div>
							 </div> 
							 <div class="form-group">
							 <div class="col-sm-10">
							 <p>
							 	<input type="hidden" id="term_value" value="${term}">
								<label for="term">term:</label>
				 				<input type="text" id="term" style="border: 0;font-weight: bold;">
							</p>

							<div id="term-range"></div>
							</div>
							 
								 <!--  
									    <label for="inputEmail3" class="col-sm-2 control-label">Term</label>
									    <div class="col-sm-10">
									    	<input name="term" class="form-control" value="${term}">
									    </div> -->
								 </div>  
								 
								 <div class="form-group">
									    <label for="inputEmail3" class="col-sm-2 control-label"></label>
									    <div class="col-sm-10">
									    	<h5 style="color: red;">${validation}</h5>
									    </div>
								 </div> 
				          <div class="col-md-1 col-md-offset-7" style="margin-top:10px">
							<input id="issue_submit" type="submit" class="btn btn-default btn-sm" value="Issue" style="background-color: #00A1A1 ; color: #FFFFFF">
						</div>
				        </form>
				   </div>     
</div>