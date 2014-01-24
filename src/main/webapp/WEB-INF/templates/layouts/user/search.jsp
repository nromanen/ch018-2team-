<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<tilesx:useAttribute name="genres" />

<div class="col-lg-6">
	<div style="margin: 0 0 0 -30px;">

		<ul class="navbar-form navbar-left navbar-nav list-unstyled">
			<li class="dropdown"><a href="#" data-toggle="dropdown"
				class="dropdown-toggle"><spring:message code="message.lang" /><b
					class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="?lang=en">English</a></li>
					<li><a href="?lang=ua">Ukrainian</a></li>

				</ul></li>
		</ul>

	</div>

	<form class="navbar-form navbar-right" role="search">
		<div class="form-group">


			<input maxlength="50" type="text" class="form-control" id="search_field" url= "${pageContext.request.contextPath}" name="search" placeholder="Search" 
				onkeydown="if (window.event.keyCode == 13)
                      search($(this).val(), $('#search_button').attr('url'))">



		</div>
		<button type="submit" class="btn btn-default" id="search_button"
			url="${pageContext.request.contextPath}/books/search">
			<spring:message code="message.search" />
		</button>
	</form>
	<div class="clearfix"></div>
	<div class="pull-right">

			<a id="advanced_search_button"><spring:message
					code="message.advanced" /></a>

			<div id="advanced_search_panel">
				<div class="control-group col-md-12">
					<input maxlength="25" class="form-control"
						id="advanced_search_title" type="text" placeholder="Title">
				</div>
				<div class="control-group col-md-12">
					<input maxlength="25" class="form-control"
						id="advanced_search_authors" type="text" placeholder="Author">
				</div>
				<div class="control-group col-md-12">
					<input maxlength="25" class="form-control"
						id="advanced_search_publisher" type="text" placeholder="Publisher">
				</div>

				<div class="control-group col-md-12">
					<select class="form-control" id="advanced_search_select">
						<option selected="selected" value="0">All Genres</option>
						<c:forEach var="genre" items="${genres}">
							<option value="${genre.getId()}">${genre.genreTranslation}</option>
						</c:forEach>
					</select>
				</div>
				<div class="control-group col-md-12">
					<button class="form-control btn-info" id="advanced_search_submit"
						url="${pageContext.request.contextPath}/books/advancedSearch">search</button>
				</div>
			</div>
	</div>
</div>

