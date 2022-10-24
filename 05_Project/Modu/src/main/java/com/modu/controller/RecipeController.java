package com.modu.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.imageio.spi.RegisterableService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

<<<<<<< HEAD
import com.modu.domain.recipe.Direction;
import com.modu.domain.recipe.Ingredient;
import com.modu.domain.recipe.Recipe;
=======
import com.modu.domain.member.Scrap;
>>>>>>> 844e045a256e24b6289486ec9ae5bfca9298244e
import com.modu.domain.recipe.RecipeDetail;
import com.modu.domain.recipe.RecipeListVo;
import com.modu.domain.recipe.RecipeReply;
import com.modu.domain.recipe.RecipeReplyList;
<<<<<<< HEAD
import com.modu.domain.recipe.RecipeTag;
=======
import com.modu.mapper.RecipeLegacyMapper;
>>>>>>> 844e045a256e24b6289486ec9ae5bfca9298244e
import com.modu.service.RecipeFindingService;
import com.modu.service.RecipeRegisterService;
import com.modu.service.SearchService;

import lombok.extern.log4j.Log4j;
import oracle.jdbc.proxy.annotation.Post;


@Log4j
@Controller
@RequestMapping("recipe")
public class RecipeController {
	@Autowired
	private RecipeFindingService recipeFindingService;
	@Autowired
	private RecipeRegisterService recipeRegisterService;
	@Autowired
<<<<<<< HEAD
	private SearchService searchService;
=======
	private RecipeSearchService searchService;
	@Autowired
	private RecipeLegacyMapper recipeLegacyMapper;

>>>>>>> 844e045a256e24b6289486ec9ae5bfca9298244e

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

//	@ResponseBody
//	@PostMapping("/write")
//	public String submit(HttpServletRequest request, HttpSession session) {
//		//log.info("########write: " + request.getParameter("food"));
//		//recipeRegisterService.registerRecipe(request, session);
//		return "redirect:/";
//	}
	
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
	        map.put("msg", "Î°úÍ∑∏Ïù∏ ÌõÑ Ïù¥Ïö© Ìï¥Ï£ºÏÑ∏Ïöî");
	    } else {
            recipeRegisterService.registerRecipe(request, session, files, mainItems, subItems, directions, tags);
            log.info("#####1" + tags);
	        map.put("msg", "ÏÑúÎπÑÏä§Î°ú Îç∞Ïù¥ÌÑ∞ Ï†ÑÏÜ° ÏÑ±Í≥µ");
	    } 
	    return map;
	}
	
	@GetMapping("/update")
	public ModelAndView RecipeRead(long id) {//HttpSession session
	    RecipeDetail recipeDetail = recipeFindingService.RecipeRead(id);
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("recipe/update");
	    mv.addObject("rs", recipeDetail);
	    mv.addObject("id", id);
	    return mv;
	}
	@PostMapping("/update")
	public String updateRecipe(long id) {
	    recipeRegisterService.updateRecipe(id);
	    return "redirect:detail";
	}
	/* ÌéòÏù¥ÏßÄ Ïó∞Îèô ÌõÑ idÍ∞íÏùÑ Ï∞æÏùÑÎñÑ ÏÇ¨Ïö©Îê† Î°úÏßÅ
	@GetMapping("/update")
    public ModelAndView RecipeRead(HttpSession session) {//
        String ids = (String)session.getAttribute("id");
        long id = Integer.parseInt(ids);
        long rId = id;
        RecipeDetail recipeDetail = recipeFindingService.RecipeRead(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("recipe/update");
        mv.addObject("rs", recipeDetail);
        mv.addObject("id", id);
        return mv;
    }
	*/
	
//	@PostMapping("/update")
//	public String  updateRecipe(RecipeDetail recipeDetail,
//	        HttpServletRequest request,
//            HttpSession session,
//            ArrayList<MultipartFile> files,
//            ArrayList<String> mainItems,
//            ArrayList<String> subItems,
//            ArrayList<String> directions,
//            ArrayList<String> tags) {
//	    recipeRegisterService.updateRecipe(recipeDetail, request, 
//	            session, files, mainItems,subItems,directions, tags);
//	    return "redirect:detail";
//	}
	
	@ResponseBody
	@PostMapping("/update")
	public String updateRecipe(@RequestParam long id,
	        @RequestParam HttpServletRequest request,
	        @RequestParam HttpSession session,
	        @RequestParam ArrayList<MultipartFile> files, 
	        @RequestParam ArrayList<String> mainItems,
	        @RequestParam ArrayList<String> subItems,
	        @RequestParam ArrayList<String> directions,
	        @RequestParam ArrayList<String> tags) {
	    Recipe recipe = new Recipe();
	    Ingredient ingredient = new Ingredient();
	    Direction direction = new Direction();
	    RecipeTag recipeTag = new RecipeTag();
	    recipeRegisterService.updateRecipe(id,request,session,files,mainItems,subItems,directions,tags);
	   return "redirect:detail";
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
	 * ->ÎåÄÎåìÍ∏Ä/ÎØ∏ÏôÑ
	 * 
	 * @PostMapping("/insertRreply.do") public @ResponseBody String
	 * insertNestedReply (RecipeNestedReply recipeNestedReply){ String result =
	 * recipeRegisterService.registerNestedReply(recipeNestedReply);
	 * log.info("#recipeNestedReply" +recipeNestedReply); return result; }
	 */
	
	@ResponseBody
	@PostMapping("/scrap")
	public HashMap<String, Object> scrap(HttpServletRequest request, HttpSession session){
	    
	    HashMap<String, Object> map = new HashMap<String, Object>();
	    String id = (String)request.getParameter("id");
	    String email = (String)session.getAttribute("email");
	    if (email == null) {
	        map.put("error", "Ω∫≈©∑¶ ±‚¥…¿∫ ∑Œ±◊¿Œ »ƒ ¿ÃøÎ«“ ºˆ ¿÷Ω¿¥œ¥Ÿ.");
	        return map;
	    } else {
    	    long rId = Long.parseLong(id);
    	    Scrap scrap = new Scrap();
    	    scrap.setRId(rId);
    	    scrap.setMEmail(email);
    	    
    	    Scrap scrap1 = recipeFindingService.getScrap(rId, email);
    	    String emailInScrap; 
    	    try {
    	        // Ω∫≈©∑¶¡ﬂ
    	        emailInScrap = scrap1.getMEmail();
    	    } catch (NullPointerException npe) {
    	        emailInScrap = "Ω∫≈©∑¶æ∆¥‘";
    	        log.info("#scrap recipe id: " + rId);
    	        log.info("#scrap email: " + email);
    	        recipeLegacyMapper.insertScrap(email, rId);
    	    }
    	    log.info("#scrap recipe id: " + id);
    	    log.info("#scrap email: " + email);
    	    log.info("#scrap scrap: " + scrap1);
    	    log.info("#scrap emailInScrap: " + emailInScrap);
    	    map.put("user", email);
    	    return map;
	    }
	}
}
