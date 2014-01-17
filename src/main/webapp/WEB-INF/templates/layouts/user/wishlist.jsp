<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row well">

	<div class="col-md-2" id="left_main">
		<div class="pull-left text-info">
			<h3>WishList</h3>
		</div>
	</div>
	<div class="col-md-8" id="center_main">
		<ul class="list-unstyled">
			<li class="list-group-item">
				<div class="row">
					<div class="col-md-4">Title</div>
					<div class="col-md-5">Available From</div>
					<div class="col-md-3"></div>
				</div>
			</li>
			<c:forEach var="entry" items="${map}">

				<li class="list-group-item" id="wish_li_${entry.key.id}">
					<div class="row">
						<div class="col-md-4">${entry.key.book.title}</div>
						<div class="col-md-5">
							<input type="hidden" class="wishId" value="${entry.key.id}">
							<input type="hidden" class="bookId" value="${entry.key.book.bId}">
							<input type="hidden" class="minDate"
								value="${entry.value.minOrderDate.time}"> <input
								type="text" class="calendar">
							<button class="btn-info wish_confirm_button">Order</button>
							<div class="alert alert-danger wish_date_err hide"></div>
							<div class="picker_info">
								<c:forEach var="order" items="${entry.value.orders}">
									<div class="order" start="${order.orderDate.time}"
										days="${order.daysAmount}"></div>
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

	<!--Modal Empty-->

	<div class="modal fade" id="empty_wish_list" tabindex="-1"
		role="dialog" aria-labelledby="empty_wish_list_label"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="empty_wish_list_label">Empty List
						Notification</h4>
				</div>
				<div class="modal-body">
					<h2>Your WishList is empty yet</h2>
					<h3>Try search in our collection</h3>
				</div>
				<div class="modal-footer">

					<button type="button"
						onclick="location.href = '${pageContext.request.contextPath}//books'"
						class="btn btn-primary">View Books</button>
				</div>
			</div>
		</div>
	</div>
	<!--Modal Empty-->

</div>

