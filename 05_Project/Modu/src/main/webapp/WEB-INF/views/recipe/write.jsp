<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<!-- 공통 부분 END -->
<link href="/css/recipe-write.css" rel="stylesheet">
<script src="/js/recipe-write.js"></script>

<title>모두의 식탁 - 레시피 등록</title>

</head>
<body>

	<div id="page" class="hfeed site">
		<!-- start page wrapper -->

		<%@ include file="/WEB-INF/views/common/menu.jsp"%>

		<div class="head-title py-4 m-0">
			<div class="container">
				<p class="page-title m-0 fs-2">RECIPE</p>
			</div>
			<!-- end container -->
		</div>
		<!-- end head-title -->

		<div id="main" class="d-flex justify-content-center my-3">
			<div
				class="main-container row d-flex justify-content-center m-0 py-4">
				<div class="col-md-10 main-box px-0">
					<div class="pt-4 form-bg top-round"></div>
					<div class="p-4 p-title form-bg h3 mb-0 text-center">#레시피 등록</div>
					<div class="cooking-information form-bg p-4">
						<div class="row">
							<div class="main-photo col-4">
								<input type="hidden" name="food_photo">
								<div class="text-center">
									<img class="w-100 rounded-3" src="/imgs/no-image.jpg">
								</div>
							</div>
							<div class="main-intro col-8">
								<div class="cooking-title pb-3">
									<label for="food" class="form-label mb-1 form-title">음식
										이름</label> <input class="form-control" type="text" name="food"
										placeholder="예) 닭볶음탕">
								</div>
								<div class="cooking-title pb-3">
									<label for="title" class="form-label mb-1 form-title">레시피
										제목</label> <input class="form-control" type="text" name="title"
										placeholder="예) 닭볶음탕 황금 레시피">
								</div>
							</div>
						</div>
					</div>
					<!-- end cooking-information 레시피정보 -->
					<div class="cooking-introduce form-bg px-4">
						<label for="info" class="form-label mb-1 form-title">레시피
							소개</label>
						<textarea class="form-control" name="info" rows=5
							placeholder="레시피에 대한 소개글을 써주세요"></textarea>
					</div>
					<!-- end cooking-introduce 레시피소개 -->
					<div class="category p-4 form-bg">
						<div class="form-content row align-items-center">
							<label class="form-label mb-1 pe-3 form-title">카테고리</label> <select
								class="form-select mx-2" name="sort" id="sort" text="종류별">
								<option selected>종류별</option>
								<option value="1">밑반찬</option>
								<option value="2">메인반찬</option>
								<option value="3">국/탕/찌개</option>
								<option value="4">디저트</option>
								<option value="5">면/만두</option>
								<option value="6">밥/죽/떡</option>
								<option value="7">샐러드</option>
								<option value="8">스프</option>
								<option value="9">기타</option>
							</select> <select class="form-select me-2" name="ingredient" id="ingr"
								text="재료별">
								<option selected>재료별</option>
								<option value="1">소고기</option>
								<option value="2">돼지고기</option>
								<option value="3">닭고기</option>
								<option value="4">양고기</option>
								<option value="5">오리고기</option>
								<option value="6">채소류</option>
								<option value="7">해물류</option>
								<option value="8">기타</option>
							</select>
						</div>
					</div>
					<!-- end category 카테고리 -->
					<div class="recipe-info form-bg p-4">
						<div class="d-flex row align-items-center">
							<label class="form-label mb-1 pe-3 form-title">요리정보</label> <select
								class="form-select mx-2" name="serving" id="serving">
								<option selected>인원</option>
								<option value="1">1인분</option>
								<option value="2">2인분</option>
								<option value="3">3인분</option>
								<option value="4">4인분</option>
								<option value="5">5인분</option>
								<option value="6">6인분이상</option>
							</select>
							<!--                            <label class="form-label ms-2 mb-0 p-1">시간</label>-->
							<select class="form-select me-2" name="cook_time" id="cook-time"
								text="요리시간">
								<option selected>요리시간</option>
								<option value="1">5분이내</option>
								<option value="2">10분이내</option>
								<option value="3">15분이내</option>
								<option value="4">20분이내</option>
								<option value="5">30분이내</option>
								<option value="6">60분이내</option>
								<option value="7">90분이내</option>
								<option value="8">2시간이내</option>
								<option value="9">2시간이상</option>
							</select>
							<!--                            <label class="form-label ms-2 mb-0 p-1">난이도</label>-->
							<select class="form-select" name="difficulty_level"
								id="difficulty-level" text="난이도">
								<option selected>난이도</option>
								<option value="1">아무나</option>
								<option value="2">초급</option>
								<option value="3">중급</option>
								<option value="4">고급</option>
								<option value="5">신의경지</option>
							</select>
						</div>
					</div>
					<!-- end recipe-info 요리정보 -->
					<div class="ingredient form-bg py-4 px-2">
						<div class="d-flex ingredient-cover">
							<div id="main-ingredient" class="col-md-6">
								<div class="row d-flex justify-content-center">
									<label class="form-label mb-1 px-3 form-title">메인재료</label>
									<div class="main-items px-4">
										<div class="row d-flex align-items-center mb-2 item"
											id="main-1">
											<input type="text" class="ingredient-1 form-control input1"
												placeholder="예) 닭"> <input type="text"
												class="quantity-1 form-control input2 mx-2"
												placeholder="예) 1마리">
											<button class="minus main-1" onclick="deleteItem(this)"></button>
										</div>
										<div class="row d-flex align-items-center mb-2 item"
											id="main-2">
											<input type="text" class="ingredient-2 form-control input1"
												placeholder="예) 감자"> <input type="text"
												class="quantity-2 form-control input2 mx-2"
												placeholder="예) 1개">
											<button class="minus main-2" onclick="deleteItem(this)"></button>
										</div>
										<div class="row d-flex align-items-center mb-2 item"
											id="main-3">
											<input type="text" class="ingredient-3 form-control input1"
												placeholder="예) 당근"> <input type="text"
												class="quantity-3 form-control input2 mx-2"
												placeholder="예) 1개">
											<button class="minus main-3" onclick="deleteItem(this)"></button>
										</div>
									</div>
									<div>
										<button class="btn border mt-2 p-btn plus" id="main-add"
											onclick="addItem(this)">&nbsp&nbsp추가</button>
									</div>
								</div>
							</div>
							<div id="seasoning-ingredient" class="col-md-6">
								<div class="row d-flex justify-content-center">
									<label class="form-label mb-1 px-3 form-title">양념재료</label>
									<div class="sub-items px-4">
										<div class="row d-flex align-items-center mb-2 item"
											id="sub-1">
											<input type="text" class="ingredient-1 form-control input1"
												placeholder="예) 설탕"> <input type="text"
												class="quantity-1 form-control input2 mx-2"
												placeholder="예) 3T">
											<button class="minus sub-1" onclick="deleteItem(this)"></button>
										</div>
										<div class="row d-flex align-items-center mb-2 item"
											id="sub-2">
											<input type="text" class="ingredient-2 form-control input1"
												placeholder="예) 마늘"> <input type="text"
												class="quantity-2 form-control input2 mx-2"
												placeholder="예) 1T">
											<button class="minus sub-2" onclick="deleteItem(this)"></button>
										</div>
										<div class="row d-flex align-items-center mb-2 item"
											id="sub-3">
											<input type="text" class="ingredient-3 form-control input1"
												placeholder="예) 간장"> <input type="text"
												class="quantity-3 form-control input2 mx-2"
												placeholder="예) 3/4컵">
											<button class="minus sub-3" onclick="deleteItem(this)"></button>
										</div>
									</div>
									<div>
										<button class="btn border mt-2 p-btn plus" id="sub-add"
											onclick="addItem(this)">&nbsp&nbsp추가</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- end ingredient 재료 -->
					<div class="cooking-step p-4 form-bg" id="cooking-step">
						<label class="form-label mb-1 form-title">요리순서</label>
						<div class="row" id="steps">
							<div id="step-1" class="pb-3 step">
								<div class="form-label fw-bold step-text mb-0 px-1">STEP 1</div>
								<div class="d-flex px-0">
									<div class="col-9 px-0">
										<textarea name="direction" id="step-1-text"
											class="form-control step-textarea"
											placeholder="예) 닭이 잠길정도로 물을 넣고 손질한 닭을 끓여주세요" rows="5"></textarea>
									</div>
									<div id="step-1-photo col-3">
										<img class="border step-photo" src="/imgs/pic_none.gif">
									</div>
									<div
										class="d-flex flex-column border justify-content-between addon ms-2">
										<div>
											<button class="step-1 border up-btn mb-1"
												onclick="stepUp(this)">▲</button>
											<button class="step-1 border down-btn"
												onclick="stepDown(this)">▼</button>
										</div>
										<div>
											<button class="step-1 border delete-btn"
												onclick="stepDelete(this)">X</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div>
							<button class="btn border mt-2 p-btn plus"
								onclick="addStep(this)">&nbsp&nbsp추가</button>
						</div>
					</div>
					<!-- end cooking-step 요리순서 -->
					<div class="recipe-tag form-bg p-4 d-flex">
						<div class="form-label mb-1 form-title recipe-tag-title">태그</div>
						<div class="recipe-tag-box">
							<input name="tag" id="tag" placeholder="예) 소고기, 미역국 (최대 5개)">
							<ul class="tag-ul d-flex p-1">
							</ul>
						</div>
					</div>
					<!-- end recipe-tag 태그 -->

					<div
						class="accessibility form-bg p-4 border-top d-flex justify-content-center">
						<div class="mx-1">
							<button type="button" onclick="doSubmit('secret_save')"
								class="secret-save btn btn-info">비공개저장</button>
							<button type="button" onclick="doSubmit('open_save')"
								class="open-save btn btn-warning mx-3">저장 및 공개</button>
						</div>
						<div class="mx-1">
							<button type="button" onclick="doSubmit('temp_save')"
								class="temp-save btn btn-secondary me-3">임시저장</button>
							<button type="button" onclick="history.back();"
								class="cancel btn btn-danger">취소</button>
						</div>
					</div>
					<!-- end accessbility -->
				</div>
			</div>
			<!-- end main container-->
		</div>
		<!-- end main-->
		<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
	</div>
	<!-- end #page hfeed site -->
</body>
</html>