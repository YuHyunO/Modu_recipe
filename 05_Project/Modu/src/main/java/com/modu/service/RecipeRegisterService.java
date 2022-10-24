package com.modu.service;

import java.util.List;

import com.modu.domain.recipe.RecipeNestedReply;
import com.modu.domain.recipe.RecipeReply;
import com.modu.domain.recipe.RecipeReplyList;

public interface RecipeRegisterService {
	/* 레시피 등록,수정,삭제와 관련된 기능 중심으로 인터페이스 작성 */
	/*
	 * void addRecipe(); void modifyRecipe(); void deleteRecipe();
	 */
	String registerReply(RecipeReply recipeReply);
	List<RecipeReplyList> findRecipeReply(long id);
	void delete(long id);
	List<RecipeNestedReply> findRecipeNestedReply(long rrId);
	String registerNestedReply(RecipeNestedReply recipeNestedReply);

}
