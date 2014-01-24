$(document).ready(function() {

	
	
	//pagination part_start
	
	var cur_page = $('#pagination_info').attr('page');
	console.log('cur ' + cur_page)
	var general_pages = $('#pagination_info').attr('generalpages');
	console.log(general_pages);
	var query = $('#pagination_info').attr('query');
	console.log('current ' + cur_page + 'general = ' + general_pages + 'query' + query);
	if(cur_page == 1 && general_pages == 1) {
		$('#first_page').removeClass('hide');
		$('#first_page').addClass('disabled');
	}
	else if(cur_page > 1 && cur_page == general_pages) {
		$('#first_page').removeClass('hide');
		$('#prev_page').removeClass('hide');
		$('#last_page').removeClass('hide');
		$('#last_page').addClass('disabled');
	} 
	else if (cur_page == 1) {
		$('#first_page').removeClass('hide');
		$('#first_page').addClass('disabled');
		$('#next_page').removeClass('hide');
		$('#last_page').removeClass('hide');
	} else {
		$('#first_page').removeClass('hide');
		$('#prev_page').removeClass('hide');
		$('#current_page').removeClass('hide');
		$('#next_page').removeClass('hide');
		$('#last_page').removeClass('hide');
		
	}
		
	
	$('#choose_button').click(function(e) {
		e.preventDefault();
		var size = $('#page_size').val();
		var order = $('#sort_order').val();
		var orderField = $('#sort_field').val();
		var url = $('#search_button').attr('url');
		var query = $('#pagination_info').attr('query');
		var page = 1;
		var yearStart = $('#year_start').val() === "" ? -1 : $('#year_start').val();
		var yearEnd = $('#year_end').val() === "" ? -1 : $('#year_end').val();
		var pageStart = $('#page_start').val() === "" ? -1 : $('#page_start').val();
		var pageEnd = $('#page_end').val() === "" ? -1 : $('#page_end').val();
		//$('#pagination_info').attr('size', size);
		//$('#pagination_info').attr('order', order);
		//$('#pagination_info').attr('orderField', orderField);
		//search(query, url, page, size, order, orderField);
		doPost({
			pageSize : size,
			order : order,
			orderField : orderField,
			query : query,
			page : page,
			yearStart : yearStart,
			yearEnd : yearEnd,
			bookPageStart : pageStart,
			bookPageEnd : pageEnd
		}, url);
	});
	

	$('#first_page').click(function () {
		var url = $('#search_button').attr('url');
		var page = 1;
		var size = $('#pagination_info').attr('size');
		var query = $('#pagination_info').attr('query');
		var order = $('#pagination_info').attr('order');
		var orderfield = $('#pagination_info').attr('orderfield');
		search(query, url, page, size, order, orderfield);
	});
	
	$('#prev_page').click(function () {
		var url = $('#search_button').attr('url');
		var page = Number($('#pagination_info').attr('page'));
		page = page - 1;
		var size = $('#pagination_info').attr('size');
		var query = $('#pagination_info').attr('query');
		var order = $('#pagination_info').attr('order');
		var orderfield = $('#pagination_info').attr('orderfield');
		search(query, url, page, size, order, orderfield);
	});
	
	$('#next_page').click(function () {
		var url = $('#search_button').attr('url');
		var page = Number($('#pagination_info').attr('page'));
		page = page + 1;
		var size = $('#pagination_info').attr('size');
		var query = $('#pagination_info').attr('query');
		var order = $('#pagination_info').attr('order');
		var orderfield = $('#pagination_info').attr('orderfield');
		search(query, url, page, size, order, orderfield);
	});
	
	$('#last_page').click(function () {
		var url = $('#search_button').attr('url');
		var page = $('#pagination_info').attr('generalPages');
		var size = $('#pagination_info').attr('size');
		var query = $('#pagination_info').attr('query');
		var order = $('#pagination_info').attr('order');
		var orderfield = $('#pagination_info').attr('orderfield');
		search(query, url, page, size, order, orderfield);
	});
	
	//pagination part_end
	
	
	$('#search_field').autocomplete({
		serviceUrl : $('#search_field').attr('url') +  "/books/autocomplete",
		minChars : 2

	});

	$('body').on('click', '#search_button', function(e) {
		e.preventDefault();
		var url = $(this).attr('url');
		page = 1;
		var size = $('#pagination_info').attr('size');
		var query = $('#search_field').val();
		var order = $('#pagination_info').attr('order');
		var orderfield = $('#pagination_info').attr('orderfield');
		search(query, url, page, size, order, orderfield);
	});

	$('body').on('click', '#advanced_search_button', function() {

		$('#advanced_search_panel').toggle("slow");

	});

	$('body').on('click', '#advanced_search_submit', function() {
		var url = $(this).attr('url');
		advancedSearch(url);
	});
});

function search(query, url, page, size, order, orderField) {
	doPost({
		query : query,
		page : page,
		pageSize : size,
		orderField : orderField,
		order : order
	}, url);

}

function advancedSearch(url) {

	var title = $('#advanced_search_title').val();
	var authors = $('#advanced_search_authors').val();
	var publisher = $('#advanced_search_publisher').val();
	var genreId = $('#advanced_search_select').val();
	doPost({
		title : title,
		authors : authors,
		publisher : publisher,
		genreId : genreId
	}, url);

}

function doPost(params, url) {
	
	$form = $('<form>', {
		action : url,
		method : 'POST',
		style : 'display:none'
	});
	
	$.each(params, function(index, value) {
		
		if(value === -1)
			return;
		
		$('<input>', {
			name : index,
			val : value
		}).appendTo($form);
	});

	$form.appendTo('body');
	$form.submit();
}
