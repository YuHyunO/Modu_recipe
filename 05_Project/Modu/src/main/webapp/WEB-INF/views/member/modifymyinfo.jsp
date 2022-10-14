<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 

<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/common/head.jsp"%><!-- 공통 부분 END -->
<title>모두의식탁 - 마이페이지</title>
<link href="/css/register.css" rel="stylesheet">
<style type="text/css">	 
	.main {
    background: #EEEEEE;
}

/* 	element.style {
    height: auto;
} */

	#modifyOkBtn {
		color: #fff;
		background: #937062;
		border-style: none;
		margin: auto;
		display: block;
		width:80%;
	}
          
	#removeOkBtn {
		background: rgba(214, 200, 194, 0.25);
		color: gray;
		border-style: none;
		margin: auto;
		display: block;
		width: 80%;
	}
	
	#previewImg {
		background: rgba(214, 200, 194, 0.25);
		border-style: none;
		margin: auto;
		display: block;
	}
	
	.form-control-file {
       text-align:center;  
       justify-content: center;
       align-items: center;  
	}
	/*내정보 수정시 닉네임 유효성 검사(중복체크) ajax 문구*/
	.nickname_ajax_1,
	.nickname_ajax_4 {
		display: none;
		font-size: 12px;
		color: rgb(147, 112, 98);
       text-align:center;  
       justify-content: center;
       align-items: center; 
	}
	
	.nickname_ajax_2,
	.nickname_ajax_3 {
		display: none;
		font-size: 12px;
		color: green;
       text-align:center;  
       justify-content: center;
       align-items: center; 
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
				<p class="page-title m-0 fs-2">MY INFORMATION</p>
			</div><!-- end container -->
		</div><!-- end head-title -->

		<div class="main">
			<div class="py-3"></div>
			<div class="container">
				<div class="row">
					<div class="d-flex justify-content-center">
						<div class="acc-box equal"><!-- equal 지워야 사이즈 가변적 -->
							<div class="p-4 pt-0 p-title h3 mb-0 text-center">
							#내 정보 수정</div>
							<p class="mb-0 text-center acc-btn">
							<em>You can modify your information.</em></p>
							<p class="mt-3 acc-btn text-right">
							* 표시는 수정 불가합니다.</p>

							<!-- 파일 업로드시 form태그 안에 enctype="multipart/form-data" 입력해야함.
							enctype(인코딩타입)을 multipart/form-data로 반드시 설정 -->
							<form 
							id="modifymyinfo_form" 
							method="POST" 
							enctype="multipart/form-data"
							action="modifymyinfo"><!-- enctype : 파일 첨부할때 필요한 속성/ action="/member/modifymyinfo" -->
								
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
											placeholder="실명을 입력해주세요." 
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
										<input id="id_marketing_yes" 
											type="radio" 
											name="marketing"
											value="1" 
											checked>&nbsp;동의&nbsp;&nbsp;&nbsp;
	                    				<input id="id_marketing_no" 
	                    					type="radio"
											name="marketing" 
											value="0">&nbsp;미동의&nbsp;&nbsp;&nbsp;
                   					</c:if>
								</div>
								<div class="form-group">
									<label for="profileImage">프로필 사진</label>
									<!-- 첨부파일이 없을 때 -->
									<c:if
										test="${ member.profileImg=='default_profile_img.png' || member.profileImg==null}">
										<div style="text-align:center;">현재 설정된 프로필 사진이 없습니다.
										</div><br/>
									</c:if>
									<!-- 첨부파일이 있을 때 미리보기 사진 보임-->
									<c:if
										test="${ member.profileImg!='default_profile_img.png' && member.profileImg!=null}">
										<div id="previewImg" style="font-size:15px; text-align:center">
											<img 
											src="<spring:url value = '/profileimg/${ member.profileImg }'/>" 
											style="max-width: 300px;"
											alt="프로필사진미리보기"/><br/>
											<c:out value="${ member.profileImgOrg } (${ member.profileImgSize } byte)"/>		
										</div><br/>
									</c:if> 
					
									<div style="text-align:center;">
									 <input name="file"
											type="file"
											id="inputfile" 
											accept="image/png, image/jpg, image/jpeg, image/gif"
											class="form-control-file"
											aria-describedby="sizeHelp" 
											style="text-align:center;">
									 <small id="sizeHelp" 
											class="form-text text-muted"
											style="font-size:13px; text-align:center;">
											파일 크기는 10MB를 넘을 수 없습니다.</small>
									<img id="preImage" 
										src="${pageContext.request.contextPath}/saveFile/${member.profileImg}" 
										style="max-width:200px;"
										/>			
							<!-- <input type="button" onclick="submitFile()" value="파일전송"> -->
									</div><!-- div style="text-align:center;" 종료 -->
								</div><!-- form-group 종료 -->
								<br/>
					    <script type="text/javascript">    
					        $(function() {
					            $("#inputfile").on('change', function(){
					                readURL(this);
					            });
					        });
					        
					        function readURL(input) {
					            if (input.files && input.files[0]) {
					               var reader = new FileReader();
					               reader.onload = function (e) {
					                  $('#preImage').attr('src', e.target.result);
					               }
					               reader.readAsDataURL(input.files[0]);
					            }
					        }
					    </script>

								<input type="submit" 
									id="modifyOkBtn" 
									class="btn btn-primary form-btn mt-2"							
									value="수정하기"
									><!--  onclick="modifypost(this)"-->
								</input><!-- button태그는 type="button"로 버튼의 기본 submit 기능을 막을 수 있음..근데 제출이 안됨.. -->
								<br/>
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
		
<!-- 탈퇴하기 모달창 로직 부분 임시삭제 -->

<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
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
				console.log($('.nickname_ajax_4').css('display')); //변경사항이 없습니다.(변경없음)
				var value = $("input[type=radio][name=marketing]:checked").val();
				console.log("마케팅 동의여부: " + value); //마케팅 동의여부 체크된 값 value
				
				let data = $("#modifymyinfo_form").serializeArray();
				//console.log(data);
				
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
						//$("#modifymyinfo_form").attr("action", "/modifymyinfo"); //modifymyinfo_form은 form 태그의 name
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

			$(document).ready(function() { //현재 페이지 들어오면 항상 수행
				/*
 				$("#modifyOkBtn").on("click", function() { //수정하기 버튼 클릭시 

 					let nickCheckok1 = $('.nickname_ajax_1').css('display'); //inline-block(사용가능), none(사용불가)
 					let nickCheckok2 = $('.nickname_ajax_2').css('display');
 					let nickCheckok3 = $('.nickname_ajax_3').css('display');
 					let nickCheckok4 = $('.nickname_ajax_4').css('display');
 					console.log($('.nickname_ajax_1').css('display')); //닉네임 중복여부 알려줌: inline-block(사용가능), none(사용불가)
 					console.log($('.nickname_ajax_4').css('display')); //변경사항이 없습니다.(변경없음)
 					var value = $("input[type=radio][name=marketing]:checked").val();
 					console.log("마케팅 동의여부: " + value); //마케팅 동의여부 체크된 값 value
 					 					
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
 							//$("#modifymyinfo_form").attr("action", "modifymyinfo"); //modifymyinfo_form은 form 태그의 name
 							//$("#modifymyinfo_form").submit(); //document.modifymyinfo_form.submit();
 							alert("회원정보가 정상적으로 수정되었습니다.");
 						} 
 							
 					} else { //닉네임 중복시 수정불가
 						console.log('닉네임 중복으로 수정불가-아무 동작 없음');
 					}
 					
				}); 
				*/
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