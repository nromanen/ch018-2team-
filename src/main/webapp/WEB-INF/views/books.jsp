<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Books</title>
</head>
<body>
    <div id="book_table">
        <table>
            <tr>
                <td>Title</td>
                <td>Author</td>
                <td>Year</td>
                <td>Publisher</td>
                <td>Pages</td>>
                <td>Genre</td>
                <td>Edit</td>
            </tr>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.getTitle()}</td>
                    <td>${book.getAuthors()}</td>
                    <td>${book.getYear()}</td>
                    <td>${book.getPublisher()}</td>
                    <td>${book.getPages()}</td>
                    <td>${book.getGenre().getDescription()}</td>
                    <td><a href="<c:url value="/books/edit?id=${book.getbId()}" />">edit</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>