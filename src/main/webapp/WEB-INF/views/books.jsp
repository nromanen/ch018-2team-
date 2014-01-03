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
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js" />"></script>
        
        <script type="text/javascript" src="<c:url value="/resources/js/books.js" />"></script>
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
                    <ul class="list-inline">
                        <c:forEach var="book" items="${books}">
                            
                            <li>
                                <div class="book">
                                    <div class="book_img_wrapper">
                                        <a href="${pageContext.request.contextPath}/books/order?id=${book.getbId()}">
                                            <img src="${book.getImg()}">
                                        </a>

                                    </div>

                                    
                                        <div class="book_title">
                                            ${book.getTitle()}
                                        </div>
                                        <div class="book_quantity">
                                            <span><b>Quantity</b></span>
                                            <span>Current: ${book.getCurrentQuantity()}</span>
                                            <span>General: ${book.getGeneralQuantity()}</span>
                                        </div>
                                        
                                    </div>
                                
                                    <div class="book_ext">
                                        <div style="background-color: white">
                                    <div class="book_ext_img_wrapper">
                                        <a href="${pageContext.request.contextPath}/books/order?id=${book.getbId()}">
                                            <img src="${book.getImg()}">
                                        </a>

                                    </div>
                                        
                                    
                                        <div class="book_title">
                                            ${book.getTitle()}
                                        </div>
                                        <div class="book_title">
                                           Authors: ${book.getAuthors()}
                                        </div>
                                        <div class="book_title">
                                           Publisher: ${book.getPublisher()}
                                        </div>
                                        <div class="book_quantity">
                                            <span><b>Quantity</b></span>
                                            <span>Current: ${book.getCurrentQuantity()}</span>
                                            <span>General: ${book.getGeneralQuantity()}</span>
                                        </div>
                                        
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
