<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <c:url value="/resources/css/books.css" var="booksCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${booksCSS}" />  
        <c:url value="/resources/css/jquery.js" var="jquery" /> 
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
        
        <script type="text/javascript" src="<c:url value="/resources/js/main.js" />"></script>
    </head>
    <body>
       
            <c:import url="/WEB-INF/views/booksheader.jsp" />
            <c:import url="/WEB-INF/views/booksmainleft.jsp" />
            <c:import url="/WEB-INF/views/booksmain.jsp" />
            <c:import url="/WEB-INF/views/booksmainright.jsp" />
            <c:import url="/WEB-INF/views/footer.jsp" />

    </body>
</html>
