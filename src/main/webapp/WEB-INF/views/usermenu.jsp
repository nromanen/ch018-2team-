<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="user_menu">
    <sec:authorize access="isAuthenticated()">
    <ul>
        <li><button id="my_books">My Books</button></li>
        <li><button id="my_orders">Ordered</button></li>
        <li><button id="my_wishlist">WishList</button></li>
        <li><button id="my_account">Hello <sec:authentication property="principal.username" /></button> <a href="<c:url value="/j_spring_security_logout" />">logout</a></li>
    </ul>
    </sec:authorize>
</div>
