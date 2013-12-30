<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <c:url value="/resources/css/bootstrap.min.css" var="bootstrapCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${bootstrapCSS}" />
        <!--<c:url value="/resources/css/main.css" var="mainCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${mainCSS}" />-->
        <c:url value="/resources/css/books.css" var="booksCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${booksCSS}" /> 
        <c:url value="/resources/css/jquery.datetimepicker.css" var="dateCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${dateCSS}" /> 
        <c:url value="/resources/css/order.css" var="orderCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${orderCSS}" /> 
        <c:url value="/resources/css/search.css" var="searchCSS" />  
        <link rel="stylesheet" type="text/css" media="screen" href="${searchCSS}" /> 
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.datetimepicker.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/order.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/wish.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/mybooks.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/books.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/search.js" />"></script>

    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-8" id="search">
                    <div class="row">
                        <div class="col-md-10">
                            <input class="form-control" id="search_field" type="text" name="search" onkeydown="if (window.event.keyCode == 13)
                                        search($(this).val())"/>
                        </div>
                        <div class="col-md-2">
                            <button class="btn-info" onclick="search($('#search_field').val());">search</button>
                        </div>
                    </div>
                    <div class="row" id="advanced_search" >
                        <button id="advanced_search_button" >Advanced search</button> 
                        <div id="advanced_search_panel" style="display: none">
                            <p>
                                <input id="advanced_search_title" type="text">
                            <p>
                                <input id="advanced_search_authors" type="text">
                            <p>
                                <input id="advanced_search_publisher" type="text">
                            <p>
                                <select id="advanced_search_select">

                                </select>    
                            <p>
                                <button id="advanced_search_submit">search</button>    
                        </div>


                    </div>


                </div>

                <c:import url="/WEB-INF/views/usermenu.jsp" />

            </div>
            <div class="row">
                <div class="col-md-2" id="left_main">
                    New Arrivals
                </div>
                <div class="col-md-8" id="center_main">

                </div>
                <div class="col-md-2" id="right_main">
                    Most ordered
                </div>
            </div>
            <div class="row">
            </div>
        </div>
    </body>
</html>
