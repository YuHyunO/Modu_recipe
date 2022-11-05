package com.modu.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.modu.domain.member.Scrap;
import com.modu.domain.recipe.RecipeDetail;
import com.modu.domain.recipe.RecipeList;
import com.modu.domain.recipe.RecipeListVo;
import com.modu.domain.recipe.RecipeNestedReply;
import com.modu.domain.recipe.RecipeReply;
import com.modu.domain.recipe.RecipeReplyList;
import com.modu.domain.recipe.RecipeReplyPhoto;
import com.modu.mapper.RecipeLegacyMapper;
import com.modu.service.MembershipService;
import com.modu.service.RecipeFindingService;
import com.modu.service.RecipeRegisterService;
import com.modu.service.RecipeSearchService;

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
    private RecipeSearchService searchService;
    @Autowired
    private MembershipService membershipService;
    @Autowired
    private RecipeLegacyMapper recipeLegacyMapper;
  
    @GetMapping("/list")
    public ModelAndView recipeList(HttpServletRequest request, HttpSession session) {
        RecipeListVo data = searchService.searchRecipe(request, session);
        ModelAndView mv = new ModelAndView("recipe/list", "data", data);
        return mv;
    }

    @GetMapping("/list.do")
    public @ResponseBody RecipeListVo updateRecipeList(HttpServletRequest request, HttpSession session) {
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
        // recipeRegisterService.registerRecipe(request, session);
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
        if ((String) session.getAttribute("email") == null) {
            map.put("msg", "로그인 후 이용 해주세요");
        } else {
            recipeRegisterService.registerRecipe(request, session, files, mainItems, subItems, directions, tags);
            log.info("#####1" + tags);
            map.put("msg", "서비스로 데이터 전송 성공");
        }
        return map;
    }

    @GetMapping("/update")
    public ModelAndView RecipeRead(long id, HttpSession session) {
        RecipeDetail recipeDetail = recipeFindingService.RecipeRead(id);
        ModelAndView mv = new ModelAndView();
        String email = (String)session.getAttribute("email");
        String recipeEmail = recipeDetail.getRecipe().getMEmail();
        
        if (!recipeEmail.equals(email)) {
            mv.setViewName("redirect:/");
        } else {
            mv.setViewName("recipe/update");
            mv.addObject("rs", recipeDetail);
            mv.addObject("id", id);
        }
        return mv;
    }

    @ResponseBody
    @PostMapping("/update")
    public HashMap<String, Object> updateRecipe(
            HttpServletRequest request,
            HttpSession session,
            @RequestParam(value="files", required=false) ArrayList<MultipartFile> files,
            @RequestParam ArrayList<String> mainItems,
            @RequestParam ArrayList<String> subItems,
            @RequestParam ArrayList<String> directions,
            @RequestParam ArrayList<String> tags,
            @RequestParam ArrayList<String> fileChanges) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        log.info("#updateRecipe");
        recipeRegisterService.updateRecipe(request, session, files, mainItems, subItems, directions, tags, fileChanges);
        return map;
    }
    
    //레시피 삭제
    @PostMapping("/delete")
    public String deleteRecipe(long id, HttpServletRequest request) {
        //HttpSession session = request.getSession();
        String getId = request.getParameter("id");
        id = Integer.parseInt(getId);      
        recipeRegisterService.recipeDelete(id);
        log.info("레시피 삭제 성공 in controller");
        return "redirect:list";
    }

    @GetMapping("/detail")
    public ModelAndView recipeDetail(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        String email = (String)session.getAttribute("email");
        long id = 0;
        try {
            id = Long.parseLong(request.getParameter("no"));
        }catch(NumberFormatException nfe) {
            return  new ModelAndView("redirect:/");
        }
        RecipeDetail detail = recipeFindingService.getRecipeDetails(id, request, response);
        String starPoint = recipeFindingService.getStarPoint(detail);

        boolean scrapState = false;
        if (email != null) {
            if (recipeFindingService.getScrap(id, email) == null) {
                // 스크랩 중 아님
            } else {
                scrapState = true;
            }
        }
        long replyCount = recipeLegacyMapper.selectReplyCount(id);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("recipe/detail");
        mv.addObject("detail", detail);        
        mv.addObject("starPoint", starPoint);
        mv.addObject("scrapState", scrapState);
        mv.addObject("replyCount", replyCount);
        return mv;
    }
    
    @GetMapping("/recent-recipe")
    public @ResponseBody List<RecipeList> callRecentRecipe(HttpServletRequest request){
        List<RecipeList> data = recipeFindingService.findRecentRecipes(request);
        return data;
    }

    @GetMapping("/recipe-reply")
    public @ResponseBody List<RecipeReplyList> callReplyData(HttpServletRequest request){
        
        List<RecipeReplyList> data = recipeFindingService.getReply(request);
        return data;
    }
    
    @GetMapping("/recipe-nested-reply")
    public @ResponseBody List<RecipeNestedReply> callNestedReplyData(HttpServletRequest request){
        
        List<RecipeNestedReply> data = recipeFindingService.getNestedReply(request);       
        return data;
    }
    
    @PostMapping("/insert.do")
    public @ResponseBody String insertReply(RecipeReply recipeReply, MultipartFile file, RecipeReplyPhoto replyPhoto){
        System.out.println("zzzzz");
        String result = recipeRegisterService.registerReply(recipeReply);
        recipeRegisterService.registerReplyPhoto(replyPhoto);
        System.out.println(result);
        System.out.println(replyPhoto);
        log.info("#recipeReply" + recipeReply);
        return result;
    }
    @GetMapping("/del.do")
    public String deleteReply(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String paramId = request.getParameter("id");
        long id = Long.parseLong(paramId);
        recipeRegisterService.deleteReply(id);
        return "redirect:detail";
    }
    @PostMapping("/insertNestedReply.do")
    public @ResponseBody String insertNestedReply (RecipeNestedReply recipeNestedReply) {
        System.out.println("# "+recipeNestedReply);    
        String result = recipeRegisterService.registerNestedReply(recipeNestedReply);
        System.out.println("#nestedReply" + recipeNestedReply);
        return result;
    } 
    @GetMapping("/delNestedReply.do")
    public String delNestedReply(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String paramId = request.getParameter("id");
        long id = Long.parseLong(paramId);
        recipeRegisterService.deleteNestedReply(id);
        return "redirect:detail";
    }

    @ResponseBody
    @PostMapping("/scrap/insert")
    public HashMap<String, Object> insertScrap(HttpServletRequest request, HttpSession session) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String id = (String) request.getParameter("id");
        String email = (String) session.getAttribute("email");
        String msg;
        long rId = Long.parseLong(id);

        if (email == null) {
            map.put("error", "스크랩 기능은 로그인 후 이용하실 수 있습니다.");
            return map;
        } else {
            msg = membershipService.scrapService(rId, email, 1);
            map.put("user", email);
            map.put("msg", msg);
            return map;
        }
    }

    @ResponseBody
    @PostMapping("/scrap/delete")
    public HashMap<String, Object> deleteScrap(HttpServletRequest request, HttpSession session) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String id = (String) request.getParameter("id");
        String email = (String) session.getAttribute("email");
        String msg;
        long rId = Long.parseLong(id);

        if (email == null) {
            map.put("error", "스크랩 기능은 로그인 후 이용하실 수 있습니다.");
            return map;
        } else {
            msg = membershipService.scrapService(rId, email, -1);
            map.put("user", email);
            map.put("msg", msg);
            return map;
        }
    }

    @ResponseBody
    @PostMapping("/follow/insert")
    public HashMap<String, Object> insertFollow(HttpServletRequest request, HttpSession session) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String targetEmail = request.getParameter("targetEmail");
        String email = (String) session.getAttribute("email");
        String msg;
        String result;

        if (email == null) {
            map.put("error", "친구 추가는 로그인 후 이용하실 수 있습니다.");
            return map;
        } else {
            msg = membershipService.followService(targetEmail, email, 1);
            map.put("user", email);
            map.put("msg", msg);
            return map;
        }
    }

    @ResponseBody
    @PostMapping("/follow/delete")
    public HashMap<String, Object> deleteFollow(HttpServletRequest request, HttpSession session) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String targetEmail = request.getParameter("targetEmail");
        String email = (String) session.getAttribute("email");
        String msg;
        String result;

        if (email == null) {
            map.put("error", "친구추가는 로그인 후 이용하실 수 있습니다.");
            return map;
        } else {
            msg = membershipService.followService(targetEmail, email, -1);
            map.put("user", email);
            map.put("msg", msg);
            return map;
        }
    }

    @ResponseBody
    @PostMapping("/follow/check")
    public HashMap<String, Object> checkFollow(HttpServletRequest request, HttpSession session) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String targetEmail = request.getParameter("targetEmail");
        String email = (String) session.getAttribute("email");
        String msg;
        String result;

        if (email != null) {
            msg = membershipService.followService(targetEmail, email, 0);
            map.put("user", email);
            map.put("state", msg); // 팔로우 서비스에서 친구이면 true 아니면 false 리턴
        } else {
            msg = "사용자가 로그인 중이지 않음";
            map.put("state", "false");
        }
        return map;
    }
}
