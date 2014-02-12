 $(document).ready(function() {
    	var max_term = $('#term_value').attr('value');
    	console.log($('#term-range'));
    	$( "#term-range" ).slider({
    		  value : max_term > 14 ? 14 : max_term,
		      min: 1,
		      max: max_term,
		      slide: function( event, ui ) {
		        $( "#term" ).val(ui.value + " days");
		      }
		    });
		    $( "#term" ).val($( "#term-range" ).slider( "value") + " days" );
		    
		    
		$('#issue_submit').click(function (e) {
			e.preventDefault();
			
			$form = $('#issue_form');
			
			$('<input>', {
				name : 'term',
				val : $('#term-range').slider("value")
			}).appendTo($form);
			
			$form.submit();
		});   
		
 });
			