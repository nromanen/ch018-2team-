$(document).ready(function() {
					
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
							 _this.setOptions({
									value : date,
									minDate : date.split(" ")[0],
									weekends: getWeekEnds($('.order'))
								});
						 }); 
						
						
						 
						},
						
						
						format : 'Y/m/d H:i',
						//value : minD,
						minDate : minDateSplit[0],
						weekends : getWeekEnds($('.order')),
						allowTimes : [ '09:00', '10:00',
								'11:00', '12:00', '14:00',
								'15:00', '16:00' ]
						
					});
					
					$('#order_button').click(function() {

								var bookId = $('#bookId').val();
								var time = getLongFromFormatTime($('#datetimepicker').val());
								if(isNaN(time)){
									$('#datetimepicker').datetimepicker('show');
									return;
								}
								makeOrder(bookId, time);

							});

					$('#wish_button').click(function() {
						var bookId = $('#bookId').val();
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

		}

	});
}

function getOrders(date) {
	return $.ajax({
		url : $('#path').attr('url') + "/books/order/getAdditionalOrders",
		type : "POST",
		data : {
			'bookId' : $('#bookId').val(),
			'time' : date.getTime(),
		},
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded',
		mimeType : 'application/json',

		success : function(data) {
			$('#orders').empty();
			var $orders = $('#orders');
			$.each(data.orders, function(index, value) {
				console.log(value.days + " " + value.orderDate);
				var $order = $('<div>', {class : 'order'});
				$order.attr('start', value.orderDate);
				$order.attr('days', value.days);
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
			'bookId' : $('#bookId').val(),
		},
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded',
		mimeType : 'application/json',

		success : function(data) {
	
			var $mindate = $('#min_date');		
			$mindate.empty();	
			$inner = $('<div>', {id: 'min_date_inner'});		
			$inner.appendTo($mindate);
			$inner.attr('date', getDateInFormat(data.minDate));
	
		}
	});	
};


