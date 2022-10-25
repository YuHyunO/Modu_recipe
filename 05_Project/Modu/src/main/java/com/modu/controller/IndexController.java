package com.modu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.modu.domain.member.Member;
import com.modu.domain.recipe.RecipeList;
import com.modu.service.MembershipService;
import com.modu.service.RecipeFindingService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class IndexController {
	
	@Autowired
	private MembershipService membershipService;
	
	@Autowired
	private RecipeFindingService recipeFindingservice;
	
	@GetMapping("/")
	public ModelAndView index() {
		long beginRow, endRow;
		beginRow = 1;
		endRow = 8;
		
		List<Member> rankList = membershipService.selectMemberRankS();
		List<RecipeList> recipeList = recipeFindingservice.selectRecipeListByBestHits(beginRow, endRow);
		
		// 랭킹 TOP 6 멤버 확인
		for (Member member: rankList) {
			log.info("#IndexController: " + member);
		}
        // 베스트 레시피(8개) 확인
        for (RecipeList recipeList1: recipeList) {
            log.info("#IndexController: " + recipeList1);
        }
		ModelAndView mv = new ModelAndView("index", "rankList", rankList);
		mv.addObject("recipeList", recipeList);
		return mv;
	}
	
}
