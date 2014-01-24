<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row">


	<div class="col-md-12">
	
		<div id="pagination_info" page="${page.searchParams.page}" generalPages="${page.pagesQuantity}" query="${page.searchQuery.query}"
			size="${page.searchParams.pageSize}" orderField="${page.searchParams.orderField}" order="${page.searchParams.order}" 
			path="${pageContext.request.contextPath}" >
			
		</div>
	
		<div class="row">
			<div class="col-md-offset-2 ">
			<form class="form-inline">
				<select class="form-control" id="sort_field">
					<option selected="selected" value="title">Sort by</option>
					<option value="title">Title</option>
					<option value="authors">Authors</option>
					<option value="publisher">Publisher</option>
					<option value="currentQuantity">Quantity</option>
				</select>
				<select class="form-control" id="sort_order">
					<option selected="selected" value="false">Sort order</option>
					<option value="false">Ascending</option>
					<option value="true">Descending</option>
				</select>
				<select class="form-control" id="page_size">
					<option selected="selected" value="2">Books on page</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select>
				Year
				<c:choose>
					<c:when test="${page.searchParams.yearStart == null}">
						<input class="form-control" type="text" id="year_start" placeholder="from" style="width: 80px;">
					</c:when>
					<c:otherwise>
						<input class="form-control" type="text" id="year_start" placeholder="from" style="width: 80px;" value="${page.searchParams.yearStart}">
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${page.searchParams.yearEnd == null}">
						<input class="form-control" type="text" id="year_end" placeholder="from" style="width: 80px;">
					</c:when>
					<c:otherwise>
						<input class="form-control" type="text" id="year_end" placeholder="from" style="width: 80px;" value="${page.searchParams.yearEnd}">
					</c:otherwise>
				</c:choose>
				Pages
				<c:choose>
					<c:when test="${page.searchParams.bookPageStart == null}">
						<input class="form-control" type="text" id="page_start" placeholder="from" style="width: 80px;">
					</c:when>
					<c:otherwise>
						<input class="form-control" type="text" id="page_start" placeholder="from" style="width: 80px;" value="${page.searchParams.bookPageStart}">
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${page.searchParams.bookPageEnd == null}">
						<input class="form-control" type="text" id="page_end" placeholder="from" style="width: 80px;">
					</c:when>
					<c:otherwise>
						<input class="form-control" type="text" id="page_end" placeholder="from" style="width: 80px;" value="${page.searchParams.bookPageEnd}">
					</c:otherwise>
				</c:choose>
				
				<button class="btn btn-info btn-sm" id="choose_button">Show</button>
				</form>
			</div>
		</div>
	
		<c:choose>
			<c:when test="${nothing}">
				<h3>
					<spring:message code="message.nothing" />
					${query}
				</h3>
			</c:when>
		</c:choose>
		
		<c:set var="i" value="0" scope="page" />
		<c:forEach var="book" items="${page.books}">

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

<div class="row">
	<div class="col-md-3 col-md-offset-4">
		<ul class='pagination'>
			<li id="first_page" class="hide"><span>1</span></li>
			<li id="prev_page" class="hide"><span> << </span></li>
			<li id="current_page" class="disabled hide"><span>${page.searchParams.page}<span></li>
			<li id="next_page" class="hide"><span> >> </span></li>
			<li id="last_page" class="hide"><span>${page.pagesQuantity}</span></li>
		</ul>
	</div>
</div>

