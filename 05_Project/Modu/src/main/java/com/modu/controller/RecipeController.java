package com.modu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.modu.domain.recipe.Direction;
import com.modu.domain.recipe.Ingredient;
import com.modu.domain.recipe.Recipe;
import com.modu.domain.recipe.RecipeTag;
import com.modu.service.RecipeFindingService;
import com.modu.service.RecipeRegisterService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("recipe")
public class RecipeController {
	
	@Autowired
	private RecipeRegisterService recipeRegisterServcie;
	
	@GetMapping("/list")
	public String recipeList() {
		return "recipe/list";
	}

	@GetMapping("/detail")
	public String recipeDetail() {
		return "recipe/detail";
	}

	@GetMapping("/write")
	public String recipeWrite() {
		return "recipe/write";
	}
	@ResponseBody
	@PostMapping("/write")
	public String submit(HttpServletRequest request, HttpSession session) {
		recipeRegisterServcie.registerRecipe(request, session);		
		return "redirect:/";
	}
}
