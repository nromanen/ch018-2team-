$(document).ready(function() {

	
	
	//pagination part_start
	
	var cur_page = $('#pagination_info').attr('page');
	console.log('cur ' + cur_page)
	var general_pages = $('#pagination_info').attr('generalPages');
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
		$('#next_page').removeClass('hide');
		$('#last_page').removeClass('hide');
		
	}
		
	
	

	$('#first_page').click(function () {
		var url = $('#search_button').attr('url');
		var page = 1;
		var size = $('#pagination_info').attr('size');
		var query = $('#pagination_info').attr('query');
		search(query, url, page, size);
	});
	
	$('#prev_page').click(function () {
		var url = $('#search_button').attr('url');
		var page = Number($('#pagination_info').attr('page'));
		page = page - 1;
		var size = $('#pagination_info').attr('size');
		var query = $('#pagination_info').attr('query');
		search(query, url, page, size);
	});
	
	$('#next_page').click(function () {
		var url = $('#search_button').attr('url');
		var page = Number($('#pagination_info').attr('page'));
		page = page + 1;
		var size = $('#pagination_info').attr('size');
		var query = $('#pagination_info').attr('query');
		search(query, url, page, size);
	});
	
	$('#last_page').click(function () {
		var url = $('#search_button').attr('url');
		var page = $('#pagination_info').attr('generalPages');
		var size = $('#pagination_info').attr('size');
		var query = $('#pagination_info').attr('query');
		search(query, url, page, size);
	});
	
	//pagination part_end
	
	
	$('#search_field').autocomplete({
		serviceUrl : $('#search_field').attr('url') +  "/books/autocomplete",
		minChars : 2

	});

	$('body').on('click', '#search_button', function(e) {
		e.preventDefault();
		var url = $(this).attr('url');
		var query = $('#search_field').val();
		search(query, url);
	});

	$('body').on('click', '#advanced_search_button', function() {

		$('#advanced_search_panel').toggle("slow");

	});

	$('body').on('click', '#advanced_search_submit', function() {
		var url = $(this).attr('url');
		advancedSearch(url);
	});
});

function search(query, url, page, size) {
	doPost({
		query : query,
		viewPageNum : page,
		booksOnPage : size
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
		$('<input>', {
			name : index,
			val : value
		}).appendTo($form);
	});

	$form.appendTo('body');
	$form.submit();
}
