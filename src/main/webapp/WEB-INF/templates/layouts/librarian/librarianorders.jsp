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
                    <td><button id="surnameSortButton" style="height: 30px" onclick="jQuery:sortSurname($('#surnameSort').text());" contenteditable="false"><img src="">sort</button></td>
                    <a id="surnameSort" hidden="true">1</a>
                    <td width="20%" onblur="jQuery:bl();" contenteditable="true" onkeyup="jQuery:tdid();" id="TDTitle" onclick="jQuery:clkTitle();">Title</td>
                    <td width="30%" onblur="jQuery:bl();" contenteditable="true" id="TDDate" onclick="jQuery:clkDate();">Date</td>
                    <td width="30%">Options</td>


                </tr>
            </table>
            <div id="content1" class="table table-hover table-striped  table-condensed">
                <table id="downTable" border="1" width="80%" class="table table-hover table-striped table-bordered table-condensed">
                    <c:forEach items="${orders}" var="order">
                        <tr>

                            <td hidden="true">${order.id}</td>
                            <td width="20%">${order.person.surname}</td>
                            <td width="20%">${order.book.title}</td>
                            <td width="30%">${order.orderDate}</td>


                            <td><a href="${pageContext.request.contextPath}/librarian/orders/issue?id=${order.id}" style="color: #0E3846">Issue</a>
                        </tr>
                    </c:forEach>
                </table>
            </div>


        </div>
    </div>
</div>
<script type="text/javascript">

    function tdid(){
        contenteditable="true"
        $("#content1").load("${pageContext.request.contextPath}/librarian/orders/searchById #content1", {"title":$("#TDTitle").text(),"surname":$("#TDSurname").text(),"date":$("#TDDate").text()});

    }
    function sortSurname(how){

        $("#content1").load("${pageContext.request.contextPath}/librarian/orders/sortSurname #content1",{"title":$("#TDTitle").text(),"surname":$("#TDSurname").text(),"date":$("#TDDate").text(),"how":how});
        if (how=="1") $("#surnameSort").text("0");
        if (how=="0") $("#surnameSort").text("1");
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

