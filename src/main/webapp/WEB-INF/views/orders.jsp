<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Orders</title>

</head>
<body>
<h3>Orders List</h3>
<c:set var="highlight" value="Students" scope="request"/>
<br>
<table border = "2">
        <thead>
                <tr>
                        <td> <b> ID </b> </td>
                        <td> <b> Date </b> </td>
                        <td> <b> Book </b> </td>
                        <td> <b> Person </b> </td>
                </tr>
        </thead>
        <c:forEach items="${orders}" var="order">
                <tr>
                        <td>${order.id}</td>
                        <td><c:out value="${order.date}" escapeXml="true"/></td>
                        <td><c:out value="${order.book.title}" escapeXml="true"/></td>
                        <td><c:out value="${order.person.name}" escapeXml="true"/></td>
                        
                        
                </tr>
        </c:forEach>
</table>

</body>
</html>