package com.modu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.modu.domain.recipe.RecipeDetail;
import com.modu.domain.recipe.RecipeList;


public interface RecipeFindingService {
	/* ������ ��ȸ,�˻� ���� ��� �߽����� �������̽� �ۼ� */
	List<RecipeList> selectRecipeListByBestHits(long beginRow, long endRow);
	RecipeDetail findRecipedetails(long id);
	String getStarPoint(RecipeDetail recipeDetail);
	RecipeDetail RecipeRead(long id);
}
