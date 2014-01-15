<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row" id="content_bg">
              <input type="hidden" id="search_key" value="">  
                <div class="col-md-2" id="left_main">
                    <div class="col-md-10">
                        <form>
                            <div class="text-center">Sort by</div>
                            <select class="form-control" name="sort">
                                <option selected="selected" value="title">Title</option>
                                <option value="authors">Authors</option>
                                <option value="year">Year</option>
                                <option value="publisher">Publisher</option>
                            </select> 
                            <div class="text-center">Books on page</div>
                            <select class="form-control"  name="items">
                                <option selected="selected" value="20">20</option>
                                <option value="30">30</option>
                                <option value="40">40</option>
                                <option value="50">50</option>
                            </select> 
                            <div class="row">
                            <div class="text-center">Year</div>
                            <div class="col-md-6">
                            <select class="form-control"  name="items">
                                <option selected="selected" value="0">start</option>
                                <c:forEach var="i" begin="1950" end="2013">
                                <option value="${i}">${i}</option>
                                </c:forEach>
                            </select> 
                            </div>
                            <div class="col-md-6">
                            <select class="form-control"  name="items">
                                <option selected="selected" value="0">end</option>
                                <c:forEach var="i" begin="1950" end="2013">
                                <option value="${i}">${i}</option>
                                </c:forEach>
                            </select> 
                            </div>
                            </div>
                            <button class="form-control btn btn-info" id="show_button" style="margin-top: 10px;">Show</button>
                           
                        </form>
                        <div id="ajax_info" class="hide">
                            <div id="query"></div>
                            <div id="booksOnPage" value="20"></div>
                            <div id="orderBy" value="title"></div>
                            <div id="order" value="false"></div>
                            <div id="currentPage" value="1"></div>
                            <div id="bookPages"></div>
                            <div id="bookYears"></div>
                        </div>
                    </div>
                    
                    
                </div>
                
                <div class="col-md-8 book1" id="center_main">
                    <c:choose>
                        <c:when test="${nothing}">
                            <h3><spring:message code="message.nothing" />  ${query}</h3> 
                        </c:when>
                    </c:choose>
                    <ul class="list-inline list-unstyled">
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
                    </ul>
                </div>
                <div class="col-md-2" id="left_main">
                    <!--New Arrivals-->
                    
                </div>
                
            </div>
    

