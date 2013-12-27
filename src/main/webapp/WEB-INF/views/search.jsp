<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="search">
    
    <input id="search_field" type="text" name="search" onkeydown="if (window.event.keyCode==13) search($(this).val())"/>
        <button onclick="search($('#search_field').val());">search</button>
        
    
</div>
        