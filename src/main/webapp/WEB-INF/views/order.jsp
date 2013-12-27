<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <c:url value="/resources/css/books.css" var="booksCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${booksCSS}" />  
        <c:url value="/resources/css/orders.css" var="ordersCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${ordersCSS}" />  
        <c:url value="/resources/css/jquery.datetimepicker.css" var="timeCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${timeCSS}" />  
        
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.datetimepicker.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/order.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/search.js" />"></script>
    </head>
    <body>
        <input id="bookid" type="hidden" value="${bookid}">
            <c:import url="/WEB-INF/views/mainheader.jsp" />
            <c:import url="/WEB-INF/views/mainleft.jsp" />
            <c:import url="/WEB-INF/views/center.jsp" />
            <c:import url="/WEB-INF/views/mainright.jsp" />
            <c:import url="/WEB-INF/views/footer.jsp" />

    </body>
</html>