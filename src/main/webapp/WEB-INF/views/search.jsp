<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="search">
    <form action="<c:url value="/search" />" method="get">
        <input type="text" name="search" />
        <input type="submit" value="Search" />
    </form>
</div>
        