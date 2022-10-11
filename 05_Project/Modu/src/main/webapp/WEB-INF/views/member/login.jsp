<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<!-- 공통 부분 END -->
<link href="/css/login.css" rel="stylesheet">
<title>모두의 식탁 - 로그인</title>
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
							<p class="text-center">
								<em>Welcome back to 모두의식탁</em>
							</p>
							<form method="POST">
								<div class="form-group">
									<label for="InputEmailAcc1">이메일 *</label> <input type="email"
										class="form-control" id="InputEmailAcc1" name="email"
										placeholder="Enter email" />
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">비밀번호 *</label> <input
										type="password" class="form-control"
										id="exampleInputPassword1" name="pwd" placeholder="Password" />
								</div>
								<div class="form-check">
									<input type="checkbox" class="form-check-input"
										id="rememberCheck" /> <label class="form-check-label"
										for="rememberCheck">다음에도 이 정보를 기억합니다. </label>
								</div>
								<button id="loginbutton" type="submit"
									class="btn btn-primary form-btn mt-2"
									style="background: #937062">Login</button>
								<span class="float-right" style="display: none;">forget
									password?<a href="#">click here</a>
								</span>
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
</body>
</html>
