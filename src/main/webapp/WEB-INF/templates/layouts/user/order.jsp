<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="row well">
	
	<div id="path" url="${pageContext.request.contextPath}"></div>

	<sec:authorize access="isAuthenticated()">
	<div id="picker_date">
		<div id="min_date" value="${minDate}"></div>
		<div id="disabled_dates" value="${disabled}"></div>
		<div id="allow_times"></div>
		<c:forEach var="order" items="${orders}">
			<div class="order" start="${order.orderDate.getTime()}"
				days="${order.daysAmount}"></div>
		</c:forEach>

	</div>
	</sec:authorize>

	<!--<div class="col-md-2" id="left_main">
                    New Arrivals
                </div>-->

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
						<h1>You've reached the limit of books</h1>
						<h3>Return books or delete some from orders</h3>
					</div>
					<div class="modal-footer">

						<button type="button"
							onclick="location.href = '/library/books/order/my'"
							class="btn btn-primary">View Orders</button>
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
								<b>Title: </b> ${book.getTitle()}
							</div>
						</div>
						<div class="row">
							<div class="text-info">
								<b>Authors: </b>${book.getAuthors()}
							</div>
						</div>
						<div class="row">
							<div class="text-info">
								<b>Publisher: </b> ${book.getPublisher()}
							</div>
						</div>
						<div class="row" id="order_book_description">
							<div class="text-info">
								<b>Description: </b> ${book.getDescription()}
							</div>
						</div>
					</div>
				</div>
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
								<div class="alert alert-danger col-lg-6">Book  temporarily unavailable</div>
							</c:when>
							<c:when test="${inUse}">
								<div class="alert alert-danger col-lg-6">You already use
									that book</div>
							</c:when>
							<c:when test="${inOrders}">
								<div class="alert alert-warning col-lg-6">You already
									ordered that book</div>
								<div class="clearfix"></div>
	
	
								<a href="${pageContext.request.contextPath}/books/order/my"
									class="btn btn-primary">View Orders</a>
							</c:when>
							<c:when test="${inWishList}">
								<input type="hidden" id="minDate" value="${minDate}">
								
								<input class="form-control" id="datetimepicker">
								
								<input type="hidden" id="bookId" value="${book.getbId()}">
								<div class="alert alert-info col-lg-6">Already in WishList</div>
								<div class="clearfix"></div>
								<button id="order_button" class="btn btn-info">Order</button>
	
							</c:when>
							<c:otherwise>
								<input type="hidden" id="minDate" value="${minDate}">
								<input class="form-control" id="datetimepicker">
								<input type="hidden" id="bookId" value="${book.getbId()}">
	
								<button id="order_button" class="btn btn-info btn-sm">Order</button>
	
								<button id="wish_button" class="btn btn-warning btn-sm">Add To
									WishList</button>
							</c:otherwise>
				 	</c:choose>
					<div class="clearfix"></div>
					<div class="alert alert-danger hide" id="order_err"></div>

					<div class="modal fade" id="order_modal" tabindex="-1"
						role="dialog" aria-labelledby="order_modal_label"
						aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="order_modal_label">Order
										Notification</h4>
								</div>
								<div class="modal-body">
									<h3>Congratulations! You've Ordered: </h3>
									<h5>${book.getTitle()}</h5>
								</div>
								<div class="modal-footer">
									<button type="button"
										onclick="location.href = '/library/books'"
										class="btn btn-info" data-dismiss="modal">Return to
										books</button>
									<button type="button"
										onclick="location.href = '/library/books/order/my'"
										class="btn btn-primary">View Orders</button>
								</div>
							</div>
						</div>
					</div>

				</sec:authorize>
				</div>
				
					
				<div class="modal fade" id="wish_modal" tabindex="-1" role="dialog"
					aria-labelledby="wish_modal_label" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="wish_modal_label">Order
									Notification</h4>
							</div>
							<div class="modal-body">
								<h3>You've Added To WishList:</h3>
								<h5>${book.getTitle()}</h5>
							</div>
							<div class="modal-footer">
								<button type="button" onclick="location.href = '/library/books'"
									class="btn btn-info" data-dismiss="modal">Return to
									books</button>
								<button type="button"
									onclick="location.href = '/library/books/wishlist/my'"
									class="btn btn-primary">View WishList</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</div>


	</div>

</div>

