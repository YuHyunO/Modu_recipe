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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.modu.domain.recipe.RecipeNestedReply;
import com.modu.domain.recipe.RecipeReply;
import com.modu.domain.recipe.RecipeReplyList;
import com.modu.mapper.RecipeLegacyMapper;




@Log4j
@Controller
@Service
public class RecipeRegisterServiceImpl implements RecipeRegisterService {
	
	@Autowired
	private RecipeLegacyMapper recipeLegacyMapper;
	
	@Autowired
	private RecipeMapper recipeMapper;
	
	@Autowired
	private MemberMapper memberMapper;


	@Override
	public void registerRecipe(HttpServletRequest request, HttpSession session) {
		
		System.out.println("리퀘스트 "+ request.getParameter("recipe"));
		//String email =(String)session.getAttribute("email");
		/*
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

		String accessibilityS = request.getParameter("accessibility");
		int accessibility = Integer.parseInt(accessibilityS);

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
		recipe.setAccessibility(accessibility);
		recipeMapper.insertRecipe(recipe);

		log.info("#####9");
		
		// ingredient 테이블 insert 로직
		long rId = recipeMapper.selectRecipeId(email);
		ingredient.setRId(rId);
		//메인재료에 데이터를 넣기 위해 type을 지정하는 로직
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
		//양념재료에 데이터를 넣기 위한 type을 지정해주는 로직
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
		//direction 테이블 insert로직
		direction.setRId(rId);
		direction.setDirection(request.getParameter("direction"));
		direction.setOriginalFile(originalFile);
		direction.setSaveFile(saveFile);
		recipeMapper.insertDirection(direction);
		log.info("#####4");
		
		//tag 테이블 insert로직
		recipeTag.setRId(rId);
		recipeTag.setTag(request.getParameter("tag"));
		log.info(request.getParameter("tag"));
		recipeMapper.insertTag(recipeTag);
		log.info("#####5");
		*/
	}

	@Override
	public List<RecipeReplyList> findRecipeReply(long id) {
		return recipeLegacyMapper.selectReply(id);		
	}
	@Override
	public void delete(long id) {
		recipeLegacyMapper.deleteReply(id);
	}
	
	
	@Override
	public String registerReply(RecipeReply recipeReply) {
		String result;
		try {
			recipeLegacyMapper.insertReply(recipeReply);
			result = "성공";
		} catch(Exception e) {
			System.out.println("#registerReply: " + e);
			result = "실패";
		}
		System.out.println("#registerReply: " + result);
		return result;
	}

	
	@Override	
	public String registerNestedReply(RecipeNestedReply recipeNestedReply) {
		String result;
		try {
			recipeLegacyMapper.insertNestedReply(recipeNestedReply);
			result = "성공";
		} catch(Exception e) {
			System.out.println("#registerNestedReply: " + e);
			result = "실패";
		}
		System.out.println("#registerNestedReply: " + result);
		return result;
	}
	
}
