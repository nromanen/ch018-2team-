<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
         <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
         <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin.js"></script>
        
    </head>
    <body>
    <div id="pagination_info" page="${sessionScope['scopedTarget.searchParamsPerson'].page}" pageSize="${sessionScope['scopedTarget.searchParamsPerson'].pageSize}" pagesQuantity="${sessionScope['scopedTarget.searchParamsPerson'].pagesQuantity}" orderField="${sessionScope['scopedTarget.searchParamsPerson'].orderField}" order="${sessionScope['scopedTarget.searchParamsPerson'].order}" 
			path="${pageContext.request.contextPath}" >
			
		</div>
		
    <div id="path" path="${pageContext.request.contextPath}"></div>
        <div class="container">
            <div class="row">
            	<div class="col-md-3">
				<div class="panel panel-info">
					<div class="panel-heading">
						<div class="panel-title">Admin Menu</div>
					</div>
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked" id="nav_bar">
							<li><a href="#users_tab" data-toggle="tab"
								class="btn btn-default">Users</a></li>
							<li><a href="#settings_tab" data-toggle="tab"
								class="btn btn-default">System Settings</a></li>
						</ul>
					</div>

				</div>
			</div>
                <div class="col-md-8">
                
                <div id="tab_content" class="tab-content">
				<div id="users_tab" class="tab-pane fade active in">
                <div class="panel panel-info">
							<div class="panel-heading">
								<div class="panel-title">Users</div>
							</div>
							<div class="panel-body">
							<div class="row">
								<div class="col-md-3 ">
			
									<label for="sortby"><spring:message code="message.sortby" />:</label>
									<select id="sortby" name="sortby" class="form-control input-sm">
										<option value="email" order="false"><spring:message code="message.libEMail" /> (A-Z)</option>
										<option value="email" order="true"><spring:message code="message.libEMail" /> (Z-A)</option>
									</select>
				
							</div>
								<div class="col-md-3">
								<label for="pageSize">Persons on page:</label> 
									<select id="pageSize" class="form-control input-sm" type="text">
											<option value="3">3</option>
											<option value="6">6</option>
											<option value="12">12</option>
											<option value="48">48</option>
									</select>
								</div>
								<div class="col-md-6">
									<form class="form-inline" action="${pageContext.request.contextPath}/admin" method="get">
										<div class="col-lg-8">
										<label for="search_input"></label> 
											<input id="search_input" name="email" class="form-control input-sm" type="text">${sessionScope['scopedTarget.searchParamsPerson'].email}
										</div>
										<div class="col-lg-4">
										<label for="search_input"></label> 
											<input class="form-control input-sm" type="submit" value="Search">
										</div>
										
									</form>
								</div>
								</div>
								
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
                      <div class="row">
								<div class="col-md-4 col-md-offset-4" >
									<ul class='pagination' style="margin-left: 20px;">
										<li  class=" first_page hide"><a href="#"><span>1</span></a></li>
										<li  class=" prev_page hide"><a href="#"><span> &laquo; </span></a></li>
										<li  class="current_page disabled hide"><a href="#"><span>${sessionScope['scopedTarget.searchParamsPerson'].page}<span></a></li>
										<li  class="next_page hide"><a href="#"><span> &raquo; </span></a></li>
										<li  class="last_page hide"><a href="#"><span>${sessionScope['scopedTarget.searchParamsPerson'].pagesQuantity}</span></a></li>
									</ul>
								</div>
	
							</div>
                    </div>
                    </div>
                    </div>
                    <div id="settings_tab" class="tab-pane fade">
						<div class="panel panel-info">
							<div class="panel-heading">
								<div class="panel-title">System Settings</div>
							</div>
							<div class="panel-body">
								<form role="form"
									action="${pageContext.request.contextPath}/admin/syssetings"
									method="post">
									<div class="checkbox">
										<label> 
										<c:choose>
												<c:when test="${switcher.switcher}">
													<input id="switcher" class="checkbox" type="checkbox" name="switcher"
														checked="checked"> Local search
                                                </c:when>
												<c:otherwise>
													<input id="switcher" class="checkbox" type="checkbox" name="switcher"
														> Local search
                                                </c:otherwise>
										</c:choose>
										<div class="clearfix"></div>
										<label> 
										<c:choose>
												<c:when test="${switcher.switcher}">
													<input id="recommendation" class="checkbox" type="checkbox" name="recommendation"
														checked="checked"> Recomendation for users enabled.
                                                </c:when>
												<c:otherwise>
													<input id="recommendation" class="checkbox" type="checkbox" name="recommendation"
														> Recomendation for users disabled
                                                </c:otherwise>
										</c:choose>

										</label>
									</div>


								</form>
							</div>

						</div>
					</div>
					</div>
                </div>   
                <div class="col-md-1">
                    <a href="${pageContext.request.contextPath}/j_spring_security_logout">logout</a>
                </div>
            </div>
            
        </div>
    </body>
</html>
