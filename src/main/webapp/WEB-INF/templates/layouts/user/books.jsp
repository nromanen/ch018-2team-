<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

            <div class="row">
                
                <div class="col-md-1" id="left_main">
                    <!--New Arrivals-->
                    
                </div>
                
                <div class="col-md-10 book1" id="center_main">
                    <ul class="list-inline list-unstyled">
                        <c:forEach var="book" items="${books}">         
                            <li class="col-md-3" >
                                
                                <div class="thumbnail book1" style="height: 280px;" >
                                    <img src="${book.getImg()}" style="height: 180px;">
                                    <div class="caption">
                                        <h6>${book.getTitle()}</h6>
                                        <p>
                                                Quantity:
                                                Current: ${book.getCurrentQuantity()}
                                                General: ${book.getGeneralQuantity()}
                                        </p>
                                    </div>
                                </div>
                                
                                       
                                    <div class="thumbnail hide book1_ext" style="position: absolute; top: 0; z-index: 9999 ">
                                    <img data-src="${book.getImg()}" src="${book.getImg()}" style="width: 350px;">
                                    <div class="caption">
                                        <h6>${book.getTitle()}</h6>
                                        <p>
                                                Quantity:
                                                Current: ${book.getCurrentQuantity()}
                                                General: ${book.getGeneralQuantity()}
                                        </p>
                                        <p>
                                            Authors: ${book.getAuthors()}
                                        </p>
                                        <p>
                                            Publisher: ${book.getPublisher()}
                                        </p>
                                        <sec:authorize access="isAuthenticated()">
                                        <p>
                                            <a href="${pageContext.request.contextPath}/books/order?id=${book.getbId()}" class="btn btn-info"><spring:message code="message.order" /></a>
                                        </p>
                                        </sec:authorize>
                                    </div>
                            
                                    </div>
                            </li>
                            
                        </c:forEach>
                    </ul>
                </div>
                <div class="col-md-1" id="left_main">
                    <!--New Arrivals-->
                    
                </div>
                
            </div>
    

