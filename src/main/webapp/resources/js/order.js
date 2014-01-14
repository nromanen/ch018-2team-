

$(document).ready(function(){
    
    var isLimitReacher = $('#book_limit').val();
    if(isLimitReacher === 'true')
        $('#book_limit_modal').modal('show');
    
    tmpDate = $('#minDate').val();
    
    minD = getDateInFormat(tmpDate);
    minDateSpl = minD.split(" ");
    
    $('#datetimepicker').datetimepicker({
                onGenerate:function( ct , $input ){
                    $(this).find('.xdsoft_date.xdsoft_weekend')
                            .addClass('xdsoft_disabled');
                    
                    
                },
                onSelectDate:function(current_time,$input){
                    var days = getAvailableDays(current_time, $('.order'));
                    
                    if($(this).find($('#picker_notify')).attr('id') === undefined){
                        console.log($(this));
                        var $div = $('<div>', {id: 'picker_notify', style : 'position : absolute; left: 100%; width:100px; height:100px; border : 2px solid black'}).text(days);
                        $div.appendTo($(this));
                    
                    }else{
                        $('#picker_notify').text(days);
                    }
                    
                },
                format: 'Y/m/d H:i',
                value: minD,
                minDate: minDateSpl[0],
                weekends: getWeekEnds($('.order')),
                allowTimes:[
                    '09:00', '10:00', '11:00', '12:00', '14:00',
                    '15:00', '16:00'
                ],
                validateOnBlur: true
            });
    $('#order_button').click(function() {
        
        var bookId = $('#bookId').val();
        var time = getLongFromFormatTime($('#datetimepicker').val());
        makeOrder(bookId, time);
        
    });
    
    $('#wish_button').click(function() {
       bookId = $(this).prev().val();
        addToWishList(bookId);
    });
    
    

});

    
function makeOrder(bookId, time){
    $.ajax({
            url: "/library/books/order/add",
            type: "POST",
            data: {'bookId' : bookId, 'time' : time},
            dataType: "json",
            contentType: 'application/x-www-form-urlencoded',
            mimeType: 'application/json',
            
         success: function () {
                
                $('#order_modal').modal('show');
                
             }
         });
}   

function addToWishList(bookId){
    
    $.ajax({
        url: "/library/books/wishlist/add",
        type: "POST",
        data: {'bookId' : bookId},
        dataType: "json",
        contentType: 'application/x-www-form-urlencoded',
        mimeType: 'application/json',
        success: function() {
            
             
               $('#wish_modal').modal('show');
               
               
            }



        });
}



