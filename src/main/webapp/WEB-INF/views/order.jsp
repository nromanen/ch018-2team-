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
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.datetimepicker.js" />"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/wishlist.js"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/order.js" />"></script>
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
                    
                    <div class="row">
                        <div class="col-md-7" id="order_book_part">
                            <div class="row" id="book">
                                <div class="col-md-6" id="order_book_img">
                                    <img class="img-responsive" src="${book.getImg()}">
                                </div>
                                <div class="col-md-6" id="order_book_info">
                                    <div class="row">
                                        ${book.getTitle()}
                                    </div>
                                    <div class="row">
                                        ${book.getAuthors()}
                                    </div>
                                    <div class="row">
                                        ${book.getPublisher()}
                                    </div>
                                    <div class="row" id="order_book_description">
                                        ${book.getDescription()}
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5" id="order_order_wish_part">
                            <div class="row" id="order_order_button_part">
                                <input type="hidden" id="minDate" value="${minDate}">
                                <input class="form-control" id="datetimepicker">
                                <input type="hidden" value="${book.getbId()}">
                                <c:choose>
                                    <c:when test="${inUse}">
                                        <button disabled="disabled" class="btn-info">Order</button> 
                                    </c:when>
                                    <c:otherwise>
                                        <button id="order_button"  class="btn-info">Order</button> 
                                    </c:otherwise>
</c:choose>
                                    
                            </div>
                            
                            <div class="row" id="order_wish_button_part">
                                <input type="hidden" value="${book.getbId()}">
                                <c:choose>
                                    <c:when test="${inUse}">
                                        <button disabled="disabled" class="btn-info">Add To WishList</button> 
                                    </c:when>
                                    <c:otherwise>
                                        <button id="wish_button"  class="btn-warning">Add To WishList</button> 
                                    </c:otherwise>
                                        </c:choose>
                            </div>
                               
                        </div>
                    </div>
                        
                    
                </div>
                
                
            </div>
            <c:import url="/WEB-INF/views/footer.jsp" />
        </div>
    </body>
</html>