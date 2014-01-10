<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form"
prefix="form"%>
<html>
<head>
 <title>Test</title>
  <c:url value="/resources/css/bootstrap.min.css" var="bootstrapCSS" />  
  <link rel="stylesheet" type="text/css" media="screen" href="${bootstrapCSS}" />
  <c:url value="/resources/css/index.css" var="indexCSS" />  
  <link rel="stylesheet" type="text/css" media="screen" href="${indexCSS}" />
  <c:url value="/resources/css/bootstrap-theme.min.css" var="bootstrapTCSS" />  
  <link rel="stylesheet" type="text/css" media="screen" href="${bootstrapTCSS}" />
  
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index.js"></script>
</head>
<body>
    
    
        <div class="row"  id="index_head">
            <div class="col-md-7">
                <a class="btn btn-success" href="?lang=en">English</a>
                <a class="btn btn-success" href="?lang=ua">Українська</a>
            </div>
            
            <div class="col-md-5">
                <div class="row">
                    <form class="form-inline" role="form" action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
                        <div class="form-group">
                            <input  class="form-control" type="text" name="j_username" placeholder="Enter Email">
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="password" name="j_password" placeholder="Enter Password">
                        </div>


                        <button  type="submit" class="btn btn-primary"><spring:message code="message.singin" /></button>
                    </form>
                </div>
                        <div class="row">
                            
                                <label class="text-left">
                                    <input  type="checkbox" name="_spring_security_remember_me">
                                    <span><spring:message code="message.remember" />
                                    
                                </label>
                            
                        </div>
            </div>
        </div>
        <div class="row" id="index_body">
            <div class="col-md-8 row-padded">
                
            </div>
            <div class="col-md-4">
                <div class="row">
                    
                  
                    
                    
                    
                    <form id="registration_form" class="form-horizontal" role="form" >
                        <legend><spring:message code="message.registration" /></legend>
                        <div class="form-group col-lg-8">
                            <input id="reg_name" class="form-control" type="text" name="name" minlength="2" placeholder="<spring:message code="message.name" />">
                        </div>
                        <div class="form-group col-lg-8">
                            <input id="reg_surname" class="form-control" type="text" name="surname"  placeholder="<spring:message code="message.surname" />">
                        </div>
                        <div class="form-group col-lg-8">
                            <input id="reg_mail" class="form-control" type="email" name="email" required placeholder="Email">
                        </div>
                        <div class="form-group col-lg-8">
                            <input id="reg_rmail" class="form-control" equalto="#reg_mail" type="email" name="rEmail" required placeholder="<spring:message code="message.reenter" /> Email">
                        </div>
                        <div class="form-group col-lg-8">
                            <input id="reg_pass" class="form-control" type="password" name="password" required placeholder="Password">
                        </div>
                        <div class="form-group col-lg-8">
                            <input id="reg_rpass" class="form-control" equalto="#reg_pass" type="password" name="rPassword" required placeholder="<spring:message code="message.reenter" /> Password">
                        </div>
                        <div class="form-group col-lg-8">
                            <input id="reg_phone" class="form-control" type="text" name="cellPhone" required placeholder="<spring:message code="message.phone" />">
                        </div>
                        <div class="form-group col-lg-8">
                        <button id="form_submit" class="btn btn-success" type="submit" ><spring:message code="message.registration" /></button>
                        <div id="error_div" class="alert alert-danger hide">
                            
                        </div>
                        </div>
                        
                    </form>
                        
                     <!--Modal Books Limit-->
                    <input id="book_limit" type="hidden" value="${isBookLimitReached}">
                    <div class="modal fade" id="success_reg" tabindex="-1" role="dialog" aria-labelledby="succes_reg_label" aria-hidden="true">
                                        <div class="modal-dialog">
                                          <div class="modal-content">
                                            <div class="modal-header">
                                              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                              <h4 class="modal-title" id="success_reg_label">Limit Notification</h4>
                                            </div>
                                            <div class="modal-body">
                                              <h1>Congratulation!</h1>
                                              <h3>You successfully register!</h3>
                                              <h6>Please login</h6>
                                            </div>
                                            <div class="modal-footer">
                                           
                                                <div class="row">
                                                    <form class="form-inline" role="form" action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
                                                        <div class="form-group">
                                                            <input  class="form-control" type="text" name="j_username" placeholder="Enter Email">
                                                        </div>
                                                        <div class="form-group">
                                                            <input class="form-control" type="password" name="j_password" placeholder="Enter Password">
                                                        </div>
                            
                            
                                                        <button  type="submit" class="btn btn-primary"><spring:message code="message.singin" /></button>
                                                    </form>
                                                </div>
                                              
                                            </div>
                                          </div>
                                        </div>
                                </div>
                    <!--Modal Books Limit-->   
                      
                </div>
                        <div class="row">
                            <button id="view_books" class="btn btn-success"><spring:message code="message.books" /></button>
                        </div>
        
      
                
                        
                <div class="row">
                     
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-1">
            </div>
        </div>
  
    
</body>
</html>