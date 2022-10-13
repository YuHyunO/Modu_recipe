package com.modu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.modu.domain.member.FollowList;
import com.modu.domain.member.Member;

public interface MemberMapper {

	//void insertMember(Member member);
	void insertFollow(@Param("email")String email, @Param("followee")String followee);
	
	Member selectMember(String email); //내정보수정 조회
	void updateMember(Member member); //내정보수정 post
	void updatePoint(@Param("email") String email, @Param("point") int point);

	void deleteMember(String email);
	void deleteFollow(long id);
	
	//아직 미사용
	String selectEmailCheck(String email);
	String selectNicknameCheck(String nickname);
	/*     ***     */
	String selectNickname(String email);
	String selectProfileImg(String email);
	int selectPoint(String email);
	List<FollowList> selectFollowee(@Param("email") String email, @Param("beginRow")int beginRow, @Param("endRow")int endRow);
	List<FollowList> selectFollower(@Param("email") String email, @Param("beginRow")int beginRow, @Param("endRow")int endRow);
	
	//�׽�Ʈ��
	List<String> selectEmails(int bound);
	
	// ��ŷ���� ȸ�� �̱�
	List<Member> selectMemberRank();
	
	//서인추가 메소드 10.11
	void insertMember(Member member); //회원가입 (마케팅 미동의)
	void insertMember2(Member member); //회원가입 (마케팅 동의)
	
	public int emailCheck(String email); //회원가입 - 이메일 중복검사 ajax
	public int nicknameCheck(String email); //회원가입 - 닉네임 중복검사 ajax
	public int loginCheck(String email, String pwd); //로그인 유효성 검사
	public Member login(Member member); //로그인 post
}
