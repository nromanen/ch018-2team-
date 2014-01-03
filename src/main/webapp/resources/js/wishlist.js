
$(document).ready(function(){
    
    $(".calendar").each(function(){
        
        var minD = $(this).val().split(" ");
        $(this).datetimepicker({
                    format: 'Y/m/d H:m',
                    minDate: minD[0],
                    minTime: minD[1]
               });
        
    });
    
    $('.wish_delete_button').click(function(){
        var wishId = $(this).prev().val();
        deleteWish(wishId);
    });
    $('.wish_confirm_button').click(function(){
        var date = $(this).prev().val();
        var bookId = $(this).prev().prev().val();
        var wishId = $(this).prev().prev().prev().val();
        
        confirmWish(wishId, bookId, date);
    });
    
});

function addToWishList(bookId){
    
    $.ajax({
        url: "/library/books/wishlist/add",
        type: "POST",
        data: {'bookId' : bookId},
        dataType: "json",
        contentType: 'application/x-www-form-urlencoded',
        mimeType: 'application/json',
        success: function(data) {
            
            
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
                var $block = $('<div>', {class : 'popup'});
                var $popup_title = $('<div>', {class : 'row'});
                
                $popup_title.appendTo($block);
                var $popup_buttons_wrap = $('<div>', {class : 'row'});
                var $popup_return_to_books = $('<div>', {class : 'col-md-6'});
                var $ret_button = $('<button>', {class : 'btn-info'}).text('Return to books view');
                $ret_button.appendTo($popup_return_to_books);
                var $popup_return_to_wish = ('<div>', {class : 'col-md-6'});
                var $wish_return_button = $('<button>', {class : 'btn-info'}).text('Return to WishList');
                
                $wish_return_button.appendTo($popup_return_to_wish);
                $popup_return_to_books.appendTo($popup_buttons_wrap);
                $popup_return_to_wish.appendTo($popup_buttons_wrap);
                $popup_buttons_wrap.appendTo($block);
                
                $block.appendTo($('#center_main'));
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