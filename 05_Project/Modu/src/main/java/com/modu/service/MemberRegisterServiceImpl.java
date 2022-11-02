package com.modu.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.modu.domain.member.Member;
import com.modu.fileset.Path;
import com.modu.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor
@NoArgsConstructor
@Service
public class MemberRegisterServiceImpl implements MemberRegisterService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public void registerMember(Member member) {
		memberMapper.insertMember(member);
	}
	
	@Override
	public int checkEmail(String email) {
		return memberMapper.emailCheck(email);
	}
	
	@Override
	public int checkNickname(String nickname) {
		return memberMapper.nicknameCheck(nickname);
	}

	@Override
	public Member login(Member member) {
		return memberMapper.login(member);
	}

	@Override
	public Member readMyInfo(String email) {
		Member member = memberMapper.selectMember(email);
		return member;
	}
	
	//내정보 수정(사진X)
	@Override
	public Member modifyMyInfo(Member member) {
		memberMapper.updateMember(member);
		return member;
	}
	
	//프로필사진 포함하여 내정보수정 post
	@Override
	public Member modifyMyInfo2(Member member) { 
		memberMapper.updateMember2(member);
		return member;
	}
	
	@Override public void removeProfileImg(String email) {
	  memberMapper.deleteProfileImg(email); 
	}
	
	//회원 탈퇴
	@Override
	public void removeMyInfo(String email) {
	    log.info("#회원탈퇴 서비스 removeMyInfo 메소드 진입");
		
		memberMapper.deleteMemberRecipe(email);
		memberMapper.deleteMemberRecipeReply(email);
		memberMapper.deleteMemberRecipeNestedReply(email);
        memberMapper.deleteMemberBoard(email);
        memberMapper.deleteMemberBoardReply(email);  
		memberMapper.deleteMember(email);
		log.info("#회원탈퇴 서비스 removeMyInfo 탈퇴완료 성공");
	}

}
