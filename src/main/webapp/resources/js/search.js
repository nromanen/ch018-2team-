function search(query){
    //var query = $('#search_field').val();
    /*var booksOnPage = $('#booksOnPage').attr('value');
    var viewPageNum = $('#currentPage').attr('value');
    var sort = $('#orderBy').attr('value');
    var order = $('#order').attr('value');*/
    $.ajax({
            url: "/library/books",
            type: "POST",
            data: {'query' : query}, //'booksOnPage' : booksOnPage, 'viewPageNum' : viewPageNum, 'sort' : sort, 'order' : 'order'},
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
           var $books_row = $('<div>', {class : 'row'});
           var $ul = $('<ul>', {class : 'list-inline list-unstyled'});
           $ul.appendTo($books_row);
           $books_row.appendTo($('#center_main'));
               $.each(data.books, function (indx, value){
                
                
                var $li = $('<li>', {class : 'col-md-3'});
                $li.appendTo($ul);
                
                var $thumbnail = $('<div>', {class : 'thumbnail book1', style : 'height: 280px;'});
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
                var $p_authors_ext = $('<p>').text('Authors: ' + value.authors);
                var $p_publisher_ext = $('<p>').text('Publisher: ' + value.publisher);
                
                $h6_ext.appendTo($caption_ext);
                $p_quantity_ext.appendTo($caption_ext);
                $p_authors_ext.appendTo($caption_ext);
                $p_publisher_ext.appendTo($caption_ext);
                
                if(data.auth === true){
                    var $book_order = $('<p>');
                    var $button = $('<a>', {class: 'btn btn-info', href: '/library/books/order?id=' + value.bId});
                    $button.text("Order");
                    $button.appendTo($book_order);
                    $book_order.appendTo($thumbnail_ext);
                    
                }
            
               
            });
            
            
            var $page_row = $('<div>', {class : 'row'});
            var $ul_page = $('<ul>', {class : 'pager'});
            if(data.currentPage > 1){
                var previous = data.currentPage - 1;
                var next = data.currentPage + 1;
                var $li_page_prev = $('<li>').append($('<a>', {id : 'previous', value : previous, style: 'color : black'}).text('Previous'));
                var $li_page_next = $('<li>').append($('<a>', {id : 'next', value : next, style: 'color : black'}).text('Next'));
                $li_page_prev.appendTo($ul_page);
                $li_page_next.appendTo($ul_page);
            }else{
                
                var next = data.currentPage + 1;
                var $li_page_next = $li_page_next = $('<li>').append($('<a>', {id : next, value : next, style: 'color : black'}).text('Next'));
                $li_page_next.appendTo($ul_page);
            }
            $ul_page.appendTo($page_row);
            $page_row.appendTo($('#center_main'));
            
            
            
};


