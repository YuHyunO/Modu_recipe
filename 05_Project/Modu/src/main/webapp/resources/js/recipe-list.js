$(function(e){
    // 랜덤추천 컬러 변경
    function randomColor(){
        let r_color = Math.floor(Math.random() * 255);
        let g_color = Math.floor(Math.random() * 255);
        let b_color = Math.floor(Math.random() * 255);
        $('.random').css('color', 'rgb('+r_color+','+g_color+', '+b_color+')');
    }

    let random;
    randomColor();
    random = setInterval(randomColor, 1000);

    

});

// 태그검색
function searchTag(e){
    let text = $(e).text(); //현재 클릭한 태그의 텍스트
    // 클래스명 변경
    $('.tag-li').removeClass('active');
    $(e).addClass('active');
    
}