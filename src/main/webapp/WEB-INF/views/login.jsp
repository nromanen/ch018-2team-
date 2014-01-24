<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.maskedinput.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index.js"></script>
</head>
<body>
	<div class="container">
		<div class="col-md-4 col-md-offset-4" style="margin-top: 50px;">

			<div class="panel panel-warning">
				<div class="panel-heading">
					<div class="panel-title">Login form</div>
						<c:if test="${fail != null}" >
							<div class="panel-title" style="color: red">Incorrect login/password entered</div>
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

								<label class="text-info"> <input type="checkbox" name="_spring_security_remember_me"> 
									<span><spring:message code="message.remember" />
								</label> 
								<label class="text-info" style="margin-left: 30px;">
									<span><a id="restore" class="text-info"><spring:message code="message.forgot" /></a>
								</label>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--Modal Forgot pass-->
<div class="modal fade" id="forgot_pass" tabindex="-1" role="dialog" aria-labelledby="forgot_pass_label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="forgot_pass_label">Notification</h4>
            </div>
            <div class="modal-body">
                <div class="text-info"><h2>For Restoring password input your email</h2></div>
            </div>
            <div class="modal-footer">
                <form id="restore_mail_form" action="${pageContext.request.contextPath}/restore" method="post">
                    <input type="email" class="form-control" name="email" placeholder="Email">
                    <p>
                        <input id="restore_mail_form_submit" url="${pageContext.request.contextPath}/restore" type="submit" class="form-control btn btn-warning" value="Restore">
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
                <div class="text-info"><h3>Check your email for password restore details</h3></div>

            </div>

        </div>
    </div>
</div>

<!--Modal forgot_mail_send-->  
</body>
</html>
