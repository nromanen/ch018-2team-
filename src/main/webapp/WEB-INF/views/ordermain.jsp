<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="order_main">
    
    <div id="order_left">
        
            
           <div id="order_img">
                    <img src="${book.getImg()}">
            </div>
            <div id="order_title_wrapper">
                
            
                <div id="order_title">
                    <span>${book.getTitle()}
                    <span><b>by </b>${book.getAuthors()}
                    <span><b>Publisher: </b>${book.getPublisher()}
                </div>
            
                <div id="order_description">
                    <b>Description</b> <span>${book.getDescription()}
                </div>
                
            </div>
    </div>
    
        <div id="order_right">
            <div id ="order_order">
                Choose Order Date and Time
                <input type="text" id="datetimepicker">
                <input type="hidden" id="mindate" value="${minDate}">
                <input type="hidden" id="bookid" value="${book.getbId()}">
                <button onclick="sendR();">Order</a>
            
            </div>
            <div id ="order_wish">
                <a id="wish_button" href="<c:url value="/books/wish/add?bookid=${book.getbId()}" />">Wish</a>
            </div>
        </div>
        
    
</div>