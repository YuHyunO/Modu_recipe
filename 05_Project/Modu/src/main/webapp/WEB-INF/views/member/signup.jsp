<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<!-- 공통 부분 END -->
<link href="/css/signup.css" rel="stylesheet">
<script src="/js/signup.js"></script>

<title>모두의 식탁 - 회원가입</title>
</head>
<body>
	<div id="page" class="hfeed site">
		<!-- start page wrapper -->
		<%@ include file="/WEB-INF/views/common/menu.jsp"%>
		<div class="head-title py-4 m-0">
			<div class="container">
				<p class="page-title m-0 fs-2">SIGN UP</p>
			</div>
			<!-- end container -->
		</div>
		<!-- end head-title -->

		<div class="main">
			<div class="py-3"></div>
			<div class="container">
				<div class="row">
					<div class="d-flex justify-content-center">
						<div class="acc-box equal">
							<div class="p-4 pt-0 p-title h3 mb-0 text-center">#회원가입</div>
							<p class="mb-0 text-center acc-btn">Join in our 모두의식탁</p>
							<p class="mt-3 acc-btn">*표시는 필수입력 항목입니다.</p>

							<form method="GET">
								<div class="form-group">
									<label for="emailaddress">이메일 주소 *</label><input type="email"
										class="form-control" id="id_email" placeholder="you@email.com"
										name="email" required /> <span class="email_input_re_1"
										style="display: none; color: green; text-align: left;">사용
										가능한 이메일 입니다.</span> <span class="email_input_re_2"
										style="display: none; color: red; text-align: left;">사용
										중인 이메일입니다.</span>
								</div>
								<div class="form-group">
									<label for="pw1">비밀번호 *</label><input type="password"
										class="form-control" id="pw1" placeholder="10자 이상 입력해주세요."
										name="pwd" required />
								</div>
								<div class="form-group">
									<label for="pw2">비밀번호 재입력*</label><input type="password"
										class="form-control" id="pw2" placeholder="10자 이상 입력해주세요."
										required />
								</div>
								<div class="form-group">
									<label for="completeName">이름*</label><input type="text"
										class="form-control" id="id_name" placeholder="ex. 홍길동"
										maxlength=10 name="name" required />
								</div>
								<div class="form-group">
									<label for="nickName">닉네임</label><input type="text"
										class="form-control" id="id_nickname" placeholder="ex. 보라돌이"
										maxlength=12 name="nickname" required />
								</div>
								<div class="form-group">
									<label for="phonenum">휴대폰번호</label><input type="number"
										class="form-control" id="id_phonenum" placeholder="숫자만 가능해요."
										name="phone" required />
								</div>
								<div class="form-group">
									<label for="completeName">프로필사진</label><input type="text"
										class="form-control" id="completeName"
										placeholder="drag&drop 예정" name="profile_img" />
								</div>

								<div class="form-check">
									<input type="checkbox" class="form-check-input" value="checked"
										id="form2Example3cg" required />
									<div class="form-check-label" for="form2Example3g">
										본 사이트의 <a href="" class="text-body" target='_blank'><u>이용약관</u></a>
										및 <a href="" class="text-body" target='_blank'><u>개인정보
												수집 및 이용약관</u></a>에 동의합니다. (필수)
									</div>
								</div>

								<div class="form-check">
									<input type="checkbox" class="form-check-input" value="checked"
										id="form2Example3cg" />
									<div class="form-check-label" for="form2Example3g">
										본 사이트에서 진행하는 <a href="" class="text-body" target='_blank'><u>마케팅
												및 이벤트 프로모션</u></a>에 고객님의 회원정보 활용에 동의합니다. (선택)
									</div>
								</div>

								<button id="signupbutton" type="submit"
									class="btn btn-primary form-btn">가입하기</button>
								<!-- <a href="../login.do" class="fw-bold text-body"><u>Go Login</u></a></p> -->
							</form>

						</div>
						<!-- end acc-box -->
					</div>
					<!-- end col -->

				</div>
			</div>
			<!-- end container -->
		</div>
		<!-- end main -->
		<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
	</div>
	<!-- end #page hfeed site -->
</body>
</html>