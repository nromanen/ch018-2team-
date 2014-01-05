$(document).ready(function (){
    
    $('#acc_email_button').click( function() {
        
        if($('#acc_email_button').text() === 'Change'){
            $('#email_text').addClass('hide');
            $('#email_edit').removeClass('hide');
            $('#acc_email_button').text('Confirm');
            
        }else{
            var email = $('#email_edit').val();
            $.ajax({
                url: "/library/account/changeEmail",
                type: "POST",
                data: {'email' : email},
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded',
                mimeType: 'application/json',

                success: function (data) {

                    $('#email_edit').addClass('hide');
                    $('#email_text').removeClass('hide');
                    $('#acc_email_button').text('Change');
                    $('#email_text').text(data.email);

             }
         });
            
            
        }
        
        
         
        });
        
    });
    $('body').on('click', '#acc_email_button_confirm', function() {
       alert("AAA");
       
        
    });

