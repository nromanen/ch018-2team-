$(document).ready(function () {
     
     $('#search_field').autocomplete({
         serviceUrl: "/library/books/autocomplete",
         minChars: 2
         
     });
     
     $('body').on('click', '#search_button', function(e) {
        e.preventDefault();
        var url = $(this).attr('url');
        var query = $('#search_field').val();
        search(query, url);   
    });

    $('body').on('click', '#advanced_search_button', function() {
        
        
        $('#advanced_search_panel').toggle("slow");
       
    });
    
    $('body').on('click', '#advanced_search_submit', function() {
        var url = $(this).attr('url');
        advancedSearch(url);
    });
});


function search(query, url){
    doPost({query : query}, url);
  
}

function advancedSearch(url){
    
        var title = $('#advanced_search_title').val();
        var authors = $('#advanced_search_authors').val();
        var publisher = $('#advanced_search_publisher').val();
        var genreId = $('#advanced_search_select').val();
        doPost({title : title, authors : authors, publisher : publisher, genreId : genreId}, url);
    
}

function doPost(params, url) {
    
    $form = $('<form>', {action: url, method: 'POST', style: 'display:none'});
    
    $.each(params, function (index, value) {
        $('<input>', {name : index, val : value}).appendTo($form);
    });
    
    $form.appendTo('body');
    $form.submit();
}
