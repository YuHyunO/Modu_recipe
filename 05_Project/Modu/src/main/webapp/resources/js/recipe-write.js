$(function () {
	//페이지 뒤로가기 방지
	function prevent(){
		history.pushState(null, null, location.href);
		window.onpopstate = function(event) {
		    history.go(1);
		}
	}
	
	$(window).on("mousemove", () => {
		prevent();
	});
	
	$(window).on("keydown", () => {
		prevent();
	});
	
	$(window).on("click", () => {
		prevent();
	});
	
	// 태그 로직
	let tag = $('#tag');
	$('#tag').keyup(
		function (e) {
			// 태그 정규식표현 로직
			let tagsRegex = /[^a-z|A-Z|ㄱ-ㅎ|가-힣|\,/\s]/g;
			
			if(!tagsRegex.test(tag.val())){
				// 엔터, 쉼표, 스페이스 바 입력 시
				if (e.keyCode == '13' || e.keyCode == '188' || e.keyCode == '32') { 
					let colors = ['#3c1cbf', '#208b3c', '#0d6efd',
						'#212529', '#5bc0de', '#d65bde']
					let randint = Math.floor(Math.random()
						* colors.length);
					let newNum = $('.tag-ul').children('li').length + 1;
					let text = tag.val().split(',')[0];
					if(text.trim().length === 0){
						// 태그 글자가 공백일 경우 패스
					} else if(text.trim().length > 5){
						alert("태그는 5글자까지만 입력 가능합니다.");
					} else {
						if (newNum == 6) {
							alert("태그는 5개까지만 가능합니다.");
						} else {
							let html = '<li class="d-flex align-items-center px-2 me-1" id="tag-li-'
								+ newNum
								+ '" style="background: '
								+ colors[randint]
								+ '">\
		                    <input value="'
								+ text
								+ '" type="hidden" id="tag-'
								+ newNum
								+ '"/>\
		                    <span>'
								+ text
								+ '</span>\
		                    <button class="tag-delete-btn p-1" onclick="deleteTag(this)" style="background: '
								+ colors[randint]
								+ '">X</button>\
		                </li>';
							$('.tag-ul').append(html);
							tag.val('');
						}
					}
				}
			}else {
				alert("태그에는 숫자 및 특수문자를 사용할 수 없습니다.");
			}
			
			// 태그 갯수 체크
			if($('.tag-ul').find('li').length === 0){
				tag.parent().find('.warning-text').text("*태그를 1개 이상 등록해주세요");
			} else {
				tag.parent().find('.warning-text').text(" ");
			}	
		}
	); // 태그 추가 END
	
	$(".ingredient").find("input").keyup(function(){
		let text;
		let regex;
		let thisClass = $(this).attr("class").split("-")[0];

		if (thisClass === "ingredient"){
			regex = /[^a-z|A-Z|0-9|ㄱ-ㅎ|가-힣|]/g;
			text = "재료에는 특수문자를 사용할 수 없습니다.";
		} else {
			regex = /[^a-z|A-Z|0-9|ㄱ-ㅎ|가-힣|//]/g;
			text = "재료 수량에는 / 를 제외한 특수문자를 사용할 수 없습니다."; 
		}
		
		if (!regex.test($(this).val()) && !regex.test($(this).val())) {
			//console.log("정규식 통과");
		}else {
			alert(text);
		}
	}); // 재료 정규식 표현 END
	
	let foodInput = $("input[name=food]"); 
	$(foodInput).keyup(function() {
		// 음식 이름 정규식 표현 로직
		let foodRegex = /[`()<>{}~!@#$%^&*|\\\'\";:\/?-_+=]/gi;
		if (!foodRegex.test($("input[name=food]").val())) {
			// alert("특수문자 없음");
		}else {
			alert("음식 이름에는 특수문자를 사용하실 수 없습니다.");
			return false;
		}
	});
	
	let title = $("input[name=title"); // 레시피 제목
	let info = $("textarea[name=info]"); // 레시피 소개
	$(title).keyup(() => {
		checkValue(title, "레시피 제목", 10);
	})
	
	$(info).keyup(() => {
		checkValue(info, "레시피 소개", 20);
	})
	
	let steps = $('#steps').find('textarea');
	for (let i=0; i<steps.length; i++){
		$(steps).eq(i).keyup(() => {
			checkValue($(steps).eq(i), "요리방법", 10);
		})
	}
	
});

function deleteTag(e) {
	let startNum = $(e).parent().attr('id').split('-')[2] - 1;
	let endNum = $('.tag-ul').children('li').length;
	let targetChildren = $('.tag-ul').children('li');
	$(e).parent().remove();
	for (let i = endNum; i > startNum; i--) {
		targetChildren.eq(i).attr('id', 'tag-li-' + i); // item ID 변경
		targetChildren.eq(i).find('input').attr('id', 'tag-' + i) // input id
																	// 변경
	}
	// 태그 갯수 체크
	let tag = $('#tag');
	if($('.tag-ul').find('li').length === 0){
		tag.parent().find('.warning-text').text("*태그를 1개 이상 등록해주세요");
	} else {
		tag.parent().find('.warning-text').text(" ");
	}	
}

function addItem(e) {
	let btnID = $(e).attr("id");
	let text = btnID.split('-')[0];
	let lastNum = $('.' + text + '-items').children(".item").length;
	let newNum = lastNum + 1;
	let html;
	let items;
	let quantities;
	if (lastNum === 10) {
		alert('재료는 최대 10개까지만 입력 가능합니다.')
	} else {
		if (btnID === 'main-add') {
			items = ['소고기', '돼지고기', '닭고기', '양고기', '대파', '쪽파', '양파', '감자',
				'고구마', '당근'];
			quantities = ['100g', '300g', '1마리', '200g', '1쪽', '1/2쪽', '1/4개',
				'150g', '100g', '1개'];
		} else if (btnID === 'sub-add') {
			items = ['참기름', '국간장', '고춧가루', '다진마늘', '액젓', '식초', '설탕', '통깨',
				'굴소스', '물'];
			quantities = ['2스푼', '1스푼', '1스푼', '1스푼', '1큰술', '1큰술', '1.5큰술',
				'적당량', '1/2큰술', '100ml'];
		}
		html = '<div class="row d-flex align-items-center mb-2 item" id="'
			+ text + '-' + newNum
			+ '">\
            <input type="text" class="ingredient-'
			+ newNum + ' form-control input1" placeholder="예) '
			+ items[newNum - 1]
			+ '" >\
            <input type="text" class="quantity-'
			+ newNum + ' form-control input2 mx-2" placeholder="예) '
			+ quantities[newNum - 1]
			+ '" >\
            <button class="minus ' + text + '-'
			+ newNum
			+ '" onclick="deleteItem(this)" tabindex="-1"></button>\
        </div>'
		$('.' + text + '-items').append(html);
	}
}

function deleteItem(e) {
	let targetID = $(e).attr('class').split(' ')[1];
	let targetItems = targetID.split('-')[0] + '-items';
	let targetItemsChildren = $('.' + targetItems).children('.item');
	let startNum = targetID.split('-')[1] - 1;
	let endNum = targetItemsChildren.length;
	let text;
	if (targetID.split('-')[0] === 'main') {
		text = 'main';
		if ($('.main-items').find('input').length === 2){
			alert("재료는 1개 이상 등록해주세요");
			return false;
		}
	} else if (targetID.split('-')[0] === 'sub') {
		text = 'sub';
		if ($('.sub-items').find('input').length === 2){
			alert("재료는 1개 이상 등록해주세요");
			return false;
		}
	}
	
	$('#' + targetID).remove() // 해당 요소 삭제

	for (let i = endNum; i > startNum; i--) {
		targetItemsChildren.eq(i).attr('id', text + '-' + i); // item ID 변경
		targetItemsChildren.eq(i).find('.input1').attr('class',
			'ingredient-' + i + ' form-control input1') // input1 class 변경
		targetItemsChildren.eq(i).find('.input2').attr('class',
			'quantity-' + i + ' form-control input2 mx-2') // input2 class
		// 변경
		targetItemsChildren.eq(i).find('.minus').attr('class',
			'minus ' + text + '-' + i) // 삭제버튼 class 변경
	}
}

function addStep(e) {
	let target = $('#steps');
	let lastNum = target.children().length;
	if (lastNum === 12) {
		alert("요리순서는 최대 12개까지만 입력가능합니다.");
	} else {
		let newNum = lastNum + 1;
		let items = [
			'닭이 잠길정도로 물을 넣고 손질한 닭을 끓여주세요',
			'간이 잘 배도록 설탕 3T를 먼저 넣어 주세요',
			'단단한 채소 투하!\n※여기서 양파는 단맛을 내기 위해 함께 넣어 주세요:)',
			'야채가 끓기 시작하면 간마늘 1T,',
			'간장은 한컵 조금 안되게,',
			'짠맛을 중화해줄 버섯까지 모두 넣어서 보글보글 끓여 주세요.',
			'아아, 여기서 꿀팁 하나! \n집에 어린 아이가 있으신 분들은, 몇조각 작은냄비에 담아 따로 끓여주세요 :)',
			'이제 고춧가루 반컵 투하!\n※취향대로 가감해주세요:)',
			'마무리로 파1대, 고추1개 넣고 후춧가루를 톡톡 부려주시면 끄읕!',
			'아아,\n이게 내가 만든게 맞나? 싶을 정도로,\n먹는내내 감동을 ㅠㅜ\n어른, 아이 온가족이 모두 너무너무 맛있게 먹었어요 :)',
			'맛도, 비주얼도, 쵝오!\n요 닭볶음탕 정말 강추해요! 한번 도전해보세요~ \n우리 함께 맛있고 행복해져요♥',
			'(최대 12개까지만 입력가능해요)']

		let html = '<div id="step-'
			+ newNum
			+ '" class="pb-3 step">\
            <div class="form-label fw-bold step-text mb-0 px-1">STEP '
			+ newNum
			+ '</div>\
                <div class="d-flex px-0">\
                    <div class="col-9 px-0">\
                        <textarea name="direction" id="step-'
			+ newNum
			+ '-text" class="form-control step-textarea" onkeyup="checkByte(this, 50)" placeholder="예) '
			+ items[newNum - 1]
			+ '" rows="5"></textarea>\
                    </div>\
				<div class="step-photo-cover pointer">\
				<input type="file" class="hidden-input" onchange="imgUpload(this)"/>\
                    <div id="step-'
			+ newNum
			+ '-photo" onclick="fileUpButton(this)">\
                        <img class="border step-photo" src="/imgs/pic_none.gif">\
                    </div>\
				</div>\
                    <div class="d-flex flex-column border justify-content-between addon ms-2">\
                        <div class="d-flex flex-column">\
                            <button class="step-'
			+ newNum
			+ ' border up-btn mb-1" tabindex="-1" onclick="stepUp(this)">▲</button>\
                            <button class="step-'
			+ newNum
			+ ' border down-btn" tabindex="-1" onclick="stepDown(this)">▼</button>\
                        </div>\
                        <div>\
                            <button class="step-'
			+ newNum
			+ ' border delete-btn" tabindex="-1" onclick="stepDelete(this)">X</button>\
                        </div>\
                    </div>\
                </div>\
				<p class="warning-text m-1">*10글자 이상 작성해주세요</p>\
            </div>\
        </div>'

		target.append(html);
		
		let steps = $('#steps').find('textarea');
		for (let i=0; i<steps.length; i++){
			$(steps).eq(i).keyup(() => {
				checkValue($(steps).eq(i), "요리방법", 10);
			})
		}
		
		$("#step-" + newNum + "-text").focus(); // 새로 추가된 STEP에 포커스
	}
}

function stepUp(e) {
	// 1. 현재 총 STEP이 몇개 있는지 체크
	let stepsLength = $('#steps').children('.step').length;

	// 2. 버튼을 클릭한 STEP이 몇번째 인지 체크
	let currentID = '#' + $(e).attr('class').split(' ')[0];
	let stepNum = currentID.split('-')[1];
	let newNum = stepNum - 1;
	let targetID = '#step-' + (stepNum - 1);

	// 3. 버튼을 클릭한 STEP이 1번이 아닐 경우에는 작동 X
	if (stepNum === "1") {
	} else {
		// 4. STEP이 바뀔 경우 상위에 있던 STEP과 위치 변경
		$(targetID).insertAfter(currentID); // 요리순서가 먼저인 div를 현재 div 뒤에 삽입

		// 5. 태그 속성 변경(총 7개*2SET)
		// 1) 타겟 속성 변경
		$(targetID).children('.step-text').text('STEP ' + stepNum); // (1)STEP
		// 글자변경
		$(targetID).find('.step-textarea').attr('id',
			'step-' + stepNum + '-text'); // (2) textarea id 변경
		$(targetID).find('.step-photo').parent().attr('id',
			'step-' + stepNum + '-photo'); // (3) photo id 변경
		$(targetID).find('.up-btn').attr('class',
			'step-' + stepNum + ' border up-btn mb-1'); // (4) upbtn class
		// 변경
		$(targetID).find('.down-btn').attr('class',
			'step-' + stepNum + ' border down-btn'); // (5) downbtn class
		// 변경
		$(targetID).find('.delete-btn').attr('class',
			'step-' + stepNum + ' border delete-btn'); // (6) upbtn class
		// 변경
		$(targetID).attr('id', 'step-' + stepNum); // (7)ID변경(제일 마지막에 변경해야됨)

		// 2) 현재 선택된 속성 변경
		$(currentID).children('.step-text').text('STEP ' + newNum);
		$(currentID).find('.step-textarea').attr('id',
			'step-' + newNum + '-text'); // (2) textarea id 변경
		$(currentID).find('.step-photo').parent().attr('id',
			'step-' + newNum + '-photo'); // (3) photo id 변경
		$(currentID).find('.up-btn').attr('class',
			'step-' + newNum + ' border up-btn mb-1'); // (4) upbtn class
		// 변경
		$(currentID).find('.down-btn').attr('class',
			'step-' + newNum + ' border down-btn'); // (5) downbtn class 변경
		$(currentID).find('.delete-btn').attr('class',
			'step-' + newNum + ' border delete-btn'); // (6) upbtn class
		// 변경
		$(currentID).attr('id', 'step-' + newNum); // (7)ID변경(제일 마지막에 변경해야됨)
	}
}

function stepDown(e) {
	// 1. 현재 총 STEP이 몇개 있는지 체크
	let stepsLength = $('#steps').children('.step').length;

	// 2. 버튼을 클릭한 STEP이 몇번째 인지 체크
	let currentID = '#' + $(e).attr('class').split(' ')[0];
	let stepNum = currentID.split('-')[1];
	let newStepNum = parseInt(stepNum) + 1;
	let targetID = '#step-' + (parseInt(stepNum) + 1);

	// 3. 버튼을 클릭한 STEP이 1번이 아닐 경우에는 작동 X
	if (parseInt(stepNum) === stepsLength) {
	} else {
		// 4. STEP이 바뀔 경우 상위에 있던 STEP과 위치 변경
		// 요리순서가 먼저인 div를 현재 div 뒤에 삽입
		$(targetID).insertBefore(currentID);

		// 5. 태그 속성 변경(총 7개*2SET)
		// 1) 타겟 속성 변경
		$(targetID).children('.step-text').text('STEP ' + stepNum); // (1)STEP
		// 글자변경
		$(targetID).find('.step-textarea').attr('id',
			'step-' + stepNum + '-text'); // (2) textarea id 변경
		$(targetID).find('.step-photo').parent().attr('id',
			'step-' + stepNum + '-photo'); // (3) photo id 변경
		$(targetID).find('.up-btn').attr('class',
			'step-' + stepNum + ' border up-btn mb-1'); // (4) upbtn class
		// 변경
		$(targetID).find('.down-btn').attr('class',
			'step-' + stepNum + ' border down-btn'); // (5) downbtn class
		// 변경
		$(targetID).find('.delete-btn').attr('class',
			'step-' + stepNum + ' border delete-btn'); // (6) upbtn class
		// 변경

		// 2) 현재 선택된 속성 변경 ★순서유지 필수, 변경시 작동 에러
		$(currentID).children('.step-text').text('STEP ' + newStepNum);
		$(currentID).find('.step-textarea').attr('id',
			'step-' + newStepNum + '-text'); // (2) textarea id 변경
		$(currentID).find('.step-photo').parent().attr('id',
			'step-' + newStepNum + '-photo'); // (3) photo id 변경
		$(currentID).find('.up-btn').attr('class',
			'step-' + newStepNum + ' border up-btn mb-1'); // (4) upbtn
		// class 변경
		$(currentID).find('.down-btn').attr('class',
			'step-' + newStepNum + ' border down-btn'); // (5) downbtn class
		// 변경
		$(currentID).find('.delete-btn').attr('class',
			'step-' + newStepNum + ' border delete-btn'); // (6) upbtn
		// class 변경
		$(currentID).attr('id', 'step-' + newStepNum);
		$(targetID).attr('id', 'step-' + stepNum);
	}
}

function stepDelete(e) {
	let currentID = '#' + $(e).attr('class').split(' ')[0];
	let startNum = currentID.split('-')[1] - 1;
	let endNum = $('#steps').children('.step').length;
	let targetChildren = $('#steps').children('.step');
	//console.log("시작번호: ", startNum, "끝번호: ", endNum, "자식들: ", targetChildren)
	$(currentID).remove();
	for (let i = endNum; i > startNum; i--) {
		targetChildren.eq(i).children('.step-text').text('STEP ' + i);
		targetChildren.eq(i).find('.step-textarea').attr('id',
			'step-' + i + '-text'); // (2) textarea id 변경
		targetChildren.eq(i).find('.step-photo').parent().attr('id',
			'step-' + i + '-photo'); // (3) photo id 변경
		targetChildren.eq(i).find('.up-btn').attr('class',
			'step-' + i + ' border up-btn mb-1'); // (4) upbtn class 변경
		targetChildren.eq(i).find('.down-btn').attr('class',
			'step-' + i + ' border down-btn'); // (5) downbtn class 변경
		targetChildren.eq(i).find('.delete-btn').attr('class',
			'step-' + i + ' border delete-btn'); // (6) upbtn class 변경
		targetChildren.eq(i).attr('id', 'step-' + i);
	}
}

function fileUpButton(e) {
	let hiddenInput = $(e).parent().find('input');
	hiddenInput.click();
}

function imgUpload(e) {
	let files = $(e)[0].files;
	let filesArr = Array.prototype.slice.call(files);
	let regex = /(.*?)\/(jpg|jpeg|png|gif)$/;

	let formData = new FormData();
	formData.append("file", files[0]);

	filesArr.forEach(function (f) {
		if (!f.type.match(regex)) {
			alert("이미지 파일만 선택 가능합니다.");
			return;
		}
		sel_file = f;

		let reader = new FileReader();
		reader.onload = function (k) {
			$(e).parent().find('img').attr("src", k.target.result);
		}
		reader.readAsDataURL(f);
	});
}

function checkValue(e, text, limit, mode){
	if (mode === 1){
		if (e.val().trim().length < limit){
			alert(text + "은(는) " + limit + "글자 이상 작성해주세요");
			e.focus();
			window.scrollTo({ top: e.offset().top - 50, behavior: "smooth" });
			return false;
		} else {
			return true;
		}
	} else {
		let target;
		if (e.parent().find('.warning-text').length === 0){
			target = e.parents().find("#" + e.attr("id").replace("-text", ""));
		} else {
			target = e.parent(); 
		}
		if (e.val().trim().length > limit){
			target.find('.warning-text').text(" ");
			
		} else {
			target.find('.warning-text').text("*" + limit + "글자 이상 작성해주세요");
		}
	}
}

function register(e) {
	let formData = new FormData();

	// 음식 이름, 레시피 제목, 레시피 소개
	let food = $("input[name=food]");
	let title = $("input[name=title");
	let info = $("textarea[name=info]");
	
	if (checkValue(title, "레시피 제목", 10, 1) === false){
		return false;	
	};
	
	
	if (checkValue(info, "레시피 소개", 20, 1) === false){
		return false;	
	};
	
	formData.append("food", food.val());
	formData.append("title", title.val());
	formData.append("info", info.val());		
	
	// 카테고리, 요리정보 초기값 확인
	if (checkData() === false){
		return false;
	};
	
	// 카테고리(종류별, 재료별)
	let sort = $("#sort").val()
	let ingredientInfo = $("#ingredient").val()
	formData.append("sort", sort);
	formData.append("ingredient", ingredientInfo);
	
	// 요리정보(인원, 요리시간, 난이도)
	let serving = $("#serving").val()
	let cooktime = $("#cooktime").val()
	let difficultyLevel = $("#difficultyLevel").val()
	formData.append("serving", serving);
	formData.append("cooktime", cooktime);
	formData.append("difficultyLevel", difficultyLevel);
	
	// 메인재료(종류 + 양)
	let mainItemsIngredient = $('.main-items').find('.input1');
	let mainItemsQuantity = $('.main-items').find('.input2');

	for (let i=0; i<mainItemsIngredient.length; i++){
		if (checkValue(($(mainItemsIngredient).eq(i)), "메인재료", 1, 1) === false){
			return false;
		} else if (checkValue(($(mainItemsQuantity).eq(i)), "메인재료 수량", 1, 1) === false){
			return false;
		}
		formData.append("mainItems", $(mainItemsIngredient).eq(i).val() + '-' + $(mainItemsQuantity).eq(i).val());
	}
	
	// 양념재료(종류 + 양)
	let subItemsIngredient = $('.sub-items').find('.input1');
	let subItemsQuantity = $('.sub-items').find('.input2');
	for (let i=0; i<subItemsIngredient.length; i++){
		if (checkValue(($(subItemsIngredient).eq(i)), "양념재료", 1, 1) === false){
			return false;
		} else if (checkValue(($(subItemsQuantity).eq(i)), "양념재료 수량", 1, 1) === false){
			return false;
		}
		
		formData.append("subItems", $(subItemsIngredient).eq(i).val() + '-' + $(subItemsQuantity).eq(i).val());
	}
	
	// 조리내용(요리순서)
	let steps = $('#steps').find('textarea');
	for (let i=0; i<steps.length; i++){
		if (checkValue(($(steps).eq(i)), "요리방법", 10, 1) === false){
			return false;
		}
		formData.append("directions", $(steps).eq(i).val());
	}
	
	// 태그
	let tags = $('.tag-ul').find('input');
	if (tags.length < 1){
		alert("태그를 1개 이상 추가해주세요");
		$('#tag').focus();
		return false;
	}
	
	for (let i=0; i<tags.length; i++){
		formData.append("tags", $(tags).eq(i).val());
	}
	
	// 공개범위
	let openRange = $(".open-range-box").find("select");
	let accessibility;

	switch (openRange.val()) {
		case "비공개 저장":
			accessibility = 0;
			break;
		case "저장 및 공개":
			accessibility = 1;
			break;
		case "임시저장":
			accessibility = 2;
			break;
	}
	
	formData.append("accessibility", accessibility);
	
	for (let key of formData.keys()){
		// console.log(key, formData.get(key));
	}
	
	// 이미지 파일
	let hiddenInput = $('.hidden-input');
	for (i = 0; i < hiddenInput.length; i++) {
		if (hiddenInput[i].files[0] === undefined) {
			alert("사진이 빠진 곳이 없는지 확인해주세요!");
			return false;
		}
	}
	
	for (i = 0; i < hiddenInput.length; i++) {
		formData.append("files", hiddenInput[i].files[0]);
	}
	
	$.ajax({
		url: "/recipe/register.json",
		type: "POST",
		enctype: "multipart/form-data",
		processData: false,
		contentType: false,
		data: formData,
		success: function (response) {
			if (response.msg === "로그인 후 이용 해주세요"){
				alert(response.msg);
			} else {
				alert("레시피가 등록 되었습니다.");
			}
		},
		error: function (response) {
			alert("레시피 등록을 실패 했습니다.");
		}
	});
}


// 초기값 체크
function checkData(e) {
	let sort = $("#sort");
	let ingredientInfo = $("#ingredient");
	let serving = $("#serving");
	let cooktime = $("#cooktime");
	let difficultyLevel = $("#difficultyLevel");
	
	let checkList = new Array();
	checkList.push(sort);
	checkList.push(ingredientInfo);
	checkList.push(serving);
	checkList.push(cooktime);
	checkList.push(difficultyLevel);
	
	let textList = new Array("종류별", "재료별", "인원", "요리시간", "난이도");
	
	for (object of checkList){
		if(textList.includes(object.val())){
			alert(object.val() + " 선택되지 않았습니다.");
			object.focus();

			return false;
		}
	}
	return true;
}

// 요리순서 글자수 제한로직
function checkByte(e, maxByte) {
	let str = e.value; // textarea에 입력된 text값
	let str_len = str.length; // 입력된 textarea의 길이
	
	if(str_len>maxByte){
		alert(maxByte+"자 이상 입력하실 수 없습니다."); // 최대 byte 이상 입력시 경고창
		str = str.substr(0,maxByte); // 입력한 문자열 값중 0부터 마지막 바이트까지의 값을 가져옴
		str.value = str;
	}
}