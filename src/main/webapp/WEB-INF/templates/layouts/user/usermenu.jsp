<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>


<sec:authorize access="isAuthenticated()">
    <tilesx:useAttribute name="orders" />
    <ul class="nav navbar-nav navbar-right">
        <li><a href="${pageContext.request.contextPath}/books" id="my_books"><spring:message code="messages.home" /></a></li>
        <li><a  href="${pageContext.request.contextPath}/books/mybooks" id="my_books"><spring:message code="message.mybooks" /></a></li>
        <li><a  href="${pageContext.request.contextPath}/books/order/my" id="my_orders"><spring:message code="message.orders" /><span class="badge pull-right">${orders}</span></a></li>
        <li><a href="${pageContext.request.contextPath}/books/wishlist/my" id="my_wishlist"><spring:message code="message.wishlist" /></a></li>
        <li class="dropdown" id="hello">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="message.hello" /> ${pageContext.request.userPrincipal.name} <b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a href="${pageContext.request.contextPath}/account" id="my_account"><spring:message code="message.account" /></a></li>
                <li><a href="<c:url value="/j_spring_security_logout" />"><spring:message code="message.logout" /></a></li>
            </ul>
        </li>
    </ul>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ANONYMOUS')">

	<div class="nav navbar-nav navbar-left" style="margin : 8px 0 10px 0;">
	<a type="button" class="btn btn-default" href="${pageContext.request.contextPath}/books"><spring:message code="message.home" /></a>
	 <a type="button" class="btn btn-default" href="${pageContext.request.contextPath}/books/search"><spring:message code="message.catalogue" /></a>
	</div>

    <div class="nav navbar-nav navbar-right" style="margin : 10px 0 10px 0;">
        <a href="#" data-toggle="popover" role="button" data-original-title="<spring:message code="message.singin" />" id = "login_popover" class="btn btn-info btn-sm" style="margin-right: 20px;"><spring:message code="message.singin" /></a>
        <div class="hide" id="popover_content">
        	<div class="">
        	<form  role="form" action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
            <div class="form-group">
                <input  class="form-control" type="text" name="j_username" placeholder="Enter Email">
            </div>
            
            <div class="form-group">
                <input class="form-control" type="password" name="j_password" placeholder="Enter Password">
            </div>


            <button  type="submit" class="btn btn-primary btn-sm"><spring:message code="message.singin" /></button>

			<c:if test="${param.fail != null}">  
	    		<div id="login_error" class="row" style="margin-left: 5px">
					<div  style="font-size: 16px; color: red">
						<spring:message code="message.incorrectLP" />
					</div>
				</div>
			</c:if>  
			
			
            <div class="row" style="margin-left: 5px; padding-top: 10px;">
					
               		 <label class="text-info">
                    <input  type="checkbox" name="_spring_security_remember_me">
                    <span><spring:message code="message.remember" />  </span>
                	</label>
					
                	<label class="text-info" style="margin-left: 17px;">
                    <span><a id="restore" class="text-info"><spring:message code="message.forgot"/></a></span>
                    </label>
                    
               
            </div>
            
           
        </form>
        </div>
        </div>
        
       
        <a href="${pageContext.request.contextPath}/register" type="submit" class="btn btn-success btn-sm"><spring:message code="message.registration" /></a>
 		
 		
        
    </div>
   
    
   
    <div class="clearfix"></div>



</sec:authorize>


<!--Modal Forgot pass-->
<div class="modal fade" id="forgot_pass" tabindex="-1" role="dialog" aria-labelledby="forgot_pass_label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="forgot_pass_label">Notification</h4>
            </div>
            <div class="modal-body">
                <div class="text-info"><h3><spring:message code="message.restore" /></h3></div>
            </div>
            <div class="modal-footer">
                <form id="restore_mail_form" action="${pageContext.request.contextPath}/restore" method="post">
                <div class="form-group col-lg-8 col-lg-offset-2">
                    <input type="email" class="form-control" name="email" placeholder="Email">
                    </div>
                    <div class="form-group col-lg-8 col-lg-offset-2">
                        <input id="restore_mail_form_submit" url="${pageContext.request.contextPath}/restore" type="submit" class="form-control btn btn-warning" value="Restore">
                        </div class="form-group col-lg-8 col-lg-offset-2">
                        <div class="clearfix"></div>
                    <div id="forgot_error_mail_div" class="alert alert-danger hide">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!--Modal Forgot pass-->

<!--Modal forgot_mail_send-->

<div class="modal fade" id="forgot_mail_send" tabindex="-1" role="dialog" aria-labelledby="forgot_mail_send_label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="forgot_mail_send_label">Limit Notification</h4>
            </div>
            <div class="modal-body">
                <div class="text-info"><h3><spring:message code="message.restorecheck" /></h3></div>

            </div>

        </div>
    </div>
</div>

<!--Modal forgot_mail_send-->  
