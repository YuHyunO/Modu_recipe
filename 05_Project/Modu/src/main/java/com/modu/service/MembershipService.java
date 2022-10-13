package com.modu.service;

import java.util.List;

import com.modu.domain.member.Member;

public interface MembershipService {
	/* 회원 조회,마이페이지 관련 기능 중심으로 인터페이스 작성 */
	List<Member> selectMemberRankS();
}
