
$(document).ready(function () {
   
   
   
   $.ajax({
      
       
       url: "/library/books",
       type: "POST",
       dataType: "json",
       contentType: 'application/json',
       mimeType: 'application/json',
       success: function (data) {
            
           $('#books').empty();
           $.each(data, function (indx, value){
                
                var $book_order = $('<div>', {class : 'book_order'});
                var $book_quantity = $('<div>', {class : 'book_quantity'});
                var $book_info = $('<div>', {class : 'book_info'});
                var $book_img = $('<div>', {class : 'book_img'});
                var $book = $('<div>', {class : 'book'});
                
                var $a = $('<a>', {class: 'button', href: '/library/books/order?bookid=' + value.bId});
                $a.appendTo($book_order);
                
                $('<span></span>').text(value.generalQuantity).appendTo($book_quantity);
                $('<span></span>').text(value.currentQuantity).appendTo($book_quantity);
                
                $('<span></span>').text(value.title).appendTo($book_info);
                $('<b></b>').text("by " + value.authors).appendTo($book_info);
                
                $('<img>', {src : value.img}).appendTo($book_img);
                
                $book_img.appendTo($book);
                $book_info.appendTo($book);
                $book_info.appendTo($book);
                $book_quantity.appendTo($book);
                $book_order.appendTo($book);
                $book.appendTo($('#books'));
                
                
               
            });
       }
   }); 
  
    
});


function search(){
    var query = $('#search_field').val();
    alert(query);
    $.ajax({
         url:    'search?query=' 
                  + query,
         success: function(data) {
                     $('html').html(data);
                  },
         async:   false
    });     
}
