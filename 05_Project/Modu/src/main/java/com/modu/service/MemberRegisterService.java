package com.modu.service;

import com.modu.domain.member.Member;

public interface MemberRegisterService {

	public void registerMember(Member member); 
	public Member login(Member member); 
	
	public int checkEmail(String email); 
	public int checkNickname(String nickname);
	
	public Member readMyInfo(String email); //내정보수정 진입
	public Member modifyMyInfo(Member member); //내정보수정 - post1
	public Member modifyMyInfo2(Member member); //내정보수정 - post2
	
	public void removeProfileImg(String email); //내정보수정 - 프로필사진만 삭제
	
	public void removeMyInfo(String email); //내정보수정 - 회원탈퇴

}
