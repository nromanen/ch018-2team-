<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div class="row well">
	
	<div class="col-md-12">
	
	
	
	<div  class="well">
	<div class="text-info"><h3><spring:message code="message.popular" /></h3></div>
		<div id="ca-container_arrival" class="ca-container">
		<div class="ca-wrapper">
		<c:set var="i" value="1" scope="page" />
		<c:forEach var="book" items="${arrivals}">

				
					<div class="ca-item ca-item-${i} col-md-3">
						<div class="item" id="i${book.bId}">
							<div class="item-image">
								<a
									href="${pageContext.request.contextPath}/books/order/${book.bId}">
									<img src="${pageContext.request.contextPath}/${fn:substring(book.img, 0, (fn:length(book.img) - 4))}.gif" alt="" class="img-responsive">
								</a>
		
							</div>
							<div class="item-details">
								<h6>${book.title}</h6>
								
							</div>
							<hr>
							<div class="clearfix"></div>
		
							<c:choose>
								<c:when test="${book.currentQuantity <= 0}">
									<div class="item-quantity pull-left"
										style="background: red !important;"><spring:message code="message.quantity" />:
										${book.currentQuantity}</div>
								</c:when>
								<c:otherwise>
									<div class="item-quantity pull-left"><spring:message code="message.quantity" />:
										${book.currentQuantity}</div>
								</c:otherwise>
							</c:choose>
		
		
							<div class="pull-right">
								<a
									href="${pageContext.request.contextPath}/books/order/${book.bId}"
									class="btn btn-info btn-sm"><spring:message code="message.more" /></a>
		
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				
			
			<c:set var="i" value="${i + 1}" scope="page" />
	</c:forEach>
	</div>
	</div>
	</div>

	
	
	
	<div  class="well">
	<div class="text-info"><h3><spring:message code="message.popular" /></h3></div>
		<div id="ca-container" class="ca-container">
		<div class="ca-wrapper">
		<c:set var="i" value="1" scope="page" />
		<c:forEach var="book" items="${populars}">

				
					<div class="ca-item ca-item-${i} col-md-3">
						<div class="item" id="i${book.bId}">
							<div class="item-image">
								<a
									href="${pageContext.request.contextPath}/books/order/${book.bId}">
									<img src="${pageContext.request.contextPath}/${fn:substring(book.img, 0, (fn:length(book.img) - 4))}.gif" alt="" class="img-responsive">
								</a>
		
							</div>
							<div class="item-details">
								<h6>${book.title}</h6>
								
							</div>
							<hr>
							<div class="clearfix"></div>
		
							<c:choose>
								<c:when test="${book.currentQuantity <= 0}">
									<div class="item-quantity pull-left"
										style="background: red !important;"><spring:message code="message.quantity" />:
										${book.currentQuantity}</div>
								</c:when>
								<c:otherwise>
									<div class="item-quantity pull-left"><spring:message code="message.quantity" />:
										${book.currentQuantity}</div>
								</c:otherwise>
							</c:choose>
		
		
							<div class="pull-right">
								<a
									href="${pageContext.request.contextPath}/books/order/${book.bId}"
									class="btn btn-info btn-sm"><spring:message code="message.more" /></a>
		
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				
			
			<c:set var="i" value="${i + 1}" scope="page" />
	</c:forEach>
	</div>
	</div>
	</div>

</div>

</div>
