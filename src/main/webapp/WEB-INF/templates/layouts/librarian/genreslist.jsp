<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

	
	<div class="row">
			<div class="col-md-12 " style="margin-top:30px">
			
				<div class="col-md-12">
				<table class="table table-hover table-striped table-bordered table-condensed">
				
     			   <thead>
         		       <tr>
							           	                
                            <td> <b> Genre </b> </td>
                            <td> <b> Options </b> </td>
               	         
               		   </tr>
       			 </thead>
				        <c:forEach items="${genres}" var="genre">
				                <tr>
				                        <td hidden="true">${genre.id}</td>
				                        <td>${genre.description}</td>
				                        <td>
				                        <a href="<c:url value="/librarian/genres/delete?id=${genre.id}"/>" style="color: #0E3846"><spring:message code="message.libDelete"/></a>
				                        </td>
				                </tr>
				        </c:forEach>
			   </table>
			   </div>
			
		</div>
		<div class="row">
			
				<div class="col-md-1 col-md-offset-9" style="margin-top:10px">
					<a href="<c:url value="/librarian/genres/addgenre"/>" class="btn btn-default btn-sm" role="button" style="background-color: #00A1A1 ; color: #FFFFFF">Add Genre</a>
				</div>
			   
			</div>		
		</div>
