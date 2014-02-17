$(document).ready(function() {
	$('#ca-container').contentcarousel();
});

$(document).ready(function() {
	$('#ca-container_arrival').contentcarousel();
});


var int = setInterval("$('.ca-nav-next').trigger('click');",2000);    

$('.ca-item').hover(function(){
    clearInterval(int);
},function(){
    int = setInterval("$('.ca-nav-next').trigger('click');",2000);    
});