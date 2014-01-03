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
           
                var $order_a = $('<a>', {href : "order?id=" + value.bId});
                $('<img>', {src : value.img}).appendTo($book_img_wrapper).appendTo($order_a);
                $order_a.appendTo($book_img_wrapper);
                
                $book_img_wrapper.appendTo($book);
                $book_title.appendTo($book);
                $book_quantity.appendTo($book);
                $book.appendTo($li);
                
                //------book_ext
                
                var $book_ext_quantity = $('<div>', {class : 'book_quantity'});
                var $book_ext_title = $('<div>', {class : 'book_title'});
                var $book_ext_authors = $('<div>', {class : 'book_title'});
                var $book_ext_publisher = $('<div>', {class : 'book_title'});;
                var $book_ext_back = $('<div>', {class : 'book_ext_back'});
                $book_ext_back.css("background-color", "white");
                var $book_ext = $('<div>', {class : 'book_ext'});
                var $book_ext_img_wrapper = $('<div>', {class : 'book_ext_img_wrapper'});
                
                var $order_a_ext = $('<a>', {href : "/library/books/order?id=" + value.bId});
                $('<img>', {src : value.img}).appendTo($book_ext_img_wrapper).appendTo($order_a_ext);
                $order_a_ext.appendTo($book_ext_img_wrapper);
                
                $('<span>').text("Quantity").appendTo($book_ext_quantity);
                $('<span>').text(value.generalQuantity).appendTo($book_ext_quantity);
                $('<span>').text(value.currentQuantity).appendTo($book_ext_quantity);
                
                $book_ext_title.text(value.title);
                
                $book_ext_authors.text("Authors: " + value.authors);
                
                $book_ext_publisher.text("Publisher: " + value.publisher);
                
                $book_ext_img_wrapper.appendTo($book_ext_back);
                $book_ext_title.appendTo($book_ext_back);
                $book_ext_authors.appendTo($book_ext_back);
                $book_ext_publisher.appendTo($book_ext_back);
                $book_ext_quantity.appendTo($book_ext_back);
                $book_ext_back.appendTo($book_ext);
                $book_ext.appendTo($li);
                
                /*if(data.auth === true){
                    var $book_order = $('<div>', {class : 'book_order'});
                    var $button = $('<button>', {class: 'order_button', value: value.bId});
                    $button.text("Order");
                    $button.appendTo($book_order);
                    $book_order.appendTo($book);
                }*/
                
               
            });
};