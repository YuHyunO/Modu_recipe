package com.modu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.modu.domain.recipe.Direction;
import com.modu.domain.recipe.Ingredient;
import com.modu.domain.recipe.Recipe;
import com.modu.domain.recipe.RecipeTag;

import org.springframework.web.servlet.ModelAndView;
import com.modu.domain.recipe.RecipeDetail;
import com.modu.domain.recipe.RecipeNestedReply;
import com.modu.domain.recipe.RecipeReply;
import com.modu.domain.recipe.RecipeReplyList;
import com.modu.service.FileUploadService;
import com.modu.service.RecipeFindingService;
import com.modu.service.RecipeRegisterService;

import lombok.extern.log4j.Log4j;

import com.modu.fileset.Path;

@Log4j
@Controller
@RequestMapping("recipe")
public class RecipeController {

	@Autowired
	private RecipeFindingService recipeFindingService;

	@Autowired
	private RecipeRegisterService recipeRegisterService;

	@Autowired
	private FileUploadService fileUploadService;
	
	private ArrayList<String> fileInfoList = new ArrayList<String>();

	@GetMapping("/list")
	public String recipeList() {
		return "recipe/list";
	}

	@GetMapping("/write")
	public String recipeWrite() {
		return "recipe/write";
	}

	@ResponseBody
	@PostMapping("/write")
	public String submit(HttpServletRequest request, HttpSession session) {
		log.info("########write: " + request.getParameter("food"));
		recipeRegisterService.registerRecipe(request, session);
		return "redirect:/";
	}

	@PostMapping("/upload")
	public String upload(@RequestParam ArrayList<MultipartFile> files, 
			HttpServletRequest request, HttpSession session) {
		// 여러개의 파일을 업로딩
		String text = "STEP-";
		int num = 0;
		String numS;
		if(files != null) {
			for (MultipartFile file : files) {
				if(file != null) {	
					numS = Integer.toString(num);
					String ofname = file.getOriginalFilename();
					fileInfoList.clear();
					fileInfoList.add(text + numS);
					if (ofname != null) ofname = ofname.trim();
					if (ofname.length() != 0) {
						String url = fileUploadService.saveImgFile(file, Path.RECIPE_PATH + "\\temp\\", fileInfoList);
						log.info("#url: " + url);
					}	
				}
				num += 1;
			}
		}
		return "redirect:write";
	}

	@GetMapping("/detail")
	public ModelAndView recipeDetail() {
		long id = 150;
		RecipeDetail recipeDetail = recipeFindingService.findRecipedetails(id);
		String starPoint = recipeFindingService.getStarPoint(recipeDetail);
		List<RecipeReplyList> selectReply = recipeRegisterService.findRecipeReply(id);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("recipe/detail");
		mv.addObject("rec", recipeDetail);
		mv.addObject("rep", selectReply);
		mv.addObject("id", id);
		mv.addObject("starPoint", starPoint);
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
	 * ->대댓글/미완
	 * 
	 * @PostMapping("/insertRreply.do") public @ResponseBody String
	 * insertNestedReply (RecipeNestedReply recipeNestedReply){ String result =
	 * recipeRegisterService.registerNestedReply(recipeNestedReply);
	 * log.info("#recipeNestedReply" +recipeNestedReply); return result; }
	 */
}
