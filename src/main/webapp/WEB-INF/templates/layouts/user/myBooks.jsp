<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <div class="row" style="background-color: #F0F0F0;">
                
                <div class="col-md-2" id="left_main">
                    <!--New Arrivals-->
                </div>
                
                <div class="col-md-8" id="center_main">
                    <ul class="list-unstyled">
                       
                       
                            <li class="list-group-item">
                                <div class="row">
                                    <div class="col-md-5">
                                        Title
                                    </div>
                                    <div class="col-md-4">
                                        Return Date
                                    </div>
                                    <div class="col-md-3">
                                        Days To Return
                                    </div>
                                </div>
                            </li>
                            <c:forEach var="use" items="${uses}">
                            <li class="list-group-item">
                                <div class="row">
                                    <div class="col-md-5">
                                        ${use.getBook().getTitle()}
                                    </div>
                                    <div class="col-md-4">
                                        <input class="mybooks_date" type="hidden" value="${use.getReturnDate().getTime()}">
                                        
                                    </div>
                                    <div class="col-md-3">
                                        
                                        
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
          
