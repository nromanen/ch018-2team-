function getDateInFormat(tmpDate) {

	var date = new Date(Number(tmpDate));

	var month = (date.getMonth() + 1).toString().length === 1 ? "0"
			+ (date.getMonth() + 1) : date.getMonth() + 1;
			console.log("MONTH " + month);
	var day = date.getDate().toString().length === 1 ? "0" + date.getDate()
			: date.getDate();
	var hours = date.getHours().toString().length === 1 ? "0" + date.getHours()
			: date.getHours();

	return date.getFullYear() + "/" + month + "/" + day + " " + hours + ":"
			+ "00";

}
function getLongFromFormatTime(formatTime) {
	var tmpDate = formatTime.split(" ");
	var datePart = tmpDate[0].split("/");
	var timePart = tmpDate[1].split(":");
	var date = new Date();
	date.setFullYear(datePart[0]);
	date.setMonth(datePart[1] - 1);
	date.setDate(datePart[2]);
	date.setHours(timePart[0]);
	date.setMinutes(0);
	date.setSeconds(0);
	if(date.getTime() < new Date().getTime() + 2*3600*1000) {
		console.log("changing..");
		date.setHours(new Date().getHours() + 2);
		console.log(date);
	}
	console.log(date);
	return date.getTime();

}

function getAvailableDays(current_time, $order) {
	console.log(current_time);
	var time = current_time.getTime();
	var days = 14;
	$order.each(function() {
		var order_time = $(this).attr('orderDate');
		if (time < order_time) {
			days = (order_time - time) / (24 * 3600 * 1000);
			console.log("days in conv " + days);
			return false;
		}
	});
	if (days > 14)
		days = 14;
	return Math.round(days);

}

function getWeekEnds($order) {
	console.log("in getWeekEnds");
	var weekend = [];
	$order.each(function() {
		var orderDate = $(this).attr('orderDate');
		var returnDate = $(this).attr('returnDate');
		var orderDateInMillis = Number(orderDate);
		var returnDateInMillis = Number(returnDate);
		var days = (returnDateInMillis - orderDateInMillis) / (24 * 3600 * 1000);
		var date = new Date(orderDateInMillis);

		for (var i = 1; i < days; i++) {
			var month = (date.getMonth() + 1).toString().length === 1 ? "0"
					+ (date.getMonth() + 1) : date.getMonth() + 1;
			var day = date.getDate().toString().length === 1 ? "0"
					+ date.getDate() : date.getDate();
			week = day + "." + month + "." + date.getFullYear();
			weekend.push(week);
			dateInMillis = date.getTime() + (24 * 3600 * 1000);
			date = new Date(dateInMillis);

		}

	});
	return weekend;

}