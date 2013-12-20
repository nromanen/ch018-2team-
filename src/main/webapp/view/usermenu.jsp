<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="user_menu">
    <ul>
        <li><a href="<c:url value="/mybooks" />">My Books</li>
        <li><a href="<c:url value="/ordered" />">Ordered</li>
        <li><a href="<c:url value="/wish" />">WishList</li>
        <li><a href="<c:url value="/account" />">Hello ${person.getName()}</li>
    </ul>
</div>
