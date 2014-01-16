
$(document).ready(function () {
   
     //search("");
     
     //advancedSearchPanel();
     
     
   $('body').on({
       mouseenter : function(){
           
           
           $(this).next().delay(1600).removeClass('hide');
           $(this).next().hover(function(){
            
        }, function(){
            $(this).addClass('hide');
        });
       }
   }, '.book1');  
     
     
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
