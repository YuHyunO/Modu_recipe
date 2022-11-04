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
	@Test //테스트 완료
	public void testInsertMember() {		
		Member member = new Member("test5@test.com",
								   "1234",
								   "테스트닉네임5", 
								   null, 
								   "오유현5", 
								   "01054861431",
								   0,
								   1);
		memberMapper.insertMember(member);
		log.info("############Success insert data.");
	}*/
	/*
	@Test //테스트 완료
	public void testUpdateMember() {
		Member member = new Member("test1@test.com",
								   "1234",
								   "테스트닉네임1", 
								   "", 
								   "오유현1", 
								   "01054861431",
								   0,
								   1);
		memberMapper.updateMember(member);
		log.info("############Success update data. result: ");		
	}
	*/
	
	/*@Test //테스트 완료
	public void testUpdatePoint() {
		String email = "test1@test.com";
		int point = 1000;
		memberMapper.updatePoint(email, point);
	}*/
	
	/*@Test //테스트 완료
	public void testDeleteMember() {
		String email = "test2@test.com";
		//memberMapper.deleteMember(email);
//		log.info("############Success delete data.");
	}*/
	
	/*@Test //테스트 완료
	public void testSelectMember() {
		String email = "test@test.com";
		//Member member = memberMapper.selectMember(email);
		//log.info("############Success select data. Member : "+member);
	}*/
	
	/*@Test //테스트 완료
	public void testSelectEmailCheck() {
		String email = "test@test.com";
//		String result = memberMapper.selectEmailCheck(email);
//		log.info("############Success select data. Email="+result);
	}*/
	
	/*@Test //테스트 완료
	public void testSelectNicknameCheck() {
		String nickname = "테스트닉네임";
//		String result = memberMapper.selectNicknameCheck(nickname);
//		log.info("############Success select data. nickname="+result);
	}*/
	
	/*@Test //테스트 완료
	public void testSelectNickname() {
		String email = "test@test.com";
//		String result = memberMapper.selectNickname(email);
//		log.info("############Success select data. nickname="+result);
	}*/
	
	/*@Test //테스트 완료
	public void testSelectProfileImg() {
		String email = "test@test.com";
//		String result = memberMapper.selectProfileImg(email);
//		log.info("############Success select data. profileImg="+result);
	}*/
	
	/*@Test //테스트 완료
	public void testSelectPoint() {
		String email = "test1@test.com";
		int point = memberMapper.selectPoint(email);
		log.info("##########Success selected POINT : "+ point);
	}*/
	
	/*@Test //테스트 완료
	public void testInsertFollow() {
		String email = "test9@test.com";
		String followee = "test4@test.com";
		memberMapper.insertFollow(email, followee);
	}*/
	
	/*@Test //테스트 완료
	public void testDeletFollow() {
		long id=30;
		memberMapper.deleteFollow(id);
	}*/
	
	/*@Test //테스트 완료
	public void testSelectFollowee() {
		String email = "test2@test.com";
		int beginRow = 1;
		int endRow = 5;
		List<FollowList> followees = memberMapper.selectFollowee(email, beginRow, endRow);
		for(FollowList followee:followees) {
			System.out.println(followee);
		}
	}*/
	
	/*@Test //테스트 완료
	public void testSelectFollower() {
		String email = "test2@test.com";
		int beginRow = 1;
		int endRow = 5;
		List<FollowList> followees = memberMapper.selectFollower(email, beginRow, endRow);
		for(FollowList followee:followees) {
			System.out.println(followee);
		}
	}*/
	
	/*@Test //테스트 완료
	public void testselectMemberRank() {
		List<Member> list = memberMapper.selectMemberRank();
		System.out.println(list);
	}*/
}
