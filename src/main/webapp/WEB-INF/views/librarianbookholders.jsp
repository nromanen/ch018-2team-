<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Information</title>
</head>
<body>

<td><a href="<c:url value="/librarian/books"/>"><h3>Books</h3></a>

	<input type="text" value="${book.getTitle()}"/>
	<table  border = "1">
		 <thead>
                <tr>
                        <td> <b> Holder </b> </td>
                        <td> <b> Return Date </b> </td>
                        <td> <b> Days to Return </b> </td>
                        
                </tr>
        </thead>
		<c:forEach items="${booksInUse}" var="bookInUse">
                <tr>
                        <td>${bookInUse.person}</td>
                        <td>${bookInUse.getReturnDate()}</td>
                        <td>${bookInUse.daysToreturn}</td>
                </tr>
        </c:forEach>
	</table>
</body>
</html>