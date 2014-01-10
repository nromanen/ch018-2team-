<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <div class="row">
                
                <div class="col-md-1" id="left_main">
                    <!--New Arrivals-->
                </div>
                
                <div class="col-md-10" id="center_main">
                    <ul class="list-unstyled">
                        <li class="list-group-item">
                                <div class="row">
                                    <div class="col-md-4">
                                        Title
                                    </div>
                                    <div class="col-md-5">
                                        Order Date
                                    </div>
                                    <div class="col-md-3">
                                        Delete
                                    </div>
                                </div>
                            </li>
                        <c:forEach var="entry" items="${ordersMinDates}">
                            
                            <li class="list-group-item" id="order_li_${entry.key.getId()}">
                                <div class="row">
                                    <div class="col-md-4">
                                        ${entry.key.getBook().getTitle()}
                                    </div>
                                    <div class="col-md-5">
                                        
                                        <input type="hidden" class="order_id" value="${entry.key.getId()}">
                                        <input type="hidden" class="minDate" value="${entry.value}">
                                        <input type="hidden" class="orderDate" value="${entry.key.getOrderDate().getTime()}">
                                        <input type="text" class="calendar">
                                        <button class="btn-info order_change_button">Change</button>
                                        <input class="changed" type="hidden" value="${entry.key.getChanged()}">
                                        
                                    </div>
                                    <div class="col-md-3">
                                        <input type="hidden" value="${entry.key.getId()}">
                                        <button class="btn-danger order_delete_button">Delete</button>
                                    </div>
                                </div>
                            </li>
                            
                        </c:forEach>
                        
                    </ul>
                    
                </div>
                
                
            </div>
            