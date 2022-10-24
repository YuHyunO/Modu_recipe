package com.modu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.modu.domain.recipe.RecipeNestedReply;
import com.modu.domain.recipe.RecipeReply;
import com.modu.domain.recipe.RecipeReplyList;
import com.modu.mapper.RecipeLegacyMapper;

@Controller
@Service
public class RecipeRegisterServiceImpl implements RecipeRegisterService {

	@Autowired
	private RecipeLegacyMapper recipeLegacyMapper;

	@Override
	public List<RecipeReplyList> findRecipeReply(long id) {
		return recipeLegacyMapper.selectReply(id);		
	}
	
	@Override
	public void delete(long id) {
		recipeLegacyMapper.deleteReply(id);
	}
	
	
	@Override
	public String registerReply(RecipeReply recipeReply) {
		String result;
		try {
			recipeLegacyMapper.insertReply(recipeReply);
			result = "성공";
		} catch(Exception e) {
			System.out.println("#registerReply: " + e);
			result = "실패";
		}
		System.out.println("#registerReply: " + result);
		return result;
	}
	
	
	@Override
	public List<RecipeNestedReply> findRecipeNestedReply(long rrId){
		return recipeLegacyMapper.selectNestedReply(rrId);
	}

	@Override
	public String registerNestedReply(RecipeNestedReply nestedReply){
		String result;
		try {		
			recipeLegacyMapper.insertNestedReply(nestedReply);
			result = "성공";
		}catch(Exception e){
			System.out.println("#insertRReply exception: " + e);
			result = "실패";	
		}
		System.out.println("insertRReply:" + result);
		return result;
		
	}
	

	
	/*@Override	
	public String registerNestedReply(RecipeNestedReply recipeNestedReply) {
		String result;
		try {
			recipeLegacyMapper.insertNestedReply(recipeNestedReply);
			result = "성공";
		} catch(Exception e) {
			System.out.println("#registerNestedReply: " + e);
			result = "실패";
		}
		System.out.println("#registerNestedReply: " + result);
		return result;

	}*/

	
	
}
