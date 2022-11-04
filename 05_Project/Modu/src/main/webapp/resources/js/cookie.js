$(document).ready(function(e){
	let pageType = document.getElementById("page-type").value;
	//console.log("##"+pageType);
	setSecondaryUrl();	  		 
})

function setSecondaryUrl(){
	  let pageType = document.getElementById("page-type").value;
	  let url;
	  
	  switch(pageType){
	  case "index":  
	  				url = "/recent-recipe";
	  		break;
	  case "recipe-detail":
	  				url = "/recipe/recent-recipe";
	  		break;
	  case "mypage":
	  				url = "/mypage/recent-recipe";
	  }
	  setSecondaryData(url);
}

function setSecondaryData(url){
	  let cookie = getCookie("recipe");
	  //console.log("##setdata cookie: ",cookie); //rid가 출력
	  //let data = { id: cookie }
	  let data = { id: cookie.split(",") }	  
	  //console.log("##setdata data: ",data);
	  cookieAgent(url, data);
}

function cookieAgent(url, data){
	  $.ajax({
			 url: "recent-recipe",
			 type: "GET",
			 data: data,
			 dataType: "JSON",
			 traditional: true,
			 success: function(response){
				 displayData(response);
			 },
			 error: function(error){
				 console.log("X");
			 }
		  });	
}

function displayData(response){
	let i = 0;
	let html = "";
	//console.log(response);
	
	for(let item of response){
		html += '<li>';
		html += '<span class="post-category">';
		html += '<a href="../recipe/detail?no='+item.id+'">'+item.sort+'</a>';
		html += '</span>';
		html += '<figure class="post-thumbnail">';
			html += '<a href="../recipe/detail?no='+item.id+'">';
			html += '<img src="/pics/recipe/'+item.id+'/'+item.foodPhoto+'" alt="recipe_mainImage">';
/*(조건문 사용시)
 * 			html += '<c:choose>';
			html += '<c:when test="${'+item.foodPhoto+' eq "recipe_basic_img.png"}">';
			html += '<img src="/pics/recipe/recipe_basic_img.png" alt="recipe_basicImage">';
			html += '</c:when>';
			html += '<c:otherwise>';
			html += '<img src="/pics/recipe/'+item.id+'/'+item.foodPhoto+'" alt="recipe_mainImage">';
			html += '</c:otherwise>';
		html += '</c:choose>';
*/
			html += '</a>';
		html += '</figure>';
		html += '<h2 class="post-title">';
		html += '<a href="../recipe/detail?no='+item.id+'">'+item.title+'</a>';
		html += '</h2>';
		html += '</li>';
		i++;
	}
	$("#recent-recipe-area").html(html);
}

function getCookie(name) {
	  let matches = document.cookie.match(new RegExp(
	    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
	  ));
	  return matches ? decodeURIComponent(matches[1]) : undefined;
}