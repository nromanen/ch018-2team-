<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

	<div class="row">
		<div class="col-md-3 col-md-offset-5" style="margin-top:30px">
				
					<form class="form-inline" name="search" action="<c:url value="/librarian/books/simplesearch" />" method="POST" >
						  <div class="form-group">
						<input type="text" class="form-control  input-sm" id="" name="request" placeholder="Title or Author"/> 
						</div>
						<button type="submit" class="btn btn-default">
						  	<span class="glyphicon glyphicon-search"></span>
						</button>
						
						<a href="<c:url value="/librarian/books/advancedsearch"/>" class="btn btn-default">
							<span class="glyphicon glyphicon-cog"></span>
						</a>
						
					</form>
			</div>
                                                        
				
	<div class="row">
			<div class="col-md-12 " style="margin-top:30px">
			
				<div class="col-md-12">
				<table class="table table-hover table-striped table-bordered table-condensed">
				
     			   <thead>
         		       <tr>
           	                
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
                            <td> <b> <spring:message code="message.libGeneralQuantity"/></b> </td>
                            <td> <b> <spring:message code="message.libOptions"/> </b> </td>
               	         
               		   </tr>
       			 </thead>
       			  		<c:set var = "locale" value="${locale}"/>  
				        <c:forEach items="${books}" var="book">
				                <tr>
				                        <td hidden="true">${book.key.bId}</td>
				                        <td>${book.key.title}</td>
				                        <td>${book.key.authors}</td>
				                        <td>${book.key.year}</td>
				                        <td>${book.key.publisher}</td>
				                        <td>${book.key.pages}</td>
				                        <td>${book.value}</td>
				                        <td><textarea class="form-control" rows="2" style=" background:transparent;"> ${book.key.description} </textarea></td>
				                        <td>${book.key.shelf}</td>
				                        <td>${book.key.term}</td>
				                        <td>${book.key.currentQuantity}</td>
				                        <td>${book.key.generalQuantity}</td>
				                    <td><a href="<c:url value="/librarian/books/editbook?id=${book.key.bId}"/>" style="color: #0E3846"><spring:message code="message.libEdit"/> |</a>
				                    <a href="<c:url value="/librarian/books/deletebook?id=${book.key.bId}"/>" style="color: #0E3846"><spring:message code="message.libDelete"/> |</a>
				                     <a href="<c:url value="/librarian/books/holders?id=${book.key.bId}"/>"style="color: #0E3846"><spring:message code="message.libHolders"/></a></td>
				                </tr>
				        </c:forEach>
			   </table>
			   </div>
			   
					
				
				</div>
			</div>
			<div class="row">
			
				<div class="col-md-1 col-md-offset-9" style="margin-top:10px">
					<a href="<c:url value="/librarian/books/addbook"/>" class="btn btn-default btn-sm" role="button" style="background-color: #00A1A1 ; color: #FFFFFF"><spring:message code="message.libAddBook"/></a>
				</div>
			   
			</div>			
			
		</div>
