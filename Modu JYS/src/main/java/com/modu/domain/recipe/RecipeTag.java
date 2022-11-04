package com.modu.domain.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecipeTag {
	private long id;
	private long rId;
	private String tag;
}
