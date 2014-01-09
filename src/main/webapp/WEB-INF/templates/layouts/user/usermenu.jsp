<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="col-md-4" id="user_menu">
    
    <sec:authorize access="isAuthenticated()">
    <ul class="list-inline">
        <li><a href="${pageContext.request.contextPath}/books/mybooks" id="my_books"><spring:message code="message.mybooks" /></a></li>
        <li><a href="${pageContext.request.contextPath}/books/order/my" id="my_orders"><spring:message code="message.orders" /></a></li>
        <li><a href="${pageContext.request.contextPath}/books/wishlist/my" id="my_wishlist"><spring:message code="message.wishlist" /></a></li>
        <li><a href="${pageContext.request.contextPath}/account" id="my_account"><spring:message code="message.hello" /> <sec:authentication property="principal.username" /></a> <a href="<c:url value="/j_spring_security_logout" />"><spring:message code="message.logout" /></a></li>
    </ul>
    </sec:authorize>
    
</div>
