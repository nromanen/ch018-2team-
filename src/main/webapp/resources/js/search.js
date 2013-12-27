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
             
           $('#center_main').empty();
           var $books = $('<div>', {id : 'books'});
           $books.appendTo($('#center_main'));
               $.each(data.books, function (indx, value){
                
                
                
                var $book_quantity = $('<div>', {class : 'book_quantity'});
                var $book_info = $('<div>', {class : 'book_info'});
                var $book_img = $('<div>', {class : 'book_img'});
                var $book = $('<div>', {class : 'book'});
                
                
                $('<span></span>').text(value.generalQuantity).appendTo($book_quantity);
                $('<span></span>').text(value.currentQuantity).appendTo($book_quantity);
                
                $('<span></span>').text(value.title).appendTo($book_info);
                $('<b></b>').text("by " + value.authors).appendTo($book_info);
                
                $('<img>', {src : value.img}).appendTo($book_img);
                
                $book_img.appendTo($book);
                $book_info.appendTo($book);
                $book_info.appendTo($book);
                $book_quantity.appendTo($book);
                $book.appendTo($books);
                
                if(data.auth === true){
                    var $book_order = $('<div>', {class : 'book_order'});
                    var $a = $('<a>', {class: 'button', href: '/library/books/order?bookid=' + value.bId});
                    $a.appendTo($book_order);
                    $book_order.appendTo($book);
                }
                
               
            });
          
           }
       
    });     
}