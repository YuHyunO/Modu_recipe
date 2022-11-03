package com.modu.domain.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ingredient {
	private long id;
	private long rId;
	private int ingredientType;
	private String ingredient;
	private String quantity;
}
