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
var data;
var pageSize = 10;
var curruntPage = 1;
var period;
var nameOption;

function setPageSize(){
	let sizeSelector = document.getElementById("size-selector");
	pageSize = sizeSelector.options[sizeSelector.selectedIndex].value;

	
	if(sessionStorage.getItem("keyword")!=null){
		console.log("1: towards setKeywordData(); setPage(e): "+sessionStorage.getItem("keyword"));
		setKeywordData(1);
	}else{
		console.log("1: towards setData()");
		setData();
	}		
}
function setPage(e){
	currentPage = $(e).text();
	let value = $(e).attr("id");
	if(value =="pre" || value == "next"){
		currentPage = value;
	}

	if(sessionStorage.getItem("keyword")!=null){
		//console.log("2: towards setKeywordData(); setPage(e): "+sessionStorage.getItem("keyword"));
		setKeywordData(1);
	}else{
		//console.log("2: towards setData()");
		setData();
	}
}
function setData(){
	sessionStorage.clear();
	let periodSelector = document.getElementById("period-selector");	
	let period = periodSelector.options[periodSelector.selectedIndex].value;	
	
	data = {
			value: 0,
			pgSize: pageSize,
		    curPage: currentPage,
		    period: period					    						
	}
	
	dataAgent(data);
}

function setKeywordData(status){
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
	data = {
			value: 2,
		    pgSize: pageSize,
		    curPage: curPage,	
			period: period,
			nameOption: nameOption,
			keyword: keyword
	}
	console.log("1: "+pageSize +"  2: "+curPage +"  3: "+period +"  4: "+nameOption +"  5: "+keyword );
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
			console.log("#response success ");
			let curPage = response.curPage;
			let totalPage = response.totalPage;
			let boardList = response.boardList;
			let pageSize = response.pgSize;
			console.log("#01 cP : " + curPage +"  tP : " +  totalPage );
			if(totalPage == 0){
				showNullData();
			}
			
			paginate(curPage, totalPage);
			updateData(boardList, pageSize);
		},
		error: function(error){
			alert("접속이 원활하지 않습니다.\n브라우저 재접속 후 시도해주세요.");
		}
	});
}

function updateData(boardList, pageSize){
	let html = "";
	for(let item of boardList){
		html += '<tr class="border">';
		html += '<td class="id">'+item.id+'</td>';
		html += '<td class="title-td text-start text"><a href="detail?id='+item.id+'"> <span';
		html += 'class="title text">'+item.title+'</span> <span class="reply">['+item.reply+']</span>';
		html += '</a>';
		html += '</th>';
		html += '<td class="nickname text"><img class="profile-img" src="/pics/profile/'+item.profileImg+'" alt="img">'+item.mnickname+'</td>';
		html += '<td class="post-date">'+item.postDate+'</td>';
		html += '<td class="hits">'+item.hits+'</td>';
		html += '</tr>';
	
		$("#board-test").html(html);
	}
}
function showNullData(){
	$("#recipe-list").empty();
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