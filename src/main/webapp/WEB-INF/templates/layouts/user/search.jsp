<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="col-md-5 col-md-offset-1" >
    <div class="row">
        <div class="col-md-10">
            <input class="form-control" id="search_field" type="text" name="search" onkeydown="if (window.event.keyCode==13) search($(this).val())"/>
        </div>
        <div class="col-md-2">
            <button class="btn-info" onclick="search($('#search_field').val());"><spring:message code="message.search" /></button>
        </div>
    </div>
    <div class="row" style="margin-top: 10px;">
        <div class="col-md-offset-8">
            <sec:authorize access="isAuthenticated()">
                <a id="advanced_search_button"><spring:message code="message.advanced" /></a> 
                
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
                    </sec:authorize>
        </div>  

            </div>
   
    
</div>
        