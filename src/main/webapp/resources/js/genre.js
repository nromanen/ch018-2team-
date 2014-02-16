$(document).ready(function() {
	
	$('#genre_submit').click(function (e) {
		
		e.preventDefault();
		
		$.ajax({
			url : $('#genre_form').attr('action'),
			type : "POST",
			data : $('#genre_form').serialize(),
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded',
			mimeType : 'application/json',

			success : function() {
				location.href = $('#genre_form').attr('action');

			},
			error : function(xhr, status, error) {

				$('#genre_error').text(xhr.responseText);
				$('#genre_error').removeClass('hide');
			}
		});
	});
})