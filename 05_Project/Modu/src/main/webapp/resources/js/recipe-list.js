window.onload = function(){
	sessionStorage.clear();
}

$(document).ready(function() {
	  $(window).keydown(function(event){
	    if(event.keyCode == 116) {
	      event.preventDefault();
	      return false;
	    }
	  });
	});

$(function(e){
    //  랜덤추천 컬러 변경
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
    if($(e).val() == 20){//20은 랜덤추천
    	text = $(e).val();
    }
    // 클래스명 변경
    $('.tag-li').removeClass('active');
    $(e).addClass('active');
    setCategoryData(text);
}
var data;
var pageSize = 8;
var currentPage = 1;
var period;
var nameOption;

//페이지 사이즈
function setPageSize(){
	let sizeSelector = document.getElementById("size-selector");
	pageSize = sizeSelector.options[sizeSelector.selectedIndex].value;

	
	if(sessionStorage.getItem("category")!=null){
		console.log("1: towards setCategoryData()");
		setCategoryData(sessionStorage.getItem("category"));
	}else if(sessionStorage.getItem("keyword")!=null){
		console.log("1: towards setKeywordData(); setPage(e): "+sessionStorage.getItem("keyword"));
		setKeywordData(1);
	}else{
		console.log("1: towards setData()");
		setData();
	}		
}

// 현재 페이지
function setPage(e){
	currentPage = $(e).text();
	let value = $(e).attr("id");
	if(value =="pre" || value == "next"){
		currentPage = value;
	}

	if(sessionStorage.getItem("category")!=null){
		//console.log("2: towards setCategoryData()");
		setCategoryData(sessionStorage.getItem("category"));
	}else if(sessionStorage.getItem("keyword")!=null){
		//console.log("2: towards setKeywordData(); setPage(e): "+sessionStorage.getItem("keyword"));
		setKeywordData(1);
	}else{
		//console.log("2: towards setData()");
		setData();
	}
}
//기본검색 value:0 
function setData(){
	sessionStorage.clear();
	let periodSelector = document.getElementById("period-selector");	
	let period = periodSelector.options[periodSelector.selectedIndex].value;	
	
	data = {
			value: 0,
			pageSize: pageSize,
		    currentPage: currentPage,
		    period: period					    						
	}
	
	dataAgent(data);
}
//카테고리 검색 value:1 (category)
function setCategoryData(text){
	sessionStorage.removeItem("nameOption");
	sessionStorage.removeItem("keyword");
	
	let category = text;
	//console.log("category: "+category);
	//console.log(document.cookie);
	let periodSelector = document.getElementById("period-selector");
	let period = periodSelector.options[periodSelector.selectedIndex].value;
	let curPage = 1;
	
	if(category == sessionStorage.getItem("category")){
		curPage = currentPage;
	}
	
	data = {
			value: 1,
			pageSize: pageSize,
		    currentPage: curPage,
		    period: period,
			category: category		    				
	}
	sessionStorage.setItem("category", category);
	
	dataAgent(data);
}
//키워드 검색 value:2 (nameOption, keyword)
function setKeywordData(status){
	sessionStorage.removeItem("category");
	//console.log("setKeywordData(): "+sessionStorage.getItem("keyword"));
	//console.log("setKeywordData(): "+sessionStorage.getItem("nameOption"));
	
	let periodSelector = document.getElementById("period-selector");
	let nameSelector = document.getElementById("name-selector");
	let period = periodSelector.options[periodSelector.selectedIndex].value;
	let nameOption = nameSelector.options[nameSelector.selectedIndex].value;
	let keyword = document.getElementById("search").value;
	let curPage = 1;
	
	if(status ==1){
		if(sessionStorage.getItem("nameOption")){
			nameOption = sessionStorage.getItem("nameOption");
		}
		if(keyword == sessionStorage.getItem("keyword")){
			keyword = sessionStorage.getItem("keyword");
			curPage = currentPage;
		}		
	}
	
    $('.tag-li').removeClass('active');
    
	data = {
			value: 2,
		    pageSize: pageSize,
		    currentPage: curPage,	
			period: period,
			nameOption: nameOption,
			keyword: keyword
	}
	sessionStorage.setItem("nameOption", nameOption);
	sessionStorage.setItem("keyword", keyword);
	
	dataAgent(data);
}

function dataAgent(data){
	$.ajax({
		url: "./list.do",
		type: "GET",
		data: data,
	    dataType: "JSON",
	    traditional: true,
		success: function(response){
			let curPage = response.currentPage;
			let totalPage = response.totalPage;
			let recipeList = response.recipeList;
			let pageSize = response.pageSize;
			if(totalPage == 0){
				showNullData();
			}
			
			paginate(curPage, totalPage);
			updateData(recipeList, pageSize);
		},
		error: function(error){
			alert("접속이 원활하지 않습니다.\n브라우저 재접속 후 시도해주세요.");
		}
	});
}

function updateData(recipeList, pageSize){
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
		html += '<span><em>&nbsp;'+item.mNickname+'</em></span>';
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

function showNullData(){
	$("#recipe-list").empty();

	alert("※찾으시는 레시피가 없습니다!※");
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




