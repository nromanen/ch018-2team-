<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<div class="row">
     
 <nav class="navbar navbar-default" role="navigation">
 
 	<div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#nav_menu">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    
  </div>
     <div class="collapse navbar-collapse" id="nav_menu">
     
    <tiles:insertAttribute name="search" />
    
    <tiles:insertAttribute name="usermenu" />
     </div>
 </nav>
    
</div>

