package com.modu.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.modu.domain.member.Member;
import com.modu.service.FileUploadService;
import com.modu.service.MemberRegisterService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("member")
public class MemberController {

	@Autowired
	private MemberRegisterService memberRegisterService;
	@Autowired
	private FileUploadService filuploadservice; //by @AllArgsConstructor
	
	//@Autowired
	//private FileUtil fileUtil;
	
	@Autowired
	HashMap<String, String> map;
	
	//ȸ������ ������ �̵�
	@GetMapping("/register")
	public String goRegister() {
		return "member/register"; //view�� member ������ register.jsp
	}

	//ȸ�����Խ� �̸��� �ߺ��˻� ajax ���� ���
	@PostMapping("/register/emailvalidcheck")
	@ResponseBody
	public String emailValidCheck(String email){
		log.info("#1_�̸��� �ߺ�üũ ���� & email:"+ email);
		int result = memberRegisterService.checkEmail(email);
		log.info("#2_�̸��� result: "+ result);
		log.info("#3_�̸��� ���� email.length(): "+ email.length());
		if(0<email.length() && email.length()<10) {
			return "noshow";			
		} else if(result == 0) { 
			return "success";
		} else { 
			return "fail";
		}
	}
	
	//ȸ�����Խ� �г��� �ߺ��˻� ajax ���� ���
	@PostMapping("/register/nicknamevalidcheck")
	@ResponseBody
	public String nicknameValidCheck(String nickname){
		log.info("#1_�г��� �ߺ�üũ ���� & nickname:"+ nickname);
		int result = memberRegisterService.checkNickname(nickname);
		log.info("#2_�г��� �ߺ�üũ result: "+ result);
		log.info("#3_�г��� ���� nickname.length(): "+ nickname.length());
		if( 0<nickname.length() && nickname.length()<3 ) {
			return "noshow";			
		} else if(result == 0) { 
			return "success";
		} else { 
			return "fail";
		}
	}
	
	//ȸ������ ��� �˾�â 1,2
	@GetMapping("/agreement1")
	public String readAgreement1() {
		log.info("���1 �˾� click");
		return "member/agreement1";
	}
	@GetMapping("/agreement2")
	public String readAgreement2() {
		log.info("���2 �˾� click");
		return "member/agreement2";
	}
	
	//ȸ������ post - ������ �̵��ǽ�
	@PostMapping("/register")
	public void register(Member member){
	  log.info("#ȸ������ insert ��: Member member= "+ member);
	  memberRegisterService.registerMember(member); 
	  log.info("#ȸ������ ����) Member member= "+ member); //�̸���. �г��� ���� ��� �������..
	}
	
	@GetMapping("/login")
	public String goLogin(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");
	
		//ȸ�� ���� üũ(�̹� �α������� ��� �ε����� �����̷�Ʈ)
		if (email == null) {
			return "member/login";
		} else {
			return "redirect:/";
		}
		
	}

	//�α��� post
	@PostMapping("/login")
	public ModelAndView login(Member member, HttpServletRequest req){
		log.info("#login �޼ҵ� ����!! �α��� ����");
		ModelAndView mv = new ModelAndView();
		HttpSession session = req.getSession();
		Member memberInfo = memberRegisterService.login(member); //select EMAIL, NICKNAME from MEMBER where EMAIL=? and PWD=?
		if(memberInfo == null) {
			mv.setViewName("member/login");
			mv.addObject("status", 0);
            return mv; 
		}else{  
			mv.setViewName("redirect:/");
			session.setMaxInactiveInterval(1800); //1800��=���� ��ȿ�Ⱓ 30������ ����
			session.setAttribute("email", memberInfo.getEmail());
			session.setAttribute("nickname", memberInfo.getNickname());
			return mv; 
		}
	} 
	
	//�α׾ƿ�
	@GetMapping("/logout")
	public String logout(HttpServletRequest req){
		req.getSession().invalidate();  //���� ��ȿȭ
		req.getSession(true); //���ο� ���� ���� �غ� true
		log.info("#1_(�α׾ƿ� ���� ��) HttpServletRequest req: " + req); //org.apache.catalina.connector.RequestFacade@62fcbd5b
		return "redirect:/";
	}
	
	//���������� ������ �̵�
	@GetMapping("mypage")
	public String myPage() {
		return "member/mypage";
	}
	
	//���������� ������ �̵�
	@GetMapping("/modifymyinfo")
	public ModelAndView goModify(HttpSession session) { 
		
		String email = (String)session.getAttribute("email");
		String nickname = (String)session.getAttribute("nickname");
		Member member1 = memberRegisterService.readMyInfo(email); 
		ModelAndView mv = new ModelAndView("member/modifymyinfo", "member", member1); 
		log.info("######���������� �̵�get email: "+email);
		log.info("######���������� �̵�get nickname: "+nickname);
		log.info("######���������� �̵�get member1: "+member1);
		log.info("######���������� �̵�get mv: "+mv);
		return mv;
	}
	
	//������ ������ �г��� �ߺ��˻� ajax
	@PostMapping("/modify-myinfo/nicknamevalidcheck")
	@ResponseBody
	public String nicknameValidCheck(String nickname, HttpServletRequest req){
		HttpSession session = req.getSession();
		int result = memberRegisterService.checkNickname(nickname);
		log.info("#3_�г��� �ߺ�üũ result: "+ result);
		log.info("#4_�г��� ���� checkNickname.length() : "+ nickname.length());
		log.info("#5_���� ���� session.getAttribute(\"nickname\") : " + session.getAttribute("nickname")); 
		
		if(nickname.length()<3) {
			log.info("#�г��� ���̰� 3�� �̸��� ��: return noshow");
			return "noshow";			
		} else if(result == 0) { 
			log.info("#�г��� �ߺ� ���� �� ����: return success");
			return "success";
		} else { 
			log.info("#�ٸ� �г��Ӱ� �̹� �ߺ��� ��: return fail");
			return "fail";
		}
	}

	//������ ���� post
	@PostMapping("/modifymyinfo")
	public String modifymyinfo(Member member, MultipartFile file, HttpSession session) { //HttpServletRequest req �Ƚᵵ ��
		
		log.info("#1_���������� ����  member: "+ member);
		log.info("#1_������������ �Է� ���� member: "+ member);
		log.info("#1_���� ���ǿ� ����� (������) �г��� get "+session.getAttribute("nickname")); //��ũ���
		log.info("#1_���� ���ǿ� ����� (������) �����ÿ��� get "+session.getAttribute("marketing")); //0 = �̵���
		
		String ofname = file.getOriginalFilename();
		log.info("#2_���������� newfile: "+ofname);
		
		if(ofname != null)  ofname = ofname.trim();
		if(ofname.length() == 0) { //������ �������� ���� �� ���Ͼ��� update
			//memberRegisterService.modifyMyInfo(member);
			Member memberInfo = memberRegisterService.modifyMyInfo(member);
			log.info("#3_���������� ������ ������ memberInfo= "+ memberInfo);
			session.setAttribute("nickname", memberInfo.getNickname());
			session.setAttribute("marketing", memberInfo.getMarketing());
			session.setAttribute("profileImg", memberInfo.getProfileImg());
			session.setAttribute("member", memberInfo);
			log.info("#4_���� ���ǿ� ����� (������) �г��� get "+session.getAttribute("nickname")); //��ũ���
			log.info("#4_���� ���ǿ� ����� (������) �����ÿ��� get "+session.getAttribute("marketing")); //0 = �̵���
			log.info("#4_���� ���ǿ� ����� (������) �����ʻ��� get "+session.getAttribute("profileImg"));
			log.info("#4_���� ���ǿ� ����� (������) member get "+session.getAttribute("member"));
			//Member(email=111@naver.com, pwd=1111, nickname=zeze, profileImg=, name=�Ѽ���, phone=01055554, marketing=0, apiUsing=0, signupDate=null, updateDate=null, authority=0, point=0)
			return "redirect:/member/modifymyinfo";
			
		} else {
			int idx = ofname.lastIndexOf(".");
			String ofheader = ofname.substring(0,idx); //���� ���� �̱�
			String ext = ofname.substring(idx); //Ȯ���ڸ� �̱�
			long ms = System.currentTimeMillis();
			
			StringBuilder sb = new StringBuilder();
			sb.append(ofheader); //�������� �߰� 
			sb.append("_");
			sb.append(ms);
			sb.append(ext);
			String saveFileName = sb.toString();
			int fsize = (int) file.getSize();	
			log.info("���� ��");
			
			member.setProfileImgOrg(ofname);
			member.setProfileImg(saveFileName);
			member.setProfileImgSize(fsize);
			log.info("���Ͼ��ε� �� member:"+ member);
			filuploadservice.saveProfileimg(file); //���Ͼ��ε弭�񽺴ܿ��� ���� ���ÿ� ������ ���� ����
			
			//memberRegisterService.modifyMyInfo2(member);
			log.info("������� ��");
			Member memberInfo = memberRegisterService.modifyMyInfo2(member);
			log.info("#5_���������� ������ ������ memberInfo= "+ memberInfo);
			session.setAttribute("nickname", memberInfo.getNickname());
			session.setAttribute("marketing", memberInfo.getMarketing());
			session.setAttribute("profileImg", memberInfo.getProfileImg());
			session.setAttribute("member", memberInfo);
			log.info("#6_���� ���ǿ� ����� (������) �г��� get "+session.getAttribute("nickname")); //��ũ���
			log.info("#6_���� ���ǿ� ����� (������) �����ÿ��� get "+session.getAttribute("marketing")); //0 = �̵���
			log.info("#6_���� ���ǿ� ����� (������) �����ʻ��� get "+session.getAttribute("profileImg"));
			log.info("#6_���� ���ǿ� ����� (������) member get "+session.getAttribute("member"));
			//Member(email=111@naver.com, pwd=1111, nickname=zeze, profileImg=, name=�Ѽ���, phone=01055554, marketing=0, apiUsing=0, signupDate=null, updateDate=null, authority=0, point=0)
			return "redirect:/member/modifymyinfo";
		}
		
	}  
		
	//���������� - �����ʻ��� post
	/*
	@PostMapping("/modifymyinfo/img")
	public String updateImg(String email, MultipartHttpServletRequest mpRequest, 
			HttpSession session) throws Exception  { //HttpServletRequest req �Ƚᵵ ��
		
		String myImg = fileUtil.updateImg(mpRequest); 
		Member member1 = (Member) session.getAttribute("login");
		memberRegisterService.modifyProfileImg(email, myImg);
		member1.setProfileImg(myImg);
		session.setAttribute("login", member1);
				
		return "redirect:/member/mypage";
				
	}                 
*/
	//ȸ�� Ż��         
	@GetMapping("/remove-myinfo")
	public String remove(String email, HttpSession session, HttpServletRequest req) { //req �ʿ�
		memberRegisterService.removeMyInfo(email);
		session.invalidate(); //���� �����ϰ� �ִ� ������ ��ȿȭ
		//req.getSession(true); //���ο� ������ ���� �غ� true
		return "redirect:/";
	}
	
}
