
$(document).ready(function(){
   
   
   
   $('.datetimepicker').each(function (){
      
       var minD = $(this).val().split(" ");
       if(minD == "")
           minD = new Date().dateFormat("Y/m/d H").split(" ");
       
       $(this).datetimepicker({
        format: 'Y/m/d H',
        minDate: minD[0],
        minTime: minD[1].split(":")[0]
           
       });
       
   });
   
});