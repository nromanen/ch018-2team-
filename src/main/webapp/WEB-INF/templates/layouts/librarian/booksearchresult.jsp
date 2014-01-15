<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>

	
	<div class="row" >
	
		<div class="col-md-12" style="margin-top:30px">
			<table  class="table table-hover table-striped table-bordered table-condensed">
			        <thead>
			                <tr>
			                        <td> <b> ID </b> </td>
			                        <td> <b> <spring:message code="message.libTitle"/> </b> </td>
			                        <td> <b> <spring:message code="message.libAuthors"/> </b> </td> 
			                        <td> <b> <spring:message code="message.libYear"/> </b> </td>
			                        <td> <b> <spring:message code="message.libPublisher"/> </b> </td>
			                        <td> <b> <spring:message code="message.libPages"/> </b> </td>
			                        <td> <b> <spring:message code="message.libGenre"/> </b> </td>
			                        <td> <b> <spring:message code="message.libDescription"/> </b> </td>    
			                        <td> <b> <spring:message code="message.libShelf"/> </b> </td>
			                        <td> <b> <spring:message code="message.libTerm"/> </b> </td> 
			                        <td> <b> <spring:message code="message.libCurrentQuantity"/></b> </td>
			                        <td> <b> <spring:message code="message.libOptions"/> </b> </td>
			                        
			                </tr>
			        </thead>
			        <c:forEach items="${books}" var="book">
			                <tr>
			                        <td hidden="true">${book.bId}</td>
			                        <td>${book.title}</td>
			                        <td>${book.authors}</td>
			                        <td>${book.year}</td>
			                        <td>${book.publisher}</td>
			                        <td>${book.pages}</td>
			                        <td>${book.genre}</td>
			                        <td>${book.description}</td>
			                        <td>${book.shelf}</td>
			                        <td>${book.term}</td>
			                        <td>${book.generalQuantity}</td>
			                    <td><a href="<c:url value="/librarian/books/editbook?id=${book.bId}"/>" style="color: #757575"><spring:message code="message.libEdit"/></a>
			                    <a href="<c:url value="/librarian/books/deletebook?id=${book.bId}"/>" style="color: #757575"><spring:message code="message.libDelete"/></a>
			                     <a href="<c:url value="/librarian/books/holders?id=${book.bId}"/>"style="color: #757575"><spring:message code="message.libHolders"/></a></td>
			                </tr>
			        </c:forEach>
			</table>
	</div>
	</div>
