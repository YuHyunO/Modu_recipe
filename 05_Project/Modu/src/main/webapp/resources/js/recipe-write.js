$(function() {
	let tag = $('#tag');
	$('#tag').keyup(
					function(e) {
						if (e.keyCode == '13' || e.keyCode == '188') {// 엔터 또는
							// 쉼표
							// 입력시
							// console.log('enter');
							// console.log(tag.val());
							let colors = [ '#3c1cbf', '#208b3c', '#0d6efd',
									'#212529', '#5bc0de', '#d65bde' ]
							let randint = Math.floor(Math.random()
									* colors.length);
							let newNum = $('.tag-ul').children('li').length + 1;
							let text = tag.val().split(',')[0];
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
					});
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
			items = [ '소고기', '돼지고기', '닭고기', '양고기', '대파', '쪽파', '양파', '감자',
					'고구마', '당근' ];
			quantities = [ '100g', '300g', '1마리', '200g', '1쪽', '1/2쪽', '1/4개',
					'150g', '100g', '1개' ];
		} else if (btnID === 'sub-add') {
			items = [ '참기름', '국간장', '고춧가루', '다진마늘', '액젓', '식초', '설탕', '통깨',
					'굴소스', '물' ];
			quantities = [ '2스푼', '1스푼', '1스푼', '1스푼', '1큰술', '1큰술', '1.5큰술',
					'적당량', '1/2큰술', '100ml' ];
		}
		html = '<div class="row d-flex align-items-center mb-2 item" id="'
				+ text + '-' + newNum
				+ '">\
            <input type="text" class="ingredient-'
				+ newNum + ' form-control input1" placeholder="예) '
				+ items[newNum - 1]
				+ '">\
            <input type="text" class="quantity-'
				+ newNum + ' form-control input2 mx-2" placeholder="예) '
				+ quantities[newNum - 1]
				+ '">\
            <button class="minus ' + text + '-'
				+ newNum
				+ '" onclick="deleteItem(this)"></button>\
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
	} else if (targetID.split('-')[0] === 'sub') {
		text = 'sub';
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
				'(최대 12개까지만 입력가능해요)' ]

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
				+ '-text" class="form-control step-textarea" placeholder="예) '
				+ items[newNum - 1]
				+ '" rows="5"></textarea>\
                    </div>\
                    <div id="step-'
				+ newNum
				+ '-photo col-3">\
                        <img class="border step-photo" src="/imgs/pic_none.gif">\
                    </div>\
                    <div class="d-flex flex-column border justify-content-between addon ms-2">\
                        <div>\
                            <button class="step-'
				+ newNum
				+ ' border up-btn mb-1" onclick="stepUp(this)">▲</button>\
                            <button class="step-'
				+ newNum
				+ ' border down-btn" onclick="stepDown(this)">▼</button>\
                        </div>\
                        <div>\
                            <button class="step-'
				+ newNum
				+ ' border delete-btn" onclick="stepDelete(this)">X</button>\
                        </div>\
                    </div>\
                </div>\
            </div>\
        </div>'

		target.append(html);
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
	console.log("시작번호: ", startNum, "끝번호: ", endNum, "자식들: ", targetChildren)
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

function doSubmit(e) {
	var food = $("input[name=food]").val()
	// console.log(food.val());
	var title = $("input[name=title").val()
	// console.log(title.val());
	var info = $("textarea[name=info]").val()
	// console.log(info.val());
	var sort = $("#sort").val()
	// console.log(sort.val());
	var ingredientInfo = $("#ingredient").val()
	// console.log(ingr.val());
	var serving = $("#serving").val()
	// console.log(serving.val());
	var cooktime = $("#cooktime").val()
	// console.log(cooktime.val());
	var difficultyLevel = $("#difficultyLevel").val()
	// console.log(difficultyLevel.val());
	// ingredientType을 조건문으로 지정해서 for문을 돌려서 반복문을 작성하면 될것 같다.
	let ingredientTypeSub;
	let ingredientTypeMain;
	let mingredient = $('.main-items').find('.ingredient-1').val();
	let mquantity = $('.main-items').find('.quantity-1').val();

	if (mingredient != null && mquantity != null) {
		ingredientTypeMain = 0;
	}

	let singredient = $('.sub-items').find('.ingredient-1').val();
	let squantity = $('.sub-items').find('.quantity-1').val();

	if (singredient != null && squantity != null) {
		ingredientTypeSub = 1;
	}
	// console.log("재료타입"+ingredientTypeS+ingredientTypeM);
	// console.log("메인재료1: " + mingredient);
	// console.log("메인양념1: " + mquantity);
	// console.log("양념재료1: " + singredient);
	// console.log("서브양념1: " + squantity);

	var direction = $("textarea[name=direction").val();
	// console.log(direction.val());
	var tags = $("#tag-1").val();
	// console.log(tags.val());
	// 하나씩 찍어 봣으니 ajax를 통해 넘겨야된다. ajax 공부해야댐.

	let btnClass = $(e).attr("class").split("-")[0];
	let accessibility;

	switch (btnClass) {
	case 'secret':
		accessibility = 0;
		break;
	case 'open':
		accessibility = 1;
		break;
	case 'temp':
		accessibility = 2;
		break;
	}

	console.log("공개범위: " + accessibility);
	
	let data = {"food":food,"title":title,"info":info,"sort":sort,"ingredientInfo":ingredientInfo,"serving":serving,
			  "cookTime":cooktime,"difficultyLevel":difficultyLevel,"ingredientM":mingredient,"quantityM":mquantity,
			  "ingredientS":singredient,"quantityS":squantity,"direction":direction,"tag":tags,
			  "ingredientTypeSub":ingredientTypeSub,
			  "ingredientTypeMain":ingredientTypeMain, "accessibility":accessibility};
	 
	$.ajax({
		url : "../recipe/write.do",
		type : "POST",
		data : data,
		dataType : "text",
		success : function(data) {
			if (!data) {
				console.log("존재하지 않는 data");
				return false;
			}
		},
		error : function(error) {
			alert("err" + error);
		}
	})
}

$(function(){
	$("#food_photo").on("change", function(){
		let files = $('#food_photo')[0].files;
		console.log(files);
		let formData = new FormData();
		formData.append("image", files[0]);
		for (var key of formData.keys()) {
			console.log(key);
		}
		for (var value of formData.values()) {
		    console.log(value);
		}
	});
});

function fileUpload(){
	console.log($('#food_photo').length);
	console.log($('#food_photo').attr('name'));
	$('#food_photo').click();
}
