<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Access Denied</title>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-offset-2">
                    <div class="panel panel-danger">
                        <div class="panel-heading">
                            <h1>Something Wrong</h1> 
                        </div>
                        <div class="panel-body">
                            <h3>We already resolve problem.</h3>
                            <a class="btn btn-info" href="${pageContext.request.contextPath}/">Home</a>
                        </div>
                    </div>
                </div>     
            </div>
        </div>
    </body>
</html>
