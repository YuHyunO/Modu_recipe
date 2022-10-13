package com.modu.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.modu.domain.member.Member;

public interface MemberRegisterService {

	public void registerMember(Member member); //ȸ������
	public int checkEmail(String email); //String checkEmail -> �ٲٱ�
	public int checkNickname(String nickname);
	
	public int checkLogin(String email, String pwd);//�α��� ��ȿ�� �˻�
	public Member login(Member member); //�α���
	public Member readMyInfo(String email); //��������ȸ
	public Member modifyMyInfo(Member member);//����������
	public void removeMyInfo(String email); //ȸ��Ż��
	
	Map<String, List<Object>> getUpdateFileName();
	MultipartHttpServletRequest getMultipartRequest();
	void setMultipartRequest(MultipartHttpServletRequest multipartRequest);


}
