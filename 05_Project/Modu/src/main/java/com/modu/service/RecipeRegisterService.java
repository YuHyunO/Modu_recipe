package com.modu.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.modu.domain.recipe.Direction;
import com.modu.domain.recipe.Ingredient;
import com.modu.domain.recipe.Recipe;
import com.modu.domain.recipe.RecipeTag;

import java.util.List;

import com.modu.domain.recipe.RecipeNestedReply;
import com.modu.domain.recipe.RecipeReply;
import com.modu.domain.recipe.RecipeReplyList;

public interface RecipeRegisterService {
	/* ������ ���,����,������ ���õ� ��� �߽����� �������̽� �ۼ� */
	/*
	 * void addRecipe(); void modifyRecipe(); void deleteRecipe();
	 */
	void registerRecipe(HttpServletRequest request, HttpSession session);
	
	String registerReply(RecipeReply recipeReply);
	List<RecipeReplyList> findRecipeReply(long id);
	void delete(long id);
	String registerNestedReply(RecipeNestedReply recipeNestedReply);

}
