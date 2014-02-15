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
	
	$('#recommendation').change(function () {
		changeRecommendation($(this).is(':checked'));
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
	
	//sortby part
	$('#sortby > option').each(function() {
		console.log($(this).attr('order'));
		$(this).attr('url', path + '/admin?page=1&orderField=' + this.value + '&order=' + $(this).attr('order'));
		if(this.value === orderField && $(this).attr('order') === order) {
			$(this).attr('selected', 'selected');
		}
	});
	
	$('#sortby').change(function () {
		
		location.href = $('#sortby option:selected').attr('url');
		
	});
	//sortby part
	
	//pagesize
	$('#pageSize > option').each(function() {
		$(this).attr('url', path + '/admin?page=1&pageSize=' + this.value);
		if (this.value === $('#pagination_info').attr('pageSize')) {
			$(this).attr('selected', 'selected');
		}
	});
	
	$('#pageSize').change(function () {
		
		location.href = $('#pageSize option:selected').attr('url');
		
	});
	
	//pagesize
	
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
		url : $('#path').attr('path') + "/admin/syssetingsSearch",
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

function changeRecommendation(state) {
	$.ajax({
		url : $('#path').attr('path') + "/admin/syssetingsRecommendation",
		type : "POST",
		data : {
			'recommendation' : state,
		},
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded',
		mimeType : 'application/json',
		success : function(data) {

			//myOrders();
			if(data === true)
				$('#recommendation').attr('checked', 'checked');
			else
				$('#recommendation').attr('checked');

		},
		error : function() {
			alert(data);
			$('#recommendation').attr('checked');
		}

	});
}
