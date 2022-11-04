package com.modu.domain.board;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.modu.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardReply {
	private long id;
	private long bId;
	private String mEmail;
	private String mNickname;
	private String profileImg;
	private String reply;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yy/MM/dd aa hh:mm", timezone="Asia/Seoul")
	private Date replyDate;
	private int nestedReply;
}
