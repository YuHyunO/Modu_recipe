package com.modu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.modu.domain.recipe.Direction;
import com.modu.domain.recipe.Ingredient;
import com.modu.domain.recipe.Recipe;
import com.modu.domain.recipe.RecipeDetail;
import com.modu.domain.recipe.RecipeTag;
import com.modu.domain.recipe.RecipeList;
import com.modu.mapper.RecipeMapper;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class RecipeFindingServiceImpl implements RecipeFindingService {
	
	@Autowired
	private RecipeMapper recipeMapper;
	
	@Override
	public List<RecipeList> selectRecipeListByBestHits(long beginRow, long endRow) {
		return recipeMapper.selectRecipeListByBestHits(beginRow, endRow);

	}	
	
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

	@Override
	public String getStarPoint(RecipeDetail recipeDetail) {
		double star = recipeDetail.getRecipe().getStar();
		String starPoint = Double.toString(star);
		String starPoint1 = starPoint.substring(0, 1);
		String starPoint2 = starPoint.substring(2, 3);
		int starPointResult;
		
		//System.out.println("####스타포인트1:" + starPoint1);
		//System.out.println("####스타포인트2:" + starPoint2);
		
		if (Integer.parseInt(starPoint2) >= 5) {
			starPointResult = Integer.parseInt(starPoint1) + 1;
			starPoint1 = Integer.toString(starPointResult);
		}
		
		//System.out.println("####스타포인트1:" + starPoint1);
		return starPoint1;
	}

}