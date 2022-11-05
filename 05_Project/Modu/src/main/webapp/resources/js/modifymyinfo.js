$(document).ready(function () {
	
	//내정보수정 - 현재 프로필사진 삭제버튼 클릭시
	$("#removemyprofileimgBtn").click(function () {	
		$(this).attr('src',""); //$(this).remove(""); 둘다 가능 
		let data = $('#profileExist').val(); //현재 프로필 사진
		console.log(data);
		
		$.ajax({
			type : "GET",
			url : "/member/removemyprofileimg",
			data : { profileImg: data }, // '컨트롤에 넘길 데이터 이름' : '데이터(#id_email에 입력되는 값)'
			success : function(response) {
				
				if(response == 'delete성공'){
					console.log("if문 진입- delete성공")
					//$(this).attr('src',"<spring:url value = '/pics/profile/${ member.profileImg }'/>");
					//location.href="/member/mypage";
					location.reload(); //현재 접속중인 페이지를 새로고침
				} else {
					console.log("else문 진입- delete 실패")
					location.reload(); //현재 접속중인 페이지를 새로고침
				}
				
			} // success 종료
		}); // ajax 종료					
	
	}) //$("#removemyprofileimg").click(function () 종료
}); // $(document).ready(function () 종료 
 
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
						
						$.ajax({	type : "post",
									url : "/member/modify-myinfo/nicknamevalidcheck",
									data : data,
									success : function(result) {
										if (result == 'noshow') { // 닉네임 공란일시 
											console.log(result);
											
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
															"none");
										} else if (result == 'short') { // 닉네임 3자 미만시 
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
											console.log("(변경사항 없음) 원래 나의 닉네임");
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

		//프로필 사진 변경사진 업로드시 미리보기 및 삭제 이벤트 처리
	 	function handleFileSelect(event) {
			console.log("handleFileSelect 진입 시작");
			var input = this;
			console.log(input.files);
			
			if (input.files && input.files.length) {
				var reader = new FileReader();
				this.enabled = false
				reader.onload = (function(e) {
					console.log(e)
					$("#preview").html(['<img class="thumb" src="',
											e.target.result,
											'" title="',
											escape(e.name),
											'"/>' ]
											.join(''))
				});
				reader.readAsDataURL(input.files[0]);
			}
		}

		$('#file').change(handleFileSelect);
		$('.file-edit-icon').on('click',
				'.preview-delete', function() {
					$("#preview").empty()
					$("#file").val("");
				}); 
	
$(document).ready(function() { //현재 페이지 들어오면 항상 수행
		$("#modifyOkBtn").on("click",
						function() { //수정하기 버튼 클릭시 
							let nickCheckok1 = $(
									'.nickname_ajax_1')
									.css('display'); //inline-block(사용가능), none(사용불가)
							let nickCheckok2 = $(
									'.nickname_ajax_2')
									.css('display');
							let nickCheckok3 = $(
									'.nickname_ajax_3')
									.css('display');
							let nickCheckok4 = $(
									'.nickname_ajax_4')
									.css('display');
							console.log(nickCheckok1); //닉네임 중복여부 알려줌: inline-block(사용가능), none(사용불가)
							console.log(nickCheckok4); //변경사항이 없습니다.(변경없음)
							var value = $(
									"input[type=radio][name=marketing]:checked")
									.val();
							console.log("마케팅 동의여부: "
									+ value); //마케팅 동의여부 체크된 값 value

							if (nickCheckok1 === 'inline-block'
									|| nickCheckok4 === 'inline-block'
									|| nickCheckok1 === 'none'
									&& nickCheckok2 === 'none'
									&& nickCheckok3 === 'none'
									&& nickCheckok4 === 'none') { // 닉네임 사용 가능할 때
								//여기서부턴 하나라도 공란일시 alert 됨        
								if ($("#id_pwd1").val() == ""
										|| $("#id_pwd2")
												.val() == "") {
									alert("비밀번호를 입력해주세요.");
									$("#id_pwd1")
											.focus();
								} else if ($(
										"#id_nickname")
										.val() == "") {
									alert("닉네임을 입력해주세요.");
									$("#id_nickname")
											.focus();
								} else { //정상일 때 submit
									console.log('내정보 수정조건 통과!');
									$("#modifymyinfo_form").submit();
									alert("회원정보가 정상적으로 변경되었습니다.");
								}
							} else { //닉네임 중복시 수정불가
								console.log('닉네임 중복으로 수정불가-아무 동작 없음');
								alert("사용할 수 없는 닉네임입니다.");
							}
						}); //$("#modifyOkBtn").on("click", function()  종료
				}); //$(document).ready(function(){} 종료

function removeMember(e) { //탈퇴버튼 클릭시
					
	var result = confirm("정말 탈퇴하시겠습니까? \n탈퇴후 모든 정보가 파기되며 되돌릴 수 없으니 신중해주세요.");
	if (result == true) {
		let data = $('#id_email').val();
		console.log(data);
		
		$.ajax({
			type : "POST",
			url : "/member/removemyinfo",
			data : {email: data},
			success : function(result) {
				alert("정상적으로 탈퇴되었습니다. \n그동안 '모두의식탁'을 이용해주셔서 감사합니다.");
				location.href="/";
			} // success 종료
		}); // ajax 종료					
	} else {
		e.preventDefault();
		alert("탈퇴를 취소하셨습니다.");
		return false;
	}
	
}