<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="user_menu">
    <sec:authorize access="isAuthenticated()">
    <ul>
        <li><a href="<c:url value="/books/mybooks" />">My Books</li>
        <li><a href="<c:url value="/books/ordered" />">Ordered</li>
        <li><a href="<c:url value="/books/wish" />">WishList</li>
        <li><a href="<c:url value="/account" />">Hello <sec:authentication property="principal.username" /></a> <a href="<c:url value="/j_spring_security_logout" />">logout</a></li>
    </ul>
    </sec:authorize>
</div>
