package com.modu.domain.board;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDetail {
	private long bId;
	private String email;
	private String nickname;
	private String profileImg;
	private int postType;
	private String title;
	private String content;
	private long fileId;
	private String originalFile;
	private String saveFile;
	private String extension;
	private long fileSize;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss", timezone="Asia/Seoul")
	private String postDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss", timezone="Asia/Seoul")
	private Date updateDate;
	private long hits;
	
}
