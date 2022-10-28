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
    /*              *****               */
    void updateRecipe(Recipe recipe);
    void updateIngredient(Ingredient ingredient);
    void updateDirection(Direction direction);
    //void updateRecipeTag(RecipeTag tag);
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
    List<RecipeList> selectRecipeListBy(@Param("beginRow")int beginRow, @Param("endRow")int endRow);
    List<RecipeList> selectRecipeListByPeriod(@Param("period")int period, @Param("beginRow")int beginRow, @Param("endRow")int endRow);
    List<RecipeList> selectRecipeListByCategory (@Param("category")String category, @Param("period")int period,
                                                 @Param("beginRow")int beginRow, @Param("endRow")int endRow);
    List<RecipeList> selectRecipeListByKeyword(@Param("nameOption")String nameOption, @Param("keyword")String keyword,
                                               @Param("period")int period, @Param("beginRow")int beginRow, @Param("endRow")int endRow);
    List<RecipeList> selectRecipeListByIngredient(@Param("keyword")String keyword, @Param("period")int period,
                                                  @Param("beginRow")int beginRow, @Param("endRow")int endRow);
    List<RecipeList> selectRecipeListByRandom(@Param("beginRow")int beginRow, @Param("endRow")int endRow);
    List<RecipeList> selectRecipeListByIngredients(@Param("query")String query, @Param("beginRow")int beginRow, @Param("endRow")int endRow);
    List<RecipeList> selectRecipeListOfMember(@Param("email")String email, @Param("beginRow")int beginRow, @Param("endRow")int endRow);
    List<RecipeList> selectRecipeListOfMemberByType(@Param("email")String email, @Param("type")int type, @Param("beginRow")int beginRow, @Param("endRow")int endRow);
    List<RecipeList> selectRecipeListOfBookmark(@Param("email")String email, @Param("beginRow")int beginRow, @Param("endRow")int endRow);
    RecipeList selectRecipeSummary(long id);
    
    int selectRecipeCount();
    int selectRecipeCountByPeriod(int period);
    int selectRecipeCountByCategory(@Param("category")String category, @Param("period")int period);
    int selectRecipeCountByKeyword(@Param("nameOption")String nameOption, @Param("keyword")String keyword, @Param("period")int period);
    int selectRecipeCountByIngredient(@Param("keyword")String keyword, @Param("period")int period);
    int selectRecipeCountByIngredients(@Param("query")String query);
    int selectRecipeCountOfMember(String email);
    int selectRecipeCountOfMemberByType(@Param("email")String email, @Param("type")int type);
    int selectRecipeCountOfBookmark(String email);
    
    // 베스트 레시피
    List<RecipeList> selectRecipeListByBestHits(@Param("beginRow") long beginRow, @Param("endRow") long endRow);
    
    // 레시피 등록 시 사진은 별도로 업데이트
    int updateRecipePhoto(Recipe recipe);
}