<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Peoples</title>

</head>
<body>
<h3>People List</h3>
<c:set var="highlight" value="Students" scope="request"/>
<br>
<table border = "2">
        <thead>
                <tr>
                        <td> <b> ID </b> </td>
                        <td> <b> Name </b> </td>
                        <td> <b> Options </b> </td> 
                </tr>
        </thead>
        <c:forEach items="${person}" var="pers">
                <tr>
                        <td>${pers.pid}</td>
                        <td><c:out value="${pers.name}" escapeXml="true"/></td>
                        <td><a href="<c:url value="/addOrder?id=${pers.pid}"/>">Add Order</a></td>
                </tr>
        </c:forEach>
</table>

</body>
</html>