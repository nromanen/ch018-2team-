<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js">
    </script>

    <title>Librarian Orders</title>

</head>
<body>
<p id="lala"></p>
<div class="container" id="divContent">

    <div class="row">

        <div class="col-md-3 col-md-offset-1" style="margin-top: 30px" >

            <a href="<c:url value="/librarian/orders"/>" class="btn btn-default btn-sm">All</a>

            <a href="<c:url value="/librarian/orders/toissueinhour"/>" class="btn btn-default btn-sm">To issue hour</a>

            <a href="<c:url value="/librarian/orders/toissuetoday"/>" class="btn btn-default btn-sm">To issue today</a>
        </div>

        <div class="col-md-10 col-md-offset-1" style="margin-top: 10px">





            <table id="upTable" border="1" width="80%" class="table table-hover table-striped table-bordered table-condensed">
                <tr>

                    <td width="20%" onblur="jQuery:bl();" contenteditable="true" onkeyup="jQuery:tdid();" id="TDSurname" onclick="this.innerHTML=''">Surname</td>
                    <td width="20%" onblur="jQuery:bl();" contenteditable="true" onkeyup="jQuery:tdid();" id="TDTitle" onclick="this.innerHTML=''">Title</td>
                    <td width="30%" onblur="jQuery:bl();" contenteditable="true" id="TDDate" onclick="this.innerHTML=''">Date</td>
                    <td width="30%">Options</td>


                </tr>
            </table>
            <div id="content1" class="table table-hover table-striped table-bordered table-condensed">
                <table id="downTable" border="1" width="80%" class="table table-hover table-striped table-bordered table-condensed">
                    <c:forEach items="${orders}" var="order">
                        <tr>

                            <td hidden="true">${order.id}</td>
                            <td width="20%">${order.person.surname}</td>
                            <td width="20%">${order.book.title}</td>
                            <td width="30%">${order.orderDate}</td>


                            <td><a href="<c:url value="/librarian/orders/issue?id=${order.id}"/>" style="color: #0E3846">Issue</a>
                        </tr>
                    </c:forEach>
                </table>
            </div>


        </div>
    </div>
</div>
<script type="text/javascript">

    function tdid(){

        $("#content1").load("/librarian/orders/searchById #content1",{"title":$("#TDTitle").text(),"surname":$("#TDSurname").text(),"date":$("#TDDate").text()});

    }
    function bl(){

        if ($("#TDSurname").text()=="") $("#TDSurname").text("Surname");
        if ($("#TDDate").text()=="") $("#TDDate").text("Date");
        if ($("#TDTitle").text()=="") $("#TDTitle").text("Title");
    }

</script>

</body>
</html>