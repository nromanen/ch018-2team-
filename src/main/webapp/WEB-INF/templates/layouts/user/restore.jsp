<%@page contentType="text/html" pageEncoding="UTF-8"%>

    
        <div class="row well">
            <div class="col-md-4 col-md-offset-4" style="margin-top: 50px;">

                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <div class="panel-title">Password reset form</div>
                         </div>
                    <div class="panel-body">
                        <div class="form-group">
                            <form id="restore_pass_form" role="form">

                                <input type="hidden" name="key" value="${key}">
                                <input type="hidden" name="oldPass" value="111111">
                                
                                <div class="form-group">
    								<input id="pass" type="password" minlength="6" maxlength="16" class="form-control" name="newPass" placeholder="New Password">
  								</div>
                                
                                <div class="form-group">
                                	<input type="password" minlength="6" maxlength="16" equalto="#pass" class="form-control" name="rNewPass" placeholder="Re_enter Password" />
                            	</div>
                            	 <div class="form-group">
                                	<input id="restore_pass_submit" url="${pageContext.request.contextPath}/restore/password" type="submit" class="form-control btn btn-success" value="Change">
                                </div>
                                <div id="restore_pass_err" class="alert alert-danger hide">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

