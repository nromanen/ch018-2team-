function getDateInFormat(tmpDate){
    
    var date = new Date(Number(tmpDate));
    
    return date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate() + " " + date.getHours();
    
}
function getLongFromFormatTime(formatTime){
    var tmpDate = formatTime.split(" ");
    var datePart = tmpDate[0].split("/");
    var timePart = tmpDate[1].split(":");
    var date = new Date();
    date.setFullYear(datePart[0]);
    date.setMonth(datePart[1] - 1);
    date.setDate(datePart[2]);
    date.setHours(timePart[0]);
    date.setMinutes(0);
    date.setSeconds(0);
    return date.getTime();
    
}