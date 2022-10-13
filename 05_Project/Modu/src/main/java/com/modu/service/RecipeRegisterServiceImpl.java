package com.modu.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.domain.recipe.Direction;
import com.modu.domain.recipe.Ingredient;
import com.modu.domain.recipe.Recipe;
import com.modu.domain.recipe.RecipeTag;
import com.modu.mapper.MemberMapper;
import com.modu.mapper.RecipeMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class RecipeRegisterServiceImpl implements RecipeRegisterService {
	
	@Autowired
	private RecipeMapper recipeMapper;
	@Autowired
	private MemberMapper memberMapper;


	@Override
	public void registerRecipe(HttpServletRequest request, HttpSession session) {
		//String email =(String)session.getAttribute("email");
		String email = "oyh1431@naver.com"; //
		String nickname = memberMapper.selectNickname(email);
		String profileImg = memberMapper.selectProfileImg(email);
		String foodPhoto = "awef.jpg";
		String originalFile = "Asdf.jpg";
		String saveFile = "save.jpg";
		
		Recipe recipe = new Recipe();
		Ingredient ingredient = new Ingredient();
		Direction direction = new Direction();
		RecipeTag recipeTag = new RecipeTag();

		String accessibilitySecret = request.getParameter("accessibilitySecret");
		int acS = Integer.parseInt(accessibilitySecret);
		String accessibilityOpen = request.getParameter("accessibilityOpen");
		int acO = Integer.parseInt(accessibilityOpen);
		String accessibilityTemp = request.getParameter("accessibilityTemp");
		int acT = Integer.parseInt(accessibilityTemp);
		recipe.setMEmail(email);
		recipe.setMNickname(nickname);
		recipe.setProfileImg(profileImg);
		recipe.setFoodPhoto(foodPhoto);
		recipe.setTitle(request.getParameter("title"));
		recipe.setInfo(request.getParameter("info"));
		recipe.setFood(request.getParameter("food"));
		recipe.setFoodPhoto(foodPhoto);
		recipe.setSort(request.getParameter("sort"));
		recipe.setIngredient(request.getParameter("ingredientInfo"));
		recipe.setServing(request.getParameter("serving"));
		recipe.setCookTime(request.getParameter("cookTime"));
		recipe.setDifficultyLevel(request.getParameter("difficultyLevel"));
		log.info("acS: " + acS);
		log.info("acO: " + acO);
		log.info("acT: " + acT);
		if(acS == 0) { // ����� ���� ��ư�� ������ �� accessibility�� number�� 0���� ����
			recipe.setAccessibility(acS);
			recipeMapper.insertRecipe(recipe);
		}else if(acO == 1) { // ���� �� ���� ��ư�� ������ �� accessibility�� number�� 1�� ����
			recipe.setAccessibility(acO);
			recipeMapper.insertRecipe(recipe);
		}else if(acT == 2) { // �ӽ� ���� ��ư�� ��������  accessibility�� number�� 2�� ����
			recipe.setAccessibility(acT);
			recipeMapper.insertRecipe(recipe);
		}else {
			log.info("error");
		}
		log.info("#####9");
		
		// ingredient ���̺� insert ����
		long rId = recipeMapper.selectRecipeId(email);
		ingredient.setRId(rId);
		//������ῡ �����͸� �ֱ� ���� type�� �����ϴ� ����
		String ingredientTypeMain = request.getParameter("ingredientTypeMain");
		int inTM = Integer.parseInt(ingredientTypeMain);
		//log.info("inTM"+inTM);
		if(inTM == 0) {
			ingredient.setIngredientType(inTM);
			ingredient.setIngredient(request.getParameter("ingredientM"));
			//log.info("#####s"+request.getParameter("ingredientM"));
			ingredient.setQuantity(request.getParameter("quantityM"));
			//log.info("#####e"+request.getParameter("quantityM"));
			recipeMapper.insertIngredient(ingredient);
		}
		//�����ῡ �����͸� �ֱ� ���� type�� �������ִ� ����
		String ingredientTypeSub = request.getParameter("ingredientTypeSub");
		int inTS = Integer.parseInt(ingredientTypeSub);
		//log.info("inTS"+inTS);
		if(inTS == 1) {
			ingredient.setIngredientType(inTS);
			ingredient.setIngredient(request.getParameter("ingredientS"));
			//log.info("#####q"+request.getParameter("ingredientS"));
			ingredient.setQuantity(request.getParameter("quantityS"));
			//log.info("#####s"+request.getParameter("quantityS"));
			recipeMapper.insertIngredient(ingredient);
		}
		//direction ���̺� insert����
		direction.setRId(rId);
		//direction.setStep(Integer.parseInt(request.getParameter("setp")));
		direction.setDirection(request.getParameter("direction"));
		direction.setOriginalFile(originalFile);
		direction.setSaveFile(saveFile);
		recipeMapper.insertDirection(direction);
		log.info("#####4");
		
		//tag ���̺� insert����
		recipeTag.setRId(rId);
		recipeTag.setTag(request.getParameter("tag"));
		log.info(request.getParameter("tag"));
		recipeMapper.insertTag(recipeTag);
		log.info("#####5");
		
	}

}
