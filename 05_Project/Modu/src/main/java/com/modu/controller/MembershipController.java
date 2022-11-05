package com.modu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.modu.domain.member.FollowListVo;
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
	
	//마이
    @GetMapping("/main")
    public ModelAndView goMypage2(HttpSession session) {    
        ModelAndView mv = new ModelAndView("mypage/main");
        return mv;
    }
    
    @GetMapping("/recommend")
    public @ResponseBody RecipeListVo recommend(HttpServletRequest request, HttpSession session) {        
        
        RecipeListVo data = recipeSearchService.searchRecipeByIngredient(request, session);
        //log.info("마이페이지 탭1-추천레시피 받아온 데이터 data: "+data);
        //RecipeListVo(recipeList=[RecipeList(id=631, foodPhoto=7_1666235571492.jpg, title=숙주로 간단한 나시고랭 볶음밥 만들기😉✨, food=나시고랭, profileImg=favicon_1666336841209.ico, mNickname=그루터기1, mEmail=111@naver.com, star=0.0, stars=0, hits=1, sort=null),
        //RecipeList(id=632, foodPhoto=7_1666595387511.jpg, title=맛있는 새우볶음밥임니다, food=새우볶음밥, profileImg=favicon_1666336841209.ico, mNickname=그루터기1, mEmail=111@naver.com, star=0.0, stars=0, hits=1, sort=null)], 
        //currentPage=1, pageSize=0, totalPage=1)
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
    //ȸ�� Ż��         
    @PostMapping("/removemyinfo")
    public String removeMyinfo(@RequestParam("email") String email, HttpSession session, HttpServletRequest req) { //req �ʿ�
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
