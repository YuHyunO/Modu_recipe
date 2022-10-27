package com.modu.domain.recipe;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecipeReplyList {
	private long id;
	private long rId;
	private String email;
	private String nickname;
	private String profileImg;
	private String reply;
	private String photo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss", timezone="Asia/Seoul")	
	private Date replyDate;
	private int photoPresense;
	private int nestedReply;
	private int list;
}
