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
       
            <c:import url="/WEB-INF/views/booksheader.jsp" />
            <c:import url="/WEB-INF/views/booksmainleft.jsp" />
            <div id="orders_main">
                <div id="orders_table_wrapper">
                    <table>
                        <tr>
                            <td>book</td>
                            <td>order date</td>
                            <td>edit</td>
                            <td>delete</td>
                        </tr>
                        <c:forEach var="order" items="${orders}">
                            <tr>
                                <td>${order.getBook().getTitle()}</td>
                                <td>${order.getOrderDate()}</td>
                                <td><input type="text" class="datetimepicker"><button class="order_edit">Edit</button></td>
                                <td><a href="<c:url value="/orders/delete?bid=entry.key.getbId()" />">delete</a>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <c:import url="/WEB-INF/views/booksmainright.jsp" />
            <c:import url="/WEB-INF/views/footer.jsp" />

    </body>
</html>