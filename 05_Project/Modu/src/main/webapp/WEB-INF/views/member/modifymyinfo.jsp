<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/common/head.jsp"%><!-- 공통 부분 END -->
<title>모두의식탁 - 마이페이지</title>
<link href="/css/modifymyinfo.css" rel="stylesheet">
<script src="/js/modifymyinfo.js"></script>
<style type="text/css">
</style>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../js/map.js"></script>
<script type="text/javascript" src="../js/stringBuffer.js"></script>
</head>
<body>
	<div id="page" class="hfeed site">
		<%@ include file="/WEB-INF/views/common/menu.jsp"%>
		<div class="head-title py-4 m-0">
			<div class="container">
				<p class="page-title m-0 fs-2">MY INFORMATION</p>
			</div><!-- end container -->
		</div><!-- end head-title -->
		<div class="main">
			<div class="py-3"></div>
			<div class="container">
				<div class="row">
					<div class="d-flex justify-content-center">
						<div class="acc-box"><!-- equal 지워야 사이즈 가변적 -->
							<h2 style="text-align:center;">#내 정보 수정</h2>
<!-- 							<div class="p-4 pt-0 p-title h3 mb-0 text-center">#내 정보 수정</div> -->
							<p class="mb-0 text-center acc-btn">
								<em>You can modify your information.</em>
							</p>
							<p class="mt-3 acc-btn text-right">* 표시는 수정 불가합니다.<br/>
<!-- 				          <script type="text/javascript" charset="utf-8" src="/js/timeoutchk.js"></script>
				          	 <span id="timer"></span>
				          	 <a href="javascript:refreshTimer();">시간연장</a> -->
					        </p>

							<!-- 파일 업로드시 form태그 안에 enctype="multipart/form-data" 입력 필수.
							enctype(인코딩타입)을 multipart/form-data로 반드시 설정 -->
							<form id="modifymyinfo_form" 
								method="POST"
								enctype="multipart/form-data" 
								action="modifymyinfo">
								
								<div class="form-group">
									<label for="id_email">이메일*</label>
									<input name="email"
										type="email" class="form-control" id="id_email"
										placeholder="${member.email}" value="${member.email}" readonly
										required />
								</div>
								<div class="form-group">
									<label for="id_pwd1">비밀번호</label> <input name="pwd1"
										type="password" class="form-control" id="id_pwd1"
										value="${member.pwd}" maxlength=30 required />
								</div>
								<div class="form-group">
									<label for="id_pwd2">비밀번호 확인</label> <input name="pwd"
										type="password" class="form-control" id="id_pwd2"
										value="${member.pwd}" maxlength=30 required />
								</div>
								<div class="form-group">
									<label for="id_nickname">닉네임</label> <input name="nickname"
										type="text" class="form-control" id="id_nickname"
										value="${member.nickname}" maxlength=10 required /> <span
										class="nickname_ajax_1">사용 가능한 닉네임입니다.</span> <span
										class="nickname_ajax_2">이미 사용 중인 닉네임입니다.</span> <span
										class="nickname_ajax_3">닉네임은 3자 이상 입력해주세요.</span> <span
										class="nickname_ajax_4">변경사항이 없습니다.</span>
								</div>
								<c:if test="${ member.name == null }">
									<div class="form-group">
										<label for="id_name">성명 (선택)</label> <input name="name"
											type="text" class="form-control" id="id_name"
											placeholder="실명을 입력해주세요." value="${member.name}" maxlength=5 />
									</div>
								</c:if>
								<c:if test="${ member.name != null }">
									<div class="form-group">
										<label for="id_name">성명*</label> <input name="name"
											type="text" class="form-control" id="id_name"
											placeholder="${member.name}" value="${member.name}"
											maxlength=5 readonly required />
									</div>
								</c:if>
								<c:if test="${ member.phone == null }">
									<div class="form-group">
										<label for="id_phone">휴대폰번호 (선택)</label> <input name="phone"
											type="text" class="form-control" id="id_phone"
											placeholder="숫자만 입력해주세요." value="${member.phone}"
											maxlength=11 />
									</div>
								</c:if>
								<c:if test="${ member.phone != null }">
									<div class="form-group">
										<label for="id_phone">휴대폰번호 (선택)</label> <input name="phone"
											type="text" class="form-control" id="id_phone"
											value="${member.phone}" maxlength=11 />
									</div>
								</c:if>
								<div class="form-group">
									<label for="id_marketinglabel">마케팅 동의여부</label><br />
									<c:if test="${ member.marketing == 0 }">
										<div style="text-align: center;">
											<input id="id_marketing_yes" type="radio" name="marketing"
												value="1"> &nbsp;동의&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
												id="id_marketing_no" type="radio" name="marketing" value="0"
												checked> &nbsp;미동의&nbsp;&nbsp;&nbsp;
										</div>
									</c:if>
									<c:if test="${ member.marketing != 0 }">
										<div style="text-align: center;">
											<input id="id_marketing_yes" type="radio" name="marketing"
												value="1" checked>
											&nbsp;동의&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
												id="id_marketing_no" type="radio" name="marketing" value="0">
											&nbsp;미동의&nbsp;&nbsp;&nbsp;
										</div>
									</c:if>

								</div>
								<div class="form-group">
									<label for="profileImage">나의 프로필 사진</label>
									<!-- 첨부파일이 없을 때 -->
									<c:if
										test="${ member.profileImg=='default_profile_img.png' || member.profileImg==null}">
										<div id="previewImg_default">현재 설정된 프로필 사진이 없습니다.</div>
										<br />
									</c:if>
									<!-- 첨부파일이 있을 때 미리보기 사진 보임-->
									<c:if
										test="${ member.profileImg!='default_profile_img.png' && member.profileImg!=null}">
										<div id="previewImg">
											<img
												src="<spring:url value = '/pics/profile/${ member.profileImg }'/>"
												id="img_previewImg"
												alt="현재프로필사진" /><br/>
											<c:out value="${ member.profileImgOrg } (${ member.profileImgSize } byte)" />
											<br/>
											<input id="profileExist" 
													type="hidden" 
													value="${ member.profileImg }">
											<input type="button" 
													id="removemyprofileimgBtn" 
													value="사진 지우기">
										</div><!--  href="/member/removemyprofileimg?profileImg=${ member.profileImg }" -->
										<br />
									</c:if>
								</div>
								<!--file form-group 종료 -->
								<div class="form-group">
									<label for="profileImage">프로필 사진 변경</label>
									<div>
										<input type="file" name="file" id="file"
											class="upload-box upload-plus" accept="image/*"
											aria-describedby="sizeHelp"> 
										<small id="sizeHelp"
											class="form-text text-muted" style="font-size: 13px;">
											파일 크기는 10MB를 넘을 수 없습니다.</small>
										<div id="preview" style="text-align: center;"></div>
										<div class="file-edit-icon" style="text-align: center;">
										<!-- <a href="javascript:;" class="preview-edit">사진 변경</a> -->
											<a href="javascript:;" class="preview-delete">삭제</a>
										</div>
									</div>
									<!-- <input type="button" onclick="submitFile()" value="파일전송"> -->
								</div>
								<!--file form-group 종료 -->
								<input 
									type="button" 
									id="modifyOkBtn"
									class="btn btn-primary form-btn mt-2" 
									value="저장하기"><!--  onclick="modifypost(this)"-->
								<!-- button태그는 type="button"로 버튼의 기본 submit 기능을 막을 수 있음 -->
								<br />
								<div style="text-align: center;">
									<p class="cat-list" style="font-size: 15px">
										가입일시: ${ member.signupDate }<br/>
										마지막 수정일시: ${ member.updateDate }
									</p>
									<input id="removeOkBtn" 
										type="button" 
										value="회원탈퇴"
										class="btn btn-outline-secondary btn-default"
										onclick="removeMember(this);">
									<%-- 참고!! <a href='del.do?seq=${boardcontent.seq}'>삭제</a> --%>
								</div>
							</form>
						</div><!-- end acc-box -->
						
					</div><!-- end col -->
				</div><!-- end row -->
			</div><!-- end container -->
		</div><!-- end main -->
		<!-- 탈퇴하기 모달창 로직 위치(삭제함) -->
	<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
	</div><!-- end id="page" -->
						
	<script type="text/javascript">
	</script>
</body>
</html>