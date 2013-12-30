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
		       
            title
                <form:input path="title"/>
            author
            	 <form:input path="authors"/>
            year
            <form:input path="year"/>
            publisher
                <form:input path="publisher"/>
            pages
                <form:input path="pages"/>
            description
                <form:input path="description"/>
            img url
                <form:input path="img"/>
            genre
            	<form:input path="bId"/>
			<!--  <input type="text" name = "bookId" value = "bId"/> -->
                <select name="genreId" >
                    <c:forEach var="genre" items="${genre}">
                        <option value="${genre.getId()}">${genre.getDescription()}</option>
                    </c:forEach>
                </select>
                
                <input type="submit" value="Add">
            
        </form:form>
    </body>
</html>