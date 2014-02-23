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
					   <div class="table-responsive" id="content1">
						<table class="table table-hover table-striped table-bordered table-condensed">
		     			   <thead>
		         		       <tr>
		           	   	            <td>  <a href="<c:url value="/librarian/users/orderbyname"/>"> <strong> <spring:message code="message.libFirstName"/></strong>  </a></td>
		           	   	             
		            	            <td>  <a href="<c:url value="/librarian/users/orderbysurname"/>"> <strong> <spring:message code="message.libLastName"/></strong>  </a> </td> 
		                            <td>   <strong> <spring:message code="message.libEMail"/> </strong>  </td>
		                        
			                           <td>   <strong> <spring:message code="message.libReturnedOnTime"/> </strong> </td>
			                            <td>  <strong> <spring:message code="message.libReturnedLate"/> </strong> </td> 
		                            
		                            
		                            <td>  <strong>in use / max amount</strong></td>
		                            <td> <a href="<c:url value="/librarian/users/orderbyrating"/>"> <strong> <spring:message code="message.libRating"/></strong>  </a> </td>
		                            <td>  <strong> <spring:message code="message.libOptions"/> </strong> </td>
		               	         
		               		   </tr>
		       			 </thead>
				        <c:forEach items="${users}" var="user">
				                <tr>
				                
				                        <td hidden="true">${user.pid}</td>
				                        <td>${user.name}</td>
				                        <td>${user.surname}</td>
				                        
				                        <td>${user.sms}</td>
				                        <td>${user.timelyReturn}</td>
				                        <td>${user.untimekyReturn}</td>
				                        <td>${user.booksOnHands} / ${user.booksAllowed}</td>
				                        <td>${user.generalRating}/100</td>
				                        
				                    <td><a href="${pageContext.request.contextPath}/librarian/users/edituser?id=${user.pid}"  style="color: #0E3846">Details |</a>


				                        <!-- jquery:shure()--><a onclick="jQuery:delete_user($(this).attr('nik'));" nik="${pageContext.request.contextPath}/librarian/users/deleteuser?id=${user.pid}"     style="color: #0E3846; cursor:pointer"><spring:message code="message.libDelete"/>|</a>

				                        <a href="${pageContext.request.contextPath}/librarian/users/readnow?id=${user.pid}"  style="color: #0E3846">Books</a></td>
				                </tr>
				        </c:forEach>
			   			</table>
                           </div>
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
                <h4 class="modal-title">Message</h4>
            </div>
            <div id="jik" class="modal-body">
                <!--<p>Unable to delete the user. Reason: he has orders</p>  -->

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button hidden="false" id="buttonDelete" type="button" class="btn btn-primary">Delete Orders</button>
            </div>
        </div>
    </div>
</div>

<div id="su" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Message</h4>
            </div>
            <div class="modal-body">
                <p>r u shure?</p>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button id="buttonDelete" type="button" class="btn btn-primary">Delete Orders</button>
            </div>
        </div>
    </div>
</div>



</div>		
	</div>
<script type="text/javascript">
function delete_user(nik){
    $.ajax({
        url: nik,
        type: "get",
        cache: false,
        success: function(data){
            $("#buttonDelete").show();
            $("#su").modal("hide")
           if (data=="User was deleted") {
               $("#content1").load("${pageContext.request.contextPath}/library/librarian/users/orderbyrating #content1");
               $("#buttonDelete").hide();
               $("#jik").load().text(data);
               $("#er").modal("show");
           }

            $("#jik").load().text(data);
            $("#er").modal("show");

        }
        //error:  $("#er").modal("show")
    });

}
 function shure(nik){
  $("#su").modal("show");
   $("#buttonDelete").click(delete_user(nik));
 }
</script>

