$(function() { // 비밀번호 2개 일치여부 확인, 이메일/닉네임 중복체크 ajax
	$('#id_pw2').blur(function() { // 비밀번호 일치 여부 확인 alert
		if ($('#id_pw1').val().length === 0) {
		} else if ($('#id_pw1').val() != $('#id_pw2').val()) {
			if ($('#id_pw2').val() != '') {
				alert("비밀번호가 일치하지 않습니다.");
				$('#id_pw1').val('');
				$('#id_pw2').val('');
				$('#id_pw1').focus();
			}
		}
	});

	// 회원가입 이메일 중복체크 ajax
	$('#id_email').on("propertychange change tab keyup paste input",
			function() {
				var inputEmail = $('#id_email').val(); // #id_email에 입력되는 값
				var data = {
					email : inputEmail
				} // 컨트롤러에 넘길 데이터 이름' : '입력받는 이름(#id_email에 입력되는 값)'
				$.ajax({
					type : "post",
					url : "/member/register/emailvalidcheck",
					data : data,
					success : function(result) {
						if (result == 'noshow') { // 이메일 10자 미만시
							$('.email_ajax_1').css("display", "none");
							$('.email_ajax_2').css("display", "none");
							$('.email_ajax_3').css("display", "inline-block");
						} else if (result == 'success') { // 이메일 사용 가능, 중복X
							$('.email_ajax_1').css("display", "inline-block");
							$('.email_ajax_2').css("display", "none");
							$('.email_ajax_3').css("display", "none");
						} else if (result == 'fail') { // 이메일 사용 불가, 중복 이메일 존재
							$('.email_ajax_1').css("display", "none");
							$('.email_ajax_2').css("display", "inline-block");
							$('.email_ajax_3').css("display", "none");
						}
					} // success 종료
				}); // ajax 종료
			});// $('#id_email') function 종료

	// 회원가입 닉네임 중복체크 ajax
	$('#id_nickname').on(
			"propertychange change tab keyup paste input",
			function() {
				var inputNickname = $('#id_nickname').val(); // #id_nickname에
				// 입력되는 값
				var data = {
					nickname : inputNickname
				} // '컨트롤에 넘길 데이터 이름' : '데이터(#id_email에 입력되는 값)'
				$.ajax({
					type : "post",
					url : "/member/register/nicknamevalidcheck",
					data : data,
					success : function(result) {
						if (result == 'noshow') { // 닉네임 3자 미만시
							$('.nickname_ajax_1').css("display", "none");
							$('.nickname_ajax_2').css("display", "none");
							$('.nickname_ajax_3')
									.css("display", "inline-block");
						} else if (result == 'success') { // 닉네임 사용 가능, 중복X
							$('.nickname_ajax_1')
									.css("display", "inline-block");
							$('.nickname_ajax_2').css("display", "none");
							$('.nickname_ajax_3').css("display", "none");
						} else if (result == 'fail') { // 닉네임 사용 불가, 중복 닉네임
							$('.nickname_ajax_1').css("display", "none");
							$('.nickname_ajax_2')
									.css("display", "inline-block");
							$('.nickname_ajax_3').css("display", "none");
						}
					} // success 종료
				}); // ajax 종료
			});// $('id_nickname') function 종료
}); // $(function() 종료

// 회원가입 약관동의 팝업창 1,2 function
function agreement1() {
	var popupX = (document.body.offsetWidth / 2) - 100;
	// 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
	var popupY = (window.screen.height / 2) - 150;
	// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
	window.open('/member/agreement1', '개인정보 처리방침',
			'status=no, height=300, width=600, left=' + popupX + ', top='
					+ popupY);
}
/*<%=request.getContextPath()%>*/

function agreement2() {
	var popupX = (document.body.offsetWidth / 2) - 100;
	// 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
	var popupY = (window.screen.height / 2) - 150;
	// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
	window.open('/member/agreement2', '개인정보 처리방침',
			'status=no, height=300, width=600, left=' + popupX + ', top='+ popupY);
}

$(function() { // 회원가입 페이지 들어오면 작동되는 fuction
	$("#registerokbutton").on("click",function() { // 가입하기 버튼 클릭시
		var emailCheck = $('.email_ajax_1').css('display'); // inline-block(사용가능),none(사용불가)
		var nickCheck = $('.nickname_ajax_1').css('display'); // inline-block(사용가능),none(사용불가)
		//console.log($('.email_ajax_1').css('display')); // inline-block(사용가능),none(사용불가)
		//console.log($('.nickname_ajax_1').css('display'));// inline-block(사용가능), none(사용불가)

		var apiUsingCheck = document.querySelector('input[name=apiUsing]').checked; // true, false 반환
		var marketingCheck = document.querySelector('input[name=marketing]').checked;// true, false 반환
		// console.log(apiUsingCheck); // 필수약관
		// console.log(marketingCheck); // 선택약관

		// 1. 이메일,닉네임 모두 사용가능일 시(중복 아닐 때)
		if (emailCheck === 'inline-block'
				&& nickCheck === 'inline-block') {
			// 하나라도 공란일시 alert
			if ($("#id_email").val() == "") {
				alert("이메일을 입력해주세요.");
				$("#id_email").focus();
			} else if ($("#id_pw2").val() == "") {
				alert("비밀번호를 입력해주세요.");
				$("#id_pw2").focus();
			} else if ($("#id_nickname").val() == "") {
				alert("닉네임 입력해주세요.");
				$("#id_nickname").focus();
			} else if (apiUsingCheck == false) { //필수약관 미체크 시
				alert("(필수약관 동의) 본 사이트의 이용약관 및 개인정보 처리방침에 대한 동의가 필요합니다.");
			} else { // 정상 조건일 때 submit
				console.log('회원가입 조건 통과!');
				let formData = $("#register_form").serializeArray();
				console.log(formData);
				$.ajax({
						type : "post",
						url : "/member/register",
						data : formData,
						success : function(data) {
							alert("회원가입이 완료되었습니다. \n다음 화면에서 로그인 해주세요.");
							location.href = "/";
						} // success 종료
					}); // ajax 종료
				}
		} else { // 2. 이메일 혹은 닉네임 하나 이상 중복일 때
			// 가입불가(submit 막기 위해 아무동작 하지 않음)
			alert("이메일 또는 닉네임을 다시 확인해주세요.");
			$('#id_email').focus();
		}
	});
}); // $(document).ready(function(){ 종료
