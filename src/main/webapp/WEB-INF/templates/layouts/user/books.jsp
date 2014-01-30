<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<tilesx:useAttribute name="genres" />

<div class="row well">

	<div id="pagination_info" page="${page.searchParams.page}" pageSize="${page.searchParams.pageSize}" pagesQuantity="${page.pagesQuantity}" orderField="${page.searchParams.orderField}" order="${page.searchParams.order}" 
			path="${pageContext.request.contextPath}" genreId="${page.searchParams.genreId}" bookPageStart="${page.searchParams.bookPageStart}" bookPageEnd="${page.searchParams.bookPageEnd}"
			yearStart="${page.searchParams.yearStart}" yearEnd="${page.searchParams.yearEnd}" choosenBookPageStart = "${page.searchParams.choosenPageStart}"
			choosenBookPageEnd = "${page.searchParams.choosenPageEnd}" choosenYearStart = "${page.searchParams.choosenYearStart}"
			choosenYearEnd = "${page.searchParams.choosenYearEnd}">
			
		</div>

	<div class="col-md-2"  id="search_panel">

		<h5>Parameterized search</h5>
		
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
						
						<option value="${genre.genreId}">${genre.genreTranslation}</option>
					</c:forEach>
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
			<div class="col-md-2 ">
			
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
				
			</div>
			<div class="col-md-2">
			<label for="pageSize">Page size:</label> 
				<select id="pageSize" class="form-control input-sm" type="text">
						<option value="8">8</option>
						<option value="12">12</option>
						<option value="24">24</option>
						<option value="36">36</option>
						<option value="48">48</option>
				</select>
			</div>
			
	<div class="col-md-3 col-md-offset-1">
		<ul class='pagination'>
			<li  class=" first_page hide"><a href="#"><span>1</span></a></li>
			<li  class=" prev_page hide"><a href="#"><span> &laquo; </span></a></li>
			<li  class="current_page disabled hide"><a href="#"><span>${page.searchParams.page}<span></a></li>
			<li  class="next_page hide"><a href="#"><span> &raquo; </span></a></li>
			<li  class="last_page hide"><a href="#"><span>${page.pagesQuantity}</span></a></li>
		</ul>
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
		
	<div>
	
	
		<c:set var="i" value="1" scope="page" />
		<c:forEach var="book" items="${page.books}">

			<c:if test="${i mod 4 == 0}">
				<div class="row" id="${i}">
			</c:if>
			<div class="col-md-3">
				<div class="item" id="i${i}">
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
							href="${pageContext.request.contextPath}/books/order/${book.bId}"
							class="btn btn-info btn-sm">More</a>

					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			
			<c:if test="${i mod 4 == 0}">
				</div>
			</c:if>
			<c:set var="i" value="${i + 1}" scope="page" />
			
	</c:forEach>
	
	
	</div>

</div>




<div class="row">
	<div class="col-md-3 col-md-offset-6" >
		<ul class='pagination' style="margin-left: 20px;">
			<li  class=" first_page hide"><a href="#"><span>1</span></a></li>
			<li  class=" prev_page hide"><a href="#"><span> &laquo; </span></a></li>
			<li  class="current_page disabled hide"><a href="#"><span>${page.searchParams.page}<span></a></li>
			<li  class="next_page hide"><a href="#"><span> &raquo; </span></a></li>
			<li  class="last_page hide"><a href="#"><span>${page.pagesQuantity}</span></a></li>
		</ul>
	</div>
	
</div>
</div>
