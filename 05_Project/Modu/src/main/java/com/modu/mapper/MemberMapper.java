package com.modu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.modu.domain.member.FollowList;
import com.modu.domain.member.Member;

public interface MemberMapper {
	/*    메소드 사용 규칙        */
	void insertMember(Member member);
	void insertFollow(@Param("email")String email, @Param("followee")String followee);
	void updateMember(Member member);
	/* 위 두 메소드는 필수로 유효성 검사 이후 호출할 것  */
	
	void updatePoint(@Param("email") String email, @Param("point") int point);
	void deleteMember(String email);
	void deleteFollow(long id);
	
	Member selectMember(String email);
	/* 유효성 검사 메소드  */
	String selectEmailCheck(String email);
	String selectNicknameCheck(String nickname);
	/*     ***     */
	String selectNickname(String email);
	String selectProfileImg(String email);
	int selectPoint(String email);
	List<FollowList> selectFollowee(@Param("email") String email, @Param("beginRow")int beginRow, @Param("endRow")int endRow);
	List<FollowList> selectFollower(@Param("email") String email, @Param("beginRow")int beginRow, @Param("endRow")int endRow);
	
	//테스트용
	List<String> selectEmails(int bound);
	
	// 랭킹으로 회원 뽑기
	List<Member> selectMemberRank();
}
