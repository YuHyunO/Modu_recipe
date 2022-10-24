package com.modu.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.modu.domain.member.Scrap;
import com.modu.domain.recipe.RecipeDetail;
import com.modu.domain.recipe.RecipeList;


public interface RecipeFindingService {
	/* 레시피 조회,검색 관련 기능 중심으로 인터페이스 작성 */
	List<RecipeList> selectRecipeListByBestHits(long beginRow, long endRow);
	RecipeDetail findRecipedetails(long id);
	String getStarPoint(RecipeDetail recipeDetail);
<<<<<<< HEAD
	RecipeDetail RecipeRead(long id);//
=======
	RecipeDetail RecipeRead(long id);
	Scrap getScrap(long rId, String email);
>>>>>>> 844e045a256e24b6289486ec9ae5bfca9298244e
}
