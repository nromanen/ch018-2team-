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
  
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index.js"></script>
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
                    <form id="registration_form" class="form-horizontal" role="form" action="<c:url value="/register" />" method="post">
                        <legend>Sign Up for our Library</legend>
                        <div class="form-group col-lg-8">
                            <input id="reg_name" class="form-control" type="text" name="name" minlength="2" required placeholder="First Name">
                        </div>
                        <div class="form-group col-lg-8">
                            <input id="reg_surname" class="form-control" type="text" name="surname"  placeholder="Last Name">
                        </div>
                        <div class="form-group col-lg-8">
                            <input id="reg_mail" class="form-control" type="email" name="email" required placeholder="Email">
                        </div>
                        <div class="form-group col-lg-8">
                            <input id="reg_rmail" class="form-control" equalto="#reg_mail" type="email" name="rEmail" required placeholder="Re-enter Email">
                        </div>
                        <div class="form-group col-lg-8">
                            <input id="reg_pass" class="form-control" type="password" name="password" required placeholder="Password">
                        </div>
                        <div class="form-group col-lg-8">
                            <input id="reg_rpass" class="form-control" equalto="#reg_pass" type="password" name="rPassword" required placeholder="Re-enter Password">
                        </div>
                        <div class="form-group col-lg-8">
                            <input id="reg_phone" class="form-control" type="text" name="cellphone" required placeholder="Phone Number">
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