

$(document).ready(
      function() {
        var mindate = $('#mindate').val().split(" ");
        
    $('#datetimepicker').datetimepicker({
        format: 'Y/m/d H',
        minDate: mindate[0],
        minTime: mindate[1]
    });
    
});
function sendR(){
  
    var a = $('#datetimepicker').val().toString().split(" ");
            var yearPart = a[0].split("/");
            var timepart = a[1].split(":");
            var finalD = new Date();
            finalD.setYear(Number(yearPart[0]));
            finalD.setMonth(Number(yearPart[1]) - 1);
            finalD.setDate(Number(yearPart[2]));
            finalD.setHours(Number(timepart[0]));
            finalD.setMinutes(0);
            finalD.setSeconds(0);
            
      $.ajax({
         url:    'order/add?bookid=' 
                  + $('#bookid').val()
                  + '&date=' 
                  + finalD.getTime(),
         success: function() {
                      location.href = "/books";
                  },
         async:   false
    });       
    
};