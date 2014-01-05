

$(document).ready(function(){

    $(".calendar").each(function (){
        var orderDateLong = $(this).prev().val();
        var minDateLong = $(this).prev().prev().val();
        
        if(orderDateLong > minDateLong){
            var $btn = $(this).next();
            $btn.attr('data-toggle', 'popover');
            $btn.attr('data-content', 'can choose earlier date');
            $btn.popover("show");
        }
        
        var orderDate = getDateInFormat(orderDateLong);
        var minDate = getDateInFormat(minDateLong).split(" ");
        
        $(this).datetimepicker({
            format: 'Y/m/d H:m',
            value: orderDate,
            minDate: minDate[0],
            minTime: minDate[1]
        });
    });

    

    $('.order_delete_button').click(function(){
        
        deleteOrder($(this).prev().val());
    });
    
    $('.order_change_button').click(function (){
        var orderId = $(this).parent().children().val();
        
        var date = getLongFromFormatTime(($(this).prev().val()));
        editOrder(orderId, date);
        
    });
});


function deleteOrder(orderId){
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
                var $btn = $li.find('.order_change_button');
                
                if(data.date > (data.minDate + 6*60*60*1000)){
                    $btn.attr('data-toggle', 'popover');
                    $btn.attr('data-content', 'can choose earlier date');
                    $btn.popover("show");
                }
                
                var $edit_input = $li.find(".calendar");
                var minD = getDateInFormat(data.minDate).split(" ");
                var currentDate = getDateInFormat(data.date);
                 $edit_input.datetimepicker({
                    
                    format: 'Y/m/d H:m',
                    value: currentDate,
                    minDate: minD[0],
                    minTime: minD[1]
               });
               
               
            }



        });
    
}