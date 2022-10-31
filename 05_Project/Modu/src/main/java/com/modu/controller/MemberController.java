package com.modu.controller;

import java.io.File;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.modu.domain.member.Member;
import com.modu.fileset.Path;
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

	private ArrayList<String> fileInfoList = new ArrayList<String>();

	
	//ȸ������ ������ �̵�
	@GetMapping("/register")
	public String goRegister() {
		return "member/register"; //view�� member ������ register.jsp
	}

	//ȸ�����Խ� �̸��� �ߺ��˻� ajax ���� ���
	@PostMapping("/register/emailvalidcheck")
	@ResponseBody
	public String emailValidCheck(String email){
		log.info("#1_�̸��� �ߺ�üũ ���� & �Է��� email:"+ email);
		int result = memberRegisterService.checkEmail(email);
		log.info("#2_�̸��� result: "+ result);
		log.info("#3_�̸��� ���� email.length(): "+ email.length());
        if( email.length()==0 ) {
            return "noshow";            
        } else if( email.length()!=0 && 0<email.length() && email.length()<10 ) {
            return "short";            
        } 		
		else if(result == 0) { 
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
		if( nickname.length() == 0 ) {
			return "noshow";			
		} else if( nickname.length() != 0 && 0<nickname.length() && nickname.length()<3 ) {
            return "short";            
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
	  log.info("#ȸ������ insert ����: �Ķ���� Member member= "+ member);
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
		//log.info("#login �α��� post�޼ҵ� ����!!");
		ModelAndView mv = new ModelAndView();
		HttpSession session = req.getSession();
		Member memberInfo = memberRegisterService.login(member); //select EMAIL, NICKNAME from MEMBER where EMAIL=? and PWD=?
		if(memberInfo == null) {
			mv.setViewName("member/login"); //�̵��� ������ ����
			mv.addObject("status", 0);
            return mv; 
		}else{  
			mv.setViewName("redirect:/"); //�̵��� ������ ����
			session.setMaxInactiveInterval(1800); //1800��=���� ��ȿ�Ⱓ 30������ ����
			session.setAttribute("email", memberInfo.getEmail());
			session.setAttribute("nickname", memberInfo.getNickname());
			session.setAttribute("profileImg", memberInfo.getProfileImg());
			return mv; 
		}
	} 
	
	//�α׾ƿ�
	@GetMapping("/logout")
	public String logout(HttpServletRequest req){
		req.getSession().invalidate();  //���� ��ȿȭ
		req.getSession(true); //���ο� ���� ���� �غ� true
		log.info("#1_(�α׾ƿ� ����!!) HttpServletRequest req: " + req); //org.apache.catalina.connector.RequestFacade@62fcbd5b
		return "redirect:/";
	}
	
	//���������� ������ �̵�
	@GetMapping("mypage")
	public ModelAndView goMypage(HttpSession session) {
		String email = (String)session.getAttribute("email");
		Member member1 = memberRegisterService.readMyInfo(email); 
		ModelAndView mv = new ModelAndView("member/mypage", "member", member1); 
		//log.info("######���������� �̵�get member1: "+member1);
		//log.info("######���������� �̵�get mv: "+mv);
		return mv;
	}
	
	//���������� ������ �̵�
	@GetMapping("/modifymyinfo")
	public ModelAndView goModify(HttpSession session) { 
		String email = (String)session.getAttribute("email");
		//String nickname = (String)session.getAttribute("nickname");
		Member member1 = memberRegisterService.readMyInfo(email); 
		ModelAndView mv = new ModelAndView("member/modifymyinfo", "member", member1); 
		log.info("######���������� �̵�get email: "+email);
		//log.info("######���������� �̵�get nickname: "+nickname);
		log.info("######���������� �̵�get member1: "+member1);
		log.info("######���������� �̵�get mv: "+mv);
		return mv;
	}
	
	//������ ������ �г��� �ߺ��˻� ajax
	@ResponseBody
	@PostMapping("/modify-myinfo/nicknamevalidcheck")
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
		log.info("#2_���� ���ǿ� ����� (������) �г��� get "+session.getAttribute("nickname")); //��ũ���
		log.info("#2_���� ���ǿ� ����� (������) �����ÿ��� get "+session.getAttribute("marketing")); //0 = �̵���
		
		String ofname = file.getOriginalFilename();
		log.info("#2_���������� newfile: "+ofname);
		
		if(ofname != null)  ofname = ofname.trim();
		if(ofname.length() == 0) { //������ �������� ���� �� ���Ͼ��� update
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
			return "redirect:mypage";
			
		} else { //���ε��� ������ ������ ��
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
			
			member.setProfileImgOrg(ofname);
			member.setProfileImg(saveFileName);
			member.setProfileImgSize(fsize);
			log.info("#############���Ͼ��ε� fileInfoList: "+fileInfoList);
			log.info("#############fileInfoList==null:"+fileInfoList==null);
			log.info("#############fileInfoList.size():"+fileInfoList.size());
			filuploadservice.saveImgFile(file, Path.PROFILE_PATH, fileInfoList); //���Ͼ��ε弭�񽺴ܿ��� ���� ���ÿ� ������ ���� ����
			
			
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
			return "redirect:mypage";
		}
	}  
      
	//���������� - ���� ���� �����ʻ����� ����
	@ResponseBody //�ּ�ó���� return ""�� jsp�� ã�Ƽ� ����
	@GetMapping("/removemyprofileimg")
	public String removeMyProfileImg(@RequestParam String profileImg, HttpSession session) { 
		File file = new File(Path.PROFILE_PATH, profileImg); //PROFILE_PATH �ؿ� �ִ� profileImg�� ������ 
		if(file.exists()) {
			file.delete();
			String email = (String) session.getAttribute("email");
			memberRegisterService.removeProfileImg(email); //mapper.xml(sql) ���� �⺻�������� ��� ����
			return "delete����";
		} else {
			return "delete����-������ ������ �������� ����";
		}
	}
	
	//ȸ�� Ż��         
	@PostMapping("/removemyinfo")
	public String removeMyinfo(@RequestParam("email") String email, HttpSession session, HttpServletRequest req) { //req �ʿ�
		//System.out.println("#���������� - ȸ��Ż�� �޼ҵ� ���� email: " + email);
		//�����̰ų� �����ڰ� �ƴҰ�� ���� �Ұ��ϵ���
		Member member = memberRegisterService.readMyInfo(email);	
		if(member.getEmail().equals((String)session.getAttribute("email"))) {
			memberRegisterService.removeMyInfo(email);
			session.invalidate(); //���� �����ϰ� �ִ� ������ ��ȿȭ
			req.getSession(true); //���ο� ������ ���� �غ� true
			return "redirect:/";
		} else {
			return "redirect:/";
		}
	}
	
}
