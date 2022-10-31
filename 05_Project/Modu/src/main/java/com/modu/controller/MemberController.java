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

	
	//회원가입 페이지 이동
	@GetMapping("/register")
	public String goRegister() {
		return "member/register"; //view의 member 폴더속 register.jsp
	}

	//회원가입시 이메일 중복검사 ajax 문구 띄움
	@PostMapping("/register/emailvalidcheck")
	@ResponseBody
	public String emailValidCheck(String email){
		log.info("#1_이메일 중복체크 진입 & 입력한 email:"+ email);
		int result = memberRegisterService.checkEmail(email);
		log.info("#2_이메일 result: "+ result);
		log.info("#3_이메일 길이 email.length(): "+ email.length());
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
	
	//회원가입시 닉네임 중복검사 ajax 문구 띄움
	@PostMapping("/register/nicknamevalidcheck")
	@ResponseBody
	public String nicknameValidCheck(String nickname){
		log.info("#1_닉네임 중복체크 진입 & nickname:"+ nickname);
		int result = memberRegisterService.checkNickname(nickname);
		log.info("#2_닉네임 중복체크 result: "+ result);
		log.info("#3_닉네임 길이 nickname.length(): "+ nickname.length());
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
	  log.info("#회원가입 insert 진입: 파라미터 Member member= "+ member);
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
		//log.info("#login 로그인 post메소드 진입!!");
		ModelAndView mv = new ModelAndView();
		HttpSession session = req.getSession();
		Member memberInfo = memberRegisterService.login(member); //select EMAIL, NICKNAME from MEMBER where EMAIL=? and PWD=?
		if(memberInfo == null) {
			mv.setViewName("member/login"); //이동할 페이지 설정
			mv.addObject("status", 0);
            return mv; 
		}else{  
			mv.setViewName("redirect:/"); //이동할 페이지 설정
			session.setMaxInactiveInterval(1800); //1800초=세션 유효기간 30분으로 지정
			session.setAttribute("email", memberInfo.getEmail());
			session.setAttribute("nickname", memberInfo.getNickname());
			session.setAttribute("profileImg", memberInfo.getProfileImg());
			return mv; 
		}
	} 
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest req){
		req.getSession().invalidate();  //세션 무효화
		req.getSession(true); //새로운 세션 받을 준비 true
		log.info("#1_(로그아웃 성공!!) HttpServletRequest req: " + req); //org.apache.catalina.connector.RequestFacade@62fcbd5b
		return "redirect:/";
	}
	
	//마이페이지 페이지 이동
	@GetMapping("mypage")
	public ModelAndView goMypage(HttpSession session) {
		String email = (String)session.getAttribute("email");
		Member member1 = memberRegisterService.readMyInfo(email); 
		ModelAndView mv = new ModelAndView("member/mypage", "member", member1); 
		//log.info("######마이페이지 이동get member1: "+member1);
		//log.info("######마이페이지 이동get mv: "+mv);
		return mv;
	}
	
	//내정보수정 페이지 이동
	@GetMapping("/modifymyinfo")
	public ModelAndView goModify(HttpSession session) { 
		String email = (String)session.getAttribute("email");
		//String nickname = (String)session.getAttribute("nickname");
		Member member1 = memberRegisterService.readMyInfo(email); 
		ModelAndView mv = new ModelAndView("member/modifymyinfo", "member", member1); 
		log.info("######내정보수정 이동get email: "+email);
		//log.info("######내정보수정 이동get nickname: "+nickname);
		log.info("######내정보수정 이동get member1: "+member1);
		log.info("######내정보수정 이동get mv: "+mv);
		return mv;
	}
	
	//내정보 수정시 닉네임 중복검사 ajax
	@ResponseBody
	@PostMapping("/modify-myinfo/nicknamevalidcheck")
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
	public String modifymyinfo(Member member, MultipartFile file, HttpSession session) { //HttpServletRequest req 안써도 됨
		
		log.info("#1_내정보수정 진입  member: "+ member);
		log.info("#1_내정보수정시 입력 정보 member: "+ member);
		log.info("#2_현재 세션에 저장된 (수정전) 닉네임 get "+session.getAttribute("nickname")); //스크루바
		log.info("#2_현재 세션에 저장된 (수정전) 마케팅여부 get "+session.getAttribute("marketing")); //0 = 미동의
		
		String ofname = file.getOriginalFilename();
		log.info("#2_내정보수정 newfile: "+ofname);
		
		if(ofname != null)  ofname = ofname.trim();
		if(ofname.length() == 0) { //파일이 존재하지 않을 때 파일없이 update
			Member memberInfo = memberRegisterService.modifyMyInfo(member);
			log.info("#3_내정보수정 성공후 내정보 memberInfo= "+ memberInfo);
			session.setAttribute("nickname", memberInfo.getNickname());
			session.setAttribute("marketing", memberInfo.getMarketing());
			session.setAttribute("profileImg", memberInfo.getProfileImg());
			session.setAttribute("member", memberInfo);
			log.info("#4_현재 세션에 저장된 (수정후) 닉네임 get "+session.getAttribute("nickname")); //스크루바
			log.info("#4_현재 세션에 저장된 (수정후) 마케팅여부 get "+session.getAttribute("marketing")); //0 = 미동의
			log.info("#4_현재 세션에 저장된 (수정후) 프로필사진 get "+session.getAttribute("profileImg"));
			log.info("#4_현재 세션에 저장된 (수정후) member get "+session.getAttribute("member"));
			//Member(email=111@naver.com, pwd=1111, nickname=zeze, profileImg=, name=한서인, phone=01055554, marketing=0, apiUsing=0, signupDate=null, updateDate=null, authority=0, point=0)
			return "redirect:mypage";
			
		} else { //업로드할 파일이 존재할 때
			int idx = ofname.lastIndexOf(".");
			String ofheader = ofname.substring(0,idx); //파일 제목만 뽑기
			String ext = ofname.substring(idx); //확장자만 뽑기
			long ms = System.currentTimeMillis();
			
			StringBuilder sb = new StringBuilder();
			sb.append(ofheader); //파일제목 추가 
			sb.append("_");
			sb.append(ms);
			sb.append(ext);
			String saveFileName = sb.toString();
			int fsize = (int) file.getSize();	
			
			member.setProfileImgOrg(ofname);
			member.setProfileImg(saveFileName);
			member.setProfileImgSize(fsize);
			log.info("#############파일업로드 fileInfoList: "+fileInfoList);
			log.info("#############fileInfoList==null:"+fileInfoList==null);
			log.info("#############fileInfoList.size():"+fileInfoList.size());
			filuploadservice.saveImgFile(file, Path.PROFILE_PATH, fileInfoList); //파일업로드서비스단에서 실제 로컬에 물리적 파일 생성
			
			
			Member memberInfo = memberRegisterService.modifyMyInfo2(member);
			log.info("#5_내정보수정 성공후 내정보 memberInfo= "+ memberInfo);
			session.setAttribute("nickname", memberInfo.getNickname());
			session.setAttribute("marketing", memberInfo.getMarketing());
			session.setAttribute("profileImg", memberInfo.getProfileImg());
			session.setAttribute("member", memberInfo);
			log.info("#6_현재 세션에 저장된 (수정후) 닉네임 get "+session.getAttribute("nickname")); //스크루바
			log.info("#6_현재 세션에 저장된 (수정후) 마케팅여부 get "+session.getAttribute("marketing")); //0 = 미동의
			log.info("#6_현재 세션에 저장된 (수정후) 프로필사진 get "+session.getAttribute("profileImg"));
			log.info("#6_현재 세션에 저장된 (수정후) member get "+session.getAttribute("member"));
			//Member(email=111@naver.com, pwd=1111, nickname=zeze, profileImg=, name=한서인, phone=01055554, marketing=0, apiUsing=0, signupDate=null, updateDate=null, authority=0, point=0)
			return "redirect:mypage";
		}
	}  
      
	//내정보수정 - 현재 나의 프로필사진만 삭제
	@ResponseBody //주석처리시 return ""의 jsp를 찾아서 리턴
	@GetMapping("/removemyprofileimg")
	public String removeMyProfileImg(@RequestParam String profileImg, HttpSession session) { 
		File file = new File(Path.PROFILE_PATH, profileImg); //PROFILE_PATH 밑에 있는 profileImg을 가져옴 
		if(file.exists()) {
			file.delete();
			String email = (String) session.getAttribute("email");
			memberRegisterService.removeProfileImg(email); //mapper.xml(sql) 통해 기본사진으로 모두 변경
			return "delete성공";
		} else {
			return "delete실패-파일이 폴더에 존재하지 않음";
		}
	}
	
	//회원 탈퇴         
	@PostMapping("/removemyinfo")
	public String removeMyinfo(@RequestParam("email") String email, HttpSession session, HttpServletRequest req) { //req 필요
		//System.out.println("#내정보수정 - 회원탈퇴 메소드 진입 email: " + email);
		//본인이거나 관리자가 아닐경우 삭제 불가하도록
		Member member = memberRegisterService.readMyInfo(email);	
		if(member.getEmail().equals((String)session.getAttribute("email"))) {
			memberRegisterService.removeMyInfo(email);
			session.invalidate(); //현재 접속하고 있는 세션을 무효화
			req.getSession(true); //새로운 세션을 받을 준비 true
			return "redirect:/";
		} else {
			return "redirect:/";
		}
	}
	
}
