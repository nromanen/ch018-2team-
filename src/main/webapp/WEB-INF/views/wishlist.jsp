<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <c:url value="/resources/css/books.css" var="booksCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${booksCSS}" />  
        <c:url value="/resources/css/wish.css" var="wishCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${wishCSS}" />  
        <c:url value="/resources/css/jquery.datetimepicker.css" var="timeCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${timeCSS}" />  
        
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.datetimepicker.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/wish.js" />"></script>
    </head>
    <body>
       
            <c:import url="/WEB-INF/views/booksheader.jsp" />
            <c:import url="/WEB-INF/views/booksmainleft.jsp" />
            <div id="wish_list_main">
                <div id="wish_list_table_wrapper">
                    <table>
                        <tr>
                            <td>book</td>
                            <td>free from</td>
                            <td>estimated date</td>
                            <td>delete</td>
                        </tr>
                        <c:forEach var="entry" items="${map}">
                            <tr>
                                <td>${entry.key.getTitle()}</td>
                                <td>${entry.value}</td>
                                <td>
                                    <input type="text" class="datetimepicker" value="${entry.value}" />
                                    <input type="hidden" value="${entry.key.getbId()}" />
                                    <button class="confirm">confirm</button>
                                </td>
                                <td><a href="<c:url value="/books/wishlist/delete?id=${entry.key.getbId()}" />">delete</a>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <c:import url="/WEB-INF/views/booksmainright.jsp" />
            <c:import url="/WEB-INF/views/footer.jsp" />

    </body>
</html>