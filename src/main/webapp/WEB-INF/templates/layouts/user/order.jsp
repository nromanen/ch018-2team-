<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <div class="row" style="background-color: #F0F0F0;">
                
                <div id="picker_date">
                    <div id="min_date" value="${minDate}"></div>
                    <div id="disabled_dates" value="${disabled}"></div>
                    <div id="allow_times"></div>
                </div>
                
                <div class="col-md-2" id="left_main">
                    <!--New Arrivals-->
                </div>
                
                <div class="col-md-8" id="center_main">
                    
                    <!--Modal Books Limit-->
                    <input id="book_limit" type="hidden" value="${isBookLimitReached}">
                    <div class="modal fade" id="book_limit_modal" tabindex="-1" role="dialog" aria-labelledby="book_limit_modal_label" aria-hidden="true">
                                        <div class="modal-dialog">
                                          <div class="modal-content">
                                            <div class="modal-header">
                                              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                              <h4 class="modal-title" id="obook_limit_modal_label">Limit Notification</h4>
                                            </div>
                                            <div class="modal-body">
                                              <h1>You've reached the limit of books</h1>
                                              <h3>Return books or delete some from orders</h3>
                                            </div>
                                            <div class="modal-footer">
                                           
                                              <button type="button" onclick="location.href = '/library/books/order/my'"class="btn btn-primary">View Orders</button>
                                            </div>
                                          </div>
                                        </div>
                                </div>
                    <!--Modal Books Limit-->
                    
                    
                    <div class="row">
                        <div class="col-md-7" id="order_book_part">
                            <div class="row" id="book">
                                <div class="col-md-6" id="order_book_img">
                                    <img class="img-responsive" src="${book.getImg()}">
                                </div>
                                <div class="col-md-6" id="order_book_info">
                                    <div class="row">
                                        ${book.getTitle()}
                                    </div>
                                    <div class="row">
                                        ${book.getAuthors()}
                                    </div>
                                    <div class="row">
                                        ${book.getPublisher()}
                                    </div>
                                    <div class="row" id="order_book_description">
                                        ${book.getDescription()}
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5" id="order_order_wish_part">
                            <div class="row" id="order_order_button_part">
                                <input type="hidden" id="minDate" value="${minDate}">
                                <input class="form-control" id="datetimepicker">
                                <input type="hidden" id="bookId" value="${book.getbId()}">
                                
                                <c:choose>
                                    <c:when test="${inOrders || inUse}">
                                        <button disabled="disabled" id="order_button" class="btn-info">Order</button> 
                                    </c:when>
                                    <c:otherwise>
                                        <button id="order_button" class="btn-info">Order</button> 
                                    </c:otherwise>
                                </c:choose>
                                
                                <div class="modal fade" id="order_modal" tabindex="-1" role="dialog" aria-labelledby="order_modal_label" aria-hidden="true">
                                        <div class="modal-dialog">
                                          <div class="modal-content">
                                            <div class="modal-header">
                                              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                              <h4 class="modal-title" id="order_modal_label">Order Notification</h4>
                                            </div>
                                            <div class="modal-body">
                                              <h1>Congratulations! You've Ordered Book!</h1>
                                            </div>
                                            <div class="modal-footer">
                                              <button type="button" onclick= "location.href = '/library/books'" class="btn btn-info" data-dismiss="modal">Return to books</button>
                                              <button type="button" onclick="location.href = '/library/books/order/my'"class="btn btn-primary">View Orders</button>
                                            </div>
                                          </div>
                                        </div>
                                </div>
                                    
                                        
                            </div>
                            
                            <div class="row" id="order_wish_button_part">
                                <input type="hidden" value="${book.getbId()}">
                                <c:choose>
                                    <c:when test="${ inUse || inWishList || inOrders}">
                                        <button disabled="disabled" id="wish_button"  class="btn-warning">Add To WishList</button> 
                                    </c:when>
                                    <c:otherwise>
                                        <button id="wish_button"  class="btn-warning">Add To WishList</button> 
                                    </c:otherwise>
                                        </c:choose>
                            </div>
                                <div class="row" id="book_orders">
                                    <ul class="list-group list-unstyled">
                                        <li class="list-group-item-heading dropdown">Book already ordered for dates</li>
                                        <c:forEach var="order" items="${orders}">
                                            <li class="list-group-item">
                                                <div class="tab-content text-warning">
                                                    ${order.getOrderDate()}
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            <div class="modal fade" id="wish_modal" tabindex="-1" role="dialog" aria-labelledby="wish_modal_label" aria-hidden="true">
                                        <div class="modal-dialog">
                                          <div class="modal-content">
                                            <div class="modal-header">
                                              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                              <h4 class="modal-title" id="wish_modal_label">Order Notification</h4>
                                            </div>
                                            <div class="modal-body">
                                              <h1>You've Added Book To WishList!</h1>
                                            </div>
                                            <div class="modal-footer">
                                              <button type="button" onclick= "location.href = '/library/books'" class="btn btn-info" data-dismiss="modal">Return to books</button>
                                              <button type="button" onclick="location.href = '/library/books/wishlist/my'"class="btn btn-primary">View WishList</button>
                                            </div>
                                          </div>
                                        </div>
                                </div>    
                        </div>
                    </div>
                        
                    
                </div>
                                <div class="col-md-2" id="left_main">
                    <!--New Arrivals-->
                </div>
                
                
            </div>
            
