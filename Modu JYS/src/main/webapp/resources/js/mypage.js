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
		html += '<img class="profile-img" src="/imgs/content/auth-00.png" alt="?????????">';
		html += '<span><em>&nbsp;'+item.nickname+'</em></span>';
		html += '</figure>';
		html += '<div class="recipe-icons d-flex justify-content-between">';
		html += '<span class="d-flex align-items-center">';
		html += '<img class="stars" src="/imgs/stars5.png">';
		html += '<span class="p-1 mt-1">'+item.star+'('+item.stars+')</span>';
		html += '</span>';
		html += '<span class="d-flex align-items-center">';
		html += '<span class="p-1 mt-1">?????? '+item.hits+'</span>';
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
		html += '<img src="/imgs/content/thumb-1.png" alt="/imgs/content/thumb-1.png">';
		html += '</div>';
		html += '<div class="recipe-desc">';
		html += '<h2 class="recipe-title">';
		html += '<a href="'+item.id+'">'+item.title+'</a>';
		html += '</h2>';
		html += '<figure class="profile">';
		html += '<img class="profile-img" src="/imgs/content/auth-00.png" alt="?????????">';
		html += '<span><em>&nbsp;'+item.nickname+'</em></span>';
		html += '</figure>';
		html += '<div class="recipe-icons d-flex justify-content-between">';
		html += '<span class="d-flex align-items-center">';
		html += '<img class="stars" src="/imgs/stars5.png">';
		html += '<span class="p-1 mt-1">'+item.star+'('+item.stars+')</span>';
		html += '</span>';
		html += '<span class="d-flex align-items-center">';
		html += '<span class="p-1 mt-1">?????? '+item.hits+'</span>';
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
		html += '<img class="profile-img" src="/imgs/content/auth-00.png" alt="?????????">';
		html += '<span><em>&nbsp;'+item.nickname+'</em></span>';
		html += '</figure>';
		html += '<div class="recipe-icons d-flex justify-content-between">';
		html += '<span class="d-flex align-items-center">';
		html += '<img class="stars" src="/imgs/stars5.png">';
		html += '<span class="p-1 mt-1">'+item.star+'('+item.stars+')</span>';
		html += '</span>';
		html += '<span class="d-flex align-items-center">';
		html += '<span class="p-1 mt-1">?????? '+item.hits+'</span>';
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
		html += '<img class="friendProfileimg" src="/pics/profile/'+item.profileImg+'" alt="????????????"> ';
		html += '</figure>';
		html += '<p class="team-name">'+item.nickname+'</p>';
		html += '<large class="team-tag">'+item.email+'</large>';
		html += '<small class="team-tag">'+item.followDate+'?????? ?????????</small>';
		html += '<div class="handlemyfriend">';
		html += '<button class="handlemyfriendBtn" onclick="../gofriendrecipe?id='+item.id+'">????????? ??????</button>&nbsp;';
		html += '<button class="handlemyfriendBtn" onclick="../deletefriend?id='+item.id+'">?????? ??????</button>';
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
		divPrevious += 'href="javascript:void(0);" onclick="setPage(this)" id="pre">???</a></li>';
	}else if(cp == 1){
		divPrevious += '<li class="page-item"><a class="page-link page-previous"';
		divPrevious += 'href="javascript:void(0);">???</a></li>';
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
		divNext += 'href="javascript:void(0);" onclick="setPage(this)" id="next">???</a></li>';
	}else if(cp == total){
		divNext += '<li class="page-item"><a class="page-link page-next"';
		divNext += 'href="javascript:void(0);">???</a></li>';
	}
	$("#pagination-pre-"+mode).html(divPrevious);	
	$("#pagination-area-"+mode).html(divArea);
	$("#pagination-next-"+mode).html(divNext);
}


// ???????????? ?????? - ??????????????? ????????? ?????? ???????????? ???????????? function
$(function(){
	  // script
	  let mode1 = " ingredient-tab";
	  vegetables = new Array("??????", "?????????", "??????", "??????", "??????", "??????",  
	  "??????", "??????", "??????", "???", "??????", "??????","??????","?????????", "??????",
	  "?????????", "???????????????", "????????????", "????????????", "??????", "?????????",
	  "?????????", "?????????", "?????????", "????????????","??????", "????????????", "??????");
	  for(i=0;i<vegetables.length;i++){
	    var html = '<div class="d-flex col check-div"><label><input type="checkbox" name="Checkbox1" onclick="setUrl(\'ingredient-tab\')" value="'+vegetables[i]+'"/><span class="px-1">'
	                + vegetables[i]+"</span></label></<div>";
	    $('#vegetableSection').append(html);
	  }

	  meats = new Array("?????????","??????","?????????","????????????","?????????","?????????","????????????");
	  for(i=0;i<meats.length;i++){
	    var html = '<div class="d-flex col check-div"><label><input type="checkbox" name="Checkbox1" onclick="setUrl(\'ingredient-tab\')" value="'+meats[i]+'"/><span class="px-1">'
	                + meats[i]+"</span></label></<div>";
	    $('#meatSection').append(html);
	  }

	  fishes = new Array("?????????","??????","??????","??????","???","??????","?????????","??????","?????????",
	          "??????","??????","??????","?????????","??????","??????","??????","?????????","?????????","??????","??????",
	          "??????","?????????","??????","?????????","??????","??????","??????","??????","?????????","??????");
	  for(i=0;i<fishes.length;i++){
	    var html = '<div class="d-flex col check-div"><label><input type="checkbox" name="Checkbox1" onclick="setUrl(\'ingredient-tab\')" value="'+fishes[i]+'"/><span class="px-1">'
	                + fishes[i]+"</span></label></<div>";
	    $('#fishSection').append(html);
	  }
	  
	  processeds = new Array("?????????","?????????", "?????????",
	  "??????", "???", "??????","??????", "?????????", "??????","??????","????????????","??????",
	  "?????????","????????????","?????????", "?????????","????????????","?????????","??????","??????","?????????");
	  for(i=0;i<processeds.length;i++){
	    var html = '<div class="d-flex col check-div"><label><input type="checkbox" name="Checkbox1" onclick="setUrl(\'ingredient-tab\')" value="'+processeds[i]+'"/><span class="px-1">'
	                + processeds[i]+"</span></label></<div>";
	    $('#processedSection').append(html);
	  }
	  
	  seasonings = new Array("??????","??????","?????????","?????????","???","?????????","??????",
	  "????????????","??????","????????????","??????","?????????","??????","?????????","??????",
	  "?????????","??????","????????????","??????????????????","?????????","????????????",
	  "????????????","????????????","??????","????????????","??????","?????????");
	  for(i=0;i<seasonings.length;i++){
	    var html = '<div class="d-flex col check-div"><label><input type="checkbox" name="Checkbox1" onclick="setUrl(\'ingredient-tab\')" value="'+seasonings[i]+'"/><span class="px-1">'
	                + seasonings[i]+"</span></label></<div>";
	    $('#seasoningSection').append(html);
	  }

	  milks = new Array("??????", "?????????", "??????", "????????????", "?????????",
	    "????????????", "??????????????????", "?????????", "????????????", "?????????",
	    "?????????","????????????","?????????","????????????","????????????", "?????????", "?????????","?????????");
	  for(i=0;i<milks.length;i++){
	    var html = '<div class="d-flex col check-div"><label><input type="checkbox" name="Checkbox1" onclick="setUrl(\'ingredient-tab\')" value="'+milks[i]+'"/><span class="px-1">'
	                + milks[i]+"</span></label></<div>";
	    $('#milkSection').append(html);
	  }
});

function getCheckboxValue()  {
	  // ????????? ??????????????? ????????????
	const query = 'input[name="Checkbox1"]:checked';
	const selectedEls = document.querySelectorAll(query);
  
  // ????????? ?????????????????? value ??????
	let data = new Array();
	let result = '';
	selectedEls.forEach((el) => {
	    result += el.value + ' ';
	    data.push(el.value);
	});
  // ?????? value???(????????????) ??????(????????? ??????)
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

