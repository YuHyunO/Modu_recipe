package com.modu.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.modu.domain.recipe.Direction;
import com.modu.domain.recipe.Ingredient;
import com.modu.domain.recipe.Recipe;
import com.modu.domain.recipe.RecipeDetail;
import com.modu.domain.recipe.RecipeTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.modu.domain.recipe.RecipeNestedReply;
import com.modu.domain.recipe.RecipeReply;
import com.modu.domain.recipe.RecipeReplyList;
import com.modu.domain.recipe.RecipeReplyPhoto;

public interface RecipeRegisterService {
	/* 레시피 등록,수정,삭제와 관련된 기능 중심으로 인터페이스 작성 */
	void registerRecipe(HttpServletRequest request,
            HttpSession session,
            ArrayList<MultipartFile> files, 
            ArrayList<String> mainItems,
            ArrayList<String> subItems,
            ArrayList<String> directions,
            ArrayList<String> tags);	
	RecipeReply registerReply(RecipeReply recipeReply);
	List<RecipeReplyList> findRecipeReply(long id);
	String registerNestedReply(RecipeNestedReply recipeNestedReply);
	RecipeReplyPhoto registerReplyPhoto(long recipeId, RecipeReplyPhoto recipereplyPhoto, MultipartFile files);
	void recipeDelete(long id);
    void updateRecipe(HttpServletRequest request, HttpSession session, ArrayList<MultipartFile> files,
            ArrayList<String> mainItems, ArrayList<String> subItems, ArrayList<String> directions,
            ArrayList<String> tags, ArrayList<String> fileChanges);

    void deleteReply(long id);
    void deleteNestedReply(long id);
}
