<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

	<div id="path" url="${pageContext.request.contextPath}"></div>
	
	<div id="pagination_info" page="${sessionScope['scopedTarget.searchParamsBook'].page}" pageSize="${sessionScope['scopedTarget.searchParamsBook'].pageSize}" pagesQuantity="${sessionScope['scopedTarget.searchParamsBook'].pagesQuantity}" orderField="${sessionScope['scopedTarget.searchParamsBook'].orderField}" order="${sessionScope['scopedTarget.searchParamsBook'].order}">
	</div>
	<div class="row">
		<div class="col-md-3 col-md-offset-5" style="margin-top:30px">
				
					<form class="form-inline" name="search" action="<c:url value="/librarian/books/search" />" method="GET" >
						  <div class="form-group">
						<input type="text" class="form-control  input-sm" id="" name="query" placeholder="Title or Author"/> 
						</div>
						<input type="hidden" name="generalQuery" value="true">
						<button type="submit" class="btn btn-default">
						  	<span class="glyphicon glyphicon-search"></span>
						</button>
						
						
					</form>
			</div>

	</div>


	<div class="row" >
		<table class="table table-hover table-striped table-bordered table-condensed">
			<tr>
				<td>
					Title <a href="${pageContext.request.contextPath}/librarian/books/search?orderField=title&order=false"><img src="${pageContext.request.contextPath}/resources/css/img/asc.png"></a>
						<a href="${pageContext.request.contextPath}/librarian/books/search?orderField=title&order=true"><img src="${pageContext.request.contextPath}/resources/css/img/desc.png"></a>
				</td>
				<td>
					Year <a href="${pageContext.request.contextPath}/librarian/books/search?orderField=year&order=false"><img src="${pageContext.request.contextPath}/resources/css/img/asc.png"></a>
						<a href="${pageContext.request.contextPath}/librarian/books/search?orderField=year&order=true"><img src="${pageContext.request.contextPath}/resources/css/img/desc.png"></a>
				</td>
				<td>
					Current QTY <a href="${pageContext.request.contextPath}/librarian/books/search?orderField=currentQuantity&order=false"><img src="${pageContext.request.contextPath}/resources/css/img/asc.png"></a>
						<a href="${pageContext.request.contextPath}/librarian/books/search?orderField=currentQuantity&order=true"><img src="${pageContext.request.contextPath}/resources/css/img/desc.png"></a>
				</td>
				<td>
					General QTY <a href="${pageContext.request.contextPath}/librarian/books/search?orderField=generalQuantity&order=false"><img src="${pageContext.request.contextPath}/resources/css/img/asc.png"></a>
						<a href="${pageContext.request.contextPath}/librarian/books/search?orderField=generalQuantity&order=true"><img src="${pageContext.request.contextPath}/resources/css/img/desc.png"></a>
				</td>
				<td>
					
				</td>
			</tr>
			<c:forEach var="book" items="${books}">
				<tr>
					<td>
						${book.title}
					</td>
					<td>
						${book.year}
					</td>
					<td>
						${book.currentQuantity}
					</td>
					<td>
						${book.generalQuantity}
					</td>
					<td>
						<div class="btn-group">
						  <button  type="button" class="actions btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" bid="${book.bId}">
						    Action <span class="caret"></span>
						  </button>
						  <ul class="dropdown-menu" role="menu">
						    <li><a href="${pageContext.request.contextPath}/librarian/books/holders?id=${book.bId}">Holders <span class="holders"></span></a></li>
						    <li><a href="${pageContext.request.contextPath}//librarian/orders/book?id=${book.bId}">Orders <span class="orders"></span></a></li>
						    <li><a href="${pageContext.request.contextPath}/librarian/books/editbook?id=${book.bId}">Edit</a></li>
						  </ul>
						</div>
					</td>
				</tr>
			</c:forEach>
			
		</table>
		<div class="col-md-3 col-md-offset-5">
		<ul class='pagination'>
			<li  class=" first_page hide"><a href="#"><span>1</span></a></li>
			<li  class=" prev_page hide"><a href="#"><span> &laquo; </span></a></li>
			<li  class="current_page disabled hide"><a href="#"><span>${sessionScope['scopedTarget.searchParamsBook'].page}</span></a></li>
			<li  class="next_page hide"><a href="#"><span> &raquo; </span></a></li>
			<li  class="last_page hide"><a href="#"><span>${sessionScope['scopedTarget.searchParamsBook'].pagesQuantity}</span></a></li>
		</ul>
	</div>
	</div>


        