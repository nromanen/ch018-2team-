
$(document).ready(function(){
    
    $(".calendar").each(function(){
        
        var minDate = getDateInFormat($(this).prev().val()).split(" ");
        
        $(this).datetimepicker({
                    format: 'Y/m/d H:m',
                    value: minDate[0] + " " + minDate[1],
                    minDate: minDate[0],
                    minTime: minDate[1]
               });
        
    });
    
    $('.wish_delete_button').click(function(){
        var wishId = $(this).prev().val();
        deleteWish(wishId);
    });
    $('.wish_confirm_button').click(function(){
        var date = getLongFromFormatTime($(this).prev().val());
        var wishId = $(this).parent().children().val();
        var bookId = $(this).prev().prev().prev().val();
        
        confirmWish(wishId, bookId, date);
    });
    
});


function deleteWish(wishId){
    
    $.ajax({
        url: window.location.pathname + "wishlist/delete",
        type: "POST",
        data: {'wishId' : wishId},
        dataType: "json",
        contentType: 'application/x-www-form-urlencoded',
        mimeType: 'application/json',
        success: function() {
            
            //myOrders();
            
            $('#wish_li_' + wishId).remove(); 
            }
        });
    
}

function confirmWish(wishId, bookId, date){
    
    $.ajax({
            url: "/library/books/order/add",
            type: "POST",
            data: {'bookId' : bookId, 'time' : date},
            dataType: "json",
            contentType: 'application/x-www-form-urlencoded',
            mimeType: 'application/json',
            
         success: function () {
            
                $('#wish_li_' + wishId).remove(); 
 
             }
         });
}

