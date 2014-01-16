$.validator.addMethod('customphone', function (value, element) {
    return this.optional(element) || /^\d{3}-\d{3}-\d{4}$/.test(value);
}, "Please enter a valid phone number");
$(document).ready(function (){
    
    $('#acc_cellphone').mask("999-999-9999");
    
    $('body').click(function () {
       
        $('#error_div').addClass('hide');
        
    });
   
    $('#nav_bar a').click(function (e) {
       
        e.preventDefault();
        $(this).tab('show');
        
    });
    $('#info_form').validate({
       
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
        submitHandler: function(form) {
            
            
            $.ajax({
            url: $('#info_form').attr('action'),
            type: "POST",
            data: $('#info_form').serialize(),
            dataType: "json",
            contentType: 'application/x-www-form-urlencoded',
            mimeType: 'application/json',
         success: function () {
                $('#info_success').text('Information successfully changed');
                $('#info_success').removeClass('hide');
             },
         error: function(xhr, status, error){
                $('#info_error').text(xhr.responseText);
                $('#info_error').removeClass('hide');
         }
         });
        return false;
        }
    });
    
    $('#pass_form').validate({
        rules: {
            oldPass: {
                required : true,
                minlength : 6,
                maxlength : 16
            },
            NewPass: {
                required : true,
                minlength : 6,
                maxlength : 16
            },
            rNewPass: {
                required : true,
                minlength : 6,
                maxlength : 16
            },
        messages :{
            rNewPass :{
                equalTo : 'entered password\'s don\'t match'
            }
        }
        },   
        submitHandler: function(form) {


            $.ajax({
                url: $('#pass_form').attr('action'),
                type: "POST",
                data: $('#pass_form').serialize(),
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded',
                mimeType: 'application/json',
                success: function() {
                    $('#pass_success').text('Password successfully changed. You will be redirected to login page');
                    $('#pass_success').removeClass('hide');
                    setTimeout(function() {
                        window.location.href = "/library/";
                    }, 3000);
                },
                error: function(xhr, status, error) {
                    $('#pass_error').text(xhr.responseText);
                    $('#pass_error').removeClass('hide');
                }
            });
            return false;
            }
            });
            
            

    $('#email_form').validate({
        rules: {
            email: {
                required : true,
                maxlength : 30
            }
        },
        
        submitHandler: function(form) {


            $.ajax({
                url: $('#email_form').attr('action'),
                type: "POST",
                data: $('#email_form').serialize(),
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded',
                mimeType: 'application/json',
                success: function(data) {
                    $('#email_success').text('Email successfully changed to ' + data.email + ' You will be redirected to login page');
                    $('#email_success').removeClass('hide');
                    setTimeout(function() {
                        window.location.href = "/library/";
                    }, 4000);
                },
                error: function(xhr, status, error) {
                    $('#email_error').text(xhr.responseText);
                    $('#email_error').removeClass('hide');
                }
            });
            return false;
            }
    });
    
    

    $('body').click(function() {

        $('#info_error').addClass('hide');
        $('#mail_error').addClass('hide');
        $('#pass_error').addClass('hide');
        $('#info_success').addClass('hide');
        $('#mail_success').addClass('hide');
        $('#pass_success').addClass('hide');

    });
   
    
    });
   
