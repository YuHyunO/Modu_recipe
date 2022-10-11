package com.modu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.modu.domain.member.FollowList;
import com.modu.domain.member.Member;

public interface MemberMapper {
	/*    �޼ҵ� ��� ��Ģ        */
	void insertMember(Member member);
	void insertFollow(@Param("email")String email, @Param("followee")String followee);
	void updateMember(Member member);
	/* �� �� �޼ҵ�� �ʼ��� ��ȿ�� �˻� ���� ȣ���� ��  */
	
	void updatePoint(@Param("email") String email, @Param("point") int point);
	void deleteMember(String email);
	void deleteFollow(long id);
	
	Member selectMember(String email);
	/* ��ȿ�� �˻� �޼ҵ�  */
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
}
