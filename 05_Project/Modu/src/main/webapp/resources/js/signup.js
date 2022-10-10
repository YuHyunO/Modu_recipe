$(document).ready(function() { // 회원가입 페이지 들어오면 작동되는 fuction

	$("#join_button").on("click", function() { // 가입하기 버튼 클릭시 수행

		// 중복 이메일을 넣고 가입하기 버튼 클릭시
		var inputEmail = $('#id_email').val(); // #id_email에 입력되는 값
		var data = {
			checkEmail : inputEmail
		} // '컨트롤에 넘길 데이터 이름' : '데이터(#id_email에 입력되는 값)'
		$.ajax({
			type : "post",
			url : "/signup.do/memberIdChk",
			data : data,
			success : function(result) {
				if (result == 'fail') { // 사용 불가
					alert("사용 중인 이메일입니다. 이메일을 다시 확인해주세요.");
				} else if (result == 'noshow') { // 사용 불가
					alert("이메일 길이가 너무 짧습니다.(10자 이상 입력해주세요)");
				}

				$("#id_email").focus();
				return false;
			} // success 종료
		// 여기에 작성하면 오류 발생
		}); // ajax 종료

		// 여기서부턴 하나라도 공란일시 alert 됨
		if ($("#id_email").val() == "") {
			alert("이메일을 입력해주세요.");
			$("#id_email").focus();
			return false;
		}

		if ($("#id_pw2").val() == "") {
			alert("비밀번호를 입력해주세요.");
			$("#id_pw1").focus();
			return false;
		}
		if ($("#id_name").val() == "") {
			alert("이름을 입력해주세요.");
			$("#id_name").focus();
			return false;
		}
		if ($("#id_nickname").val() == "") {
			alert("닉네임을 입력해주세요.");
			$("#id_nickname").focus();
			return false;
		}
		if ($("#id_phonenum").val() == "") {
			alert("휴대폰 번호를 입력해주세요.");
			$("#id_phonenum").focus();
			return false;
		}

		// 회원가입 버튼(회원가입 기능 정상 작동) // Q. 이메일 중복시 아래가 수행되지 않도록 하려면?
		$("#signup_form").attr("action", "/signup.do");
		$("#signup_form").submit();
	});

});

$(function() {
	$('#id_pw2').blur(function() { // 비밀번호 일치 여부 확인 alert
		if ($('#id_pw1').val() != $('#id_pw2').val()) {
			if ($('#id_pw2').val() != '') {
				alert("비밀번호가 일치하지 않습니다.");
				// $'#id_pw1').val('');
				$('#id_pw2').val('');
				$('#id_pw2').focus();
			}
		}
	});
});

// 가입 이메일(id) 중복여부 ajax
$('#id_email').on("propertychange change tab keyup paste input", function() {

	var inputEmail = $('#id_email').val(); // #id_email에 입력되는 값
	var data = {
		checkEmail : inputEmail
	} // 컨트롤러에 넘길 데이터 이름' : '입력받는 이름(#id_email에 입력되는 값)'

	$.ajax({
		type : "post",
		url : "/signup.do/memberIdChk",
		data : data,
		success : function(result) {
			if (result == 'noshow') { // 이메일 10자 미만시 문구 미노출
				$('.email_input_re_1').css("display", "none");
				$('.email_input_re_2').css("display", "none");
			} else if (result == 'success') { // 사용 가능, 중복X
				$('.email_input_re_1').css("display", "inline-block");
				$('.email_input_re_2').css("display", "none");
			} else if (result == 'fail') { // 사용 불가, 중복 이메일 존재
				$('.email_input_re_1').css("display", "none");
				$('.email_input_re_2').css("display", "inline-block");
			}
		} // success 종료
	}); // ajax 종료
});// $('#id_email') function 종료
