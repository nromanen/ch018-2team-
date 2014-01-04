

$(document).ready(function(){
    tmpDate = $('#minDate').val();
    
    minDate = getDateInFormat(tmpDate).split(" ");
    
    $('#datetimepicker').datetimepicker({
                format: 'Y/m/d H:m',
                minDate: minDate[0],
                minTime: minDate[1],
                validateOnBlur: true
            });
    $('#order_button').click(function() {
        
        var bookId = $('#bookId').val();
        var time = getLongFromFormatTime($('#datetimepicker').val());
        makeOrder(bookId, time);
        
    });
    
    $('#wish_button').click(function() {
       bookId = $(this).prev().val();
        addToWishList(bookId);
    });
    

});

    
function makeOrder(bookId, time){
    $.ajax({
            url: window.location.pathname + "/add",
            type: "POST",
            data: {'bookId' : bookId, 'time' : time},
            dataType: "json",
            contentType: 'application/x-www-form-urlencoded',
            mimeType: 'application/json',
            
         success: function () {
            
                $('#order_modal').modal('show');
                
             }
         });
}   

function addToWishList(bookId){
    
    $.ajax({
        url: "/library/books/wishlist/add",
        type: "POST",
        data: {'bookId' : bookId},
        dataType: "json",
        contentType: 'application/x-www-form-urlencoded',
        mimeType: 'application/json',
        success: function() {
            
             
               $('#wish_modal').modal('show');
               
               
            }



        });
}

function deleteOrder(orderId){
    $.ajax({
        url: window.location.pathname + "order/delete",
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

