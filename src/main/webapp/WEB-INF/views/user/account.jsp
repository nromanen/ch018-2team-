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
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/account.js"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/search.js" />"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/books.js"></script>
        

    </head>
    <body>
        
        <div class="container">
            <c:import url="/WEB-INF/views/user/mainheader.jsp" />
            <div class="row">
                
                <div class="col-md-1" id="left_main">
                    <!--New Arrivals-->
                </div>
                
                <div class="col-md-11" id="center_main">
                    <div class="row">
                        <div class="col-md-3" id="acc_img_graph_wrapper">
                            <div class="row" id="acc_img_wrapper">
                                <img class="img-thumbnail" src="${pageContext.request.contextPath}/resources/img/freebsd.jpg">
                            </div>
                            <div class="row">
                                <button class="btn-xs" id="acc_img_button">Change</button>
                            </div>
                            <div class="row" id="acc_graph_wrapper">
                                Area For Ratio Graph
                            </div>
                        </div>
                        <div class="col-md-9" id="acc_user_info_wrapper">
                             <ul class="list-unstyled">
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-md-3">Email</div>   
                                        <div class="col-md-6">
                                            <input id="email_edit" type="text" class="form-control hide" placeholder="${person.getEmail()}">
                                            <div id="email_text" class="text-info">${person.getEmail()}</div>
                                        </div>
                                        <div class="col-md-3">
                                            
                                            <button class="btn-xs" id="acc_email_button">Change</button>
                                            
                                        </div>
                                    </div>
                                </li>
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-md-3">Name</div>   
                                        <div class="col-md-6">${person.getName()}</div>
                                        <div class="col-md-3"><button class="btn-xs" id="acc_name_button">Change</button></div>
                                    </div>
                                </li>
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-md-3">Surname</div>   
                                        <div class="col-md-6">${person.getSurname()}</div>
                                        <div class="col-md-3"><button class="btn-xs" id="acc_surname_button">Change</button></div>
                                    </div>
                                </li>
                                
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-md-3">Phone</div>   
                                        <div class="col-md-6">${person.getCellphone()}</div>
                                        <div class="col-md-3"><button class="btn-xs" id="acc_phone_button">Change</button></div>
                                    </div>
                                </li>
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-md-3">Password</div>   
                                        <div class="col-md-9"><button class="btn-xs" id="acc_password_button">Change Password</button></div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                
                
            </div>
            <c:import url="/WEB-INF/views/user/footer.jsp" />
        </div>
    </body>
</html>
