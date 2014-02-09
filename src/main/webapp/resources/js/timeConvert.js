function getDateInFormat(tmpDate) {

	var date = new Date(Number(tmpDate));

	var month = date.getMonth().toString().length === 1 ? "0"
			+ (date.getMonth() + 1) : date.getMonth();
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
	return date.getTime();

}

function getAvailableDays(current_time, $order) {
	console.log(current_time);
	var time = current_time.getTime();
	var days = 14;
	$order.each(function() {
		var order_time = $(this).attr('start');
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
	var weekend = [];
	$order.each(function() {
		var start = $(this).attr('start');
		var days = $(this).attr('days');
		var dateInMillis = Number(start);
		var date = new Date(dateInMillis);

		for (var i = 1; i < days; i++) {
			var month = date.getMonth().toString().length === 1 ? "0"
					+ (date.getMonth() + 1) : date.getMonth();
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