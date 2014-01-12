<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="col-md-4 col-md-offset-4" style="margin-top: 50px;">
                <div class="form-group">
                    <form id="restore_pass_form">
                        <input type="hidden" name="key" value="${key}">
                        <input type="hidden" name="oldPass" value="111111">
                        <input type="password" class="form-control" name="newPass" placeholder="New Password">
                        <p />
                        <input type="password" class="form-control" name="rNewPass" placeholder="Re_enter Password">
                        <p />
                            <input id="restore_pass_submit" type="submit" class="form-control btn btn-success" value="Change">
                            <div id="restore_pass_err" class="alert alert-danger hide">
                            </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
