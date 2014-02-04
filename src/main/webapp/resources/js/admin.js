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
	
	
	var path = $('#pagination_info').attr('path');
	var orderField = $('#pagination_info').attr('orderField');
	var order = $('#pagination_info').attr('order');
	var page  = Number($('#pagination_info').attr('page'));
	var pagesQuantity = Number($('#pagination_info').attr('pagesQuantity'));
	
	//page part
	if(page == 1 && pagesQuantity == 1) {
		
		}
	else if(page > 1 && page == pagesQuantity) {
		$('.first_page').removeClass('hide');
		$('.first_page').children().attr('href', path + '/admin?page=1');
		
		$('.prev_page').removeClass('hide');
		var prev_page = page - 1;
		$('.prev_page').children().attr('href', path + '/admin?page=' + prev_page);
		
		$('.last_page').removeClass('hide');
		$('.last_page').addClass('disabled');
	} 
	else if (page == 1) {
		$('.first_page').removeClass('hide');
		$('.first_page').addClass('disabled');
		
		$('.next_page').removeClass('hide');
		var next_page = page + 1;
		$('.next_page').children().attr('href', path + '/admin?page=' + next_page);
		
		$('.last_page').removeClass('hide');
		$('.last_page').children().attr('href', path + '/admin?page=' + pagesQuantity);
	} else {
		$('.first_page').removeClass('hide');
		$('.first_page').children().attr('href', path + '/admin?page=1');
		
		$('.prev_page').removeClass('hide');
		var prev_page = page - 1;
		$('.prev_page').children().attr('href', path + '/admin?page=' + prev_page);
		
		$('.current_page').removeClass('hide');
		
		$('.next_page').removeClass('hide');
		var next_page = page + 1;
		$('.next_page').children().attr('href', path + '/admin?page=' + next_page);
		
		$('.last_page').removeClass('hide');
		$('.last_page').children().attr('href', path + '/admin?page=' + pagesQuantity);
	}
	
	//page part
	
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
