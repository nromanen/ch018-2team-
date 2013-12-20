<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="home_main">
    <div id="left_main">
        New Arrivals
    </div>
    <div id="center_main">
        <div id="books">
            <c:forEach var="book" items="${books}">
            <div class="book">
                <div class="book_img">
                    <img src="${book.getImg()}"/>
                </div>
                <div class="book_info">
                    <span>${book.getTitle()} by ${book.getAuthors()}</span>
                </div>
                <div class="book_order">
                    <a class="button" href="<c:url value="/order?bookid=${book.getbId()}&personid=${person.getPid()}" />">Order</a>
                </div>
            </div>
            </c:forEach>
        </div>
        <div id="pages">
            
        </div>
    </div>
    <div id="right_main">
        Most ordered
    </div>
</div>