
$(document).ready(function () {
   
     search("");
     
     $('#search_field').autocomplete({
         serviceUrl: window.location.pathname + "autocomplete",
         minChars: 2
         
     });
     
});
     




