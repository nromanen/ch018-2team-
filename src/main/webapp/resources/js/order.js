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
										onChangeMonth : function (month, $input) {
											console.log("month = " + month.getMonth() + " input = " + $input);
											
											$.ajax({
												url : $('#path').attr('url') + "/books/order/getAdditionalOrders",
												type : "POST",
												data : {
													'bookId' : $('#bookId').val(),
													'month' : month.getMonth(),
													'year' : month.getFullYear()
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
													//var week = getWeekEnds($('.order'));
													
													$('#datetimepicker').datetimepicker('reload');
												
													
													/*$.each(week, function(index, value) {
														var splited = value.split('.');
														var day = splited[0];
														if(day.substring(0, 1) === '0')
															day = day.substring(1);
														var month = splited[1];
														if(month.substring(0, 1) === '0')
															month = month.substring(1);
														var year = splited[2];
														//$('.xdsoft_calendar').find('td[data-date=' + day + '][data-month=' + month + '][data-year=' + year + ']').addClass('.xdsoft_disabled');
														//console.log($('td[data-date=' + 15 + '][data-month=' + 1 + '][data-year=' + 2014 + ']').attr('class'));
														//console.log('td[data-date=' + day + '][data-month=' + month + '][data-year=' + year + ']');
													});*/
													
												}
											});
											
											
											
										},
										format : 'Y/m/d H:i',
										value : minD,
										minDate : minDateSplit[0],
										weekends : getWeekEnds($('.order')),
										allowTimes : [ '09:00', '10:00',
												'11:00', '12:00', '14:00',
												'15:00', '16:00' ],
										
									});
					$('#order_button').click(function() {

								var bookId = $('#bookId').val();
								var time = getLongFromFormatTime($('#datetimepicker').val());
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
