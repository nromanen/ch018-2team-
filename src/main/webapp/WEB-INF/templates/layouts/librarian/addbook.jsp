<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

        	<div class="row">
        		
			
					<div class="col-md-7">
						
				        <form:form method="POST" commandName="book" class="form-horizontal" role="form" style="margin-left: 70px ; margin-top:30px">
				             
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">Title</label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="title" placeholder="Title">
							    </div>
							</div> 
				          
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">Author</label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="authors" placeholder="Author">
							    </div>
							</div> 
				          
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">Year</label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="year" placeholder="Year">
							    </div>
							</div> 
				           
				           <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">Publisher</label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="publisher" placeholder="Publisher">
							    </div>
							</div> 
				           
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">Pages</label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="pages" placeholder="Pages">
							    </div>
							</div> 
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">Description</label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="description" placeholder="Description">
							    </div>
							</div>
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">Image URL</label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="img" placeholder="Image URL">
							    </div>
							</div>
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">Shelf</label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="shelf" placeholder="Shelf">
							    </div>
							</div>
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">Term</label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="term" placeholder="Term">
							    </div>
							</div>
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">Quantity</label>
							    <div class="col-sm-10">
							    	<input type="text" class="form-control" name="generalQuantity" placeholder="Quantity">
							    </div>
							</div>
				            
				            <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">Genre</label>
							    <div class="col-sm-10">
							    	   <select name="genreId" class="form-control">
					                    <c:forEach var="genre" items="${genre}">
					                        <option value="${genre.getId()}">${genre.getDescription()}</option>
					                    </c:forEach>
				                </select>
							    </div>
							 </div>
				              
		
						<div class="col-md-1 col-md-offset-10" style="margin-top:10px">
							<input type="submit" class="btn btn-default btn-sm" value="Add">
						</div>
				        </form:form>
				        
				   </div>     
		    </div>    	
      