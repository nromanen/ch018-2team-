$(document).ready(function() {

	$('.btn-danger').click(function() {

		var userId = $(this).val();

		deleteUser(userId);
	});

	$('.change_role').click(function() {

		var userId = $(this).val();
		var role = $(this).prev().val();
		changeUserRole(userId, role);

	});
});

function deleteUser(userId) {
	$.ajax({
		url : "admin/delete",
		type : "POST",
		data : {
			'id' : userId
		},
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded',
		mimeType : 'application/json',
		success : function() {

			//myOrders();
			$('#person_li_' + userId).remove();

		},
		error : function() {
			alert("error");
		}

	});

}

function changeUserRole(userId, role) {
	$.ajax({
		url : "admin/change",
		type : "POST",
		data : {
			'id' : userId,
			'role' : role
		},
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded',
		mimeType : 'application/json',
		success : function() {

			//myOrders();
			$('#person_li_' + userId).find(".user_role").text(role);

		},
		error : function() {
			alert("error");
		}

	});

}