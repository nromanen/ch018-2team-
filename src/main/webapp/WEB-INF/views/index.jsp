<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
 <title>Test</title>
  <c:url value="/resources/css/bootstrap.min.css" var="bootstrapCSS" />  
  <link rel="stylesheet" type="text/css" media="screen" href="${bootstrapCSS}" />
  <c:url value="/resources/css/index.css" var="indexCSS" />  
  <link rel="stylesheet" type="text/css" media="screen" href="${indexCSS}" />
  <c:url value="/resources/css/bootstrap-theme.min.css" var="bootstrapTCSS" />  
  <link rel="stylesheet" type="text/css" media="screen" href="${bootstrapTCSS}" />
  
  <c:url value="/resources/css/jquery.js" var="jquery" /> 
  <script type="text/javascript" src="/resources/js/jquery.js"></script>
  <script type="text/javascript" src="/resources/js/genScript.js" /></script>
</head>
<body>
    
    <div class="container">
        <div class="row">
            <div class="col-md-7">
            </div>
            <div class="col-md-5">
                <form class="form-inline" role="form" action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
                    <div class="form-group">
                        <input  class="form-control" type="text" name="j_username" placeholder="Enter Email">
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="password" name="j_password" placeholder="Enter Password">
                    </div>
                    <div class="form-group">
                        <input type="checkbox" name="_spring_security_remember_me">
                    </div>
                    <button type="submit" class="btn btn-primary">Sing In</button>
                </form>
            </div>
        </div>
        <div class="row" >
            <div class="col-md-8 row-padded">
            </div>
            <div class="col-md-4">
                <div class="row">
                    <form class="form-horizontal" role="form" action="<c:url value="/register" />" method="post">
                        <h3>Sign Up for our Library</h3>
                        <div class="form-group col-lg-8">
                            <input class="form-control" type="text" name="name"  placeholder="First Name">
                        </div>
                        <div class="form-group col-lg-8">
                            <input class="form-control" type="text" name="surname" placeholder="Last Name">
                        </div>
                        <div class="form-group col-lg-8">
                            <input class="form-control" type="email" name="email" placeholder="Email">
                        </div>
                        <div class="form-group col-lg-8">
                            <input class="form-control" type="email" name="rEmail" placeholder="Re-enter Email">
                        </div>
                        <div class="form-group col-lg-8">
                            <input class="form-control" type="password" name="password" placeholder="Password">
                        </div>
                        <div class="form-group col-lg-8">
                            <input class="form-control" type="password" name="rPassword" placeholder="Re-enter Password">
                        </div>
                        <div class="form-group col-lg-8">
                            <input class="form-control" type="text" name="cellphone" placeholder="Phone Number">
                        </div>
                        <div class="form-group col-lg-8">
                        <button class="btn btn-success" type="submit" >Sign Up</button>
                        </div>
                    </form>
                </div>
                </div>
                <div class="row">
                    
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-1">
            </div>
        </div>
    </div>
    
</body>
</html>