function search(query){
    //var query = $('#search_field').val();
    $.ajax({
            url: "/library/books",
            type: "POST",
            data: {'query' : query},
            dataType: "json",
            contentType: 'application/x-www-form-urlencoded',
            mimeType: 'application/json',
            
         success: function (data) {
             
             buildMainBooksFromJson(data);
          
           }
        
     });
     
     
  
}

function advancedSearch(){
    
        var title = $('#advanced_search_title').val();
        var authors = $('#advanced_search_authors').val();
        var publisher = $('#advanced_search_publisher').val();
        var genreId = $('#advanced_search_select').val();
        
        $.ajax({
            url: "/library/books/advancedSearch",
            type: "POST",
            data: {'title' : title, 'authors' : authors, 'publisher' : publisher, 'genreId' : genreId},
            dataType: "json",
            contentType: 'application/x-www-form-urlencoded',
            mimeType: 'application/json',
            
         success: function (data) {
                buildMainBooksFromJson(data);
           }
        
     });
   
    
}

function advancedSearchPanel(){
    
    $.ajax({
            url: window.location.pathname + "advancedSearch/getGenres",
            type: "POST",
            dataType: "json",
            contentType: 'application/x-www-form-urlencoded',
            mimeType: 'application/json',
            
         success: function (data) {
             
                var $select = $('#advanced_search_select');
                $.each(data, function (indx, value){
                   
                    $('<option>', {value : value.id}).text(value.description).appendTo($select);
                    
                    
                });
                
          
           }
        
     });
    
}

function buildMainBooksFromJson(data){
    
    $('#center_main').empty();
           var $ul = $('<ul>', {class : 'list-inline list-unstyled'});
           $ul.appendTo($('#center_main'));
               $.each(data.books, function (indx, value){
                
                
                var $li = $('<li>', {class : 'col-md-3'});
                $li.appendTo($ul);
                
                var $thumbnail = $('<div>', {class : 'thumbnail book1'});
                $thumbnail.appendTo($li);
                var $img = $('<img>', {src : value.img, style : 'height: 180px;'});
                $img.appendTo($thumbnail);
                var $caption = $('<div>', {class : 'caption'});
                $caption.appendTo($thumbnail);
                var $h6 = $('<h6>').text(value.title);
                var $p_quantity = $('<p>').text('Quantity:\n' + 'Current: ' + value.currentQuantity + '\n' + 'General: ' + value.generalQuantity);
                $h6.appendTo($caption);
                $p_quantity.appendTo($caption);
                
                var $thumbnail_ext = $('<div>', {class : 'thumbnail hide book1_ext', style : 'position: absolute; top: 0; z-index: 9999'});
                $thumbnail_ext.appendTo($li);
                var $img_ext = $('<img>', {src : value.img, style : 'width: 350px;'});
                $img_ext.appendTo($thumbnail_ext);
                var $caption_ext = $('<div>', {class : 'caption'});
                $caption_ext.appendTo($thumbnail_ext);
                var $h6_ext = $('<h6>').text(value.title);
                var $p_quantity_ext = $('<p>').text('Quantity:\n' + 'Current: ' + value.currentQuantity + '\n' + 'General: ' + value.generalQuantity);
                $h6_ext.appendTo($caption_ext);
                $p_quantity_ext.appendTo($caption_ext);
                
                if(data.auth === true){
                    var $book_order = $('<div>', {class : 'book_order'});
                    var $button = $('<a>', {class: 'btn btn-info', href: '/library/books/order?id=' + value.bId});
                    $button.text("Order");
                    $button.appendTo($thumbnail_ext);
                    
                }
                
                
                //------book_ext
                
                
                
                /*if(data.auth === true){
                    var $book_order = $('<div>', {class : 'book_order'});
                    var $button = $('<button>', {class: 'order_button', value: value.bId});
                    $button.text("Order");
                    $button.appendTo($book_order);
                    $book_order.appendTo($book);
                }*/
                
               
            });
};

/*function editOrder(orderId, date){
    $.ajax({
        url: window.location.pathname + "order/edit",
        type: "POST",
        data: {'orderId' : orderId, 'date' : date},
        dataType: "json",
        contentType: 'application/x-www-form-urlencoded',
        mimeType: 'application/json',
        success: function(data) {
            
                var $li = $('#orders_li_' + orderId);
                
                $li.find('.li_orderDate').text(date);
                var $edit_input = $li.find(".edit_calendar");
                var minD = data.minDate.split(" ");
                 $edit_input.datetimepicker({
                    format: 'Y/m/d H:m',
                    minDate: minD[0],
                    minTime: minD[1]
               });
               
               
            }



        });
    
}*/
