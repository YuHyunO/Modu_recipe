package com.modu.domain.member;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
	private String email;
	private String pwd;
	private String nickname;
	private String name;
	private String phone; 
	private String profileImg;
	private String profileImgOrg;
	private int profileImgSize;
	private int marketing;
	private int apiUsing; 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss", timezone="Asia/Seoul")
	private Date signupDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss", timezone="Asia/Seoul")
	private Date updateDate;
	private int authority;
	private int point;
	
	public Member(String email, String pwd, String nickname,
				  String profileImg, String name, String phone,
				  int marketing, int apiUsing){
		if (profileImg == null) {
			profileImg = "default_profile_img.png";
		}
		
		this.email = email;
		this.pwd = pwd;
		this.nickname = nickname;
		this.profileImg = profileImg;
		this.name = name;
		this.phone = phone;
		this.marketing = marketing;
		this.apiUsing = apiUsing;
	}
}
