package com.modu.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modu.domain.member.FollowList;
import com.modu.domain.member.Scrap;
import com.modu.domain.recipe.RecipeDetail;
import com.modu.domain.recipe.RecipeList;
import com.modu.domain.recipe.RecipeNestedReply;
import com.modu.domain.recipe.RecipeReplyList;

public interface RecipeFindingService {
	/* ������ ��ȸ,�˻� ���� ��� �߽����� �������̽� �ۼ� */
    RecipeDetail getRecipeDetails(long id, HttpServletRequest request, HttpServletResponse response);
	List<RecipeList> getBestRecipeList(long beginRow, long endRow);
	String getStarPoint(RecipeDetail recipeDetail);
	RecipeDetail RecipeRead(long id);
	Scrap getScrap(long rId, String email);
	List<RecipeList> findRecentRecipes(HttpServletRequest request);
	FollowList getFollower(String targetEmail, String email);
	List<RecipeReplyList> getReply(HttpServletRequest request);
	List<RecipeNestedReply> getNestedReply(HttpServletRequest request);
	List<RecipeList> getLatestRecipeList(long limitNumber);
	int getReplyCount(long recipeId);
}
