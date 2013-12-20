<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <c:url value="/resources/css/general.css" var="generalCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${generalCSS}" />  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Book</title>
    </head>
    <body>
        
        <form id="add_book" action="<c:url value="/books/addBook" />" method="POST">
            <div id="inner">
            title
                <input type="text" name="title">
            author
                <input type="text" name="authors">
            year
                <input type="text" name="year">
            publisher
                <input type="text" name="publisher">
            pages
                <input type="text" name="pages">
            description
                <input type="text" name="description">
            img url
                <input type="text" name="img">
            genre

                <select name="genreId" >
                    <c:forEach var="genre" items="${genres}">
                        <option value="${genre.getId()}">${genre.getDescription()}</option>
                    </c:forEach>
                </select>
                <input type="submit">
            </div>
        </form>
    </body>
</html>
