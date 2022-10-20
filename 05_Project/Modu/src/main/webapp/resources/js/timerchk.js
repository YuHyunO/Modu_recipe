//실시간 타임아웃 기능, 출처: https://devsh.tistory.com/m/92

var iSecond; //초단위로 환산
var timerchecker = null;
window.onload = function() {
    fncClearTime();
    initTimer();
}
 
function fncClearTime() {
    iSecond = 3600; //(참고) 3600=1시간, 7200=2시간, 60=1분, 10=10초
}
 
Lpad = function(str, len) {
    str = str + "";
    while (str.length < len) {
        str = "0" + str;
    }
    return str;
}
 
initTimer = function() {
    var timer = document.getElementById("timer");
    rHour = parseInt(iSecond / 3600);
    rHour = rHour % 60;
 
    rMinute = parseInt(iSecond / 60);
    rMinute = rMinute % 60;
    rSecond = iSecond % 60;
 
    if (iSecond > 0) {
        timer.innerHTML = "세션 " + Lpad(rHour, 2) + "시간 " + Lpad(rMinute, 2)
                + "분 " + Lpad(rSecond, 2) + "초 남음";
        iSecond--;
        timerchecker = setTimeout("initTimer()", 1000); // 1초 간격으로 체크
    } else {
        logoutUser();
    }
    
}
 
function refreshTimer() { //시간연장 버튼 클릭시 
    var xhr = initAjax();
    //session.setMaxInactiveInterval(1800); 
    
    xhr.open("GET", "/", false);
    xhr.send();
    fncClearTime();
}
 
function logoutUser() {
    clearTimeout(timerchecker);
    alert('자동 로그아웃 되었습니다. \n재로그인 후 이용 바랍니다.');
    
    var xhr = initAjax();
    xhr.open("GET", "/member/logout", false);
    //console.log("xhr.open 직후 xhr2:"+xhr); //[object XMLHttpRequest]
    xhr.send();
    location.replace("/"); //다른 페이지로 이동 
    //location.reload(); //현재 접속중인 페이지를 다시 불러오는 새로고침 역할 
}
 
function initAjax() { // 브라우저에 따른 AjaxObject 인스턴스 분기 처리
    var xmlhttp;
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;
}