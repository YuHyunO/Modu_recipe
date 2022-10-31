package com.modu.controller;

import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.modu.domain.member.FollowList;
import com.modu.domain.member.FollowListVo;
import com.modu.domain.member.Member;
import com.modu.domain.recipe.RecipeList;
import com.modu.domain.recipe.RecipeListVo;
import com.modu.service.FileUploadService;
import com.modu.service.MemberRegisterService;
import com.modu.service.MembershipService;
import com.modu.service.RecipeFindingService;
import com.modu.service.RecipeSearchService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@AllArgsConstructor
@RequestMapping("mypage")
public class MembershipController {

	private MembershipService membershipService;
	private RecipeSearchService recipeSearchService;
	private RecipeFindingService recipeFindingService;
	
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
	
    @GetMapping("/follow")
    public @ResponseBody FollowListVo follow(HttpServletRequest request, HttpSession session) {
            	
        FollowListVo data = membershipService.getFollowList(request, session);      
        return data;
    }

    @PostMapping("/unfollow")
    public String removeFollow(@RequestParam("id") int id, HttpServletRequest request, HttpSession session) {
                
        membershipService.removeMyFollow(id);      
        return "success";
    }
/*
    //회원 탈퇴         
    @PostMapping("/removemyinfo")
    public String removeMyinfo(@RequestParam("email") String email, HttpSession session, HttpServletRequest req) { //req 필요
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
    */
    
    @GetMapping("/recent-recipe")
    public @ResponseBody List<RecipeList> callRecentRecipe(HttpServletRequest request){
        List<RecipeList> data = recipeFindingService.findRecentRecipes(request);
        return data;
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
