<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <div id="center_main">
        <div id="books">
           <!-- <c:forEach var="book" items="${books}">
            <div class="book">
                <div class="book_img">
                    <img src="${book.getImg()}"/>
                </div>
                <div class="book_info">
                    <span>${book.getTitle()}</span>
                    <b>by</b> ${book.getAuthors()}
                </div>
                <div class="book_quantity">
                    
                </div>
                <sec:authorize access="isAuthenticated()">
                <div class="book_order">
                    <a class="button" href="<c:url value="/books/order?bookid=${book.getbId()}" />">Order</a>
                </div>
                </sec:authorize>
            </div>
            </c:forEach>-->
            
        </div>
        <div id="pages">
            
        </div>
    </div>
