<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row well">

	<div class="col-md-2" id="left_main">
		<div class="pull-left text-info">
			<h3><spring:message code="message.use" /></h3>
		</div>
	</div>

	<div class="col-md-8" id="center_main">

		<ul class="list-unstyled">


			<li class="list-group-item">
				<div class="row">
					<div class="col-md-5"><spring:message code="message.libTitle" /></div>
					<div class="col-md-4"><spring:message code="message.returndate" /></div>
					<div class="col-md-3"><spring:message code="message.daystoreturn" /></div>
				</div>
			</li>
			<c:forEach var="use" items="${uses}">
				<li class="list-group-item">
					<div class="row">
						<div class="col-md-5">${use.book.title}</div>
						<div class="col-md-4">
							<input class="mybooks_date" type="hidden"
								value="${use.returnDate.time}">

						</div>
						<div class="col-md-3"></div>
					</div>
				</li>

			</c:forEach>

		</ul>

	</div>
	<div class="col-md-2" id="left_main">
		<!--New Arrivals-->
	</div>

</div>

