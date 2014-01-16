$(document).ready(function() {
	var field = new Array("#titleval"); //required fields 				
		$("#titleval").change(function(e) {
			console.log("submit");
			var error=0; // errors
			$("form #addbook").find(":input").each(function() {// every input
				for(var i=0;i<field.length;i++){ // if field is required
					if($(this).attr("id")==field[i]){ 
						
						if(!$(this).val()){// if field is empty
							$(this).css('border', 'red 1px solid');// red border
							error=1;// error
														
						}
						else{
							$(this).css('border', '#CCCCCC 1px solid');// else gray border
						}
						
					}						
				}				
		   })
		   
		   
		   //check email
		   
			var email = $("#titleval").val();
		   	if(!isValidEmailAddress(email)){
				error=2;
				$("#inputEmailReg").css('border', 'red 1px solid');// if not valid - red border
			}
		   	else{
				$("#inputEmailReg").css('border', '#CCCCCC 1px solid');// else gray border
			}
			
			//password
			var pas1 = $("#inputPasswordReg").val();
			var pas2 = $("#confirmPasswordReg").val();
		   	if(pas1!=pas2){
				error=3;
				$("#inputPasswordReg").css('border', 'red 1px solid');// red border
				$("#confirmPasswordReg").css('border', 'red 1px solid');// red
			}
		   	else {
		   		$("#inputPasswordReg").css('border', '#CCCCCC 1px solid');
		   		$("#confirmPasswordReg").css('border', '#CCCCCC 1px solid');
		   	}
			
			if(error==0){ // if no errors all good :)
				console.log("sdsdsdsdsdsdsd");
				//return true;
			}
			else{
				console.log("sssssssssssssss");
			var err_text = "";
			if(error==1)  err_text="Не все обязательные поля заполнены!";
			
			
			$("#messenger").html(err_text);	
			$("#messenger").fadeIn("slow");	
			//return false; //
			}


	});