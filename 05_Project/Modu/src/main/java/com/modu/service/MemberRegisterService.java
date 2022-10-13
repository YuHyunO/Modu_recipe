package com.modu.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.modu.domain.member.Member;

public interface MemberRegisterService {

	public void registerMember(Member member); //회원가입
	public int checkEmail(String email); //String checkEmail -> 바꾸기
	public int checkNickname(String nickname);
	
	public int checkLogin(String email, String pwd);//로그인 유효성 검사
	public Member login(Member member); //로그인
	public Member readMyInfo(String email); //내정보조회
	public Member modifyMyInfo(Member member);//내정보수정
	public void removeMyInfo(String email); //회원탈퇴
	
	Map<String, List<Object>> getUpdateFileName();
	MultipartHttpServletRequest getMultipartRequest();
	void setMultipartRequest(MultipartHttpServletRequest multipartRequest);


}
