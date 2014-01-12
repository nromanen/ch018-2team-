

$(document).ready(function(){

    $(".calendar").each(function (){
        var changed = $(this).parent().find(".changed").val();
        var orderDateLong = $(this).prev().val();
        var minDateLong = $(this).prev().prev().val();
        var orderDate = getDateInFormat(orderDateLong);
        var rawMinDate = getDateInFormat(minDateLong);
        var minDate = rawMinDate.split(" ");
        
        if(changed === 'true'){
            
            var $btn = $(this).next();
            $btn.attr('data-toggle', 'popover');
            $btn.attr('data-content', 'choose another date');
            $btn.popover("show");
        }else if(orderDateLong > (minDateLong + (24*3600*1000))){
            
            var $btn = $(this).next();
            $btn.attr('data-toggle', 'popover');
            $btn.attr('data-content', 'can choose earlier date');
            $btn.popover("show");
            
        }
        
        
        $(this).datetimepicker({
            format: 'Y/m/d H:i',
            value: orderDate,
            minDate: minDate[0],
            allowTimes:[
                    '09:00', '10:00', '11:00', '12:00', '14:00',
                    '15:00', '16:00'
                ],
            validateOnBlur: true
        });
    });

    

    $('.order_delete_button').click(function(){
        
        deleteOrder($(this).prev().val());
    });
    
    $('.order_change_button').click(function (){
        alert("edit");
        var orderId = $(this).parent().children().val();
        
        var date = getLongFromFormatTime(($(this).prev().val()));
        alert(orderId, date);
        editOrder(orderId, date);
        
    });
});


function deleteOrder(orderId){
    $.ajax({
        url: "/library/books/order/delete",
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
        url: "/library/books/order/edit",
        type: "POST",
        data: {'orderId' : orderId, 'date' : date},
        dataType: "json",
        contentType: 'application/x-www-form-urlencoded',
        mimeType: 'application/json',
        success: function(data) {
            
                var $li = $('#order_li_' + orderId);
                var $btn = $li.find('.order_change_button');
                
                if(data.date > (data.minDate + 12*3600*1000)){
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