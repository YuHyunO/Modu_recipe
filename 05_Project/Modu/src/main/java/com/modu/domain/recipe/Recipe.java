package com.modu.domain.recipe;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Recipe {
	private long id;
	private String mEmail;
	private String mNickname;
	private String profileImg;
	private String title;
	private String info;
	private String food;
	private String foodPhoto;
	private String sort;
	private String ingredient;
	private String serving;
	private String cookTime;
	private String difficultyLevel;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss", timezone="Asia/Seoul")
	private Date postDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss", timezone="Asia/Seoul")
	private Date updateDate;
	private long hits;
	private long reply;
	private double star;
	private int accessibility;	
}
