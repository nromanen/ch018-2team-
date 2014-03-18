$(document).ready(function () {
	
	var path = $('#path').attr('url');
	var orderField = $('#pagination_info').attr('orderField');
	var order = $('#pagination_info').attr('order');
	var page  = Number($('#pagination_info').attr('page'));
	var pagesQuantity = Number($('#pagination_info').attr('pagesQuantity'));
	
	//page part
	if(page == 1 && pagesQuantity == 1) {
		
		}
	else if(page > 1 && page == pagesQuantity) {
		$('.first_page').removeClass('hide');
		$('.first_page').children().attr('href', path + '/librarian/books/search?page=1');
		
		$('.prev_page').removeClass('hide');
		var prev_page = page - 1;
		$('.prev_page').children().attr('href', path + '/librarian/books/search?page=' + prev_page);
		
		$('.last_page').removeClass('hide');
		$('.last_page').addClass('disabled');
	} 
	else if (page == 1) {
		$('.first_page').removeClass('hide');
		$('.first_page').addClass('disabled');
		
		$('.next_page').removeClass('hide');
		var next_page = page + 1;
		$('.next_page').children().attr('href', path + '/librarian/books/search?page=' + next_page);
		
		$('.last_page').removeClass('hide');
		$('.last_page').children().attr('href', path + '/librarian/books/search?page=' + pagesQuantity);
	} else {
		$('.first_page').removeClass('hide');
		$('.first_page').children().attr('href', path + '/librarian/books/search?page=1');
		
		$('.prev_page').removeClass('hide');
		var prev_page = page - 1;
		$('.prev_page').children().attr('href', path + '/librarian/books/search?page=' + prev_page);
		
		$('.current_page').removeClass('hide');
		
		$('.next_page').removeClass('hide');
		var next_page = page + 1;
		$('.next_page').children().attr('href', path + '/librarian/books/search?page=' + next_page);
		
		$('.last_page').removeClass('hide');
		$('.last_page').children().attr('href', path + '/librarian/books/search?page=' + pagesQuantity);
	}
	
	//page part
	
	
	
	$('.actions').click(function() {
		
		var bid = $(this).attr('bid');
		var $action = $(this);
		
		
		$.ajax({
			url : path + "/librarian/books/getOrdersHolders",
			type : "POST",
			data : {
				'id' : bid
			},
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded',
			mimeType : 'application/json',

			success : function(data) {
				
				var $holders = $action.parent().find('.holders');
				var $orders = $action.parent().find('.orders');
				
				$holders.text(data.holders);
				$orders.text(data.orders);
			}
		});	
	});
});