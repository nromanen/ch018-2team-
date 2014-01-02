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

<c:set var="highlight" value="books" scope="request"/>
<td><a href="<c:url value="/librarian/books"/>"><h3>Books</h3></a>
<td><a href="<c:url value="/librarian/orders"/>"><h3>Orders</h3></a>
<td><a href="<c:url value="/librarian/toreturn"/>"><h3>To Return</h3></a>
<br>
<br>
<form name="search" action="<c:url value="/librarian/orders/search" />" method="POST" >
		
		<input type="text"  name="request"/>
		<input type="submit" value="Show Result"/>
		
		</form>
<table border = "1">
        <thead>
                <tr>
                        <td> <b> ID </b> </td>
                        <td> <b> Person </b> </td>
                        <td> <b> Book </b> </td>
                        <td> <b> Return Date</b> </td>
                         
                </tr>
        </thead>
        <c:forEach items="${booksInUse}" var="bookInUse">
                <tr>
                        <td>${bookInUse.id}</td>
                        <td>${bookInUse.person.getName()}</td>
                        <td>${bookInUse.book.getTitle()}</td>
                        <td>${bookInUse.getReturnDate()}</td>
                        <td><a href="<c:url value="/librarian/toreturn/getback?id=${bookInUse.id}"/>">Get back</a>
                </tr>
        </c:forEach>
</table>
</body>
</html>