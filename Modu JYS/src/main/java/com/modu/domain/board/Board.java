package com.modu.domain.board;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {
	private long id;
	private String mEmail;
	private String mNickname;
	private String profileImg;
	private int postType;
	private String title;
	private String content;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yy/ MM/dd hh:mm", timezone="Asia/Seoul")
	private Date postDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yy/ MM/dd hh:mm", timezone="Asia/Seoul")
	private Date updateDate;
	private long hits;
	private long reply;
	private int files;
	
	public Board(String mEmail, String mNickname, String profileImg, 
				 int postType, String title, String content){
		this.mEmail = mEmail;
		this.mNickname = mNickname;
		this.profileImg = profileImg;
		this.postType = postType;
		this.title = title;
		this.content = content;
	}
	
	public Board(long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}
}
