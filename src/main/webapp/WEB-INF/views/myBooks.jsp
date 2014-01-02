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
        <c:url value="/resources/css/mybooks.css" var="mybooksCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${mybooksCSS}" />
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js" />"></script>
        
        <script type="text/javascript" src="<c:url value="/resources/js/order.js" />"></script>
        <!--<script type="text/javascript" src="<c:url value="/resources/js/books.js" />"></script>-->
        <script type="text/javascript" src="<c:url value="/resources/js/search.js" />"></script>

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
                        
                        <c:forEach var="use" items="${uses}">
                            <li class="list-group-item">
                                <div class="row">
                                    <div class="col-md-5">
                                        Title
                                    </div>
                                    <div class="col-md-4">
                                        Return Date
                                    </div>
                                    <div class="col-md-3">
                                        Days To Return
                                    </div>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <div class="row">
                                    <div class="col-md-5">
                                        ${use.getBook().getTitle()}
                                    </div>
                                    <div class="col-md-4">
                                        ${use.getReturnDate()}
                                    </div>
                                    <div class="col-md-3">
                                        <%
                                            BooksInUse u = (BooksInUse) pageContext.getAttribute("use");
                                            int days = (int) (u.getReturnDate().getTime() - new Date().getTime())/(1000 * 3600 * 24);
                                            pageContext.setAttribute("days", days);
                                        %>
                                        ${days}
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
