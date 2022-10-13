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
</head>
<body>
	<div id="page" class="hfeed site">
		<!-- start page wrapper -->
		<%@ include file="/WEB-INF/views/common/menu.jsp"%>
		<div class="head-title py-4 m-0">
			<div class="container">
				<p class="page-title m-0 fs-2">REGISTER</p>
			</div>
			<!-- end container -->
		</div>
		<!-- end head-title -->

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
									<label for="id_email">이메일 *</label> <input name="email"
										type="email" class="form-control" id="id_email"
										placeholder="you@email.com" maxlength=40 required /> <span
										class="email_ajax_1">사용 가능한 이메일입니다.</span> <span
										class="email_ajax_2">이미 사용 중인 이메일입니다.</span> <span
										class="email_ajax_3">올바른 이메일을 입력해주세요.</span>
								</div>

								<div class="form-group">
									<label for="id_pw1">비밀번호 *</label><input name="pwd1"
										type="password" class="form-control" id="id_pw1"
										placeholder="5자 이상 입력해주세요." maxlength=30 required />
								</div>

								<div class="form-group">
									<label for="id_pw2">비밀번호 확인 *</label><input name="pwd"
										type="password" class="form-control" id="id_pw2"
										placeholder="5자 이상 입력해주세요." maxlength=30 required />
								</div>

								<div class="form-group">
									<label for="id_nickname">닉네임 *</label><input name="nickname"
										type="text" class="form-control" id="id_nickname"
										placeholder="3자 이상 입력해주세요." maxlength=10 required /> <span
										class="nickname_ajax_1">사용 가능한 닉네임입니다.</span> <span
										class="nickname_ajax_2">이미 사용 중인 닉네임입니다.</span> <span
										class="nickname_ajax_3">닉네임은 3자 이상 입력해주세요.</span>
								</div>

								<div class="form-group">
									<label>성명 (선택)</label> <input name="name" type="text"
										class="form-control" id="id_name" placeholder="실명을 입력해주세요."
										maxlength=5 />
								</div>
								<div class="form-group">
									<label for="id_phone">휴대폰번호 (선택)</label><input name="phone"
										type="text" class="form-control" id="id_phone"
										placeholder="숫자만 입력해주세요." maxlength=11 />
								</div>

								<label for="agreements">약관 동의</label>
								<div class="agreements" id="agreements">
									<div for="apiUsing">
										<input type="checkbox" id="apiUsing" name="apiUsing" value="1">
										<span><strong>본 사이트의 <a
												href="javascript:agreement1()"
												style="text-decoration: none; color: black;"><u>이용약관</u></a>
												및 <a href="javascript:agreement2()"
												style="text-decoration: none; color: black;"><u>개인정보
														처리방침</u></a>에 동의합니다.
										</strong> (필수)</span>
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
		</div>
		<!-- end main -->

		<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
	</div>
	<!-- end #page hfeed site -->

	<script type="text/javascript">
	    $(function(){ //비밀번호 2개 일치여부 확인, 이메일/닉네임 중복체크 ajax
	        $('#id_pw2').blur(function(){ //비밀번호 일치 여부 확인 alert
		        if($('#id_pw1').val().length===0){ 
		        }
		        else if($('#id_pw1').val() != $('#id_pw2').val()) {
		                if($('#id_pw2').val()!=''){
		                    alert("비밀번호가 일치하지 않습니다.");
		                    $('#id_pw1').val('');
		                    $('#id_pw2').val('');
		                    $('#id_pw1').focus();
		                    }
		                }	
	        	});
	        
		      //회원가입 이메일 중복체크 ajax
		      $('#id_email').on("propertychange change tab keyup paste input", function(){
		          var inputEmail = $('#id_email').val(); // #id_email에 입력되는 값
		          var data = {email : inputEmail} // 컨트롤러에 넘길 데이터 이름' : '입력받는 이름(#id_email에 입력되는 값)'
		          $.ajax({
		              type : "post",
		              url : "/member/register/emailvalidcheck",
		              data : data,
		              success : function(result){
		                  if(result == 'noshow'){ // 이메일 10자 미만시
		                      $('.email_ajax_1').css("display","none");
		                      $('.email_ajax_2').css("display", "none");
		                      $('.email_ajax_3').css("display", "inline-block");
		                  } else if(result == 'success'){ //이메일 사용 가능, 중복X
		                      $('.email_ajax_1').css("display", "inline-block");
		                      $('.email_ajax_2').css("display","none");
		                      $('.email_ajax_3').css("display","none");
		                  } else if (result == 'fail'){ //이메일 사용 불가, 중복 이메일 존재
		                      $('.email_ajax_1').css("display","none");
		                      $('.email_ajax_2').css("display", "inline-block");
		                      $('.email_ajax_3').css("display","none");}
		              } // success 종료
		          }); // ajax 종료
		      });// $('#id_email') function 종료
		
		      //회원가입 닉네임  중복체크 ajax
		      $('#id_nickname').on("propertychange change tab keyup paste input", function(){
		         var inputNickname = $('#id_nickname').val();	// #id_nickname에 입력되는 값
		         var data = { nickname : inputNickname } // '컨트롤에 넘길 데이터 이름' : '데이터(#id_email에 입력되는 값)'
		             $.ajax({
		                 type : "post",
		                 url : "/member/register/nicknamevalidcheck",
		                 data : data,
		                 success : function(result){
		                     if(result == 'noshow'){ // 닉네임 3자 미만시 
		                         $('.nickname_ajax_1').css("display","none");
		                         $('.nickname_ajax_2').css("display","none");
		                         $('.nickname_ajax_3').css("display","inline-block");
		                     } else if(result == 'success'){ //닉네임 사용 가능, 중복X
		                         $('.nickname_ajax_1').css("display", "inline-block");
		                         $('.nickname_ajax_2').css("display","none");
		                         $('.nickname_ajax_3').css("display","none");
		                     } else if (result == 'fail'){ //닉네임 사용 불가, 중복 닉네임
		                         $('.nickname_ajax_1').css("display","none");
		                         $('.nickname_ajax_2').css("display", "inline-block");
		                         $('.nickname_ajax_3').css("display","none");
		                     }
		                 } // success 종료
		             }); // ajax 종료
		      	});// $('id_nickname') function 종료
	     	}); //$(function() 종료
	    
	     			//회원가입 약관동의 팝업창 1,2 function 
	      function agreement1(){
		       var popupX = (document.body.offsetWidth / 2) - (600 / 2);
			    // 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
			    var popupY= (window.screen.height / 2) - (300 / 2);
			    // 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음

		    	window.open('<%=request.getContextPath()%>/member/agreement1', '개인정보 처리방침', 'status=no, height=300, width=600, left='+ popupX + ', top='+ popupY);
	     	}
	     	
	      function agreement2(){
	    	  var popupX = (document.body.offsetWidth / 2) - (600 / 2);
	    	// 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
	    	var popupY= (window.screen.height / 2) - (300 / 2);
	    	// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음

		    window.open('<%=request.getContextPath()%>/member/agreement2', '개인정보 처리방침', 'status=no, height=300, width=600, left='+ popupX + ', top='+ popupY);
	      }

	      
	  	$(document).ready(function(){ //회원가입 페이지 들어오면 작동되는 fuction
          $("#registerokbutton").on("click", function(){ //가입하기 버튼 클릭시
        	  var emailCheck = $('.email_ajax_1').css('display'); //inline-block(사용가능), none(사용불가)
        	  var nickCheck = $('.nickname_ajax_1').css('display'); //inline-block(사용가능), none(사용불가)
    		  console.log($('.email_ajax_1').css('display')); //이메일 중복여부 알려줌: inline-block(사용가능), none(사용불가)
    		  console.log($('.nickname_ajax_1').css('display')); //닉네임 중복여부 알려줌: inline-block(사용가능), none(사용불가)
       	  
              var apiUsingCheck = document.querySelector('input[name=apiUsing]').checked; //true, false 반환
        	  var marketingCheck = document.querySelector('input[name=marketing]').checked; //true, false 반환
        	  //console.log(apiUsingCheck); // 필수약관 true or false
        	  //console.log(marketingCheck); // 선택약관 true or false
        	  
        	//1. 이메일,닉네임 모두 사용가능일 시(중복 아닐 때)
        	  if (emailCheck === 'inline-block' && nickCheck === 'inline-block'){ 
        			//하나라도 공란일시 alert
        		  if($("#id_email").val()==""){ 
                      alert("이메일을 입력해주세요.");
                      $("#id_email").focus();
                   }else if($("#id_pw2").val()==""){
                        alert("비밀번호를 입력해주세요.");
                        $("#id_pw2").focus();
                    }else if($("#id_nickname").val()==""){
                        alert("닉네임 입력해주세요.");
                        $("#id_nickname").focus();
                    } else if(apiUsingCheck==false){ //필수 약관 미체크일 때
                	   alert("(필수약관 동의) 본 사이트의 이용약관 및 개인정보 처리방침에 대한 동의가 필요합니다.");
                   } else { //정상 조건일 때 submit
                	  console.log('회원가입 조건 통과!');
                	  
                	  let formData = $("#register_form").serializeArray();
                	  console.log(formData);
	        		  $.ajax({
	                      type : "post",
	                      url : "/member/register",
	                      data : formData,
	                      success : function(data){
	    		              alert("회원가입이 완료되었습니다. \n다음 화면에서 로그인 해주세요.");
	    		              location.href="/";
	                      } // success 종료
	                  }); // ajax 종료
                   }
        	  } else {
        		//2. 이메일 혹은 닉네임 하나 이상 중복일 때 가입불가(submit 막기 위해 아무동작 하지 않음)
        		  console.log('가입불가-아무 동작 없음')
        	  }
          });
	  }); //$(document).ready(function(){ 종료
    </script>
</body>
</html>