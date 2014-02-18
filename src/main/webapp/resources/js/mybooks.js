$(document).ready(
		function() {

			$('.mybooks_date').each(
					function() {
						var rawDate = $(this).val();
						var date = getDateInFormat(rawDate);
						var $parent = $(this).parent();
						$parent.text(date.split(" ")[0]);
						var diff = parseInt((rawDate - new Date().getTime())
								/ (24 * 3600 * 1000));
						$parent.next().text(diff);
						if (diff < 2) {
							$parent.parent().parent().css("background-color",
									"#FA8072");
						} else if (diff < 5) {
							$parent.parent().parent().css("background-color",
									"#FFFF66");
						} else {
							$parent.parent().parent().css("background-color",
									"#00FF7F");
						}
					});

		});
