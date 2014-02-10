$(document).ready(function() {
					if ($(".calendar").size() == 0) {
						$('#empty_wish_list').modal("show");
					}
					$(".calendar").each(function() {
						var bid = $(this).parent().find('.bookId').val();
										$(this).datetimepicker({
															onGenerate : function(ct, $input) {
																$(this).find('.xdsoft_date.xdsoft_weekend').addClass('xdsoft_disabled');
															},
															onSelectDate : function(current_time, $input) {
																var days = getAvailableDays(current_time,$orders);
																var d = 'can order for '+ days + 'days';
																if ($(this).find($('.picker_notify')).attr('class') === undefined) {
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
															/*value : minDate[0]
																	+ " "
																	+ minDate[1],
															minDate : minDate[0],*/
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

					$('.wish_delete_button').click(function() {
						var wishId = $(this).prev().val();
						deleteWish(wishId);
					});
					$('.wish_confirm_button').click(function() {
						var $parent = $(this).parent();
						var $calendar = $parent.find('.calendar');
						var date = getLongFromFormatTime($calendar.val());
						if(isNaN(date)) {
							$calendar.datetimepicker('show');
							return;
						}
						var wishId = $parent.find($('.wishId')).val();
						var bookId = $parent.find($('.bookId')).val();

						confirmWish(wishId, bookId, date);
					});

				});

function deleteWish(wishId) {

	$.ajax({
		url : $('#path').attr('url') + "/books/wishlist/delete",
		type : "POST",
		data : {
			'wishId' : wishId
		},
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded',
		mimeType : 'application/json',
		success : function() {
			$('#wish_li_' + wishId).remove();
			if ($(".calendar").size() == 0) {
				$('#empty_wish_list').modal("show");
			}
		}
	});

}

function confirmWish(wishId, bookId, date) {

	$.ajax({
		url : $('#path').attr('url') + "/books/order/add",
		type : "POST",
		data : {
			'bookId' : bookId,
			'time' : date
		},
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded',
		mimeType : 'application/json',

		success : function() {

			$('#wish_li_' + wishId).remove();

		},
		error : function(xhr, status, error) {
			var $li = $('#wish_li_' + wishId);
			var $err = $li.find($('.wish_date_err')).removeClass('hide');
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