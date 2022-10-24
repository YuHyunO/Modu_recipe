package com.modu.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.modu.domain.member.FollowList;
import com.modu.domain.member.FollowListVo;
import com.modu.domain.member.Member;
import com.modu.domain.recipe.RecipeListVo;
import com.modu.service.FileUploadService;
import com.modu.service.MemberRegisterService;
import com.modu.service.MembershipService;
import com.modu.service.RecipeSearchService;

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
	private RecipeSearchService recipeSearchService;
	
    //MemberController.java에 있는 마이페이지 페이지 이동
/*    @GetMapping("/main")
    public ModelAndView gomyPage(HttpServletRequest request, HttpSession session) {    
        String email = (String)session.getAttribute("email");
        Member member1 = memberRegisterService.readMyInfo(email); 
        ModelAndView mv = new ModelAndView("member/mypage", "member", member1);         
        log.info("######마이페이지 이동get member1: "+member1);
        log.info("######마이페이지 이동get mv: "+mv);
        return mv;
    }*/

    @GetMapping("/main")
    public ModelAndView myPage(HttpServletRequest request, HttpSession session) {    
        ModelAndView mv = new ModelAndView("member/mypage");
        return mv;
    }
    
    @GetMapping("/recommend")
    public @ResponseBody RecipeListVo recommend(HttpServletRequest request, HttpSession session) {        
        
        RecipeListVo data = recipeSearchService.searchRecipeByIngredient(request, session);
    	return data;
    }
    
    @GetMapping("/recipe")
    public @ResponseBody RecipeListVo myRecipe(HttpServletRequest request, HttpSession session) {
        
        RecipeListVo data = recipeSearchService.searchRecipeOfMember(request, session);
    	return data;
    }
    
    @GetMapping("/bookmark")
    public @ResponseBody RecipeListVo bookmark(HttpServletRequest request, HttpSession session) {
        
        RecipeListVo data = recipeSearchService.searchRecipeOfBookmark(request, session);
    	return data;    
    }
	
    @GetMapping("/post")
    public @ResponseBody String post(HttpServletRequest request, HttpSession session) {
    	
    	return "4";
    }
	
    //탭5- 친구 관리(팔로잉 친구 목록)
    @GetMapping("/follow")
    public @ResponseBody FollowListVo getfollowing(HttpServletRequest request, HttpSession session) {
    	
        FollowListVo data = membershipService.getFollowList(request, session);
    	return data;
    }
    
    //탭5- 친구 레시피 목록 이동
	@GetMapping("/friendrecipe")
	public ModelAndView goFriendRecipe(HttpSession session) { //HttpServletRequest req
        String email = (String)session.getAttribute("email");
        Member member1 = memberRegisterService.readMyInfo(email); 
        ModelAndView mv = new ModelAndView("member/mypage", "member", member1); 
	    log.info("######마이페이지-친구관리탭 선택 get mv: "+mv);
		return mv;
	}
	
    //탭5- 친구 끊기
    @GetMapping("/unfollow")
    public void unfollow(@RequestParam("id") long id) {
        membershipService.unfollowFriend(id);
        log.info("#마이페이지 탭5 unfollow 메소드 id, session:"+ id);
    }
}
