
$(document).ready(function(){
    
    $(".calendar").each(function(){
        
        var minD = $(this).val().split(" ");
        $(this).datetimepicker({
                    format: 'Y/m/d H:m',
                    minDate: minD[0],
                    minTime: minD[1]
               });
        
    })
    
    $('.wish_delete_button').click(function(){
        var wishId = $(this).prev().val();
        deleteWish(wishId);
    });
    $('.wish_confirm_button').click(function(){
        var date = $(this).prev().val();
        var bookId = $(this).prev().prev().val();
        var wishId = $(this).prev().prev().prev().val();
        alert(wishId + " " + bookId + " " + date);
        confirmWish(wishId, bookId, date)
    });
    
});



function deleteWish(wishId){
    
    $.ajax({
        url: "delete",
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
            url: "confirm",
            type: "POST",
            data: {'wishId' : wishId, 'bookId' : bookId, 'date' : date},
            dataType: "json",
            contentType: 'application/x-www-form-urlencoded',
            mimeType: 'application/json',
            
         success: function () {
            
                $('#wish_li_' + wishId).remove(); 
                /*$popup = $('<div>', {id : 'popup'});
                $continue_button = $('<button>', {id : 'continue_button'})
                        .text("Continue");
                $wishList_button = $('<button>', {class : 'wishList_button'})
                        .text("View WishList");
                $continue_button.appendTo($popup);
                $wishList_button.appendTo($popup);
                $popup.appendTo($('body'));
                
                $('#center_main').css("opacity", "0.1");
                $popup.css("display", "block");*/
            
             
                 
             }
         });
}