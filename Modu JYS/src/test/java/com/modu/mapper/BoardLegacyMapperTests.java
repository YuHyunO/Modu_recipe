package com.modu.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.modu.domain.board.Board;
import com.modu.domain.board.BoardFile;
import com.modu.domain.board.BoardNestedReply;
import com.modu.domain.board.BoardReply;
import com.modu.domain.board.BoardReplyPicture;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardLegacyMapperTests {
	@Autowired
	BoardLegacyMapper boardLegacyMapper;
	@Autowired
	MemberMapper memberMapper;
	
	/*@Test //테스트 완료
	public void testInsertReply() {
		String bId = "B120";
		String email = "test4@test.com";
		String reply = "1 답글 내용 테스트 작성함.";
		
		BoardReply boardReply = new BoardReply();
	 	boardReply.setBId(bId);
		boardReply.setMEmail(email);
		boardReply.setReply(reply);
		boardReply.setMNickname(memberMapper.selectNickname(email));
		boardReply.setProfileImg(memberMapper.selectProfileImg(email));
	
		//BoardReply boardReply = new BoardReply(bId, email, reply);
		boardLegacyMapper.insertReply(boardReply);
		log.info("#######Success test. The Reply is inserted. : "+boardReply);
	}*/
	
	/*@Test //테스트 완료
	public void testInsertReplyPicture() {
		BoardReplyPicture replyPicture = new BoardReplyPicture();
		replyPicture.setBrId("BR4");
		replyPicture.setOriginalFile("음식사진5.png");
		replyPicture.setSaveFile("FHA1D2W4S1Q523.png");
		boardLegacyMapper.insertReplyPicture(replyPicture);
	}*/
	
	/*@Test //테스트 완료
	public void testInsertNestedReply() {
		BoardNestedReply nestedReply = new BoardNestedReply();
		String brId = "BR2";
		String email = "test1@test.com";
		String reply = "대댓글 작성 테스트";
		
		nestedReply.setBrId(brId);
		nestedReply.setMEmail(email);
		nestedReply.setMNickname(memberMapper.selectNickname(email));
		nestedReply.setProfileImg(memberMapper.selectProfileImg(email));
		nestedReply.setReply(reply);
		
		boardLegacyMapper.insertNestedReply(nestedReply);
	}*/
	
	/*@Test //테스트 완료
	public void testDeleteReply() {
		String id = "BR3";
		boardLegacyMapper.deleteReply(id);
		log.info("#######Success test. The Reply is deleted.");
	}*/
	
	/*@Test //테스트 완료
	public void testDeleteNestedReply() {
		String id = "BRN11";
		boardLegacyMapper.deleteNestedReply(id);
		log.info("#######Success test. The Reply is deleted.");
	}*/
	
	/*@Test //테스트 완료
	public void testSelectReply() {
		long bId = 200;
		List<BoardReply> replys = boardLegacyMapper.selectReply(bId);
		System.out.println("#######Success test. result : ");
		for(BoardReply reply: replys) {
			System.out.println(reply);
		}
	}*/
	
	/*@Test //테스트 완료
	public void testSelectReplyBy() {
		long beginRow = 15;
		long endRow = 20;
		long bId = 200;
		List<BoardReply> replys = boardLegacyMapper.selectReplyBy(bId, beginRow, endRow);
		System.out.println("#######Success test. result : ");
		for(BoardReply reply: replys) {
			System.out.println(reply);
		}
	}*/
	
	/*@Test //테스트 완료
	public void testSelectReplyOfMember() {
		String email = "shr4@gmail.com";
		List<BoardReply> replys = boardLegacyMapper.selectReplyOfMember(email);
		System.out.println("#######Success test. result : ");
		for(BoardReply reply: replys) {
			System.out.println(reply);
		}
	}*/
	
	/*@Test //테스트 완료
	public void testSelectReplyOfMemberBy() {
		String email = "shr4@gmail.com";
		long beginRow = 1;
		long endRow = 5;
		List<BoardReply> replys = boardLegacyMapper.selectReplyOfMemberBy(email, beginRow, endRow);
		System.out.println("#######Success test. result : ");
		for(BoardReply reply: replys) {
			System.out.println(reply);
		}
	}*/
	
	/*@Test //테스트 완료
	public void testSelectNestedReply() {
		long brId = 16000;
		List<BoardNestedReply> replys = boardLegacyMapper.selectNestedReply(brId);
		for(BoardNestedReply reply:replys) {
			System.out.println(reply);
		}
	}*/
	
	/*@Test //테스트 완료
	public void testSelectNestedReplyBy() {
		long brId = 16000;
		long beginRow = 6;
		long endRow = 10;
		List<BoardNestedReply> replys = boardLegacyMapper.selectNestedReplyBy(brId, beginRow, endRow);
		for(BoardNestedReply reply:replys) {
			System.out.println(reply);
		}
	}*/
	/*@Test //테스트 완료
	public void testSelectNestedReplyOfMember() {
		String email = "erwy3er@hanmail.net";
		List<BoardNestedReply> replys = boardLegacyMapper.selectNestedReplyOfMember(email);
		for(BoardNestedReply reply:replys) {
			System.out.println(reply);
		}
	}*/
	
	/*@Test //테스트 완료
	public void testSelectNestedReplyOfMemberBy() {
		String email = "erwy3er@hanmail.net";
		long beginRow = 50;
		long endRow = 100;
		List<BoardNestedReply> replys = boardLegacyMapper.selectNestedReplyOfMemberBy(email, beginRow, endRow);
		for(BoardNestedReply reply:replys) {
			System.out.println(reply);
		}
	}*/
	
	/*@Test //테스트 완료
	public void testSelectReplyCount() {
		long bId = 150;
		long count = boardLegacyMapper.selectReplyCount(bId);
		System.out.println("#######Successfully counted : "+count);
	}*/
	
	/*@Test //테스트 완료
	public void testSelectNestedReplyCount() {
		long brId = 16000;
		long count = boardLegacyMapper.selectNestedReplyCount(brId);
		System.out.println("#######Successfully counted : "+count);
	}*/
	
	/*@Test //테스트 완료
	public void testReplyCountByMember() {
		String email = "erwy3er@hanmail.net";
		long count = boardLegacyMapper.selectReplyCountByMember(email);
		System.out.println("#######Successfully counted : "+count);
	}*/
	
	//###############샘플 데이터 작성용
	/*@Test
	public void testMakeReplyPictureSample() {
		for(int i=9; i<=10; i++) {
			String oFile="댓글첨부사진(원본명)"+".jpg";
			String sFile="댓글첨부사진(저장명)"+".jpg";
			for(int x=5000; x<=16090; x=x+3) {
				System.out.println("INSERT INTO BOARD_REPLY_PICTURE VALUES(B_REPLY_PICTURE_SEQ.nextval,"+x+",'"+oFile+"','"+sFile+"');");
			}
		}
	}*/
	/*@Test
	public void testMakeNestedReply() {
		for(int i=1; i<=3; i++) {
			String email = "test"+i+"@test.com";
			String nickname = memberMapper.selectNickname(email);
			String profileImg = memberMapper.selectProfileImg(email);
			String reply = "대댓글 내용"+i;
			for(int x=750; x<=850; x++) {
				System.out.println("INSERT INTO BOARD_NESTED_REPLY (ID,BR_ID,M_EMAIL,M_NICKNAME,PROFILE_IMG,REPLY) VALUES(BOARD_REPLY_SEQ.nextval,"+x+",'"+email+"','"+nickname+"','"+profileImg+"','"+reply+"');");
			}
		}
	}*/

	/*@Test
	public void testMakeReplySample() {
		List<String> emails = memberMapper.selectEmails(25);
		for(String email:emails) {
			String nickname = memberMapper.selectNickname(email);
			String profileImg = memberMapper.selectProfileImg(email);
			
			for(int x=16080; x<=16090; x++) {
				int bId = x;
				String reply ="[대댓글]"+nickname+"님이 대댓글을 작성했습니다.";
				System.out.println("INSERT INTO BOARD_NESTED_REPLY (ID,BR_ID,M_EMAIL,M_NICKNAME,PROFILE_IMG,REPLY) VALUES(B_NESTED_REPLY_SEQ.nextval,"+x+",'"+email+"','"+nickname+"','"+profileImg+"','"+reply+"');");
			}
		}
	}*/
}
