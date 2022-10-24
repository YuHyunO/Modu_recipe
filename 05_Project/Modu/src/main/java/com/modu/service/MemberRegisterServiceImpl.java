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
	
	@Inject
	private MemberMapper memberMapper;
	private MultipartHttpServletRequest multipartRequest;
	private Map<String, List<Object>> map;
	
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
	public int checkLogin(String email, String pwd) {
		return memberMapper.loginCheck(email, pwd);
	}

	@Override
	public Member login(Member member) {
		return memberMapper.login(member);
	}

	@Override
	public Member readMyInfo(String email) {
		//return memberMapper.showmyinfo(email);
		Member member = memberMapper.selectMember(email);
		return member;
	}

	@Override
	public Member modifyMyInfo(Member member) {
		memberMapper.updateMember(member);
		return member;
	}

	@Override
	public void removeMyInfo(String email) {
		memberMapper.deleteMember(email);
	}

	@Override
	public Map<String, List<Object>> getUpdateFileName() {
		upload();
		return map;
	}

	@Override
	public MultipartHttpServletRequest getMultipartRequest() {
		return multipartRequest;
	}

	@Override
	public void setMultipartRequest(MultipartHttpServletRequest multipartRequest) {
		this.multipartRequest = multipartRequest;
	}


	private void upload() {
		map = new Hashtable<String, List<Object>>(); // (key,value)
		Iterator<String> itr = multipartRequest.getFileNames(); // Iterator
		List<Object> ofnames = new ArrayList<Object>();
		List<Object> savefnames = new ArrayList<Object>();
		List<Object> fsizes = new ArrayList<Object>();

		while (itr.hasNext()) { // hasNext: 
			StringBuilder sb = new StringBuilder();
			MultipartFile mpf = multipartRequest.getFile(itr.next());
			String ofname = mpf.getOriginalFilename(); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String ctm = sdf.format(System.currentTimeMillis());

			int idx = ofname.lastIndexOf(".");
			String ofheader = ofname.substring(0, idx); 
			sb.append(ofheader).append(ctm).append(ofname.substring(ofname.lastIndexOf("."))); 
			String savefname = sb.toString();
			long fsize = mpf.getSize();
			String fileFullPath = Path.FILE_STORE + savefname;

			try {
				mpf.transferTo(new File(fileFullPath)); 
				ofnames.add(ofname); // List 
				savefnames.add(savefname);
				fsizes.add(fsize);
			} catch (IOException ie) {
				log.info("#파일업로드 while문 중 예외 발생: MemberServiceImpl upload() ie: " + ie);
			}
		}
		map.put("#1_파일upload_ofnames", ofnames);
		map.put("#2_파일upload_savefnames", savefnames);
		map.put("#3_파일upload_fsizes", fsizes);
		log.info("#4_파일upload_map: " + map);
	}
}
