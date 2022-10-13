package com.modu.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.modu.domain.recipe.Direction;
import com.modu.domain.recipe.Ingredient;
import com.modu.domain.recipe.Recipe;
import com.modu.domain.recipe.RecipeTag;

public interface RecipeRegisterService {
	/* 레시피 등록,수정,삭제와 관련된 기능 중심으로 인터페이스 작성 */
	//void addRecipe();
	//void modifyRecipe();
	//void deleteRecipe();
	void registerRecipe(HttpServletRequest request, HttpSession session);
}
