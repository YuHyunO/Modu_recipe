<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<!-- 공통 부분 END -->
<link href="/css/board-write.css" rel="stylesheet">

<title>모두의 식탁 - 자유게시판 등록</title>
<script type="text/javascript"> 
	   function check(e){
		   var titleF = document.getElementById('title').value;
		   var contentF = document.getElementById('content').value;;
		   if(titleF == "" || contentF == "")
		   {alert("내용을쓰세요");
			   return false;
		   }else{
			   document.input.submit();
		   };	  
	   };
	   
	   function cancel(){
		   if (confirm("정말 취소하시겠습니까??") == true){
				 location.href='list';
			 }else{
			     return false;
			 }
	   }
</script>
</head>
<body>

	<div id="page" class="hfeed site">
		<!-- start page wrapper -->
		<%@ include file="/WEB-INF/views/common/menu.jsp"%>
		<div class="head-title py-4 m-0">
			<div class="container">
				<p class="page-title m-0 fs-2">free board write</p>
			</div>
			<!-- end container -->
		</div>
		<!-- end head-title -->
		<div class="main">
			<div
				class="main-container row d-flex justify-content-center m-0 py-4">
				<div class="col-md-8 row justify-content-center">
					<form method="POST" action="write.do" name="input"
						enctype="multipart/form-data"
						class="row justify-content-center p-5 main-size">
						<div class="p-4 pt-0 p-title h3 mb-0 text-center">#게시판 등록</div>
						<div class="form-group align-items-center mb-3 p-0"
							style="display: none !important;">
							<label for="post_type" class="m-0 p-2">카테고리</label> <select
								id="post_type" name="post_type" class="form-select">
								<option>공지사항</option>
								<option>FAQ</option>
							</select>
						</div>
						<div class="form-group align-items-center mb-3 p-0">
							<label for="m_nickname" class="m-0 p-2">작성자</label> <input
								type="text" size="50" name="m_nickname" id="m_nickname"
								class="form-control" maxlength="30" placeholder="${sessionScope.nickname}" readonly />
						</div>
						<div class="form-group align-items-center mb-3 p-0">
							<label for="title" class="m-0 p-2">제목</label> <input type="text"
								size="50" name="title" id="title" class="form-control rounded-1"
								maxlength="30" placeholder="제목을 작성해주세요" />
						</div>

						<div class="form-group align-items-center mb-3 p-0">
							<label for="content" class="m-0 p-2">내용</label>
							<textarea cols="50" rows="10" name="content" id="content"
								class="form-control" placeholder="내용을 작성해주세요"></textarea>
						</div>
						<div class="form-group align-items-center mb-3 p-0 mb-0 file">
							<input class="form-control" type="file" id="formFileMultiple"
								name='file' multiple="multiple">
						</div>
						<div class="border-top mt-3 pb-2"></div>
						<div class="d-flex justify-content-center my-3 p-2 pe-0">
							<button type="button" class="btn form-btn border btn gold-btn" name="checkButton" onclick="check()" >등록</button>
							<button type="button"
								class="btn form-btn border ms-4 btn btn-secondary" onclick="cancel()">취소</button>
						</div>
					</form>
				</div>
			</div>
			<!-- end main-size -->
		</div>
		<!-- end main-container -->
	</div>
	<!-- end main -->
	<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
	</div>
	<!-- end #page hfeed site -->
</body>
</html>