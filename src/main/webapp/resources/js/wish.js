
$(document).ready(function(){
    
    $(".calendar").each(function(){
        
        var minD = $(this).val().split(" ");
        $(this).datetimepicker({
                    format: 'Y/m/d H:m',
                    minDate: minD[0],
                    minTime: minD[1]
               });
        
    });
    
    $('.wish_delete_button').click(function(){
        var wishId = $(this).prev().val();
        deleteWish(wishId);
    });
    $('.wish_confirm_button').click(function(){
        var date = $(this).prev().val();
        var bookId = $(this).prev().prev().val();
        var wishId = $(this).prev().prev().prev().val();
        
        confirmWish(wishId, bookId, date);
    });
    
});


function deleteWish(wishId){
    
    $.ajax({
        url: window.location.pathname + "wishlist/delete",
        type: "POST",
        data: {'wishId' : wishId},
        dataType: "json",
        contentType: 'application/x-www-form-urlencoded',
        mimeType: 'application/json',
        success: function() {
            
            //myOrders();
            
            $('#wishes_li_' + wishId).remove(); 
            }
        });
    
}

function confirmWish(wishId, date){
    
    $.ajax({
            url: "confirm",
            type: "POST",
            data: {'wishId' : wishId, 'date' : date},
            dataType: "json",
            contentType: 'application/x-www-form-urlencoded',
            mimeType: 'application/json',
            
         success: function () {
            
                $('#wishes_li_' + wishId).remove(); 
 
             }
         });
}

