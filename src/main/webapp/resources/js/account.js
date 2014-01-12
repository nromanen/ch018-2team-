$(document).ready(function (){
    
    $('body').click(function () {
       
        $('#error_div').addClass('hide');
        
    });
    
    $('#acc_email_button').click(function (){
        if($('#acc_email_button').text() === 'Change'){
            $('#email_text').addClass('hide');
            $('#email_edit').removeClass('hide');
            $('#acc_email_button').text('Confirm');
            
        }else{
            
            var field = $('#email_edit').val();
            $.ajax({
                url: "/library/account/changeEmail",
                type: "POST",
                data: {'email' : field},
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded',
                mimeType: 'application/json',

                success: function (data) {

                    window.location.href = "/library";

             },
               error: function(xhr, status, error){
                 
                    $('#error_div').text(xhr.responseText);
                    $('#error_div').removeClass('hide');
               
               }
         });
            
            
        }
    });
    
    $('#acc_name_button').click(function (){
        if($('#acc_name_button').text() === 'Change'){
            $('#name_text').addClass('hide');
            $('#name_edit').removeClass('hide');
            $('#acc_name_button').text('Confirm');
            
        }else{
            
            var field = $('#name_edit').val();
            $.ajax({
                url: "/library/account/changeField",
                type: "POST",
                data: {'fieldName' : 'name', 'fieldValue' : field},
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded',
                mimeType: 'application/json',

                success: function (data) {

                    $('#name_edit').addClass('hide');
                    $('#name_text').removeClass('hide');
                    $('#acc_name_button').text('Change');
                    $('#name_text').text(data.field);

             }
         });
            
            
        }
    });
    
    $('#acc_password_button').click(function (){
        if($('#acc_password_button').text() === 'Change Password'){
            
            $('#old_pass').removeClass('hide');
            $('#new_pass').removeClass('hide');
            $('#re_new_pass').removeClass('hide');
            
            $('#acc_password_button').text('Confirm');
            
        }else{
            
            var old_pass = $('#old_pass').val();
            var new_pass = $('#new_pass').val();
            var re_new_pass = $('#re_new_pass').val();
            
            $.ajax({
                url: "/library/account/changePassword",
                type: "POST",
                data: {'oldPass' : old_pass, 'newPass' : new_pass, 'rNewPass' : re_new_pass},
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded',
                mimeType: 'application/json',

                success: function () {

                    window.location.href = "/library";

             },
              error: function(xhr, status, error){
                 alert(xhr.responseText);
                $('#error_pass_div').text(xhr.responseText);
                $('#error_pass_div').removeClass('hide');
              }
         });
            
            
        }
    });
        
        $('#acc_phone_button').click(function (){
        if($('#acc_phone_button').text() === 'Change'){
            $('#phone_text').addClass('hide');
            $('#phone_edit').removeClass('hide');
            $('#acc_phone_button').text('Confirm');
            
        }else{
            
            var field = $('#phone_edit').val();
            $.ajax({
                url: "/library/account/changeField",
                type: "POST",
                data: {'fieldName' : 'phone', 'fieldValue' : field},
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded',
                mimeType: 'application/json',

                success: function (data) {

                    $('#phone_edit').addClass('hide');
                    $('#phone_text').removeClass('hide');
                    $('#acc_phone_button').text('Change');
                    $('#phone_text').text(data.field);

             }
         });
            
            
        }
    });
    
    
    $('#acc_surname_button').click(function (){
        if($('#acc_surname_button').text() === 'Change'){
            $('#surname_text').addClass('hide');
            $('#surname_edit').removeClass('hide');
            $('#acc_surname_button').text('Confirm');
            
        }else{
            
            var field = $('#surname_edit').val();
            $.ajax({
                url: "/library/account/changeField",
                type: "POST",
                data: {'fieldName' : 'surname', 'fieldValue' : field},
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded',
                mimeType: 'application/json',

                success: function (data) {

                    $('#surname_edit').addClass('hide');
                    $('#surname_text').removeClass('hide');
                    $('#acc_surname_button').text('Change');
                    $('#surname_text').text(data.field);

             }
         });
            
            
        }
    });
        
        
        
        
    });
   
