package com.modu.controller;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.modu.domain.member.FollowList;
import com.modu.domain.member.Member;
import com.modu.service.FileUploadService;
import com.modu.service.MemberRegisterService;
import com.modu.service.MembershipService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@AllArgsConstructor
@RequestMapping("mypage")
public class MembershipController {

	private MemberRegisterService memberRegisterService;
	private FileUploadService filuploadservice; //by @AllArgsConstructor
	private MembershipService membershipService;
	
	/*
	//마이페이지 페이지 이동(MemberController.java 에 있는 메소드 가져옴)
	@GetMapping("mypage")
	public ModelAndView goMypage(HttpSession session) {
		String email = (String)session.getAttribute("email");
		Member member1 = memberRegisterService.readMyInfo(email); 
		ModelAndView mv = new ModelAndView("member/mypage", "member", member1); 
		log.info("######마이페이지 이동get member1: "+member1);
		log.info("######마이페이지 이동get mv: "+mv);
		return mv;
	}
	*/
	
    @GetMapping("/main")
    public ModelAndView myPage(HttpServletRequest request, HttpSession session) {    
    	
        ModelAndView mv = new ModelAndView("member/mypage");
        return mv;
    }
    
    @GetMapping("/mypage-recommend")
    public @ResponseBody String recommend(HttpServletRequest request, HttpSession session) {

    	return "1";
    }
    
    @GetMapping("/mypage-recipe")
    public @ResponseBody String recipe(HttpServletRequest request, HttpSession session) {

    	return "2";
    }
    
    @GetMapping("/mypage-bookmark")
    public @ResponseBody String bookmark(HttpServletRequest request, HttpSession session) {

    	return "3";
    }
	
    @GetMapping("/mypage-post")
    public @ResponseBody String post(HttpServletRequest request, HttpSession session) {
    	
    	return "4";
    }
	
    @GetMapping("/mypage-follow")
    public @ResponseBody List<FollowList> follow(HttpServletRequest request, HttpSession session) {
    	
    	List<FollowList> testList = membershipService.getFollowList(request, session);
    	return testList;
    }
		
	//마이페이지 페이지 이동
	/*@GetMapping("gofriendrecipe")
	public ModelAndView goFriendRecipe(HttpSession session) {
		
		//String email = (String)session.getAttribute("email");
		List<FollowList> followlist = membershipService.getFollowList(session);
		
		//Member member1 = memberRegisterService.readMyInfo(email); 
		ModelAndView mv = new ModelAndView("member/mypage", "followlist", followlist);
		
		log.info("######마이페이지 이동get member1: "+followlist);
		log.info("######마이페이지 이동get mv: "+mv);
		
		return mv;
	}*/
	
	
	
	

	
}
