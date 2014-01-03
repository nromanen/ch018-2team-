

$(document).ready(function(){

    $(".calendar").each(function (){
        var minD = $(this).prev().val().split(" ");
        $(this).datetimepicker({
            
            format: 'Y/m/d H:m',
            minDate: minD[0],
            minTime: minD[1]
        });
    });


    $('.order_delete_button').click(function(){
        deleteOrders($(this).prev().val());
    });
    
    $('.order_change_button').click(function (){
        var orderId = $(this).prev().prev().prev().val();
        var date = $(this).prev().val();
        editOrder(orderId, date);
    });
});


function deleteOrders(orderId){
    $.ajax({
        url: "delete",
        type: "POST",
        data: {'orderId' : orderId},
        dataType: "json",
        contentType: 'application/x-www-form-urlencoded',
        mimeType: 'application/json',
        success: function() {
            
            //myOrders();
            $('#order_li_' + orderId).remove();
               
                
            }



        });
    
}

function editOrder(orderId, date){
    $.ajax({
        url: "edit",
        type: "POST",
        data: {'orderId' : orderId, 'date' : date},
        dataType: "json",
        contentType: 'application/x-www-form-urlencoded',
        mimeType: 'application/json',
        success: function(data) {
            
                var $li = $('#order_li_' + orderId);
                
                
                var $edit_input = $li.find(".calendar");
                var minD = data.minDate.split(" ");
                 $edit_input.datetimepicker({
                    format: 'Y/m/d H:m',
                    minDate: minD[0],
                    minTime: minD[1]
               });
               
               
            }



        });
    
}