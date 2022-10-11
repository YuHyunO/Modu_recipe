package com.modu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
