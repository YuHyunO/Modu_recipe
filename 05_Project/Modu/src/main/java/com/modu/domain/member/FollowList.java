package com.modu.domain.member;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FollowList {
	private long id;
	private String email;
	private String nickname;
	private String profileImg;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss", timezone="Asia/Seoul")
	private Date followDate;
}
