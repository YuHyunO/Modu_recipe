<<<<<<< HEAD
function getCheckboxValue()  {
  // 선택된 체크박스들 가져오기
  const query = 'input[name="Checkbox1"]:checked';
  const selectedEls = 
      document.querySelectorAll(query);
  
  // 선택된 체크박스에서 value 찾기
  let data = new Array();
  let result = '';
  selectedEls.forEach((el) => {
    result += el.value + ' ';
    data.push(el.value);
  });
  console.log(data);
  // 찾은 value값(재료명들) 출력(검색창 직전)
  document.getElementById('result').innerText
    = result;
  
  dataAgent(data);
}

function dataAgent(data){
	$.ajax({
		url: "./mypage-recommend",
		type: "GET",
		data: {data: data},
		dataType: "JSON",
		traditional: true,
		success: function(response){
			let recipeList = response.recipeList;
			let pageSize = response.pageSize;
			//updateData(recipeList, pageSize);
		},
		error: function(error){
			console.log("error"+error);
			//alert("접속이 원활하지 않습니다.\n브라우저 재접속 후 시도해주세요.");
		}
	});
}
function updateData(recipeList, pageSize){
=======
let mode;
let currentPage = 1;

function setPage(e){
	currentPage = $(e).text();
	let id = $(e).attr("id");
	console.log("###"+id);
	if(id =="pre" || id == "next"){
		currentPage = id;
	}
	setUrl(mode);
}

function setUrl(e){
   let url = "";
   let tab = $(e).attr("id");
   
   if(tab == "recipe-access-option"){
	   tab = 2;
   }
   if(tab == null){
	   tab = e;
   }

   switch(tab){
   case 1:
   case "ingredient-tab":
	    url = "/mypage/recommend";
        mode = 1;
        break;
   case 2:        
   case "article-tab": 
	    url = "/mypage/recipe";
        mode = 2;
        break;
   case 3:        
   case "bookmark-tab":   
	    url = "/mypage/bookmark";
        mode = 3;
        break;
   case 4:
   case "mypost-tab":
	    url = "/mypage/post";
        mode = 4;
        break;
   case 5:
   case "myfriend-tab":
	    url = "/mypage/follow";
        mode = 5;
   }
   console.log("###url: "+url);
   console.log("###currentPage: "+currentPage);
   setData(url, mode);
}

function setData(url, mode){
   let data;
   switch(mode){
   case 1: data = { list: getCheckboxValue(),
			   		currentPage: currentPage }
   		   break;
   case 2: data = { currentPage: currentPage,
		   			type: getTypeOption()} 
   		   break;
   case 3: data = { currentPage: currentPage }
   		   break;
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

function displayRecommened(response){
	let recipeList = response.recipeList;
	let currentPage = response.currentPage;
	let totalPage = response.totalPage; 	
	let html = "";
	console.log("##mode: "+mode);
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
	}
	$("#recipe-list-1").html(html);
	setPagingArea();
	paginate(currentPage, totalPage);
}

function displayMyRecipe(response){
	let recipeList = response.recipeList;
	let currentPage = response.currentPage;
	let totalPage = response.totalPage; 	
>>>>>>> 844e045a256e24b6289486ec9ae5bfca9298244e
	let html = "";
	console.log("##mode: "+mode);
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
	}
	$("#recipe-list-2").html(html);
	setPagingArea();
	paginate(currentPage, totalPage);
}

function displayBookmark(response){
	let recipeList = response.recipeList;
	let currentPage = response.currentPage;
	let totalPage = response.totalPage; 	
	let html = "";
	console.log("##mode: "+mode);
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
				
	}
<<<<<<< HEAD
}

function setUrl(e){
	let url = "";
	let tab = $(e).text();
=======
	$("#recipe-list-3").html(html);
	setPagingArea();
	paginate(currentPage, totalPage);
}

function displayMyPost(response){
	console.log("4");
}

function displayFollow(response){	
	console.log("5");
}

function setPagingArea(){
	let commonPagingArea = "";
	commonPagingArea += '<nav aria-label="Page navigation">';
	commonPagingArea += '<ul id="pagination-ul" class="pagination justify-content-center">';
	commonPagingArea += '<div id="pagination-pre-'+mode+'" class="pagination justify-content-center"></div>';
	commonPagingArea += '<div id="pagination-area-'+mode+'" class="pagination justify-content-center"></div>';
	commonPagingArea += '<div id="pagination-next-'+mode+'" class="pagination justify-content-center"></div>';
	commonPagingArea += '</ul>';
	commonPagingArea += '</nav>';

	$(".common-area").empty();
	$("#paging-area-"+mode).html(commonPagingArea);
}

function paginate(currentPage, totalPage){
	let cp = currentPage;
	let total = totalPage;
	let divPrevious = "";
	let divArea = "";
	let divNext = "";
>>>>>>> 844e045a256e24b6289486ec9ae5bfca9298244e
	
	switch(tab){
	case "냉장고 비우기": url = "/mypage/mypage-recommend";
		  
		  break;
	case "나의 레시피": url = "/mypage/mypage-recipe";
	
		  break;
	case "북마크한 레시피": url = "/mypage/mypage-bookmark";
	
		  break;
	case "내 게시글": url = "/mypage/mypage-post";
	
		  break;
	case "친구 관리": url = "/mypage/mypage-follow";
		  
	}
	setData(url);
}

function setData(url){
	let data = "";
	
	contentAgent(url, data);
}

function contentAgent(url, data){
	console.log("URL: "+url);
	$.ajax({
		url: url,
		type: "GET",
		data: data,
		dataType: "JSON",
		traditional: true,
		success: function(response){
			console.log("success");
			
		},
		error: function(error){
			console.log("X");
		}
	});
}

function showRecommned(){
	
}

function showMyRecipe(){
	
}

function showBookmark(){
	
}

function showMyPost(){
	
}

function showFollow(){	
	
	let html = "";
	
	let followList = response.followList;
	
	for(let item of followList.following){
		//내가 팔로우 한 사람
	}
	for(let itme of followList.follower){
		//나를 팔로우 한 사람
	}
<<<<<<< HEAD
	
=======
	$("#pagination-pre-"+mode).html(divPrevious);	
	$("#pagination-area-"+mode).html(divArea);
	$("#pagination-next-"+mode).html(divNext);
>>>>>>> 844e045a256e24b6289486ec9ae5bfca9298244e
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

<<<<<<< HEAD
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
=======
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
>>>>>>> 844e045a256e24b6289486ec9ae5bfca9298244e

  milks = new Array("계란", "생크림", "버터", "메추리알", "요거트",
    "체다치즈", "모짜렐라치즈", "마가린", "캔옥수수", "참치캔",
    "밀가루","부침가루","빵가루","찹쌀가루","아보카도", "건포도", "검은콩","완두콩");
  for(i=0;i<milks.length;i++){
    var html = '<div class="d-flex col check-div"><input type="checkbox" name="Checkbox1" onclick="getCheckboxValue()" value="'+milks[i]+'"/><span class="px-1">'
                + milks[i]+"</span></<div>";
    $('#milkSection').append(html);
  }
});

<<<<<<< HEAD
=======
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

function getTypeOption(){
	let selector = document.getElementById("recipe-access-option");
	let type = selector.options[selector.selectedIndex].value;
	currentPage = 1;
	return type;
}

>>>>>>> 844e045a256e24b6289486ec9ae5bfca9298244e
function activePage(e){
  $('.page-number').removeClass('active');
  $(e).addClass('active')
}
