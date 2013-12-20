<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:url value="/resources/css/general.css" var="generalCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${generalCSS}" />  
        <title>JSP Page</title>
    </head>
    <body>
        
        <form id="add_book" action="<c:url value="/add" />" method="POST">
            <div id="inner">
                person
                <select name="pId" >
                    <c:forEach var="person" items="${persons}">
                        <option value="${person.getPid()}">${person.getSurname()}</option>
                    </c:forEach>
                </select>
                book
                <select name="bId" >
                    <c:forEach var="book" items="${books}">
                        <option value="${book.getbId()}">${book.getTitle()}</option>
                    </c:forEach>
                </select>
                <input type="submit">
            </div>
        </form>
    </body>
</html>
