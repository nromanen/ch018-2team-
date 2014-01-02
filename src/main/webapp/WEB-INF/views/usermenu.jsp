<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="col-md-4 id="user_menu">
    
    <sec:authorize access="isAuthenticated()">
    <ul class="list-inline">
        <li><a href="<c:url value="/books/mybooks" />" id="my_books">My Books</a></li>
        <li><a href="<c:url value="/books/order/my" />" id="my_orders">Ordered</a></li>
        <li><a href="<c:url value="/books/wishlist/my" />" id="my_wishlist">WishList</a></li>
        <li><a href="<c:url value="/account" />" id="my_account">Hello <sec:authentication property="principal.username" /></a> <a href="<c:url value="/j_spring_security_logout" />">logout</a></li>
    </ul>
    </sec:authorize>
</div>
