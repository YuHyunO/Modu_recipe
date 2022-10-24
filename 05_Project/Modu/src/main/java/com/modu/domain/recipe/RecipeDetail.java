package com.modu.domain.recipe;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecipeDetail {
	private Recipe recipe;
	private List<Ingredient> ingredient;
	private List<Direction> direction;
	private List<RecipeTag> tag;
	
}
