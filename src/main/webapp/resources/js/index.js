$.validator.addMethod('customphone', function(value, element) {
	return this.optional(element) || /^\d{3}-\d{3}-\d{4}$/.test(value);
}, "Please enter a valid phone number");

$(document).ready(function() {
	

	$('#reg_phone').mask("999-999-9999");

	$('#restore').click(function() {
		$('#forgot_pass').modal("show");
	});

	$('#restore_mail_form').validate({
		errorClass : "my-error-class",
		submitHandler : function(form) {
			$.ajax({
				url : $('#restore_mail_form_submit').attr('url'),
				type : "POST",
				data : $('#restore_mail_form').serialize(),
				dataType : "json",
				contentType : 'application/x-www-form-urlencoded',
				mimeType : 'application/json',
				success : function() {
					$('#forgot_pass').modal('hide');
					$('#forgot_mail_send').modal('show');

				},
				error : function(xhr, status, error) {

					$('#forgot_error_mail_div').text(xhr.responseText);
					$('#forgot_error_mail_div').removeClass('hide');
				}
			});
		}
	});

	$('#registration_form').validate({
		errorClass : "my-error-class",
		rules : {
			name : {
				required : true,
				minlength : 2,
				maxlength : 20
			},
			surname : {
				required : true,
				minlength : 2,
				maxlength : 20
			},
			cellphone : 'customphone'
		},
		messages : {
			name : {
				required : 'name is required'
			},
			surname : {
				required : 'surname is required'
			},
			rEmail : {
				equalTo : 'entered email\'s don\'t match'
			},
			rPass : {
				equalTo : 'entered password\'s don\'t match'
			}
		},
		submitHandler : function(form) {

			$.ajax({
				url : $('#form_submit').attr('url'),
				type : "POST",
				data : $('#registration_form').serialize(),
				dataType : "json",
				contentType : 'application/x-www-form-urlencoded',
				mimeType : 'application/json',
				success : function() {
					$('#success_reg').modal('show');

				},
				error : function(xhr, status, error) {

					$('#error_div').text(xhr.responseText);
					$('#error_div').removeClass('hide');
				}
			});

		}

	});

	$('#restore_pass_form').validate({
		errorClass : "my-error-class",
		submitHandler : function(form) {
			$.ajax({
				url : $('restore_pass_submit').attr('url'),
				type : "POST",
				data : $('#restore_pass_form').serialize(),
				dataType : "json",
				contentType : 'application/x-www-form-urlencoded',
				mimeType : 'application/json',
				success : function() {
					location.href = "/";
				},
				error : function(xhr, status, error) {

					$('#restore_pass_err').text(xhr.responseText);
					$('#restore_pass_err').removeClass('hide');
				}
			});

		}
	});

});