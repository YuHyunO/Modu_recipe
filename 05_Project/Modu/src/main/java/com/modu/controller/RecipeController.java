package com.modu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("recipe")
public class RecipeController {
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
}
