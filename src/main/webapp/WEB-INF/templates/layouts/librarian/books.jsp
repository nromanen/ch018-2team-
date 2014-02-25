<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

	<div class="row">
		<div class="col-md-3 col-md-offset-5" style="margin-top:30px">
				
					<form class="form-inline" name="search" action="<c:url value="/librarian/books/simplesearch" />" method="POST" >
						  <div class="form-group">
						<input type="text" class="form-control  input-sm" id="" name="request" placeholder="Title or Author"/> 
						</div>
						<button type="submit" class="btn btn-default">
						  	<span class="glyphicon glyphicon-search"></span>
						</button>
						
						<a href="<c:url value="/librarian/books/advancedsearch"/>" class="btn btn-default">
							<span class="glyphicon glyphicon-cog"></span>
						</a>
						
					</form>
			</div>


        <div class="col-md-10 col-md-offset-1" style="margin-top: 10px">





            <table id="upTable" border="1" width="80%" class="table table-hover table-striped table-bordered table-condensed">
                <tr>

                    <td id="TDTitle" width="25%" onkeyup="jQuery:pagin($('#sort').text(),$('#whatKindOfSort').text(),$('#page').text(),$('#countP').text());" onblur="jQuery:bl();" onclick="jQuery:clkTitle();" contenteditable="true"><spring:message code="message.libTitle"/></td>
                    <td width="3%" onclick="jQuery:pagin($('#sort').text(),'title',$('#page').text(),$('#countP').text());" contenteditable="false"><img style="height: 20px; width: 20px" src="${pageContext.request.contextPath}/resources/img/sort.ico"></td>

                    <td id="TDYear" width="4%" onkeyup="jQuery:pagin($('#sort').text(),$('#whatKindOfSort').text(),$('#page').text(),$('#countP').text());" onblur="jQuery:bl();" onclick="jQuery:clkYear();" contenteditable="true"><spring:message code="message.libYear"/></td>
                    <td width="3%" onclick="jQuery:pagin($('#sort').text(),'year',$('#page').text(),$('#countP').text());" contenteditable="false"><img style="height: 20px; width: 20px" src="${pageContext.request.contextPath}/resources/img/sort.ico"></td>

                    <td id="TDPages" width="4%" onkeyup="jQuery:pagin($('#sort').text(),$('#whatKindOfSort').text(),$('#page').text(),$('#countP').text());" onblur="jQuery:bl();" onclick="jQuery:clkPages();" contenteditable="true"><spring:message code="message.libPages"/></td>
                    <td width="3%" onclick="jQuery:pagin($('#sort').text(),'pages',$('#page').text(),$('#countP').text());" contenteditable="false"><img style="height: 20px; width: 20px" src="${pageContext.request.contextPath}/resources/img/sort.ico"></td>

                    <td id="TDShelf" width="4%" onkeyup="jQuery:pagin($('#sort').text(),$('#whatKindOfSort').text(),$('#page').text(),$('#countP').text());" onblur="jQuery:bl();" onclick="jQuery:clkShelf();" contenteditable="true"><spring:message code="message.libShelf"/></td>
                    <td width="3%" onclick="jQuery:pagin($('#sort').text(),'shelf',$('#page').text(),$('#countP').text());" contenteditable="false"><img style="height: 20px; width: 20px" src="${pageContext.request.contextPath}/resources/img/sort.ico"></td>

                    <td id="TDCq" width="10%" onkeyup="jQuery:pagin($('#sort').text(),$('#whatKindOfSort').text(),$('#page').text(),$('#countP').text());" onblur="jQuery:bl();" onclick="jQuery:clkCq();" contenteditable="true"><spring:message code="message.libCurrentQuantity"/></td>
                    <td width="3%" onclick="jQuery:pagin($('#sort').text(),'currentQuantity',$('#page').text(),$('#countP').text());" contenteditable="false"><img style="height: 20px; width: 20px" src="${pageContext.request.contextPath}/resources/img/sort.ico"></td>

                    <td id="TDGq" width="10%" onkeyup="jQuery:pagin($('#sort').text(),$('#whatKindOfSort').text(),$('#page').text(),$('#countP').text());" onblur="jQuery:bl();" onclick="jQuery:clkGq();" contenteditable="true"><spring:message code="message.libGeneralQuantity"/></td>
                    <td width="3%" onclick="jQuery:pagin($('#sort').text(),'generalQuantity',$('#page').text(),$('#countP').text());" contenteditable="false"><img style="height: 20px; width: 20px" src="${pageContext.request.contextPath}/resources/img/sort.ico"></td>

                    <td width="20%"><spring:message code="message.libOptions"/></td>

                    <a id="sort" hidden="true">asc</a>
                    <a id="whatKindOfSort" hidden="true"></a>
                    <a id="page" hidden="true">1</a>
                </tr>
            </table>
            <div id="content1" class="table table-hover table-striped  table-condensed">
                <table id="downTable" border="1" width="80%" class="table table-hover table-striped table-bordered table-condensed">
                    <c:forEach items="${books}" var="book">
                        <tr>
                            <td hidden="true">${book.bId}</td>

                            <td width="22%">${book.title}</td>

                            <td width="7%">${book.year}</td>

                            <td width="7%">${book.pages}</td>

                            <td width="5%">${book.shelf}</td>

                            <td width="10%">${book.currentQuantity}</td>

                            <td width="10%">${book.generalQuantity}</td>

                            <td width="20%"><a href="<c:url value="/librarian/books/editbook?id=${book.bId}"/>" style="color: #0E3846"><spring:message code="message.libEdit"/> |</a>
                                <a href="<c:url value="/librarian/books/deletebook?id=${book.bId}"/>" style="color: #0E3846"><spring:message code="message.libDelete"/> |</a>
                                <a href="<c:url value="/librarian/books/holders?id=${book.bId}"/>"style="color: #0E3846"><spring:message code="message.libHolders"/></a>
                                | <a href="<c:url value="/librarian/orders/book?id=${book.bId}"/>"style="color: #0E3846">Orders</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <ul class="pagination pager">
                <li ><a onclick="jQuery:pagin($('#sort').text(),$('#whatKindOfSort').text(),1,$('#countP').text());">&laquo;</a></li>

                <li><a onclick="jQuery:pageMinus($('#sort').text(),$('#whatKindOfSort').text(),$('#page').text(),$('#countP').text());">Previous</a></li>
                <li><a onclick="jQuery:pagePlus($('#sort').text(),$('#whatKindOfSort').text(),$('#page').text(),$('#countP').text());">Next</a></li>

                <li><a>&raquo;</a>
                    <table><tr><td><a>fields QTY:</a></td><td id="countP" contenteditable="true" onkeyup="jQuery:countChange();jQuery:pagin($('#sort').text(),$('#whatKindOfSort').text(),$('#page').text(),$('#countP').text())" onclick="$('#countP').text('')">10</td><tr></table>
                </li>
            </ul>


        </div>
    </div>
</div>
<script type="text/javascript">
    function countChange(){
        $('#count').text($('#countP').text());
        //alert("fuck: "+$('#count').text());
    }
    function pagePlus(how,what,page,count){
        $('#page').text(parseInt($('#page').text())+1);
        //alert("HOW:"+how+","+"WHAT:"+what+","+"PAGE:"+$('#page').text()+","+"COUNT:"+count+".");
        $("#content1").load("${pageContext.request.contextPath}/librarian/books/searchPagin #content1",{"title":$("#TDTitle").text(),"year":$("#TDYear").text(),"pages":$("#TDPages").text(),"shelf":$("#TDShelf").text(),"cq":$("#TDCq").text(),"gq":$("#TDGq").text(),"how":how,"what":what,"page":$("#page").text(),"count":count});
        $("#whatKindOfSort").text(what);
    }
    function pageMinus(how,what,page,count){
        $('#page').text(parseInt($('#page').text())-1);
        //alert("HOW:"+how+","+"WHAT:"+what+","+"PAGE:"+$('#page').text()+","+"COUNT:"+count+".");
        $("#content1").load("${pageContext.request.contextPath}/librarian/books/searchPagin #content1",{"title":$("#TDTitle").text(),"year":$("#TDYear").text(),"pages":$("#TDPages").text(),"shelf":$("#TDShelf").text(),"cq":$("#TDCq").text(),"gq":$("#TDGq").text(),"how":how,"what":what,"page":$("#page").text(),"count":count});
        $("#whatKindOfSort").text(what);
    }

    function pagin(how,what,page,count){
        if (how=="desc") $("#sort").text("asc");
        if (how=="asc") $("#sort").text("desc");
        //alert("HOW:"+how+","+"WHAT:"+what+","+"PAGE:"+page+","+"COUNT:"+count+".");
        $("#whatKindOfSort").text(what);
        $("#content1").load("${pageContext.request.contextPath}/librarian/books/searchPagin #content1",{"title":$("#TDTitle").text(),"year":$("#TDYear").text(),"pages":$("#TDPages").text(),"shelf":$("#TDShelf").text(),"cq":$("#TDCq").text(),"gq":$("#TDGq").text(),"how":how,"what":what,"page":page,"count":count});

    }

    function bl(){

        if ($("#TDTitle").text()=="") $("#TDTitle").text("Title");
        if ($("#TDYear").text()=="") $("#TDYear").text("Year");
        if ($("#TDPages").text()=="") $("#TDPages").text("Pages");
        if ($("#TDShelf").text()=="") $("#TDShelf").text("Shelf");
        if ($("#TDCq").text()=="") $("#TDCq").text("Current QTY");
        if ($("#TDGq").text()=="") $("#TDGq").text("General QTY");
    }

    function clkTitle(){
        if ($("#TDTitle").text()=="Title") $("#TDTitle").text("");
    }

    function clkYear(){
        if ($("#TDYear").text()=="Year") $("#TDYear").text("");
    }

    function clkPages(){
        if ($("#TDPages").text()=="Pages") $("#TDPages").text("");
    }

    function clkShelf(){
        if ($("#TDShelf").text()=="Shelf") $("#TDShelf").text("");
    }

    function clkCq(){
        if ($("#TDCq").text()=="Current QTY") $("#TDCq").text("");
    }

    function clkGq(){
        if ($("#TDGq").text()=="General QTY") $("#TDGq").text("");
    }
</script>