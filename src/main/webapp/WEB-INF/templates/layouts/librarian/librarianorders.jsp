<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8"%>



<div class="container" id="divContent">

    <div class="row">

        <div class="col-md-3 col-md-offset-1" style="margin-top: 30px" >

            <a href="${pageContext.request.contextPath}/librarian/orders" class="btn btn-default btn-sm">All</a>

            <a href="${pageContext.request.contextPath}/librarian/orders/toissueinhour" class="btn btn-default btn-sm">To issue hour</a>

            <a href="${pageContext.request.contextPath}/librarian/orders/toissuetoday" class="btn btn-default btn-sm">To issue today</a>
        </div>

        <div class="col-md-10 col-md-offset-1" style="margin-top: 10px">





            <table id="upTable" border="1" width="80%" class="table table-hover table-striped table-bordered table-condensed">
                <tr>

                    <td width="20%" onblur="jQuery:bl();" contenteditable="true" onkeyup="jQuery:tdid();" id="TDSurname" onclick="jQuery:clkSurname();">Surname</td>
                    <td width="3%" onclick="jQuery:pagin($('#sort').text(),'person.surname',$('#page').text(),$('#countP').text());" contenteditable="false"><img style="height: 20px; width: 20px" src="${pageContext.request.contextPath}/resources/img/sort.ico"></td>
                    <td width="20%" onblur="jQuery:bl();" contenteditable="true" onkeyup="jQuery:tdid();" id="TDTitle" onclick="jQuery:clkTitle();">Title</td>
                    <td width="3%" onclick="jQuery:pagin($('#sort').text(),'book.title',$('#page').text(),$('#countP').text());" contenteditable="false"><img style="height: 20px; width: 20px" src="${pageContext.request.contextPath}/resources/img/sort.ico"></td>
                    <td width="30%" onblur="jQuery:bl();" contenteditable="true" id="TDDate" onclick="jQuery:clkDate();">Date</td>
                    <td width="30%">Options</td>
                    <a id="sort" hidden="true">asc</a>
                    <a id="whatKindOfSort" hidden="true"></a>
                    <a id="page" hidden="true">1</a>
                    <a id="count" hidden="true">10</a>

                </tr>
            </table>
            <div id="content1" class="table table-hover table-striped  table-condensed">
                <table id="downTable" border="1" width="80%" class="table table-hover table-striped table-bordered table-condensed">
                    <c:forEach items="${orders}" var="order">
                        <tr>

                            <td hidden="true">${order.id}</td>
                            <td width="23%">${order.person.surname}</td>
                            <td width="23%">${order.book.title}</td>
                            <td width="30%">${order.orderDate}</td>


                            <td><a href="${pageContext.request.contextPath}/librarian/orders/issue?id=${order.id}" style="color: #0E3846">Issue</a>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <ul class="pagination pager">
                <li ><a onclick="jQuery:pagin($('#sort').text(),$('#whatKindOfSort').text(),1,$('#countP').text());">&laquo;</a></li>

                    <li><a onclick="jQuery:pageMinus($('#sort').text(),$('#whatKindOfSort').text(),$('#page').text(),$('#countP').text());">Previous</a></li>
                    <li><a onclick="jQuery:pagePlus($('#sort').text(),$('#whatKindOfSort').text(),$('#page').text(),$('#countP').text());">Next</a></li>

                <li><a>&raquo;</a>
                    <table><tr><td id="countP" contenteditable="true" onkeyup="jQuery:countChange();jQuery:pagin($('#sort').text(),$('#whatKindOfSort').text(),$('#page').text(),$('#countP').text())" onclick="$('#countP').text('')">10</td><tr></table>
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
        $("#content1").load("${pageContext.request.contextPath}/librarian/orders/sortSurname #content1",{"title":$("#TDTitle").text(),"surname":$("#TDSurname").text(),"date":$("#TDDate").text(),"how":how,"what":what,"page":$('#page').text(),"count":count});
        $("#whatKindOfSort").text(what);
    }
    function pageMinus(how,what,page,count){
        $('#page').text(parseInt($('#page').text())-1);
        //alert("HOW:"+how+","+"WHAT:"+what+","+"PAGE:"+$('#page').text()+","+"COUNT:"+count+".");
        $("#content1").load("${pageContext.request.contextPath}/librarian/orders/sortSurname #content1",{"title":$("#TDTitle").text(),"surname":$("#TDSurname").text(),"date":$("#TDDate").text(),"how":how,"what":what,"page":$('#page').text(),"count":count});
        $("#whatKindOfSort").text(what);
    }
    function tdid(){
        contenteditable="true"
        $("#content1").load("${pageContext.request.contextPath}/librarian/orders/searchById #content1", {"title":$("#TDTitle").text(),"surname":$("#TDSurname").text(),"date":$("#TDDate").text()});

    }
    function pagin(how,what,page,count){
        if (how=="desc") $("#sort").text("asc");
        if (how=="asc") $("#sort").text("desc");
        //alert("HOW:"+how+","+"WHAT:"+what+","+"PAGE:"+page+","+"COUNT:"+count+".");
        $("#content1").load("${pageContext.request.contextPath}/librarian/orders/sortSurname #content1",{"title":$("#TDTitle").text(),"surname":$("#TDSurname").text(),"date":$("#TDDate").text(),"how":how,"what":what,"page":page,"count":count});
        $("#whatKindOfSort").text(what);
    }
    function sortS(how,what){
        if (how=="desc") $("#sort").text("asc");
        if (how=="asc") $("#sort").text("desc");
        $("#content1").load("${pageContext.request.contextPath}/librarian/orders/sortSurname #content1",{"title":$("#TDTitle").text(),"surname":$("#TDSurname").text(),"date":$("#TDDate").text(),"how":how,"what":what});

        $("#whatKindOfSort").text(what);

    }
    function bl(){

        if ($("#TDSurname").text()=="") $("#TDSurname").text("Surname");
        if ($("#TDDate").text()=="") $("#TDDate").text("Date");
        if ($("#TDTitle").text()=="") $("#TDTitle").text("Title");

    }
    function clkSurname(){
        if ($("#TDSurname").text()=="Surname") $("#TDSurname").text("");


    }                                                                                                                                                                                                              contenteditable="true"
    function clkDate(){
        if ($("#TDDate").text()=="Date") $("#TDDate").text("");

    }
    function clkTitle(){
        if ($("#TDTitle").text()=="Title") $("#TDTitle").text("");
    }

</script>

