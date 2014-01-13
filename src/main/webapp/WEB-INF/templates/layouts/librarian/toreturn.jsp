<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>

  	<div class="row">
  	
		<div class="col-md-10 col-md-offset-1" style="margin-top:30px">
				
				<table class="table table-hover table-striped table-bordered table-condensed">
				        <thead>
				                <tr>
				                        <td> <b> ID </b> </td>
				                        <td> <b> Person </b> </td>
				                        <td> <b> Book </b> </td>
				                        <td> <b> Return Date</b> </td>
				                        <td> <b> Options </b> </td>
				                         
				                </tr>
				        </thead>
				        <c:forEach items="${booksInUse}" var="bookInUse">
				                <tr>
				                        <td>${bookInUse.id}</td>
				                        <td>${bookInUse.person.getName()}</td>
				                        <td>${bookInUse.book.getTitle()}</td>
				                        <td>${bookInUse.getReturnDate()}</td>
				                        <td><a href="<c:url value="/librarian/toreturn/getback?id=${bookInUse.id}"/>">Get back</a>
				                        <a href="<c:url value="/librarian/toreturn/edit?id=${bookInUse.id}"/>">Edit</a></td>
				                </tr>
				        </c:forEach>
				</table>
</div>
</div>
