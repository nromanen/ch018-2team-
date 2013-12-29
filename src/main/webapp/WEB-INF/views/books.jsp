<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <c:url value="/resources/css/main.css" var="mainCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${mainCSS}" />
        <c:url value="/resources/css/books.css" var="booksCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${booksCSS}" /> 
        <c:url value="/resources/css/jquery.datetimepicker.css" var="dateCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${dateCSS}" /> 
        <c:url value="/resources/css/order.css" var="orderCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${orderCSS}" /> 
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.datetimepicker.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/order.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/wish.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/mybooks.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/books.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/search.js" />"></script>
        
    </head>
    <body>
       
            <c:import url="/WEB-INF/views/mainheader.jsp" />
            <c:import url="/WEB-INF/views/mainleft.jsp" />
            <c:import url="/WEB-INF/views/center.jsp" />
            <c:import url="/WEB-INF/views/mainright.jsp" />
            <c:import url="/WEB-INF/views/footer.jsp" />

    </body>
</html>
