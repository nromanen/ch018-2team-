<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row well">
		<div class="col-md-4 col-md-offset-4" style="margin-top: 50px;">

			<div class="panel panel-warning">
				<div class="panel-heading">
					<div class="panel-title"><spring:message code="message.singin" /></div>
						<c:if test="${fail != null}" >
							<div class="panel-title" style="color: red"><spring:message code="message.incorrectLP" /></div>
						</c:if>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<form role="form" action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
							<div class="form-group">
								<input class="form-control" type="text" name="j_username" placeholder="Enter Email">
							</div>
							<div class="form-group">
								<input class="form-control" type="password" name="j_password" placeholder="Enter Password">
							</div>

							<button type="submit" class="btn btn-primary btn-sm">
								<spring:message code="message.singin" />
							</button>

							<div class="row">

								<label class="text-info" style="margin-left: 12px; margin-top: 5px;"> 
								<input type="checkbox" name="_spring_security_remember_me"> <span><spring:message code="message.remember" />
								</label> 
								<br>
								<label class="text-info" style="margin-left: 30px;"> <span><a id="restore" class="text-info"><spring:message code="message.forgot" /></a>
								</label>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	
	<!--Modal Forgot pass-->
<div class="modal fade" id="forgot_pass" tabindex="-1" role="dialog" aria-labelledby="forgot_pass_label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            
            <div class="modal-body">
                <div class="text-info"><h6><spring:message code="message.restore" /></h6></div>
            </div>
            <div class="modal-footer">
                <form id="restore_mail_form" action="${pageContext.request.contextPath}/restore" method="post">
                <div class="form-group col-lg-8 col-lg-offset-2">
                    <input type="email" class="form-control" name="email" placeholder="Email">
                  </div>
                  <div class="form-group col-lg-8 col-lg-offset-2">
                        <input id="restore_mail_form_submit" url="${pageContext.request.contextPath}/restore" type="submit" class="form-control btn btn-warning" value="Restore">
                        </div>
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
            
            <div class="modal-body">
                <div class="text-info"><h4><spring:message code="message.restorecheck" /></h4></div>

            </div>

        </div>
    </div>
</div>

<!--Modal forgot_mail_send-->  

</div>