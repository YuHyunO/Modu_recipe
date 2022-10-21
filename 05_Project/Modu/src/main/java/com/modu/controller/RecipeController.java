package com.modu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.modu.domain.recipe.RecipeDetail;
import com.modu.domain.recipe.RecipeListVo;
import com.modu.domain.recipe.RecipeReply;
import com.modu.domain.recipe.RecipeReplyList;
import com.modu.service.RecipeFindingService;
import com.modu.service.RecipeRegisterService;
import com.modu.service.SearchService;

import lombok.extern.log4j.Log4j;


@Log4j
@Controller
@RequestMapping("recipe")
public class RecipeController {
	@Autowired
	private RecipeFindingService recipeFindingService;
	@Autowired
	private RecipeRegisterService recipeRegisterService;
	@Autowired
	private SearchService searchService;

    @GetMapping("/list")
    public ModelAndView recipeList(HttpServletRequest request, HttpSession session) {
        RecipeListVo data = searchService.searchRecipe(request, session);
        ModelAndView mv = new ModelAndView("recipe/list", "data", data);
        return mv;
    }
    
    @GetMapping("/list.do")
    public @ResponseBody RecipeListVo updateRecipeList(HttpServletRequest request, HttpSession session){
        RecipeListVo data = searchService.searchRecipe(request, session);
        return data;
    }

	@GetMapping("/write")
	public String recipeWrite() {
		return "recipe/write";
	}

	@ResponseBody
	@PostMapping("/write")
	public String submit(HttpServletRequest request, HttpSession session) {
		log.info("########write: " + request.getParameter("food"));
		//recipeRegisterService.registerRecipe(request, session);
		return "redirect:/";
	}
	
	@ResponseBody
	@PostMapping("/register")
	public HashMap<String, Object> register(
	        HttpServletRequest request,
            HttpSession session,
	        @RequestParam ArrayList<MultipartFile> files, 
			@RequestParam ArrayList<String> mainItems,
			@RequestParam ArrayList<String> subItems,
			@RequestParam ArrayList<String> directions,
			@RequestParam ArrayList<String> tags) {
	    HashMap<String, Object> map = new HashMap<String, Object>();
	    log.info("#RecipeController Upload");
	    if((String)session.getAttribute("email") == null) {
	        map.put("msg", "로그인 후 이용 해주세요");
	    } else {
            recipeRegisterService.registerRecipe(request, session, files, mainItems, subItems, directions, tags);
            log.info("#####1" + tags);
	        map.put("msg", "서비스로 데이터 전송 성공");
	    } 
	    return map;
	}
	
	@GetMapping("/read")
	public ModelAndView RecipeRead(long id) {
	    RecipeDetail recipeDetail = recipeFindingService.RecipeRead(id);
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("recipe/read");
	    mv.addObject("rs", recipeDetail);
	    mv.addObject("id", id);
	    return mv;
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
