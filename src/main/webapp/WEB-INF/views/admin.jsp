<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
         <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <ul class="list-group list-unstyled">
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="text-center">
                                        email
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="text-center">
                                        user_role
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="text-center">
                                        change role
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="text-center">
                                        delete
                                    </div>
                                </div>
                                
                                
                            </div>
                        </li>
                        <c:forEach var="person" items="${persons}">
                            <li id="person_li_${person.getPid()}" class="list-group-item">
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="text-center">
                                            ${person.getEmail()}
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="text-center user_role">
                                            ${person.getProle()}
                                        </div>
                                    </div>
                                        <div class="col-md-3">
                                        
                                            <select class="form-control col-md-5">
                                                
                                                <c:forEach var="role" items="${roles}">
                                                    <option value="${role}">${role}</option>
                                                </c:forEach>
                                            </select>  
                                            
                                            <button class="btn-warning  change_role" value="${person.getPid()}">Change</button> 
                                        
                                    </div>
                                    <div class="col-md-3">
                                        <div class="text-center">
                                            
                                            <button class="btn-danger" value="${person.getPid()}">Delete</button> 
                                        </div>
                                    </div>
                                    
                                
                            </div>
                        </li>
                        </c:forEach>
                    </ul>
                </div>   
                <div class="col-md-offset-10">
                    <a href="${pageContext.request.contextPath}/j_spring_security_logout">logout</a>
                </div>
            </div>
        </div>
    </body>
</html>
