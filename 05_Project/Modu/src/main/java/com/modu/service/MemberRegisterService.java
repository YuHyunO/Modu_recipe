package com.modu.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.modu.domain.member.Member;

public interface MemberRegisterService {

	public void registerMember(Member member); //占쎌돳占쎌뜚揶쏉옙占쎌뿯
	public void registerMember2(Member member); //占쎌돳占쎌뜚揶쏉옙占쎌뿯
	public int checkEmail(String email); //String checkEmail -> 獄쏅떽��疫뀐옙
	public int checkNickname(String nickname);
	
	public int checkLogin(String email, String pwd);//嚥≪뮄�젃占쎌뵥 占쎌�占쎌뒞占쎄쉐 野껓옙占쎄텢
	public Member login(Member member); //嚥≪뮄�젃占쎌뵥
	
	public Member readMyInfo(String email); //占쎄땀占쎌젟癰귣똻�쒙옙�돳
	public Member modifyMyInfo(Member member);//占쎄땀占쎌젟癰귣똻�땾占쎌젟
	public Member modifyMyInfo2(Member member);//占쎄땀占쎌젟癰귣똻�땾占쎌젟
	//public void modifyProfileImg(String email, String profileImg);//占쎄땀占쎌젟癰귣똻�땾占쎌젟
	
	public void removeMyInfo(String email); //占쎌돳占쎌뜚占쎄퉱占쎈닚

}
