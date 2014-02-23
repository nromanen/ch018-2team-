<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="row well">
	
	<sec:authorize access="isAuthenticated()">
	<div id="picker_date">
		<div id="min_date"></div>
		<div id="disabled_dates" value="${disabled}"></div>
		<div id="allow_times"></div>
		<div id="orders"></div>

	</div>
	</sec:authorize>
	
	<div id="path" url="${pageContext.request.contextPath}"></div>
	<div id="bid" value="${book.bId}"></div>
	<div id="pagination_info" page="${sessionScope['scopedTarget.searchParamsRate'].page}" pagesQuantity = "${sessionScope['scopedTarget.searchParamsRate'].pagesQuantity}"></div>

	<div class="col-md-12" id="center_main">

		<sec:authorize access="isAuthenticated()">
		<!--Modal Books Limit-->
		<input id="book_limit" type="hidden" value="${isBookLimitReached}">
		<div class="modal fade" id="book_limit_modal" tabindex="-1"
			role="dialog" aria-labelledby="book_limit_modal_label"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="obook_limit_modal_label">Limit
							Notification</h4>
					</div>
					<div class="modal-body">
						<h1><spring:message code="message.limit" /></h1>
						<h3><spring:message code="message.limitreturn" /></h3>
					</div>
					<div class="modal-footer">

						<button type="button"
							onclick="location.href = '${pageContext.request.contextPath}/books/order/my'"
							class="btn btn-primary"><spring:message code="message.vieworders" /></button>
					</div>
				</div>
			</div>
		</div>
		<!--Modal Books Limit-->
		</sec:authorize>

		<div class="row">
			<div class="col-md-7" id="order_book_part">
				<div class="row" id="book">
					<div class="col-md-6" id="order_book_img">
						<img class="img-responsive" src="${pageContext.request.contextPath}/${book.img}">
					</div>
					<div class="col-md-6" id="order_book_info">
						<div class="row">
							<div class="text-info">
								<b><spring:message code="message.libTitle" />: </b> ${book.title}
							</div>
						</div>
						<div class="row">
							<div class="text-info">
								<b><spring:message code="message.libAuthors" />: </b>${book.authors}
							</div>
						</div>
						<div class="row">
							<div class="text-info">
								<b><spring:message code="message.libPublisher" />: </b> ${book.publisher}
							</div>
						</div>
						<div class="row">
							<div class="text-info">
								<b><spring:message code="message.libGenre" />: </b> ${book.genre.description}
							</div>
						</div>
						<div class="row">
							<div class="text-info">
								<b><spring:message code="message.libPages" />: </b> ${book.pages}
							</div>
						</div>
						<div class="row">
							<div class="text-info">
								<b><spring:message code="message.libYear" />: </b> ${book.year}
							</div>
						</div>
						
						<div class="row" id="order_book_description_out">
							<div class="text-info" id="order_book_description">
								<b><spring:message code="message.libDescription" />: </b> ${book.description}
							</div>
						</div>
						
						<div class="row" id="book_rating">
							<div id="book_rate" class="raty" data-score="${book.rating}""></div>
							<div id="vote_area">(${book.votes} votes)</div>
						</div> 
					</div>
				</div>
				<sec:authorize access="isAuthenticated()">
					<div class="row" style="margin-top: 20px; " id="rate_area" rated="${rate.id}" score="${rate.score}" message="${rate.message}">
						<div class="col-md-8 ">
							<div class="panel panel-info" id="rate_panel">
	                    		<div class="panel-heading">
	                        		<div id="rate_area_title" class="panel-title">Leave comment about book</div>    	
	                    		</div>
	                    	<div class="panel-body">
	                    		<div class="form-group">
	                            	<form id="rate_form" role="form">
	                                	<div class="form-group">
	    									<div id="rate"></div>
	  									</div>
	                                	<input type="hidden" name="bookId" value="${book.bId}">
	                                	<div class="form-group">
	                                		<textarea id="rate_form_textarea" class="form-control" name="message" maxlength="250" style="resize: none;" ></textarea>
	                            		</div>
	                            		<button id="rate_form_submit" type="submit" class="btn btn-default">Submit</button>
	                            		
	                                	<div id="rate_err" class="alert alert-danger hide">
	                                	</div>
	                            	</form>
	                        	</div>
	                    	</div>
						</div>
					</div>
				</div>
				</sec:authorize>
				
			</div>
			
			<div class="col-md-5" id="order_order_wish_part">
				<div class="row" id="order_order_button_part">
					<sec:authorize access='hasRole("ROLE_ANONYMOUS")'>
						<div class="alert alert-info">
						For ordering please <a href="${pageContext.request.contextPath}/login">Log-In</a>
					</div>
					</sec:authorize>
					
					<sec:authorize access="isAuthenticated()">
			
						<c:choose>
							<c:when test="${days <= 0}">
								<div class="alert alert-danger col-lg-6"><spring:message code="message.bookunavailable" /></div>
							</c:when>
							<c:when test="${inUse}">
								<div class="alert alert-danger col-lg-6"><spring:message code="message.bookuse" /></div>
							</c:when>
							<c:when test="${inOrders}">
								<div class="alert alert-warning col-lg-6"><spring:message code="message.bookordered" /></div>
								<div class="clearfix"></div>
	
	
								<a href="${pageContext.request.contextPath}/books/order/my"
									class="btn btn-primary">View Orders</a>
							</c:when>
							<c:when test="${inWishList}">
								<!-- <input type="hidden" id="minDate" value="${minDate}">  -->
								
								<input class="form-control" id="datetimepicker" value="<spring:message code="message.available" />">
								
								<!--<input type="hidden" id="bookId" value="${book.getbId()}">-->
								<div class="alert alert-info col-lg-6"><spring:message code="message.bookinwish" /></div>
								<div class="clearfix"></div>
								<button id="order_button" class="btn btn-info">Order</button>
	
							</c:when>
							<c:otherwise>
								<!-- <input type="hidden" id="minDate" value="${minDate}">  -->
								<input class="form-control" id="datetimepicker" value="<spring:message code="message.available" />">
								<!--<input type="hidden" id="bookId" value="${book.getbId()}">-->
	
								<button id="order_button" class="btn btn-info btn-sm"><spring:message code="message.order" /></button>
	
								<button id="wish_button" class="btn btn-warning btn-sm"><spring:message code="message.addtowish" /></button>
							</c:otherwise>
				 	</c:choose>
					<div class="clearfix"></div>
					<div class="alert alert-danger hide" id="order_err"></div>

					<div class="modal fade" id="order_modal" tabindex="-1"
						role="dialog" aria-labelledby="order_modal_label"
						aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								
								<div class="modal-body">
									<h4><spring:message code="message.youordered" />: </h4>
									<h5>${book.getTitle()}</h5>
								</div>
								<div class="modal-footer">
									<button type="button"
										onclick="location.href = '${pageContext.request.contextPath}/books'"
										class="btn btn-info" data-dismiss="modal">Return to
										books</button>
									<button type="button"
										onclick="location.href = '${pageContext.request.contextPath}/books/order/my'"
										class="btn btn-primary"><spring:message code="message.vieworders" /></button>
								</div>
							</div>
						</div>
					</div>
				
				</sec:authorize>
				</div>
				
				<div class="row" style="margin-top: 40px;">
				
					<div class="panel panel-info">
	                    		<div class="panel-heading">
	                        		<div class="panel-title"><a id="view_comments" href="">View comments</a></div>    	
	                    		</div>
	                    	<div id="comments_panel_body" class="panel-body " style="display : none; height: 300px; overflow: scroll; overflow-x:hidden;">
	                    		<div id="comments_list_group" class="list-group">
  									
								</div>
	                    	</div>
						</div>
					
				</div>
					
				<div class="modal fade" id="wish_modal" tabindex="-1" role="dialog"
					aria-labelledby="wish_modal_label" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							
							<div class="modal-body">
								<h4><spring:message code="message.addwish" />:</h4>
								<h5>${book.getTitle()}</h5>
							</div>
							<div class="modal-footer">
								<button type="button" onclick="location.href = '${pageContext.request.contextPath}/books'"
									class="btn btn-info" data-dismiss="modal"><spring:message code="message.catalogue" /></button>
								<button type="button"
									onclick="location.href = '${pageContext.request.contextPath}/books/wishlist/my'"
									class="btn btn-primary"><spring:message code="message.viewwish" /></button>
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</div>
		
		<sec:authorize access="isAuthenticated()">
			
			<div class="row" id="recommended">
				<div class="text-info" style="margin-left: 20px;"><h4>Recommended</h4></div>
					<c:set var="i" value="1" scope="page" />
						<c:forEach var="book" items="${recommend}">
				
								
									<div class="col-md-2">
										<div class="item" id="i${book.bId}">
											<div class="item-image">
												<a
													href="${pageContext.request.contextPath}/books/order/${book.bId}">
													<img src="${pageContext.request.contextPath}/${fn:substring(book.img, 0, (fn:length(book.img) - 4))}.gif" alt="" class="img-responsive">
												</a>
						
											</div>
											<div class="item-details">
												<h6><a href="${pageContext.request.contextPath}/books/order/${book.bId}">${book.title}</a></h6>
												
											</div>
											<div class="clearfix"></div>
										</div>
									</div>
								
							
							<c:set var="i" value="${i + 1}" scope="page" />
						</c:forEach>
			</div>
			
			
			
			
	</sec:authorize>

	</div>

</div>

