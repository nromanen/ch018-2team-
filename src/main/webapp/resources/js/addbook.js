
$(document).ready(function () {

	$('#addbook').validate({
		
		errorClass : "my-error-class",
		
		rules : {
			title : {
				required : true,
				minlength : 2,
				maxlength : 30
			},
			authors : {
				maxlength : 30
			},
			year : {
				required : true,
				number : true,
				min : 1990,
				max : new Date().getFullYear()
			},
			publisher : {
				maxlength : 30
			},
			pages : {
				required : true,
				number : true,
				min : 1,
				max : 10000
			},
			description : {
				maxlength : 2048
			},
			shelf : {
				required : true,
				number : true,
				min : 1,
				max : 50
			},
			generalQuantity : {
				required : true,
				number : true,
				min : 0,
				max : 1000
			}
			
		},
		messages : {
			year : 'must be between 1990 and ' + new Date().getFullYear(),
			pages : 'must be between 1 and 10000',
			shelf : 'must be between 1 and 50',
			
		},
		submitHandler : function(form) {

			var formData = new FormData(form);
			
			$.ajax({
				url : $('#addbook').attr('action'),
				type : "POST",
				data:  formData,
			    mimeType:"multipart/form-data",
			    contentType: false,
			    cache: false,
			    processData:false,
				success : function() {
					$('#success_add').modal('show');

				},
				error : function(xhr, status, error) {

					$('#error_div').text(xhr.responseText);
					$('#error_div').removeClass('hide');
				}
			});

		}
		
	});
	
});

