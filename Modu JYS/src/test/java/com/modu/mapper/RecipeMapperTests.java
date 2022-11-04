package com.modu.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.modu.domain.recipe.Direction;
import com.modu.domain.recipe.Ingredient;
import com.modu.domain.recipe.Recipe;
import com.modu.domain.recipe.RecipeList;
import com.modu.domain.recipe.RecipeTag;

import lombok.extern.log4j.Log4j;

 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class RecipeMapperTests {
	@Autowired
	RecipeMapper recipeMapper;
	@Autowired
	MemberMapper memberMapper;
	
	/*@Test //테스트 완료
	public void testSelectRecipeId() {
		String email = "test1@test.com";
		long id = recipeMapper.selectRecipeId(email);
		System.out.println("#######"+id);
	}*/
	
	/*@Test //테스트 완료
	public void testInsertRecipe(){
		Recipe recipe = new Recipe();
		Ingredient ingredient = new Ingredient();
		Direction direction = new Direction();
		RecipeTag recipeTag = new RecipeTag();
		List<String> members = memberMapper.selectEmails(50);
		
		for(String member:members) {
			String email = member;
			String nickname = memberMapper.selectNickname(email);
			String info = "[레시피소개]"+nickname+"님의 레시피\r\n" + 
						  "집에서 간편하게 먹을 수 있는 첫번째 요리\r\n" + 
						  "\"가자미 카레\" 지금 만나러 가보겠습니다.";
			String title = "[레시피제목]"+nickname+"님의 레시피";
			String food = "[음식이름]";
			String foodPhoto = "[레시피 사진]";
			String sort = "밥/죽/떡"; 
			String recipeIngredient = "해물류";
			int serving = 2;
			String cookTime = "20분이내";
			String difficultyLevel = "아무나";
	
			recipe.setMEmail(email);
			recipe.setMNickname(nickname);
			recipe.setProfileImg(memberMapper.selectProfileImg(email));
			recipe.setTitle(title);
			recipe.setInfo(info);
			recipe.setFood(food);
			recipe.setFoodPhoto(foodPhoto);
			recipe.setSort(sort);
			recipe.setIngredient(recipeIngredient);
			recipe.setServing(serving);
			recipe.setCookTime(cookTime);
			recipe.setDifficultyLevel(difficultyLevel);
			recipeMapper.insertRecipe(recipe);
			
			long rId = recipeMapper.selectRecipeId(email);
			int ingredientType0 = 0;
			String ingrName1 = "가자미";
			String quantity1 = "1마리";
			int ingredientType1 = 1;
			String ingrName2 = "카레가루";
			String quantity2 = "3큰술";
			
			ingredient.setRId(rId);
			ingredient.setIngredientType(ingredientType0);
			ingredient.setIngredient(ingrName1);
			ingredient.setQuantity(quantity1);
			recipeMapper.insertIngredient(ingredient);
			ingredient.setRId(rId);
			ingredient.setIngredientType(ingredientType1);
			ingredient.setIngredient(ingrName2);
			ingredient.setQuantity(quantity2);
			recipeMapper.insertIngredient(ingredient);		
			 
			direction.setRId(rId);
			direction.setStep(1);
			direction.setDirection("물을 넣는다.");
			direction.setOriginalFile("사진1");
			direction.setSaveFile("저장사진1");
			recipeMapper.insertDirection(direction);
			direction.setStep(2);
			direction.setDirection("가자미를 넣는다.");
			direction.setOriginalFile("사진2");
			direction.setSaveFile("저장사진2");
			recipeMapper.insertDirection(direction);
			direction.setStep(3);
			direction.setDirection("카레를 가루 넣는다.");
			direction.setOriginalFile("사진3");
			direction.setSaveFile("저장사진3");
			recipeMapper.insertDirection(direction);
			
			recipeTag.setRId(rId);
			recipeTag.setTag("카레");
			recipeMapper.insertTag(recipeTag);
			recipeTag.setRId(rId);
			recipeTag.setTag("가자미");
			recipeMapper.insertTag(recipeTag);
			recipeTag.setRId(rId);
			recipeTag.setTag("독특");
			recipeMapper.insertTag(recipeTag);
		}
	}*/
	/*@Test //테스트 완료
	public void testUpdateRecipe(){
		Recipe recipe = new Recipe();
		
		String email = "sd54f@hanmail.net";
		String nickname = memberMapper.selectNickname(email);
		String info = "[레시피소개:수정됨]"+nickname+"님의 레시피\r\n" + 
					  "집에서 간편하게 먹을 수 있는 첫번째 요리\r\n" + 
					  "\"감자 카레\" 지금 만나러 가보겠습니다.";
		String title = "[레시피제목:수정됨]"+nickname+"님의 레시피";
		String food = "[음식이름]";
		String foodPhoto = "[레시피 사진]";
		String sort = "밥/죽/떡"; 
		String recipeIngredient = "해물류";
		int serving = 3;
		String cookTime = "30분이내";
		String difficultyLevel = "아무나";
		long id = 1;
		
		recipe.setId(id);
		recipe.setTitle(title);
		recipe.setInfo(info);
		recipe.setFood(food);
		recipe.setFoodPhoto(foodPhoto);
		recipe.setSort(sort);
		recipe.setIngredient(recipeIngredient);
		recipe.setServing(serving);
		recipe.setCookTime(cookTime);
		recipe.setDifficultyLevel(difficultyLevel);
		recipeMapper.updateRecipe(recipe);
		
	}*/
	
	/*@Test //테스트 완료
	public void testSelectRecipeListBy() {
		long beginRow = 5;
		long endRow = 10;
		List<RecipeList> recipes = recipeMapper.selectRecipeListBy(beginRow, endRow);
		for(RecipeList recipe:recipes) {
			System.out.println(recipe);
		}
	}*/
	
	/*@Test
	public void testSelectRecipeListByType() {
		long beginRow = 5;
		long endRow = 10;
		String sort = "밥";
		String ingredient = "해물류";
		List<RecipeList> recipes = recipeMapper.selectRecipeListByType(sort, ingredient, beginRow, endRow);
		for(RecipeList recipe:recipes) {
			System.out.println(recipe);
		}
	}*/
	
	//###############샘플데이터 작성용
	/*"치킨", "닭볶음탕", "가지볶음", "관자구이", "바지락 토마토 파스타", "김치찌개", "된장찌개", "국밥", "통삽겹 구이", "라따뚜이", "시금치 무침", "고르곤졸라 피자", "간장계란밥", "사골국", "곰탕", "츄러스"
	  "채나물 무침", "감자조림", "요플레", "감귤주스", "포도주스", "스테이크", "고등어 조림", "삼치구이", "오징어볶음", "소유 라멘", "돈코츠 라멘", "콩나물 라면", "감자만두", "고기만두", "군만두", "훈제오리 야채볶음", "훈제오리 샐러드",
	  "옥수수 스프", "토마토 스튜", "램스테이크", "양고기 케밥", "캐비어", "분자요리"
	*/
	/*@Test
	public void testMakeRecipeSample() {
		ArrayList<String> foods = new ArrayList<String>();
		ArrayList<String> sorts = new ArrayList<String>();
		ArrayList<String> ingredients = new ArrayList<String>();
		ArrayList<Integer> servings = new ArrayList<Integer>();
		ArrayList<String> cookTimes = new ArrayList<String>();
		ArrayList<String> difficultyLevels = new ArrayList<String>();
		

		String[] foodList = {"캐비어", "분자요리"};
		
		//"밑반찬", "메인반찬", "국/탕/찌개", "디저트", "면/만두", "샐러드", "스프", "기타"
		String[] sortList = {"기타"} ;
		//"소고기", "돼지고기", "닭고기", "양고기", "오리고기", "채소류", "해물류", "기타"
		String[] ingredientList = {"기타"};
		//1, 2, 3, 4, 5, 6
		Integer[] servingList = {1};
		//"5분이내", "10분이내", "15분이내", "20분이내", "30분이내", "60분이내", "90분이내", "2시간이내", "2시간이상"
		String[] cookTimeList = {"2시간이내", "2시간이상"};
		//"아무나", "초급", "중급", "고급", "신의경지"
		String[] difficultyLevelList = {"고급", "신의경지"};
		
		for(int i=0; i<foodList.length; i++) {
			foods.add(foodList[i]);
		}
		for(int i=0; i<sortList.length; i++) {
			sorts.add(sortList[i]);
		}
		for(int i=0; i<ingredientList.length; i++) {
			ingredients.add(ingredientList[i]);
		}
		for(int i=0; i<servingList.length; i++) {
			servings.add(servingList[i]);
		}
		for(int i=0; i<cookTimeList.length; i++) {
			cookTimes.add(cookTimeList[i]);
		}
		for(int i=0; i<difficultyLevelList.length; i++) {
			difficultyLevels.add(difficultyLevelList[i]);
		}
		
		List<String> members = memberMapper.selectEmails(4);
		for(String email:members) {
			String nickname = memberMapper.selectNickname(email);
			String profileImg = memberMapper.selectProfileImg(email);
			int access = 0;
			for(String food:foods) {
				String title = "["+nickname+"]님의 "+food+" 레시피";
				String info = "["+nickname+"]님이 만든 "+food+" 레시피입니다. 진정한 음식이란...";
				for(String sort:sorts) {					
					for(String ingredient:ingredients) {						
						for(int serving:servings) {							
							for(String cookTime:cookTimes) {								
								for(String difficultyLevel:difficultyLevels) {
									System.out.println("INSERT INTO RECIPE (ID,M_EMAIL,M_NICKNAME,PROFILE_IMG,TITLE,INFO,FOOD,FOOD_PHOTO,SORT,INGREDIENT,SERVING,COOK_TIME,DIFFICULTY_LEVEL,ACCESSIBILITY) VALUES(RECIPE_SEQ.nextval,'"+email+"','"+nickname+"','"+profileImg+"','"+title+"','"+info+"','"+food+"','[대표사진]음식대표사진.png','"+sort+"','"+ingredient+"',"+serving+",'"+cookTime+"','"+difficultyLevel+"',"+access+");");
								}
							}
						}
					}
				}
			}
		}
	}*/
	
	/*@Test
	public void testMakeIngredientSample() {
				
		for(int i=531; i<=630; i++) {
			long rId = i;
			int ingredientType = 0;
			String ingredient = "재료1";
			String quantity = "정량1";
			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
			ingredient = "재료2";
			quantity = "정량2";
			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
			ingredient = "재료3";
			quantity = "정량3";
			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
			ingredient = "재료4";
			quantity = "정량4";
			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
			ingredient = "재료5";
			quantity = "정량5";
			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
			ingredient = "재료6";
			quantity = "정량6";
			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "재료7";
//			quantity = "정량7";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "재료8";
//			quantity = "정량8";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "재료9";
//			quantity = "정량9";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "재료10";
//			quantity = "정량10";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
			
			
			ingredientType = 1;
			ingredient = "양념1";
			quantity = "정량1";
			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
			ingredient = "양념2";
			quantity = "정량2";
			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
			ingredient = "양념3";
			quantity = "정량3";
			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "양념4";
//			quantity = "정량4";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "양념5";
//			quantity = "정량5";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "양념6";
//			quantity = "정량6";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "양념7";
//			quantity = "정량7";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "양념8";
//			quantity = "정량8";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "양념9";
//			quantity = "정량9";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "양념10";
//			quantity = "정량10";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
			
		}
	}*/
	
	/*@Test
	public void testMakeDirectionSample() {
		for(int i=431; i<=630; i++) {
			long rId = i;
			int step = 1;
			String direction = "조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 1.";
			String originalFile = "조리과정 사진(원본명)1.png";
			String saveFile = "조리과정 사진"+i+"(저장명).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 2;
			direction = "조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 2.";
			originalFile = "조리과정 사진(원본명)2.png";
			saveFile = "조리과정 사진"+i+"(저장명).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 3;
			direction = "조리과정 .조리과정 .조리과정 .조리과정 .조리과정 3.";
			originalFile = "조리과정 사진(원본명)3.png";
			saveFile = "조리과정 사진"+i+"(저장명).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 4;
			direction = "조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 4.";
			originalFile = "조리과정 사진(원본명)4.png";
			saveFile = "조리과정 사진"+i+"(저장명).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 5;
			direction = "조리과정 .조리과정 .조리과정 .조리과정 .조리과정 5.";
			originalFile = "조리과정 사진(원본명)5.png";
			saveFile = "조리과정 사진"+i+"(저장명).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 6;
			direction = "조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 6.";
			originalFile = "조리과정 사진(원본명)6.png";
			saveFile = "조리과정 사진"+i+"(저장명).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 7;
			direction = "조리과정 .조리과정 .조리과정 .조리과정 .조리과정 7.";
			originalFile = "조리과정 사진(원본명)7.png";
			saveFile = "조리과정 사진"+i+"(저장명).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 8;
			direction = "조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 8.";
			originalFile = "조리과정 사진(원본명)8.png";
			saveFile = "조리과정 사진"+i+"(저장명).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 9;
			direction = "조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 9.";
			originalFile = "조리과정 사진(원본명)9.png";
			saveFile = "조리과정 사진"+i+"(저장명).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 10;
			direction = "조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 10.";
			originalFile = "조리과정 사진(원본명)510.png";
			saveFile = "조리과정 사진"+i+"(저장명).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 11;
			direction = "조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 11.";
			originalFile = "조리과정 사진(원본명)11.png";
			saveFile = "조리과정 사진"+i+"(저장명).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 12;
			direction = "조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 .조리과정 12.";
			originalFile = "조리과정 사진(원본명)12.png";
			saveFile = "조리과정 사진"+i+"(저장명).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 }		 
	}*/
	
	/*@Test
	public void testMakeRecipeTagSample() {
		
		for(int i=531; i<=630; i++) {
			long rId = i;
			for(int x=1; x<=5; x++) {
				String tag = "태그"+x;
				System.out.println("INSERT INTO RECIPE_TAG VALUES(TAG_SEQ.nextval, '"+rId+"', '"+tag+"');");
			}
	  	}
	}*/
}
