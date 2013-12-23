

$(document).ready(
      function() {
        var mindate = $('#mindate').val().split(" ");
        alert(mindate[0]);
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
            var final = new Date();
            final.setYear(Number(yearPart[0]));
            final.setMonth(Number(yearPart[1]) - 1);
            final.setDate(Number(yearPart[2]));
            final.setHours(Number(timepart[0]));
            final.setMinutes(0);
            final.setSeconds(0);
            
      $.ajax({
         url:    'order/add?bookid=' 
                  + $('#bookid').val()
                  + '&date=' 
                  + final.getTime(),
         success: function() {
                      location.href = "/books";
                  },
         async:   false
    });       
    
};