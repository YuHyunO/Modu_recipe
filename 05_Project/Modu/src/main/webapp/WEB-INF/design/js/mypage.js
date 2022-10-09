function getCheckboxValue()  {
  // 선택된 체크박스들 가져오기
  const query = 'input[name="Checkbox1"]:checked';
  const selectedEls = 
      document.querySelectorAll(query);
  
  // 선택된 체크박스에서 value 찾기
  let result = '';
  selectedEls.forEach((el) => {
    result += el.value + ' ';
  });
  
  // 찾은 value값(재료명들) 출력(검색창 직전)
  document.getElementById('result').innerText
    = result;
}

// 권장하는 방법 - 마이페이지 접속시 항상 실행되는 제이쿼리 function
$(function(){
  // script
  vegetables = new Array("감자", "고구마", "당근", "오이", "대파", "양파",  
  "상추", "가지", "고추", "무", "깻잎", "마늘","배추","애호박", "호박",
  "양배추", "양송이버섯", "팽이버섯", "표고버섯", "열무", "콩나물",
  "토마토", "시금치", "샐러리", "브로콜리","비트", "파프리카", "피망");
  for(i=0;i<vegetables.length;i++){
    var html = '<div class="d-flex col check-div"><input type="checkbox" name="Checkbox1" onclick="getCheckboxValue()" value="'+vegetables[i]+'"/><span class="px-1">'
                + vegetables[i]+"</span></<div>";
    $('#vegetableSection').append(html);
  }

  meats = new Array("삼겹살","목살","닭고기","돼지고기","소고기","양고기","오리고기");
  for(i=0;i<meats.length;i++){
    var html = '<div class="d-flex col check-div"><input type="checkbox" name="Checkbox1" onclick="getCheckboxValue()" value="'+meats[i]+'"/><span class="px-1">'
                + meats[i]+"</span></<div>";
    $('#meatSection').append(html);
  }

  fishes = new Array("고등어","갈치","조기","굴비","굴","꼬막","골뱅이","새우","게맛살",
          "꽁치","꽃게","낙지","다시마","동태","생태","명태","코다리","다시마","멸치","문어",
          "미역","바지락","소라","오징어","어묵","연어","전어","조개","쭈꾸미","홍합");
  for(i=0;i<fishes.length;i++){
    var html = '<div class="d-flex col check-div"><input type="checkbox" name="Checkbox1" onclick="getCheckboxValue()" value="'+fishes[i]+'"/><span class="px-1">'
                + fishes[i]+"</span></<div>";
    $('#fishSection').append(html);
  }

  processeds = new Array("베이컨","미트볼", "소시지",
  "스팸", "햄", "순대","만두", "물만두", "당면","라면","파스타면","소면",
  "우동면","칼국수면","가래떡", "떡국떡","떡볶이떡","바게트","식빵","두부","순두부");
  for(i=0;i<processeds.length;i++){
    var html = '<div class="d-flex col check-div"><input type="checkbox" name="Checkbox1" onclick="getCheckboxValue()" value="'+processeds[i]+'"/><span class="px-1">'
                + processeds[i]+"</span></<div>";
    $('#processedSection').append(html);
  }

  seasonings = new Array("소금","설탕","식용유","참기름","꿀","청국장","된장",
  "초고추장","물엿","올리고당","식초","고추장","후추","매실액","미림",
  "굴소스","액젓","다진마늘","데리야끼소스","명란젓","마요네즈",
  "짜장가루","카레가루","춘장","올리브유","케첩","핫소스");
  for(i=0;i<seasonings.length;i++){
    var html = '<div class="d-flex col check-div"><input type="checkbox" name="Checkbox1" onclick="getCheckboxValue()" value="'+seasonings[i]+'"/><span class="px-1">'
                + seasonings[i]+"</span></<div>";
    $('#seasoningSection').append(html);
  }

  milks = new Array("계란", "생크림", "버터", "메추리알", "요거트",
    "체다치즈", "모짜렐라치즈", "마가린", "캔옥수수", "참치캔",
    "밀가루","부침가루","빵가루","찹쌀가루","아보카도", "건포도", "검은콩","완두콩");
  for(i=0;i<milks.length;i++){
    var html = '<div class="d-flex col check-div"><input type="checkbox" name="Checkbox1" onclick="getCheckboxValue()" value="'+milks[i]+'"/><span class="px-1">'
                + milks[i]+"</span></<div>";
    $('#milkSection').append(html);
  }
});

function activePage(e){
  $('.page-number').removeClass('active');
  $(e).addClass('active')
}