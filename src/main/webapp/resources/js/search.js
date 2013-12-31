function search(query){
    //var query = $('#search_field').val();
    $.ajax({
            url: window.location.pathname,
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
            url: window.location.pathname + "/advancedSearch",
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
           var $ul = $('<ul>', {class : 'list-inline'});
           $ul.appendTo($('#center_main'));
               $.each(data.books, function (indx, value){
                
                
                var $li = $('<li>');
                $li.appendTo($ul);
                var $book_quantity = $('<div>', {class : 'book_quantity'});
                var $book_title = $('<div>', {class : 'book_title'});
                var $book_img_wrapper = $('<div>', {class : 'book_img_wrapper'});
                var $book = $('<div>', {class : 'book'});
                
                $('<span>').text("Quantity").appendTo($book_quantity);
                $('<span>').text(value.generalQuantity).appendTo($book_quantity);
                $('<span>').text(value.currentQuantity).appendTo($book_quantity);
                
                
                $book_title.text(value.title);
           
                $a = $('<a>', {href : "order?id=" + value.bId});
                $('<img>', {src : value.img}).appendTo($book_img_wrapper).appendTo($a);
                $a.appendTo($book_img_wrapper);
                
                $book_img_wrapper.appendTo($book);
                $book_title.appendTo($book);
                $book_quantity.appendTo($book);
                $book.appendTo($li);
                
                //------book_ext
                /*
                var $book_ext = $('<div>', {class : 'book_ext'});
                var $book_ext_img_wrapper = $('<div>', {class : 'book_ext_img_wrapper'});
                $a.appendTo($book_ext_img_wrapper);
                $book_ext_img_wrapper.appendTo($book_ext);
                $book_title.appendTo($book_ext);
                $book_quantity.appendTo($book_ext);
                $book_ext.appendTo($li);*/
                
                /*if(data.auth === true){
                    var $book_order = $('<div>', {class : 'book_order'});
                    var $button = $('<button>', {class: 'order_button', value: value.bId});
                    $button.text("Order");
                    $button.appendTo($book_order);
                    $book_order.appendTo($book);
                }*/
                
               
            });
};