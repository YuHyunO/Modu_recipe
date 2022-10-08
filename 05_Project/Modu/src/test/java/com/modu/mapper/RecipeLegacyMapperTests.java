package com.modu.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class RecipeLegacyMapperTests {
	@Autowired
	private MemberMapper memberMapper;
	
	
	
	
	//####ª˘«√ µ•¿Ã≈Õ  ¿€º∫øÎ
	/*@Test
	public void testMakeReplySample() {
 		List<String> emails = memberMapper.selectEmails(7);
		for(String mEmail:emails) {
			String mNickname = memberMapper.selectNickname(mEmail);
			String profileImg = memberMapper.selectProfileImg(mEmail);
			
			for(int x=528; x<=630; x=x+3) {
				int rId = x;
				String reply ="[¥Ò±€]"+mNickname+"¥‘¿Ã ¥Ò±€¿ª ¿€º∫«ﬂΩ¿¥œ¥Ÿ.";
				System.out.println("INSERT INTO RECIPE_REPLY (ID,R_ID,M_EMAIL,M_NICKNAME,PROFILE_IMG,REPLY) VALUES(RECIPE_REPLY_SEQ.nextval,"+rId+",'"+mEmail+"','"+mNickname+"','"+profileImg+"','"+reply+"');");
			}
		}		 
	}*/
	
	/*@Test
	public void testMakeReplyPictureSample() {
		String oFile="¥Ò±€√∑∫ŒªÁ¡¯(ø¯∫ª∏Ì)"+".jpg";
		String sFile="¥Ò±€√∑∫ŒªÁ¡¯(¿˙¿Â∏Ì)"+".jpg";
		for(int x=1; x<=9348; x=x+3) {
			System.out.println("INSERT INTO RECIPE_REPLY_PHOTO VALUES(R_REPLY_PHOTO_SEQ.nextval,"+x+",'"+oFile+"','"+sFile+"');");
		}	
	}*/
	
	/*@Test
	public void testMakeNestedReply() {
 		List<String> emails = memberMapper.selectEmails(9);
		for(String mEmail:emails) {
			String mNickname = memberMapper.selectNickname(mEmail);
			String profileImg = memberMapper.selectProfileImg(mEmail);
			
			for(int x=5001; x<=9348; x=x+3) {
				int rId = x;
				String reply ="[¥Î¥Ò±€]"+mNickname+"¥‘¿Ã ¥Î¥Ò±€¿ª ¿€º∫«ﬂΩ¿¥œ¥Ÿ.";
				System.out.println("INSERT INTO RECIPE_NESTED_REPLY (ID,RR_ID,M_EMAIL,M_NICKNAME,PROFILE_IMG,REPLY) VALUES(R_NESTED_REPLY_SEQ.nextval,"+x+",'"+mEmail+"','"+mNickname+"','"+profileImg+"','"+reply+"');");
			}
		}		
	}*/
}
