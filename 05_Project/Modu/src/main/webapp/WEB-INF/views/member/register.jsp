<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>모두의 식탁 - 회원가입</title>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<!-- 공통 부분 END -->
<link href="/css/register.css" rel="stylesheet">
<script src="/js/register.js"></script>
<style type="text/css">

</style>
<script src="https://code.jquery.com/jquery-latest.js"></script> <!-- CDN(신뢰할 수 있는 사이트) -->
<script type="text/javascript" src="../js/map.js"></script> <!-- 내 로컬 서버 -->
<script type="text/javascript" src="../js/stringBuffer.js"></script>

</head>
<body>
	<div id="page" class="hfeed site">
		<!-- start page wrapper -->
		<%@ include file="/WEB-INF/views/common/menu.jsp"%>
		<div class="head-title py-4 m-0">
			<div class="container">
				<p class="page-title m-0 fs-2">REGISTER</p>
			</div><!-- end container -->
		</div><!-- end head-title -->

		<div class="main">
			<div class="py-3"></div>
			<div class="container">
				<div class="row">
					<div class="d-flex justify-content-center">
						<div class="acc-box">
							<div class="p-4 pt-0 p-title h3 mb-0 text-center">#회원가입</div>
							<p class="mb-0 text-center acc-btn">
								<em>Join in our </em><span class="main-color"><em>모두의식탁</em></span>
							</p>
							<p class="mt-3 acc-btn text-right">*표시는 필수입력 항목입니다.</p>

							<form id="register_form" method="post">
								<div class="form-group">
									<label for="id_email">이메일*</label> <input name="email"
										type="email" class="form-control" id="id_email"
										placeholder="you@email.com" maxlength=40 required /> <span
										class="email_ajax_1">사용 가능한 이메일입니다.</span> <span
										class="email_ajax_2">이미 사용 중인 이메일입니다.</span> <span
										class="email_ajax_3">올바른 이메일을 입력해주세요.</span>
								</div>
								<div class="form-group">
									<label for="id_pw1">비밀번호*</label><input name="pwd1"
										type="password" class="form-control" id="id_pw1"
										placeholder="사용하실 비밀번호를 입력해주세요." maxlength=30 required />
								</div>

								<div class="form-group">
									<label for="id_pw2">비밀번호 확인*</label><input name="pwd"
										type="password" class="form-control" id="id_pw2"
										placeholder="사용하실 비밀번호를 입력해주세요." maxlength=30 required />
								</div>

								<div class="form-group">
									<label for="id_nickname">닉네임*</label><input name="nickname"
										type="text" class="form-control" id="id_nickname"
										placeholder="3자 이상 입력해주세요." maxlength=10 required /> <span
										class="nickname_ajax_1">사용 가능한 닉네임입니다.</span> <span
										class="nickname_ajax_2">이미 사용 중인 닉네임입니다.</span> <span
										class="nickname_ajax_3">닉네임은 3자 이상 입력해주세요.</span>
								</div>

								<div class="form-group">
									<label>성명</label> <input name="name" type="text"
										class="form-control" id="id_name" placeholder="실명을 입력해주세요."
										maxlength=5 />
								</div>
								<div class="form-group">
									<label for="id_phone">휴대폰번호</label><input name="phone"
										type="text" class="form-control" id="id_phone"
										placeholder="(- 제외) 숫자만 입력해주세요." maxlength=11 />
								</div>

								<label for="agreements">약관 동의</label>
								<div class="agreements" id="agreements">
									<div for="apiUsing">
										<input type="checkbox" id="apiUsing" name="apiUsing" value="1">
										<span><strong>본 사이트의 <a
												href="javascript:agreement1()"
												style="text-decoration: none; color: black;">
												<u>이용약관</u></a>
												및 <a href="javascript:agreement2()"
												style="text-decoration: none; color: black;">
												<u>개인정보 처리방침</u></a>에 동의합니다.</strong>
												(필수)</span>
									</div>
									<div for="marketing">
										<input type="checkbox" id="marketing" name="marketing"
											value="1"> <span><strong>본 사이트에서 진행하는
												<u>마케팅 및 이벤트 프로모션</u>을 위한 고객님의 회원정보 활용에 동의합니다.
										</strong> (선택)</span>
									</div>
								</div>
								<!-- end agreement -->
							</form>
							<br>
							<button name="registerokbutton" id="registerokbutton"
								type="button" class="btn btn-primary" value="가입하기">
								가입하기</button>
							<!-- type="button"로 버튼의 기본 submit 기능을 막음 -->
							<br />
							<div class="text-center">
								<a id="loginBtn" href="/member/login" class="alreadyregister">
									이미 가입하셨나요?</a><br />
							</div>
						</div>
						<!-- end acc-box -->
					</div>
					<!-- end col -->
				</div>
				<!-- end row -->
			</div>
			<!-- end container -->
		</div><!-- end main -->
		<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
	</div><!-- end id="page" -->

	<script type="text/javascript">
	 //회원가입 약관동의 팝업창 1,2 function 
	  function agreement1(){
	       var popupX = (document.body.offsetWidth / 2) - 50;
		    // 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
		    var popupY= (window.screen.height / 2) - 200;
		    // 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
	    	window.open('<%=request.getContextPath()%>/member/agreement1', '모두의식탁 이용약관', 'status=no, height=300, width=600, left='+ popupX + ', top='+ popupY);	
	 	}
	 	
	  function agreement2(){
	       var popupX = (document.body.offsetWidth / 2) - 50;
		    // 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
		    var popupY= (window.screen.height / 2) - 300;
		// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
	    window.open('<%=request.getContextPath()%>/member/agreement2', '모두의식탁 개인정보 처리방침', 'status=no, height=300, width=600, left='+ popupX + ', top='+ popupY);
	  }
    </script>
</body>
</html>