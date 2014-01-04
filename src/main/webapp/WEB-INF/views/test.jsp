<%-- 
    Document   : test
    Created on : Jan 4, 2014, 12:08:31 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${person.getName()}</h1>
        <h1>${state.isInUse()}</h1>
        <h1>${state.isInOrders()}</h1>
        <h1>${state.isInWishList()}</h1>
    </body>
</html>
