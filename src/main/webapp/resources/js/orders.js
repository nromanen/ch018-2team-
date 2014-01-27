
$(document).ready(function() {
					if ($(".calendar").size() == 0) {
						$('#empty_orders_list').modal("show");
					}
					$(".calendar").each(
									function() {
										var changed = $(this).parent().find(
												".changed").val();
										var orderDateLong = $(this).prev()
												.val();
										var minDateLong = $(this).parent()
												.find($('.minDate')).val();
										var orderDate = getDateInFormat(orderDateLong);
										var rawMinDate = getDateInFormat(minDateLong);
										var minDate = rawMinDate.split(" ");
										console.log("minDate " + minDate);
										var $orders = $(this).parent().find('.order');

										/*
										 * if(changed === 'true'){
										 * 
										 * var $btn = $(this).next();
										 * $btn.attr('data-toggle', 'popover');
										 * $btn.attr('data-content', 'choose
										 * another date'); $btn.popover('show');
										 * }else if(orderDateLong > (minDateLong +
										 * (24*3600*1000))){
										 * 
										 * var $btn = $(this).next();
										 * $btn.attr('data-toggle', 'popover');
										 * $btn.attr('data-content', 'can choose
										 * earlier date');
										 * $btn.popover({placement : 'bottom'});
										 * $btn.popover('show');
										 * $btn.popover().hover(function () {
										 * $btn.popover('hide'); });
										 *  }
										 */

										$(this)
												.datetimepicker(
														{
															onGenerate : function(
																	ct, $input) {
																$(this)
																		.find(
																				'.xdsoft_date.xdsoft_weekend')
																		.addClass(
																				'xdsoft_disabled');
															},
															onSelectDate : function(current_time, $input) {
																var days = getAvailableDays(current_time, $orders);
																console.log(days);
																var d = 'can order for '+ days + 'days';
																if ($(this).find($('.picker_notify'))
																		.attr('class') === undefined) {
																	var $div = $('<div>',
																			{
																				class : 'picker_notify',
																				title : d,
																				style : 'position : absolute; top: 0%; left: 50%; text-size:16px;'
																			});
																	$div.attr('data-toggle', 'tooltip');
																	$div.appendTo($(this));
																	$('.picker_notify')
																			.tooltip('show');

																} else {
																	$('.picker_notify').attr('data-original-title',d)
																			.tooltip('fixTitle')
																			.tooltip('show');
																}

															},
															format : 'Y/m/d H:i',
															value : orderDate,
															minDate : minDate[0],
															allowTimes : [
																	'09:00',
																	'10:00',
																	'11:00',
																	'12:00',
																	'14:00',
																	'15:00',
																	'16:00' ],
															weekends : getWeekEnds($orders)
														});
									});

					$('.order_delete_button').click(function() {

						deleteOrder($(this).prev().val());
					});

					$('.order_change_button')
							.click(
									function() {
										var orderId = $(this).parent()
												.children().val();
										var date = getLongFromFormatTime(($(
												this).prev().val()));
										editOrder(orderId, date);

									});
				});

function deleteOrder(orderId) {
	$.ajax({
		url : $('#path').attr('url') + "/books/order/delete",
		type : "POST",
		data : {
			'orderId' : orderId
		},
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded',
		mimeType : 'application/json',
		success : function() {

			// myOrders();
			$('#order_li_' + orderId).remove();
			if ($(".calendar").size() == 0) {
				$('#empty_orders_list').modal("show");
			}

		}

	});

}

function editOrder(orderId, date) {

	$.ajax({
		url : $('#path').attr('url') + "/books/order/edit",
		type : "POST",
		data : {
			'orderId' : orderId,
			'date' : date
		},
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded',
		mimeType : 'application/json',
		success : function(data) {

			var $li = $('#order_li_' + orderId);
			var $btn = $li.find('.order_change_button');
			var $err = $li.find($('.order_date_err')).addClass('hide');
			/*
			 * if(data.date > (data.minDate + (12*3600*1000))){
			 * $btn.attr('data-toggle', 'popover'); $btn.attr('data-content',
			 * 'can choose earlier date'); $btn.popover("show"); }
			 */

			var $edit_input = $li.find(".calendar");
			var minD = getDateInFormat(data.minDate).split(" ");
			var currentDate = getDateInFormat(data.date);
			console.log("after success order edit minD " + minD
					+ " | currentDate " + currentDate);
			$edit_input.datetimepicker({

				format : 'Y/m/d H:i',
				value : currentDate,
				minDate : minD[0],
				minTime : minD[1]
			});

		},
		error : function(xhr, status, error) {
			var $li = $('#order_li_' + orderId);
			var $err = $li.find($('.order_date_err')).removeClass('hide');
			$err.html(xhr.responseText);

		}

	});

}