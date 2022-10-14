package com.modu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.modu.domain.member.FollowList;
import com.modu.domain.member.Member;

public interface MemberMapper {

	//void insertMember(Member member);	
	Member selectMember(String email); //�궡�젙蹂댁닔�젙 議고쉶
	
	void updatePoint(@Param("email") String email, @Param("point") int point);
	void deleteMember(String email);
	
	void insertFollow(@Param("email")String email, @Param("followee")String followee);
	void deleteFollow(long id);
	
	//�븘吏� 誘몄궗�슜
	String selectEmailCheck(String email);
	String selectNicknameCheck(String nickname);
	/*     ***     */
	String selectNickname(String email);
	String selectProfileImg(String email);
	int selectPoint(String email);
	List<FollowList> selectFollowee(@Param("email") String email, @Param("beginRow")int beginRow, @Param("endRow")int endRow);
	List<FollowList> selectFollower(@Param("email") String email, @Param("beginRow")int beginRow, @Param("endRow")int endRow);
	
	//占쌓쏙옙트占쏙옙
	List<String> selectEmails(int bound);
	
	// 占쏙옙킹占쏙옙占쏙옙 회占쏙옙 占싱깍옙
	List<Member> selectMemberRank();
	
	//�꽌�씤異붽� 硫붿냼�뱶 10.11
	void insertMember(Member member); //
	void insertMember2(Member member); //
	
	public int emailCheck(String email); //회원가입 이메일 중복검사 ajax
	public int nicknameCheck(String email); //회원가입 닉네임 중복검사 ajax
	public int loginCheck(String email, String pwd); //아직 미사용 
	public Member login(Member member); //로그인 post

	void updateMember(Member member); //내정보수정 post(프로필사진 없음)
	void updateMember2(Member member); //프로필사진 포함 내정보수정
	void updateImg(String email, String profileImg); //프로필사진만 변경(아직 미사용)
}
