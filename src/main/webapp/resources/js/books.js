
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
    
    $('body').on('click', '#my_orders', function() {
        myOrders();
    });
    
    $('body').on('click', '.delete_order_button', function() {
        var orderId = $(this).val();
        deleteOrder(orderId);
    });
    
    $('body').on('click', '.edit_order_button', function() {
        
        var orderId = $(this).val();
        var date = $(this).prev().val();
        
        editOrder(orderId, date);
    });
    

});
     




