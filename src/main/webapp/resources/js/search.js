$(document).ready(function() {

	
	//pagination part_start
	
		var path = $('#pagination_info').attr('path');
		var orderField = $('#pagination_info').attr('orderField');
		var order = $('#pagination_info').attr('order');
		var page  = Number($('#pagination_info').attr('page'));
		var pagesQuantity = Number($('#pagination_info').attr('pagesQuantity'));
		var bookPageStart = Number($('#pagination_info').attr('bookPageStart'));
		var bookPageEnd = Number($('#pagination_info').attr('bookPageEnd'));
		var yearStart = Number($('#pagination_info').attr('yearStart'));
		var yearEnd = Number($('#pagination_info').attr('yearEnd'));
		var choosenBookPageStart = isNaN(Number($('#pagination_info').attr('choosenBookPageStart'))) ? bookPageStart : Number($('#pagination_info').attr('choosenBookPageStart'));
		var choosenBookPageEnd = isNaN(Number($('#pagination_info').attr('choosenBookPageEnd'))) ? bookPageEnd : Number($('#pagination_info').attr('choosenBookPageEnd'));
		var choosenYearStart = isNaN(Number($('#pagination_info').attr('choosenYearStart'))) ? yearStart : Number($('#pagination_info').attr('choosenYearStart'));
		var choosenYearEnd = isNaN(Number($('#pagination_info').attr('choosenYearEnd'))) ? yearEnd : Number($('#pagination_info').attr('choosenYearEnd'));
		choosenBookPageStart = choosenBookPageStart === 0 ? bookPageStart : choosenBookPageStart;
		choosenBookPageEnd = choosenBookPageEnd === 0 ? bookPageEnd : choosenBookPageEnd;
		choosenYearStart = choosenYearStart === 0 ? yearStart : choosenYearStart;
		choosenYearEnd = choosenYearEnd === 0 ? yearEnd : choosenYearEnd;
			console.log('bookPageStart ' + bookPageStart + ' ' + 'bookPageEnd ' + bookPageEnd + ' ' + 'yearStart ' + yearStart + 'yearEnd ' + yearEnd );
			console.log('choosenBookPageStart ' + choosenBookPageStart + ' ' + 'choosenBookPageEnd ' + choosenBookPageEnd + ' ' + 'choosenYearStart ' + choosenYearStart + 'choosenYearEnd ' + choosenYearEnd );

		
		//sortby part
		$('#sortby > option').each(function() {
			console.log($(this).attr('order'));
			$(this).attr('url', path + '/books/search?page=1&orderField=' + this.value + '&order=' + $(this).attr('order'));
			if(this.value === orderField && $(this).attr('order') === order) {
				$(this).attr('selected', 'selected');
			}
		});
		
		$('#sortby').change(function () {
			
			location.href = $('#sortby option:selected').attr('url');
			
		});
		//sortby part
		
		//genreselect
		

			$('#genreSelect > option').each(function() {

				if (this.value === $('#pagination_info').attr('genreId')) {
					$(this).attr('selected', 'selected');
					return false;
				}
			});
		
		//genreselect
		
		//pagesize
			$('#pageSize > option').each(function() {
				$(this).attr('url', path + '/books/search?page=1&pageSize=' + this.value);
				if (this.value === $('#pagination_info').attr('pageSize')) {
					$(this).attr('selected', 'selected');
				}
			});
			
			$('#pageSize').change(function () {
				
				location.href = $('#pageSize option:selected').attr('url');
				
			});
			
		//pagesize
	
		//page part
		if(page == 1 && pagesQuantity == 1) {
			
			}
		else if(page > 1 && page == pagesQuantity) {
			$('.first_page').removeClass('hide');
			$('.first_page').children().attr('href', path + '/books/search?page=1');
			
			$('.prev_page').removeClass('hide');
			var prev_page = page - 1;
			$('.prev_page').children().attr('href', path + '/books/search?page=' + prev_page);
			
			$('.last_page').removeClass('hide');
			$('.last_page').addClass('disabled');
		} 
		else if (page == 1) {
			$('.first_page').removeClass('hide');
			$('.first_page').addClass('disabled');
			
			$('.next_page').removeClass('hide');
			var next_page = page + 1;
			$('.next_page').children().attr('href', path + '/books/search?page=' + next_page);
			
			$('.last_page').removeClass('hide');
			$('.last_page').children().attr('href', path + '/books/search?page=' + pagesQuantity);
		} else {
			$('.first_page').removeClass('hide');
			$('.first_page').children().attr('href', path + '/books/search?page=1');
			
			$('.prev_page').removeClass('hide');
			var prev_page = page - 1;
			$('.prev_page').children().attr('href', path + '/books/search?page=' + prev_page);
			
			$('.current_page').removeClass('hide');
			
			$('.next_page').removeClass('hide');
			var next_page = page + 1;
			$('.next_page').children().attr('href', path + '/books/search?page=' + next_page);
			
			$('.last_page').removeClass('hide');
			$('.last_page').children().attr('href', path + '/books/search?page=' + pagesQuantity);
		}
		
		//page part
		
		//sliders
		
		   $( "#pages-range" ).slider({
			      range: true,
			      min: bookPageStart,
			      max: bookPageEnd,
			      values: [ choosenBookPageStart, choosenBookPageEnd ],
			      slide: function( event, ui ) {
			        $( "#pages" ).val(ui.values[ 0 ] + " - " + ui.values[ 1 ] );
			      }
			    });
			    $( "#pages" ).val($( "#pages-range" ).slider( "values", 0 ) +
			      " - " + $( "#pages-range" ).slider( "values", 1 ) );
			    
			$( "#years-range" ).slider({
				      range: true,
				      min: yearStart,
				      max: yearEnd,
				      values: [ choosenYearStart, choosenYearEnd ],
				      slide: function( event, ui ) {
				        $( "#years" ).val(ui.values[ 0 ] + " - " + ui.values[ 1 ] );
				      }
				    });
				    $( "#years" ).val($( "#years-range" ).slider( "values", 0 ) +
				      " - " + $( "#years-range" ).slider( "values", 1 ) );
		//sliders
	
		//searchPost button
				    
				    $('#searchPost').click(function () {
				    	var query = $('#query').val();
				    	var title = $('#title').val();
				    	var authors = $('#authors').val();
				    	var publisher = $('#publisher').val();
				    	var minPages = $( "#pages-range" ).slider("values")[0];
				    	var maxPages = $( "#pages-range" ).slider("values")[1];
				    	var minYear = $( "#years-range" ).slider("values")[0];
				    	var maxYear = $( "#years-range" ).slider("values")[1];
				    	var pageSize = $('#pageSize').val();
				    	var genreId = $('#genreSelect').val();
				    	var url = path + '/books/search';
				    		
				    	doPost({
				    		query : query,
				    		title : title,
				    		authors : authors,
				    		publisher : publisher,
				    		choosenPageStart : minPages,
				    		choosenPageEnd : maxPages,
				    		choosenYearStart : minYear,
				    		choosenYearEnd : maxYear,
							pageSize : pageSize,
							genre : genreId,
							page : 1
						}, url);
				    	
				    });
				    
		//searchPost button
	
	
	//pagination part_end
	
				    $('#search_field').autocomplete({
						serviceUrl : $('#search_field').attr('url') +  "/books/autocomplete",
						minChars : 2

					});

				$('body').on('click', '#search_button', function(e) {
					e.preventDefault();
					var url = $(this).attr('url');
					page = 1;
					var query = $('#search_field').val();
					doPost({
						query : query,
						page : page,
						generalQuery: true
					}, url);
				});

	



});

function doPost(params, url) {

	$form = $('<form>', {
		action : url,
		method : 'POST',
		style : 'display:none'
	});

	$.each(params, function(index, value) {
		
		$('<input>', {
			name : index,
			val : value
		}).appendTo($form);
	});

	$form.appendTo('body');
	$form.submit();
	}
