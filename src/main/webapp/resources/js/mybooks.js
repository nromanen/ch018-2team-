function myBooks(){
    
    $.ajax({
        url: window.location.pathname + "mybooks",
        type: "POST",
        dataType: "json",
        contentType: 'application/x-www-form-urlencoded',
        mimeType: 'application/json',
        success: function(data) {
            
            
            $('#center_main').empty();
            
            
            var $center_main = $('#center_main');
           
            var $myBooks_wrap = $('<div>', {id: 'mybooks_wrap'});
            $myBooks_wrap.appendTo($center_main);
            
            var $ul = $('<ul>', {class : 'main_mybooks_ul'});
            $ul.appendTo($myBooks_wrap);
            
            
            $.each(data, function (indx, value) {
                
               
               
               var $li = $('<li>', {class : 'mybooks_li_'});
               
               
               var $inner_div_li = $('<div>', {class : 'inner_div_li'});
               var $li_title = $('<div>', {class : 'li_title'});
               var $li_returnDate = $('<div>', {class : 'li_returndate'});
               var $li_daysLeft = $('<div>', {class : 'li_daysleft'});
               
               
               $li_title.text(value.title);
               $li_title.appendTo($inner_div_li);
               
               $li_returnDate.text(value.returnDate);
               $li_returnDate.appendTo($inner_div_li);
               
               $li_daysLeft.text(value.days);
               $li_daysLeft.appendTo($inner_div_li);
               
               $inner_div_li.appendTo($li);
               
               $li.appendTo($ul);
               
                
            });



        }
    });
    
}

