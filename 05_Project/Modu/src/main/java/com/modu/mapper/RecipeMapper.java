package com.modu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.modu.domain.recipe.Direction;
import com.modu.domain.recipe.Ingredient;
import com.modu.domain.recipe.Recipe;
import com.modu.domain.recipe.RecipeList;
import com.modu.domain.recipe.RecipeTag;

public interface RecipeMapper {
	/* *Required : The four methods below are an unit. And the calling order must be kept. */
	int insertRecipe(Recipe recipe);
	void insertIngredient(Ingredient ingredient);
	void insertDirection(Direction direction);
	void insertTag(RecipeTag recipeTag);
	/* 				***** 				*/
	void updateRecipe(Recipe recipe);
	void updateIngredient(Ingredient ingredient);
	void updateDirection(Direction direction);
	void updateRecipeHits(long id);
	void updateReplyCount(long id);
	
	void deleteRecipe(long id);
	void deleteIngredientAll(long rId);
	void deleteIngredient(long id);
	void deleteDirectionAll(long rId);
	void deleteDirection(long id);
	void deleteTagAll(long rId);
	void deleteTag(long id);
		
	long selectRecipeId(String email);
	Recipe selectRecipe(long id);
	List<Ingredient> selectIngredient(long rId);
	List<Direction> selectDirection(long rId);
	List<RecipeTag> selectRecipeTag(long rId);
	List<RecipeList> selectRecipeListBy(@Param("beginRow")long beginRow, @Param("endRow")long endRow);
	List<RecipeList> selectRecipeListByType(@Param("sort")String sort, @Param("ingredient")String ingredient, 
											@Param("beginRow")long beginRow, @Param("endRow")long endRow);//구현중
	int selectRecipeCount();
	int selecrRecipeCountByType(@Param("sort")String sort, @Param("ingredient")String ingredient);//구현중
	
	// 베스트 레시피
	List<RecipeList> selectRecipeListByBestHits(@Param("beginRow") long beginRow, @Param("endRow") long endRow);
    
	// 레시피 등록 시 사진은 별도로 업데이트
	int updateRecipePhoto(Recipe recipe);
}
