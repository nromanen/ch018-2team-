<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

        	<div class="row">
        		
					<div class="col-md-7">
						
				        <form id="addbook" method="POST" action="${pageContext.request.contextPath}/librarian/books/addbook" class="form-horizontal" role="form" style="margin-left: 70px ; margin-top:30px" enctype="multipart/form-data">
				            <div class="form-group">
							    <label for="title" class="col-sm-2 control-label"><spring:message code="message.libTitle"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="title" id="titleval" />
							        
							    </div>
							</div> 
				          
				            <div class="form-group">
							    <label for="authors" class="col-sm-2 control-label"><spring:message code="message.libAuthors"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="authors"/>
							    </div>
							</div> 
				          
				            <div class="form-group">
							    <label for="year" class="col-sm-2 control-label"><spring:message code="message.libYear"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="year"/>
							    </div>
							</div> 
				           
				           <div class="form-group">
							    <label for="publisher" class="col-sm-2 control-label"><spring:message code="message.libPublisher"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="publisher"/>
							    </div>
							</div> 
				           
				            <div class="form-group">
							    <label for="pages" class="col-sm-2 control-label"><spring:message code="message.libPages"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="pages"/>
							    </div>
							</div> 
				            
				            <div class="form-group">
							    <label for="description" class="col-sm-2 control-label"><spring:message code="message.libDescription"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="description"/>
							    </div>
							</div>
				            
				            <div class="form-group">
							    <label for="file" class="col-sm-2 control-label"><spring:message code="message.libImg"/></label>
							    <div class="col-sm-10">
							    	<input type="file" class="form-control" name="file" />
							    </div>
							</div>
				            
				            <div class="form-group">
							    <label for="shelf" class="col-sm-2 control-label"><spring:message code="message.libShelf"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="shelf"/>
							    </div>
							</div>
				            
				            <div class="form-group">
							    <label for="term" class="col-sm-2 control-label"><spring:message code="message.libTerm"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="term" />
							    </div>
							</div>
				            
				            <div class="form-group">
							    <label for="generalQuantity" class="col-sm-2 control-label"><spring:message code="message.libGeneralQuantity"/></label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="generalQuantity" />
							    </div>
							</div>
				            
				            <div class="form-group">
							    <label for="gid" class="col-sm-2 control-label"><spring:message code="message.libGenre"/></label>
							    <div class="col-sm-10">
							    	   <select name="gid" class="form-control">
					                    <c:forEach var="genre" items="${genres}">
					                        <option value="${genre.id}">${genre.description}</option>
					                    </c:forEach>
				                </select>
							    </div>
							 </div>
				              
		
						<div class="col-md-1 col-md-offset-10" style="margin-top:10px" >
							<input type="submit" class="btn btn-default btn-sm" value="Add" style="background-color: #00A1A1 ; color: #FFFFFF">
						</div>
				        </form>
				        
				   </div>     
		    </div>    	
      