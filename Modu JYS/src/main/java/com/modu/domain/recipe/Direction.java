package com.modu.domain.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Direction {
	private long id;
	private long rId;
	private int step;
	private String direction;
	private String originalFile;
	private String saveFile;
}
