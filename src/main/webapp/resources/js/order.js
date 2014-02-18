$(document).ready(function() {
	

	$(document).ready(function() {
		$('#ca-container').contentcarousel();
	});
					//raty part
						var $rate_area = $('#rate_area');
						if($rate_area.attr('rated') != '') {
							$('#rate').raty({
								readOnly : true,
								path: $('#path').attr('url')  + '/resources/js/img',
								half: true,
								score: $rate_area.attr('score')
							});
							$('#rate_form_textarea').text($rate_area.attr('message'));
							$('#rate_form_textarea').attr('disabled', 'disabled');
							$('#rate_form_submit').addClass('hide');
							$('#rate_area_title').text('Your comment');
						} else {
							$('#rate').raty({
								path : $('#path').attr('url')  + '/resources/js/img',
								half : true,
							});
							
						}
	
	
						$('.raty').each(function () {
							$(this).raty({
								path: $('#path').attr('url')  + '/resources/js/img',
								readOnly : true,
								score : $(this).attr('data-score'),
								
							});
						});
	
					//raty part
						
					//comments
						
						$('#view_comments').click(function(e) {
							
							
							
							e.preventDefault();
							if($(this).text() === 'Hide comments') {
								$(this).text('View comments');
								$('#comments_list_group').empty();
							}
							else {
								$(this).text('Hide comments');
								loadComments();
							}
							$('#comments_panel_body').toggle();
						});
						
						$('body').on('click', '#load_more_button', function (e) {
							e.preventDefault();
							loadMoreComments();
						});
						
					//comments	
					
					//vote_form
						
						$('#rate_form_submit').click(function (e) {
							e.preventDefault();
							if($('#rate_form').find('input[name="score"]').val() === "")
								$('#rate_form').find('input[name="score"]').val(0);
							var data = $('#rate_form').serialize();
							
							sendVote(data);
						});
						
					//vote_form
						
					var isLimitReacher = $('#book_limit').val();
					if (isLimitReacher === 'true')
						$('#book_limit_modal').modal('show');

					tmpDate = $('#minDate').val();

					minD = getDateInFormat(tmpDate);
					minDateSplit = minD.split(" ");

					$('#datetimepicker').datetimepicker({
						
						onGenerate : function(ct, $input) {
							$(this).find('.xdsoft_date.xdsoft_weekend').addClass('xdsoft_disabled');
						},
						
						onSelectDate : function(current_time, $input) {
							
							var days = getAvailableDays(current_time, $('.order'));
							var d = 'can order for ' + days + 'days';
							console.log('can order for ' + days + 'days');
							if ($(this).find($('#picker_notify')).attr('id') === undefined) {
								console.log($(this));

								var $div = $(
										'<div>',
										{
											id : 'picker_notify',
											title : d,
											style : 'position : absolute; top: 0%; left: 50%; text-size:16px;'
										});
								$div.attr('data-toggle','tooltip');
								$div.appendTo($(this));
								$('#picker_notify').tooltip('show');

							} else {
								console.log("before" + $('#picker_notify').attr('title'));
								//$('#picker_notify').attr('title', d);

								$('#picker_notify').attr('data-original-title', d)
										.tooltip('fixTitle')
										.tooltip('show');
								console.log("after"+ $('#picker_notify').attr('title'));
							}
							

						},
						
						onChangeMonth: function( currentDateTime ){
							var _this = this;
							$.when(getOrders(currentDateTime)).done(function (){
								_this.setOptions({
									value : '',
									weekends: getWeekEnds($('.order'))
								});
							});
							

						},
						
						onShow : function(currentDateTime) {
							
							var _this = this;
						 $.when(getMinDate(), getOrders(currentDateTime)).done(function(){
							 var date = $('#min_date_inner').attr('date');
							 console.log("Date = " + date);
							 _this.setOptions({
									value : date,
									minDate : date,
									weekends: getWeekEnds($('.order'))
								});
						 }); 
						
						
						 
						},
						
						
						format : 'Y/m/d H:i',
						//value : minD,
						//minDate : minDateSplit[0],
						weekends : getWeekEnds($('.order')),
						allowTimes : [ '09:00', '10:00',
								'11:00', '12:00', '14:00',
								'15:00', '16:00' ]
						
					});
					
					$('#order_button').click(function() {

								var bookId = $('#bid').attr('value');
								console.log('picker val ' + $('#datetimepicker').val());
								var time = getLongFromFormatTime($('#datetimepicker').val());
								if(isNaN(time)){
									$('#datetimepicker').datetimepicker('show');
									return;
								}
								makeOrder(bookId, time);

							});

					$('#wish_button').click(function() {
						var bookId = $('#bid').attr('value');
						addToWishList(bookId);
					});

				});

function makeOrder(bookId, time, url) {
	$.ajax({
		url : $('#path').attr('url') + "/books/order/add",
		type : "POST",
		data : {
			'bookId' : bookId,
			'time' : time
		},
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded',
		mimeType : 'application/json',

		success : function() {

			$('#order_modal').modal('show');

		},
		error : function(xhr, status, error) {
			if(xhr.responseText === 'You already ordered this book') {
				$('#wish_button').addClass('hide');
				$('#order_button').addClass('hide');
				$('#datetimepicker').addClass('hide');

			}
			$('#order_err').text(xhr.responseText);
			$('#order_err').removeClass('hide');
		}
	});
}

function addToWishList(bookId, url) {

	$.ajax({
		url : $('#path').attr('url') +  '/books/wishlist/add',
		type : "POST",
		data : {
			'bookId' : bookId
		},
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded',
		mimeType : 'application/json',
		success : function() {

			$('#wish_modal').modal('show');

		},
		error : function(xhr, status, error) {
			if(xhr.responseText === 'Book already in WishList') {
				$('#wish_button').addClass('hide');
				$('#order_button').addClass('hide');
				$('#datetimepicker').addClass('hide');

			}
			$('#order_err').text(xhr.responseText);
			$('#order_err').removeClass('hide');
		}

	});
}

function getOrders(date) {
	return $.ajax({
		url : $('#path').attr('url') + "/books/order/getAdditionalOrders",
		type : "POST",
		data : {
			'bookId' : $('#bid').attr('value'),
			'time' : date.getTime(),
		},
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded',
		mimeType : 'application/json',

		success : function(data) {
			$('#orders').empty();
			var $orders = $('#orders');
			$.each(data.orders, function(index, value) {
				var $order = $('<div>', {class : 'order'});
				$order.attr('orderDate', value.orderDate);
				$order.attr('returnDate', value.returnDate);
				$order.appendTo($orders);
				
			});
		}
	});	
}

function getMinDate() {
	return $.ajax({
		url : $('#path').attr('url') + "/books/order/getMinOrderDate",
		type : "POST",
		data : {
			'bookId' : $('#bid').attr('value'),
		},
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded',
		mimeType : 'application/json',

		success : function(data) {
			console.log('new date ' + new Date(data.minDate));
			var $mindate = $('#min_date');		
			$mindate.empty();	
			$inner = $('<div>', {id: 'min_date_inner'});		
			
			$inner.attr('date', getDateInFormat(data.minDate));
			$inner.appendTo($mindate);
		},
		error : function(xhr, status, error) {

			$('#order_err').text(xhr.responseText);
			$('#order_err').removeClass('hide');
		}
	});	
};

function sendVote(data) {
	$.ajax({
		url : $('#path').attr('url') + "/books/order/addRate",
		type : "POST",
		data : data,
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded',
		mimeType : 'application/json',

		success : function(data) {
	
			$('#rate_form_submit').addClass('hide');
			$('#rate_area_title').text('Your comment');
			$('#rate').raty({
				readOnly : true,
				path: $('#path').attr('url')  + '/resources/js/img',
				half: true,
				score: data.score
			});
			$('#rate_form_textarea').attr('disabled', 'disabled');
	
		},
		error : function(xhr, status, error) {

			$('#rate_err').text(xhr.responseText);
			$('#rate_err').removeClass('hide');
		}
	});	
};

function loadComments(){
	console.log('bid' + $('#bid').attr('value'));
	$.ajax({
		url : $('#path').attr('url') + "/books/order/getComments",
		type : "POST",
		dataType : "json",
		data : {
			'book' : $('#bid').attr('value'),
			'page' : 1
		},
		contentType : 'application/x-www-form-urlencoded',
		mimeType : 'application/json',

		success : function(data) {
			
			
			if(data.comments.length > 0) {
				$.each(data.comments , function (indx, val) {
					var $list_group_item = $('<div>', {class : 'list-group-item'});
					var $list_group_item_heading = $('<h5>', {class : 'list-group-item-heading'});
					var $list_group_item_text = $('<div>', {class : 'list-group-item-text'});
					var $div_name = $('<div>', {class : 'user_name'});
					var $div_raty = $('<div>', {class : 'raty'});
					$div_raty.attr('data-number', '5');
					$div_raty.raty({
						readOnly : true,
						path: $('#path').attr('url')  + '/resources/js/img',
						half: true,
						score: val.score
					});
					$div_name.text(val.name + " " + val.surname);
					$list_group_item_text.text(val.message);
					
					$div_name.appendTo($list_group_item_heading);
					$div_raty.appendTo($list_group_item_heading);
					
					$list_group_item_heading.appendTo($list_group_item);
					$list_group_item_text.appendTo($list_group_item);
					
					$list_group_item.appendTo($('#comments_list_group'));
				});
				if(data.pagesQuantity != 1) {
					var $list_group_item = $('<div>', {class : 'list-group-item'});
					var $list_group_item_heading_more = $('<h5>', {class : 'list-group-item-heading', id : 'load_more_li'});
					var $a = $('<a>', {id : 'load_more_button', href : ''});
					$a.text('load more');
					$a.appendTo($list_group_item_heading_more);
					$list_group_item_heading_more.appendTo($list_group_item);
					$list_group_item.appendTo($('#comments_list_group'));
				}
			} else {
				$('#comments_list_group').text('No comments yet.');
			}
			
			
			
	
		},
		error : function(xhr, status, error) {

			$('#rate_err').text(xhr.responseText);
			$('#rate_err').removeClass('hide');
		}
	});	
	
};

function loadMoreComments() {
	var page = Number($('#pagination_info').attr('page')) + 1;
	
	$('#pagination_info').attr('page', page);
	$.ajax({
		url : $('#path').attr('url') + "/books/order/getComments",
		type : "POST",
		dataType : "json",
		data : {
			'book' : $('#bid').attr('value'),
			'page' : page
		},
		contentType : 'application/x-www-form-urlencoded',
		mimeType : 'application/json',

		success : function(data) {
			
			$('#comments_list_group').find('#load_more_li').parent().remove();
			
			$.each(data.comments , function (indx, val) {
				var $list_group_item = $('<div>', {class : 'list-group-item'});
				var $list_group_item_heading = $('<h5>', {class : 'list-group-item-heading'});
				var $list_group_item_text = $('<div>', {class : 'list-group-item-text'});
				var $div_name = $('<div>', {class : 'user_name'});
				var $div_raty = $('<div>', {class : 'raty'});
				$div_raty.attr('data-number', '5');
				$div_raty.raty({
					readOnly : true,
					path: $('#path').attr('url')  + '/resources/js/img',
					half: true,
					score: val.score
				});
				$div_name.text(val.name + " " + val.surname);
				$list_group_item_text.text(val.message);
				
				$div_name.appendTo($list_group_item_heading);
				$div_raty.appendTo($list_group_item_heading);
				
				$list_group_item_heading.appendTo($list_group_item);
				$list_group_item_text.appendTo($list_group_item);
				
				$list_group_item.appendTo($('#comments_list_group'));
			});
			var page = Number($('#pagination_info').attr('page')) + 1;
			var pagesQuantity = data.pagesQuantity;//Number($('#pagination_info').attr('pagesQuantity'));
			if(page > pagesQuantity) {
				
			} else {
			
			var $list_group_item = $('<div>', {class : 'list-group-item'});
			var $list_group_item_heading_more = $('<h5>', {class : 'list-group-item-heading', id : 'load_more_li'});
			var $a = $('<a>', {id : 'load_more_button', href : ''});
			$a.text('load more');
			$a.appendTo($list_group_item_heading_more);
			$list_group_item_heading_more.appendTo($list_group_item);
			$list_group_item.appendTo($('#comments_list_group'));
			}
			
			
			
	
		},
		error : function(xhr, status, error) {

			$('#rate_err').text(xhr.responseText);
			$('#rate_err').removeClass('hide');
		}
	});	
}
