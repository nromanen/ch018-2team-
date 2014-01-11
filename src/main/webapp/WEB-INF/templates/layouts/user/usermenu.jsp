<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="col-md-5" id="user_menu">
    
    <sec:authorize access="isAuthenticated()">
        <div class="navbar nav-pills" role="navigation">
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/books" id="my_books"><spring:message code="messages.home" /></a></li>
                    <li><a  href="${pageContext.request.contextPath}/books/mybooks" id="my_books"><spring:message code="message.mybooks" /></a></li>
                    <li><a  href="${pageContext.request.contextPath}/books/order/my" id="my_orders"><spring:message code="message.orders" /></a></li>
                    <li><a href="${pageContext.request.contextPath}/books/wishlist/my" id="my_wishlist"><spring:message code="message.wishlist" /></a></li>
                    <li>
                        <div class="btn-toolbar" role="toolbar" style="margin-top: 15px;">
                        <div class="btn-group">
                            <a class="dropdown-toggle" type="button" data-toggle="dropdown">
                                 <spring:message code="message.hello" /> <sec:authentication property="principal.username" /> <span class="caret"></span></a>
                                 <ul class="dropdown-menu" role="menu">
                                     <li><a href="${pageContext.request.contextPath}/account" id="my_account">account</a></li>
                                     <li><a href="<c:url value="/j_spring_security_logout" />"><spring:message code="message.logout" /></a></li>
                                 </ul>
                            
                        </div>
                        </div>
                    </li>
                    
                    
                    <!--<li><a href="${pageContext.request.contextPath}/account" id="my_account"><spring:message code="message.hello" /> <sec:authentication property="principal.username" /></a> </li>
                    <li><a href="<c:url value="/j_spring_security_logout" />"><spring:message code="message.logout" /></a></li>-->
                </ul>
            </div>
        </div>
    </sec:authorize>
    
</div>
