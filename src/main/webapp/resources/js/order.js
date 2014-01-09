

$(document).ready(function(){
    
    var isLimitReacher = $('#book_limit').val();
    if(isLimitReacher === 'true')
        $('#book_limit_modal').modal('show');
    
    tmpDate = $('#minDate').val();
    
    minD = getDateInFormat(tmpDate);
    minDateSpl = minD.split(" ");
    $('#datetimepicker').datetimepicker({
                format: 'Y/m/d H:i',
                value: minD,
                minDate: minDateSpl[0],
                minTime: minDateSpl[1],
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

