
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="login">
    <form action="<c:url value="/login" />" method="post">
        <input type="text" name="email" value="Email">
        <input type="password" name="password" value="Enter Password">
        <input type="submit" value="SingIn">
    </form>
</div>
    