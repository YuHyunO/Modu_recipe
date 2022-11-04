$(function(){
	console.log("test");
	let html = "";
	$.ajax({
		 url: "recent-tag",
		 type: "GET",
		 dataType: "JSON",
		 traditional: true,
		 success: function(response){
			 console.log(response);
			 for(data of response){
				 html += '<a href="#" class="me-1 mb-1">#'+ data.tag +'</a>';
			 }
			 $('.tag-row').append(html);
		 },
		 error: function(error){
			 console.log("X");
		 }
	  });	
});