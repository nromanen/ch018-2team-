
$(document).ready(function() {
					if ($(".calendar").size() == 0) {
						$('#empty_orders_list').modal("show");
					}
					
					$('body').click(function () {
						$('.order_change_button').tooltip('destroy');
					});
					
					$(".order_date").each(function() {
						
						var orderDateInMillis = $(this).attr('val');
						console.log('orderdate = ' + orderDateInMillis);
						$(this).text(getDateInFormat(Number(orderDateInMillis)));
						
						var returnDateInMillis = Number($(this).next().attr('val'));
						var days = (returnDateInMillis - Number(orderDateInMillis)) / (24 * 3600 * 1000);
						$(this).next().text(Math.round(days));
						
					});
					
					$(".calendar").each(
									function() {
										
										var bid = $(this).attr('bid');
										console.log("bid " + bid);

										$(this).datetimepicker({
															onGenerate : function(ct, $input) {
																$(this).find('.xdsoft_date.xdsoft_weekend').addClass('xdsoft_disabled');
															},
															onSelectDate : function(current_time, $input) {
																var days = getAvailableDays(current_time, $('.order'));
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
															onChangeMonth: function( currentDateTime ){

																var _this = this;
																$.when(getOrders(currentDateTime, bid)).done(function (){
																	_this.setOptions({
																		value : '',
																		weekends: getWeekEnds($('.order'))
																	});
																});
															},
															onShow : function(currentDateTime) {
																
																var _this = this;
															 $.when(getMinDate(bid), getOrders(currentDateTime, bid)).done(function(){
																 var date = $('#min_date_inner').attr('date');
																 _this.setOptions({
																		value : date,
																		minDate : date.split(" ")[0],
																		weekends: getWeekEnds($('.order'))
																	});
															 }); 
															
															
															 
															},
															
															format : 'Y/m/d H:i',
															//value : orderDate,
															//minDate : minDate[0],
															allowTimes : [
																	'09:00',
																	'10:00',
																	'11:00',
																	'12:00',
																	'14:00',
																	'15:00',
																	'16:00' ],
															//weekends : getWeekEnds($orders)
														});
									});

				
					
					
					$('.order_delete_button').click(function() {
						var orderId = $(this).prev().val();
						$('#delete_final').attr('orderid', orderId);
						$('#delete_title').text($('#book_title').text());
						$('#delete_order_list').modal('show');
					});
					
					$('#delete_final').click(function() {
						
						deleteOrder($(this).attr('orderid'));
						$('#delete_order_list').modal('hide');
					});
					
					$('#return').click(function() {
						$('#delete_order_list').modal('hide');
					});

					$('.order_change_button').click(function() {
						
										var orderId = $(this).parent().find('.order_id').val();
										var $calendar  = $(this).parent().find('.calendar');
										var choosenDate = $calendar.val();
										var date = getLongFromFormatTime(choosenDate);
										if(isNaN(date)) {
											$calendar.datetimepicker('show');
											return;
										}
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
			var $order_date = $li.find(".order_date");
			var $days = $li.find(".days");
			var minD = getDateInFormat(data.minDate).split(" ");
			var currentDate = getDateInFormat(Number(data.orderDate));
			$order_date.text(currentDate);
			console.log("days " + $days.attr('class'));
			$days.text(data.days);
			console.log("after success order edit minD " + minD
					+ " | currentDate " + currentDate);
			/*$edit_input.datetimepicker({

				format : 'Y/m/d H:i',
				value : currentDate,
				minDate : minD[0],
				minTime : minD[1]
			});*/
			$btn.tooltip({
				placement: 'bottom',
				trigger: 'manual',
				}).tooltip('show');
			
		

		},
		error : function(xhr, status, error) {
			var $li = $('#order_li_' + orderId);
			var $err = $li.find($('.order_date_err')).removeClass('hide');
			$err.html(xhr.responseText);

		}

	});

}
function getOrders(date, bid) {
	return $.ajax({
		url : $('#path').attr('url') + "/books/order/getAdditionalOrders",
		type : "POST",
		data : {
			'bookId' : bid,
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
				$order.attr('orderDate', value.orderDate);
				$order.attr('returnDate', value.returnDate);
				$order.appendTo($orders);
				
			});
		}
	});	
}

function getMinDate(bookid) {
	return $.ajax({
		url : $('#path').attr('url') + "/books/order/getMinOrderDate",
		type : "POST",
		data : {
			'bookId' : bookid,
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