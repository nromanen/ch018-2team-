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
	
	$('#switcher').change(function () {
		changeSort($(this).is(':checked'));
	});
});

function deleteUser(userId) {
	$.ajax({
		url : $('#path').attr('path') + "/admin/delete",
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
		url : $('#path').attr('path') + "/admin/change",
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

function changeSort(state) {
	$.ajax({
		url : $('#path').attr('path') + "/admin/syssetings",
		type : "POST",
		data : {
			'switcher' : state,
		},
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded',
		mimeType : 'application/json',
		success : function(data) {

			//myOrders();
			if(data === true)
				$('#switcher').attr('checked', 'checked');
			else
				$('#switcher').attr('checked');

		},
		error : function() {
			alert(data);
			$('#switcher').attr('checked');
		}

	});
	
}