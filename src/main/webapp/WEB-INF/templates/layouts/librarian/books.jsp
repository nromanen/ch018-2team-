<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

	<div class="row">
		<div class="col-md-2 col-md-offset-5" style="margin-top:30px">
				
					<form name="search" action="<c:url value="/librarian/books/simplesearch" />" method="POST" >
						<input type="text" class="form-control" id="" name="request"/>  <!--  <input type="submit" value="Search" /> -->
						
					</form>
				
			</div>
			<div class="col-md-2"  style="margin-top:35px" >
				<t class="ex1"><a href="<c:url value="/librarian/books/advancedsearch"/>"  class="btn btn-default btn-xs" role="button" style="margin-left: 7px"><spring:message code="message.libAdvanced"/></a></t>
			</div>
			
	<div class="row">
			<div class="col-md-12 " style="margin-top:30px">
			
				<div class="col-md-12">
				<table class="table table-hover table-striped table-bordered table-condensed">
				
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
                            <td> <b> <spring:message code="message.libGeneralQuantity"/></b> </td>
                            <td> <b> <spring:message code="message.libOptions"/> </b> </td>
               	         
               		   </tr>
       			 </thead>
				        <c:forEach items="${books}" var="book">
				                <tr>
				                        <td>${book.bId}</td>
				                        <td>${book.title}</td>
				                        <td>${book.authors}</td>
				                        <td>${book.year}</td>
				                        <td>${book.publisher}</td>
				                        <td>${book.pages}</td>
				                        <td>${book.genre}</td>
				                        <td>${book.description}</td>
				                        <td>${book.shelf}</td>
				                        <td>${book.term}</td>
				                        <td>${book.currentQuantity}</td>
				                        <td>${book.generalQuantity}</td>
				                    <td><a href="<c:url value="/librarian/books/editbook?id=${book.bId}"/>" style="color: #757575"><spring:message code="message.libEdit"/></a>
				                    <a href="<c:url value="/librarian/books/deletebook?id=${book.bId}"/>" style="color: #757575"><spring:message code="message.libDelete"/></a></td>
				                </tr>
				        </c:forEach>
			   </table>
			   </div>
			   
					
				
				</div>
			</div>
<div class="row">

	<div class="col-md-1 col-md-offset-9" style="margin-top:10px">
		<a href="<c:url value="/librarian/books/addbook"/>" class="btn btn-default btn-sm" role="button"><spring:message code="message.libAddBook"/></a>
	</div>
   
</div>			
			
		</div>
