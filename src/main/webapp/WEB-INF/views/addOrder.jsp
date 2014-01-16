<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Order</title>
</head>
<body>
<body>
        <form:form method="POST" commandName="person">
                <table>
                        <tr>
                                <td>Name</td>
                                <td>${person.name}</td>
                        </tr>
                </table>
        </form:form>
        
        
	<form:select path="book" id="title" items="${book}" itemValue="title" itemLabel="title" />

</body>
</body>
</html>