package com.modu.service;

import com.modu.domain.member.Member;

public interface MemberRegisterService {
	/* 회원가입,회원정보 수정,회원 탈퇴 관련 인터페이스 작성 */
	void signup();
	void login();
	void resignation();
}
