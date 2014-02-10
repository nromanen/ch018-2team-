<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>

  		<div class="row">
  	
			<div class="col-md-10 col-md-offset-1" style="margin-top:30px">
					
					<table class="table table-hover table-striped table-bordered table-condensed">

					                <tr>
                                        <td width="20%" onblur="jQuery:bl();" contenteditable="true" onkeyup="jQuery:tdid();" id="TDSurname" onclick="jQuery:clkSurname();">Surname</td>
                                        <td width="20%" onblur="jQuery:bl();" contenteditable="true" onkeyup="jQuery:tdid();" id="TDTitle" onclick="jQuery:clkTitle();">Title</td>
                                        <td width="30%" onblur="jQuery:bl();" contenteditable="true" id="TDDate" onclick="jQuery:clkDate();">Date</td>
                                        <td width="30%">Options</td>
					                         
					                </tr>
					</table>
                <div id="content1" class="table table-hover table-striped  table-condensed">
                    <table id="downTable" border="1" width="80%" class="table table-hover table-striped table-bordered table-condensed">
					        <c:forEach items="${booksInUse}" var="bookInUse">
					                <tr>
					                        <td hidden="true">${bookInUse.id}</td>
					                        <td width="20%">${bookInUse.person.getSurname()}</td>
					                        <td width="20%">${bookInUse.book.getTitle()}</td>
					                        <td width="30%">${bookInUse.getReturnDate()}</td>
					                        <td width="30%"><a href="<c:url value="/librarian/toreturn/getback?id=${bookInUse.id}"/>"  style="color: #0E3846">Get back |</a>

					                        <a href="<c:url value="/librarian/toreturn/edit?id=${bookInUse.id}"/>"  style="color: #0E3846">Edit</a></td>
					                </tr>
					        </c:forEach>
					</table>
                 </div>
			</div>
		</div>
<script type="text/javascript">

    function tdid(){

        $("#content1").load("${pageContext.request.contextPath}/librarian/toreturn/searchById #content1",{"title":$("#TDTitle").text(),"surname":$("#TDSurname").text(),"date":$("#TDDate").text()});

    }
    function bl(){

        if ($("#TDSurname").text()=="") $("#TDSurname").text("Surname");
        if ($("#TDDate").text()=="") $("#TDDate").text("Date");
        if ($("#TDTitle").text()=="") $("#TDTitle").text("Title");

    }
    function clkSurname(){
        if ($("#TDSurname").text()=="Surname") $("#TDSurname").text("");

    }
    function clkDate(){
        if ($("#TDDate").text()=="Date") $("#TDDate").text("");

    }
    function clkTitle(){
        if ($("#TDTitle").text()=="Title") $("#TDTitle").text("");
    }

</script>
