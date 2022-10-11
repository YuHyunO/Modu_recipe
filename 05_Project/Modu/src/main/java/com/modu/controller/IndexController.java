package com.modu.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		
		// ·©Å· TOP 6 ¸â¹ö È®ÀÎ
		for (Member member: rankList) {
			log.info("#IndexController: " + member);
		}
		
		ModelAndView mv = new ModelAndView("index", "rankList", rankList);
		mv.addObject("recipeList", recipeList);
		return mv;
	}
	
}
