<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/common/head.jsp"%><!-- 공통 부분 END -->
<title>모두의식탁 - 마이페이지</title>
<link href="/css/register.css" rel="stylesheet">
<script src="/js/register.js"></script>
<style type="text/css">
	/*가운데 정렬*/
/* 	#modifymyinfocol {
		float: none;
		margin: 0 auto;
	}
	 */
	#modifyOkBtn {
		background: #937062;
		border-style: none;
		margin: auto;
		display: block;
		width:100%;
	}
	
	#removeOkBtn {
		background: #d6c8c2;
		border-style: none;
		margin: auto;
		display: block;
	}
	
	#profileImg {
		/*  background: rgba(214, 200, 194, 0.25); */
		border-style: none;
		margin: auto;
		display: block;
	}
	
	/*내정보 수정시 닉네임 유효성 검사(중복체크) ajax 문구*/
	.nickname_ajax_1,
	.nickname_ajax_4 {
		display: none;
		font-size: 12px;
		color: rgb(147, 112, 98);
		text-align: right;
	}
	
	.nickname_ajax_2,
	.nickname_ajax_3 {
		display: none;
		font-size: 12px;
		color: green;
		text-align: right;
	}
	
	#id_marketing_yes, 
	#id_marketing_no {
		text-align: center;
	}
	
  }
	</style>
	<script src="https://code.jquery.com/jquery-latest.js"></script><!-- CDN(신뢰할 수 있는 사이트) -->
	<script type="text/javascript" src="../js/map.js"></script><!-- 내 로컬 서버 -->
	<script type="text/javascript" src="../js/stringBuffer.js"></script>
</head>
<body>
	<div id="page" class="hfeed site">
		<%@ include file="/WEB-INF/views/common/menu.jsp"%>
		<div class="head-title py-4 m-0">
			<div class="container">
				<p class="page-title m-0 fs-2">MY INFOMATION</p>
			</div><!-- end container -->
		</div><!-- end head-title -->

		<div id="main">
			<div class="py-3"></div>
			<div class="container">
				<div class="row">
					<div class="d-flex justify-content-center" id="modifymyinfocol">
						<div class="acc-box equal">
							<div class="p-4 pt-0 p-title h3 mb-0 text-center">
							#내 정보 수정</div>
							<p class="mb-0 text-center acc-btn">
							<em>You can modify your information.</em></p>
							<p class="mt-3 acc-btn text-right">
							* 표시는 수정 불가합니다.</p>

						<!-- 파일 업로드시 form태그 안에 enctype="multipart/form-data" 입력해야함.
						enctype(인코딩타입)을 multipart/form-data로 반드시 설정 -->
							<form id="modifymyinfo_form" method="post" 
								enctype="multipart/form-data" ><!-- enctype : 파일 첨부할때 필요한 속성/ action="/member/modifymyinfo" -->
								
								<div class="form-group">
									<label for="id_email">이메일 *</label>
									<input 
										name="email"
										type="email" 
										class="form-control" 
										id="id_email"
										placeholder="${member.email}" 
										value="${member.email}" 
										readonly
										required />
								</div>
								<div class="form-group">
									<label for="id_pwd1">비밀번호</label>
									<input 
										name="pwd1"
										type="password" 
										class="form-control" 
										id="id_pwd1"
										value="${member.pwd}" 
										maxlength=30 
										required/>
								</div>
								<div class="form-group">
									<label for="id_pwd2">비밀번호 확인</label>
									<input name="pwd"
										type="password" 
										class="form-control" 
										id="id_pwd2"
										value="${member.pwd}" 
										maxlength=30 
										required />
								</div>
								<div class="form-group">
									<label for="id_nickname">닉네임</label>
									<input name="nickname"
										type="text" 
										class="form-control" 
										id="id_nickname"
										value="${member.nickname}" 
										maxlength=10 
										required />
									<span class="nickname_ajax_1">사용 가능한 닉네임입니다.</span> <span
										class="nickname_ajax_2">이미 사용 중인 닉네임입니다.</span> <span
										class="nickname_ajax_3">닉네임은 3자 이상 입력해주세요.</span> <span
										class="nickname_ajax_4">변경사항이 없습니다.</span>
								</div>
								<c:if test="${ member.name == null }">
									<div class="form-group">
										<label for="id_name">성명 (선택)</label>
										<input name="name"
											type="text" 
											class="form-control" 
											id="id_name"
											placeholder="ex. 홍길동" 
											value="${member.name}" 
											maxlength=5 />
									</div>
								</c:if>
								<c:if test="${ member.name != null }">
									<div class="form-group">
										<label for="id_name">성명 *</label>
										<input name="name" 
										    type="text"
											class="form-control" 
											id="id_name"
											placeholder="${member.name}" 
											value="${member.name}"
											maxlength=5 
											readonly 
											required />
									</div>
								</c:if>
								<c:if test="${ member.phone == null }">
									<div class="form-group">
										<label for="id_phone">휴대폰번호 (선택)</label>
										<input name="phone"
											type="text" 
											class="form-control" 
											id="id_phone"
											placeholder="숫자만 입력해주세요." 
											value="${member.phone}"
											maxlength=11 />
									</div>
								</c:if>
								<c:if test="${ member.phone != null }">
									<div class="form-group">
										<label for="id_phone">휴대폰번호</label>
										<input name="phone"
											type="text" 
											class="form-control" 
											id="id_phone"
											value="${member.phone}"
											maxlength=11 />
									</div>
								</c:if>

								<div class="form-group">
									<label for="id_marketinglabel">마케팅 동의여부</label><br />
									<c:if test="${ member.marketing == 0 }">
										<input 
											id="id_marketing_yes" 
											type="radio" 
											name="marketing"
											value="1">
											&nbsp;동의&nbsp;&nbsp;&nbsp;
				                    <input 
				                    		id="id_marketing_no" 
				                    		type="radio"
											name="marketing" 
											value="0" 
											checked>&nbsp;미동의&nbsp;&nbsp;&nbsp;
                   					</c:if>
									<c:if test="${ member.marketing != 0 }">
										<input id="id_marketing_yes" type="radio" name="marketing"
											value="1" checked>&nbsp;동의&nbsp;&nbsp;&nbsp;
	                    				<input id="id_marketing_no" type="radio"
											name="marketing" value="0">&nbsp;미동의&nbsp;&nbsp;&nbsp;
                   					</c:if>
								</div>
								<div class="form-group">
									<label for="profileImage">프로필 사진</label>
									<c:if
										test="${ member.profileImg=='default_profile_img.png' || member.profileImg==null}">
										<!-- 첨부파일이 없을 때 -->
										<div align="left" id="profileImg">현재 프로필 사진 : (기본 이미지)</div>
									</c:if>
									<c:if
										test="${ member.profileImg!='default_profile_img.png' && member.profileImg!=null}">
										<!-- 첨부파일이 있을 때, 근데 질문: 여기서 var="board"의 의미는? 없어도 되는지?-->
										<div align="left" id="profileImg">현재 프로필 사진 : ${ member.profileImg}</div>
									</c:if>
									<%-- 		        <c:if test="${ }"> --%>
									<input name="profileImg" value="${member.profileImg}"
										type="file" class="form-control-file"
										aria-describedby="sizeHelp" /><small
										id="sizeHelp" class="form-text text-muted"
										style="font-size: 13px">파일 크기는
										10MB를 넘을 수 없습니다.</small>
									<%-- </c:if> --%>
								</div>
								<br />
								<!-- <input type="button" onclick="submitFile()" value="파일전송"> -->
								<div>
									
								</div>
								<button 
									id="modifyOkBtn" 
									class="btn btn-primary form-btn mt-2"							
									type="button"
									onclick="modifypost(this)"><!--  class="btn btn-primary form-btn mt-2" -->
								수정하기
								</button>
								<br /><!-- type="button"로 버튼의 기본 submit 기능을 막을 수 있음..근데 제출이 안됨.. -->
								<div style="text-align: center;">
									<p class="cat-list"
									   style="font-size: 15px">
										가입일시: ${ member.signupDate }<br/>
										마지막 수정일시: ${ member.updateDate }
									</p>
		<!-- 				
							<input id="removeOkBtn" type="submit" value="회원탈퇴"
									style="font-size:15px; text-align:center;"></input> -->
									<button id="removeOkBtn" type="button"
										class="btn btn-outline-secondary btn-default">회원탈퇴</button>
									<br />
								</div>
							</form>
						</div><!-- end acc-box -->
					</div><!-- end col -->
				</div><!-- end row -->
			</div><!-- end container -->
		</div><!-- end main -->

		<div class="modal2 hidden"><!--모달창 시작-->
			<div class="bg"></div><!--뒷배경 창화면 어둡게 -->
			<div class="modalBox2">
				<div class="container">
					<div class="row">
						<div class="col-12 col-md-12">
							<div class="acc-box1 equal" style="border-radius: 1rem;">
								<button class="close-area2">✖</button><!--닫기 버튼-->
								<h2>탈퇴하기</h2>
								<p>
									<em>모두의식탁 탈퇴하기</em>
								</p>
								<form name="login_form" method="post" action="/member/login">
									<div class="form-group">
										<label for="login_email">이메일</label> <input name="email"
											type="email" class="form-control" id="id_removeEmail"
											placeholder="이메일을 입력해주세요." />
										<!-- id="delete_email" -->
									</div>
									<div class="form-group">
										<label for="login_pwd">비밀번호</label> <input name="pwd"
											type="password" class="form-control" id="id_removePwd"
											placeholder="비밀번호를 입력해주세요." />
										<!-- id="delete_pwd" -->
									</div>
									<button id="removeokbutton"
										class="btn btn-outline-secondary btn-lg">탈퇴하기</button>
									<br />
								</form>
							</div><!-- end acc-box1 -->
						</div><!-- end col -->
					</div><!-- end row -->
				</div><!-- end container -->
			</div><!--end modalBox2 -->
		</div><!--end modal2 hidden, 모달창 종료-->

	<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
	  <script type="text/javascript">
       const open = () => {
      document.querySelector(".modal2").classList.remove("hidden");
    }
    const close = () => {
      document.querySelector(".modal2").classList.add("hidden");
    }
    document.querySelector("#loginBtn").addEventListener("click", open);
    document.querySelector(".close-area").addEventListener("click", close);   
    //document.querySelector(".bg").addEventListener("click", close); //배경 누르면 모달창 꺼지도록 함

	    $(function(){
		    $("#loginokbutton").click(function(){ //로그인 버튼 클릭시
			        //$("#login_form").attr("action", "/member/login");
			        $("#login_form").submit();
		    }); // $("#loginokbutton").click(function() 종료	
			
		}); //$(function() 종료    

</script>

		<script type="text/javascript">
			$(function() { //내정보수정 페이지 들어오면 항상 작동되는 js
				$('#id_pwd2').blur(function() { //비밀번호 일치 여부 확인 alert
					if ($('#id_pwd1').val().length === 0) {
					} else if ($('#id_pwd1').val() != $('#id_pwd2').val()) {
						if ($('#id_pwd2').val() != '') {
							alert("비밀번호가 일치하지 않습니다.");
							$('#id_pwd1').val('');
							$('#id_pwd2').val('');
							$('#id_pwd1').focus();
						}
					}
				});

				//내정보수정시 닉네임  중복체크 ajax
				$('#id_nickname')
						.on("propertychange change tab keyup paste input",
								function() {
									var inputNickname = $('#id_nickname').val(); // #id_nickname에 입력되는 값
									var data = {
										nickname : inputNickname
									} // '컨트롤에 넘길 데이터 이름' : '데이터(#id_email에 입력되는 값)'
									$.ajax({
											type : "post",
											url : "/member/modify-myinfo/nicknamevalidcheck",
											data : data,
											success : function(result) {
												if (result == 'noshow') { // 닉네임 3자 미만시 
													console.log(result);
													$('.nickname_ajax_1')
															.css("display",
																	"none");
													$('.nickname_ajax_2')
															.css("display",
																	"none");
													$('.nickname_ajax_3')
															.css("display",
																	"inline-block");
													$('.nickname_ajax_4')
															.css("display",
																	"none");
												} else if (result == 'success') { //닉네임 사용 가능, 중복X
													console.log(result);
													$('.nickname_ajax_1')
															.css("display",
																	"inline-block");
													$('.nickname_ajax_2')
															.css("display",
																	"none");
													$('.nickname_ajax_3')
															.css("display",
																	"none");
													$('.nickname_ajax_4')
															.css("display",
																	"none");
												} else if (inputNickname === "${member.nickname}") { //아래 else if (result == 'fail')와 순서 바뀌면 안됨, 기존 내 닉네임과 동일할 때
													console
															.log("원래 나의 닉네임(변경사항 없음)");
													$('.nickname_ajax_1')
															.css("display",
																	"none");
													$('.nickname_ajax_2')
															.css("display",
																	"none");
													$('.nickname_ajax_3')
															.css("display",
																	"none");
													$('.nickname_ajax_4')
															.css("display",
																	"inline-block");
												} else if (result == 'fail') { //닉네임 사용 불가, 중복 닉네임
													console.log(result);
													$('.nickname_ajax_1')
															.css("display",
																	"none");
													$('.nickname_ajax_2')
															.css("display",
																	"inline-block");
													$('.nickname_ajax_3')
															.css("display",
																	"none");
													$('.nickname_ajax_4')
															.css("display",
																	"none");
													}
												} // success 종료
											}); // ajax 종료
								});// $('id_nickname').on function 종료
			}); //$(function() 종료

			function modifypost(e) {
				//e.preventDefault();
				
				let nickCheckok1 = $('.nickname_ajax_1').css('display'); //inline-block(사용가능), none(사용불가)
				let nickCheckok2 = $('.nickname_ajax_2').css('display');
				let nickCheckok3 = $('.nickname_ajax_3').css('display');
				let nickCheckok4 = $('.nickname_ajax_4').css('display');
				console.log($('.nickname_ajax_1').css('display')); //닉네임 중복여부 알려줌: inline-block(사용가능), none(사용불가)
				var value = $("input[type=radio][name=marketing]:checked").val();
				console.log("마케팅 동의여부: " + value); //마케팅 동의여부 체크된 값 value
				
				//$("#modifymyinfo_form").val();
				//console.log($("#modifymyinfo_form").val());
				let data = $("#modifymyinfo_form").serializeArray();
				console.log(data);
				
				

				if (nickCheckok1 === 'inline-block'|| nickCheckok4 === 'inline-block'
						||nickCheckok1==='none'&&nickCheckok2==='none'&&nickCheckok3==='none'&&nickCheckok3==='none') { // 닉네임 사용 가능할 때
					//여기서부턴 하나라도 공란일시 alert 됨        
					if ($("#id_pwd1").val() == ""||$("#id_pwd2").val() == "") {
						alert("비밀번호를 입력해주세요.");
						$("#id_pwd1").focus();
					} else if ($("#id_nickname").val() == "") {
						alert("닉네임을 입력해주세요.");
						$("#id_nickname").focus();
					} else { //정상일 때 submit
						console.log('내정보 수정조건 통과!');
						//$("#modifymyinfo_form").attr("action", "/member/modifymyinfo"); //modifymyinfo_form은 form 태그의 name
						//$("#modifymyinfo_form").submit(); //document.modifymyinfo_form.submit();
						//alert("회원정보가 정상적으로 수정되었습니다.");
						
						$.ajax({
							type : "post",
							url : "/member/modifymyinfo",
							data : data,
							success : function(result) {
								alert("회원정보가 정상적으로 수정되었습니다.");
							} // success 종료
						}); // ajax 종료
					}
				} else { //닉네임 중복시 수정불가
					console.log('닉네임 중복으로 수정불가-아무 동작 없음');
				}
			}

			$(document).ready(function() { //현재 페이지 들어오면 작동되는 fuction
/* 				$("#modifyOkBtn").on("click", function() { //수정하기 버튼 클릭시 
				}); */

				$("#removemyOkBtn").on("click", function() { //탈퇴버튼 클릭시
					var result = confirm("정말 탈퇴하시겠습니까? \n탈퇴후 되돌릴 수 없습니다.");
					if (result == true) {
						$("#removemyOkBtn").submit();
						alert("정상적으로 탈퇴 되었습니다. 감사합니다.");
						return "index";
					} else {
						e.preventDefault();
						alert("탈퇴를 취소하셨습니다.");
						return false;
					}
				});
			}); //$(document).ready(function(){} 종료
		</script>
</body>
</html>