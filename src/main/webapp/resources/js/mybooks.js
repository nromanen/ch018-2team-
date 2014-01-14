$(document).ready(function(){
    
    $('.mybooks_date').each(function(){
        var rawDate = $(this).val();
        var date = getDateInFormat(rawDate);
        var $parent = $(this).parent();
        $parent.text(date);
        var diff = parseInt( (rawDate - new Date().getTime())/(24*3600*1000) );
        $parent.next().text(diff);
        if(diff*(-1) > 0){
            //alert("AA");
            $parent.parent().parent().css("background-color", "#FF6633");
        }else if(diff < 5){
            $parent.parent().parent().css("background-color", "#FFFF66");
        }else{
            $parent.parent().parent().css("background-color", "#99FF66");
        }
    });
    
});

