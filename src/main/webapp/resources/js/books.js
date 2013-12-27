
$(document).ready(function () {
   
     search("");
     
     $('#search_field').autocomplete({
         serviceUrl: window.location.pathname + "autocomplete",
         minChars: 2
         
     });
     
    $('body').on('click', '.button', function() {
        order($(this).val());
    });
    $('body').on('click', '#order_button', function() {
        var bookId = $(this).val();
        var time = $(this).prev().val();
        makeOrder(bookId, time);
    });

});
     




