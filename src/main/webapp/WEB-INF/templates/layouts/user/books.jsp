<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>

<tilesx:useAttribute name="genres" />

<div class="row">

	<div id="pagination_info" page="${page.searchParams.page}" pageSize="${page.searchParams.pageSize}" pagesQuantity="${page.pagesQuantity}" orderField="${page.searchParams.orderField}" order="${page.searchParams.order}" 
			path="${pageContext.request.contextPath}" genreId="${page.searchParams.genreId}" bookPageStart="${page.searchParams.bookPageStart}" bookPageEnd="${page.searchParams.bookPageEnd}"
			yearStart="${page.searchParams.yearStart}" yearEnd="${page.searchParams.yearEnd}" choosenBookPageStart = "${page.searchParams.choosenPageStart}"
			choosenBookPageEnd = "${page.searchParams.choosenPageEnd}" choosenYearStart = "${page.searchParams.choosenYearStart}"
			choosenYearEnd = "${page.searchParams.choosenYearEnd}">
			
		</div>

	<div class="col-md-2">

		<div class="row">
			<label for="query">Query:</label> 
				<input class="form-control input-sm" type="text" id="query" value="${page.searchParams.query}">
		</div>
		<div class="row">
			<label for="title">Title:</label> 
				<input class="form-control input-sm" type="text" id="title" value="${page.searchParams.title}">
		</div>
		<div class="row">
			<label for="authors">Authors:</label> 
				<input class="form-control input-sm" type="text" id="authors" value="${page.searchParams.authors}">
		</div>
		<div class="row">
			<label for="publisher">Publisher:</label> 
				<input class="form-control input-sm" type="text" id="publisher" value="${page.searchParams.publisher}">
		</div>
		<div class="row">
			<label for="genreId">Genre:</label> 
				<select id="genreSelect" class="form-control input-sm" type="text">
					<option value="0">All Genres</option>
					<c:forEach var="genre" items="${genres}">
						
						<option value="${genre.id}">${genre.description}</option>
					</c:forEach>
				</select>
		
		</div>
		<div class="row">
			<label for="pageSize">Page size:</label> 
				<select id="pageSize" class="form-control input-sm" type="text">
						<option value="8">8</option>
						<option value="12">12</option>
						<option value="24">24</option>
						<option value="36">36</option>
						<option value="48">48</option>
				</select>
		
		</div>
		<div class="row">
			<p>
				<label for="pages">Pages range:</label> 
				<input type="text"	id="pages" style="border: 0; color: #f6931f; font-weight: bold;">
			</p>

			<div id="pages-range"></div>
		</div>
		<div style="margin: 5px;"></div>
		<div class="row">
			<p>
				<label for="years">Years range:</label> <input type="text"
					id="years" style="border: 0; color: #f6931f; font-weight: bold;">
			</p>

			<div id="years-range"></div>
		</div>
		<div class="row" style="margin-top: 10px; ">
			
				<button id="searchPost"  class="btn btn-info btn-sm">Search</button>
			
		</div>
	</div>
	<div class="col-md-10">
	
		
	
		<div class="row">
			<div class="col-md-offset-1 ">
			<form class="form-inline">
				<label for="sortby">SortBy:</label>
				<select id="sortby" name="sortby" class="form-control input-sm">
					<option value="title" order="false">Title (A-Z)</option>
					<option value="title" order="true">Title (Z-A)</option>
					<option value="authors" order="false">Authors (A-Z)</option>
					<option value="authors" order="true">Authors (Z-A)</option>
					<option value="publisher" order="false">Publisher (A-Z)</option>
					<option value="publisher" order="true">Publisher (Z-A)</option>
					<option value="currentQuantity" order="false">Quantity (Low-High)</option>
					<option value="currentQuantity" order="true">Quantity (High-Low)</option>
				</select>
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
	<div class="col-md-4 col-md-offset-5">
		<ul class='pagination'>
			<li id="first_page" class="hide"><a href="#"><span>1</span></a></li>
			<li id="prev_page" class="hide"><a href="#"><span> &laquo; </span></a></li>
			<li id="current_page" class="disabled hide"><a href="#"><span>${page.searchParams.page}<span></a></li>
			<li id="next_page" class="hide"><a href="#"><span> &raquo; </span></a></li>
			<li id="last_page" class="hide"><a href="#"><span>${page.pagesQuantity}</span></a></li>
		</ul>
	</div>
</div>

