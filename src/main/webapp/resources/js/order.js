
function order(val){
        
        $.ajax({
            url: window.location.pathname + "/order",
            type: "POST",
            data: {'bookid' : val},
            dataType: "json",
            contentType: 'application/x-www-form-urlencoded',
            mimeType: 'application/json',
            
         success: function (data) {
             $('#center_main').empty();
             var $order_wrapper = $('<div>', {id : 'order_wrapper'});
             $order_wrapper.appendTo($('#center_main'));
             
             $book_info_wrapper = $('<div>', {id : 'book_info_wrapper'});
             $book_info_wrapper.appendTo($order_wrapper);
      
             $book_ordering_wrapper = $('<div>', {id : 'book_ordering_wrapper'});
             $book_ordering_wrapper.appendTo($order_wrapper);
             
             $img_div = $('<div>', {id : 'img_div'});
             $img = $('<img>', {id : 'img', src: data.img});
             $img.appendTo($img_div);
             $img_div.appendTo($book_info_wrapper);
             
             $book_info = $('<div>', {id : 'book_info'});
             $title = $('<span>').text(data.title);
             $title.appendTo($book_info);
             $authors = $('<span>').text(data.authors);
             $authors.appendTo($book_info);
             $publisher = $('<span>').text(data.publisher);
             $publisher.appendTo($book_info);
             $description  = $('<span>', {id : 'description'}).text(data.description);
             $description.appendTo($book_info);
             $book_info.appendTo($book_info_wrapper);
             
             /*$book_description = $('<div>', {id : 'book_description'});
             $description = $('<span>').text(data.description);
             $description.appendTo($book_description);
             $book_description.appendTo($book_info_wrapper);*/
             
             $ordering_div = $('<div>', {id : 'ordering_div'});
             $calendar = $('<input>', {id: 'calendar', type : 'text', value : data.minReturn});
             var minD = data.minReturn.split(" ");
             $calendar.datetimepicker({
                format: 'Y/m/d H:m',
                minDate: minD[0],
                minTime: minD[1]
            });
            $calendar.appendTo($ordering_div);
            $order_button = $('<button>', {id : 'order_button', value : data.bId}).text('Order');
            $order_button.appendTo($ordering_div);
            $ordering_div.appendTo($book_ordering_wrapper);
            
            $wishing_div = $('<div>', {id : 'wishing_div'});
            $wish_button = $('<button>', {id : 'wish_button', value : data.bId}).text('Add to WishList');
            $wish_button.appendTo($wishing_div);
            $wishing_div.appendTo($book_ordering_wrapper);
             
             
                 
             }
         });
         
        }
    
function makeOrder(bookId, time){
    $.ajax({
            url: window.location.pathname + "order/add",
            type: "POST",
            data: {'bookId' : bookId, 'time' : time},
            dataType: "json",
            contentType: 'application/x-www-form-urlencoded',
            mimeType: 'application/json',
            
         success: function () {
            
            
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

function myOrders(){
    $.ajax({
        url: window.location.pathname + "order/my",
        type: "GET",
        dataType: "json",
        contentType: 'application/x-www-form-urlencoded',
        mimeType: 'application/json',
        success: function(data) {
            
            
            $('#center_main').empty();
            
            
            var $center_main = $('#center_main');
           
            var $orders_wrap = $('<div>', {id: 'orders_wrap'});
            $orders_wrap.appendTo($center_main);
            
            var $ul = $('<ul>', {class : 'main_orders_ul'});
            $ul.appendTo($orders_wrap);
            
            
            $.each(data, function (indx, value) {
                
               
               
               var $li = $('<li>', {id : 'orders_li_' + value.orderId});
               
               
               var $inner_div_li = $('<div>', {class : 'inner_div_li'});
               var $li_title = $('<div>', {class : 'li_title'});
               var $li_orderDate = $('<div>', {class : 'li_orderDate'});
               var $li_edit = $('<div>', {class : 'li_edit'});
               var $li_delete = $('<div>', {class : 'li_delete'});
               
               $li_title.text(value.title);
               $li_title.appendTo($inner_div_li);
               
               $li_orderDate.text(value.orderDate);
               $li_orderDate.appendTo($inner_div_li);
               
               var $edit_input = $('<input>', {class : 'edit_calendar', type : "text", value : value.orderDate});
               var minD = value.minDate.split(" ");
               $edit_input.datetimepicker({
                    format: 'Y/m/d H:m',
                    minDate: minD[0],
                    minTime: minD[1]
               });
               
               
               var $edit_order_button = $('<button>', {class : 'edit_order_button', value : value.orderId}).text('Change');
               $edit_input.appendTo($li_edit);
               $edit_order_button.appendTo($li_edit);
               
               $li_edit.appendTo($inner_div_li);
               
               var $delete_order_button = $('<button>', {class : 'delete_order_button', value : value.orderId}).text('Delete');
               
               $delete_order_button.appendTo($li_delete);
               
               $li_delete.appendTo($inner_div_li);
               
               $inner_div_li.appendTo($li);
               
               $li.appendTo($ul);
               
                
            });



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
            $('#orders_li_' + orderId).remove();
               
                
            }



        });
    
}

function editOrder(orderId, date){
    $.ajax({
        url: window.location.pathname + "order/edit",
        type: "POST",
        data: {'orderId' : orderId, 'date' : date},
        dataType: "json",
        contentType: 'application/x-www-form-urlencoded',
        mimeType: 'application/json',
        success: function(data) {
            
                var $li = $('#orders_li_' + orderId);
                $edit_input.appendTo($li);
           
                var $edit_input = $('<input>', {class : 'edit_calendar', type : "text", value : date});
                $edit_input.appendTo($li);
                var minD = data.minDate.split(" ");
                 $edit_input.datetimepicker({
                    format: 'Y/m/d H:m',
                    minDate: minD[0],
                    minTime: minD[1]
               });
               
               
            }



        });
    
}