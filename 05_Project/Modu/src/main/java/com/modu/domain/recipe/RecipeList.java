package com.modu.domain.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecipeList {
	private long id;
	private String foodPhoto;
	private String title;
	private String food;
	private String info;
	private long hits;
	private int star;
	private String mNickname;
	private String profileImg;
}
