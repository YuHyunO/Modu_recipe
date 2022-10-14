package com.modu.service;

import com.modu.domain.member.Member;

public interface MemberRegisterService {

	public void registerMember(Member member); 
	public int checkEmail(String email); 
	public int checkNickname(String nickname);
	
	public int checkLogin(String email, String pwd);
	public Member login(Member member); 
	
	public Member readMyInfo(String email); 
	public Member modifyMyInfo(Member member);
	public Member modifyMyInfo2(Member member);
	//public void modifyProfileImg(String email, String profileImg);
	
	public void removeMyInfo(String email);

}
