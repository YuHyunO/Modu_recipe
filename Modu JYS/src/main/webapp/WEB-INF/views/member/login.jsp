<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<!-- 공통 부분 END -->
<link href="/css/login.css" rel="stylesheet">
<title>모두의 식탁 - 로그인</title>
<style type="text/css">
/* 비밀번호를 잊으셨나요, 이미 회원이신가요? 문구, login.css만 넣으면 적용안됨*/
.forgotpwd, .goregister {
	font-size: 13px;
	color: #42332d;
	text-decoration: none;
	background-color: transparent;
	align-items: center;
}
</style>
</head>
<body>
	<div id="page" class="hfeed site">
		<!-- start page wrapper -->
		<%@ include file="/WEB-INF/views/common/menu.jsp"%>
		<div class="head-title py-4 m-0">
			<div class="container">
				<p class="page-title m-0 fs-2">login</p>
			</div>
			<!-- end container -->
		</div>
		<!-- end head-title -->

		<div class="main">
			<div class="py-3 space"></div>
			<div class="container d-flex justify-content-center">
				<div class="row">
					<div class="d-flex justify-content-center">
						<div class="acc-box equal">
							<div class="p-4 pt-0 p-title h3 mb-0 text-center">#로그인</div>
							
								<c:if test="${status ne 0}">
									<p class="mb-0 text-center acc-btn">
										<em>Welcome back to </em><span class="main-color"><em>모두의식탁</em></span>
									</p>
								</c:if>
								<c:if test="${status eq 0}">
									<p class="mb-0 text-center acc-btn">
										<em>Welcome back to </em><span class="main-color"><em>모두의식탁</em></span>
									</p>
									<p class="mb-0 text-center acc-btn">
										<span class="login_warn2">
											이메일 혹은 비밀번호를 잘못 입력하셨습니다.<br>
											정보를 다시 확인해주세요.
										</span>
									</p>
								</c:if>

							<form name="login_form" method="post" action="/member/login">
								<div class="form-group">
									<label for="id_email">이메일</label> <input name="email"
										type="email" class="form-control" id="id_email"
										placeholder="your@email.com" maxlength=40 required />
								</div>
								<div class="form-group">
									<label for="id_pwd">비밀번호</label> <input name="pwd"
										type="password" class="form-control" id="id_pwd"
										placeholder="비밀번호를 입력해주세요." maxlength=30 required />
								</div>
<!-- 								<div class="form-check">
									<input 
									type="checkbox" 
									class="form-check-input"
										id="rememberCheck" />
									<label 
									class="form-check-label"
									for="rememberCheck"
									>다음에도 이 정보를 기억합니다. </label>
								</div>
								-->
								<button id="login-button" type="submit"
									class="btn form-btn gold-btn mt-2">Login</button>
								<br />
								<br />
								<div class="float-right">
									<a href="#" class="forgotpwd">이메일 혹은 비밀번호를 잊으셨나요?</a>
								</div>
								<br />
								<div class="float-right">
									<a href="/member/register" class="goregister">아직 회원이 아니신가요?</a>
								</div>
							</form>
						</div>
						<!-- end acc-box -->
					</div>
					<!-- end col -->
				</div>
			</div>
			<!-- end container -->
		</div>
		<div class="py-3 space"></div>
		<!-- end main -->
		<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
	</div>
	<!-- end #page hfeed site -->

	<script type="text/javascript">
		$(function() {
			$("#login-button").click(function() { //로그인 버튼 클릭시
				//$("#login_form1").attr("action", "/member/login");
				//alert("버튼 작동");
				//$("#login_form").submit();
			}); // $("#loginokbutton1").click(function() 종료	
		}); //$(function() 종료
	</script>
</body>
</html>
