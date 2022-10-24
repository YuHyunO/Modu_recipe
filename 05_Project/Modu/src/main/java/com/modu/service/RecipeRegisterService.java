package com.modu.service;

import java.util.List;

import com.modu.domain.recipe.RecipeNestedReply;
import com.modu.domain.recipe.RecipeReply;
import com.modu.domain.recipe.RecipeReplyList;

public interface RecipeRegisterService {
	/* ������ ���,����,������ ���õ� ��� �߽����� �������̽� �ۼ� */
	/*
	 * void addRecipe(); void modifyRecipe(); void deleteRecipe();
	 */
	String registerReply(RecipeReply recipeReply);
	List<RecipeReplyList> findRecipeReply(long id);
	void delete(long id);
	List<RecipeNestedReply> findRecipeNestedReply(long rrId);
	String registerNestedReply(RecipeNestedReply recipeNestedReply);

}
