package com.modu.domain.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecipeReplyPhoto {
	private long id;
	private long rrId;
	private String originalFile;
	private String saveFile;
}
