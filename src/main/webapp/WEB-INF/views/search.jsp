<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-8" id="search">
    <div class="row">
        <div class="col-md-10">
            <input class="form-control" id="search_field" type="text" name="search" onkeydown="if (window.event.keyCode==13) search($(this).val())"/>
        </div>
        <div class="col-md-2">
            <button class="btn-info" onclick="search($('#search_field').val());">search</button>
        </div>
    </div>
    <div class="row" id="advanced_search">
                <button id="advanced_search_button">Advanced search</button> 
                <div id="advanced_search_panel">
                    <p>
                    <input id="advanced_search_title" type="text">
                    <p>
                    <input id="advanced_search_authors" type="text">
                    <p>
                    <input id="advanced_search_publisher" type="text">
                    <p>
                    <select id="advanced_search_select">

                    </select>    
                    <p>
                    <button id="advanced_search_submit">search</button>    
                </div>


            </div>
   
    
</div>
        