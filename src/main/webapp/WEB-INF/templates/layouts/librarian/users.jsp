<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

	<div class=row>
			
			<div class="col-md-2 col-md-offset-5" style="margin-top:30px">
					<form name="search" action="<c:url value="/librarian/users/simplesearch" />" method="POST" >
						<input type="text" class="form-control" name="request"/>  <!-- <input type="submit" value="Search"/>  --> 
					</form>
			</div>
			
			<div class="col-md-2"  style="margin-top:35px" >
			<a href="<c:url value="/librarian/users/advencedsearch"/>"  class="btn btn-default btn-xs" role="button" style="margin-left: -13px"><spring:message code="message.libAdvanced"/></a>
			</div>
	</div>
	<div class="row">		
			<div class="col-md-12">
				
					<div class="col-md-12" style="margin-top:30px">
					
						<table class="table table-hover table-striped table-bordered table-condensed">
		     			   <thead>
		         		       <tr>
		           	                <td>  <h5> <strong> ID </strong> </h5>  </td>
		           	   	            <td>  <h5> <strong> <spring:message code="message.libFirstName"/></strong> </h5> </td>
		            	            <td>  <h5> <strong> <spring:message code="message.libLastName"/> </strong> </h5>  </td> 
		                            <td>  <h5> <strong> <spring:message code="message.libEMail"/> </strong> </h5>  </td>
		                            <td>  <h5> <strong> <spring:message code="message.libRole"/> </strong> </h5> </td>
		                            <td>  <h5> <strong> <spring:message code="message.libCellPhone"/> </strong> </h5>  </td>
		                            <td>  <h5> <strong> <spring:message code="message.libConfirmed"/> </strong> </h5>  </td>
		                            <td> <h5> <strong> <spring:message code="message.libSMS"/> </strong> </h5>  </td>    
		                            <td>  <h5> <strong> <spring:message code="message.libReturnedOnTime"/> </strong> </h5>  </td>
		                            <td>  <h5> <strong> <spring:message code="message.libReturnedLate"/> </strong> </h5>  </td> 
		                            <td>  <h5> <strong> <spring:message code="message.libBooksOnHands"/> </strong> </h5>  </td>
		                            <td> <h5> <strong> <spring:message code="message.libRating"/> </strong> </h5>  </td>
		                            <td>  <h5> <strong> <spring:message code="message.libOptions"/> </strong> </h5>  </td>
		               	         
		               		   </tr>
		       			 </thead>
				        <c:forEach items="${users}" var="user">
				                <tr>
				                
				                        <td>${user.pid}</td>
				                        <td>${user.name}</td>
				                        <td>${user.surname}</td>
				                        <td>${user.email}</td>
				                        <td>${user.prole}</td>
				                        <td>${user.cellphone}</td>
				                        <td>${user.confirm}</td>
				                        <td>${user.sms}</td>
				                        <td>${user.timelyReturn}</td>
				                        <td>${user.untimekyReturn}</td>
				                        <td>${user.multiBook} / ${user.booksAllowed}</td>
				                        <td>${user.generalRating}/100</td>
				                        
				                    <td><a href="<c:url value="/librarian/users/edituser?id=${user.pid}"/>"  style="color: #757575"><spring:message code="message.libEdit"/></a>
				                        <a href="<c:url value="/librarian/users/deleteuser?id=${user.pid}"/>"  style="color: #757575"><spring:message code="message.libDelete"/></a></td>
				                </tr>
				        </c:forEach>
			   			</table>
					</div>
				
					
				
			</div>
		<div class="row">

	<div class="col-md-1 col-md-offset-9" style="margin-top:10px">
		<a href="<c:url value="/librarian/users/adduser"/>" class="btn btn-default btn-sm" role="button"><spring:message code="message.libAddUser"/></a>
	</div>

</div>		
	</div>