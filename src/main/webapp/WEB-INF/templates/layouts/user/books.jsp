<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="row">
             
                
                <div class="col-md-12" id="center_main">
                    <c:choose>
                        <c:when test="${nothing}">
                            <h3><spring:message code="message.nothing" />  ${query}</h3> 
                        </c:when>
                    </c:choose>
                            <c:set var="i" value="0" scope="page" />
                            <c:forEach var="book" items="${books}">
                                
                                    <c:if test="${i mod 4 == 0}">
                                        <div class="row" id="${i}">
                                        </c:if>
                                    <div class="col-md-3">
                                        <div class="item" id="i${i}">
                                            <div class="item-image">
                                                <sec:authorize access="isAuthenticated()">
                                                <a href="${pageContext.request.contextPath}/books/order?id=${book.bId}">
                                                </sec:authorize>
                                                    <img src="${book.img}" alt="" class="img-responsive">
                                                    <sec:authorize access="isAuthenticated()">
                                                </a>
                                                    </sec:authorize>
                                            </div>
                                            <div class="item-details">
                                                <h5>${book.title}</h5>
                                            </div>
                                            <hr>
                                            <div class="clearfix"></div>
                                            <div class="item-quantity pull-left">
                                                Quantity: ${book.currentQuantity}
                                            </div>
                                            
                                            <div class="pull-right">
                                                <sec:authorize access="isAuthenticated()">
                                                <a href="${pageContext.request.contextPath}/books/order?id=${book.bId}" class="btn btn-info">More</a>
                                                </sec:authorize>
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>  
                                            <c:set var="i" value="${i + 1}" scope="page"/>
                                    <c:if test="${i mod 4 == 0}">
                                        </div>
                                        </c:if>
                                    </c:forEach>

                            
                            
                            
                            
                            
                    <!--<ul class="list-inline list-unstyled">
                        <c:forEach var="book" items="${books}">         
                            <li class="col-md-3" >
                                
                                <div class="thumbnail book1" style="height: 280px;" >
                                    <img src="${book.getImg()}" style="height:  180px;">
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
                                    <img data-src="${book.getImg()}" src="${book.getImg()}" style="width: 300px;">
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
                    </ul>-->
                </div>
                
                
            </div>
    

