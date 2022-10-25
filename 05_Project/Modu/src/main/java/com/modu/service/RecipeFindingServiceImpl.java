package com.modu.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.modu.domain.member.Scrap;
import com.modu.domain.recipe.Direction;
import com.modu.domain.recipe.Ingredient;
import com.modu.domain.recipe.Recipe;
import com.modu.domain.recipe.RecipeDetail;
import com.modu.domain.recipe.RecipeTag;
import com.modu.fileset.Path;
import com.modu.domain.recipe.RecipeList;
import com.modu.mapper.RecipeLegacyMapper;
import com.modu.mapper.RecipeMapper;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class RecipeFindingServiceImpl implements RecipeFindingService {
	
    private Recipe recipe = new Recipe();
    private Ingredient ingredient = new Ingredient();
    private Direction direction = new Direction();
    
    private ArrayList<String> fileInfoList = new ArrayList<String>();
    
	@Autowired
	private RecipeMapper recipeMapper;
	
	@Autowired
    private RecipeLegacyMapper recipeLegacyMapper;
	
	@Autowired FileUploadService fileUploadService;
	
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

    @Override
    public RecipeDetail RecipeRead(long id) {
        long rId = id;
        Recipe recipe = recipeMapper.selectRecipe(id);
        List<Ingredient> ingredient = recipeMapper.selectIngredient(rId);
        List<Direction> direction = recipeMapper.selectDirection(rId);
        List<RecipeTag> recipetag = recipeMapper.selectRecipeTag(rId);
        RecipeDetail recipeDetail = new RecipeDetail();

        recipeDetail.setRecipe(recipe);
//        for(int i=0; i<ingredient.size(); i++) {
        recipeDetail.setIngredient(ingredient);
        log.info("####1: " + ingredient);
//        }
        recipeDetail.setDirection(direction);
        log.info("####2: " + direction);
        recipeDetail.setTag(recipetag);
        log.info("####3: " + recipetag);
        return recipeDetail;
    }

    @Override
    public Scrap getScrap(long rId, String email) {
        Scrap scrap1 = recipeLegacyMapper.selectScrapByRecipeId(rId, email);
        return scrap1;
    }

    @Override
    public List<RecipeList> findRecentRecipes(HttpServletRequest request){
        String[] cookieId = request.getParameterValues("id");
        List<RecipeList> recipeList = new ArrayList<RecipeList>();
        
        for(int i=0; i<cookieId.length; i++) {
            long id = Long.parseLong(cookieId[i]);
            recipeList.add(recipeMapper.selectRecipeSummary(id));
        }                       
        return recipeList;
    }
}