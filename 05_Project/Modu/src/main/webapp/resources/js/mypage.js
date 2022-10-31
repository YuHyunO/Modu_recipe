let mode;
let state;
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
		   			type: getTypeOption() } 
   		   break;
   case 3: data = { currentPage: currentPage }
   		   break;
   case 5: data = { currentPage: currentPage,
		   			state: state }
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
         console.log("X: mypage.js dataAgent 함수에서 에러 발생");
      }
   });
}

function displayRecommened(response){
	let recipeList = response.recipeList;
	let currentPage = response.currentPage;
	let totalPage = response.totalPage; 	
	let html = "";
	console.log("##response: "+response);
	console.log("##mode: "+mode);
	console.log(recipeList); //(2) [{…}, {…}]
	//console.log("##recipeList: "+recipeList); //문자열 넣으면 Object 타입으로만 보임 주의 ##recipeList: [object Object],[object Object]
	//console.log("##recipeList[0].mnickname: "+recipeList[0].mnickname); //닉네임 정상 출력
	//console.log("##recipeList[0].mNickname: "+recipeList[0].mNickname); //닉네임 undefined
	for(let item of recipeList){
		html += '<div id="recipe-item" class="col-6 col-md-3">';
		html += '<div class="recipe-thumb">';
		html += '<img src="/pics/recipe/'+item.id+'/'+item.foodPhoto+'" alt="recipe_mainImage">';
		html += '</div>';
		html += '<div class="recipe-desc">';
		html += '<h2 class="recipe-title">';
		html += '<a href="../recipe/detail?no='+item.id+'" target="_blank">'+item.title+'</a>'; //http://127.0.0.1:8080/recipe/detail?no=460
		html += '</h2>';
		html += '<figure class="profile">';
		html += '<img class="profile-img" src="/pics/profile/'+item.profileImg+'" alt="작성자">';
		html += '<span><em>&nbsp;'+item.mnickname+'</em></span>';
		html += '</figure>';
		html += '<div class="recipe-icons d-flex justify-content-between">';
		html += '<span class="d-flex align-items-center">';
		html += '<img class="stars" src="/imgs/stars5.png">';
		html += '<span class="p-1 mt-1">'+item.star+'('+item.stars+')</span>';
		html += '</span>';
		html += '<span class="d-flex align-items-center">';
		html += '<span class="p-1 mt-1">조회수&nbsp;'+item.hits+'</span>';
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
	let html = "";
	console.log("##mode: "+mode);
	for(let item of recipeList){
		html += '<div id="recipe-item" class="col-6 col-md-3">';
		html += '<div class="recipe-thumb">';
		html += '<img src="/pics/recipe/'+item.id+'/'+item.foodPhoto+'" alt="recipe_mainImage">';
		html += '</div>';
		html += '<div class="recipe-desc">';
		html += '<h2 class="recipe-title">';
		html += '<a href="../recipe/detail?no='+item.id+'">'+item.title+'</a>';
		html += '</h2>';
		html += '<figure class="profile">';
		html += '<img class="profile-img" src="/pics/profile/'+item.profileImg+'" alt="작성자">';
		html += '<span><em>&nbsp;'+item.mnickname+'</em></span>';
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
	console.log(response.recipeList);
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
	$("#recipe-list-3").html(html);
	setPagingArea();
	paginate(currentPage, totalPage);
}

function displayMyPost(response){
	console.log("4");
}

function displayFollow(response){ 
	let followList = response.followList;
	let currentPage = response.currentPage;
	let totalPage = response.totalPage;
	let html = "";	
	console.log("###"+mode);
	for(let item of followList){
		//console.log("##item:"+item); //##item:[object Object]
		html += '<div class="col-md-3">';
		html += '<div class="team-col">';
		html += '<input type="hidden" value="'+item.id+'">';
		html += '<figure>';
		html += '<img class="friendProfileimg" src="/pics/profile/'+item.profileImg+'" alt="파일없음"> ';
		html += '</figure>';
		html += '<p class="team-name">'+item.nickname+'</p>';
		html += '<large class="team-tag">'+item.email+'</large>';
		html += '<small class="team-tag">'+item.followDate+'부터 친구중</small>';
		html += '<div class="handlemyfriend">';
		html += '<button class="handlemyfriendBtn" onclick="../gofriendrecipe?id='+item.id+'">레시피 보기</button>&nbsp;';
		html += '<button class="handlemyfriendBtn" onclick="../deletefriend?id='+item.id+'">구독 끊기</button>';
		html += '</div>';
		html += '</div>';
		html += '</div>';
	}
	if(state == 1){		
		$("#following-list").html(html);
	}else if(state == 2){
		$("#follower-list").html(html);
	}
	setPagingArea();
	paginate(currentPage, totalPage);
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
	$("#pagination-pre-"+mode).html(divPrevious);	
	$("#pagination-area-"+mode).html(divArea);
	$("#pagination-next-"+mode).html(divNext);
}

//마이페이지 접속시 항상 실행되는 제이쿼리 function
$(function(){
	  // script
	  let mode1 = " ingredient-tab";
	  vegetables = new Array("가지", "감자", "고구마", "고추", "깻잎", "당근", "대파", 
			  "마늘","배추","애호박", "무", "브로콜리","비트", "상추", "샐러리", "시금치", 
			  "양송이버섯", "양파", "열무", "오이", "콩나물", "토마토", "파프리카", "팽이버섯", 
			  "표고버섯", "피망", "호박", "양배추");
	  for(i=0;i<vegetables.length;i++){
	    var html = '<div class="d-flex col check-div"><label><input type="checkbox" name="Checkbox1" onclick="setUrl(\'ingredient-tab\')" value="'+vegetables[i]+'"/><span class="px-1">'
	                + vegetables[i]+"</span></label></div>";
	    $('#vegetableSection').append(html);
	  }

	  meats = new Array("닭고기","돼지고기","목살","삼겹살","소고기","양고기","오리고기");
	  for(i=0;i<meats.length;i++){
	    var html = '<div class="d-flex col check-div"><label><input type="checkbox" name="Checkbox1" onclick="setUrl(\'ingredient-tab\')" value="'+meats[i]+'"/><span class="px-1">'
	                + meats[i]+"</span></label></div>";
	    $('#meatSection').append(html);
	  }

	  fishes = new Array("고등어","갈치","조기","굴비","굴","꼬막","골뱅이","새우","게맛살",
	          "꽁치","꽃게","낙지","다시마","동태","생태","명태","코다리","다시마","멸치","문어",
	          "미역","바지락","소라","오징어","어묵","연어","전어","조개","쭈꾸미","홍합");
	  for(i=0;i<fishes.length;i++){
	    var html = '<div class="d-flex col check-div"><label><input type="checkbox" name="Checkbox1" onclick="setUrl(\'ingredient-tab\')" value="'+fishes[i]+'"/><span class="px-1">'
	                + fishes[i]+"</span></label></div>";
	    $('#fishSection').append(html);
	  }
	  
	  processeds = new Array("당면","라면","파스타면","소면", "우동면","칼국수면","가래떡", 
			  "떡국떡","떡볶이떡","바게트","식빵","두부","순두부", "물만두", "베이컨","미트볼", 
			  "소시지", "스팸", "순대","만두", "햄");
	  for(i=0;i<processeds.length;i++){
	    var html = '<div class="d-flex col check-div"><label><input type="checkbox" name="Checkbox1" onclick="setUrl(\'ingredient-tab\')" value="'+processeds[i]+'"/><span class="px-1">'
	                + processeds[i]+"</span></label></div>";
	    $('#processedSection').append(html);
	  }
	  
	  seasonings = new Array("소금","설탕","식용유","참기름", "들기름", "꿀","청국장","된장",
			  "초고추장","물엿","올리고당","식초","고추장","후추","매실액","미림", "미원",
			  "굴소스","액젓","다진마늘","데리야끼소스","명란젓","마요네즈",
			  "짜장가루","카레가루","춘장","올리브유","케첩","핫소스");
	  for(i=0;i<seasonings.length;i++){
	    var html = '<div class="d-flex col check-div"><label><input type="checkbox" name="Checkbox1" onclick="setUrl(\'ingredient-tab\')" value="'+seasonings[i]+'"/><span class="px-1">'
	                + seasonings[i]+"</span></label></div>";
	    $('#seasoningSection').append(html);
	  }

	  milks = new Array("계란", "건포도", "검은콩","완두콩", "땅콩","마가린", "버터", "생크림",
			  "요거트", "메추리알", "모짜렐라치즈", "체다치즈", "참치캔",
			  "밀가루","부침가루","빵가루","찹쌀가루","아보카도", "캔옥수수", "딸기잼");
	  for(i=0;i<milks.length;i++){
	    var html = '<div class="d-flex col check-div"><label><input type="checkbox" name="Checkbox1" onclick="setUrl(\'ingredient-tab\')" value="'+milks[i]+'"/><span class="px-1">'
	                + milks[i]+"</span></label></div>";
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

function getTypeOption(){
	let selector = document.getElementById("recipe-access-option");
	let type = selector.options[selector.selectedIndex].value;
	currentPage = 1;
	return type;
}

function getState(e){
	state = $(e).val();
	mode = 5;
	setUrl(mode);
}

function setFollowPageId(state){
	
}

function getCookie(name) {
	  let matches = document.cookie.match(new RegExp(
	    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
	  ));
	  return matches ? decodeURIComponent(matches[1]) : undefined;
}

function activePage(e){
	$('.page-number').removeClass('active');
	$(e).addClass('active')
}
