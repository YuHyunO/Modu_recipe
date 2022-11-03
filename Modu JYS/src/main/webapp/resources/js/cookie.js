$(document).ready(function(){
	  let cookie = getCookie("recipe");
	  let id = { id: cookie.split(",") }
	  let url = "";
	  	  
	  $.ajax({
		 url: "recent-recipe",
		 type: "GET",
		 data: id,
		 dataType: "JSON",
		 traditional: true,
		 success: function(response){
			 alert("@@@");
		 },
		 error: function(error){
			 console.log("X");
		 }
	  });
})

function getCookie(name) {
	  let matches = document.cookie.match(new RegExp(
	    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
	  ));
	  return matches ? decodeURIComponent(matches[1]) : undefined;
}