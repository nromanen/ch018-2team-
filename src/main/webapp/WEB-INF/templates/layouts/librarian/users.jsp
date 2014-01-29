<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

	<div class=row>
			
			<div class="col-md-3 col-md-offset-5" style="margin-top:30px">
					<form  class="form-inline" role="form" name="search" action="${pageContext.request.contextPath}/librarian/users/simplesearch" method="POST" >
						
						 <div class="form-group">
						
						<input type="text" class="form-control input-sm" name="request" placeholder="Name or Surname"> 
						</div>
						<button type="submit" class="btn btn-default">
						  	<span class="glyphicon glyphicon-search"></span>
						</button>
						
						<a href="${pageContext.request.contextPath}/librarian/users/advencedsearch" class="btn btn-default">
							<span class="glyphicon glyphicon-cog"></span>
						</a>
					</form>  
			</div>
			 <div class="form-group">
									    <label for="inputEmail3" class="col-sm-2 control-label"></label>
									    <div class="col-sm-10">
									    	<h5 style="color: red;">${exception}</h5>
									    </div>
			</div> 
	</div>
	<div class="row">		
			<div class="col-md-12">
				
					<div class="col-md-12" style="margin-top:30px">
					
						<table class="table table-hover table-striped table-bordered table-condensed">
		     			   <thead>
		         		       <tr>
		           	   	            <td> <a href="<c:url value="/librarian/users/orderbyname"/>">
		           	   	            <h5> <strong> <spring:message code="message.libFirstName"/></strong> </h5> </a></td>
		           	   	             
		            	            <td>  <a href="<c:url value="/librarian/users/orderbysurname"/>">
		           	   	            <h5> <strong> <spring:message code="message.libLastName"/></strong> </h5> </a> </td> 
		                            <td>  <h5> <strong> <spring:message code="message.libEMail"/> </strong> </h5>  </td>
		                            <td>  <h5> <strong> <spring:message code="message.libCellPhone"/> </strong> </h5>  </td>
		                            <td>  <h5> <strong> <spring:message code="message.libConfirmed"/> </strong> </h5>  </td>
		                            <td> <h5> <strong> <spring:message code="message.libSMS"/> </strong> </h5>  </td>    
		                            <td>  <h5> <strong> <spring:message code="message.libReturnedOnTime"/> </strong> </h5>  </td>
		                            <td>  <h5> <strong> <spring:message code="message.libReturnedLate"/> </strong> </h5>  </td> 
		                            <td>  <h5> <strong> Books Available </strong> </h5>  </td>
		                            <td> <a href="<c:url value="/librarian/users/orderbyrating"/>">
		           	   	            <h5> <strong> <spring:message code="message.libRating"/></strong> </h5> </a>  </td>
		                            <td>  <h5> <strong> <spring:message code="message.libOptions"/> </strong> </h5>  </td>
		               	         
		               		   </tr>
		       			 </thead>
				        <c:forEach items="${users}" var="user">
				                <tr>
				                
				                        <td hidden="true">${user.pid}</td>
				                        <td>${user.name}</td>
				                        <td>${user.surname}</td>
				                        <td>${user.email}</td>
				                        <td>${user.cellphone}</td>
				                        <td>${user.confirm}</td>
				                        <td>${user.sms}</td>
				                        <td>${user.timelyReturn}</td>
				                        <td>${user.untimekyReturn}</td>
				                        <td>${user.multiBook} / ${user.booksAllowed}</td>
				                        <td>${user.generalRating}/100</td>
				                        
				                    <td><a href="${pageContext.request.contextPath}/librarian/users/edituser?id=${user.pid}"  style="color: #0E3846"><spring:message code="message.libEdit"/> |</a>


				                        <a onclick="jQuery:delete_user($(this).attr('nik'));" nik="/librarian/users/deleteuser?id=${user.pid}"     style="color: #0E3846"><em title="${pageContext.request.contextPath}/librarian/users/deleteuser?id=${user.pid}"></em><spring:message code="message.libDelete"/>|</a>

				                        <a href="${pageContext.request.contextPath}/librarian/users/readnow?id=${user.pid}"  style="color: #0E3846">Books</a></td>
				                </tr>
				        </c:forEach>
			   			</table>
					</div>
				
					
				
			</div>

		<div class="row">

	<div class="col-md-1 col-md-offset-9" style="margin-top:10px">
		<a href="${pageContext.request.contextPath}/librarian/users/adduser" class="btn btn-default btn-sm" role="button" style="background-color: #00A1A1 ; color: #FFFFFF"><spring:message code="message.libAddUser"/></a>
	</div>
	
	</div>		
	
	
	
	
</div>
<div id="er" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Error</h4>
            </div>
            <div class="modal-body">
                <p>Unable to delete the user. Reason: he has orders</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

<div id="su" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Success</h4>
            </div>
            <div class="modal-body">
                <p>The user was deleted.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
	

</script>
<!--href="<c:url value="/librarian/users/deleteuser?id=${user.pid}"/>" -->
<!--$("em").attr("title")  $("#su").modal("show")-->
</div>		
	</div>
<script type="text/javascript">
function delete_user(nik){
    $.ajax({
        url: nik,
        type: "get",
        cache: false,
        success: alert(nik),
        error:  $("#er").modal("show")
    });

}

</script>

