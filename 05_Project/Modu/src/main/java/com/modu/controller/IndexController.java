package com.modu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.modu.domain.member.Member;
import com.modu.domain.recipe.RecipeList;
import com.modu.service.MembershipService;
import com.modu.service.RecipeFindingService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor
@Controller
public class IndexController {
	
	private MembershipService membershipService;
	private RecipeFindingService recipeFindingService;	
	
	@GetMapping("/")
	public ModelAndView index() {
		long beginRow, endRow;
		beginRow = 1;
		endRow = 8;
		
		List<Member> rankList = membershipService.selectMemberRankS();
		List<RecipeList> bestRecipeList = recipeFindingService.getBestRecipeList(beginRow, endRow);
		List<RecipeList> latestRecipeList = recipeFindingService.getLatestRecipeList(4);
		
		ModelAndView mv = new ModelAndView("index", "rankList", rankList);
		mv.addObject("bestRecipeList", bestRecipeList);
		mv.addObject("latestRecipeList", latestRecipeList);
		log.info("#IndexController index() bestRecipeList: " + bestRecipeList);
		log.info("#IndexController index() latestRecipeList: " + latestRecipeList);
		return mv;
	}
	
    @GetMapping("/recent-recipe")
    public @ResponseBody List<RecipeList> callRecentRecipe(HttpServletRequest request){
        List<RecipeList> data = recipeFindingService.findRecentRecipes(request);
        return data;
    }
}
