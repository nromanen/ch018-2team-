$(document).ready(function () {
    var bookid = $('#bookid').val();
    $.ajax({
        
        url: window.location.path,
        type: 'POST',
        data: {'bookid' : bookid},
        dataType: "json",
        contentType: 'application/x-www-form-urlencoded',
        mimeType: 'application/json',
            
         success: function (data) {
             alert(data.minReturn);
         }
        
    });
    
});
