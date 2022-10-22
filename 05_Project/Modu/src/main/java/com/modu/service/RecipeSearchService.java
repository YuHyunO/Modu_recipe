package com.modu.service;

import java.util.List;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.modu.domain.recipe.RecipeListVo;

public interface RecipeSearchService {
	RecipeListVo searchRecipe(HttpServletRequest request, HttpSession session);
	RecipeListVo searchRecipeByIngredient(HttpServletRequest request, HttpSession session);
	RecipeListVo searchRecipeOfMember(HttpServletRequest request, HttpSession session);
	RecipeListVo searchRecipeOfBookmark(HttpServletRequest request, HttpSession session);
}
