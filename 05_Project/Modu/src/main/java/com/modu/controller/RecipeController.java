package com.modu.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.modu.domain.recipe.RecipeDetail;
import com.modu.domain.recipe.RecipeNestedReply;
import com.modu.domain.recipe.RecipeReply;
import com.modu.domain.recipe.RecipeReplyList;
import com.modu.service.RecipeFindingService;
import com.modu.service.RecipeRegisterService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("recipe")
public class RecipeController {

	@Autowired
	private RecipeFindingService recipeFindingService;
	private RecipeRegisterService recipeRegisterService;
	
	@GetMapping("/list")
	public String recipeList() {
		return "recipe/list";
	}
	
	@GetMapping("/write")
	public String recipeWrite() {
		return "recipe/write";
	}
	
	@GetMapping("/detail")
	public ModelAndView recipeDetail() {
		long id = 150;
		RecipeDetail recipeDetail = recipeFindingService.findRecipedetails(id);
		List<RecipeReplyList> selectReply = recipeRegisterService.findRecipeReply(id);
		// System.out.println("####"+recipe);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("recipe/detail");
		mv.addObject("rec", recipeDetail);
		mv.addObject("rep", selectReply);
		mv.addObject("id", id);
		// System.out.println("########");
		return mv;
	}

	@PostMapping("/insert.do")
	public @ResponseBody String insertReply(RecipeReply recipeReply) {
		String result = recipeRegisterService.registerReply(recipeReply);
		log.info("#recipeReply" + recipeReply);
		return result;
	}

	@GetMapping("/del.do")
	public String deleteReply(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// System.out.println("session"+session);
		String paramId = request.getParameter("id");
		long id = Long.parseLong(paramId);
		// System.out.println("id"+id);
		recipeRegisterService.delete(id);
		return "redirect:detail";
	}
	/*
	 * ->����/�̿�
	 * 
	 * @PostMapping("/insertRreply.do") public @ResponseBody String
	 * insertNestedReply (RecipeNestedReply recipeNestedReply){ String result =
	 * recipeRegisterService.registerNestedReply(recipeNestedReply);
	 * log.info("#recipeNestedReply" +recipeNestedReply); return result; }
	 */

}
