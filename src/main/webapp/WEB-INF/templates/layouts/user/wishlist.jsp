<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row well">
	
	<div id="path" url="${pageContext.request.contextPath}"></div>

	<div id="picker_info">
		<div id="orders"></div>
		<div id="min_date"></div>
	</div>
	<div class="col-md-2" id="left_main">
		<div class="pull-left text-info">
			<h3><spring:message code="message.wishlistI" /></h3>
		</div>
	</div>
	<div class="col-md-8" id="center_main">
		<ul class="list-unstyled">
			<li class="list-group-item">
				<div class="row">
					<div class="col-md-4"><spring:message code="message.libTitle" /></div>
					<div class="col-md-5"><spring:message code="message.available" /></div>
					<div class="col-md-3"></div>
				</div>
			</li>
			<c:forEach var="wish" items="${wishes}">

				<li class="list-group-item" id="wish_li_${wish.id}">
					<div class="row">
						<div id="book_title" class="col-md-4" style="max-width: 90%; overflow:  hidden;">${wish.book.title}</div>
						<div class="col-md-5">
							<input type="hidden" class="wishId" value="${wish.id}">
							<input type="hidden" class="bookId" value="${wish.book.bId}">
							<input	type="text" class="calendar" value="<spring:message code="message.available" />">
							<button class="btn-info wish_confirm_button"><spring:message code="message.order" /></button>
							<div class="alert alert-danger wish_date_err hide"></div>
						</div>
						<div class="col-md-3">
							<input type="hidden" class="wishId" value="${wish.id}">
							<button class="btn-danger wish_delete_button"><spring:message code="message.libDelete" /></button>
							<div class="alert alert-danger wish_delete_err hide"></div>
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
					<h3><spring:message code="message.wishempty" /></h3>
					<h4><spring:message code="message.try" /> <a href="${pageContext.request.contextPath}/books/search?generalQuery=true&page=1"><spring:message code="message.catalogue" /></a></h4>
				</div>
				<div class="modal-footer">

					<button type="button"
						onclick="location.href = '${pageContext.request.contextPath}/books/search?generalQuery=true&page=1'"
						class="btn btn-primary"><spring:message code="message.catalogue" /></button>
				</div>
			</div>
		</div>
	</div>
	<!--Modal Empty-->
	
	<!--Modal Delete-->

	<div class="modal fade" id="delete_wish_list" tabindex="-1"
		role="dialog" aria-labelledby="delete_wish_list_label"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="delete_wish_list_label">Delete Wish
						Notification</h4>
				</div>
				<div class="modal-body">
					<h3>Delete <b id="delete_title"></b> from wishlist?</h3>
				</div>
				<div class="modal-footer">

					<button id="delete_final" type="button" class="btn btn-danger">Delete</button>
					<button id="return" type="button" class="btn btn-primary">Return to list</button>
				</div>
			</div>
		</div>
	</div>
	<!--Modal Delete-->

</div>

