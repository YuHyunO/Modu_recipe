package com.modu.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.modu.domain.board.Board;
import com.modu.domain.board.BoardDetail;
import com.modu.domain.board.BoardFile;
import com.modu.domain.board.BoardReply;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTests {
	@Autowired
	BoardMapper boardMapper;
	@Autowired
	BoardLegacyMapper boardLegacyMapper;
	@Autowired
	MemberMapper memberMapper;
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testInsertPost() {
		for(int x=1; x<2; x++) {
			String email = "test"+x+"@test.com";
			String nickname = memberMapper.selectNickname(email);
			String profileImg = memberMapper.selectProfileImg(email);
			for(int y=1; y<=57; y++) {
				Board board = new Board(email,
										nickname,
										profileImg,
										0,
										"�׽�Ʈ �Խù�"+y,
										"�Խñ� ����"+y);
				boardMapper.insertPost(board);
			}
		}
		log.info("#######Success test. inserted");
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testInsertFile() {
		BoardFile boardFile = new BoardFile();
		boardFile.setBId("B430");
		boardFile.setOriginalFile("my file.file");
		boardFile.setSaveFile("A1B1C1D2F2G2");
		boardFile.setExtension(".file");
		boardMapper.insertFile(boardFile);
		log.info("#######Success test. File is inserted.");
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testUpdatePost() {
		String id = "B1";
		String title = "����� �Խñ� ����";
		String content = "����� �Խñ� ����";
		Board board = new Board(id, title, content);
		boardMapper.updatePost(board);
		log.info("#######Success test. updated : "+board);
	}*/

	/*@Test //�׽�Ʈ �Ϸ�
	public void testUpdateFile() {
		BoardFile boardFile = new BoardFile();
		boardFile.setId("BF1");
		boardFile.setOriginalFile("anotherFile.png");
		boardFile.setSaveFile("AA11FF14S");
		boardFile.setExtension(".png");
		boardMapper.updateFile(boardFile);
		log.info("#######Success test. File is updated.");
	}*/
	
	/*@Test//�׽�Ʈ �Ϸ�
	public void testUpdatePostHits() {
		long id = 200;
		Board before = boardMapper.selectPost(id);
		boardMapper.updatePostHits(id);
		Board result = boardMapper.selectPost(id);
		log.info("#######Successfully Updated. before: "+before+" result: "+result);
	}*/
	
	/*@Test
	public void testUpdateReplyCount() {
		long bId = 200;
		long totalReply = boardLegacyMapper.selectReplyCount(bId);
		log.info("########Successfully selected. REPLY COUNT: "+totalReply);
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testUpdateFileEmpty() {
		long id = 200;
		boardMapper.updateFileEmpty(id);
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testDeletePost() {
		String id = "B3";
		boardMapper.deletePost(id);
		log.info("########Success test. deleted : "+id);
	}*/

	/*@Test //�׽�Ʈ �Ϸ�
	public void testDeleteFile() {
		String id = "BF1";
		boardMapper.deleteFile(id);
		log.info("#######Success test. The File is deleted.");
	}*/

	/*@Test //�׽�Ʈ �Ϸ�
	public void testSelectPost() {
		long id = 195;
		Board board = boardMapper.selectPost(id);
		log.info("#######Success test. selected : "+board);
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testSelectFile() {
		long id = 190;
		List<BoardFile> files = boardMapper.selectFile(id);
		for(BoardFile file:files) {
			System.out.println("FILE : "+file);
		}
		log.info("############Success selected Files");
	}*/

	@Test //�׽�Ʈ �Ϸ�
	public void testSelectPostByType() {
		int type = 0;
		long beginRow = 1;
		long endRow = 15;
		List<Board> posts = boardMapper.selectPostsByType(type, beginRow, endRow);
		log.info("#######Success test. selected : ");
		for(Board post: posts) {
			log.info("####### post : "+post);
		}
	}

	/*@Test //�׽�Ʈ �Ϸ�
	public void testSelectPostByMember() {
		String email = "test2@test.com";
		long beginRow = 1;
		long endRow = 15;
		List<Board> posts = boardMapper.selectPostsByMember(email, beginRow, endRow);
		log.info("#######Success test. selected : ");
		for(Board post: posts) {
			log.info("####### post : "+post);
		}
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testSelectPostCount() {
		long postCount = boardMapper.selectPostCount();
		log.info("#######Success test. selected : postCount="+postCount);
	}*/
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testSelectPostCountByType() {
		int type = 0;
		long postCount = boardMapper.selectPostCountByType(type);
		log.info("#######Success test. selected : postCount="+postCount);
	}*/	
	
	/*@Test //�׽�Ʈ �Ϸ�
	public void testSelectPostCountByMember() {
		String email = "test1@test.com";
		long result = boardMapper.selectPostCountByMember(email);
		log.info("#######Success test. result : "+result);
	}*/
	

	
	
	
	/* ################ SQL���� �ۼ��� �׽�Ʈ ################*/
	/*@Test
	public void makeBoardSample() {
		for(int i=1; i<=10; i++) {
			String email = "test"+i+"@test.com";
			String nickname = memberMapper.selectNickname(email);
			String profileImg = memberMapper.selectProfileImg(email);
			int postType = 1;
			for(int x=1; x<=17; x++) {
				String title = "�Խñ� ����#"+x;
				String content = "�Խñ� ����#"+x;
				System.out.println("INSERT INTO BOARD (ID,M_EMAIL,M_NICKNAME,PROFILE_IMG,POST_TYPE,TITLE,CONTENT) VALUES(BOARD_SEQ.nextval,"+"'"+email+"',"+"'"+nickname+"',"+"'"+profileImg+"',"+postType+","+"'"+title+"',"+"'"+content+"');");
			}
		}
	}*/
	/*@Test
	public void createSampleBoardNotice() {
		List<String> emails = memberMapper.selectEmails();
		int postType = 0;
		for(int i=1; i<=50; i++) {
			String email = "admin@modu.com";
			String nickname = memberMapper.selectNickname(email);
			String profileImg = memberMapper.selectProfileImg(email);			
			String title = "[����]���׽�Ʈ ! <�ʵ�> "+i+"��° ���������Դϴ�.";
			String content = "[����]���׽�Ʈ !"+nickname+"���� "+i+"��° ���������� �ۼ��߽��ϴ�.�׽�Ʈ �Խñ� �����Դϴ�.";
			System.out.println("INSERT INTO BOARD (ID,M_EMAIL,M_NICKNAME,PROFILE_IMG,POST_TYPE,TITLE,CONTENT) VALUES(BOARD_SEQ.nextval,"+"'"+email+"',"+"'"+nickname+"',"+"'"+profileImg+"',"+postType+","+"'"+title+"',"+"'"+content+"');");
		}
	}*/
	
	/*@Test
	public void createSampleBoard() {
		List<String> emails = memberMapper.selectEmails(30);
		int postType = 2;
		for(String email:emails) {
			String nickname = memberMapper.selectNickname(email);
			String profileImg = memberMapper.selectProfileImg(email);			
			String title = "[�����Խ���]���׽�Ʈ !"+nickname+"���� ����.4";
			String content = "[�����Խ���]���׽�Ʈ !"+nickname+"���� �Խñ��� �ۼ��߽��ϴ�.4.�׽�Ʈ �Խñ� �����Դϴ�.";
			System.out.println("INSERT INTO BOARD (ID,M_EMAIL,M_NICKNAME,PROFILE_IMG,POST_TYPE,TITLE,CONTENT) VALUES(BOARD_SEQ.nextval,"+"'"+email+"',"+"'"+nickname+"',"+"'"+profileImg+"',"+postType+","+"'"+title+"',"+"'"+content+"');");
		}
	}*/
	
	/*@Test
	public void testMakeBoardFileSample() {
		for(long i=195; i<=200; i++) {
			long bId = i;
			String oFile = "÷������5(������).file";
			String sFile = "÷������5(�����)";
			String extension = ".file";
			long fileSize = 150+i;
			//BoardFile boardFile = new BoardFile(0, bId, oFile, sFile, extension, fileSize);
			//boardMapper.insertFile(boardFile);
			System.out.println("INSERT INTO BOARD_FILE VALUES(BOARD_FILE_SEQ.nextval,"+"'"+i+"',"+"'"+oFile+"',"+"'"+sFile+"','"+extension+"',"+"'"+fileSize+"');");
		}
	}*/
}
