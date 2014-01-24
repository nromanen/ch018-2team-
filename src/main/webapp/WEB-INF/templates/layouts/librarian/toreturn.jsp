<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>

  		<div class="row">
  	
			<div class="col-md-10 col-md-offset-1" style="margin-top:30px">
					
					<table class="table table-hover table-striped table-bordered table-condensed">
					        <thead>
					                <tr>
					                        <td> <b> Person </b> </td>
					                        <td> <b> Book </b> </td>
					                        <td> <b> Return Date</b> </td>
					                        <td> <b> Options </b> </td>
					                         
					                </tr>
					        </thead>
					        <c:forEach items="${booksInUse}" var="bookInUse">
					                <tr>
					                        <td hidden="true">${bookInUse.id}</td>
					                        <td>${bookInUse.person.getName()} ${bookInUse.person.getSurname()}</td>
					                        <td>${bookInUse.book.getTitle()}</td>
					                      <!-- <td>${bookInUse.getReturnDate()}</td>  -->  
					                       <td> <c:out  value="${fn:substring(bookInUse.getReturnDate(), 0, 11)}"/> </td>
					                        <td><a href="<c:url value="/librarian/toreturn/getback?id=${bookInUse.id}"/>"  style="color: #0E3846">Get back |</a>
					                        <a href="<c:url value="/librarian/toreturn/edit?id=${bookInUse.id}"/>"  style="color: #0E3846">Edit</a></td>
					                </tr>
					        </c:forEach>
					</table>
			</div>
		</div>
