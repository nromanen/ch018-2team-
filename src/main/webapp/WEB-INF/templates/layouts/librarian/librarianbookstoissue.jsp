<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library</title>

</head>
<body>
<h3>Books</h3>
<c:set var="highlight" value="books" scope="request"/>
<br>
<table border = "1">
        <thead>
                <tr>
                        <td> <b> ID </b> </td>
                        <td> <b> Title </b> </td>
                        <td> <b> Authors </b> </td> 
                        <td> <b> Year </b> </td>
                        <td> <b> Publisher </b> </td>
                        <td> <b> Pages </b> </td>
                        <td> <b> Genre </b> </td>
                        <td> <b> Description </b> </td>    
                        <td> <b> Shelf </b> </td>
                        <td> <b> Options </b> </td>
                        
                </tr>
        </thead>
        <c:forEach items="${books}" var="book">
                <tr>
                        <td>${book.bId}</td>
                        <td>${book.title}</td>
                        <td>${book.authors}</td>
                        <td>${book.year}</td>
                        <td>${book.publisher}</td>
                        <td>${book.pages}</td>
                        <td>${book.genre}</td>
                        <td>${book.description}</td>
                        <td>${book.shelf}</td>
                    <td><a href="<c:url value="/librarian/books/editbook?id=${book.bId}"/>">Edit</a>
                    <td><a href="<c:url value="/librarian/books/deletebook?id=${book.bId}"/>">Delete</a></td>
                </tr>
        </c:forEach>
</table>
</body>
</html>