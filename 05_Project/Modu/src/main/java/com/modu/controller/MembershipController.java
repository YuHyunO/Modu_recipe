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
	
    @GetMapping("/main")
    public ModelAndView myPage(HttpServletRequest request, HttpSession session) {    
        ModelAndView mv = new ModelAndView("member/mypage");
        log.info("#마이페이지 진입 mv: "+mv); //model is null
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
    
    @GetMapping("/recent-recipe")
    public @ResponseBody List<RecipeList> callRecentRecipe(HttpServletRequest request){
        List<RecipeList> data = recipeFindingService.findRecentRecipes(request);
        return data;
    }	

	
}
