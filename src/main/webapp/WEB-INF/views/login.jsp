
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="login">
    <form action="<c:url value="/j_spring_security_check" />" method="post">
        <input type="text" name="j_username" value="Email">
        <input type="password" name="j_password" value="Enter Password">
        <input type="submit" value="SingIn">
    </form>
</div>
    