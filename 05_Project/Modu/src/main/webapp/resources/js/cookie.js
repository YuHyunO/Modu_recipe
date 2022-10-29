/*$(document).ready(function(e){
	let pageType = document.getElementById("page-type").value;
	console.log("##"+pageType);
	setUrl();	  		 
})

function setUrl(){
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
	  setData(url);
}

function setData(url){
	  let cookie = getCookie("recipe");
	  let data = { id: cookie.split(",") }	  
	  
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
	for(let item of response){
		html += '<li>';
		html += '<span class="post-category">';
		html += '<a id="recipe-type-'+i+'" href="#">'+item.sort+'</a>';
		html += '</span>';
		html += '<figure class="post-thumbnail">';
		html += '<a id="recipe-img-'+i+'" href="#"> <img class="rounded-3" src="/imgs/content/item.png" alt="d" />';
		html += '</a>';
		html += '</figure>';
		html += '<h2 class="post-title">';
		html += '<a id="recipe-title-'+i+'" href="#">'+item.title+'</a>';
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
}*/