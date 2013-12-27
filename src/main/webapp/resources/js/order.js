
function order(val){
        
        $.ajax({
            url: window.location.pathname + "order",
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
            
            
                $popup = $('<div>', {id : 'popup'});
                $continue_button = $('<button>', {id : 'continue_button'})
                        .text("Continue");
                $wishList_button = $('<button>', {class : 'wishList_button'})
                        .text("View WishList");
                $continue_button.appendTo($popup);
                $wishList_button.appendTo($popup);
                $popup.appendTo($('body'));
                
                $('#center_main').css("opacity", "0.1");
                $popup.css("display", "block");
            
             
                 
             }
         });
}   


