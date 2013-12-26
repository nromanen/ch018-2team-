<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <c:url value="/resources/css/books.css" var="booksCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${booksCSS}" />  
        <c:url value="/resources/css/orders.css" var="ordersCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${ordersCSS}" />  
        <c:url value="/resources/css/jquery.datetimepicker.css" var="timeCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${timeCSS}" />  
        
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.datetimepicker.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/orders.js" />"></script>
    </head>
    <body>
       
            <c:import url="/WEB-INF/views/mainheader.jsp" />
            <c:import url="/WEB-INF/views/mainleft.jsp" />
            <div id="orders_main">
                <div id="orders_table_wrapper">
                    <table>
                        <tr>
                            <td>book</td>
                            <td>edit</td>
                            <td>delete</td>
                        </tr>
                        <c:forEach var="entry" items="${map}">
                            <tr>
                                <td>${entry.key.getBook().getTitle()}</td>
                                <td>
                                    <input type="text" class="datetimepicker" value="${entry.key.getOrderDate()}">
                                    <input type="hidden" value="${entry.value}">
                                    <input type="hidden" value="${entry.key.getBook().getbId()}">
                                    <button class="edit">edit</button>
                                </td>
                                <td><a href="<c:url value="/books/order/delete?id=${entry.key.getId()}" />">delete</a>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <c:import url="/WEB-INF/views/mainright.jsp" />
            <c:import url="/WEB-INF/views/footer.jsp" />

    </body>
</html>