
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
    
    $('body').on('click', '#wish_button', function() {
        var bookId = $(this).val();
        addToWishList(bookId);
    });
    
    $('body').on('click', '#my_wishlist', function() {
        myWishes();
    });
    
    $('body').on('click', '.delete_wish_button', function() {
        var wishId = $(this).val();
        deleteWish(wishId);
    });
    
    $('body').on('click', '.confirm_wish_button', function() {
        var wishId = $(this).val();
        var date = $(this).prev().val();
        confirmWish(wishId, date);
    });
    
    $('body').on('click', '#my_books', function() {
        myBooks();
    });

});
     




