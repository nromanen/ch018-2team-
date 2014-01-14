<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <div class="row" style="background-color: #F0F0F0;">
                
                <div class="col-md-2" id="left_main">
                    <!--New Arrivals-->
                </div>
                
                <div class="col-md-8" id="center_main">
                    <ul class="list-unstyled">
                        <li class="list-group-item">
                                <div class="row">
                                    <div class="col-md-4">
                                        Title
                                    </div>
                                    <div class="col-md-5">
                                        Available From
                                    </div>
                                    <div class="col-md-3">
                                        Delete
                                    </div>
                                </div>
                            </li>
                        <c:forEach var="entry" items="${map}">
                            
                            <li class="list-group-item" id="wish_li_${entry.key.id}">
                                <div class="row">
                                    <div class="col-md-4">
                                        ${entry.key.book.title}
                                    </div>
                                    <div class="col-md-5">
                                        <input type="hidden" class="wishId" value="${entry.key.id}">
                                        <input type="hidden" class="bookId" value="${entry.key.book.bId}">
                                        <input type="hidden" class="minDate" value="${entry.value.minOrderDate.time}">
                                        <input type="text" class="calendar">
                                        <button class="btn-info wish_confirm_button">Order</button>
                                        <div class="alert alert-danger wish_date_err hide"></div>
                                        <div class="picker_info">
                                            <c:forEach var="order" items="${entry.value.orders}">
                                                <div class="order" start="${order.orderDate.time}" days="${order.daysAmount}"></div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <input type="hidden" class="wishId" value="${entry.key.id}">
                                        <button class="btn-danger wish_delete_button">Delete</button>
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
       
