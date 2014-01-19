<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row">


	<div class="col-md-12">
		<c:choose>
			<c:when test="${nothing}">
				<h3>
					<spring:message code="message.nothing" />
					${query}
				</h3>
			</c:when>
		</c:choose>
		<c:set var="i" value="0" scope="page" />
		<c:forEach var="book" items="${books}">

			<c:if test="${i mod 4 == 0}">
				<div class="row" id="${i}">
			</c:if>
			<div class="col-md-3">
				<div class="item" id="i${i}">
					<div class="item-image">
						<a
							href="${pageContext.request.contextPath}/books/order?id=${book.bId}">
							<img src="${book.img}" alt="" class="img-responsive">
						</a>

					</div>
					<div class="item-details">
						<h5>${book.title}</h5>
						
					</div>
					<hr>
					<div class="clearfix"></div>

					<c:choose>
						<c:when test="${book.currentQuantity <= 0}">
							<div class="item-quantity pull-left"
								style="background: red !important;">Quantity:
								${book.currentQuantity}</div>
						</c:when>
						<c:otherwise>
							<div class="item-quantity pull-left">Quantity:
								${book.currentQuantity}</div>
						</c:otherwise>
					</c:choose>


					<div class="pull-right">
						<a
							href="${pageContext.request.contextPath}/books/order?id=${book.bId}"
							class="btn btn-info">More</a>

					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			<c:set var="i" value="${i + 1}" scope="page" />
			<c:if test="${i mod 4 == 0}">
				</div>
			</c:if>
	</c:forEach>

</div>

</div>


