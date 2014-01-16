<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>


    
    
    <tilesx:useAttribute name="genres" />
    
    <div class="col-lg-6">
    <form class="navbar-form navbar-right" role="search">
      <div class="form-group">
          
              
          <input  maxlength="50" type="text" class="form-control" id="search_field" name="search" placeholder="Search" onkeydown="if (window.event.keyCode==13) search($(this).val(), $('#search_button').attr('url'))">
              
          
        
      </div>
      <button type="submit" class="btn btn-default" id="search_button" url="${pageContext.request.contextPath}/books/search"><spring:message code="message.search" /></button>
    </form>
    <div class="pull-right" style="margin-right: 20px;">
        <sec:authorize access="isAuthenticated()">

                <a id="advanced_search_button"><spring:message code="message.advanced" /></a> 

                <div id="advanced_search_panel">
                    <div class="control-group col-md-12">
                        <input maxlength="25" class="form-control" id="advanced_search_title" type="text" placeholder="Title">
                    </div>
                    <div class="control-group col-md-12">
                        <input maxlength="25" class="form-control" id="advanced_search_authors" type="text" placeholder="Author">
                    </div>
                    <div class="control-group col-md-12">
                        <input maxlength="25" class="form-control" id="advanced_search_publisher" type="text" placeholder="Publisher">
                    </div>

                    <div class="control-group col-md-12">
                        <select class="form-control" id="advanced_search_select">
                            <option selected="selected" value="0">All Genres</option>
                            <c:forEach var="genre" items="${genres}">
                                <option value="${genre.getId()}">${genre.getDescription()}</option>
                            </c:forEach>
                        </select>    
                    </div>

                    <button class="form-control btn-info" id="advanced_search_submit" url="${pageContext.request.contextPath}/books/advancedSearch">search</button> 

                </div>
            </sec:authorize>
    </div>
    </div>
    


    <!--<div class="col-md-5 col-md-offset-2" style="margin-top: 15px;">
    <div class="row">
        <div class="col-md-10">
            <input maxlength="50" class="form-control" id="search_field" type="text" name="search" onkeydown="if (window.event.keyCode==13) search($(this).val(), $('#search_button').attr('url'))"/>
        </div>
        <div class="col-md-2">
            <button class="form-control btn-info" id="search_button" url="${pageContext.request.contextPath}/books/search" onclick="search($('#search_field').val(), $(this).attr('url'));"><spring:message code="message.search" /></button>
        </div>
    </div>
    <div class="row" style="margin-top: 10px;">
        <div class="col-md-offset-8">
            <sec:authorize access="isAuthenticated()">

                <a style="color: white" id="advanced_search_button"><spring:message code="message.advanced" /></a> 

                <div id="advanced_search_panel">
                    <div class="control-group col-md-12">
                        <input maxlength="25" class="form-control" id="advanced_search_title" type="text" placeholder="Title">
                    </div>
                    <div class="control-group col-md-12">
                        <input maxlength="25" class="form-control" id="advanced_search_authors" type="text" placeholder="Author">
                    </div>
                    <div class="control-group col-md-12">
                        <input maxlength="25" class="form-control" id="advanced_search_publisher" type="text" placeholder="Publisher">
                    </div>

                    <div class="control-group col-md-12">
                        <select class="form-control" id="advanced_search_select">
                            <option selected="selected" value="0">All Genres</option>
                            <c:forEach var="genre" items="${genres}">
                                <option value="${genre.getId()}">${genre.getDescription()}</option>
                            </c:forEach>
                        </select>    
                    </div>

                    <button class="form-control btn-info" id="advanced_search_submit" url="${pageContext.request.contextPath}/books/advancedSearch">search</button> 

                </div>
            </sec:authorize>
        </div>  

            </div>
   
    
</div>-->
        