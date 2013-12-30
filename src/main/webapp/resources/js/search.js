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
           var $books = $('<div>', {class : 'row'});
           $books.appendTo($('#center_main'));
               $.each(data.books, function (indx, value){
                
                
                
                var $book_quantity = $('<div>', {class : 'row'});
                var $book_info = $('<div>', {class : 'row'});
                var $book_img = $('<div>', {class : 'row'});
                var $book = $('<div>', {class : 'col-md-3 book'});
                
                
                $('<div>', {class : 'col-md-6'}).text(value.generalQuantity).appendTo($book_quantity);
                $('<div>', {class : 'col-md-6'}).text(value.currentQuantity).appendTo($book_quantity);
                
                $('<div>', {class : 'row'}).text(value.title).appendTo($book_info);
                $('<div>', {class : 'row'}).text("by " + value.authors).appendTo($book_info);
                
                $('<img>', {src : value.img, class : 'img-responsive'}).appendTo($book_img);
                
                $book_img.appendTo($book);
                $book_info.appendTo($book);
                $book_info.appendTo($book);
                $book_quantity.appendTo($book);
                $book.appendTo($books);
                
                
                
                if(data.auth === true){
                    var $book_order = $('<div>', {class : 'book_order'});
                    var $button = $('<button>', {class: 'button', value: value.bId});
                    $button.text("Order");
                    $button.appendTo($book_order);
                    $book_order.appendTo($book);
                }
                
               
            });
};