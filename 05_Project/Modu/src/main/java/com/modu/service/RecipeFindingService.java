package com.modu.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.modu.domain.member.Scrap;
import com.modu.domain.recipe.RecipeDetail;
import com.modu.domain.recipe.RecipeList;


public interface RecipeFindingService {
	/* ������ ��ȸ,�˻� ���� ��� �߽����� �������̽� �ۼ� */
	List<RecipeList> selectRecipeListByBestHits(long beginRow, long endRow);
	RecipeDetail findRecipedetails(long id);
	String getStarPoint(RecipeDetail recipeDetail);
	RecipeDetail RecipeRead(long id);
	Scrap getScrap(long rId, String email);
	List<RecipeList> findRecentRecipes(HttpServletRequest request);
}
