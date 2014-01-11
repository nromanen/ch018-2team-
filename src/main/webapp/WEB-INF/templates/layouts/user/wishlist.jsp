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
                                        Free From
                                    </div>
                                    <div class="col-md-3">
                                        Delete
                                    </div>
                                </div>
                            </li>
                        <c:forEach var="entry" items="${map}">
                            
                            <li class="list-group-item" id="wish_li_${entry.key.getId()}">
                                <div class="row">
                                    <div class="col-md-4">
                                        ${entry.key.getBook().getTitle()}
                                    </div>
                                    <div class="col-md-5">
                                        
                                        
                                        
                                        <input type="hidden" value="${entry.key.getId()}">
                                        <input type="hidden" value="${entry.key.getBook().getbId()}">
                                        <input type="hidden" value="${entry.value}">
                                        <input type="text" class="calendar">
                                        <button class="btn-info wish_confirm_button">Confirm</button>
                                    </div>
                                    <div class="col-md-3">
                                        <input type="hidden" value="${entry.key.getId()}">
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
       
