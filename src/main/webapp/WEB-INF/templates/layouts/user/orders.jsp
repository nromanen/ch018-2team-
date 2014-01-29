<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row well">

	<div id="path" url="${pageContext.request.contextPath}"></div>

	<div class="col-md-2" id="left_main">
		<div class="pull-left text-info">
			<h3>Orders</h3>
		</div>
	</div>

	<div class="col-md-8" id="center_main">
		<ul class="list-unstyled">
			<li class="list-group-item">
				<div class="row">
					<div class="col-md-4">Title</div>
					<div class="col-md-5">Order Date</div>
					<div class="col-md-3"></div>
				</div>
			</li>
			<c:forEach var="entry" items="${ordersMinDates}">

				<li class="list-group-item" id="order_li_${entry.key.getId()}">
					<div class="row">
						<div class="col-md-4">${entry.key.getBook().getTitle()}</div>
						<div class="col-md-5">

							<input type="hidden" class="order_id"
								value="${entry.key.getId()}"> <input type="hidden"
								class="minDate" value="${entry.value.minOrderDate.time}">
							<input type="hidden" class="orderDate"
								value="${entry.key.orderDate.time}"> <input
								class="calendar">
							<button class="btn-info order_change_button" data-toggle="tooltip"  data-original-title="Date changed">Change</button>
							<input class="changed" type="hidden"
								value="${entry.key.getChanged()}">
							<div class="alert alert-danger order_date_err hide"></div>
							<div class="picker_info">
								<c:forEach var="order" items="${entry.value.orders}">
									<div class="order" start="${order.orderDate.time}"
										days="${order.daysAmount}"></div>
								</c:forEach>
							</div>

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
	<div class="col-md-2" id="left_main">
		<!--New Arrivals-->
	</div>

</div>

<!--Modal Empty-->

<div class="modal fade" id="empty_orders_list" tabindex="-1"
	role="dialog" aria-labelledby="empty_orders_list_label"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="empty_orders_list_label">Empty List
					Notification</h4>
			</div>
			<div class="modal-body">
				<h2>You don't have any order</h2>
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