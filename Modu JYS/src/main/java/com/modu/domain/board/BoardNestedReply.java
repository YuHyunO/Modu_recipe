package com.modu.domain.board;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardNestedReply {
	private long id;
	private long bId;
	private long brId;
	private String mEmail;
	private String mNickname;
	private String profileImg;
	private String reply;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss", timezone="Asia/Seoul")
	private Date replyDate;
}

