<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>

	<div class="row">
	
		<div class="col-md-12">
			<h5> <b> <spring:message code="message.libTitle"/>: </b> ${person.getName()} </h5> <h5> <b> <spring:message code="message.libAuthors"/>: </b> ${person.getSurname()} </h5>
		</div>
		
		<div class="col-md-12">
			<table class="table table-hover table-striped table-bordered table-condensed" style="margin-top:15px">
				 <thead>
		                <tr>
		                        <td> <b> <spring:message code="message.libHolder"/> </b> </td>
		                        <td> <b> <spring:message code="message.libReturnDate"/> </b> </td>
		                        <td> <b> Options </b> </td>
		                        
		                </tr>
		        </thead>
				<c:forEach items="${booksInUse}" var="bookInUse">
		                <tr>
		                        <td>${bookInUse.book.title}</td>
		                        <td>${bookInUse.getReturnDate()}</td>
		                        <td><a href="<c:url value="/librarian/toreturn/getback?id=${bookInUse.id}"/>" style="color: #0E3846">Get back</a>
		                        
		                </tr>
		        </c:forEach>
			</table>
		</div>
	</div>