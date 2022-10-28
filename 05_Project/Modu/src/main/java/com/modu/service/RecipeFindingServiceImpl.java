package com.modu.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.modu.domain.member.FollowList;
import com.modu.domain.member.Scrap;
import com.modu.domain.recipe.Direction;
import com.modu.domain.recipe.Ingredient;
import com.modu.domain.recipe.Recipe;
import com.modu.domain.recipe.RecipeDetail;
import com.modu.domain.recipe.RecipeTag;
import com.modu.fileset.Path;
import com.modu.domain.recipe.RecipeList;
import com.modu.domain.recipe.RecipeNestedReply;
import com.modu.domain.recipe.RecipeReplyList;
import com.modu.mapper.MemberMapper;
import com.modu.mapper.RecipeLegacyMapper;
import com.modu.mapper.RecipeMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor
@Service
public class RecipeFindingServiceImpl implements RecipeFindingService {	
    
	private RecipeMapper recipeMapper;
    private RecipeLegacyMapper recipeLegacyMapper;	
    private MemberMapper memberMapper;
	
    @Override
	public RecipeDetail getRecipeDetails(long id) {        
        long rId = id;
        int beginRow = 1;
        int endRow = 5;
        int subEndRow = 2;
        Recipe recipe = recipeMapper.selectRecipe(id);
        List<Ingredient> ingredient = recipeMapper.selectIngredient(id);
        List<Direction> direction = recipeMapper.selectDirection(id);
        List<RecipeTag> tag = recipeMapper.selectRecipeTag(id);
        List<RecipeReplyList> replyList = recipeLegacyMapper.selectReplyBy(rId, beginRow, endRow);
        System.out.println("##"+replyList);
        List<RecipeNestedReply> nestedReplyList = new ArrayList<RecipeNestedReply>();
        for(RecipeReplyList item:replyList) {
            long rrId = item.getId();
            List<RecipeNestedReply> data = recipeLegacyMapper.selectNestedReplyBy(rrId, beginRow, subEndRow);
            for(RecipeNestedReply list:data) {
                nestedReplyList.add(list);
            }
        }
        RecipeDetail details = new RecipeDetail(recipe, ingredient, direction, tag, replyList, nestedReplyList);
        
	    return details;
	}
	
	@Override
	public List<RecipeList> selectRecipeListByBestHits(long beginRow, long endRow) {
		return recipeMapper.selectRecipeListByBestHits(beginRow, endRow);

	}

	@Override
	public String getStarPoint(RecipeDetail recipeDetail) {
		double star = recipeDetail.getRecipe().getStar();
		String starPoint = Double.toString(star);
		String starPoint1 = starPoint.substring(0, 1);
		String starPoint2 = starPoint.substring(2, 3);
		int starPointResult;		
		
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
    
    @Override
    public FollowList getFollower(String targetEmail, String email) {
        
        FollowList followList = memberMapper.selectFollowerOnebyEmails(targetEmail, email);
        return followList;
    }
    
    @Override
    public List<RecipeReplyList> getReply(HttpServletRequest request){
        long rId = Long.parseLong(request.getParameter("rId"));        
        int beginRow = 1;
        try {
            beginRow = Integer.parseInt(request.getParameter("lastIndex"));
            System.out.println("##"+beginRow);
        }catch(NumberFormatException nfe) {}
        int endRow = beginRow + 5;
        
        List<RecipeReplyList> replyList = recipeLegacyMapper.selectReplyBy(rId, beginRow, endRow);
        return replyList;
    }
    
    @Override    
    public List<RecipeNestedReply> getNestedReply(HttpServletRequest request){
        long rrId = Long.parseLong(request.getParameter("rrId"));
        int beginRow = 0;
        System.out.println("##beginRow1 : "+beginRow);
        try {
            beginRow = Integer.parseInt(request.getParameter("lastIndex"));
            System.out.println("#try#beginRow : "+beginRow);
        }catch(NumberFormatException nfe) {}
        int endRow = beginRow+4;
        System.out.println("##endRow1 : "+endRow);
        List<RecipeNestedReply> replyList = recipeLegacyMapper.selectNestedReplyBy(rrId, beginRow, endRow);
        for(RecipeNestedReply item:replyList) {
            System.out.println("#"+item);
        }
        return replyList;
    }
}