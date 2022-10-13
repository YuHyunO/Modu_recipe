package com.modu.service;

import java.util.List;

import com.modu.domain.recipe.RecipeDetail;

public interface RecipeFindingService {
	/* 레시피 조회,검색 관련 기능 중심으로 인터페이스 작성 */
	RecipeDetail findRecipedetails(long id);

}
