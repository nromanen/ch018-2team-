function addToWishList(bookId){
    
    $.ajax({
        url: window.location.pathname + "wishlist/add",
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

function myWishes(){
    
    $.ajax({
        url: window.location.pathname + "wishlist/my",
        type: "POST",
        dataType: "json",
        contentType: 'application/x-www-form-urlencoded',
        mimeType: 'application/json',
        success: function(data) {
            
            
            $('#center_main').empty();
            
            
            var $center_main = $('#center_main');
           
            var $wishes_wrap = $('<div>', {id: 'wishes_wrap'});
            $wishes_wrap.appendTo($center_main);
            
            var $ul = $('<ul>', {class : 'main_wishes_ul'});
            $ul.appendTo($wishes_wrap);
            
            
            $.each(data, function (indx, value) {
                
               
               
               var $li = $('<li>', {id : 'wishes_li_' + value.wishId});
               
               
               var $inner_div_li = $('<div>', {class : 'inner_div_li'});
               var $li_title = $('<div>', {class : 'li_title'});
               var $li_freeFrom = $('<div>', {class : 'li_freefrom'});
               var $li_confirm = $('<div>', {class : 'li_confirm'});
               var $li_delete = $('<div>', {class : 'li_delete'});
               
               $li_title.text(value.title);
               $li_title.appendTo($inner_div_li);
               
               $li_freeFrom.text(value.minDate);
               $li_freeFrom.appendTo($inner_div_li);
               
               var $confirm_input = $('<input>', {class : 'edit_calendar', type : "text", value : value.minDate});
               var minD = value.minDate.split(" ");
               $confirm_input.datetimepicker({
                    format: 'Y/m/d H:m',
                    minDate: minD[0],
                    minTime: minD[1]
               });
               
               
               
               var $confirm_wish_button = $('<button>', {class : 'confirm_wish_button', value : value.wishId}).text('Confirm');
               $confirm_input.appendTo($li_confirm);
               $confirm_wish_button.appendTo($li_confirm);
               
               $li_confirm.appendTo($inner_div_li);
               
               var $delete_wish_button = $('<button>', {class : 'delete_wish_button', value : value.wishId}).text('Delete');
               
               $delete_wish_button.appendTo($li_delete);
               
               $li_delete.appendTo($inner_div_li);
               
               $inner_div_li.appendTo($li);
               
               $li.appendTo($ul);
               
                
            });



        }
    });
    
}

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
            
            $('#wishes_li_' + wishId).remove(); 
            }
        });
    
}

function confirmWish(wishId, date){
    
    $.ajax({
            url: window.location.pathname + "wishlist/confirm",
            type: "POST",
            data: {'wishId' : wishId, 'date' : date},
            dataType: "json",
            contentType: 'application/x-www-form-urlencoded',
            mimeType: 'application/json',
            
         success: function () {
            
                $('#wishes_li_' + wishId).remove(); 
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