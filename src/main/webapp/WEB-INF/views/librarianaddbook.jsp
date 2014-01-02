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
        
        <form:form method="POST" commandName="book" >
            
            Title
                <input type="text" name="title">
            Author
                <input type="text" name="authors">
            Year
                <input type="text" name="year">
            Publisher
                <input type="text" name="publisher">
            Pages
                <input type="text" name="pages">
            Description
                <input type="text" name="description">
            Img url
                <input type="text" name="img">
            Shelf 
            	<input type="text" name="shelf"> 
            Term
            	<input type="text" name="term">	    
            Quantity
            	<input type="text" name="generalQuantity">	
            genre

                <select name="genreId" >
                    <c:forEach var="genre" items="${genre}">
                        <option value="${genre.getId()}">${genre.getDescription()}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Add">
            
        </form:form>
    </body>
</html>