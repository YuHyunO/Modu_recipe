function setUrl(e){
   let mode;
   let url = "";
   let tab = $(e).attr("id");

   if(tab == null){
	   tab = e;
   }

   switch(tab){
   case "ingredient-tab": url = "/mypage/recommend";
        mode = 1;
        break;
   case "article-tab": url = "/mypage/recipe";
        mode = 2;
        break;
   case "bookmark-tab": url = "/mypage/bookmark";
        mode = 3;
        break;
   case "mypost-tab": url = "/mypage/post";
        mode = 4;
        break;
   case "myfriend-tab": url = "/mypage/follow";
        mode = 5;
   }
   setData(url, mode);
}

function setData(url, mode){
   let data;
   switch(mode){
   case 1: data = {list: getCheckboxValue()}
   }
   dataAgent(url, data, mode);
}

function dataAgent(url, data, mode){
   $.ajax({
      url: url,
      type: "GET",
      data: data,
      dataType: "JSON",
      traditional: true,
      success: function(response){
         switch(mode){
         case 1: displayRecommened(response);
                 break;
         case 2: displayMyRecipe(response);
                 break;         
         case 3: displayBookmark(response);
                 break;
         case 4: displayMyPost(response);
                 break;
         case 5: displayFollow(response);
         }
         
      },
      error: function(error){
         console.log("X");
      }
   });
}

function setPage(e){
	currentPage = $(e).text();
	let id = $(e).attr("id");
	if(id =="pre" || id == "next"){
		currentPage = id;
	}
	data = {
			currentPage: curPage,
			list: list
			}
	dataAgent(data)
}

function displayRecommened(response){
	console.log("1");
	let recipeList = response.recipeList;
	let pageSize = response.pageSize;
	console.log("##"+recipeList);
	
	let html = "";
	for(let item of recipeList){
		html += '<div id="recipe-item" class="col-6 col-md-3">';
		html += '<div class="recipe-thumb">';
		html += '<img src="/imgs/content/thumb-1.png" alt="/imgs/content/thumb-1.png">';
		html += '</div>';
		html += '<div class="recipe-desc">';
		html += '<h2 class="recipe-title">';
		html += '<a href="'+item.id+'">'+item.title+'</a>';
		html += '</h2>';
		html += '<figure class="profile">';
		html += '<img class="profile-img" src="/imgs/content/auth-00.png" alt="작성자">';
		html += '<span><em>&nbsp;'+item.nickname+'</em></span>';
		html += '</figure>';
		html += '<div class="recipe-icons d-flex justify-content-between">';
		html += '<span class="d-flex align-items-center">';
		html += '<img class="stars" src="/imgs/stars5.png">';
		html += '<span class="p-1 mt-1">'+item.star+'('+item.stars+')</span>';
		html += '</span>';
		html += '<span class="d-flex align-items-center">';
		html += '<span class="p-1 mt-1">조회 '+item.hits+'</span>';
		html += '</span>';
		html += '</div>';
		html += '</div>';
		html += '</div>';
		
		$("#recipe-list").html(html);
	}
}

function displayMyRecipe(response){
	console.log("2");
}

function displayBookmark(response){
	console.log("3");
}

function displayMyPost(response){
	console.log("4");
}

function displayFollow(response){	
	console.log("5");
}

function paginate(curPage, totalPage){
	let cp = curPage;
	let total = totalPage;
	let divPrevious = "";
	let divArea = "";
	let divNext = "";
	
	//pagination-previous
	if(cp != 1){
		divPrevious += '<li class="page-item"><a class="page-link page-previous"';
		divPrevious += 'href="javascript:void(0);" onclick="setPage(this)" id="pre">＜</a></li>';
	}else if(cp == 1){
		divPrevious += '<li class="page-item"><a class="page-link page-previous"';
		divPrevious += 'href="javascript:void(0);">＜</a></li>';
	}
	//pagination-area
	if(total > 5){
		if(cp < 3){
			for(let i=1; i<=5; i++){
				if(i == cp){
					divArea += '<li class="page-item"><a class="page-link active page-number"';
					divArea += 'href="javascript:void(0);" onclick="activePage(this);setPage(this);">'+i+'</a></li>';
				}else{
					divArea += '<li class="page-item"><a class="page-link page-number" ';
					divArea += 'href="javascript:void(0);" onclick="activePage(this);setPage(this);">'+i+'</a></li>';
				}
			}
		}else if(cp >= 3){
			if(total-cp < 2){
				for(let i=total-4; i<=total; i++){
					if(i == cp){
						divArea += '<li class="page-item"><a class="page-link active page-number"';
						divArea += 'href="javascript:void(0);" onclick="activePage(this);setPage(this);">'+i+'</a></li>';
					}else{
						divArea += '<li class="page-item"><a class="page-link page-number" ';
						divArea += 'href="javascript:void(0);" onclick="activePage(this);setPage(this);">'+i+'</a></li>';
					}
				}
			}else{
				for(let i=cp-2; i<=cp+2; i++){
					if(i == cp){
						divArea += '<li class="page-item"><a class="page-link active page-number"';
						divArea += 'href="javascript:void(0);" onclick="activePage(this);setPage(this);">'+i+'</a></li>';
					}else{
						divArea += '<li class="page-item"><a class="page-link page-number"';
						divArea += 'href="javascript:void(0);" onclick="activePage(this);setPage(this);">'+i+'</a></li>';
					}
				}
			}
		}		
	}else if(total < 5){
		for(let i=1; i<=total; i++){
			if(i == cp){
				divArea += '<li class="page-item"><a class="page-link active page-number"';
				divArea += 'href="javascript:void(0);" onclick="activePage(this);setPage(this);">'+i+'</a></li>';
			}else{
				divArea += '<li class="page-item"><a class="page-link page-number"';
				divArea += 'href="javascript:void(0);" onclick="activePage(this);setPage(this);">'+i+'</a></li>';
			}
		}
	}
	//pagination-next
	if(cp != total){
		divNext += '<li class="page-item"><a class="page-link page-next"';
		divNext += 'href="javascript:void(0);" onclick="setPage(this)" id="next">＞</a></li>';
	}else if(cp == total){
		divNext += '<li class="page-item"><a class="page-link page-next"';
		divNext += 'href="javascript:void(0);">＞</a></li>';
	}
	
	$("#pagination-previous").html(divPrevious);	
	$("#pagination-area").html(divArea);
	$("#pagination-next").html(divNext);
}


// 권장하는 방법 - 마이페이지 접속시 항상 실행되는 제이쿼리 function
$(function(){
	  // script
	  let mode1 = " ingredient-tab";
	  vegetables = new Array("감자", "고구마", "당근", "오이", "대파", "양파",  
	  "상추", "가지", "고추", "무", "깻잎", "마늘","배추","애호박", "호박",
	  "양배추", "양송이버섯", "팽이버섯", "표고버섯", "열무", "콩나물",
	  "토마토", "시금치", "샐러리", "브로콜리","비트", "파프리카", "피망");
	  for(i=0;i<vegetables.length;i++){
	    var html = '<div class="d-flex col check-div"><label><input type="checkbox" name="Checkbox1" onclick="setUrl(\'ingredient-tab\')" value="'+vegetables[i]+'"/><span class="px-1">'
	                + vegetables[i]+"</span></label></<div>";
	    $('#vegetableSection').append(html);
	  }

	  meats = new Array("삼겹살","목살","닭고기","돼지고기","소고기","양고기","오리고기");
	  for(i=0;i<meats.length;i++){
	    var html = '<div class="d-flex col check-div"><label><input type="checkbox" name="Checkbox1" onclick="setUrl(\'ingredient-tab\')" value="'+meats[i]+'"/><span class="px-1">'
	                + meats[i]+"</span></label></<div>";
	    $('#meatSection').append(html);
	  }

	  fishes = new Array("고등어","갈치","조기","굴비","굴","꼬막","골뱅이","새우","게맛살",
	          "꽁치","꽃게","낙지","다시마","동태","생태","명태","코다리","다시마","멸치","문어",
	          "미역","바지락","소라","오징어","어묵","연어","전어","조개","쭈꾸미","홍합");
	  for(i=0;i<fishes.length;i++){
	    var html = '<div class="d-flex col check-div"><label><input type="checkbox" name="Checkbox1" onclick="setUrl(\'ingredient-tab\')" value="'+fishes[i]+'"/><span class="px-1">'
	                + fishes[i]+"</span></label></<div>";
	    $('#fishSection').append(html);
	  }

	  processeds = new Array("베이컨","미트볼", "소시지",
	  "스팸", "햄", "순대","만두", "물만두", "당면","라면","파스타면","소면",
	  "우동면","칼국수면","가래떡", "떡국떡","떡볶이떡","바게트","식빵","두부","순두부");
	  for(i=0;i<processeds.length;i++){
	    var html = '<div class="d-flex col check-div"><label><input type="checkbox" name="Checkbox1" onclick="setUrl(\'ingredient-tab\')" value="'+processeds[i]+'"/><span class="px-1">'
	                + processeds[i]+"</span></label></<div>";
	    $('#processedSection').append(html);
	  }

	  seasonings = new Array("소금","설탕","식용유","참기름","꿀","청국장","된장",
	  "초고추장","물엿","올리고당","식초","고추장","후추","매실액","미림",
	  "굴소스","액젓","다진마늘","데리야끼소스","명란젓","마요네즈",
	  "짜장가루","카레가루","춘장","올리브유","케첩","핫소스");
	  for(i=0;i<seasonings.length;i++){
	    var html = '<div class="d-flex col check-div"><label><input type="checkbox" name="Checkbox1" onclick="setUrl(\'ingredient-tab\')" value="'+seasonings[i]+'"/><span class="px-1">'
	                + seasonings[i]+"</span></label></<div>";
	    $('#seasoningSection').append(html);
	  }

	  milks = new Array("계란", "생크림", "버터", "메추리알", "요거트",
	    "체다치즈", "모짜렐라치즈", "마가린", "캔옥수수", "참치캔",
	    "밀가루","부침가루","빵가루","찹쌀가루","아보카도", "건포도", "검은콩","완두콩");
	  for(i=0;i<milks.length;i++){
	    var html = '<div class="d-flex col check-div"><label><input type="checkbox" name="Checkbox1" onclick="setUrl(\'ingredient-tab\')" value="'+milks[i]+'"/><span class="px-1">'
	                + milks[i]+"</span></label></<div>";
	    $('#milkSection').append(html);
	  }
});

function getCheckboxValue()  {
	  // 선택된 체크박스들 가져오기
	const query = 'input[name="Checkbox1"]:checked';
	const selectedEls = document.querySelectorAll(query);
  
  // 선택된 체크박스에서 value 찾기
	let data = new Array();
	let result = '';
	selectedEls.forEach((el) => {
	    result += el.value + ' ';
	    data.push(el.value);
	});
  // 찾은 value값(재료명들) 출력(검색창 직전)
	document.getElementById('result').innerText = result;
  
	return data;
}

function activePage(e){
	$('.page-number').removeClass('active');
	$(e).addClass('active')
}


