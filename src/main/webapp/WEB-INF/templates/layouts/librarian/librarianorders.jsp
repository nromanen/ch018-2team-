<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8"%>

    <div class="row">

        <div class="col-md-10 col-md-offset-1" style="margin-top:30px">

            <table id="upTable" class="table table-hover table-striped table-bordered table-condensed" width="80%">
                <tr>
                    <!--<td width="10%" onblur="jQuery:onblur();" contenteditable="true" onkeyup="jQuery:tdid();" id="TDId" name="idsearch" onclick="this.innerHTML=''"><b>ID</b></td> -->    <!--onkeyup="resources/js/searchById.js"-->
                    <td width="20%" onblur="jQuery:onblur();" contenteditable="true" onkeyup="jQuery:tdid();" id="TDSurname" onclick="this.innerHTML=''"><b>Person</b></td>
                    <td width="20%"contenteditable="true" onkeyup="jQuery:tdid();" id="TDTitle" onclick="this.innerHTML=''"><b>Title</b></td>
                    <td width="30%"contenteditable="true" id="TDDate" onclick="this.innerHTML=''"><b>Date</b></td>
                    <td width="30%" ><b>Options</b></td>
                    <!--  this.getElementById("TDId").innerHTML-->
                </tr>
            </table>
            <div id="content1">
                <table id="downTable" class="table table-hover table-striped table-bordered table-condensed"  width="80%" style="margin-top:-15px" >
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <!--<td width="10%">${order.id}</td>-->
                            <td width="20%">${order.person.name} ${order.person.surname}</td>
                            <td width="20%">${order.book.title}</td>
                            <td width="30%">${order.orderDate}</td>


                            <td><a href="<c:url value="/librarian/orders/issue?id=${order.id}"/>" style="color: #0E3846">Issue</a>
                        </tr>
                    </c:forEach>
                </table>
            </div>


        </div>
    </div>

<script type="text/javascript">
    /* $(document).ready(function(){
     $("#TDId").keyup(function(){ */
    function tdid(){

        $("#content1").load("/librarian/orders/searchById #content1",{/*"id":$("#TDId").text(),*/"title":$("#TDTitle").text(),"surname":$("#TDSurname").text(),"date":$("#TDDate").text()}/*,{"title":$("#TDTitle").text()}*/);
    }
    function onblur(){
        $("#TDSurname").text("ID");
        //if ($("#TDSurname").text()=="")
    }
    /*      })
     }); */
</script>

