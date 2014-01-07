$.validator.addMethod('customphone', function (value, element) {
    return this.optional(element) || /^\d{3}-\d{3}-\d{4}$/.test(value);
}, "Please enter a valid phone number");

$(document).ready(function () {
    
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
    
});