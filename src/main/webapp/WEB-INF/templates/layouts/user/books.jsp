<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <div class="row">
                
                <div class="col-md-1" id="left_main">
                    <!--New Arrivals-->
                </div>
                
                <div class="col-md-11" id="center_main">
                    <ul class="list-inline">
                        <c:forEach var="book" items="${books}">
                            
                            <li>
                                <div class="book">
                                    <div class="book_img_wrapper">
                                     
                                        <a href="${pageContext.request.contextPath}/books/order?id=${book.getbId()}">
                                            <img src="${book.getImg()}">
                                        </a>

                                    </div>

                                    
                                        <div class="book_title">
                                            ${book.getTitle()}
                                        </div>
                                        <div class="book_quantity">
                                            <span><b>Quantity</b></span>
                                            <span>Current: ${book.getCurrentQuantity()}</span>
                                            <span>General: ${book.getGeneralQuantity()}</span>
                                        </div>
                                        
                                    </div>
                                
                                    <div class="book_ext">
                                        <div style="background-color: white">
                                    <div class="book_ext_img_wrapper">
                                        <a href="${pageContext.request.contextPath}/books/order?id=${book.getbId()}">
                                            <img src="${book.getImg()}">
                                        </a>

                                    </div>
                                        
                                    
                                        <div class="book_title">
                                            ${book.getTitle()}
                                        </div>
                                        <div class="book_title">
                                           Authors: ${book.getAuthors()}
                                        </div>
                                        <div class="book_title">
                                           Publisher: ${book.getPublisher()}
                                        </div>
                                        <div class="book_quantity">
                                            <span><b>Quantity</b></span>
                                            <span>Current: ${book.getCurrentQuantity()}</span>
                                            <span>General: ${book.getGeneralQuantity()}</span>
                                        </div>
                                        
                                    </div>
                                </div>
                            </li>
                            
                        </c:forEach>
                        
                    </ul>
                    
                </div>
                
                
            </div>
    

