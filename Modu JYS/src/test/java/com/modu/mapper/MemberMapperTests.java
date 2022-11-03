package com.modu.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.modu.domain.member.FollowList;
import com.modu.domain.member.Member;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTests {
	@Autowired
	private MemberMapper memberMapper;
	
	/*
	@Test //�׽�Ʈ �Ϸ�
	public void testInsertMember() {		
		Member member = new Member("test5@test.com",
								   "1234",
								   "�׽�Ʈ�г���5", 
								   null, 
								   "������5", 
								   "01054861431",
								   0,
								   1);
		memberMapper.insertMember(member);
		log.info("############Success insert data.");
	}*/
	/*
	@Test //�׽�Ʈ �Ϸ�
	public void testUpdateMember() {
		Member member = new Member("test1@test.com",
								   "1234",
								   "�׽�Ʈ�г���1", 
								   "", 
								   "������1", 
								   "01054861431",
								   0,
								   1);
		memberMapper.updateMember(member);
		log.info("############Success update data. result: ");		
	}
	*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testUpdatePoint() {
		String email = "test1@test.com";
		int point = 1000;
		memberMapper.updatePoint(email, point);
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testDeleteMember() {
		String email = "test2@test.com";
		//memberMapper.deleteMember(email);
//		log.info("############Success delete data.");
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testSelectMember() {
		String email = "test@test.com";
		//Member member = memberMapper.selectMember(email);
		//log.info("############Success select data. Member : "+member);
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testSelectEmailCheck() {
		String email = "test@test.com";
//		String result = memberMapper.selectEmailCheck(email);
//		log.info("############Success select data. Email="+result);
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testSelectNicknameCheck() {
		String nickname = "�׽�Ʈ�г���";
//		String result = memberMapper.selectNicknameCheck(nickname);
//		log.info("############Success select data. nickname="+result);
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testSelectNickname() {
		String email = "test@test.com";
//		String result = memberMapper.selectNickname(email);
//		log.info("############Success select data. nickname="+result);
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testSelectProfileImg() {
		String email = "test@test.com";
//		String result = memberMapper.selectProfileImg(email);
//		log.info("############Success select data. profileImg="+result);
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testSelectPoint() {
		String email = "test1@test.com";
		int point = memberMapper.selectPoint(email);
		log.info("##########Success selected POINT : "+ point);
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testInsertFollow() {
		String email = "test9@test.com";
		String followee = "test4@test.com";
		memberMapper.insertFollow(email, followee);
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testDeletFollow() {
		long id=30;
		memberMapper.deleteFollow(id);
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testSelectFollowee() {
		String email = "test2@test.com";
		int beginRow = 1;
		int endRow = 5;
		List<FollowList> followees = memberMapper.selectFollowee(email, beginRow, endRow);
		for(FollowList followee:followees) {
			System.out.println(followee);
		}
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testSelectFollower() {
		String email = "test2@test.com";
		int beginRow = 1;
		int endRow = 5;
		List<FollowList> followees = memberMapper.selectFollower(email, beginRow, endRow);
		for(FollowList followee:followees) {
			System.out.println(followee);
		}
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testselectMemberRank() {
		List<Member> list = memberMapper.selectMemberRank();
		System.out.println(list);
	}*/
}
