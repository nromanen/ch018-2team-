<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"	prefix="tilesx"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>


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
                      doPost($(this).val(), $('#search_button').attr('url'))">



		</div>
		<button type="submit" class="btn btn-default" id="search_button"
			url="${pageContext.request.contextPath}/books/search">
			<span class="glyphicon glyphicon-search"></span>
		</button>
	</form>
	<div class="clearfix"></div>

</div>

