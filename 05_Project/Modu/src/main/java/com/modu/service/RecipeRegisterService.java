package com.modu.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.modu.domain.recipe.Direction;
import com.modu.domain.recipe.Ingredient;
import com.modu.domain.recipe.Recipe;
import com.modu.domain.recipe.RecipeTag;

public interface RecipeRegisterService {
	/* ������ ���,����,������ ���õ� ��� �߽����� �������̽� �ۼ� */
	//void addRecipe();
	//void modifyRecipe();
	//void deleteRecipe();
	void registerRecipe(HttpServletRequest request, HttpSession session);
}
