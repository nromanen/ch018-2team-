<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>${book.getTitle()}
        <p>${person.getName()}
            <c:forEach var="bis" items="${biulist}">
                <hr>
                <p>${bis.getReturnDate()}
            </c:forEach>    
    </body>
</html>
