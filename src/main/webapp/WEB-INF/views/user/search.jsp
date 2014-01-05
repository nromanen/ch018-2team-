<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-md-8" >
    <div class="row">
        <div class="col-md-10">
            <input class="form-control" id="search_field" type="text" name="search" onkeydown="if (window.event.keyCode==13) search($(this).val())"/>
        </div>
        <div class="col-md-2">
            <button class="btn-info" onclick="search($('#search_field').val());">search</button>
        </div>
    </div>
    <div class="row">
        <div class="col-md-offset-8">
                <a id="advanced_search_button">Advanced search</a> 
                <div id="advanced_search_panel">
                    <div class="control-group col-md-12">
                        <input class="form-control" id="advanced_search_title" type="text" placeholder="Title">
                    </div>
                    <div class="control-group col-md-12">
                        <input class="form-control" id="advanced_search_authors" type="text" placeholder="Author">
                    </div>
                    <div class="control-group col-md-12">
                        <input class="form-control" id="advanced_search_publisher" type="text" placeholder="Publisher">
                    </div>
                    <div class="control-group col-md-12">
                        <select class="form-control" id="advanced_search_select">
                            <option selected="selected" value="0">All Genres</option>
                            <c:forEach var="genre" items="${genres}">
                                <option value="${genre.getId()}">${genre.getDescription()}</option>
                            </c:forEach>
                        </select>    
                    </div>
                 
                    <button class="form-control btn-info" id="advanced_search_submit">search</button>    
                </div>
        </div>  

            </div>
   
    
</div>
        