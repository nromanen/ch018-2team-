$.validator.addMethod('customphone', function (value, element) {
    return this.optional(element) || /^\d{3}-\d{3}-\d{4}$/.test(value);
}, "Please enter a valid phone number");

$(document).ready(function () {
    
    $('#view_books').click(function () {
       
        location.href = "books";
        
    });
    
   /* $('#registration_form').validate({
        
        rules: {
            name: {
                required : true,
                minlength : 2,
                maxlength : 20
            },
            surname: {
                required : true,
                minlength : 2,
                maxlength: 20
            },
            cellphone : 'customphone'
        },
        messages :{
            name : {
                required : 'name is required'
            },
            surname : {
                required : 'surname is required'
            },
            rEmail :{
                equalTo : 'entered email\'s don\'t match'
            },
            rPass :{
                equalTo : 'entered password\'s don\'t match'
            }
        }
        
    });*/
    
    $('#form_submit').click(function(event){
        event.preventDefault();
        var form_input = $('#registration_form').serialize();
        
        $.ajax({
            url: "/library/register",
            type: "POST",
            data: form_input,
            dataType: "json",
            contentType: 'application/x-www-form-urlencoded',
            mimeType: 'application/json',
         success: function () {
                alert("AA");
                $('#success_reg').modal('show');
                
             },
         error: function(xhr, status, error){
                 
                $('#error_div').text(xhr.responseText);
                $('#error_div').removeClass('hide');
         }
         });
        
    });
    
    
});