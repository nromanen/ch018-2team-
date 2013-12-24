
$(document).ready(function(){
   
   
   
   $('.datetimepicker').each(function (){
      
       var minTime = $(this).next().val().replace(/-/g, '/').split(" ");
       
       $(this).datetimepicker({
        format: 'Y/m/d H',
        minDate: minTime[0],
        minTime: minTime[1].split(":")[0]
           
       });
       
   });
   
   $('.edit').click(function () {
        var date = $(this).prev().prev().prev().val().split(" ");
        var yearPart = date[0].split("/");
        var timepart = date[1].substring(1,2);
        var finalD = new Date();
        finalD.setYear(Number(yearPart[0]));
        finalD.setMonth(Number(yearPart[1]) - 1);
        finalD.setDate(Number(yearPart[2]));
        
        finalD.setHours(Number(timepart));
        finalD.setMinutes(0);
        finalD.setSeconds(0);
       
        
        var bookid = $(this).prev().val();
        
        $.ajax({
         url:    'edit?bookid=' 
                  + bookid
                  + '&date=' 
                  + finalD.getTime(),
         success: function() {
                      alert("yeah");
                  },
         async:   false
    });     
   });

});

    
    

    
