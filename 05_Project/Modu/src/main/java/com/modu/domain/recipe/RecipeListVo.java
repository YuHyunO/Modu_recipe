package com.modu.domain.recipe;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecipeListVo {
	private List<RecipeList> recipeList;
	private int currentPage;
	private int pageSize;
	private int totalPage;
}
