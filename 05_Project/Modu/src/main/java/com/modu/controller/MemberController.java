package com.modu.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.modu.domain.member.Member;
import com.modu.service.MemberRegisterService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("member")
public class MemberController {

	@Autowired
	private MemberRegisterService memberRegisterService;
	
	@Autowired
	HashMap<String, String> map;
	
	//회원가입 페이지 이동
	@GetMapping("/register")
	public String goRegister() {
		return "member/register"; //view 폴더>member 폴더> register.jsp 를 리턴하기
	}

	//회원가입시 이메일 중복검사 ajax 문구 띄움
	@PostMapping("/register/emailvalidcheck")
	@ResponseBody
	public String emailValidCheck(String email){
		log.info("#1_이메일 중복체크 진입 & email:"+ email);
		int result = memberRegisterService.checkEmail(email);
		log.info("#2_이메일 result: "+ result);
		log.info("#3_이메일 길이 email.length(): "+ email.length());
		if(0<email.length() && email.length()<10) {
			return "noshow";			
		} else if(result == 0) { 
			return "success";
		} else { 
			return "fail";
		}
	}
	
	//회원가입시 닉네임 중복검사 ajax 문구 띄움
	@PostMapping("/register/nicknamevalidcheck")
	@ResponseBody
	public String nicknameValidCheck(String nickname){
		log.info("#1_닉네임 중복체크 진입 & nickname:"+ nickname);
		int result = memberRegisterService.checkNickname(nickname);
		log.info("#2_닉네임 중복체크 result: "+ result);
		log.info("#3_닉네임 길이 nickname.length(): "+ nickname.length());
		if( 0<nickname.length() && nickname.length()<3 ) {
			return "noshow";			
		} else if(result == 0) { 
			return "success";
		} else { 
			return "fail";
		}
	}
	
	//회원가입 약관 팝업창 1,2
	@GetMapping("/agreement1")
	public String readAgreement1() {
		log.info("약관1 팝업 click");
		return "member/agreement1";
	}
	@GetMapping("/agreement2")
	public String readAgreement2() {
		log.info("약관2 팝업 click");
		return "member/agreement2";
	}
	
	//회원가입 post - 마케팅 미동의시
	@PostMapping("/register")
	public void register(Member member){
	  log.info("#회원가입 insert 전: Member member= "+ member);
	  memberRegisterService.registerMember(member); 
	  log.info("#회원가입 성공) Member member= "+ member); //이메일. 닉네임 빼고 모두 비어있음..
	}
	
	@GetMapping("/login")
	public String goLogin(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");
	
		//회원 세션 체크(이미 로그인했을 경우 인덱스로 리다이렉트)
		if (email == null) {
			return "member/login";
		} else {
			return "redirect:/";
		}
		
	}

	//로그인 post
	@PostMapping("/login")
	public ModelAndView login(Member member, HttpServletRequest req){
		log.info("#login 메소드 진입!! 로그인 진입");
		ModelAndView mv = new ModelAndView();
		HttpSession session = req.getSession();
		Member memberInfo = memberRegisterService.login(member); //select EMAIL, NICKNAME from MEMBER where EMAIL=? and PWD=?
		if(memberInfo == null) {
			mv.setViewName("member/login");
			mv.addObject("status", 0);
            return mv; 
		}else{  
			mv.setViewName("redirect:/");
			session.setMaxInactiveInterval(1800); //1800초=세션 유효기간 30분으로 지정
			session.setAttribute("email", memberInfo.getEmail());
			session.setAttribute("nickname", memberInfo.getNickname());
			return mv; 
		}
	} 
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest req){
		req.getSession().invalidate();  //세션 무효화
		req.getSession(true); //새로운 세션 받을 준비 true
		log.info("#1_(로그아웃 성공 후) HttpServletRequest req: " + req); //org.apache.catalina.connector.RequestFacade@62fcbd5b
		return "redirect:/";
	}
	
	
	@GetMapping("mypage")
	public String myPage() {
		return "member/mypage";
	}
	
	//내정보 수정 이동
	@GetMapping("/modifymyinfo")
	public ModelAndView goModify(HttpSession session) { 
		
		String email = (String)session.getAttribute("email");
		String nickname = (String)session.getAttribute("nickname");
		Member member = memberRegisterService.readMyInfo(email); 
		ModelAndView mv = new ModelAndView("member/modifymyinfo", "member", member); 
		log.info("######내정보수정 이동get email: "+email);
		log.info("######내정보수정 이동get nickname: "+nickname);
		log.info("######내정보수정 이동get member: "+member);
		log.info("######내정보수정 이동get mv: "+mv);
		
		return mv;
	}
	
	//내정보 수정시 닉네임 중복검사 ajax
	@PostMapping("/modify-myinfo/nicknamevalidcheck")
	@ResponseBody
	public String nicknameValidCheck(String nickname, HttpServletRequest req){
		HttpSession session = req.getSession();
		int result = memberRegisterService.checkNickname(nickname);
		log.info("#3_닉네임 중복체크 result: "+ result);
		log.info("#4_닉네임 길이 checkNickname.length() : "+ nickname.length());
		log.info("#5_세션 정보 session.getAttribute(\"nickname\") : " + session.getAttribute("nickname")); 
		
		if(nickname.length()<3) {
			log.info("#닉네임 길이가 3자 미만일 때: return noshow");
			return "noshow";			
		} else if(result == 0) { 
			log.info("#닉네임 중복 없을 때 성공: return success");
			return "success";
		} else { 
			log.info("#다른 닉네임과 이미 중복일 때: return fail");
			return "fail";
		}
	}
		
	//내정보 수정 post
	@PostMapping("/modifymyinfo")
	public String modify(Member member, HttpSession session) { //HttpServletRequest req 안써도 됨
		//session.removeAttribute("pw");
		//session.removeAttribute("nickname");
		log.info("#1_내정보 수정 진입성공 입력정보 member: "+ member);
		log.info("#1_내정보 수정시 입력 정보 member: "+ member);
		//log.info("#2_내정보 수정전 세션 정보 session: "+ session);
		log.info("#2_현재 세션에 저장된 (수정전) 닉네임 가져오기 session.getAttribute(\"nickname\"); "+session.getAttribute("nickname")); //스크루바
		log.info("#2_현재 세션에 저장된 (수정전) 마케팅여부 가져오기 session.getAttribute(\"marketing\"); "+session.getAttribute("marketing")); //0 = 미동의
	
		Member memberInfo = memberRegisterService.modifyMyInfo(member); 
		session.setAttribute("nickname", memberInfo.getNickname());
		session.setAttribute("marketing", memberInfo.getMarketing());
		//session.setAttribute("updateDate", memberInfo.getUpdateDate());
		session.setAttribute("member", memberInfo);
		log.info("#3_내정보 수정 성공!후 내정보 memberInfo= "+ memberInfo);
		log.info("#5_현재 세션에 저장된 (수정후) 닉네임 가져오기 session.getAttribute(\"nickname\"); "+session.getAttribute("nickname")); //스크루바
		log.info("#5_현재 세션에 저장된 (수정후) 마케팅여부 가져오기 session.getAttribute(\"marketing\"); "+session.getAttribute("marketing")); //0 = 미동의
		log.info("#5_현재 세션에 저장된 (수정후) member 가져오기 session.getAttribute(\"member\"); "+session.getAttribute("member"));
		//Member(email=111@naver.com, pwd=1111, nickname=zeze, profileImg=, name=한서인, phone=01055554, marketing=0, apiUsing=0, signupDate=null, updateDate=null, authority=0, point=0)
		return "redirect:/member/mypage";
	}                 
	
	//회원 탈퇴         
	@GetMapping("/remove-myinfo")
	public String remove(String email, HttpSession session, HttpServletRequest req) { //req 필요
		memberRegisterService.removeMyInfo(email);
		session.invalidate(); //현재 접속하고 있는 세션을 무효화
		//req.getSession(true); //새로운 세션을 받을 준비 true
		return "redirect:/";
	}
	
}
