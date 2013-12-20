<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <c:url value="/resources/css/home.css" var="homeCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${homeCSS}" />  
        <c:url value="/resources/css/jquery.js" var="jquery" /> 
        <script type="text/javascript" src="/resources/js/jquery.js"></script>
        <script type="text/javascript" src="/resources/js/genScript.js" /></script>
    </head>
    <body>
       
            <c:import url="/view/homeheader.jsp" />
            <c:import url="/view/homemain.jsp" />
            <c:import url="/view/footer.jsp" />

    </body>
</html>
