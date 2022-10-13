package com.modu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.modu.domain.recipe.Direction;
import com.modu.domain.recipe.Ingredient;
import com.modu.domain.recipe.Recipe;
import com.modu.domain.recipe.RecipeDetail;
import com.modu.domain.recipe.RecipeTag;
import com.modu.mapper.RecipeMapper;

@Service
public class RecipeFindingServiceImpl implements RecipeFindingService {

	@Autowired
	private RecipeMapper recipeMapper;

	@Override
	public RecipeDetail findRecipedetails(long id) {
		long rId = id;
		Recipe recipe = recipeMapper.selectRecipe(id);
		List<Ingredient> ingredient = recipeMapper.selectIngredient(rId);
		List<Direction> direction = recipeMapper.selectDirection(rId);
		List<RecipeTag> recipetag = recipeMapper.selectRecipeTag(rId);
		RecipeDetail recipeDetail = new RecipeDetail();
		recipeDetail.setRecipe(recipe);
		recipeDetail.setIngredient(ingredient);
		recipeDetail.setDirection(direction);
		recipeDetail.setTag(recipetag);
		return recipeDetail;
	}

}
