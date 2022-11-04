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
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testSelectRecipeId() {
		String email = "test1@test.com";
		long id = recipeMapper.selectRecipeId(email);
		System.out.println("#######"+id);
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testInsertRecipe(){
		Recipe recipe = new Recipe();
		Ingredient ingredient = new Ingredient();
		Direction direction = new Direction();
		RecipeTag recipeTag = new RecipeTag();
		List<String> members = memberMapper.selectEmails(50);
		
		for(String member:members) {
			String email = member;
			String nickname = memberMapper.selectNickname(email);
			String info = "[�����ǼҰ�]"+nickname+"���� ������\r\n" + 
						  "������ �����ϰ� ���� �� �ִ� ù��° �丮\r\n" + 
						  "\"���ڹ� ī��\" ���� ������ �����ڽ��ϴ�.";
			String title = "[����������]"+nickname+"���� ������";
			String food = "[�����̸�]";
			String foodPhoto = "[������ ����]";
			String sort = "��/��/��"; 
			String recipeIngredient = "�ع���";
			int serving = 2;
			String cookTime = "20���̳�";
			String difficultyLevel = "�ƹ���";
	
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
			String ingrName1 = "���ڹ�";
			String quantity1 = "1����";
			int ingredientType1 = 1;
			String ingrName2 = "ī������";
			String quantity2 = "3ū��";
			
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
			direction.setDirection("���� �ִ´�.");
			direction.setOriginalFile("����1");
			direction.setSaveFile("�������1");
			recipeMapper.insertDirection(direction);
			direction.setStep(2);
			direction.setDirection("���ڹ̸� �ִ´�.");
			direction.setOriginalFile("����2");
			direction.setSaveFile("�������2");
			recipeMapper.insertDirection(direction);
			direction.setStep(3);
			direction.setDirection("ī���� ���� �ִ´�.");
			direction.setOriginalFile("����3");
			direction.setSaveFile("�������3");
			recipeMapper.insertDirection(direction);
			
			recipeTag.setRId(rId);
			recipeTag.setTag("ī��");
			recipeMapper.insertTag(recipeTag);
			recipeTag.setRId(rId);
			recipeTag.setTag("���ڹ�");
			recipeMapper.insertTag(recipeTag);
			recipeTag.setRId(rId);
			recipeTag.setTag("��Ư");
			recipeMapper.insertTag(recipeTag);
		}
	}*/
	/*@Test //�׽�Ʈ �Ϸ�
	public void testUpdateRecipe(){
		Recipe recipe = new Recipe();
		
		String email = "sd54f@hanmail.net";
		String nickname = memberMapper.selectNickname(email);
		String info = "[�����ǼҰ�:������]"+nickname+"���� ������\r\n" + 
					  "������ �����ϰ� ���� �� �ִ� ù��° �丮\r\n" + 
					  "\"���� ī��\" ���� ������ �����ڽ��ϴ�.";
		String title = "[����������:������]"+nickname+"���� ������";
		String food = "[�����̸�]";
		String foodPhoto = "[������ ����]";
		String sort = "��/��/��"; 
		String recipeIngredient = "�ع���";
		int serving = 3;
		String cookTime = "30���̳�";
		String difficultyLevel = "�ƹ���";
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
	
	/*@Test //�׽�Ʈ �Ϸ�
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
		String sort = "��";
		String ingredient = "�ع���";
		List<RecipeList> recipes = recipeMapper.selectRecipeListByType(sort, ingredient, beginRow, endRow);
		for(RecipeList recipe:recipes) {
			System.out.println(recipe);
		}
	}*/
	
	//###############���õ����� �ۼ���
	/*"ġŲ", "�ߺ�����", "��������", "���ڱ���", "������ �丶�� �Ľ�Ÿ", "��ġ�", "�����", "����", "���� ����", "�������", "�ñ�ġ ��ħ", "�������� ����", "��������", "���", "����", "�򷯽�"
	  "ä���� ��ħ", "��������", "���÷�", "�����ֽ�", "�����ֽ�", "������ũ", "���� ����", "��ġ����", "��¡���", "���� ���", "������ ���", "�ᳪ�� ���", "���ڸ���", "��⸸��", "������", "�������� ��ä����", "�������� ������",
	  "������ ����", "�丶�� ��Ʃ", "��������ũ", "���� �ɹ�", "ĳ���", "���ڿ丮"
	*/
	/*@Test
	public void testMakeRecipeSample() {
		ArrayList<String> foods = new ArrayList<String>();
		ArrayList<String> sorts = new ArrayList<String>();
		ArrayList<String> ingredients = new ArrayList<String>();
		ArrayList<Integer> servings = new ArrayList<Integer>();
		ArrayList<String> cookTimes = new ArrayList<String>();
		ArrayList<String> difficultyLevels = new ArrayList<String>();
		

		String[] foodList = {"ĳ���", "���ڿ丮"};
		
		//"�ع���", "���ι���", "��/��/�", "����Ʈ", "��/����", "������", "����", "��Ÿ"
		String[] sortList = {"��Ÿ"} ;
		//"�Ұ��", "�������", "�߰��", "����", "�������", "ä�ҷ�", "�ع���", "��Ÿ"
		String[] ingredientList = {"��Ÿ"};
		//1, 2, 3, 4, 5, 6
		Integer[] servingList = {1};
		//"5���̳�", "10���̳�", "15���̳�", "20���̳�", "30���̳�", "60���̳�", "90���̳�", "2�ð��̳�", "2�ð��̻�"
		String[] cookTimeList = {"2�ð��̳�", "2�ð��̻�"};
		//"�ƹ���", "�ʱ�", "�߱�", "���", "���ǰ���"
		String[] difficultyLevelList = {"���", "���ǰ���"};
		
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
				String title = "["+nickname+"]���� "+food+" ������";
				String info = "["+nickname+"]���� ���� "+food+" �������Դϴ�. ������ �����̶�...";
				for(String sort:sorts) {					
					for(String ingredient:ingredients) {						
						for(int serving:servings) {							
							for(String cookTime:cookTimes) {								
								for(String difficultyLevel:difficultyLevels) {
									System.out.println("INSERT INTO RECIPE (ID,M_EMAIL,M_NICKNAME,PROFILE_IMG,TITLE,INFO,FOOD,FOOD_PHOTO,SORT,INGREDIENT,SERVING,COOK_TIME,DIFFICULTY_LEVEL,ACCESSIBILITY) VALUES(RECIPE_SEQ.nextval,'"+email+"','"+nickname+"','"+profileImg+"','"+title+"','"+info+"','"+food+"','[��ǥ����]���Ĵ�ǥ����.png','"+sort+"','"+ingredient+"',"+serving+",'"+cookTime+"','"+difficultyLevel+"',"+access+");");
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
			String ingredient = "���1";
			String quantity = "����1";
			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
			ingredient = "���2";
			quantity = "����2";
			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
			ingredient = "���3";
			quantity = "����3";
			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
			ingredient = "���4";
			quantity = "����4";
			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
			ingredient = "���5";
			quantity = "����5";
			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
			ingredient = "���6";
			quantity = "����6";
			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "���7";
//			quantity = "����7";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "���8";
//			quantity = "����8";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "���9";
//			quantity = "����9";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "���10";
//			quantity = "����10";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
			
			
			ingredientType = 1;
			ingredient = "���1";
			quantity = "����1";
			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
			ingredient = "���2";
			quantity = "����2";
			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
			ingredient = "���3";
			quantity = "����3";
			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "���4";
//			quantity = "����4";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "���5";
//			quantity = "����5";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "���6";
//			quantity = "����6";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "���7";
//			quantity = "����7";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "���8";
//			quantity = "����8";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "���9";
//			quantity = "����9";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
//			ingredient = "���10";
//			quantity = "����10";
//			System.out.println("INSERT INTO INGREDIENT VALUES(INGR_SEQ.nextval, "+rId+", "+ingredientType+", '"+ingredient+"', '"+quantity+"');");
			
		}
	}*/
	
	/*@Test
	public void testMakeDirectionSample() {
		for(int i=431; i<=630; i++) {
			long rId = i;
			int step = 1;
			String direction = "�������� .�������� .�������� .�������� .�������� .�������� .�������� 1.";
			String originalFile = "�������� ����(������)1.png";
			String saveFile = "�������� ����"+i+"(�����).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 2;
			direction = "�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� 2.";
			originalFile = "�������� ����(������)2.png";
			saveFile = "�������� ����"+i+"(�����).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 3;
			direction = "�������� .�������� .�������� .�������� .�������� 3.";
			originalFile = "�������� ����(������)3.png";
			saveFile = "�������� ����"+i+"(�����).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 4;
			direction = "�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� 4.";
			originalFile = "�������� ����(������)4.png";
			saveFile = "�������� ����"+i+"(�����).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 5;
			direction = "�������� .�������� .�������� .�������� .�������� 5.";
			originalFile = "�������� ����(������)5.png";
			saveFile = "�������� ����"+i+"(�����).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 6;
			direction = "�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� 6.";
			originalFile = "�������� ����(������)6.png";
			saveFile = "�������� ����"+i+"(�����).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 7;
			direction = "�������� .�������� .�������� .�������� .�������� 7.";
			originalFile = "�������� ����(������)7.png";
			saveFile = "�������� ����"+i+"(�����).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 8;
			direction = "�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� 8.";
			originalFile = "�������� ����(������)8.png";
			saveFile = "�������� ����"+i+"(�����).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 9;
			direction = "�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� 9.";
			originalFile = "�������� ����(������)9.png";
			saveFile = "�������� ����"+i+"(�����).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 10;
			direction = "�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� 10.";
			originalFile = "�������� ����(������)510.png";
			saveFile = "�������� ����"+i+"(�����).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 11;
			direction = "�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� 11.";
			originalFile = "�������� ����(������)11.png";
			saveFile = "�������� ����"+i+"(�����).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 	step = 12;
			direction = "�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� .�������� 12.";
			originalFile = "�������� ����(������)12.png";
			saveFile = "�������� ����"+i+"(�����).png";
		 	System.out.println("INSERT INTO DIRECTION VALUES(DIR_SEQ.nextval, "+rId+", "+step+", '"+direction+"', '"+originalFile+"', '"+saveFile+"');");
		 }		 
	}*/
	
	/*@Test
	public void testMakeRecipeTagSample() {
		
		for(int i=531; i<=630; i++) {
			long rId = i;
			for(int x=1; x<=5; x++) {
				String tag = "�±�"+x;
				System.out.println("INSERT INTO RECIPE_TAG VALUES(TAG_SEQ.nextval, '"+rId+"', '"+tag+"');");
			}
	  	}
	}*/
}
