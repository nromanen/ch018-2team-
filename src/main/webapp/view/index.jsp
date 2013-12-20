<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
 <title>Test</title>
  <c:url value="/resources/css/general.css" var="generalCSS" />  
  <link rel="stylesheet" type="text/css" media="screen" href="${generalCSS}" />  
  <c:url value="/resources/css/jquery.js" var="jquery" /> 
  <script type="text/javascript" src="/resources/js/jquery.js"></script>
  <script type="text/javascript" src="/resources/js/genScript.js" /></script>
</head>
<body>
<c:import url="/view/header.jsp" />
<div id="main_body">
    <div id="left">
        
    </div>
    <div id="right">
        <form id="registration" action="<c:url value="/register" />" method="post">
            <div id="reg_head"><span>Sign Up for our Library</span></div>
            <input type="text" name="name"  value="First Name">
            <input type="text" name="surname" value="Last Name">
            <input type="email" name="email" value="Email">
            <input type="email" name="rEmail" value="Re-enter email">
            <input type="password" name="password">
            <input type="text" name="cellphone" value="Phone number">
            <input id='tmp' type="submit" value="Sign Up">
        </form>
    </div>
</div>
<c:import url="/view/footer.jsp" />
</body>
</html>