
function search(){
    var query = $('#search_field').val();
    alert(query);
    $.ajax({
         url:    'search?query=' 
                  + query,
         success: function(data) {
                     $('html').html(data);
                  },
         async:   false
    });     
}
