<%@page import="com.ch018.library.entity.Orders"%>
<%@page import="java.util.Map"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ch018.library.entity.BooksInUse"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <c:url value="/resources/css/bootstrap.min.css" var="bootstrapCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${bootstrapCSS}" />
        <c:url value="/resources/css/main.css" var="mainCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${mainCSS}" /> 
        <c:url value="/resources/css/jquery.datetimepicker.css" var="dateCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${dateCSS}" /> 
        <c:url value="/resources/css/search.css" var="searchCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${searchCSS}" />
        <c:url value="/resources/css/orders.css" var="ordersCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${ordersCSS}" />
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.datetimepicker.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/wishlist.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/search.js" />"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/books.js"></script>
        

    </head>
    <body>
        
        <div class="container">
            <c:import url="/WEB-INF/views/mainheader.jsp" />
            <div class="row">
                
                <div class="col-md-1" id="left_main">
                    <!--New Arrivals-->
                </div>
                
                <div class="col-md-11" id="center_main">
                    <ul class="list-unstyled">
                        <li class="list-group-item">
                                <div class="row">
                                    <div class="col-md-4">
                                        Title
                                    </div>
                                    <div class="col-md-5">
                                        Free From
                                    </div>
                                    <div class="col-md-3">
                                        Delete
                                    </div>
                                </div>
                            </li>
                        <c:forEach var="entry" items="${map}">
                            
                            <li class="list-group-item" id="wish_li_${entry.key.getId()}">
                                <div class="row">
                                    <div class="col-md-4">
                                        ${entry.key.getBook().getTitle()}
                                    </div>
                                    <div class="col-md-5">
                                        
                                        
                                        <!--<input type="hidden" value="${entry.value}">-->
                                        <input type="hidden" value="${entry.key.getId()}">
                                        <input type="hidden" value="${entry.key.getBook().getbId()}">
                                        <input type="text" class="calendar" value="${entry.value}">
                                        <button class="btn-info wish_confirm_button">Confirm</button>
                                    </div>
                                    <div class="col-md-3">
                                        <input type="hidden" value="${entry.key.getId()}">
                                        <button class="btn-danger wish_delete_button">Delete</button>
                                    </div>
                                </div>
                            </li>
                            
                        </c:forEach>
                        
                    </ul>
                    
                </div>
                
                
            </div>
            <c:import url="/WEB-INF/views/footer.jsp" />
        </div>
    </body>
</html>
