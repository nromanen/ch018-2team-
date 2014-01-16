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
    <style>
        t.ex1 {margin:300px 20px}
        t.ex3 {margin:10px 70px}
        t.ex2 {position:fixed; right:430px; top: 195px}
    </style>
    <title>Librarian Orders</title>

</head>
<body>

<div class="container" id="divContent">

    <div class="row">

        <div class=>

            <t class="ex3">
                <c:set var="highlight" value="books" scope="request"/>
                <a href="<c:url value="/librarian/users"/>"><h4>Users</h4></a>
                <td><a href="<c:url value="/librarian/books"/>"><h4>Books</h4></a>
                <td><a href="<c:url value="/librarian/orders"/>"><h4>Orders</h4></a>
                <td><a href="<c:url value="/librarian/toreturn"/>"><h4>To Return</h4></a>
            </t>

        </div>



        <div>
            <div>


                <form name="search" action="<c:url value="/librarian/orders/search" />" method="POST" >
                    <input type="text"  name="request"/>
                    <input type="submit" value="Search"/>

                </form>
            </div>


            <div class="col-md-8 col-md-offset-1">
                <a href="<c:url value="/librarian/orders"/>">All</a>

                <a href="<c:url value="/librarian/orders/toissueinhour"/>">To issue hour</a>

                <a href="<c:url value="/librarian/orders/toissuetoday"/>">To issue today</a>
            </div>

            <table id="upTable" border = "1" width="80%">
                <tr>
                    <!--<td width="10%" onblur="jQuery:onblur();" contenteditable="true" onkeyup="jQuery:tdid();" id="TDId" name="idsearch" onclick="this.innerHTML=''"><b>ID</b></td>-->     <!--onkeyup="resources/js/searchById.js"-->
                    <td width="20%"contenteditable="true" onkeyup="jQuery:tdid();" id="TDSurname" onclick="this.innerHTML=''"><b>Surname</b></td>
                    <td width="20%"contenteditable="true" onkeyup="jQuery:tdid();" id="TDTitle" onclick="this.innerHTML=''"><b>Title</b></td>
                    <td width="30%"contenteditable="true" id="TDDate" onclick="this.innerHTML=''"><b>Date</b></td>
                    <td></td>  <!--  this.getElementById("TDId").innerHTML-->
                </tr>
            </table>
            <div id="content1">
                <table id="downTable" border = "1" width="80%" >
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <!--<td width="10%">${order.id}</td>-->
                            <td width="20%">${order.person.getSurname()}</td>
                            <td width="20%">${order.book.getTitle()}</td>
                            <td width="30%">${order.getOrderDate()}</td>


                            <td><a href="<c:url value="/librarian/orders/issue?id=${order.id}"/>">Issue</a>
                        </tr>
                    </c:forEach>
                </table>
            </div>


        </div>
    </div>
</div>
<script type="text/javascript">
    /* $(document).ready(function(){
     $("#TDId").keyup(function(){ */
    function tdid(){

        $("#content1").load("/librarian/orders/searchById #content1",{"id":$("#TDId").text(),"title":$("#TDTitle").text(),"surname":$("#TDSurname").text(),"date":$("#TDDate").text()}/*,{"title":$("#TDTitle").text()}*/);
    }
    function onblur(){
        if ($("#TDId").text()=="") $("#TDId").text("ID");
    }
    /*      })
     }); */
</script>

</body>
</html>